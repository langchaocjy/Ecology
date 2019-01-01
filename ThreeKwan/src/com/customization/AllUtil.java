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
   //ȥ��������֮��ĺ��
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
   //��ȡ��ǰ�����գ�����string
   public static String returnYmd()
   {
	   Date date = new Date();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   String dateNowStr = sdf.format(date);
	return dateNowStr;
   }
   //�ó�ͬ����������֮�������
   public static long compareDate(String date1,String date2) throws ParseException
   {
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	   Date d1=sdf.parse(date1);
	   Date d2=sdf.parse(date2);
	   long daysBetween=(d2.getTime()-d1.getTime()+1000000)/(60*60*24*1000);
	   return daysBetween;
   }
   //��ȡ����������֮�������������
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
   //������ʱ������
   public static String tranDateTime(String a,String a1,String b,String b1)
   {
	   String one=a.substring(0,4)+a.substring(5,7)+a.substring(8)+" "+a1+"-"+b.substring(0,4)+b.substring(5,7)+b.substring(8)+" "+b1;
	    return one;
   }
   //����ĸ��������ɸѡ����
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
			  log.error("[����ʱ��-->]:�ݼ�����"+xjlx+" "+ksrq+"  "+kssj+"  "+jsrq+"  "+jssj);
			  if (!ksrq.equals("")&&!jsrq.equals("")&&index!=4) 
			  {
				  String k_jTime = AllUtil.tranDateTime(ksrq, kssj, jsrq, jssj);//�������ʱ��
				  if (index==1) 
				  {
					  k_jTime = getQjlx(xjlx)+k_jTime;//Ҫ���µ��ױ������
				  }
				  List<Date> ones;
				  try {
					  ones = AllUtil.getBetweenDates(ksrq, jsrq);
					  for (int j = 0; j < ones.size(); j++) 
					  {
						  Date one = ones.get(j);
						  String rizi = new SimpleDateFormat("yyyy-MM-dd").format(one);//��������������������
						  String common_z = "";//Ҫ���µ�uf_kqsj�ֶ�����
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
						  log.error("��sql2-->��"+sql2);
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
                log.error("��sql4-->��"+sql4);
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
			   xjlx="�¼�";
			   break;
		   case 1:
			   xjlx="����";
			   break;
		   case 2:
			   xjlx="���ݼ�";
			   break;
		   case 3:
			   xjlx="���";
			   break;
		   case 4:
			   xjlx="ɥ��";
			   break;
		   case 5:
			   xjlx="�����";
			   break;
		   case 6:
			   xjlx="����";
			   break;
		   default:
			   break;
		   }
	   }
	   return xjlx;
   }
   //ͳ���ݼ����ڵ�����
   public static int Numberofstatistical(String ym,String ksrq,String jsrq)
   {
       int days = 0 ;
       int label = 0;
	   if (ym.equals(ksrq.substring(0, 7)) && ym.equals(jsrq.substring(0,7)) && ksrq.substring(0,7).equals(jsrq.substring(0,7))) //��ʼ�������ڵ����¶����
	   {
		   try {
			    days =  (int)compareDate(ksrq, jsrq);
			    days+=1;
		   } catch (ParseException e) {
			   basebean.writeLog("ͳ�����������ˣ�");
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
            basebean.writeLog("����list���ϳ����ˣ�");
			e.printStackTrace();
		}
	   }
	  return days;
   }
   //��jsonarray����ͬһ���˵�ͳ�������ϲ���ӳ�һ��
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
				String nameI = newJsonObjectI.getString("����");
				String depI = newJsonObjectI.getString("����");
				String sqrI = newJsonObjectI.getString("sqr");
				String shijiaI = newJsonObjectI.getString("�¼�");
				String bingjiaI = newJsonObjectI.getString("����");
				String nianjiaI = newJsonObjectI.getString("���");
				String hunjiaI = newJsonObjectI.getString("���");
				String sangjiaI = newJsonObjectI.getString("ɥ��");
				String chanjianjiaI = newJsonObjectI.getString("�����");
				String chanjiaI = newJsonObjectI.getString("����");
				String peichanjiaI=newJsonObjectI.getString("�����");
				String burujiaI=newJsonObjectI.getString("�����");
				String cctsI = newJsonObjectI.getString("��������");
				String wctsI = newJsonObjectI.getString("�������");
				String ldktsI = newJsonObjectI.getString("©������");
				String sjcqI = newJsonObjectI.getString("ʵ�ʳ�������");
				String ztfzI = newJsonObjectI.getString("���˷���");
				String cdfzI = newJsonObjectI.getString("�ٵ�����");
				String ztcsI = newJsonObjectI.getString("���˴���");
				String cdcsI = newJsonObjectI.getString("�ٵ�����");
				String kgtsI = newJsonObjectI.getString("��������");
				String x1I = newJsonObjectI.getString("19:30ǰ�°�");
				String x2I = newJsonObjectI.getString("19:30-20:30�Ӱ�");
				String x3I = newJsonObjectI.getString("20:30-22:00�Ӱ�");
				String x4I = newJsonObjectI.getString("22:00��Ӱ�");
				String jbcsI = newJsonObjectI.getString("�Ӱ����");
				String jbscI = newJsonObjectI.getString("�Ӱ�ʱ��");
				String pjjbscI = newJsonObjectI.getString("ƽ���Ӱ�ʱ��");
				String zonggongshiI= newJsonObjectI.getString("�ܹ�ʱ");
				String pingjunrgsI=newJsonObjectI.getString("ƽ���չ�ʱ");
				String qingjiacishuI=newJsonObjectI.getString("��ٴ���");
				String qingjiatianshuI=newJsonObjectI.getString("�������");
				
				String nameJ = newJsonObjectJ.getString("����");
				String depJ = newJsonObjectJ.getString("����");
				String sqrJ = newJsonObjectJ.getString("sqr");
				String shijiaJ = newJsonObjectJ.getString("�¼�");
				String bingjiaJ = newJsonObjectJ.getString("����");
				String nianjiaJ = newJsonObjectJ.getString("���");
				String hunjiaJ = newJsonObjectJ.getString("���");
				String sangjiaJ = newJsonObjectJ.getString("ɥ��");
				String chanjianjiaJ = newJsonObjectJ.getString("�����");
				String chanjiaJ = newJsonObjectJ.getString("����");
				String peichanjiaJ =newJsonObjectJ.getString("�����");
				String burujiaJ=newJsonObjectJ.getString("�����");
				String cctsJ = newJsonObjectJ.getString("��������");
				String wctsJ = newJsonObjectJ.getString("�������");
				String ldktsJ = newJsonObjectJ.getString("©������");
				String sjcqJ = newJsonObjectJ.getString("ʵ�ʳ�������");
				String ztfzJ = newJsonObjectJ.getString("���˷���");
				String cdfzJ = newJsonObjectJ.getString("�ٵ�����");
				String ztcsJ = newJsonObjectJ.getString("���˴���");
				String cdcsJ = newJsonObjectJ.getString("�ٵ�����");
				String kgtsJ = newJsonObjectJ.getString("��������");
				String x1J = newJsonObjectJ.getString("19:30ǰ�°�");
				String x2J = newJsonObjectJ.getString("19:30-20:30�Ӱ�");
				String x3J = newJsonObjectJ.getString("20:30-22:00�Ӱ�");
				String x4J = newJsonObjectJ.getString("22:00��Ӱ�");
				String jbcsJ = newJsonObjectJ.getString("�Ӱ����");
				String jbscJ = newJsonObjectJ.getString("�Ӱ�ʱ��");
				String pjjbscJ = newJsonObjectJ.getString("ƽ���Ӱ�ʱ��");
				String zonggongshiJ= newJsonObjectJ.getString("�ܹ�ʱ");
				String pingjunrgsJ=newJsonObjectJ.getString("ƽ���չ�ʱ");
				String qingjiacishuJ=newJsonObjectJ.getString("��ٴ���");
				String qingjiatianshuJ=newJsonObjectJ.getString("�������");
				
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
					newJSONobject.put("����", nameI);
					newJSONobject.put("����", depI);
					newJSONobject.put("sqr", sqrI);
					newJSONobject.put("�¼�", newShijia);
					newJSONobject.put("����", newBingjia);
					newJSONobject.put("���", newNianjia);
					newJSONobject.put("���", newHunjia);
					newJSONobject.put("ɥ��", newSangjia);
					newJSONobject.put("�����", newChanjianjia);
					newJSONobject.put("����", newChanjia);
					newJSONobject.put("�����", newPeichanjia);
					newJSONobject.put("�����", newBurujia);
					newJSONobject.put("��������", newCcts);
					newJSONobject.put("�������", newWcts);
					newJSONobject.put("�������", newQjts);
					newJSONobject.put("��ٴ���", newQjcs);
					newJSONobject.put("©������", newa);
					newJSONobject.put("ʵ�ʳ�������", newb);
					newJSONobject.put("���˷���", newc);
					newJSONobject.put("�ٵ�����", newd);
					newJSONobject.put("���˴���", newe);
					newJSONobject.put("�ٵ�����", newf);
					newJSONobject.put("��������", newg);
					
					newJSONobject.put("19:30ǰ�°�", newX1);
					newJSONobject.put("19:30-20:30�Ӱ�", newX2);
					newJSONobject.put("20:30-22:00�Ӱ�", newX3);
					newJSONobject.put("22:00��Ӱ�", newX4);
					newJSONobject.put("�Ӱ����", newjbcs);
					newJSONobject.put("�Ӱ�ʱ��", newjbsc);
					newJSONobject.put("ƽ���Ӱ�ʱ��", newpjjbsc);
					newJSONobject.put("�ܹ�ʱ", newzonggongshi);
					newJSONobject.put("ƽ���չ�ʱ", newpingjunrgs);
					
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
   //�ٵ������˶��ٷ���
   public static String dateDiff(String startTime, String endTime,String format) {     
       // ���մ���ĸ�ʽ����һ��simpledateformate����     
       SimpleDateFormat sd = new SimpleDateFormat(format);     
       long nd = 1000 * 24 * 60 * 60;// һ��ĺ�����     
       long nh = 1000 * 60 * 60;// һСʱ�ĺ�����     
       long nm = 1000 * 60;// һ���ӵĺ�����     
       long ns = 1000;// һ���ӵĺ�����     
       long diff;     
       long day = 0;     
       long hour = 0;     
       long min = 0;     
       long sec = 0;     
       long zongshijian = 0;
       // �������ʱ��ĺ���ʱ�����     
       try {     
           diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();     
           day = diff / nd;// ����������     
           hour = diff % nd / nh + day * 24;// ��������Сʱ     
           min = diff % nd % nh / nm + day * 24 * 60;// �������ٷ���     
           sec = diff % nd % nh % nm / ns;// ����������     
           // ������     
//           System.out.println("ʱ����" + day + "��" + (hour - day * 24) + "Сʱ"    
//                   + (min - day * 24 * 60) + "����" + sec + "�롣");     
//           System.out.println("hour=" + hour + ",min=" + min);      
           zongshijian = hour*60+min;
       } catch (ParseException e) {     
           e.printStackTrace();     
       }     
    	   return Long.toString(zongshijian);    
   }  
