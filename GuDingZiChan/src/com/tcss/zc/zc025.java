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
			String aktiv = return_type0.getAKTIV();//资产化日期
			String anlkl = return_type0.getANLKL();//资产类别
			String anln1 = return_type0.getANLN1();//资产编号
			String bukrs = return_type0.getBUKRS();//公司代码
			String e_msg = return_type0.getE_MSG();//错误信息
			String gsber = return_type0.getGSBER();//业务范围
			String invnr = return_type0.getINVNR();//NC资产编号
			String kansw = return_type0.getKANSW();//期末净值
			String kfzkz = return_type0.getKFZKZ();//执照编号
			String kostl = return_type0.getKOSTL();//成本中心
			String meins = return_type0.getMEINS();//计量单位
			String ndurj = return_type0.getNDURJ();//预计使用期限
			String ord42 = return_type0.getORD42();//城市
			String ord44 = return_type0.getORD44();//增加方式
			String pernr = return_type0.getPERNR();//人员编号
			String symon = return_type0.getSYMON();//剩余资产折旧年月
			String txt50 = return_type0.getTXT50();//资产名称
			String ysymon = return_type0.getYSYMON();//已使用期限
			String answl = return_type0.getANSWL();//资产原值
			String zzcjz = return_type0.getZZCJZ();//资产净值
			
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
