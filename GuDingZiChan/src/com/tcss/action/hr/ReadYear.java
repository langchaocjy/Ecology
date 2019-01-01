package com.tcss.action.hr;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;
import net.sf.json.JSONObject;
import out.deal.afs.ry003.DT_OA_AFS_OA003;
import out.deal.afs.ry003.DT_OA_AFS_OA003_REP;
import out.deal.afs.ry003.MT_OA_AFS_OA003;
import out.deal.afs.ry003.MT_OA_AFS_OA003_REP;
import out.deal.afs.ry003.SIO_OA_AFS_OA003Service;
import out.deal.afs.ry003.SIO_OA_AFS_OA003ServiceStub;
import weaver.general.BaseBean;
import weaver.general.Util;

public class ReadYear extends BaseBean{
	public JSONObject ReadYear(String workcode ,String date){
		   String username=getPropValue("tcsssaphr", "username_hr");
	   	   String password=getPropValue("tcsssaphr", "password_hr");
	   	   SIO_OA_AFS_OA003Service stub;
		   JSONObject json = new JSONObject();
	   	   try {
			   stub = new SIO_OA_AFS_OA003ServiceStub();
				  HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
				  bas.setUsername("IF_OA_QAS");
				  bas.setPassword("qaz123,0");
				  ((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
				  
				  DT_OA_AFS_OA003 afs_OA003 = new DT_OA_AFS_OA003();
			      afs_OA003.setGh(workcode);
			      afs_OA003.setSqrq(date);
			      
			      MT_OA_AFS_OA003 afs_OA0032=new MT_OA_AFS_OA003();
			      afs_OA0032.setMT_OA_AFS_OA003(afs_OA003);
			      MT_OA_AFS_OA003_REP afs_OA003_REP=stub.sIO_OA_AFS_OA003(afs_OA0032);
			      DT_OA_AFS_OA003_REP afs_OA003_REP2=afs_OA003_REP.getMT_OA_AFS_OA003_REP();
			      String fdzj= Util.null2String(afs_OA003_REP2.getFdzj());
			      String jnnjss=Util.null2String(afs_OA003_REP2.getJnnjss());
			      String jntxss=Util.null2String(afs_OA003_REP2.getJntxss());
			      String kxnj=Util.null2String(afs_OA003_REP2.getKxnj());
			      String kxtx=Util.null2String(afs_OA003_REP2.getKxtx());
			      String qnnjss=Util.null2String(afs_OA003_REP2.getQnnjss());
			      String qntxss=Util.null2String(afs_OA003_REP2.getQntxss());
			      json.put("fdzj", fdzj);
			      json.put("jnnjss", jnnjss);
			      json.put("kxnj", kxnj);
			      json.put("kxtx", kxtx);
			      json.put("jntxss", jntxss);
			      json.put("qnnjss", qnnjss);
			      json.put("qntxss", qntxss);
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   	   return json;
	}	
	public static void main(String[] args){
		JSONObject json = new ReadYear().ReadYear("70228","20190102");
		System.out.println(json);
		
	}
}
