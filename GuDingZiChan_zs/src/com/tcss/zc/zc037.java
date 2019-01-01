package com.tcss.zc;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc037.DT_FI_OA_AFS_OA037;
import out.deal.afs.zc037.DT_FI_OA_AFS_OA037_REP;
import out.deal.afs.zc037.ITEM_type0;
import out.deal.afs.zc037.ITEM_type1;
import out.deal.afs.zc037.MT_FI_OA_AFS_OA037;
import out.deal.afs.zc037.MT_FI_OA_AFS_OA037_REP;
import out.deal.afs.zc037.SIO_FI_OA_AFS_OA037Service;
import out.deal.afs.zc037.SIO_FI_OA_AFS_OA037ServiceStub;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class zc037 extends BaseBean{
   public JSONObject createOrder(JSONObject mainData,JSONArray detailData){
	   JSONObject jsonobject = new JSONObject();
	   String username=getPropValue("tcsssaphr", "username_hr");
   	   String password=getPropValue("tcsssaphr", "password_hr");
	   SIO_FI_OA_AFS_OA037Service stub;
	   try {
		  stub = new SIO_FI_OA_AFS_OA037ServiceStub();
		  HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
		  bas.setUsername(username);
		  bas.setPassword(password);
		  ((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
		  DT_FI_OA_AFS_OA037 dt_FI_OA_AFS_OA037 = new DT_FI_OA_AFS_OA037();
		  dt_FI_OA_AFS_OA037.setBUKRS(mainData.getString("gsdm"));//��˾����
		  dt_FI_OA_AFS_OA037.setLIFNR(mainData.getString("gys"));//��Ӧ�̱���
		  dt_FI_OA_AFS_OA037.setEKORG(mainData.getString("ywfw"));//Ʒ��ҵ��Χ
		  dt_FI_OA_AFS_OA037.setZOANU(mainData.getString("lsh"));//���ʲ��ɹ�����oa����
		  dt_FI_OA_AFS_OA037.setZOAST(mainData.getString("zoast"));//״̬Ϊ1�������ϴ���״̬Ϊ2�������ϴ�
		  dt_FI_OA_AFS_OA037.setZOATP(mainData.getString("djlx"));//oa��������
		  dt_FI_OA_AFS_OA037.setZOATN(mainData.getString("djlxms"));//oa������������
		  if (mainData.getString("zoast").equals("1")) 
		  {
			  dt_FI_OA_AFS_OA037.setZOANO(mainData.getString("times"));//�ϴ�����
      	  }
		  else
      	  {
      		  dt_FI_OA_AFS_OA037.setZOANO(mainData.getString("times2"));//�ϴ�����
      	  }
		  dt_FI_OA_AFS_OA037.setZZBST(mainData.getString("ywlx"));//ҵ������
		  dt_FI_OA_AFS_OA037.setZOAUU(mainData.getString("tdr_code"));//�ϴ����˺�
		  dt_FI_OA_AFS_OA037.setZOAUM(mainData.getString("tdr_name"));//�ϴ�������
		  dt_FI_OA_AFS_OA037.setZDATE(mainData.getString("sqrq_"));//�ϴ�����
		  dt_FI_OA_AFS_OA037.setZTIMES(mainData.getString("scsjc"));//�ϴ�ʱ���
		  dt_FI_OA_AFS_OA037.setZOAUR(mainData.getString("sqr_code"));//�������˺�
		  dt_FI_OA_AFS_OA037.setZOATU(mainData.getString("sqr_name"));//����������
		  dt_FI_OA_AFS_OA037.setZOAUN(mainData.getString("sqrq_"));//�������

		  dt_FI_OA_AFS_OA037.setEKGRP("X02");//�ɹ���
		  dt_FI_OA_AFS_OA037.setBSART("P002");//��������
		  writeLog("[-----��������װ�ؽ���-----] "+mainData);
		  int index = 0;
		  for (int a = 0; a < detailData.size(); a++) 
		  {
			  index =detailData.size();
			  ITEM_type1 item_type1 = new ITEM_type1();
			  dt_FI_OA_AFS_OA037.addITEM(item_type1);
		  }
		  
			  ITEM_type1[] datas = dt_FI_OA_AFS_OA037.getITEM();
			  for (int b = 0; b < datas.length; b++) 
			  {
				      ITEM_type1 data = datas[b];
					  JSONObject js = detailData.getJSONObject(b);
					  data.setZITEM(String.valueOf(b+1));//���ʲ��ɹ�������ϸoa���к�
					  data.setZOASQ(js.getString("oazcsqdh"));//OA�ʲ����뵥��
					  data.setZITSQ(js.getString("oazcsqdhh"));//oa�ʲ����뵥�к�
					  data.setTXZ01(js.getString("dwb"));//���ı��ʲ�����
					  String jhrq = js.getString("jhrq");
					  data.setEEIND(jhrq.substring(0,4)+jhrq.substring(5,7)+jhrq.substring(8));//��������
					  data.setNETWR(js.getString("jingjia").replace(",", ""));//��˰���
					  data.setANLN1(js.getString("sapzcbm"));//�ʲ�����
					  data.setMWSKZ(js.getString("shuima"));//˰��
		
					  data.setKNTTP("A");//��Ŀ���
					  data.setMENGE("1");//�ɹ���������
					  data.setMEINS("PC");//������λ
					  data.setWGBEZ("A01");//������
					  data.setWERKS(mainData.getString("gongchang"));//����
					  data.setLGORT("C017");//���ص�
					  dt_FI_OA_AFS_OA037.setITEM(datas);
			  }
		  writeLog("[-----��ϸ������װ�ؽ���------] "+detailData);
		  MT_FI_OA_AFS_OA037 mt_FI_OA_AFS_OA037 = new MT_FI_OA_AFS_OA037();
		  mt_FI_OA_AFS_OA037.setMT_FI_OA_AFS_OA037(dt_FI_OA_AFS_OA037);
		  MT_FI_OA_AFS_OA037_REP mt_FI_OA_AFS_OA037_REP = stub.sIO_FI_OA_AFS_OA037(mt_FI_OA_AFS_OA037);
		  DT_FI_OA_AFS_OA037_REP dt_FI_OA_AFS_OA037_REP = mt_FI_OA_AFS_OA037_REP.getMT_FI_OA_AFS_OA037_REP();
		  String e_msg = Util.null2String(dt_FI_OA_AFS_OA037_REP.getE_MSG());//��Ϣ
		  String e_subrc = dt_FI_OA_AFS_OA037_REP.getE_SUBRC();//0-�ɹ���1-ʧ��\
		  writeLog("037_e_msg--��"+e_msg+"  e_subrc:"+e_subrc);
		  if(!e_subrc.equals("S")){
			  writeLog("[037�ӿ�ʧ�ܺ󷵻ص���Ϣ---��  ]"+e_msg+"--"+e_subrc);
			  jsonobject.put("e_msg", e_msg);
			  jsonobject.put("e_subrc", e_subrc);
		  }
		  if (e_subrc.equals("S"))
		  {
			  writeLog("[037�ӿڳɹ��󷵻ص���Ϣ---��  ]"+e_msg+"--"+e_subrc);
			  jsonobject.put("e_msg", e_msg);
			  jsonobject.put("e_subrc", e_subrc);
		  }
		  
		  RecordSet rs = new RecordSet();
		  for(int d = 0; d < index;d++)
		  {
			  ITEM_type0 item_t = new ITEM_type0();
			  dt_FI_OA_AFS_OA037_REP.addITEM(item_t);
		  }
		  ITEM_type0[] item_type0s = dt_FI_OA_AFS_OA037_REP.getITEM();
		  for (int e = 0; e < item_type0s.length; e++) {
			  ITEM_type0 item_type0 = item_type0s[e];
			  String zoanu = item_type0.getZOANU();//oa����
			  String zitem = item_type0.getZITEM();//oa���к�
			  String ebeln = item_type0.getEBELN();//�ɹ�������
			  String ebelp = item_type0.getEBELP();//�ɹ������к�
			  writeLog("[037�ӿڷ��صľ�����Ϣ---��  ]  oa���ţ�"+zoanu+"  oa���кţ�"+zitem+"  �ɹ������ţ�"+ebeln+"  �ɹ������кţ�"+ebelp);
			  if (e_subrc.equals("S")) {
				JSONObject j_item = detailData.getJSONObject(e);
			    String sql2 = "update formtable_main_187_dt1 set sapcgddhh='"+ebelp+"',cgdh037='"+ebeln+"',sapoadh='"+zoanu+"'"
					 		+ "where id='"+j_item.getString("id")+"'";
			    writeLog("[sql2-->]"+sql2);
				rs.execute(sql2);
			  } 
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return jsonobject;
   }
}
