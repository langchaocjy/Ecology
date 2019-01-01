package com.tcss.zc;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc039.DATA_type0;
import out.deal.afs.zc039.DT_FI_OA_AFS_OA039;
import out.deal.afs.zc039.DT_FI_OA_AFS_OA039_REP;
import out.deal.afs.zc039.MT_FI_OA_AFS_OA039;
import out.deal.afs.zc039.MT_FI_OA_AFS_OA039_REP;
import out.deal.afs.zc039.SIO_FI_OA_AFS_OA039Service;
import out.deal.afs.zc039.SIO_FI_OA_AFS_OA039ServiceStub;
import weaver.general.BaseBean;

public class zc039 extends BaseBean{
     public JSONArray readTaxCode(String taxcode){
    	 String username=getPropValue("tcsssaphr", "username_hr");
     	 String password=getPropValue("tcsssaphr", "password_hr");
     	 JSONArray json = new JSONArray();
     	 SIO_FI_OA_AFS_OA039Service stub;
     	 try {
     		 stub = new SIO_FI_OA_AFS_OA039ServiceStub();
     		 HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
     		 bas.setUsername(username);
     		 bas.setPassword(password);
     		 ((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
     	     
     		 DT_FI_OA_AFS_OA039 dt_FI_OA_AFS_OA039 = new DT_FI_OA_AFS_OA039();
     		 dt_FI_OA_AFS_OA039.setMWSKZ(taxcode);
     		 
     		 MT_FI_OA_AFS_OA039 mt_FI_OA_AFS_OA039 = new MT_FI_OA_AFS_OA039();
     		 mt_FI_OA_AFS_OA039.setMT_FI_OA_AFS_OA039(dt_FI_OA_AFS_OA039);
     		 
     		 MT_FI_OA_AFS_OA039_REP mt_FI_OA_AFS_OA039_REP = stub.sIO_FI_OA_AFS_OA039(mt_FI_OA_AFS_OA039);
     		 DT_FI_OA_AFS_OA039_REP dt_FI_OA_AFS_OA039_REP = mt_FI_OA_AFS_OA039_REP.getMT_FI_OA_AFS_OA039_REP();
     		 String e_msg = dt_FI_OA_AFS_OA039_REP.getE_MSG();//��Ϣ
     		 String e_subrc = dt_FI_OA_AFS_OA039_REP.getE_SUBRC();//�ɹ���ʧ��
     		 writeLog(e_msg+"---"+e_subrc);
     		 
     		 DATA_type0[] data_type0s = dt_FI_OA_AFS_OA039_REP.getDATA();
     		 JSONObject jsonobject = new JSONObject();
     		 if(data_type0s != null){
     			 for (int i = 0; i < data_type0s.length; i++) {
     				 DATA_type0 data = data_type0s[i];
     				 String mwskz = data.getMWSKZ();//˰��
     				 String mwart = data.getMWART();//˰����
     				 String kbetr = data.getKBETR();//˰��
     				 String text1 = data.getTEXT1();//����
     				 jsonobject.put("mwskz", mwskz);
     				 jsonobject.put("mwart", mwart);
     				 jsonobject.put("kbetr", kbetr);
     				 jsonobject.put("text1", text1);
     				 json.add(jsonobject);
				}
     		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
     		
     }
}
