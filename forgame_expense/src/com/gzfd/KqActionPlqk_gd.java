package com.gzfd;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionPlqk_gd extends KqUtil implements Action {
	public String execute(RequestInfo request) {
		boolean flag = true;
		String errors = "";
		int lcid = Util.getIntValue(request.getRequestid(), 0);
		execPlqk(lcid);
		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		return "1";
	}

	public void execPlqk(int lcid) {
		String tbName = getTabName(1, lcid);
		RecordSet rs = new RecordSet();
		rs.execute("select * from " + tbName + " where requestid=" + lcid);
		if (rs.next()) {
			if (gHrmMap.isEmpty()) {
				initMap("");
			}

			String plqkry = Util.null2String(rs.getString("plqkry"));
			String qkrq = Util.null2String(rs.getString("qkrq"));
			String qksj = Util.null2String(rs.getString("qksj"));
			String[] qkrys = Util.TokenizerString2(plqkry, ",");
			for (String ry : qkrys) {
				int ryid = Util.getIntValue(ry, 0);
				JSONObject ryJson = new JSONObject();
				String gzmc = Util.null2String(ryJson.optString("kqgzMc"));
				String ddmc = Util.null2String(ryJson.optString("gzd"));
				JSONObject lcJson = new JSONObject();
				lcJson.put("lcid", Integer.valueOf(lcid));
				lcJson.put("ryid", Integer.valueOf(ryid));
				lcJson.put("lx", "批量签卡");
				lcJson.put("ksrq", qkrq);
				lcJson.put("jsrq", qkrq);
				lcJson.put("ts", Integer.valueOf(0));
				lcJson.put("lcgz", gzmc);
				lcJson.put("lccs", ddmc);
				lcJson.put("kssj", qksj);
				lcJson.put("jssj", qksj);
				lcJson.put("sc", Integer.valueOf(1));
				lcJson.put("zt", "归档");
				setLcjl(lcJson);
			}
		}
	}
}
