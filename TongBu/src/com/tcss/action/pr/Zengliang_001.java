package com.tcss.action.pr;

import java.util.Date;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import com.tcss.hr.HrUtil;
import com.tcss.hr.SapPiInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.company.DT_OA_AFS_OA001;
import out.deal.afs.company.DT_OA_AFS_OA001_REP;
import out.deal.afs.company.Hrmjobtitles_type0;
import out.deal.afs.company.MT_OA_AFS_OA001;
import out.deal.afs.company.MT_OA_AFS_OA001_REP;
import out.deal.afs.company.SHEET_type0;
import out.deal.afs.company.SIO_OA_AFS_OA001Service;
import out.deal.afs.company.SIO_OA_AFS_OA001ServiceStub;

public class Zengliang_001 extends HrUtil{
   public JSONArray zengliang001=new JSONArray();
   public JSONArray getZengliang001(){
	   return zengliang001;
   }
   public void setZengliang001(JSONArray zengliang001){
	   this.zengliang001=zengliang001;
   }
   public Zengliang_001(){
	   String date = getDateString("yyyyMMdd", new Date());
	   JSONArray zengliang001=new JSONArray();
	   String username = SapPiInfo.getUsername_Hr();
	   String password = SapPiInfo.getPassword_Hr();
	   try {
		    SIO_OA_AFS_OA001Service stub = new SIO_OA_AFS_OA001ServiceStub();
			HttpTransportPropertiesImpl.Authenticator basicauth = new HttpTransportPropertiesImpl.Authenticator();
			basicauth.setUsername(username); // 服务器访问用户名
			basicauth.setPassword(password); // 服务器访问密码
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicauth);
			DT_OA_AFS_OA001 dt = new DT_OA_AFS_OA001();
			dt.setBEGDA(date);
			dt.setENDDA(date);
			dt.setZFLAG("1");
			MT_OA_AFS_OA001 mt = new MT_OA_AFS_OA001();
			mt.setMT_OA_AFS_OA001(dt);
			MT_OA_AFS_OA001_REP mt_erp = stub.sIO_OA_AFS_OA001(mt);
			DT_OA_AFS_OA001_REP dt_erp = mt_erp.getMT_OA_AFS_OA001_REP();
			SHEET_type0 s = dt_erp.getSHEET();
			Hrmjobtitles_type0[] hrmjobtitles = s.getHrmjobtitles();
			if (hrmjobtitles != null) {
				write("SAP传递岗位数量：" + hrmjobtitles.length);
				int e = 0;
				for (int i = 0; i < hrmjobtitles.length; i++) {
					JSONObject json = new JSONObject();
					Hrmjobtitles_type0 hrmjobtitles_type0 = hrmjobtitles[i];
					String departmentnamebm = null2String(hrmjobtitles_type0.getDepartmentnamebm());
					String jobtitlecode = null2String(hrmjobtitles_type0.getJobtitlecode());
					String jobtitlemark = null2String(hrmjobtitles_type0.getJobtitlemark());
					String kgbs=null2String(hrmjobtitles_type0.getKgbs());
					String bukrs=null2String(hrmjobtitles_type0.getBukrs());
					String bk1=null2String(hrmjobtitles_type0.getBk1());
					String bk2=null2String(hrmjobtitles_type0.getBk2());
					if (!jobtitlecode.equals("") && !jobtitlemark.equals("")) {
						json.put("岗位名称", jobtitlemark);
						json.put("岗位简称", jobtitlemark);
						json.put("岗位编码", jobtitlecode);
						json.put("部门编码", departmentnamebm);
						json.put("空岗标识", kgbs);
						json.put("公司代码", bukrs);
						json.put("预留字段1", bk1);
						json.put("预留字段2", bk2);
						zengliang001.add(json);
					} else {
						write(++e + "岗位残缺数据：departmentnamebm:" + departmentnamebm + "  " + "jobtitlecode:" + jobtitlecode + "  " + "jobtitlemark:" + jobtitlemark + " ");
					}
				}
			} else {
				write("SAP传递岗位数量：null");
			}

	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   setZengliang001(zengliang001);
   }
}
