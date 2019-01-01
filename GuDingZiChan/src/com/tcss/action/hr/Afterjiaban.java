package com.tcss.action.hr;

import com.tcss.zc_ut.WISap_2_util;
import com.weaver.general.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestManager;

public class Afterjiaban extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		RequestManager manager = requestinfo.getRequestManager();
		
		WISap_2_util sap_2_util=new WISap_2_util();
		JSONObject mainData=sap_2_util.getMainData(requestid);
		JSONArray detailData = sap_2_util.getDetailData(requestid, 1);
		
		double byyjbss = Util.getDoubleValue(mainData.getString("byyjbss"), 0.00);
		double bcjbss_all = Util.getDoubleValue(mainData.getString("bcjbss"),0.00);
		String sqrq = Util.null2String(mainData.getString("sqrq")).replace("-", "");
		int intsqrq = Util.getIntValue(sqrq.substring(0, 6), 201012);
		writeLog("byyjbss:  "+byyjbss+"  bcjbss_all:"+bcjbss_all+"  intsqrq:"+intsqrq);
		for (int i = 0; i < detailData.size(); i++) {
			JSONObject json = detailData.getJSONObject(i);
			double bcjbss = Util.getDoubleValue(json.getString("bcjbss"), 0.00);
			writeLog("bcjbss:  "+bcjbss);
			
			double b1 = bcjbss/0.5;
			int index = String.valueOf(b1).indexOf(".");
			int b2 = Util.getIntValue(String.valueOf(b1).substring(0, index));
			if (b1 != b2 || bcjbss < 0.5 || bcjbss==0) {
				manager.setMessagecontent("本次加班时数必须是0.5的整数倍且不能等于0，在第"+i+"行！");
				manager.setMessageid(System.currentTimeMillis()+"");
			}
			
			String nf = Util.null2String(json.getString("nf"));
			String yf = Util.null2String(json.getString("yf"));
			if (yf.length() == 1) {
				yf = "0"+yf;
			}
			String nfyf = nf+yf;
			int intnfyf = Util.getIntValue(nfyf,1);
			if (intsqrq > intnfyf) {
				manager.setMessagecontent("不能申请本月以前的加班时间!");
				manager.setMessageid(System.currentTimeMillis()+"");
			}
		}
		
		double alltime=byyjbss+bcjbss_all;
		writeLog("alltime:  "+alltime);
		if (alltime>36) {
			manager.setMessagecontent("加班时间超过36小时！");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		return SUCCESS;
	}
	public static void main(String[] args){
		double a = 0.0;
		double a1 = 0.0/0.5;
		System.out.println(a1);
		int index =String.valueOf(a1).indexOf(".");
		int b2 = Util.getIntValue(String.valueOf(a1).substring(0, index));
		System.out.println(b2);
	}
}
