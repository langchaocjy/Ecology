package com.tcss.zc;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONObject;
import out.deal.afs.zc025.DT_FI_OA_AFS_OA025;
import out.deal.afs.zc025.DT_FI_OA_AFS_OA025_REP;
import out.deal.afs.zc025.MT_FI_OA_AFS_OA025;
import out.deal.afs.zc025.MT_FI_OA_AFS_OA025_REP;
import out.deal.afs.zc025.RETURN_type0;
import out.deal.afs.zc025.SIO_FI_OA_AFS_OA025Service;
import out.deal.afs.zc025.SIO_FI_OA_AFS_OA025ServiceStub;
import weaver.general.BaseBean;

public class zc025 extends BaseBean{
    public JSONObject readCard(String gsdm,String zcbh){
    	String username=getPropValue("tcsssaphr", "username_hr");
    	String password=getPropValue("tcsssaphr", "password_hr");
    	JSONObject json = new JSONObject();
    	SIO_FI_OA_AFS_OA025Service stub;
    	try {
			stub = new SIO_FI_OA_AFS_OA025ServiceStub();
			HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
			bas.setUsername(username);
			bas.setPassword(password);
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
			
			DT_FI_OA_AFS_OA025 dt_FI_OA_AFS_OA025 = new DT_FI_OA_AFS_OA025();
			dt_FI_OA_AFS_OA025.setANLN1(zcbh);
			dt_FI_OA_AFS_OA025.setBUKRS(gsdm);
			
			MT_FI_OA_AFS_OA025 mt_FI_OA_AFS_OA025 = new MT_FI_OA_AFS_OA025();
			mt_FI_OA_AFS_OA025.setMT_FI_OA_AFS_OA025(dt_FI_OA_AFS_OA025);
			
			MT_FI_OA_AFS_OA025_REP mt_FI_OA_AFS_OA025_REP = stub.sIO_FI_OA_AFS_OA025(mt_FI_OA_AFS_OA025);
			DT_FI_OA_AFS_OA025_REP dt_FI_OA_AFS_OA025_REP = mt_FI_OA_AFS_OA025_REP.getMT_FI_OA_AFS_OA025_REP();
			
		
			RETURN_type0 return_type0 = dt_FI_OA_AFS_OA025_REP.getRETURN();
			String aktiv = return_type0.getAKTIV();//�ʲ�������
			String anlkl = return_type0.getANLKL();//�ʲ����
			String anln1 = return_type0.getANLN1();//�ʲ����
			String bukrs = return_type0.getBUKRS();//��˾����
			String e_msg = return_type0.getE_MSG();//������Ϣ
			String gsber = return_type0.getGSBER();//ҵ��Χ
			String invnr = return_type0.getINVNR();//NC�ʲ����
			String kansw = return_type0.getKANSW();//��ĩ��ֵ
			String kfzkz = return_type0.getKFZKZ();//ִ�ձ��
			String kostl = return_type0.getKOSTL();//�ɱ�����
			String meins = return_type0.getMEINS();//������λ
			String ndurj = return_type0.getNDURJ();//Ԥ��ʹ������
			String ord42 = return_type0.getORD42();//����
			String ord44 = return_type0.getORD44();//���ӷ�ʽ
			String pernr = return_type0.getPERNR();//��Ա���
			String symon = return_type0.getSYMON();//ʣ���ʲ��۾�����
			String txt50 = return_type0.getTXT50();//�ʲ�����
			String ysymon = return_type0.getYSYMON();//��ʹ������
			String answl = return_type0.getANSWL();//�ʲ�ԭֵ
			String zzcjz = return_type0.getZZCJZ();//�ʲ���ֵ
			
			json.put("aktiv", aktiv);
			json.put("anlkl", anlkl);
			json.put("anln1", anln1);
			json.put("bukrs", bukrs);
			json.put("e_msg", e_msg);
			json.put("gsber", gsber);
			json.put("invnr", invnr);
			json.put("kansw", kansw);
			json.put("kfzkz", kfzkz);
			json.put("kostl", kostl);
			json.put("meins", meins);
			json.put("ndurj", ndurj);
			json.put("ord42", ord42);
			json.put("ord44", ord44);
			json.put("pernr", pernr);
			json.put("symon", symon);
			json.put("txt50", txt50);
			json.put("ysymon", ysymon);
			json.put("answl", answl);
			json.put("zzcjz", zzcjz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return json;
    }
    

}
