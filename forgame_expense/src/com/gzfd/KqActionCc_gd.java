package com.gzfd;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionCc_gd extends KqUtil implements Action {
	public String execute(RequestInfo request) {
		boolean flag = true;
		String errors = "";
		int lcid = Util.getIntValue(request.getRequestid(), 0);
		execCc(lcid);
		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		return "1";
	}

	public void execCc(int lcid) {
		String tbName = getTabName(1, lcid);
		RecordSet rs = new RecordSet();
		rs.execute("select * from " + tbName + " where requestid=" + lcid);
		if (rs.next()) {
			int kqgz = Util.getIntValue(rs.getString("kqgz"), -1);
			int bgdd = Util.getIntValue(rs.getString("bgdd"), 0);
			int ryid = Util.getIntValue(rs.getString("sqr"), 0);
			if (gKqgz.isEmpty()) {
				initMap("");
			}
			String gzmc = "";
			String ddmc = "";
			if ((kqgz == -1) || (bgdd == 0)) {
				JSONObject ryJson = new JSONObject();
				kqgz = Util.getIntValue(ryJson.optString("kqgz"));
				gzmc = Util.null2String(ryJson.optString("kqgzMc"));
				bgdd = Util.getIntValue(ryJson.optString("gzdId"));
				ddmc = Util.null2String(ryJson.optString("gzd"));
			} else {
				gzmc = (gKqgz.containsKey(Integer.valueOf(kqgz))) ? (String) gKqgz.get(Integer.valueOf(kqgz)) : "";
				ddmc = (gKqcs.containsKey(Integer.valueOf(bgdd))) ? (String) gKqcs.get(Integer.valueOf(bgdd)) : "";
			}
			String ksrq = Util.null2String(rs.getString("ksrq"));
			String jsrq = Util.null2String(rs.getString("jsrq"));
			String kssj = Util.null2String(rs.getString("kssj"));
			String jssj = Util.null2String(rs.getString("jssj"));
			String[] ccsc = getCcsc(gzmc, ksrq, jsrq, kssj, jssj);

			JSONObject lcJson = new JSONObject();
			lcJson.put("lcid", Integer.valueOf(lcid));
			lcJson.put("ryid", Integer.valueOf(ryid));
			lcJson.put("lx", "出差");
			lcJson.put("ksrq", ksrq);
			lcJson.put("jsrq", jsrq);
			lcJson.put("ts", ccsc[0]);
			lcJson.put("lcgz", gzmc);
			lcJson.put("lccs", ddmc);
			lcJson.put("kssj", kssj);
			lcJson.put("jssj", jssj);
			lcJson.put("sc", ccsc[1]);
			lcJson.put("zt", "归档");
			setLcjl(lcJson);
		}
	}
}