////���Сʱ
   public static String dateDiff2(int times) {     
	    int time = times;
        int hours = (int) Math.floor(time / 60);
        int minute = time % 60;
    	   return hours+"ʱ"+minute+"��";    
   }  
   
   //ת���Ű�
   public static String getPb(String bc){
	   String pb ="";
	   if (!bc.equals("")) 
	   {
		   switch(Integer.parseInt(bc))
		   {
		   case 1:
			   pb="A��";
			   break;
		   case 2:
			   pb="B��";
			   break;
		   case 3:
			   pb="C��";
			   break;
		   case 4:
			   pb="D��";
			   break;
		   case 5:
			   pb="E��";
			   break;
		   default:
			   break;
		   }
	   }
	return pb;
   }
   //���Ű���Ա��ȡ���������Ĵ�ʱ�����
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
					 String flag1 = AllUtil.getZzZw("07:30", signtime, "max");//��������ص�flag������ssfm��˵�����s2�ڷ�Χ�ڣ�����Ҫ��
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
   
   //�Ű���Ա�����������ʱ��ɸѡ
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
				int flag = d1.compareTo(d2);//1->d1����d2,   -1->d1С��d2,    0->d1=d2
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
   //��򵥵�ʱ��ȴ�С
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
   
   //����һ��ʱ������Ϣʱ��
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
   //����Աid�Ѳ���
   public static String sreachDepratment(String userid){
 	  rs.execute("select t2.id from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t1.id='"+userid+"'");
 	  String departmentid="";
 	  if (rs.next()) 
 	  {
 		  departmentid = rs.getString("id");
 	  }
	   return departmentid;
   }
   //�ж�json�����Ƿ����������һ�������
   public static boolean getTf(JSONArray root_jsons,String userid,String signdate){
	   boolean isHave = false;
	   if (root_jsons.size()>0) {
			  for (int i = 0; i < root_jsons.size(); i++) {
				  JSONObject isJSONS = root_jsons.getJSONObject(i);
				  String isUserid = Util.null2String(isJSONS.getString("����"));
				  String isSigndate = Util.null2String(isJSONS.getString("����"));
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
   //�ж��Ƿ�����ĩ�����ڷ��Ű���Ա
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
