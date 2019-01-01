package com.tcss.action.pr;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
/**
 * 入职办理单普工前四个节点退回清空
 * **/
public class ruzhibanlidan_sigejiedian extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		FlowUtil flowUtil = new FlowUtil();
		JSONObject mainData = flowUtil.getMainData(requestid);
		
		String gwbm = Util.null2String(mainData.getString("gwbm"));
		if (!gwbm.equals("")) {
			RecordSet rs = new RecordSet();
			rs.execute("update uf_bmgwxx set oasybs='' where jobtitlecode='"+gwbm+"'");
		}
		return SUCCESS;
	}

}
