package com.tcss.zc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import com.tcss.zc_ut.Message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc006.DOC_HEADER_type0;
import out.deal.afs.zc006.DOC_ITEM_type0;
import out.deal.afs.zc006.DT_FI_OA_AFS_OA006;
import out.deal.afs.zc006.DT_FI_OA_AFS_OA006_REP;
import out.deal.afs.zc006.ET_RETURN_type0;
import out.deal.afs.zc006.MT_FI_OA_AFS_OA006;
import out.deal.afs.zc006.MT_FI_OA_AFS_OA006_REP;
import out.deal.afs.zc006.SIO_FI_OA_AFS_OA006Service;
import out.deal.afs.zc006.SIO_FI_OA_AFS_OA006ServiceStub;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class zc006 extends BaseBean{
   private ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
   public ArrayList<Map<String,String>> getList() {
	   return list;
   }
   public void setList(ArrayList<Map<String,String>> list) {
	   this.list = list;
   }
   public zc006(JSONArray detailData ,JSONObject mainData){
	   String username=getPropValue("tcsssaphr", "username_hr");
   	   String password=getPropValue("tcsssaphr", "password_hr");
   	   SIO_FI_OA_AFS_OA006Service stub;
   	   try {
		   stub = new SIO_FI_OA_AFS_OA006ServiceStub();
			  HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
			  bas.setUsername(username);
			  bas.setPassword(password);
			  ((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
			  
			  DT_FI_OA_AFS_OA006 afs_OA006 = new DT_FI_OA_AFS_OA006();
			  DOC_HEADER_type0 doc_HEADER_type0=afs_OA006.getDOC_HEADER();
			  doc_HEADER_type0.setCOMP_CODE(Util.null2String(mainData.getString("gsdm")));//��˾����
			  doc_HEADER_type0.setD_DATE(Util.null2String(mainData.getString("sqrq")).replace("-", ""));//oa�������
			  doc_HEADER_type0.setDOC_DATE(Util.null2String(mainData.getString("jzrq")).replace("-", ""));//ƾ֤�е�ƾ֤����
			  doc_HEADER_type0.setDOC_TYPE(Util.null2String(mainData.getString("pzlx")));//ƾ֤����
			  doc_HEADER_type0.setF_NAME(Util.null2String(mainData.getString("bdmc")));//oa������
			  doc_HEADER_type0.setHEADER_TXT(Util.null2String(mainData.getString("pzttwb")));//ƾ̧֤ͷ�ı�
			  doc_HEADER_type0.setKURSF(Util.null2String(mainData.getString("huilv")));//����
			  doc_HEADER_type0.setP_USER(Util.null2String(mainData.getString("currentname")));//����ڵ��Ӧ����
			  doc_HEADER_type0.setPSTNG_DATE(Util.null2String(mainData.getString("pzrq")).replace("-", ""));//ƾ֤�еĹ�������
			  doc_HEADER_type0.setREF_DOC_NO(Util.null2String(mainData.getString("lsh")));//�ο�ƾ֤���
			  doc_HEADER_type0.setSTAMP(Util.null2String(mainData.getString("scsjc")));//oa�ϴ�ʱ���
			  doc_HEADER_type0.setT_ACCOUNT(Util.null2String(mainData.getString("gonghao")));//oa�ύ���˺�
			  doc_HEADER_type0.setT_NAME(Util.null2String(mainData.getString("sqr")));//oa�ύ������
			  doc_HEADER_type0.setTIMES(Util.null2String(mainData.getString("tims006")));//oa�ϴ�����
			  doc_HEADER_type0.setTYPE(Util.null2String(mainData.getString("bdlx")));//oa������
			  doc_HEADER_type0.setUSERNAME(Util.null2String(mainData.getString("nodeoperators")));//���������ڵ��Ӧ�˺�
			  doc_HEADER_type0.setWAERS(Util.null2String(mainData.getString("bizhong")));//����
			  doc_HEADER_type0.setXREF1_HD(Util.null2String(mainData.getString("ckm")));//ҵ��ο�
			  afs_OA006.setDOC_HEADER(doc_HEADER_type0);
			  
			  int index = detailData.size();
			  for (int i = 0; i < index; i++) {
				  DOC_ITEM_type0 doc_ITEM_type0=new DOC_ITEM_type0();
				  afs_OA006.addDOC_ITEM(doc_ITEM_type0);
			  }
			  
			  DOC_ITEM_type0[] doc_ITEM_type0s=afs_OA006.getDOC_ITEM();
			  for (int i = 0; i < doc_ITEM_type0s.length; i++) {
				 DOC_ITEM_type0 doc_HEADER_type02=doc_ITEM_type0s[i];
				 JSONObject jsonObject=detailData.getJSONObject(i);
				 doc_HEADER_type02.setALLOC_NMBR(jsonObject.getString("fp"));//�����
				 doc_HEADER_type02.setAMT_DOCCUR(jsonObject.getString("jine"));//ƾ֤���ҽ��
				 doc_HEADER_type02.setANBWA(jsonObject.getString("cgywlx"));//�ɹ�ҵ������
				 doc_HEADER_type02.setBASE_UOM(jsonObject.getString("danwei"));//��λ
				 doc_HEADER_type02.setBSCHL(jsonObject.getString("jd"));//������
				 doc_HEADER_type02.setBUS_AREA(jsonObject.getString("ywfw"));//ҵ��Χ
				 doc_HEADER_type02.setBZDAT(jsonObject.getString("zbhrq"));//�ʲ���ֵ��
				 doc_HEADER_type02.setCITY(jsonObject.getString("cs"));//����
				 doc_HEADER_type02.setCOSTCENTER(jsonObject.getString("cbzx"));//�ɱ�����
				 doc_HEADER_type02.setDMBTR(jsonObject.getString("bwbje"));//��λ�ҽ��
				 doc_HEADER_type02.setGL_ACCOUNT(jsonObject.getString("km"));//���˿�Ŀ
				 doc_HEADER_type02.setITEM_TEXT(jsonObject.getString(""));//��Ŀ�ı�
				 doc_HEADER_type02.setLAND1(jsonObject.getString("guojia"));//����
				 doc_HEADER_type02.setNAME(jsonObject.getString("mingchen"));//����
				 doc_HEADER_type02.setORDERID(jsonObject.getString("ddh"));//������
				 doc_HEADER_type02.setPMNTTRMS(jsonObject.getString("fktj"));//������������
				 doc_HEADER_type02.setPROFIT_CTR(jsonObject.getString("lrzx"));//��������
				 doc_HEADER_type02.setQUANTITY(jsonObject.getString("shuliang"));//����
				 doc_HEADER_type02.setREF_KEY_1(jsonObject.getString("ck1"));//ҵ����ο���
				 doc_HEADER_type02.setREF_KEY_2(jsonObject.getString("ck2"));//ҵ����ο���
				 doc_HEADER_type02.setREF_KEY_3(jsonObject.getString("ck3"));//ҵ����ο���
				 doc_HEADER_type02.setRSTGR(jsonObject.getString("yydm"));//����ԭ�����
				 doc_HEADER_type02.setUMSKS(jsonObject.getString("zcswlx"));//�ʲ���������
				 doc_HEADER_type02.setUMSKZ(jsonObject.getString("tbzzbz"));//�������ʱ�ʶ
				 doc_HEADER_type02.setVALUT(jsonObject.getString("jzr"));//��Ϣ��
				 doc_HEADER_type02.setXNEGP(jsonObject.getString("fjz"));//��ʶ:������
				 doc_HEADER_type02.setZFBDT(jsonObject.getString("fkjzrq"));//���ڵ����ռ���Ļ�׼����
				 doc_HEADER_type02.setZLIFNR(jsonObject.getString("hzhb"));//��Ӧ�̻�ծȨ�˵��˺�
				 afs_OA006.setDOC_ITEM(doc_ITEM_type0s);
			  } 
			  
			  MT_FI_OA_AFS_OA006 afs_OA0062 = new MT_FI_OA_AFS_OA006();
			  afs_OA0062.setMT_FI_OA_AFS_OA006(afs_OA006);
			  
			  MT_FI_OA_AFS_OA006_REP afs_OA006_REP = stub.sIO_FI_OA_AFS_OA006(afs_OA0062);
			  DT_FI_OA_AFS_OA006_REP afs_OA006_REP2 = afs_OA006_REP.getMT_FI_OA_AFS_OA006_REP();
			  String e_belnr = Util.null2String(afs_OA006_REP2.getE_BELNR());
			  String e_bukrs = Util.null2String(afs_OA006_REP2.getE_BUKRS());
			  String e_gjahr = Util.null2String(afs_OA006_REP2.getE_GJAHR());
			  String v_message=Util.null2String(afs_OA006_REP2.getV_MESSAGE());
			  String v_subrc = Util.null2String(afs_OA006_REP2.getV_SUBRC());
			  RecordSet rs = new RecordSet();
			  rs.execute("update formtable_main_187 set sapcode='"+e_belnr+"',jgbs006='"+v_subrc+"',cwxx006='"+v_message+"' where id='"+mainData.getString("id")+"'");
			  
			  for (int i = 0; i <index; i++) {
				  ET_RETURN_type0 et_RETURN_type0=new ET_RETURN_type0();
				  afs_OA006_REP2.addET_RETURN(et_RETURN_type0);
			  }
			  
			  ArrayList<Map<String,String>> lists = new ArrayList<Map<String,String>>();
			  ET_RETURN_type0[] et_RETURN_type0s=afs_OA006_REP2.getET_RETURN();
			  for (int i = 0; i < et_RETURN_type0s.length; i++) {
				 ET_RETURN_type0 et_RETURN_type0=et_RETURN_type0s[i];
				 String id = Util.null2String(et_RETURN_type0.getID());
				 String message = Util.null2String(et_RETURN_type0.getMESSAGE());
				 String number = Util.null2String(et_RETURN_type0.getNUMBER());
				 String type = Util.null2String(et_RETURN_type0.getTYPE());
				 if (i==0) {
					 rs.execute("update formtable_main_187 set xxlx006='"+type+"' where id='"+mainData.getString("id")+"'");
				 }
				 Map<String ,String> map = new HashMap<String, String>();
				 map.put("e_belnr", e_belnr);
				 map.put("e_bukrs", e_bukrs);
				 map.put("e_gjahr", e_gjahr);
				 map.put("v_message", v_message);
				 map.put("v_subrc", v_subrc);
				 
				 map.put("id", id);
				 map.put("message", message);
				 map.put("number", number);
				 map.put("type", type);
				 lists.add(map);
			  }
			  setList(lists);
	   } catch (Exception e) {
		   throw new Message("ss");
	   }
   }
}
