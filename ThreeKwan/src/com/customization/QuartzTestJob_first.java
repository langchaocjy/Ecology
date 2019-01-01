package com.customization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.schedule.BaseCronJob;

public class QuartzTestJob_first
  extends BaseCronJob
{
  private Logger logger = LoggerFactory.getLogger(QuartzTestJob_first.class);
  private String cronExpr;
  
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
      RecordSet rs = new RecordSet(),rs2 = new RecordSet();
      JSONArray root_jsons = new JSONArray();
      JSONObject root_json = new JSONObject();
      String a = "非排班人员",b = "排班人员";
      //-----搜索一般时间表上下班时间-----
	  String sbsj=AllUtil.getHrmschedule("monstarttime1");
	  String xbsj=AllUtil.getHrmschedule("monendtime2");
	  String zoumos=AllUtil.getHrmschedule("satstarttime1");//星期六上午开始时间
	  String zoumox=AllUtil.getHrmschedule("sunstarttime1");//星期日上午开始时间
	  String sxbsj = "";
	  //-----结束一般时间表上下班时间-----
	  //=====搜索存储考勤机数据的系统表=====
      rs.execute("select * from HRMSCHEDULESIGNIMP");
      while(rs.next())
      {
    	  String signdate = rs.getString("signdate");//日期
    	  String userid = rs.getString("userid");//人员id
    	  String departmentid=AllUtil.sreachDepratment(userid);
    	  String signtime12="", minTime=null,maxTime=null;
    	  
    	  boolean weekOrNo = AllUtil.getWeek(signdate);//判断是否是周末
    	  String importsql = Util.null2String(rs.getString("importsql"));
    	  
          boolean isHave = AllUtil.getTf(root_jsons, userid, signdate);
          if (!isHave) {//如果不重复
      		          boolean sftj = false;
                	  if (!importsql.equals("")) 
                	  {
                		  if (a.equals(importsql.substring(0, 5)) && !departmentid.equals("18"))//非排班人员按一般工作时间表里面的时间规则 
                		  {
                			  //----开始筛选取最早打卡时间和最晚打卡时间----
                			  if (!weekOrNo) {
                				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
                				  sxbsj="D班["+sbsj+" "+xbsj+"]";
                			  } else {
                				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
                				  sxbsj=zoumos+" "+zoumox;
                			  }
                			  //----结束筛选取最早打卡时间和最晚打卡时间----
                		  }
                		  else if (b.equals(importsql.substring(0, 4)) && departmentid.equals("18"))//如果是客服部的员工，importsql是排班人员，说明他今天上班
                		  {
                			  /*
                			   * 2018-10-10 14:00:00-----2018-10-11 02:59:00
                			   * 2018-10-10 22:00:00-----2018-10-11 11:30:00
                			   * 2018-10-10 12:00:00-----2018-10-11 01:00:00
                			   * */
                			  String[] arr1 = importsql.split(";");//排班人员;上班开始时间:2018-10-10 14:00:00;下班结束时间:2018-10-11 02:59:00
                			  String[] arr2 = arr1[1].split(" ");
                			  String[] arr3 = arr1[2].split(" ");
                			  String symd = arr2[0].substring(7, 17);//2018-10-10
                			  String xymd = arr3[0].substring(7, 17);//2018-10-11
                			  String ssfm = arr2[1].substring(0, 5);//14:00
                			  String xsfm = arr3[1].substring(0, 5);//02:59
                			  String sql6="";
                			  if (ssfm.equals("06:00") || ssfm=="06:00") {
                				  sxbsj="A班[08:00 16:00]";
                			  }else if(ssfm.equals("14:00") || ssfm=="14:00"){
                				  sxbsj="B班[16:00 23:59]";
                			  }else if(ssfm.equals("22:00") || ssfm=="22:00"){
                				  sxbsj="C班[00:00 08:30]";
                			  }else if(ssfm.equals("07:30") || ssfm=="07:30"){
                				  sxbsj="D班[09:30 18:00]";
                			  }else{
                				  sxbsj="E班[14:00 22:00]";
                			  }
                			  /*
                			   * 排班人员的较为特殊，这里不用signtype=1，2来判断上下班打卡时间，写一个工具类，通过获取到importsql的有效打卡时间
                			   * 在这个范围内取最小和最大的时间组合在一起
                			   * */
                			  if (symd.equals(xymd))//如果相等，取有效时间内的时间，不管跨不跨天，大于上班时间即可
                			  {
                				  sql6="select signtime from HRMSCHEDULESIGNIMP where signdate='"+symd+"' and userid='"+userid+"'";
                				  rs2.execute(sql6);
                				  while (rs2.next()) 
                				  {
                					  String s2 = rs2.getString("signtime").substring(0,5);
//                					  String flag1 = AllUtil.getZzZw(ssfm, s2, "max");//如果他返回的flag不等于ssfm，说明这个s2在范围内，符合要求
//                					  String flag2 = AllUtil.getZzZw(xsfm, s2, "min");
//                					  if (flag1.equals(s2) && flag2.equals(s2)) {
//                						  String flag3 = AllUtil.getZzZw(minTime,s2, "min");
//                						  String flag4 = AllUtil.getZzZw(maxTime,s2, "max");
//                						  minTime=flag3;
//                						  maxTime=flag4;
//                					  } else {
//                						  this.logger.info("[筛选不跨天的最小打卡时间时----》不符合打卡范围的时间:]"+s2+" 它应该大于："+ssfm+" 小于："+xsfm);
//                					  }
                					  
                					  String flag1 = AllUtil.getZzZw(ssfm, s2, "max");
                					  if (flag1.equals(s2)) {
										 String flag3 = AllUtil.getZzZw(minTime,s2, "min");
										 minTime=flag3;
									  }
                					  
                					  String flag4=AllUtil.getZzZw(maxTime, s2, "max");
                					  maxTime=flag4;
                				  }
                			  }
                			  else //如果不相等，说明时间跨天了，先去第二天拿最晚下班时间 ，如果没有，取今天最晚打卡时间
                			  {
                				  sql6="select signtime from HRMSCHEDULESIGNIMP where signdate='"+symd+"' and userid='"+userid+"'";
                				  rs2.execute(sql6);
                				  while (rs2.next()) 
                				  {
                					  String s2 = rs2.getString("signtime").substring(0,5);
                					  String flag1 = AllUtil.getZzZw(ssfm, s2, "max");//如果他返回的flag不等于ssfm，说明这个s2在范围内，符合要求
                					  String flag2 = AllUtil.getZzZw("23:59", s2, "min");
                					  if (flag1.equals(s2) && flag2.equals(s2)) {
                						  String flag3 = AllUtil.getZzZw(minTime,s2, "min");
                						  String flag4 = AllUtil.getZzZw(maxTime,s2, "max");
                						  minTime=flag3;
                						  maxTime=flag4;
                					  }
                				  }
                				  sql6="select signtime from HRMSCHEDULESIGNIMP where signdate='"+xymd+"' and userid='"+userid+"'";
                				  rs2.execute(sql6);
                				  if (rs2.next()) {
                					  String aa = null;
                					  while(rs2.next()){
                						  String s2 = rs2.getString("signtime").substring(0,5);
                						  String flag1 = AllUtil.getZzZw(xsfm, s2, "min");
                						  if (flag1.equals(s2)) {
                							  String flag2 = AllUtil.getZzZw(aa, s2, "max");
                							  aa=flag2;
                						  }
                					  }
                					  maxTime=aa;
                				  }
                			  }
                			  if (minTime!=null && maxTime!=null) {
                				  signtime12=minTime.substring(0,5)+" "+maxTime.substring(0, 5); 
                			  }else if(minTime!=null && maxTime==null){
                				  signtime12=minTime.substring(0,5); 
                			  }else if(minTime==null && maxTime!=null){
                				  signtime12="--:-- "+" "+maxTime.substring(0,5); 
                			  }else{
                				  signtime12="";
                			  }
//                          this.logger.info("[排班人员的上下班打卡时间：]"+signtime12);
                		  }else if(a.equals(importsql.substring(0, 5)) && departmentid.equals("18")){//如果是客服部的，importsql是非排班人员，说明他今天没有排班，休息
                			  signtime12=" ";
                			  sxbsj="R班";
                		  }
             	  }else if (importsql.equals("") && !departmentid.equals("18")) {
        			  if (!weekOrNo) {//如果不是周末
        				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
        				  sxbsj="D班["+sbsj+" "+xbsj+"]";
        			  }else{
        				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
        				  sxbsj="休息"+zoumos+" "+zoumox;
        			  }
        		  }else if (importsql.equals("") && departmentid.equals("18")) {
             		  rs2.execute("select importsql from HRMSCHEDULESIGNIMP where userid='"+userid+"' and signdate='"+signdate+"'");
             		  while(rs2.next()){
             			  String importsql2 = Util.null2String(rs2.getString("importsql"));
             			  if (!importsql2.equals("")) {
       						  sftj=true;//说明这一天下面还有不为空的数据，只是没有遍历到，不加入数组
                              break;
						  }
             		  }
             	  }
            	
                	  if (!sftj) {
                		  root_json.put("姓名", userid);//这里的姓名是id，拿到底表后自动会根据id搜人名
                		  root_json.put("部门", departmentid);
                		  root_json.put("日期", signdate);
                		  root_json.put("打卡时间", signtime12);
                		  root_json.put("排班时间", sxbsj);
                		  root_json.put("importsql",importsql);
                		  root_jsons.add(root_json);
					  }
        			  
        				String sql66 = "";
        				rs2.execute("select * from uf_kqsj where xm='"+userid+"' and bm='"+departmentid+"' and rq='"+signdate+"'");
        				if (rs2.next()) 
        				{
        					sql66="update uf_kqsj set dksj='"+signtime12+"' where xm='"+userid+"' and bm='"+departmentid+"' and rq='"+signdate+"'";
        				}
        				else
        				{
        				    sql66 = "insert into uf_kqsj (xm,bm,rq,dksj,pbsj,"
        				    		+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
        						    + "values ('"+userid+"','"+departmentid+"','"+signdate+"','"+signtime12+"','"+sxbsj+"',"
        						    + "'1','"+AllUtil.returnYmd()+"','00:00','5','0')";
        				}
        				rs2.execute(sql66);
        		  }

      }
      //结束搜索考勤机系统表数据搜索
	  this.logger.info("=============================jieshu " + getClass().getName() + " job...");
  }
}
