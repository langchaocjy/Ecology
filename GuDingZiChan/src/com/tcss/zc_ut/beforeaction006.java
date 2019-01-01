package com.tcss.zc_ut;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class beforeaction006 extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		RecordSet rs = new RecordSet();
		String requestid = requestinfo.getRequestid();
		WISap_2_util sap_2_util = new WISap_2_util();
		JSONObject mainData=sap_2_util.getMainData(requestid);
		String sgyy = Util.null2String(mainData.getString("sgyy"));
		String gys=Util.null2String(mainData.getString("gys"));
		String fycdbm=Util.null2String(mainData.getString("fycdbm"));
		rs.execute("select lrzx,lrzxmc from uf_cbyjjzxxx "
				+ "where cbzx in(select cbzxbm from formtable_main_30 where id='"+fycdbm+"')");
		String lrzx = "",lrzxmc="";
		if (rs.next()) {
			lrzx=Util.null2String(rs.getString("lrzx"));
			lrzxmc=Util.null2String(rs.getString("lrzxmc"));
		}
		JSONArray detailData=sap_2_util.getDetailData(requestid, 1);
		
		int hjje = 0;
		for (int i = 0; i < detailData.size(); i++) {
			JSONObject dD = detailData.getJSONObject(i);
			String km=Util.null2String(dD.getString("km"));
			String kmms=Util.null2String(dD.getString("kmms"));
			int hsje = Util.getIntValue(dD.getString("hsje"), 0);
			hjje += hsje;
			rs.execute("insert into formtable_main_187_dt2(jd,km,kmms,jine,xmnw,zcswlx,fp)"
					+ "values('70','"+km+"','"+kmms+"','"+hsje+"','"+sgyy+"','100','"+gys+"')");
		}
		rs.execute("insert into formtable_man_187_dt2(jd,km,kmms,jine,xmnw,lrzx,lrzxms,fp)"
				+ "values('50','2518','其他应付款-房租-预提','"+hjje+"','"+sgyy+"','"+lrzx+"','"+lrzxmc+"','"+gys+"')");
		return SUCCESS;
	}
   
}
