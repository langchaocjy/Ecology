package com.customization.newKQ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

import com.customization.AllUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.datasource.DataSource;
import weaver.interfaces.schedule.BaseCronJob;
/***
 * Author:泛微陈嘉源
 * **/
public class CronJob_1
  extends BaseCronJob
{
  private Logger logger = LoggerFactory.getLogger(CronJob_1.class);
  private String cronExpr;
  private newKQUtil kqUtil=new newKQUtil();
  private DataSource ds = (DataSource)StaticObj.getServiceByFullname(("datasource.KQJ"), DataSource.class);
  public void setCronExpr(String cronExpr)
  {
    this.cronExpr = cronExpr;
  }
  public String getCronExpr()
  {
    return this.cronExpr;
  }
  public void execute()
  {
      this.logger.info("=============================run " + getClass().getName() + " job...");
	  //连接外部考勤机，同步数据到uf_tbwbkqjsj
	   Connection conn = null;
	   try {
		   int count = 0;
		   conn = ds.getConnection();
		   ResultSet rs1 = conn.createStatement().executeQuery("select count(*) cnt from USERINFO LEFT JOIN CHECKINOUT on USERINFO.USERID=checkinout.userid ");
	       if (rs1.next()) {
			    count = Util.getIntValue(rs1.getString("cnt"));
		   }
	       int page = count/1000;
	       this.logger.info("总条数 : "+count+"  页数："+page);
	       int begrow = 0;
	       int endrow = 0;
	       for (int i = 1; i < page+2; i++) {
	    	    if (i==1) {
	                begrow = 1;
	                endrow = 1000*begrow;
			    }else{
			    	begrow = endrow+1;
			    	endrow = endrow+999;
			    }
	    	    this.logger.info("begrow : "+begrow+" endrow:"+endrow);
	    	    Allcomp(begrow,endrow);
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }finally{
		   try {
			  conn.close();
		   } catch (Exception e2) {
			  e2.printStackTrace();
		   }
	   }
		   this.logger.info("=============================jieshu " + getClass().getName() + " job...");
  }
  public void Allcomp(int begrow,int endrow){
      RecordSet rs = new RecordSet();
	
	   Connection conn = null;
	   try {
		   conn = ds.getConnection();
		   ResultSet rs2 = conn.createStatement().executeQuery("select * from ("
		   		+ "select *,ROW_NUMBER() OVER(order by t1.USERid DESC) as Row FROM("
		   		+ "SELECT USERINFO.USERID,USERINFO.BADGENUMBER,USERINFO.NAME,CHECKINOUT.CHECKTIME from USERINFO LEFT JOIN CHECKINOUT on USERINFO.USERID=checkinout.userid)"
		   		+ " as t1)"
		   		+ " as t2 "
		   		+ " where t2.Row BETWEEN "+begrow+" and "+endrow+"");
          while (rs2.next()) {
       	   String xm=Util.null2String(rs2.getString("name"));//姓名
       	   String checktime=Util.null2String(rs2.getString("checktime"),"9999-99-99 99:99").substring(0,16);//打卡日期时间  2016-11-01 20:54:31.000
       	   String[] ctarr=checktime.split(" ");
       	   String date=Util.null2String(ctarr[0]);//打卡日期
       	   
       	   JSONObject json= newKQUtil.selectName(xm);
       	   String personid = Util.null2String(json.getString("personid"),"0");//人员id
       	   int departmentid = Util.getIntValue(json.getString("departmentid"),0);//部门id
       	   
       	   String paiban = "";
       	   JSONObject partyon18json = null;
       	   if (departmentid == 18) {
       		   JSONObject pbdetail = kqUtil.JudgeSchedul(personid,date);//根据人员id和日期找到对应的排班
       		   String field001=Util.null2String(pbdetail.getString("field001"));//班次名称
       		   String field002=Util.null2String(pbdetail.getString("field002"));//排班开始日期
       		   String field003=Util.null2String(pbdetail.getString("field003"));//排班结束日期
       		   String aaaaaaaa=Util.null2String(pbdetail.getString("field007"));//工作总时间
       		   int field007=Util.getIntValue(aaaaaaaa,Double.valueOf(aaaaaaaa).intValue());//工作总时间
       		   if (!field001.equals("无排班")) {
       			   String yxbegtime = Util.null2String(kqUtil.addDateMinut(date+" "+field002,-2));//有效上班打卡时间
       			   String yxendtime = Util.null2String(kqUtil.addDateMinut(date+" "+field003, 3));//有效下班打卡时间
       			   //根据有效打卡时间判断是否跨天，只有两种情况
       			   if (yxbegtime.split(" ")[0].equals(yxendtime.split(" "))) { //不跨天，跟非客服部的同一个规则
       				   partyon18json = partno18(ctarr,personid,date);
       				   paiban = field001+"["+field002+" "+field003+"]";
       			   } else {//跨天了，客服部专属规则
       				   partyon18json = partyes18(checktime,personid,date,yxbegtime,yxendtime,field007);
       				   paiban = field001+"["+field002+" "+field003+"]";
       			   }
			   }else{
					   partyon18json = partno18(ctarr,personid,date);
   				       paiban = field001+"["+field002+" "+field003+"]";
			   }
		   }else{
				   paiban = "D班[09:30 18:00]";
				   partyon18json = partno18(ctarr,personid,date);
		   }
       	   
       	   if (!partyon18json.isEmpty()) {
       		   boolean flag1 = partyon18json.getBoolean("flag1");
       		   String sbdk = Util.null2String(partyon18json.getString("sbdk"));
       		   String xbdk = Util.null2String(partyon18json.getString("xbdk"));
       		   if (flag1) {
       			   rs.execute("update uf_tbwbkqjsj set bm='"+departmentid+"',pbsj='"+paiban+"',sbdk='"+sbdk+"',xbdk='"+xbdk+"' where xm='"+personid+"' and rq='"+date+"'");
       		   }else{
       			   if (!ctarr[1].substring(0,5).equals("99:99")) {
       				   int index3 = kqUtil.compartToTime(Util.null2String(ctarr[1].substring(0,5)), "11:59","hh:mm");//如果等于-1，说明是上午打卡
       				   if (index3 == -1 || index3==0) {
       					   sbdk = Util.null2String(ctarr[1].substring(0,5));
       				   }else{
       					   xbdk = Util.null2String(ctarr[1].substring(0,5));
       				   }
       			   }
       			   rs.execute("insert into uf_tbwbkqjsj(xm,bm,rq,pbsj,sbdk,xbdk,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)"
       					   + "values('"+personid+"','"+departmentid+"','"+date+"','"+paiban+"','"+sbdk+"','"+xbdk+"','1','"+ kqUtil.getDateString("yyyy-MM-dd", new Date())+"','"+kqUtil.getDateString("HH:mm:ss", new Date())+"','9','0')");
       		   }
			   }
       	   /********************************************************************/
       	 
       	   JSONObject formtable = kqUtil.getFormtable_data(personid,date);
       	   String ccsj = Util.null2String(formtable.getString("ccsj"));
       	   String xjsj = Util.null2String(formtable.getString("xjsj"));
       	   String cmsj = Util.null2String(formtable.getString("cmsj"));
       	   String ldksj= Util.null2String(formtable.getString("ldksj"));
       	   rs.execute("update uf_tbwbkqjsj set qjjl='"+xjsj+"',ccjl='"+ccsj+"',wcjl='"+cmsj+"',bq='"+ldksj+"' where xm='"+personid+"' and rq='"+date+"'");
		   }
          rs2.close();
	   } catch (Exception e) {
		   this.logger.info("******************************catch异常关闭了考勤机conn连接*************************************************");
		   e.printStackTrace();
	   }finally{
		   try {
			   this.logger.info("******************************异常关闭了考勤机conn连接*************************************************");
			  conn.close();
		   } catch (Exception e2) {
			  e2.printStackTrace();
		   }
	   }
  }
  public JSONObject partno18(String[] ctarr,String personid,String date){
	   JSONObject json = new JSONObject();
	   RecordSet rs = new RecordSet();
	   String sbdk="",xbdk="";
	   boolean flag1 = false;
 	   rs.execute("select * from uf_tbwbkqjsj where xm='"+personid+"' and rq='"+date+"'");
	   if (rs.next()) {
		   String time1 = Util.null2String(rs.getString("sbdk"));
		   String time2 = Util.null2String(rs.getString("xbdk"));
		   String kqti  = Util.null2String(ctarr[1].substring(0,5));//***************************************************************
		   int index1 =kqUtil.compartToTime(kqti, "11:59","hh:mm");//如果等于-1，说明是上午打卡
		   if (time1.equals("") && (index1 == -1||index1==0)) {
			   sbdk = kqti;
			   xbdk = time2;
			   flag1 = true;
		   }else if(!time1.equals("") && (index1 == -1||index1==0)){
			   int index2 = kqUtil.compartToTime(time1, kqti,"hh:mm");
			   if (index2 == 1) {
				   sbdk=kqti;
				   xbdk = time2;
				   flag1 =true;
			   }else{
				   sbdk=time1;
				   xbdk=time2;
				   flag1=true;
			   }
		   }
		   
		   if (time2.equals("") && index1 ==1) {
			    sbdk=time1;
			    xbdk=kqti;
			    flag1 = true;
		   }else if(!time2.equals("")&& index1 == 1 && !kqti.equals("99:99")){
			   int index2 = kqUtil.compartToTime(time2,kqti,"hh:mm");
			   if (index2 == -1) {//如果上班打卡比新时间小，把新时间赋值给下班打卡
				    sbdk=time1;
				    xbdk=kqti;
				    flag1 = true;
			   }else{
				   sbdk=time1;
				   xbdk=time2;
				   flag1=true;
			   }
		   }
	   }
	   json.put("flag1", flag1);
	   json.put("sbdk", sbdk);
	   json.put("xbdk", xbdk);
	return json;
  }
  public JSONObject partyes18(String checktime,String personid,String date,String yxbegtime,String yxendtime,int field007){
	  JSONObject json = new JSONObject();
	  RecordSet rs = new RecordSet();
	  String sbdk="",xbdk="";
	  boolean flag1 = false;
	  rs.execute("select * from uf_tbwbkqjsj where xm='"+personid+"' and rq='"+date+"'");
	   if (rs.next()) {
		   String time1 = Util.null2String(rs.getString("sbdk"));
		   String time2 = Util.null2String(rs.getString("xbdk"));
		   String middletime = Util.null2String(kqUtil.addDateMinut(yxbegtime,field007/2));
		   if (kqUtil.compare_date(checktime,yxbegtime,"yyyy-MM-dd hh:mm")!=-1 && kqUtil.compare_date(checktime, yxendtime, "yyyy-MM-dd hh:mm")!=1) {
			   int index1 = kqUtil.compartToTime(checktime,middletime,"yyyy-MM-dd hh:mm");
			   String[] checktimearr=checktime.split(" ");
			   if (time1.equals("")&&(index1 == -1 || index1 == 0)) {//说明在上半时区
				   sbdk = checktimearr[1];
				   xbdk = time2;
				   flag1=true;
			   }else if(!time1.equals("") && (index1 == -1||index1==0)){//下半时区
				   int index2 = kqUtil.compartToTime(time1, checktimearr[1], "hh:mm");
				   if (index2 == 1) {
					  sbdk = checktimearr[1];
					  xbdk = time2;
					  flag1= true;
				   }else{
					   sbdk=time1;
					   xbdk=time2;
					   flag1=true;
				   }
			   }
			   
			   if (time2.equals("") && index1 ==1) {
				    sbdk=time1;
				    xbdk=checktimearr[1];
				    flag1 = true;
			   }else if(!time2.equals("")&& index1 == 1 && !checktimearr[1].equals("99:99")){
				   int index2 = kqUtil.compartToTime(time2,checktimearr[1],"hh:mm");
				   if (index2 == -1) {//如果上班打卡比新时间小，把新时间赋值给下班打卡
					    sbdk=time1;
					    xbdk=checktimearr[1];
					    flag1 = true;
				   }else{
					   sbdk=time1;
					   xbdk=time2;
					   flag1=true;
				   }
			   }
			   
		   }
	   }
	   json.put("flag1", flag1);
	   json.put("sbdk", sbdk);
	   json.put("xbdk", xbdk);
	return null;
	  
  }
 
}
