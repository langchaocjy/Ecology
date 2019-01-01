package com.tcss.zc;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc032.DT_FI_OA_AFS_OA032;
import out.deal.afs.zc032.DT_FI_OA_AFS_OA032_REP;
import out.deal.afs.zc032.MT_FI_OA_AFS_OA032;
import out.deal.afs.zc032.MT_FI_OA_AFS_OA032_REP;
import out.deal.afs.zc032.SIO_FI_OA_AFS_OA032Service;
import out.deal.afs.zc032.SIO_FI_OA_AFS_OA032ServiceStub;
import out.deal.afs.zc032.DATA_type0;
import weaver.general.BaseBean;

public class zc032 extends BaseBean{
    public JSONArray readCity(String code){
    	 String username=getPropValue("tcsssaphr", "username_hr");
     	 String password=getPropValue("tcsssaphr", "password_hr");
     	 JSONArray json = new JSONArray();
     	 SIO_FI_OA_AFS_OA032Service stub;
     	 try {
			stub = new SIO_FI_OA_AFS_OA032ServiceStub();
			HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
			bas.setUsername(username);
			bas.setPassword(password);
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
     	    
			DT_FI_OA_AFS_OA032 dt_FI_OA_AFS_OA032 = new DT_FI_OA_AFS_OA032();
			dt_FI_OA_AFS_OA032.setORD4X(code);
			
			MT_FI_OA_AFS_OA032 mt_FI_OA_AFS_OA032 = new MT_FI_OA_AFS_OA032();
			mt_FI_OA_AFS_OA032.setMT_FI_OA_AFS_OA032(dt_FI_OA_AFS_OA032);
			
			MT_FI_OA_AFS_OA032_REP mt_FI_OA_AFS_OA032_REP = stub.sIO_FI_OA_AFS_OA032(mt_FI_OA_AFS_OA032);
			DT_FI_OA_AFS_OA032_REP dt_FI_OA_AFS_OA032_REP = mt_FI_OA_AFS_OA032_REP.getMT_FI_OA_AFS_OA032_REP();
			String e_msg = dt_FI_OA_AFS_OA032_REP.getE_MSG();
			String e_subrc = dt_FI_OA_AFS_OA032_REP.getE_SUBRC();
			writeLog(e_msg+"-"+e_subrc);
			DATA_type0[] data_type0 = dt_FI_OA_AFS_OA032_REP.getDATA();
			JSONObject jsonobject = new JSONObject();
			for (int i = 0; i < data_type0.length; i++) {
				DATA_type0 data = data_type0[i];
				String ord4x = data.getORD4X();
				String ordtx = data.getORDTX();
				String zkeds = data.getZKEDS();
				writeLog(ord4x+"-"+ordtx+"-"+zkeds);
				
				jsonobject.put("ord4x", ord4x);
				jsonobject.put("ordtx", ordtx);
				jsonobject.put("zkeds", zkeds);
				json.add(jsonobject);
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		return json;
    }
}
