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
			   if (!nameid.equals("")) //���������Ϊ�գ��Ͳ������
			   {
				   sql1 = "select * from uf_tbwbkqjsj where xm='"+nameid+"' and rq = '"+rq+"'";
			   }
			   else if(nameid.equals("") && sub.equals("") && !dep.equals("")) //�ֲ�Ϊ�գ����Ų�Ϊ�գ��ò���
			   {
				   sql1 = "select * from uf_tbwbkqjsj where bm='"+dep+"' and rq='"+rq+"'";
			   }
			   else if(nameid.equals("") && !sub.equals("") && !dep.equals(""))//��������Ϊ�գ��ò���
			   {
				   sql1 = "select * from uf_tbwbkqjsj where bm='"+dep+"' and rq ='"+rq+"'";
			   }
			   else if(nameid.equals("") && !sub.equals("") && dep.equals(""))//�ֲ���Ϊ�գ����ſ��ˣ���ֲ��µ�������
			   {
				   sql1 = "select * from uf_tbwbkqjsj t1,hrmdepartment t2 where t1.bm=t2.id and t2.subcompanyid1='"+sub+"' and t1.rq='"+rq+"'";
			   }
			   else if(nameid.equals("") && sub.equals("") && dep.equals(""))//������Ϊ��
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
					   
					   String sf = "��";
					   String dksj = "";
					   if (sbdk.equals("") && !xbdk.equals("")) {
						   dksj = "-- : -- "+xbdk;
						   sf="��";
					   }else if(!sbdk.equals("") && xbdk.equals("")){
						   dksj = sbdk+" -- : --";
						   sf="��";
					   }else if(!sbdk.equals("") && !xbdk.equals("")){
						   dksj = sbdk +" "+xbdk;
						   sf="��";
					   }else{
						   dksj="";
						   sf="��";
					   }
					   root_json.put("����", departmentname);
					   root_json.put("����", lastname);
					   root_json.put("����", rq1);
					   root_json.put("��ʱ��", dksj);
					   root_json.put("�����¼", ccjl);
					   root_json.put("��ǩ", bq);
					   root_json.put("�Ű�ʱ��", pbsj);
					   root_json.put("��ټ�¼", qjjl);
					   root_json.put("�����¼", wcjl);
					   root_json.put("�Ƿ���������", sf);
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
						   if (!departmentname.equals("�ͷ���") && !weeken) {
							   pbsj = "D��[09:30 18:00]"; 
						   }else if(!departmentname.equals("�ͷ���") && weeken){
							   pbsj = "��ĩ";
						   }else if(departmentname.equals("�ͷ���")){
							   JSONObject pbdetail = kqUtil.JudgeSchedul(nameid,rq);//������Աid�������ҵ���Ӧ���Ű�
							   String field001=Util.null2String(pbdetail.getString("field001"));//�������
							   String field002=Util.null2String(pbdetail.getString("field002"));//�Ű࿪ʼ����
							   String field003=Util.null2String(pbdetail.getString("field003"));//�Ű��������
							   String aaaaaaaa=Util.null2String(pbdetail.getString("field007"));//������ʱ��
							   pbsj = field001+"["+field002+" "+field003+"]";
						   }
						   String sf = "��";
						   if (weeken) {
							   sf = "��";
						   }else{
							   sf = "��"; 
							   basebean.writeLog("���������ݣ� "+departmentname+"  lastname:"+lastname);
						   }
						   root_json.put("����", departmentname);
						   root_json.put("����", lastname);
						   root_json.put("����", rq);
						   root_json.put("��ʱ��", "");
						   root_json.put("�����¼", ccsj);
						   root_json.put("��ǩ", ldksj);
						   root_json.put("�Ű�ʱ��", pbsj);
						   root_json.put("��ټ�¼", xjsj);
						   root_json.put("�����¼", cmsj);
						   root_json.put("�Ƿ���������", sf);
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
