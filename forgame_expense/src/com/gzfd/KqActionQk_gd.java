package com.gzfd;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionQk_gd extends KqUtil implements Action {
	public String execute(RequestInfo request) {
		boolean flag = true;
		String errors = "";
		int lcid = Util.getIntValue(request.getRequestid(), 0);
		execQk(lcid);
		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		return "1";
	}

	public void execQk(int lcid) {
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
			String riqi = Util.null2String(rs.getString("riqi"));
			String shijian = Util.null2String(rs.getString("shijian"));
			int qkyy = Util.getIntValue(rs.getString("qkyy"), 0);
			String yymc = (qkyy == 1) ? "忘记打卡" : "工作原因";

			JSONObject lcJson = new JSONObject();
			lcJson.put("lcid", Integer.valueOf(lcid));
			lcJson.put("ryid", Integer.valueOf(ryid));
			lcJson.put("lx", "签卡");
			lcJson.put("lb", yymc);
			lcJson.put("ksrq", riqi);
			lcJson.put("jsrq", riqi);
			lcJson.put("ts", Integer.valueOf(0));
			lcJson.put("lcgz", gzmc);
			lcJson.put("lccs", ddmc);
			lcJson.put("kssj", shijian);
			lcJson.put("jssj", shijian);
			lcJson.put("sc", Integer.valueOf(1));
			lcJson.put("zt", "归档");
			setLcjl(lcJson);
		}
	}
}
