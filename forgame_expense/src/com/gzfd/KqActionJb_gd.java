package com.gzfd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionJb_gd extends KqUtil implements Action {
	public String execute(RequestInfo request) {
		boolean flag = true;
		String errors = "";
		int lcid = Util.getIntValue(request.getRequestid(), 0);
		execJb(lcid);
		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		return "1";
	}

	public void execJb(int lcid) {
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
			String jbrq = Util.null2String(rs.getString("jbrq"));
			String kssj = Util.null2String(rs.getString("kssj"));
			String jssj = Util.null2String(rs.getString("jssj"));
			int wcjb = Util.getIntValue(rs.getString("wcjb"), -1);
			JSONObject lcJson = new JSONObject();
			JSONObject txJson = new JSONObject();
			lcJson.put("lcid", Integer.valueOf(lcid));
			lcJson.put("ryid", Integer.valueOf(ryid));
			lcJson.put("lx", "加班");
			lcJson.put("ksrq", jbrq);
			lcJson.put("jsrq", jbrq);
			lcJson.put("ts", Integer.valueOf(0));
			lcJson.put("lcgz", gzmc);
			lcJson.put("lccs", ddmc);
			if (wcjb == 0) {
				lcJson.put("lb", "外出加班");
			} else {
				Map kaMap = getRyDk(ryid, jbrq, jbrq);
				if (kaMap.containsKey(jbrq)) {
					ArrayList kaList = (ArrayList) kaMap.get(jbrq);
					String[] kaArr = (String[]) kaList.toArray(new String[kaList.size()]);
					Arrays.sort(kaArr);
					log.info("加班取打卡记录 kaArr=" + Arrays.toString(kaArr));
					int len = kaArr.length;
					if (len > 0) {
						if (kaArr[0].compareTo(kssj) > 0) {
							kssj = kaArr[0];
						}
						String dq = ("香港|台湾".indexOf(gzmc) > -1) ? gzmc : "内地";
						if ((!(gzmc.equals("简理财"))) && (isRqlb(dq, jbrq, 1))) {
							kssj = (kssj.compareTo("19:30") > 0) ? kssj : "19:30";
						}
						if (kaArr[(len - 1)].compareTo(jssj) < 0) {
							jssj = kaArr[(len - 1)];
						}
					} else {
						lcJson.put("lb", "待重算时长");
					}
				} else {
					lcJson.put("lb", "待重算时长");
				}
			}
			int jbsc = getJbTxsc(gzmc, jbrq, kssj, jssj, false);
			lcJson.put("kssj", kssj);
			lcJson.put("jssj", jssj);
			lcJson.put("sc", Integer.valueOf(jbsc));
			lcJson.put("zt", "归档");
			setLcjl(lcJson);

			txJson.put("ryid", Integer.valueOf(ryid));
			txJson.put("jbrq", jbrq);
			txJson.put("lcid", Integer.valueOf(lcid));
			txJson.put("sjlx", "1");
			txJson.put("jbsc", Integer.valueOf(jbsc));
			txJson.put("lcgz", gzmc);
			setTxxx(txJson);
		}
	}
}
