package com.gzfd;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionQj_gd extends KqUtil implements Action {
	public String execute(RequestInfo request) {
		boolean flag = true;
		String errors = "";
		int lcid = Util.getIntValue(request.getRequestid(), 0);
		execQj(lcid, "归档");
		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		return "1";
	}

	public void execQj(int lcid, String zt) {
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
			String xjlx = Util.null2String(rs.getString("xjlx"));
			String xjmc = (gXjlx.containsKey(xjlx)) ? (String) gXjlx.get(xjlx) : "";
			String ksrq = Util.null2String(rs.getString("ksrq"));
			String jsrq = Util.null2String(rs.getString("jsrq"));
			String kssj = Util.null2String(rs.getString("kssj"));
			String jssj = Util.null2String(rs.getString("jssj"));
			String[] qjsc = getQjsc(gzmc, ksrq, jsrq, kssj, jssj, xjlx);

			JSONObject lcJson = new JSONObject();
			lcJson.put("lcid", Integer.valueOf(lcid));
			lcJson.put("ryid", Integer.valueOf(ryid));
			lcJson.put("lx", "请假");
			lcJson.put("lb", xjmc);
			lcJson.put("ksrq", ksrq);
			lcJson.put("jsrq", jsrq);
			lcJson.put("ts", qjsc[0]);
			lcJson.put("lcgz", gzmc);
			lcJson.put("lccs", ddmc);
			lcJson.put("kssj", kssj);
			lcJson.put("jssj", jssj);
			lcJson.put("sc", qjsc[1]);
			lcJson.put("zt", zt);
			setLcjl(lcJson);

			if (xjmc.equals("调休")) {
				JSONObject txJson = new JSONObject();
				txJson.put("ryid", Integer.valueOf(ryid));
				txJson.put("jbrq", ksrq);
				txJson.put("lcid", Integer.valueOf(lcid));
				txJson.put("sjlx", "2");
				txJson.put("txsc", qjsc[1]);
				txJson.put("lcgz", gzmc);
				setTxxx(txJson);
			} else if (xjmc.equals("年休假")) {
				CalNj(ryid);
			}
		}
	}
}
