package com.rs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.interfaces.datasource.DataSource;
import weaver.interfaces.schedule.BaseCronJob;

public class SyncBse_person extends BaseCronJob{
   private static BaseBean basebean = new BaseBean();
   public static Timestamp getDate(String date){
	   SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	   Timestamp timestamp=null;
	   Date ymd=null;
	   try {
		   if (!date.equals("")) {
			   ymd=(Date) dateFormat.parse(date);
			   timestamp=new Timestamp(ymd.getTime());
		   }
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   return timestamp;
   }
   public void execute()
   {
	   basebean.writeLog("------开始从uf_hrminfo同步到BSE_PERSON-------");
	   DataSource ds = (DataSource)StaticObj.getServiceByFullname(("datasource.betine"), DataSource.class);
	   Connection conn = null;
	   try {
		   SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		   RecordSet rs1 = new RecordSet();
		   rs1.execute("select * from uf_hrminfo");
		   conn = ds.getConnection();
		   while(rs1.next())
		   {
			   //左边是中间表，右边是底表
			   String oa_id=Util.null2String(rs1.getString("WID"));
			   String DEPARTMENT_ID=Util.null2String(rs1.getString("DEPARTMENT_ID"));//部门id
			   String PERSON_NO=Util.null2String(rs1.getString("PERSON_NO"));//人员编码
			   String PERSON_NAME=Util.null2String(rs1.getString("PERSON_NAME"));//人员名称
			   String TECH_POST=Util.null2String(rs1.getString("TECH_POST"));//职称
			   String DUTY =Util.null2String(rs1.getString("DUTY"));//职务
			   String ADDRESS=Util.null2String(rs1.getString("ADDRESS"));//户籍地址
			   String DEGREE =Util.null2String(rs1.getString("DEGREE"));//学历学位
			   String PHONE=Util.null2String(rs1.getString("PHONE"));//电话
			   String SPECIALTY=Util.null2String(rs1.getString("SPECIALTY"));//专业
			   String PASSWORD=Util.null2String(rs1.getString("PASSWORD"));//密码
			   String CULTURE =Util.null2String(rs1.getString("CULTURE"));//学历学位
			   String NATIVE_PLACE = Util.null2String(rs1.getString("NATIVE_PLACE"));//籍贯
			   String E_MAIL=Util.null2String(rs1.getString("E_MAIL"));//邮件
			   String IDNO=Util.null2String(rs1.getString("IDNO"));//身份证
			   String REGION=Util.null2String(rs1.getString("REGION"));//地区
			   String POLITY=Util.null2String(rs1.getString("POLITY"));//政治面貌
			   String HANDCOLL=Util.null2String(rs1.getString("HANDCOLL"));//手机
			   String CONTRACT_NO=Util.null2String(rs1.getString("CONTRACT_NO"));//文件号
			   String PART_FILE=Util.null2String(rs1.getString("PART_FILE"));//任职文件号
			   String TECH_CLASS=Util.null2String(rs1.getString("TECH_CLASS"));//职称类别
			   String WORKKIND=Util.null2String(rs1.getString("WORKKIND"));//工作性质
			   String NATION=Util.null2String(rs1.getString("NATION"));//民族
			   String MARRYTYPE=Util.null2String(rs1.getString("MARRYTYPE"));//婚姻状态
			   String SEX=Util.null2String(rs1.getString("SEX"));//性别
			   String POSITIONID=Util.null2String(rs1.getString("POSITIONID"));//岗位ID
			   String SUPER_ID=Util.null2String(rs1.getString("SUPER_ID"));//管理部门
			   String HUKOU=Util.null2String(rs1.getString("HUKOU"));//户口性质
			   String ZCPY_DEPLABEL=Util.null2String(rs1.getString("ZCPY_DEPLABEL"));//聘用部门
			   String SCHOOLNATURE=Util.null2String(rs1.getString("SCHOOLNATURE"));//学校性质
			   
			   String BIRTHDAY =Util.null2String(rs1.getString("BIRTHDAY"));//出生日期
			   String BEGINDATE=Util.null2String(rs1.getString("BEGINDATE"));//参加工作日期
			   String IMMUREDATE =Util.null2String(rs1.getString("IMMUREDATE"));//入党日期
			   String AUDIT_DATE=Util.null2String(rs1.getString("AUDIT_DATE"));//毕业日期
			   String GW_DATE=Util.null2String(rs1.getString("GW_DATE"));//岗位日期
			   String ZCPY_DATE=Util.null2String(rs1.getString("ZCPY_DATE"));//聘用日期
			   String ZCPD_DATE=Util.null2String(rs1.getString("ZCPD_DATE"));//评定日期
			   String LS_DATE=Util.null2String(rs1.getString("LS_DATE"));//来所日期
			   String CONTRACT_BEGIN=Util.null2String(rs1.getString("CONTRACT_BEGIN"));//签订日期
			   String CONTRACT_END=Util.null2String(rs1.getString("CONTRACT_END"));//合同结束日期
			   
			   
			   basebean.writeLog("出生日期："+BIRTHDAY+"  结束日期："+CONTRACT_END+"  开始日期："+CONTRACT_BEGIN);
	           ResultSet rs2 = conn.createStatement().executeQuery("select * from BSE_PERSON where PERSON_NO='"+PERSON_NO+"'");
	           boolean isHave=false;
	           if (rs2.next()) {
				   isHave=true;
				   basebean.writeLog("是否有重复数据：   "+isHave);
			   }
	           rs2.close();
	           if (!isHave) {
	        	   String sql2 = "insert into BSE_PERSON"
	        			   + "(oa_id,CONTRACT_END,DEPARTMENT_ID,PERSON_NO,PERSON_NAME,TECH_POST,"
	        			   + "DUTY,BIRTHDAY,ADDRESS,DEGREE,PHONE,SPECIALTY,PASSWORD,CULTURE,BEGINDATE,"
	        			   + "NATIVE_PLACE,IMMUREDATE,AUDIT_DATE,E_MAIL,IDNO,REGION,GW_DATE,ZCPY_DATE,"
	        			   + "ZCPD_DATE,LS_DATE,POLITY,HANDCOLL,CONTRACT_BEGIN,CONTRACT_NO,"
	        			   + "PART_FILE,TECH_CLASS,WORKKIND,NATION,MARRYTYPE,SEX,POSITIONID,SUPER_ID,"
	        			   + "HUKOU,ZCPY_DEPLABEL,SCHOOLNATURE) "
	        			   + "values('"+oa_id+"','"+CONTRACT_END+"','"+DEPARTMENT_ID+"','"+PERSON_NO+"','"+PERSON_NAME+"','"+TECH_POST+"','"+DUTY+"',"
	        			   + "'"+BIRTHDAY+"','"+ADDRESS+"','"+DEGREE+"','"+PHONE+"','"+SPECIALTY+"','"+PASSWORD+"',"
	        			   + "'"+CULTURE+"','"+BEGINDATE+"','"+NATIVE_PLACE+"','"+IMMUREDATE+"','"+AUDIT_DATE+"',"
	        			   + "'"+E_MAIL+"','"+IDNO+"','"+REGION+"','"+GW_DATE+"','"+ZCPY_DATE+"','"+ZCPD_DATE+"',"
	        			   + "'"+LS_DATE+"','"+POLITY+"','"+HANDCOLL+"','"+CONTRACT_BEGIN+"',"
	        			   + "'"+CONTRACT_NO+"','"+PART_FILE+"','"+TECH_CLASS+"','"+WORKKIND+"','"+NATION+"',"
	        			   + "'"+MARRYTYPE+"','"+SEX+"','"+POSITIONID+"','"+SUPER_ID+"','"+HUKOU+"','"+ZCPY_DEPLABEL+"','"+SCHOOLNATURE+"')";
	        	   basebean.writeLog("[insert=sql2----------> ] "+sql2);
	        	   conn.createStatement().execute(sql2);
			  }else{
				  String sql3="update BSE_PERSON set oa_id='"+oa_id+"',CONTRACT_END='"+CONTRACT_END+"',DEPARTMENT_ID='"+DEPARTMENT_ID+"',PERSON_NO='"+PERSON_NO+"',"
				  		   + "PERSON_NAME='"+PERSON_NAME+"',TECH_POST='"+TECH_POST+"',DUTY='"+DUTY+"',BIRTHDAY='"+BIRTHDAY+"',"
				  		   + "ADDRESS='"+ADDRESS+"',DEGREE='"+DEGREE+"',PHONE='"+PHONE+"',SPECIALTY='"+SPECIALTY+"',"
				  		   + "PASSWORD='"+PASSWORD+"',CULTURE='"+CULTURE+"',BEGINDATE='"+BEGINDATE+"',"
	        			   + "NATIVE_PLACE='"+NATIVE_PLACE+"',IMMUREDATE='"+IMMUREDATE+"',AUDIT_DATE='"+AUDIT_DATE+"',"
	        			   + "E_MAIL='"+E_MAIL+"',IDNO='"+IDNO+"',REGION='"+REGION+"',GW_DATE='"+GW_DATE+"',ZCPY_DATE='"+ZCPY_DATE+"',"
	        			   + "ZCPD_DATE='"+ZCPD_DATE+"',LS_DATE='"+LS_DATE+"',POLITY='"+POLITY+"',HANDCOLL='"+HANDCOLL+"',"
	        			   + "CONTRACT_BEGIN='"+CONTRACT_BEGIN+"',CONTRACT_NO='"+CONTRACT_NO+"',"
	        			   + "PART_FILE='"+PART_FILE+"',TECH_CLASS='"+TECH_CLASS+"',WORKKIND='"+WORKKIND+"',NATION='"+NATION+"',"
	        			   + "MARRYTYPE='"+MARRYTYPE+"',SEX='"+SEX+"',POSITIONID='"+POSITIONID+"',SUPER_ID='"+SUPER_ID+"',"
	        			   + "HUKOU='"+HUKOU+"',ZCPY_DEPLABEL='"+ZCPY_DEPLABEL+"',SCHOOLNATURE='"+SCHOOLNATURE+"' where PERSON_NO='"+PERSON_NO+"'";
				  basebean.writeLog("[update=sql3--->]"+sql3);
				  conn.createStatement().execute(sql3);
			  }
			   }
		   basebean.writeLog("------结束bse计划任务-------");
	   } catch (Exception e) {
		   basebean.writeLog(e);
	   }finally{
		   try {
			  conn.close();
		   } catch (Exception e2) {
			  e2.printStackTrace();
		   }
	   }
   }
}
