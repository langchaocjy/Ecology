package com.gzfd;

import java.util.Arrays;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.common.Tools;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class KqActionQj_Tj extends KqUtil implements Action {
	private boolean flag = true;
	private String errors = "";

	public String execute(RequestInfo request) {
		flag = true;
		errors = "";

		int lcid = Util.getIntValue(request.getRequestid(), 0);
		CheckQj(lcid);

		if (!(flag)) {
			String tmp = "失败：" + errors + ";requestid=" + lcid;
			request.getRequestManager().setMessageid("10999");
			request.getRequestManager().setMessagecontent(tmp);
			return "0";
		}
		new KqActionQj_gd().execQj(lcid, "提交");

		return "1";
	}

	public void CheckQj(int lcid) {
		String tbName = getTabName(1, lcid);
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
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

			log.info("CheckQj lcid=" + lcid + ";xjlx=" + xjlx + ";xjmc=" + xjmc + ";gzmc=" + gzmc + ";ddmc=" + ddmc);
			if ((xjmc.equals("带薪病假")) && (!(gzmc.equals("简理财")))) {
				flag = false;
				KqActionQj_Tj tmp437_436 = this;
				tmp437_436.errors = tmp437_436.errors + "简理财 才可以申请”" + xjmc + "“";
				return;
			}
			if (("婚假|丧假|哺乳假|公假".indexOf(xjmc) > -1) && ("香港|台湾".indexOf(gzmc) > -1)) {
				flag = false;
				KqActionQj_Tj tmp502_501 = this;
				tmp502_501.errors = tmp502_501.errors + gzmc + " 不可以申请”" + xjmc + "“";
				return;
			}
			String ksrq = Util.null2String(rs.getString("ksrq"));
			String jsrq = Util.null2String(rs.getString("jsrq"));
			String kssj = Util.null2String(rs.getString("kssj"));
			String jssj = Util.null2String(rs.getString("jssj"));
			log.info("lcid=" + lcid + ";" + ksrq + " " + kssj + " 至 " + jsrq + " " + jssj);
			if ((ksrq.length() != 10) || (jsrq.length() != 10 || (kssj.length() != 5) || (jssj.length() != 5) || (ksrq + " " + kssj).compareTo(jsrq + " " + jssj) > 0)) {
				flag = false;
				errors += "日期/时间填写错误";
				return;
			}
			rs0.execute("select * from uf_lcjl where ryid=" + ryid + " and lx='请假' and (ksrq between '" + ksrq + "' and '" + jsrq + "' or jsrq between '" + ksrq + "' and '" + jsrq + "')");
			while (rs0.next()) {
				String lckssj = Util.null2String(rs0.getString("kssj"));
				String lcjssj = Util.null2String(rs0.getString("jssj"));
				String lcksrq = Util.null2String(rs0.getString("ksrq"));
				String lcjsrq = Util.null2String(rs0.getString("jsrq"));
				if ((lcjsrq + " " + lcjssj).compareTo(ksrq + " " + kssj) <= 0 || ((lcksrq + " " + lckssj).compareTo(jsrq + " " + jssj) >= 0)) {
					continue;
				}
				flag = false;
				KqActionQj_Tj tmp1015_1014 = this;
				tmp1015_1014.errors = tmp1015_1014.errors + "填写的时间段已经存在请假流程(ID=" + Util.null2String(rs0.getString("lcid")) + ")";
				log.info("lcid=" + lcid + ";lc:" + lcksrq + " " + lckssj + " 至 " + lcjsrq + " " + lcjssj);
				return;
			}

			String[] qjsc = getQjsc(gzmc, ksrq, jsrq, kssj, jssj, xjlx);
			log.info("CheckQj lcid=" + lcid + ";qjsc=" + Arrays.toString(qjsc));
			JSONObject json;
			if (xjmc.equals("年休假")) {
				json = getNjxx(ryid + "");
				double qjts = Util.getDoubleValue(qjsc[0], 0.0D);
				double njye = Util.getDoubleValue(json.optString("njye"), 0.0D);
				if (Tools.round(qjts, 1) > Tools.round(njye, 1)) {
					flag = false;
					errors += "年假余额不足";
				}
				log.info("CheckQj lcid=" + lcid + ";年休假=>qjts=" + qjts + ";njye=" + njye + ";getNjxx=" + json.toString());
			} else if (xjmc.equals("婚假")) {
				if ("菲动|云客".indexOf(gzmc) > -1) {
					if (("北京|上海".indexOf(ddmc) > -1) && (Util.getDoubleValue(qjsc[0], 0.0D) > 10.0D)) {
						flag = false;
						KqActionQj_Tj tmp1409_1408 = this;
						tmp1409_1408.errors = tmp1409_1408.errors + gzmc + "(" + ddmc + ") 婚假 最多申请10天";
					} else if (("广州|九江".indexOf(ddmc) > -1) && (Util.getDoubleValue(qjsc[0], 0.0D) > 3.0D)) {
						flag = false;
						KqActionQj_Tj tmp1488_1487 = this;
						tmp1488_1487.errors = tmp1488_1487.errors + gzmc + "(" + ddmc + ") 婚假 最多申请3天";
					}
				} else if ("维动1|维动2".indexOf(gzmc) > -1) {
					if (Util.getDoubleValue(qjsc[0], 0.0D) > 3.0D) {
						flag = false;
						KqActionQj_Tj tmp1567_1566 = this;
						tmp1567_1566.errors = tmp1567_1566.errors + gzmc + " 婚假 最多申请3天";
					}
				} else if ((gzmc.equals("简理财")) && (Util.getDoubleValue(qjsc[0], 0.0D) > 10.0D)) {
					flag = false;
					KqActionQj_Tj tmp1633_1632 = this;
					tmp1633_1632.errors = tmp1633_1632.errors + gzmc + " 婚假 最多申请10天";
				}

				log.info("CheckQj lcid=" + lcid + ";婚假=>gzmc=" + gzmc + ";ddmc=" + ddmc + ";qjsc[0]=" + qjsc[0]);
			} else if (xjmc.equals("丧假")) {
				if (Util.getDoubleValue(qjsc[0], 0.0D) > 3.0D) {
					flag = false;
					KqActionQj_Tj tmp1756_1755 = this;
					tmp1756_1755.errors = tmp1756_1755.errors + gzmc + " 丧假 最多申请3天";
				}
			} else if (xjmc.equals("哺乳假")) {
				if (Util.getDoubleValue(qjsc[1], 0.0D) > 1.0D) {
					flag = false;
					KqActionQj_Tj tmp1821_1820 = this;
					tmp1821_1820.errors = tmp1821_1820.errors + gzmc + " 哺乳假 最多申请1小时";
				}
			} else if (xjmc.equals("公假")) {
				if (Util.getDoubleValue(qjsc[0], 0.0D) > 1.0D) {
					flag = false;
					KqActionQj_Tj tmp1886_1885 = this;
					tmp1886_1885.errors = tmp1886_1885.errors + gzmc + " 公假 最多申请1天";
				}
			} else {
				double qjxs;
				double kysc;
				if (xjmc.equals("调休")) {
					json = getTxxx(ryid + "");
					qjxs = Util.getDoubleValue(qjsc[1], 0.0D);
					kysc = Util.getDoubleValue(json.optString("txkysc"), 0.0D);
					if (Tools.round(qjxs, 0) > Tools.round(kysc, 0)) {
						flag = false;
						errors += "调休可用时长不足";
					}
					log.info("CheckQj lcid=" + lcid + ";调休=>qjxs=" + qjxs + ";kysc=" + kysc + ";getTxxx=" + json.toString());
				} else if (xjmc.equals("带薪病假")) {
					json = getDxbjxx(ryid + "", "");
					qjxs = Util.getDoubleValue(qjsc[1], 0.0D);
					kysc = Util.getDoubleValue(json.optString("bnkyts"), 0.0D);
					if (Tools.round(qjxs, 0) > Tools.round(kysc, 0)) {
						flag = false;
						errors += "带薪病假可用时长不足";
					}
					log.info("CheckQj lcid=" + lcid + ";带薪病假=>qjxs=" + qjxs + ";kysc=" + kysc + ";getDxbjxx=" + json.toString());
				}
			}
		}
	}
}
