package com.tcss.action.pr;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class updateLevel extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		String requestid = request.getRequestid();
		String tablename = request.getRequestManager().getBillTableName();
		RecordSet rs = new RecordSet();
		String sql1 = "select gh,aqjb from "+tablename+" where requestid ='"+requestid+"'";
		writeLog("[sql1   ] " +sql1);
		rs.execute(sql1);
		String gh=null,aqjb=null;
		if (rs.next()) 
		{
			gh = Util.null2String(rs.getString("gh"));
			aqjb = Util.null2String(rs.getString("aqjb"));
		}
		String sql2 = "update hrmresource set seclevel ='"+aqjb+"' where loginid ='"+gh+"'";
		writeLog("[sql2   ] "+sql2);
		rs.execute(sql2);
		return SUCCESS;
	}
}
