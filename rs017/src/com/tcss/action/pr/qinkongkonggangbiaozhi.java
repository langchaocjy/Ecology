package com.tcss.action.pr;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * ��ְ���뵥������
 * �����ǹ鵵��ʱ�����uf_bmgwxx��λ�����oasybs�ֶ�
 * **/
public class qinkongkonggangbiaozhi extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		FlowUtil flowUtil = new FlowUtil();
		JSONObject mainData = flowUtil.getMainData(requestid);
		String xgwbm =mainData.getString("xgwbm");
		RecordSet rs = new RecordSet();
		rs.execute("update uf_bmgwxx set oasybs='' where jobtitlecode='"+xgwbm+"'");
		return SUCCESS;
	}

}
