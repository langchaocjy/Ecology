package com.customization.newKQ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class PersonKq {
	private static BaseBean basebean = new BaseBean();
	   public static JSONArray getData(String nameid,String sub,String dep,String ym){
		   basebean.writeLog("nameid: "+nameid+" sub:"+sub+" dep:"+dep+" ym:"+ym);
		   newKQUtil kqUtil=new newKQUtil();
		   JSONArray root_jsons = new JSONArray();
		   JSONObject root_json = new JSONObject();
		   RecordSet rs = new RecordSet(),rs2=new RecordSet();
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   Date date = null;
		   try {
			    date = format.parse(ym+"-01");
		   } catch (ParseException e) {
			    e.printStackTrace();
		   }  
	            
		   List<Date> list =kqUtil.getAllTheDateOftheMonth(date);
		   for (int i = 0; i < list.size(); i++) {
			   String rq = format.format(list.get(i));
			   basebean.writeLog("rq:  "+rq);
			   String sql1 = "";
			   if (!nameid.equals("")) //如果姓名不为空，就查这个人
			   {
				   sql1 = "select * from uf_tbwbkqjsj where xm='"+nameid+"' and rq = '"+rq+"'";
			   }
			   else if(nameid.equals("") && sub.equals("") && !dep.equals("")) //分部为空，部门不为空，用部门
			   {
				   sql1 = "select * from uf_tbwbkqjsj where bm='"+dep+"' and rq='"+rq+"'";
			   }
			   else if(nameid.equals("") && !sub.equals("") && !dep.equals(""))//两个都不为空，用部门
			   {
				   sql1 = "select * from uf_tbwbkqjsj where bm='"+dep+"' and rq ='"+rq+"'";
			   }
			   else if(nameid.equals("") && !sub.equals("") && dep.equals(""))//分部不为空，部门空了，查分部下的所有人
			   {
				   sql1 = "select * from uf_tbwbkqjsj t1,hrmdepartment t2 where t1.bm=t2.id and t2.subcompanyid1='"+sub+"' and t1.rq='"+rq+"'";
			   }
			   else if(nameid.equals("") && sub.equals("") && dep.equals(""))//两个都为空
			   {
				   sql1 = "select * from uf_tbwbkqjsj where rq = '"+rq+"'";
			   }
			   basebean.writeLog("sql1 : "+sql1);
			   rs.execute(sql1);
			   boolean flag = false;
				   while (rs.next()) 
				   {
					   flag =true;
					   String xm = Util.null2String(rs.getString("xm"));
					   String departmentname="",lastname="";
					   rs2.execute("select t1.lastname,t2.DEPARTMENTNAME from hrmresource t1 ,hrmdepartment t2 where t1.DEPARTMENTID=t2.id and t1.id='"+xm+"'");
					   if (rs2.next()) 
					   {
						   departmentname=Util.null2String(rs2.getString("departmentname"));
						   lastname=Util.null2String(rs2.getString("lastname"));
					   }
					   String rq1 = Util.null2String(rs.getString("rq"));//2019-11-15
					   String sbdk = Util.null2String(rs.getString("sbdk"));
					   String xbdk = Util.null2String(rs.getString("xbdk"));
					   String pbsj = Util.null2String(rs.getString("pbsj"));
					   String qjjl =Util.null2String(rs.getString("qjjl"));
					   String ccjl = Util.null2String(rs.getString("ccjl"));
					   String wcjl = Util.null2String(rs.getString("wcjl"));
					   String bq = Util.null2String(rs.getString("bq"));
					   
					   String sf = "是";
					   String dksj = "";
					   if (sbdk.equals("") && !xbdk.equals("")) {
						   dksj = "-- : -- "+xbdk;
						   sf="否";
					   }else if(!sbdk.equals("") && xbdk.equals("")){
						   dksj = sbdk+" -- : --";
						   sf="否";
					   }else if(!sbdk.equals("") && !xbdk.equals("")){
						   dksj = sbdk +" "+xbdk;
						   sf="是";
					   }else{
						   dksj="";
						   sf="否";
					   }
					   root_json.put("部门", departmentname);
					   root_json.put("姓名", lastname);
					   root_json.put("日期", rq1);
					   root_json.put("打卡时间", dksj);
					   root_json.put("出差记录", ccjl);
					   root_json.put("补签", bq);
					   root_json.put("排班时间", pbsj);
					   root_json.put("请假记录", qjjl);
					   root_json.put("外出记录", wcjl);
					   root_json.put("是否正常考勤", sf);
					   root_jsons.add(root_json);
				   }
				   if (!flag) {
					   
					   String sql3= "";
					   if (!nameid.equals("")) {
						   sql3="select t1.lastname,t2.DEPARTMENTNAME from hrmresource t1 ,hrmdepartment t2 where t1.DEPARTMENTID=t2.id and t1.id='"+nameid+"'";
					   }else if(nameid.equals("") && sub.equals("") && !dep.equals("")){
						   sql3="select t2.departmentname,t1.lastname from hrmresource t1,hrmdepartment t2 where t1.DEPARTMENTID=t2.id and t1.DEPARTMENTID='"+dep+"'";
					   }else if (nameid.equals("") && !sub.equals("") && !dep.equals("")) {
						   sql3="select t2.departmentname,t1.lastname from hrmresource t1,hrmdepartment t2 where t1.DEPARTMENTID=t2.id and t1.DEPARTMENTID='"+dep+"'";
					   }else if(nameid.equals("") && !sub.equals("") && dep.equals("")){
						   sql3="select t1.lastname,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.subcompanyid1='"+sub+"'";
					   }else if(nameid.equals("") && sub.equals("") && dep.equals("")){
						   sql3="select t1.lastname,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id";
					   }
					   
					   rs2.execute(sql3);
					   while(rs2.next()) 
					   {
						   String departmentname=Util.null2String(rs2.getString("departmentname"));
						   String lastname=Util.null2String(rs2.getString("lastname"));
						   JSONObject json = kqUtil.getFormtable_data(nameid,rq);
						   String ccsj = Util.null2String(json.getString("ccsj"));
						   String xjsj = Util.null2String(json.getString("xjsj"));
						   String cmsj = Util.null2String(json.getString("cmsj"));
						   String ldksj= Util.null2String(json.getString("ldksj"));
						   boolean weeken = kqUtil.getWeek(rq);
						   String pbsj="";
						   if (!departmentname.equals("客服部") && !weeken) {
							   pbsj = "D班[09:30 18:00]"; 
						   }else if(!departmentname.equals("客服部") && weeken){
							   pbsj = "周末";
						   }else if(departmentname.equals("客服部")){
							   JSONObject pbdetail = kqUtil.JudgeSchedul(nameid,rq);//根据人员id和日期找到对应的排班
							   String field001=Util.null2String(pbdetail.getString("field001"));//班次名称
							   String field002=Util.null2String(pbdetail.getString("field002"));//排班开始日期
							   String field003=Util.null2String(pbdetail.getString("field003"));//排班结束日期
							   String aaaaaaaa=Util.null2String(pbdetail.getString("field007"));//工作总时间
							   pbsj = field001+"["+field002+" "+field003+"]";
						   }
						   String sf = "是";
						   if (weeken) {
							   sf = "是";
						   }else{
							   sf = "否"; 
							   basebean.writeLog("非正常数据： "+departmentname+"  lastname:"+lastname);
						   }
						   root_json.put("部门", departmentname);
						   root_json.put("姓名", lastname);
						   root_json.put("日期", rq);
						   root_json.put("打卡时间", "");
						   root_json.put("出差记录", ccsj);
						   root_json.put("补签", ldksj);
						   root_json.put("排班时间", pbsj);
						   root_json.put("请假记录", xjsj);
						   root_json.put("外出记录", cmsj);
						   root_json.put("是否正常考勤", sf);
						   root_jsons.add(root_json);
					   }
				   }
		   }
		return root_jsons;
	   }
	   
	   public static void main(String[] args){
		   int wf_khxyqx = Util.getIntValue("464", 110);
		   System.out.println(wf_khxyqx);
	   }
}
