package com.customization.baobiao;

import com.customization.AllUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class kaoqinchaxun {
   private static BaseBean basebean = new BaseBean();
   public static JSONArray getData(String nameid,String sub,String dep,String ym,String status)
   {
	   RecordSet rs = new RecordSet(),rs1 = new RecordSet(),rs2 = new RecordSet();
	   String sql1 = "";
	   if (!nameid.equals("")) //���������Ϊ�գ��Ͳ������
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t1.id='"+nameid+"'";
	   }
	   else if(nameid.equals("") && sub.equals("") && !dep.equals("")) //�ֲ�Ϊ�գ����Ų�Ϊ�գ��ò���
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.id='"+dep+"'";
	   }
	   else if(nameid.equals("") && !sub.equals("") && !dep.equals(""))//��������Ϊ�գ��ò���
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.id='"+dep+"'";
	   }
	   else if(nameid.equals("") && !sub.equals("") && dep.equals(""))//�ֲ���Ϊ�գ����ſ��ˣ���ֲ��µ�������
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.subcompanyid1='"+sub+"'";
	   }
	   else if(nameid.equals("") && sub.equals("") && dep.equals(""))//������Ϊ��
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id";
	   }
	   basebean.writeLog("[sql1----->]"+sql1);
	   rs.execute(sql1);
	   JSONArray jsons_sqr = new JSONArray();
	   JSONObject json_sqr = new JSONObject();
	   while(rs.next())
	   {
		   String ryid = Util.null2String(rs.getString("id"));//hrmresource
		   String lastname = Util.null2String(rs.getString("lastname"));//hrmresource
		   String departmentid = Util.null2String(rs.getString("departmentid"));//hrmresource
		   String departmentname = Util.null2String(rs.getString("departmentname"));//hrmdepartment
		   
		   rs1.execute("select id from uf_kqxg where sqr='"+ryid+"' ");
		   if (rs1.next()) {
			   while(rs1.next()){
				   String zhubiaoid = Util.null2String(rs1.getString("id"));
				   json_sqr.put("id",zhubiaoid);
				   json_sqr.put("����", lastname);
				   json_sqr.put("����", departmentname);
				   json_sqr.put("����id", departmentid);
				   json_sqr.put("sqr", ryid);
				   jsons_sqr.add(json_sqr);
			   }
		   } else {
			   json_sqr.put("id","0");
			   json_sqr.put("����", lastname);
			   json_sqr.put("����", departmentname);
			   json_sqr.put("����id", departmentid);
			   json_sqr.put("sqr", ryid);
			   jsons_sqr.add(json_sqr);
		   }
	   }
	   
	   JSONArray return_alljson = null;
	   JSONArray jsons_tongji = new JSONArray();
	   JSONObject json_tongji = new JSONObject();

		  for (int i = 0; i < jsons_sqr.size(); i++) 
		  {
			  //�ݼ�����ͳ�ƿ�ʼ
			int shijia = 0,bingjia = 0,nianjia = 0,hunjia=0,sangjia=0,chanjianjia=0,chanjia=0,peichanjia=0,burujia=0,chuchaitianshu=0,waichutianshu=0,qingjiacishu=0,qingjiatianshu=0;
			   JSONObject j_s1 = jsons_sqr.getJSONObject(i);
			  String id = Util.null2String(j_s1.getString("id"));
			 String sqr = Util.null2String(j_s1.getString("sqr"));
			String name = Util.null2String(j_s1.getString("����"));
			String department = Util.null2String(j_s1.getString("����"));
			String departmentid=Util.null2String(j_s1.getString("����id"));
			rs.execute("select * from uf_kqxg_dt1 where mainid = '"+id+"'");
			if (rs.next()) {qingjiacishu+=1;}
			while (rs.next()) 
			{
				String xjlx = Util.null2String(rs.getString("xjlx"));
				String ksrq = Util.null2String(rs.getString("ksrq"));
				String jsrq = Util.null2String(rs.getString("jsrq"));
				/*
				 * ���1���û�����10�·ݵ����ݣ���Щ���������9�·�����ģ��������ȫ����10�·ݡ�  ֻ��Ҫ�ж��������¸���ʼ�����Ƿ�ƥ��
				 * ���2���û�����10�·ݵ����ݣ�9�·�������9��27�ŵ�10��7�ŵ���ټ�¼�����ʱ���Ҫ��ȡ10��1�ŵ�7�ŵ�����
				 * ���3���û�����10�·ݵ����ݣ���10��27��11��3�ŵ���ټ�¼����ȡ10��27��11��1��֮ǰ������
				 */
				if (ym.equals(ksrq.substring(0, 7)) || ym.equals(jsrq.substring(0, 7))) 
				{
					int days = AllUtil.Numberofstatistical(ym,ksrq, jsrq);
					if (xjlx.equals("")) {
						xjlx = "9";
					}
					if (Integer.parseInt(xjlx)==0){
						shijia += days;
					}else if(Integer.parseInt(xjlx)==1){
						bingjia += days;
					}else if(Integer.parseInt(xjlx)==2){
						nianjia += days;
					}else if(Integer.parseInt(xjlx)==3){
						hunjia += days;
					}else if(Integer.parseInt(xjlx)==4){
						sangjia += days;
					}else if(Integer.parseInt(xjlx)==5){
						chanjianjia += days;
					}else if(Integer.parseInt(xjlx)==6){
						chanjia += days;
					}else if(Integer.parseInt(xjlx)==7){
						peichanjia+=days;
					}else if(Integer.parseInt(xjlx)==8){
						burujia+=days;
					}
				}
			 }
			qingjiatianshu=shijia+bingjia+nianjia+hunjia+sangjia+chanjianjia+chanjia+peichanjia+burujia;
			json_tongji.put("����", name);
			json_tongji.put("����", department);
			json_tongji.put("sqr", sqr);
			json_tongji.put("�¼�", shijia);
			json_tongji.put("����", bingjia);
			json_tongji.put("���", nianjia);
			json_tongji.put("���", hunjia);
			json_tongji.put("ɥ��", sangjia);
			json_tongji.put("�����", chanjianjia);
			json_tongji.put("����", chanjia);
			json_tongji.put("�����", peichanjia);
			json_tongji.put("�����", burujia);
			json_tongji.put("��ٴ���", qingjiacishu);
			json_tongji.put("�������", qingjiatianshu);
			//��������ͳ�ƿ�ʼ
			rs.execute("select * from uf_kqxg_dt2 where mainid = '"+id+"'");
			while(rs.next())
			{
				String ksrq = Util.null2String(rs.getString("ksrq"));
				String jsrq = Util.null2String(rs.getString("jsrq"));
				String ccts = Util.null2String(rs.getString("xglc"));
				if (ym.equals(ksrq.substring(0, 7)) || ym.equals(jsrq.substring(0, 7))) 
			    {
					int days = AllUtil.Numberofstatistical(ym,ksrq, jsrq);
			    	chuchaitianshu += days;
				}	
			}
			json_tongji.put("��������", chuchaitianshu);
			//���ͳ�ƿ�ʼ
			rs.execute("select * from uf_kqxg_dt3 where mainid = '"+id+"'");
			while(rs.next())
			{
				String ksrq = Util.null2String(rs.getString("ksrq"));
				String jsrq = Util.null2String(rs.getString("jsrq"));
				if (ym.equals(ksrq.substring(0, 7)) || ym.equals(jsrq.substring(0, 7))) 
			    {
					int days = AllUtil.Numberofstatistical(ym,ksrq, jsrq);
					waichutianshu += days;
			    }
			}
			json_tongji.put("�������", waichutianshu);
		
			//�ٵ����˿���ͳ�ƿ�ʼ
			int kgts=0,cdcs=0,cdfz=0,ztcs=0,ztfz=0,sjcqts=0,ldkcs=0,zonggongshi=0;
			int x1=0,x2=0,x3=0,x4=0,jbsc=0;
			boolean isHave = AllUtil.getTf2s(jsons_tongji, sqr);
			if (!isHave) {
				rs.execute("select * from uf_kqsj where xm='"+sqr+"'");
				while (rs.next()) {
					String rq =Util.null2String(rs.getString("rq"));//����2018-11-18
					if (rq.substring(0, 7).equals(ym)) {
						String dksj=Util.null2String(rs.getString("dksj"));//��ʱ��  "08:00 16:00";
						String pbsjs=Util.null2String(rs.getString("pbsj"));//�Ű�ʱ��  "A��[08:00 16:00]" "R��";
						String bq=Util.null2String(rs.getString("bq"));    //��ǩ
						if (!pbsjs.equals("R��") && !pbsjs.equals("") && !pbsjs.trim().equals("��Ϣ")) {
							int x = pbsjs.indexOf("[");
							int y = pbsjs.indexOf("]");
							String pbsj=pbsjs.substring(x+1, y);
							if (!dksj.equals("")) {sjcqts+=1;}//ʵ�ʳ�������
							if (dksj.length()>7) {//�������,ͳ�ƼӰ����,ʱ��
								String bOrs1=Util.null2String(AllUtil.getZzZw(dksj.split(" ")[0], pbsj.split(" ")[0], "max"));//�ж��Ƿ�ٵ�
								String bOrs2=Util.null2String(AllUtil.getZzZw(dksj.split(" ")[1], pbsj.split(" ")[1], "max"));//�ж��Ƿ�����
								if (bOrs1.equals(dksj.split(" ")[0])) //�ٵ�
								{
									cdcs+=1;
									cdfz+=Integer.parseInt(AllUtil.dateDiff(pbsj.split(" ")[0], dksj.split(" ")[0], "hh:mm"));
								}
								if (bOrs2.equals(pbsj.split(" ")[1])) //����
								{
									ztcs+=1;
									ztfz+=Integer.parseInt(AllUtil.dateDiff(dksj.split(" ")[1], pbsj.split(" ")[1], "hh:mm"));
								}

								if (!departmentid.equals("18")) {//�Ӱ�������ֶ�ֻ����ǿͷ�����Ա
										int flag = AllUtil.compartToTime(dksj.split(" ")[1], "19:30");
										if (flag==-1||flag==0) {//19:30
											x1+=1;
										}else{
											flag = AllUtil.compartToTime(dksj.split(" ")[1], "20:30");
											if (flag==-1 || flag==0) {//19:30-20:30
												x2+=1;
											}else{
												flag = AllUtil.compartToTime(dksj.split(" ")[1], "22:00");
												if (flag==-1 || flag==0) {//20:30-22:00
													x3+=1;
													jbsc+=Integer.parseInt(AllUtil.dateDiff("20:30", dksj.split(" ")[1], "hh:mm"));
												}else{
													x4+=1;
													jbsc+=Integer.parseInt(AllUtil.dateDiff("20:30", dksj.split(" ")[1], "hh:mm"));
												}
											}
										}
										zonggongshi+=Integer.parseInt(AllUtil.dateDiff("18:00", dksj.split(" ")[1], "hh:mm"));//�°࿨��18��00֮���ʱ��
								}
							}else if(!dksj.equals("")&& dksj.length()<7){//�����û���°࿨�����
								if (bq.equals("")) {//©�򿨵������ָ����û�д򿨣�Ҳû�����벹ǩ
									ldkcs+=1;//©�򿨴���
								}else{//��ǩ��Ͳ����ڳٵ����ˣ���Ϊ�û��϶����ʱ�����ó��°�ʱ�䣬����û��ҲĬ�ϡ�
									zonggongshi+=Integer.parseInt(AllUtil.dateDiff("18:00", dksj.split(" ")[1], "hh:mm"));
								}
							}else{//����ǿ��������,����©��
								if (bq.equals("")) {
									ldkcs+=1;
									kgts+=1;
								}
							}
						}
					}
				}
				
				int shijichuqintianshu = sjcqts+chuchaitianshu+waichutianshu;
				int pingjunrgs = shijichuqintianshu==0?0:((shijichuqintianshu*7*60+zonggongshi)/shijichuqintianshu);
				int pjjbsc=(x3+x4)==0?0:(jbsc/(x3+x4));
				json_tongji.put("©������", ldkcs);
				json_tongji.put("ʵ�ʳ�������", shijichuqintianshu);
				json_tongji.put("���˷���", ztfz);
				json_tongji.put("�ٵ�����", cdfz);
				json_tongji.put("���˴���", ztcs);
				json_tongji.put("�ٵ�����", cdcs);
				json_tongji.put("��������", kgts);
				json_tongji.put("19:30ǰ�°�",x1);
				json_tongji.put("19:30-20:30�Ӱ�", x2);
				json_tongji.put("20:30-22:00�Ӱ�", x3);
				json_tongji.put("22:00��Ӱ�", x4);
				json_tongji.put("�Ӱ����", (x3+x4));
				json_tongji.put("�Ӱ�ʱ��", jbsc);
				json_tongji.put("ƽ���Ӱ�ʱ��", pjjbsc);
				json_tongji.put("�ܹ�ʱ", (shijichuqintianshu*7*60+zonggongshi));
				json_tongji.put("ƽ���չ�ʱ", pingjunrgs);
				jsons_tongji.add(json_tongji);
			}else{
				json_tongji.put("©������", "0");
				json_tongji.put("ʵ�ʳ�������", "0");
				json_tongji.put("���˷���", "0");
				json_tongji.put("�ٵ�����", "0");
				json_tongji.put("���˴���", "0");
				json_tongji.put("�ٵ�����", "0");
				json_tongji.put("��������", "0");
				json_tongji.put("19:30ǰ�°�","0");
				json_tongji.put("19:30-20:30�Ӱ�", "0");
				json_tongji.put("20:30-22:00�Ӱ�", "0");
				json_tongji.put("22:00��Ӱ�", "0");
				json_tongji.put("�Ӱ����", "0");
				json_tongji.put("�Ӱ�ʱ��", "0");
				json_tongji.put("ƽ���Ӱ�ʱ��", "0");
				json_tongji.put("�ܹ�ʱ", "0");
				json_tongji.put("ƽ���չ�ʱ", "0");
				jsons_tongji.add(json_tongji);
			}
		  }
		  return_alljson = AllUtil.returnJSON(jsons_tongji);
	return return_alljson;
   }
}
