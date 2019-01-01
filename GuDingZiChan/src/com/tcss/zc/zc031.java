package com.tcss.zc;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc031.DATA_type0;
import out.deal.afs.zc031.DT_FI_OA_AFS_OA031;
import out.deal.afs.zc031.DT_FI_OA_AFS_OA031_REP;
import out.deal.afs.zc031.MT_FI_OA_AFS_OA031;
import out.deal.afs.zc031.MT_FI_OA_AFS_OA031_REP;
import out.deal.afs.zc031.SIO_FI_OA_AFS_OA031Service;
import out.deal.afs.zc031.SIO_FI_OA_AFS_OA031ServiceStub;
import weaver.general.BaseBean;


/**
 * author 陈嘉源   资产类别读取
 * 
 * **/
public class zc031 extends BaseBean{
    public JSONArray zplb(String AF){
    	String username=getPropValue("tcsssaphr", "username_hr");
    	String password=getPropValue("tcsssaphr", "password_hr");
    	JSONArray jsonarray = new JSONArray();
    		SIO_FI_OA_AFS_OA031Service stub;
			try {
				stub = new SIO_FI_OA_AFS_OA031ServiceStub();
				HttpTransportPropertiesImpl.Authenticator basicauth=new HttpTransportPropertiesImpl.Authenticator();
	    		basicauth.setUsername(username);
	    		basicauth.setPassword(password);
	    		((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicauth);
	    		DT_FI_OA_AFS_OA031 dt_FI_OA_AFS_OA031=new DT_FI_OA_AFS_OA031();
	    		dt_FI_OA_AFS_OA031.setAFAPL(AF);
	    		
	    		MT_FI_OA_AFS_OA031 mt_FI_OA_AFS_OA031=new MT_FI_OA_AFS_OA031();
	    		mt_FI_OA_AFS_OA031.setMT_FI_OA_AFS_OA031(dt_FI_OA_AFS_OA031);
	    		
	    		MT_FI_OA_AFS_OA031_REP mt_FI_OA_AFS_OA031_REP=stub.sIO_FI_OA_AFS_OA031(mt_FI_OA_AFS_OA031);
	    		DT_FI_OA_AFS_OA031_REP dt_FI_OA_AFS_OA031_REP=mt_FI_OA_AFS_OA031_REP.getMT_FI_OA_AFS_OA031_REP();
	    		DATA_type0[] datas=dt_FI_OA_AFS_OA031_REP.getDATA();
	    		
	    		JSONObject jsonobject = new JSONObject();
	    		if(datas!=null){
	    			for(int i=0;i<datas.length;i++){
	    				DATA_type0 data=datas[i];
	    				String afapl = data.getAFAPL();
	    				String anlkl = data.getANLKL();
	    				String txk20 = data.getTXK20();
	    				String zkeds = data.getZKEDS();
	    				jsonobject.put("AFAPL", afapl);
	    				jsonobject.put("ANLKL", anlkl);
	    				jsonobject.put("TXK20", txk20);
	    				jsonobject.put("ZKEDS", zkeds);
	    				jsonarray.add(jsonobject);
	    			}
	    		}
			} catch (AxisFault e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return jsonarray;
    }
}
