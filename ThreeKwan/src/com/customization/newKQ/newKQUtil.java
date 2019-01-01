package com.customization.newKQ;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;

public class newKQUtil {
       //用考勤机人名查人员id和部门id和部门名称
       public static JSONObject selectName(String name){
	   JSONObject json = new JSONObject();
	   RecordSet rs = new RecordSet();
	   rs.execute("select t1.departmentid,t1.id,t2.departmentname from hrmresource t1,hrmdepartment t2 "
	   		+ "where t1.DEPARTMENTID=t2.id and t1.lastname='"+name+"'");
	   if (rs.next()) {
		   String departmentid = Util.null2String(rs.getString("departmentid"));
		   String personid = Util.null2String(rs.getString("id"));
		   String departmentname = Util.null2String(rs.getString("departmentname"));
		   json.put("departmentid", departmentid);
		   json.put("departmentname", departmentname);
		   json.put("personid", personid);
	   }else{
		   json.put("departmentid", "0");
		   json.put("departmentname", "0");
		   json.put("personid", "0");
	   }
	return json;
       } 
       //判断班次
       public static JSONObject JudgeSchedul(String personid,String date){
    	 JSONObject json = new JSONObject();
	     String field001="",field002="",field003="",field007="";
	     RecordSet rs = new RecordSet();
	     rs.execute("select * from hrm_schedule_worktime where field001 in("
	     		+ "select field001 from hrm_schedule_shifts_set where id in("
	     		+ "select field001 from hrm_schedule_set_detail where field002='"+personid+"' and field003='"+date+"'))");
	     if (rs.next()) {
			  field001=Util.null2String(rs.getString("field001"));
			  field002=Util.null2String(rs.getString("field002"));
			  field003=Util.null2String(rs.getString("field003"));
              field007=Util.null2String(rs.getString("field007"));
		 }else{
			 field001="无排班";
			 field002="00:00";
			 field003="23:59";
			 field007="24";
		 }
	     json.put("field001", field001);//班次名称：ABCD班
	     json.put("field002", field002);//排班开始时间
	     json.put("field003", field003);//排班结束时间
	     json.put("field007", field007);//工作总时间
	     return json;
       }
       //减2个小时  加3个小时
	   public static String addDateMinut(String day, int hour){   
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date date = null;   
	        try {   
	            date = format.parse(day);   
	        } catch (Exception ex) {   
	            ex.printStackTrace();   
	        }   
	        if (date == null)   
	            return "";   
	        Calendar cal = Calendar.getInstance();   
	        cal.setTime(date);   
	        cal.add(Calendar.HOUR, hour);// 24小时制   
	        date = cal.getTime();   
	        cal = null;   
	        return format.format(date);   
	    }
       //获取当前年月日，返回string
	   public String getDateString(String type, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateTime = sdf.format(data);
		return dateTime;
	   }
	   //获取两个年月日之间的所有年月日
	   public static List<Date> getBetweenDates(String a ,String b) throws ParseException {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date start = sdf.parse(a);
		    Date end = sdf.parse(b);
		    List<Date> result = new ArrayList<Date>();
		    Calendar tempStart = Calendar.getInstance();
		    tempStart.setTime(start);
		    tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    
		    Calendar tempEnd = Calendar.getInstance();
		    tempEnd.setTime(end);
		    result.add(start);
		    while (tempStart.before(tempEnd)) 
		    {
		        result.add(tempStart.getTime());
		        tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    }
		    return result;
		}   
	   //取四个流程表的所有请假记录
	   public static JSONObject getFormtable_data(String personid,String date){
		   JSONObject json = new JSONObject();
		   RecordSet rs1=new RecordSet(),rs2=new RecordSet(),rs3=new RecordSet(),rs4=new RecordSet();
		   rs1.execute("select t1.*,t2.* from formtable_main_22 t1,formtable_main_22_dt1 t2 where t1.id=t2.mainid and t1.sqr='"+personid+"'");
		   String ccsj="";//出差时间
		   while(rs1.next()) {
			   String ksrq=Util.null2String(rs1.getString("ksrq"));
			   String jsrq=Util.null2String(rs1.getString("jsrq"));
			   boolean festival=false; 
			   if(compare_date(date,ksrq,"yyyy-MM-dd")!=-1 && compare_date(date,jsrq,"yyyy-MM-dd")!=1) 
			   { 
			       festival=true; 
			   } 
			   if (festival) {
				   String kssj=Util.null2String(rs1.getString("kssj"));
				   String jssj=Util.null2String(rs1.getString("jssj"));
				   ccsj = ksrq.replace("-", "")+" "+kssj+" "+jsrq.replace("-", "")+" "+jssj;   
			   }
		   }
		   
		   String xjsj="";//休假时间
		   rs2.execute("select t1.*,t2.* from formtable_main_25 t1,formtable_main_25_dt1 t2 where t1.id=t2.mainid and t1.sqr='"+personid+"'");
		   while (rs2.next()) {
			   String ksrq=Util.null2String(rs2.getString("ksrq"));
			   String jsrq=Util.null2String(rs2.getString("jsrq"));
			   boolean flag = false;
			   if(compare_date(date,ksrq,"yyyy-MM-dd")!=-1 && compare_date(date,jsrq,"yyyy-MM-dd")!=1) 
			   { 
			       flag=true; 
			   } 
			   if (flag) {
				   String xjlx=getQjlx(Util.null2String(rs2.getString("xjlx")));
				   String kssj=Util.null2String(rs2.getString("kssj"));
				   String jssj=Util.null2String(rs2.getString("jssj"));
				   xjsj=xjlx+ksrq.replace("-", "")+" "+kssj +" "+ jsrq.replace("-", "")+" "+jssj;
			   }
		   }
		   
		   String cmsj="";//外出时间
		   rs3.execute("select t1.*,t2.* from formtable_main_27 t1,formtable_main_27_dt1 t2 where t1.id=t2.mainid and t1.sqr='"+personid+"'");
		   while (rs3.next()) {
			   String wcrq=Util.null2String(rs3.getString("wcrq"));
			   String fhrq=Util.null2String(rs3.getString("fhrq"));
			   boolean flag = false;
			   if(compare_date(date,wcrq,"yyyy-MM-dd")!=-1 && compare_date(date,fhrq,"yyyy-MM-dd")!=1) 
			   { 
			       flag=true; 
			   } 
			   if (flag) {
				   String wcsj=Util.null2String(rs3.getString("wcsj"));
				   String fhsj=Util.null2String(rs3.getString("fhsj"));
				   cmsj=wcrq.replace("-", "")+" "+wcsj +" "+ fhrq.replace("-", "")+" "+fhsj;
			   }
		   }
		   
		   String ldksj="";//漏打卡时间
		   rs4.execute("select t1.*,t2.* from formtable_main_29 t1,formtable_main_29_dt1 t2 where t1.id=t2.mainid and t1.sqr='"+personid+"'");
		   while (rs4.next()) {
			   String qkrq=Util.null2String(rs4.getString("qkrq"));
			   if (date.equals(qkrq)) {
				   String qksj=Util.null2String(rs4.getString("qksj"));
                   ldksj=qkrq.replace("-", "")+" "+qksj;				
			   }
		   }
		   json.put("ccsj", ccsj);
		   json.put("xjsj", xjsj);
		   json.put("cmsj", cmsj);
		   json.put("ldksj", ldksj);
		return json;
	   }
	   //判断一个日期是否在两个日期之间
	   public static int compare_date(String DATE1, String DATE2,String type) { 
		   DateFormat df = new SimpleDateFormat(type); 
		   try { 
			   if (!DATE1.equals("") && !DATE2.equals("")) {
				   Date dt1 = df.parse(DATE1); 
				   Date dt2 = df.parse(DATE2); 
				   if (dt1.getTime() > dt2.getTime()) { 
					   return 1; 
				   } else if (dt1.getTime() < dt2.getTime()) { 
					   return -1; 
				   } else { 
					   return 0; 
				   } 
			   }else{
				   return 1;
			   }
		   } catch (Exception exception) { 
		       exception.printStackTrace(); 
		   } 
		   return 0; 
	  }
	   //判断休假类型
	   public static String getQjlx(String number)
	   {
		   String xjlx ="";
		   if (!number.equals("")) 
		   {
			   switch(Integer.parseInt(number))
			   {
			   case 0:
				   xjlx="事假";
				   break;
			   case 1:
				   xjlx="病假";
				   break;
			   case 2:
				   xjlx="年休假";
				   break;
			   case 3:
				   xjlx="婚假";
				   break;
			   case 4:
				   xjlx="丧假";
				   break;
			   case 5:
				   xjlx="产检假";
				   break;
			   case 6:
				   xjlx="产假";
				   break;
			   default:
				   break;
			   }
		   }
		   return xjlx;
	   }
	   //输入当月获取当月所有日期
	   public List<Date> getAllTheDateOftheMonth(Date date) {
	        List<Date> list = new ArrayList<Date>();
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.DATE, 1);
	        int month = cal.get(Calendar.MONTH);
	        while(cal.get(Calendar.MONTH) == month){
	            list.add(cal.getTime());
	            cal.add(Calendar.DATE, 1);
	        }
	        return list;
	   }
	   //比较时分大小
	   public static int compartToTime(String time1,String time2,String type){
		   SimpleDateFormat sdf = new SimpleDateFormat(type);
		   int flag = 0 ;
		   try {
			   Date d1=sdf.parse(time1),d2=sdf.parse(time2);
			   flag=d1.compareTo(d2); 
		   } catch (ParseException e) {
			e.printStackTrace();
		   }
		return flag;
	   }
	   //判断是否是周末，用于非排班人员
	   public static boolean getWeek(String bDate){
		   boolean flag = false;
		   SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	       Date bdate;
		   try {
			   bdate = format1.parse(bDate);
		       Calendar cal = Calendar.getInstance();
		       cal.setTime(bdate);
		       if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
		           flag = true;
		       } else {
		           flag = false;
		       }
		   } catch (ParseException e) {
			e.printStackTrace();
		   }
		return flag;
	   }
}
