package com.gzfd;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionQk_tj extends KqUtil implements Action {
	public String execute(RequestInfo request) {
		boolean flag = true;
		String errors = "";
		int lcid = Util.getIntValue(request.getRequestid(), 0);

		String tbName = getTabName(1, lcid);
		RecordSet rs = new RecordSet();
		int ryid = 0;
		String ny = "";
		rs.execute("select * from " + tbName + " where requestid=" + lcid);
		if (rs.next()) {
			ryid = Util.getIntValue(rs.getString("sqr"), 0);
			String riqi = Util.null2String(rs.getString("riqi"));
			riqi = (riqi.equals("")) ? curDate : riqi;
			ny = riqi.substring(0, 7);
		}
		rs.execute("select sum(sc) sc from uf_lcjl where lx='签卡' and ryid=" + ryid + " and ksrq like '" + ny + "%'");
		if (rs.next()) {
			double sc = Util.getDoubleValue(rs.getString("sc"), 0.0D);
			if (sc >= 3.0D) {
				flag = false;
				errors = "签卡次数已达3次";
			}
		}

		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		return "1";
	}
}
