package com.customization;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;

public class AllUtil {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static BaseBean basebean = new BaseBean();
	static RecordSet rs = new RecordSet();
   public static int returnInteger(String number)
   {
	   int returnDay = Integer.parseInt(number);
	   return returnDay;
   }
   //去除年月日之间的横杠
   public static String delete_(String date)
   {
	   String[] str = date.split("-");
	   StringBuffer sb = new StringBuffer();
	   for(int i = 0; i < str.length; i++){
	    sb. append(str[i]);
	   }
	   String s = sb.toString();
	   return s;
   }
   //获取当前年月日，返回string
   public static String returnYmd()
   {
	   Date date = new Date();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   String dateNowStr = sdf.format(date);
	return dateNowStr;
   }
   //得出同月两个日期之间的天数
   public static long compareDate(String date1,String date2) throws ParseException
   {
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	   Date d1=sdf.parse(date1);
	   Date d2=sdf.parse(date2);
	   long daysBetween=(d2.getTime()-d1.getTime()+1000000)/(60*60*24*1000);
	   return daysBetween;
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
   //将日期时间整合
   public static String tranDateTime(String a,String a1,String b,String b1)
   {
	   String one=a.substring(0,4)+a.substring(5,7)+a.substring(8)+" "+a1+"-"+b.substring(0,4)+b.substring(5,7)+b.substring(8)+" "+b1;
	    return one;
   }
   //完成四个表的数据筛选更新
   public boolean ScreenAndUpdate(String id,String name,String bm,int index,String a,String b,String c,String d,String xiujialeixing)
   {
	      boolean flag = true;
	      RecordSet rs = new RecordSet();
		  String sql1 = "select * from uf_kqxg_dt"+index+" where mainid ="+id; 
		  log.error("[sql1-->]"+sql1);
		  rs.execute(sql1);
		  while (rs.next()) 
		  {
			  String xjlx = "";
			  if (!xiujialeixing.equals("")) 
			  {
				     xjlx = Util.null2String(rs.getString(xiujialeixing));
			  }
			  String ksrq = Util.null2String(rs.getString(a));
			  String kssj = Util.null2String(rs.getString(b));
			  String jsrq = Util.null2String(rs.getString(c));
			  String jssj = Util.null2String(rs.getString(d));
			  log.error("[搜索时间-->]:休假类型"+xjlx+" "+ksrq+"  "+kssj+"  "+jsrq+"  "+jssj);
			  if (!ksrq.equals("")&&!jsrq.equals("")&&index!=4) 
			  {
				  String k_jTime = AllUtil.tranDateTime(ksrq, kssj, jsrq, jssj);//组合日期时间
				  if (index==1) 
				  {
					  k_jTime = getQjlx(xjlx)+k_jTime;//要更新到底表的日期
				  }
				  List<Date> ones;
				  try {
					  ones = AllUtil.getBetweenDates(ksrq, jsrq);
					  for (int j = 0; j < ones.size(); j++) 
					  {
						  Date one = ones.get(j);
						  String rizi = new SimpleDateFormat("yyyy-MM-dd").format(one);//用来做更新条件的日期
						  String common_z = "";//要更新的uf_kqsj字段名字
						  if (index==1) 
						  {
							  common_z = "qjjl";
						  }
						  else if(index==2)
						  {
							  common_z = "ccjl";
						  }
						  else if(index==3)
						  {
							  common_z = "wcjl";
						  }
						  String sql2 = "update uf_kqsj set "+common_z+"='"+k_jTime+"' where xm='"+name+"' and bm='"+bm+"' and rq='"+rizi+"'";
						  log.error("【sql2-->】"+sql2);
						  rs.execute(sql2);
						  flag = true;
					  }
				  } catch (ParseException e) {
					  flag = false;
					  e.printStackTrace();
				  }
			  }
			  
			  if (!ksrq.equals("")&&!jsrq.equals("")&&index==4) 
			  {
				String bq = ksrq+"-"+jsrq;
                String sql4="update uf_kqsj set bq = '"+bq+"' where xm='"+name+"' and bm='"+bm+"' and rq = '"+kssj+"'";
                log.error("【sql4-->】"+sql4);
				rs.execute(sql4);
				flag = true;
			  }
	  }
	  return flag;
   }
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
   //统计休假日期的天数
   public static int Numberofstatistical(String ym,String ksrq,String jsrq)
   {
       int days = 0 ;
       int label = 0;
	   if (ym.equals(ksrq.substring(0, 7)) && ym.equals(jsrq.substring(0,7)) && ksrq.substring(0,7).equals(jsrq.substring(0,7))) //开始结束日期的年月都相等
	   {
		   try {
			    days =  (int)compareDate(ksrq, jsrq);
			    days+=1;
		   } catch (ParseException e) {
			   basebean.writeLog("统计天数出错了！");
				e.printStackTrace();
		   }
	   }
	   else 
	   {
		  try {
			List<Date> list = getBetweenDates(ksrq,jsrq);
			for (int i = 0; i < list.size(); i++) 
			{
			    String riqi = new SimpleDateFormat("yyyy-MM-dd").format(list.get(i)).substring(0,7);
			    if (ym.equals(ksrq.substring(0, 7)) && ksrq.substring(0,7).equals(riqi)) 
			    {
					days += 1;
				}
			    if (ym.equals(jsrq.substring(0, 7)) && jsrq.substring(0,7).equals(riqi)) 
			    {
			    	label = 1;
					days += 1;
				}
			}
			days = label==1 ? days+=1 : days;
		} catch (ParseException e) {
            basebean.writeLog("返回list集合出错了！");
			e.printStackTrace();
		}
	   }
	  return days;
   }
   //将jsonarray里面同一个人的统计天数合并相加成一条
   public static JSONArray returnJSON(JSONArray jsonsarray)
   {
	  JSONArray arrayTemp = new JSONArray();
	  int num = 0;
	  for (int i = 0; i < jsonsarray.size(); i++) 
	  {
		 if (num == 0) 
		 {
		    arrayTemp.add(jsonsarray.get(i));	
		 } 
		 else
		 {
			 int numJ = 0;
			for (int j = 0; j < arrayTemp.size(); j++) 
			{
				JSONObject newJsonObjectI = (JSONObject)jsonsarray.get(i);
				JSONObject newJsonObjectJ = (JSONObject)arrayTemp.get(j);
				String nameI = newJsonObjectI.getString("姓名");
				String depI = newJsonObjectI.getString("部门");
				String sqrI = newJsonObjectI.getString("sqr");
				String shijiaI = newJsonObjectI.getString("事假");
				String bingjiaI = newJsonObjectI.getString("病假");
				String nianjiaI = newJsonObjectI.getString("年假");
				String hunjiaI = newJsonObjectI.getString("婚假");
				String sangjiaI = newJsonObjectI.getString("丧假");
				String chanjianjiaI = newJsonObjectI.getString("产检假");
				String chanjiaI = newJsonObjectI.getString("产假");
				String peichanjiaI=newJsonObjectI.getString("陪产假");
				String burujiaI=newJsonObjectI.getString("哺乳假");
				String cctsI = newJsonObjectI.getString("出差天数");
				String wctsI = newJsonObjectI.getString("外出天数");
				String ldktsI = newJsonObjectI.getString("漏打卡天数");
				String sjcqI = newJsonObjectI.getString("实际出勤天数");
				String ztfzI = newJsonObjectI.getString("早退分钟");
				String cdfzI = newJsonObjectI.getString("迟到分钟");
				String ztcsI = newJsonObjectI.getString("早退次数");
				String cdcsI = newJsonObjectI.getString("迟到次数");
				String kgtsI = newJsonObjectI.getString("旷工天数");
				String x1I = newJsonObjectI.getString("19:30前下班");
				String x2I = newJsonObjectI.getString("19:30-20:30加班");
				String x3I = newJsonObjectI.getString("20:30-22:00加班");
				String x4I = newJsonObjectI.getString("22:00后加班");
				String jbcsI = newJsonObjectI.getString("加班次数");
				String jbscI = newJsonObjectI.getString("加班时长");
				String pjjbscI = newJsonObjectI.getString("平均加班时长");
				String zonggongshiI= newJsonObjectI.getString("总工时");
				String pingjunrgsI=newJsonObjectI.getString("平均日工时");
				String qingjiacishuI=newJsonObjectI.getString("请假次数");
				String qingjiatianshuI=newJsonObjectI.getString("请假天数");
				
				String nameJ = newJsonObjectJ.getString("姓名");
				String depJ = newJsonObjectJ.getString("部门");
				String sqrJ = newJsonObjectJ.getString("sqr");
				String shijiaJ = newJsonObjectJ.getString("事假");
				String bingjiaJ = newJsonObjectJ.getString("病假");
				String nianjiaJ = newJsonObjectJ.getString("年假");
				String hunjiaJ = newJsonObjectJ.getString("婚假");
				String sangjiaJ = newJsonObjectJ.getString("丧假");
				String chanjianjiaJ = newJsonObjectJ.getString("产检假");
				String chanjiaJ = newJsonObjectJ.getString("产假");
				String peichanjiaJ =newJsonObjectJ.getString("陪产假");
				String burujiaJ=newJsonObjectJ.getString("哺乳假");
				String cctsJ = newJsonObjectJ.getString("出差天数");
				String wctsJ = newJsonObjectJ.getString("外出天数");
				String ldktsJ = newJsonObjectJ.getString("漏打卡天数");
				String sjcqJ = newJsonObjectJ.getString("实际出勤天数");
				String ztfzJ = newJsonObjectJ.getString("早退分钟");
				String cdfzJ = newJsonObjectJ.getString("迟到分钟");
				String ztcsJ = newJsonObjectJ.getString("早退次数");
				String cdcsJ = newJsonObjectJ.getString("迟到次数");
				String kgtsJ = newJsonObjectJ.getString("旷工天数");
				String x1J = newJsonObjectJ.getString("19:30前下班");
				String x2J = newJsonObjectJ.getString("19:30-20:30加班");
				String x3J = newJsonObjectJ.getString("20:30-22:00加班");
				String x4J = newJsonObjectJ.getString("22:00后加班");
				String jbcsJ = newJsonObjectJ.getString("加班次数");
				String jbscJ = newJsonObjectJ.getString("加班时长");
				String pjjbscJ = newJsonObjectJ.getString("平均加班时长");
				String zonggongshiJ= newJsonObjectJ.getString("总工时");
				String pingjunrgsJ=newJsonObjectJ.getString("平均日工时");
				String qingjiacishuJ=newJsonObjectJ.getString("请假次数");
				String qingjiatianshuJ=newJsonObjectJ.getString("请假天数");
				
				if (nameI.equals(nameJ) && depI.equals(depJ) && sqrI.equals(sqrJ)) 
				{
					int newShijia = Integer.parseInt(shijiaI)+Integer.parseInt(shijiaJ);
					int newBingjia = Integer.parseInt(bingjiaI)+Integer.parseInt(bingjiaJ);
					int newNianjia = Integer.parseInt(nianjiaI)+Integer.parseInt(nianjiaJ);
					int newHunjia = Integer.parseInt(hunjiaI)+Integer.parseInt(hunjiaJ);
					int newSangjia = Integer.parseInt(sangjiaI)+Integer.parseInt(sangjiaJ);
					int newChanjianjia = Integer.parseInt(chanjianjiaI)+Integer.parseInt(chanjianjiaJ);
					int newChanjia = Integer.parseInt(chanjiaI)+Integer.parseInt(chanjiaJ);
					int newCcts = Integer.parseInt(cctsI)+Integer.parseInt(cctsJ);
					int newWcts = Integer.parseInt(wctsI)+Integer.parseInt(wctsJ);
					int newPeichanjia=Integer.parseInt(peichanjiaI)+Integer.parseInt(peichanjiaJ);
					int newBurujia=Integer.parseInt(burujiaI)+Integer.parseInt(burujiaJ);
					
					int newa = Integer.parseInt(ldktsI)+Integer.parseInt(ldktsJ);
					int newb = Integer.parseInt(sjcqI)+Integer.parseInt(sjcqJ);
					int newc = Integer.parseInt(ztfzI)+Integer.parseInt(ztfzJ);
					int newd = Integer.parseInt(cdfzI)+Integer.parseInt(cdfzJ);
					int newe = Integer.parseInt(ztcsI)+Integer.parseInt(ztcsJ);
					int newf = Integer.parseInt(cdcsI)+Integer.parseInt(cdcsJ);
					int newg = Integer.parseInt(kgtsI)+Integer.parseInt(kgtsJ);
					int newX1 = Integer.parseInt(x1I)+Integer.parseInt(x1J);
					int newX2 = Integer.parseInt(x2I)+Integer.parseInt(x2J);
					int newX3 = Integer.parseInt(x3I)+Integer.parseInt(x3J);
					int newX4 = Integer.parseInt(x4I)+Integer.parseInt(x4J);
					int newjbcs = Integer.parseInt(jbcsI)+Integer.parseInt(jbcsJ);
					int newjbsc = Integer.parseInt(jbscI)+Integer.parseInt(jbscJ);
					int newpjjbsc=Integer.parseInt(pjjbscI)+Integer.parseInt(pjjbscJ);
					int newzonggongshi=Integer.parseInt(zonggongshiI)+Integer.parseInt(zonggongshiJ);
					int newpingjunrgs=Integer.parseInt(pingjunrgsI)+Integer.parseInt(pingjunrgsJ);
					int newQjcs=Integer.parseInt(qingjiacishuI)+Integer.parseInt(qingjiatianshuJ);
					int newQjts=Integer.parseInt(qingjiatianshuI)+Integer.parseInt(qingjiatianshuJ);
					arrayTemp.remove(j);
					JSONObject newJSONobject = new JSONObject();
					newJSONobject.put("姓名", nameI);
					newJSONobject.put("部门", depI);
					newJSONobject.put("sqr", sqrI);
					newJSONobject.put("事假", newShijia);
					newJSONobject.put("病假", newBingjia);
					newJSONobject.put("年假", newNianjia);
					newJSONobject.put("婚假", newHunjia);
					newJSONobject.put("丧假", newSangjia);
					newJSONobject.put("产检假", newChanjianjia);
					newJSONobject.put("产假", newChanjia);
					newJSONobject.put("陪产假", newPeichanjia);
					newJSONobject.put("哺乳假", newBurujia);
					newJSONobject.put("出差天数", newCcts);
					newJSONobject.put("外出天数", newWcts);
					newJSONobject.put("请假天数", newQjts);
					newJSONobject.put("请假次数", newQjcs);
					newJSONobject.put("漏打卡天数", newa);
					newJSONobject.put("实际出勤天数", newb);
					newJSONobject.put("早退分钟", newc);
					newJSONobject.put("迟到分钟", newd);
					newJSONobject.put("早退次数", newe);
					newJSONobject.put("迟到次数", newf);
					newJSONobject.put("旷工天数", newg);
					
					newJSONobject.put("19:30前下班", newX1);
					newJSONobject.put("19:30-20:30加班", newX2);
					newJSONobject.put("20:30-22:00加班", newX3);
					newJSONobject.put("22:00后加班", newX4);
					newJSONobject.put("加班次数", newjbcs);
					newJSONobject.put("加班时长", newjbsc);
					newJSONobject.put("平均加班时长", newpjjbsc);
					newJSONobject.put("总工时", newzonggongshi);
					newJSONobject.put("平均日工时", newpingjunrgs);
					
					arrayTemp.add(newJSONobject);
					break;
				}
				numJ++;
			}  
			if (numJ-1 == arrayTemp.size() - 1) 
			{
				arrayTemp.add(jsonsarray.get(i));
			}
		 }
		 num++;
	  }
	   return arrayTemp;
   }
   //迟到早退了多少分钟
   public static String dateDiff(String startTime, String endTime,String format) {     
       // 按照传入的格式生成一个simpledateformate对象     
       SimpleDateFormat sd = new SimpleDateFormat(format);     
       long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数     
       long nh = 1000 * 60 * 60;// 一小时的毫秒数     
       long nm = 1000 * 60;// 一分钟的毫秒数     
       long ns = 1000;// 一秒钟的毫秒数     
       long diff;     
       long day = 0;     
       long hour = 0;     
       long min = 0;     
       long sec = 0;     
       long zongshijian = 0;
       // 获得两个时间的毫秒时间差异     
       try {     
           diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();     
           day = diff / nd;// 计算差多少天     
           hour = diff % nd / nh + day * 24;// 计算差多少小时     
           min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟     
           sec = diff % nd % nh % nm / ns;// 计算差多少秒     
           // 输出结果     
//           System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"    
//                   + (min - day * 24 * 60) + "分钟" + sec + "秒。");     
//           System.out.println("hour=" + hour + ",min=" + min);      
           zongshijian = hour*60+min;
       } catch (ParseException e) {     
           e.printStackTrace();     
       }     
    	   return Long.toString(zongshijian);    
   }  
////相差小时
   public static String dateDiff2(int times) {     
	    int time = times;
        int hours = (int) Math.floor(time / 60);
        int minute = time % 60;
    	   return hours+"时"+minute+"分";    
   }  
   
   //转换排班
   public static String getPb(String bc){
	   String pb ="";
	   if (!bc.equals("")) 
	   {
		   switch(Integer.parseInt(bc))
		   {
		   case 1:
			   pb="A班";
			   break;
		   case 2:
			   pb="B班";
			   break;
		   case 3:
			   pb="C班";
			   break;
		   case 4:
			   pb="D班";
			   break;
		   case 5:
			   pb="E班";
			   break;
		   default:
			   break;
		   }
	   }
	return pb;
   }
   //非排班人员的取最早和最晚的打卡时间规则
   public static String getFpbZzZw(String signdate,String userid){
	      String minTime=null,maxTime=null,signtime12="";
		  String sql = "select signtime from HRMSCHEDULESIGNIMP where signdate='"+signdate+"' and userid='"+userid+"'";
		  rs.execute(sql);
		  while (rs.next()) {
			  String signtime = Util.null2String(rs.getString("signtime"));
			  if (!signtime.equals("")) {
				 signtime=signtime.substring(0,5);
				 String dy12 = AllUtil.getZzZw(signtime,"11:59", "max");
				 if (dy12.equals("11:59")) {
					 String flag1 = AllUtil.getZzZw("07:30", signtime, "max");//如果他返回的flag不等于ssfm，说明这个s2在范围内，符合要求
//				 String flag2 = AllUtil.getZzZw("21:00", signtime, "min");
					 if (flag1.equals(signtime)) {
						 String flag3 = AllUtil.getZzZw(minTime,signtime, "min");
						 minTime=flag3;
					 }
				 }else{
					 String flag4 = AllUtil.getZzZw(maxTime, signtime, "max");
					 maxTime=flag4;
				 }
				 
			  }else{
				  minTime=null;
				  maxTime=null;
			  }
		  }
		  if (minTime==maxTime&&minTime!=null&&maxTime!=null) {
			  signtime12 = minTime.substring(0,5);
		  }else if(minTime!=null&&maxTime!=null){
			  signtime12 = minTime.substring(0,5)+" "+maxTime.substring(0,5);						
		  }else if(minTime==null&&maxTime!=null){
			  signtime12="--:-- "+" "+maxTime;
		  }else{
			  signtime12 = "";
		  }
		  return signtime12;
   }
   
   //排班人员的最早最晚打卡时间筛选
   public static String getZzZw(String zz,String zw,String dx){
	   SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
	   Date d1 = null, d2 = null;
	   String time = null;
	   try {
		   if (zz==null) {
			time=zw;
		} else {
			d1 = sdf.parse(zz);
			d2 = sdf.parse(zw);
			if (dx.equals("min")) {
				int flag = d1.compareTo(d2);//1->d1大于d2,   -1->d1小于d2,    0->d1=d2
				time=flag==-1?zz:zw;
			}else{
				int flag = d1.compareTo(d2);
				time=flag==1?zz:zw;
			}
		}
	   } catch (ParseException e) {
		  e.printStackTrace();
	   }
	    return time;
   }
   //最简单的时间比大小
   public static int compartToTime(String time1,String time2){
	   SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
	   int flag = 0 ;
	   try {
		   Date d1=sdf.parse(time1),d2=sdf.parse(time2);
		   flag=d1.compareTo(d2); 
	   } catch (ParseException e) {
		e.printStackTrace();
	   }
	return flag;
   }
   
   //搜索一般时间表的作息时间
   public static String getHrmschedule(String monstarttime){
	      String sxbsj="";
	      String hrmschedule = "select * from hrmschedule";
		  rs.execute(hrmschedule);
		  if (rs.next()) 
		  {
				sxbsj=rs.getString(monstarttime);
		  }
	return sxbsj;
   }
   //用人员id搜部门
   public static String sreachDepratment(String userid){
 	  rs.execute("select t2.id from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t1.id='"+userid+"'");
 	  String departmentid="";
 	  if (rs.next()) 
 	  {
 		  departmentid = rs.getString("id");
 	  }
	   return departmentid;
   }
   //判断json里面是否有这个人这一天的数据
   public static boolean getTf(JSONArray root_jsons,String userid,String signdate){
	   boolean isHave = false;
	   if (root_jsons.size()>0) {
			  for (int i = 0; i < root_jsons.size(); i++) {
				  JSONObject isJSONS = root_jsons.getJSONObject(i);
				  String isUserid = Util.null2String(isJSONS.getString("姓名"));
				  String isSigndate = Util.null2String(isJSONS.getString("日期"));
				  if (userid.equals(isUserid)&&signdate.equals(isSigndate)) {
					  isHave = true;
					  break;
				  }else {
					  isHave = false;
				  }
			  }
		  }
	return isHave;
   }
   public static boolean getTf2s(JSONArray root_jsons,String userid){
	   boolean isHave = false;
	   if (root_jsons.size()>0) {
			  for (int i = 0; i < root_jsons.size(); i++) {
				  JSONObject isJSONS = root_jsons.getJSONObject(i);
				  String isUserid = isJSONS.getString("sqr");
				  if (userid.equals(isUserid)) {
					  isHave = true;
				  }else {
					  isHave = false;
				  }
			  }
		  }
	return isHave;
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
   
   public String delHTMLTag(String htmlStr)
   {
     String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
     String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
     String regEx_html = "<[^>]+>";
     
     Pattern p_script = Pattern.compile(regEx_script, 2);
     Matcher m_script = p_script.matcher(htmlStr);
     htmlStr = m_script.replaceAll("");
     
     Pattern p_style = Pattern.compile(regEx_style, 2);
     Matcher m_style = p_style.matcher(htmlStr);
     htmlStr = m_style.replaceAll("");
     
     Pattern p_html = Pattern.compile(regEx_html, 2);
     Matcher m_html = p_html.matcher(htmlStr);
     htmlStr = m_html.replaceAll("");
     return htmlStr.trim();
   }

}
