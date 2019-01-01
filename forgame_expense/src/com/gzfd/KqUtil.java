package com.gzfd;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.common.Tools;

public class KqUtil extends BaseBean {
	public static String logname = "A2";
	public static final Logger log = Logger.getLogger(logname);
	public final String KqSource = "kaoqin";
	public final String initRq = "2018-10-01";
	public static String curDate = TimeUtil.getCurrentDateString();
	public static String curTime = TimeUtil.getOnlyCurrentTimeString();
	public static final int drnjid = 24;
	public static final int njid = 23;
	public static Map<Integer, JSONObject> gHrmMap = new HashMap();
	public static Map<Integer, String> gKqgz = new HashMap();
	public static Map<Integer, String> gKqcs = new HashMap();
	public static Map<String, Integer> gPin = new HashMap();
	public static Map<String, Integer> gGh = new HashMap();
	public static Map<String, Map<String, Integer>> gPubHoliDay = new HashMap();
	public static Map<String, String> gXjlx = new HashMap();
	public static Map<String, JSONObject> gKqgzMap = new HashMap();
	public String[] Rpt01Name = "员工编号|员工姓名|所属公司|一级部门|二级部门|职位|日期|班值|上班|下班|时长|卡钟记录|异常考勤提示|休假情况|签卡情况|加班计划|备注".split("\\|", -1);

	static {
		gKqgzMap.clear();
		JSONObject json = new JSONObject();
		json.put("sbsj", "09:00");
		json.put("xbsj", "18:00");
		json.put("zwsj", "12:00-14:00");
		json.put("xwsj", "18:00-19:30");
		json.put("cdsj", "09:30");
		gKqgzMap.put("菲动", json);
		gKqgzMap.put("维动1", json);
		gKqgzMap.put("维动2", json);
		json = new JSONObject();
		json.put("sbsj", "09:00");
		json.put("xbsj", "18:00");
		json.put("zwsj", "12:00-14:00");
		json.put("xwsj", "18:00-19:30");
		json.put("cdsj", "09:15");
		gKqgzMap.put("云客", json);
		json = new JSONObject();
		json.put("sbsj", "09:30");
		json.put("xbsj", "18:30");
		json.put("zwsj", "13:00-14:00");
		json.put("xwsj", "18:00-19:30");
		json.put("cdsj", "09:30");
		gKqgzMap.put("香港", json);
		json = new JSONObject();
		json.put("sbsj", "10:00");
		json.put("xbsj", "18:00");
		json.put("zwsj", "12:30-13:30");
		json.put("xwsj", "18:00-19:30");
		json.put("cdsj", "10:00");
		gKqgzMap.put("台湾", json);

		if (gPubHoliDay.isEmpty()) {
			initPubholiDay("");
		}
		if (gHrmMap.isEmpty()) {
			initMap("");
		}
	}

	public static void initPubholiDay(String year) {
		gPubHoliDay.clear();
		year = (year.equals("")) ? curDate.substring(0, 4) : year;
		int beforyear = Util.getIntValue(year) - 1;
		int nextyear = Util.getIntValue(year) + 1;

		Map pubHoliDay1 = new HashMap();
		Map pubHoliDay3 = new HashMap();
		Map pubHoliDay4 = new HashMap();
		RecordSet rs = new RecordSet();
		String sql = "select holidaydate,changetype,countryid from hrmpubholiday where countryid in(1,3,4) and holidaydate between '" + beforyear + "-01-01' and '" + nextyear + "-12-31'";
		rs.execute(sql);
		while (rs.next()) {
			String holidaydate = Util.null2String(rs.getString("holidaydate"));
			int changetype = Util.getIntValue(rs.getString("changetype"));
			int countryid = Util.getIntValue(rs.getString("countryid"));
			if (countryid == 1) {
				pubHoliDay1.put(holidaydate, Integer.valueOf(changetype));
			} else if (countryid == 3) {
				pubHoliDay3.put(holidaydate, Integer.valueOf(changetype));
			} else if (countryid == 4) {
				pubHoliDay4.put(holidaydate, Integer.valueOf(changetype));
			}
		}
		gPubHoliDay.put("内地", pubHoliDay1);
		gPubHoliDay.put("香港", pubHoliDay3);
		gPubHoliDay.put("台湾", pubHoliDay4);
	}

	public static void initMap(String hrmid) {
		log.info("initMap  begin....." + hrmid);
		gHrmMap.clear();
		if (hrmid.equals("")) {
			gKqgz.clear();
			gPin.clear();
			gKqcs.clear();
			gXjlx.clear();
		}

		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		if (gKqgz.isEmpty()) {
			rs.execute("select selectvalue,selectname from cus_selectitem where fieldid=(select id from cus_formdict where fieldname='field0') order by selectvalue");
			while (rs.next()) {
				int selectvalue = Util.getIntValue(rs.getString("selectvalue"));
				String selectname = Util.null2String(rs.getString("selectname"));
				gKqgz.put(Integer.valueOf(selectvalue), selectname);
			}
		}
		if (gKqcs.isEmpty()) {
			rs.execute("select id,locationname from HrmLocations");
			while (rs.next()) {
				int csid = Util.getIntValue(rs.getString("id"));
				String csmc = Util.null2String(rs.getString("locationname"));
				gKqcs.put(Integer.valueOf(csid), csmc);
			}
		}
		if (gXjlx.isEmpty()) {
			rs.execute("select field004,field001 from hrmLeaveTypeColor");
			while (rs.next()) {
				String field004 = Util.null2String(rs.getString("field004"));
				String field001 = Util.null2String(rs.getString("field001"));
				gXjlx.put(field004, field001);
			}
		}
		String sql = "select * from hrmresource where status in(0,1,2,3) ";
		if (hrmid.equals("")) {
			sql = sql + " order by id";
		} else {
			sql = sql + " and id=" + Util.getIntValue(hrmid, 0);
		}
		rs.execute(sql);
		while (rs.next()) {
			JSONObject json = new JSONObject();
			int hrmId = Util.getIntValue(rs.getString("id"), 0);
			int departmentid = Util.getIntValue(rs.getString("departmentid"), 0);
			json.put("bmId", Integer.valueOf(departmentid));
			int supdepid = 0;
			rs0.execute("select departmentname,supdepid from HrmDepartment where id=" + departmentid);
			if (rs0.next()) {
				supdepid = Util.getIntValue(rs0.getString("supdepid"), 0);
				json.put("bmMc", Util.null2String(rs0.getString("departmentname")));
			}
			json.put("sjbmId", Integer.valueOf(supdepid));
			if (supdepid > 0) {
				rs0.execute("select departmentname from HrmDepartment where id=" + supdepid);
				if (rs0.next()) {
					json.put("sjbmMc", Util.null2String(rs0.getString("departmentname")));
				}
			}
			int subcompanyid1 = Util.getIntValue(rs.getString("subcompanyid1"), 0);
			json.put("fbId", Integer.valueOf(subcompanyid1));
			rs0.execute("select subcompanyname from HrmSubCompany where id=" + subcompanyid1);
			if (rs0.next()) {
				json.put("fbMc", Util.null2String(rs0.getString("subcompanyname")));
			}
			int jobtitle = Util.getIntValue(rs.getString("jobtitle"), 0);
			json.put("zwId", Integer.valueOf(jobtitle));
			rs0.execute("select jobtitlename from HrmJobTitles where id=" + jobtitle);
			if (rs0.next()) {
				json.put("zwMc", Util.null2String(rs0.getString("jobtitlename")));
			}
			int locationid = Util.getIntValue(rs.getString("locationid"), 0);
			json.put("gzdId", Integer.valueOf(locationid));
			json.put("gzd", (gKqcs.containsKey(Integer.valueOf(locationid))) ? (String) gKqcs.get(Integer.valueOf(locationid)) : "");
			json.put("xm", Util.null2String(rs.getString("lastname")));
			String gh = Util.null2String(rs.getString("workcode"));
			json.put("gh", gh);
			rs0.execute("select field0,field1,field2 from cus_fielddata where id=" + hrmId + " and scope='HrmCustomFieldByInfoType' and scopeid=-1");
			if (rs0.next()) {
				int gzid = Util.getIntValue(rs0.getString("field0"), -1);
				json.put("kqgz", gzid);
				json.put("kqgzMc", (gKqgz.containsKey(Integer.valueOf(gzid))) ? (String) gKqgz.get(Integer.valueOf(gzid)) : "");
				String kqkh = Util.null2String(rs0.getString("field1"));
				json.put("kqkh", kqkh);
				if (!(kqkh.equals(""))) {
					gPin.put(kqkh, Integer.valueOf(hrmId));
				}
				json.put("rzrq", Util.null2String(rs0.getString("field2")));
			}
			if (!(gh.equals(""))) {
				gGh.put(gh, Integer.valueOf(hrmId));
			}
			gHrmMap.put(Integer.valueOf(hrmId), json);
		}
		log.info("initMap  end....." + hrmid);
	}

	public void SyncDaKa(String pin) {
		if (gHrmMap.isEmpty()) {
			initMap("");
		}
		log.info("SyncDaKa  begin.....");
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		rs.execute("select * from uf_dkjl where ryid = 0 and datediff(DAY,dkrq,getdate())<35");
		while (rs.next()) {
			String kqkh = Util.null2String(rs.getString("kqkh"));
			int ryid = (gPin.containsKey(kqkh)) ? gPin.get(kqkh).intValue() : 0;
			if (ryid > 0) {
				JSONObject json = new JSONObject();
				String kqgz = Util.null2String(json.optString("kqgzMc"));
				int id = Util.getIntValue(rs.getString("id"));
				rs0.execute("update uf_dkjl set ryid=" + ryid + ",kqgz='" + kqgz + "' where id=" + id);
			}
		}
		int maxDkId = 0;
		if (pin.equals("")) {
			rs.execute("select max(dkid) dkid from uf_dkjl");
			if (rs.next()) {
				maxDkId = Util.getIntValue(rs.getString("dkid"), 0);
			}
		} else {
			rs.execute("delete from uf_dkjl where kqkh='" + pin + "'");
		}
		String dk_sql = "select id,pin,convert(char(19),checktime,120) checktime from checkinout";
		if ((maxDkId == 0) || (!(pin.equals("")))) {
			dk_sql = dk_sql + " where convert(char(10),checktime,120) >= '2018-10-01'";
			if (!(pin.equals(""))) {
				dk_sql = dk_sql + " and pin='" + pin + "'";
			}
		} else {
			dk_sql = dk_sql + " where id > " + maxDkId;
		}
		log.info("SyncDaKa dk_sql=" + dk_sql);
		RecordSetDataSource dkdb = new RecordSetDataSource("kaoqin");
		dkdb.executeSql(dk_sql);
		String errors = "";
		while (dkdb.next()) {
			String kqkh = Util.null2String(dkdb.getString("pin"));
			int dkid = Util.getIntValue(dkdb.getString("id"));
			String rqsj = Util.null2String(dkdb.getString("checktime"));
			String dkrq = rqsj.substring(0, 10);
			String dksj = rqsj.substring(11, 16);
			String kqgz = "";
			int ryid = (gPin.containsKey(kqkh)) ? gPin.get(kqkh).intValue() : 0;
			if (ryid > 0) {
				JSONObject json = new JSONObject();
				kqgz = Util.null2String(json.optString("kqgzMc"));
			} else if ((errors.equals("")) || (errors.indexOf(kqkh) == -1)) {
				errors = errors + kqkh + ",";
			}

			String in_sql = "insert into uf_dkjl(dkid,ryid,kqgz,kqkh,dkrq,dksj,rqsj,modedatacreatedate,modedatacreatetime) ";
			in_sql = in_sql + "values(" + dkid + "," + ryid + ",'" + kqgz + "','" + kqkh + "','" + dkrq + "','" + dksj + "','" + rqsj + "','" + curDate + "','" + curTime + "')";
			rs0.execute(in_sql);
		}
		if (!(errors.equals(""))) {
			log.error("打卡同步 pin 未找到对应的人员：" + errors);
		}
		log.info("SyncDaKa  end.....");
	}

	public String getTabName(int lx, int lcid) {
		String value = "";
		String sql = "";
		if (lx == 0) {
			sql = "select tablename from workflow_bill where id=(select formid from workflow_base where id=" + lcid + ")";
		} else if (lx == 1) {
			sql = "select tablename from workflow_bill where id in(select formid from workflow_base ";
			sql = sql + "where id in(select workflowid from workflow_requestbase where requestid=" + lcid + "))";
		}
		if (!(sql.equals(""))) {
			RecordSet rs = new RecordSet();
			rs.execute(sql);
			if (rs.next()) {
				value = rs.getString("tablename");
			}
		}
		return value;
	}

	public void setNolcid() {
		RecordSet rs = new RecordSet();
		ArrayList<Integer> idList = new ArrayList<Integer>();

		rs.execute("select distinct ryid from uf_lcjl where not exists(select 1 from workflow_requestbase where requestid=uf_lcjl.lcid)");
		while (rs.next()) {
			int ryid = Util.getIntValue(rs.getString("ryid"), 0);
			idList.add(Integer.valueOf(ryid));
		}
		rs.execute("delete from uf_lcjl where not exists(select 1 from workflow_requestbase where requestid=uf_lcjl.lcid)");
		for (Integer ryid : idList) {
			CalNj(ryid.intValue());
		}

		idList.clear();
		rs.execute("select distinct mainid from uf_txxx_dt1 where not exists(select 1 from workflow_requestbase where requestid=uf_txxx_dt1.lcid)");
		while (rs.next()) {
			int dataid = Util.getIntValue(rs.getString("mainid"), 0);
			idList.add(Integer.valueOf(dataid));
		}
		rs.execute("delete from uf_txxx_dt1 where not exists(select 1 from workflow_requestbase where requestid=uf_txxx_dt1.lcid)");
		for (Integer dataid : idList) {
			CalSjTx(dataid.intValue());
		}
	}

	public static void Caljb() {
		RecordSet rs = new RecordSet();
		String beforDay = TimeUtil.dateAdd(curDate, -35);
		rs.execute("select * from uf_lcjl where lx='加班' and lb='待重算时长' and ksrq between '" + beforDay + "' and '" + curDate + "'");
		while (rs.next()) {
			int lcid = Util.getIntValue(rs.getString("lcid"), 0);
			new KqActionJb_gd().execJb(lcid);
		}
	}

	public void setXjTh(int lcid) {
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		int mainid = 0;
		rs.execute("select * from uf_lcjl where lcid=" + lcid);
		if (rs.next()) {
			int ryid = Util.getIntValue(rs.getString("ryid"), 0);
			rs0.execute("delete from uf_lcjl where lcid=" + lcid);
			CalNj(ryid);
		}
		rs.execute("select * from uf_txxx_dt1 where lcid=" + lcid);
		if (rs.next()) {
			mainid = Util.getIntValue(rs.getString("mainid"), 0);
		}
		if (mainid != 0) {
			rs.execute("delete from uf_txxx_dt1 where lcid=" + lcid);
			CalSjTx(mainid);
		}
	}

	public static void setNjJzGq(boolean isJz) {
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		int bn = Util.getIntValue(curDate.substring(0, 4));
		String jzrq = String.valueOf(bn) + "-01-01";
		if ((jzrq.equals(curDate)) || (isJz)) {
			log.info("setNjJzGq 上年年假结转 begin........ jzrq=" + jzrq);
			rs.execute("select * from uf_njxx where formmodeid=23 and bnye>0");
			while (rs.next()) {
				int mainid = Util.getIntValue(rs.getString("id"), 0);
				double bnye = getBts(Util.getDoubleValue(rs.getString("bnye"), 0.0D));
				String jzsxrq = "";
				log.info("setNjJzGq id=" + mainid + ";bnye=" + bnye);
				rs0.execute("select * from uf_njxx_dt1 where mainid=" + mainid + " and nd=" + (bn - 1) + " and lx=0");
				if (rs0.next()) {
					double njts = getBts(Util.getDoubleValue(rs0.getString("njts"), 0.0D));
					if (njts < bnye) {
						bnye = njts;
					}
					jzsxrq = Util.null2String(rs0.getString("rq2"));
					log.info("setNjJzGq id=" + mainid + ";bnye=" + bnye + ";njts=" + njts + ";jzsxrq=" + jzsxrq);
				}
				if ((!(jzsxrq.equals(""))) && (jzsxrq.compareTo(jzrq) > 0)) {
					rs0.execute("update uf_njxx set jzts=" + getBts(bnye) + ",jzsxrq='" + jzsxrq + "' where id=" + mainid);
				}
			}
			log.info("setNjJzGq 上年年假结转 end........jzrq=" + jzrq);
		}
		log.info("setNjJzGq 上年 失效日期检查begin..........");
		ArrayList<Integer> njid = new ArrayList<Integer>();
		String sql = "select * from uf_njxx_dt1 where zt=0 and rq2<convert(char(12),getdate(),120) and mainid>0 and nd=" + (bn - 1);
		rs.execute(sql);
		while (rs.next()) {
			int id = Util.getIntValue(rs.getString("id"), 0);
			int mainid = Util.getIntValue(rs.getString("mainid"), 0);
			rs0.execute("update uf_njxx_dt1 set zt=1 where id=" + id);
			if (!(njid.contains(Integer.valueOf(mainid)))) {
				njid.add(Integer.valueOf(mainid));
			}
		}
		for (int billid : njid) {
			new modeExpand().CalNj(billid);
		}
		log.info("setNjJzGq 上年 失效日期检查end.........." + njid.size());
	}

	public Integer CalNj(int ryid) {
		int billid = 0;
		RecordSet rs = new RecordSet();
		rs.execute("select * from uf_njxx where ryid=" + ryid + " and formmodeid=" + 23);
		if (rs.next()) {
			billid = Util.getIntValue(rs.getString("id"));
			new modeExpand().CalNj(billid);
		}
		return Integer.valueOf(billid);
	}

	public JSONObject getNjxx(String ryid) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		int billid = CalNj(Util.getIntValue(ryid, 0)).intValue();
		if (billid != 0) {
			rs.execute("select * from uf_njxx where id=" + billid);
			if (rs.next()) {
				double bnts = Util.getDoubleValue(rs.getString("bnts"), 0.0D);
				double bnyxts = Util.getDoubleValue(rs.getString("bnyxts"), 0.0D);
				double bnye = Util.getDoubleValue(rs.getString("bnye"), 0.0D);
				double snts = Util.getDoubleValue(rs.getString("snts"), 0.0D);
				double snyxts = Util.getDoubleValue(rs.getString("snyxts"), 0.0D);
				double snye = Util.getDoubleValue(rs.getString("snye"), 0.0D);
				double njye = Tools.round(snye + bnye, 1);
				double yxts = Tools.round(bnyxts + snyxts, 1);
				double jzts = Util.getDoubleValue(rs.getString("jzts"), 0.0D);
				String jzsxrq = Util.null2String(rs.getString("jzsxrq"));
				if ((!(jzsxrq.equals(""))) && (jzsxrq.compareTo(curDate) < 0)) {
					jzts = 0.0D;
				}
				jzts += snts;
				double bnxz = Util.getDoubleValue(rs.getString("bnxz"), 0.0D);
				json.put("billid", Integer.valueOf(billid));
				json.put("bnts", Double.valueOf(getBts(Tools.round(bnts, 1))));
				json.put("yxts", Double.valueOf(getBts(Tools.round(yxts, 1))));
				json.put("snye", Double.valueOf(getBts(Tools.round(jzts, 1))));
				json.put("bnxz", Double.valueOf(getBts(Tools.round(bnxz, 1))));
				json.put("njye", Double.valueOf(getBts(Tools.round(njye, 1))));
			}
		}
		return json;
	}

	public JSONObject getTxxx(String ryid) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		int dataid = 0;
		rs.execute("select * from uf_txxx where ryid=" + Util.getIntValue(ryid, 0));
		if (rs.next()) {
			dataid = Util.getIntValue(rs.getString("id"), 0);
		}
		if (dataid != 0) {
			json = CalSjTx(dataid);
		}
		return json;
	}

	public void setLcjl(JSONObject json) {
		log.info("写入流程数据setLcjl json=" + json);
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		int lcid = Util.getIntValue(json.optString("lcid"), 0);
		int ryid = Util.getIntValue(json.optString("ryid"), 0);
		if ((lcid != 0) && (ryid != 0)) {
			int dataid = 0;
			rs.execute("select * from uf_lcjl where lcid=" + lcid + " and ryid=" + ryid);
			if (rs.next()) {
				dataid = Util.getIntValue(rs.getString("id"), 0);
			}
			if (dataid == 0) {
				rs0.execute("insert into uf_lcjl(lcid,ryid,lx,lb,ksrq,kssj,jsrq,jssj,sc,ts,lcgz,lccs,zt) values(" + lcid + "," + ryid + ",'" + Util.null2String(json.optString("lx")) + "','" + Util.null2String(json.optString("lb")) + "','" + Util.null2String(json.optString("ksrq")) + "','" + Util.null2String(json.optString("kssj")) + "','" + Util.null2String(json.optString("jsrq")) + "','" + Util.null2String(json.optString("jssj")) + "'," + Util.getDoubleValue(json.optString("sc"), 0.0D) + "," + Util.getDoubleValue(json.optString("ts"), 0.0D) + ",'" + Util.null2String(json.optString("lcgz")) + "','" + Util.null2String(json.optString("lccs")) + "','" + Util.null2String(json.optString("zt")) + "')");
			} else {
				rs0.execute("update uf_lcjl set lx='" + Util.null2String(json.optString("lx")) + "',lb='" + Util.null2String(json.optString("lb")) + "',ksrq='" + Util.null2String(json.optString("ksrq")) + "',kssj='" + Util.null2String(json.optString("kssj")) + "',jsrq='" + Util.null2String(json.optString("jsrq")) + "',jssj='" + Util.null2String(json.optString("jssj")) + "',sc=" + Util.getDoubleValue(json.optString("sc"), 0.0D) + ",ts='" + Util.getDoubleValue(json.optString("ts"), 0.0D) + "',lcgz='" + Util.null2String(json.optString("lcgz")) + "',lccs='" + Util.null2String(json.optString("lccs")) + "',zt='" + Util.null2String(json.optString("zt")) + "' where id=" + dataid);
			}
		}
	}

	public void setTxxx(JSONObject json) {
		log.info("写入调休信息setTxxx json=" + json);
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		int dataid = 0;
		int ryid = Util.getIntValue(json.optString("ryid"), 0);
		if (ryid != 0) {
			rs.execute("select * from uf_txxx where ryid=" + ryid);
			if (rs.next()) {
				dataid = Util.getIntValue(rs.getString("id"), 0);
			} else {
				rs0.execute("insert into uf_txxx(ryid) values(" + ryid + ")");
			}
			if (dataid == 0) {
				rs.execute("select * from uf_txxx where ryid=" + ryid);
				if (rs.next()) {
					dataid = Util.getIntValue(rs.getString("id"), 0);
				}
			}
			int sjlx = Util.getIntValue(json.optString("sjlx"), -1);
			String jbrq = Util.null2String(json.optString("jbrq"));
			String lcgz = Util.null2String(json.optString("lcgz"));
			String gqrq = "";
			if ((sjlx == 1) && (!(jbrq.equals("")))) {
				String nextMonthDay = monthAdd(jbrq, 1).substring(0, 8) + "01";
				gqrq = monthAdd(nextMonthDay, 3);
				if (lcgz.equals("简理财")) {
					gqrq = yearAdd(nextMonthDay, 1);
				}
			}
			int lcid = Util.getIntValue(json.optString("lcid"), 0);
			rs.execute("select * from uf_txxx_dt1 where lcid=" + lcid);
			if (rs.next()) {
				int mxid = Util.getIntValue(rs.getString("id"), 0);
				rs0.execute("update uf_txxx_dt1 set jbrq='" + jbrq + "',gqrq='" + gqrq + "',jbsc=" + Util.getDoubleValue(json.optString("jbsc"), 0.0D) + ",txsc=" + Util.getDoubleValue(json.optString("txsc"), 0.0D) + ",lcgz='" + lcgz + "',sjlx=" + sjlx + " where id=" + mxid);
			} else {
				rs0.execute("insert into uf_txxx_dt1(mainid,lcid,jbrq,gqrq,jbsc,txsc,lcgz,sjlx) values(" + dataid + "," + lcid + ",'" + jbrq + "','" + gqrq + "'," + Util.getDoubleValue(json.optString("jbsc"), 0.0D) + "," + Util.getDoubleValue(json.optString("txsc"), 0.0D) + ",'" + lcgz + "'," + sjlx + ")");
			}
			CalSjTx(dataid);
		}
	}

	public JSONObject getDxbjxx(String ryid, String rq) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		JSONObject gJson = new JSONObject();
		if ("简理财".equals(gJson.optString("kqgzMc"))) {
			rq = (rq.equals("")) ? curDate : rq;
			int bn = Util.getIntValue(rq.substring(0, 4));
			double bnsyts = 0.0D;
			double bnfpts = 0.0D;
			double bnkyts = 0.0D;
			rs.execute("select dxbjts from uf_jlcdxbjdrd where xingming=" + Util.getIntValue(ryid, 0) + " and niandu=" + bn);
			if (rs.next()) {
				bnfpts = Util.getDoubleValue(rs.getString("dxbjts"), 0.0D);
			}
			rs.execute("select sum(ts) ts from uf_lcjl where ryid=" + Util.getIntValue(ryid, 0) + " and lb='带薪病假' and ksrq like '" + bn + "%'");
			if (rs.next()) {
				bnsyts = Util.getDoubleValue(rs.getString("ts"), 0.0D);
				bnkyts = bnfpts - bnsyts;
				bnkyts = (bnkyts < 0.0D) ? 0.0D : bnkyts;
			}
			json.put("bnsyts", Double.valueOf(getBts(Tools.round(bnsyts, 1))));
			json.put("bnfpts", Double.valueOf(getBts(Tools.round(bnfpts, 1))));
			json.put("bnkyts", Double.valueOf(getBts(Tools.round(bnkyts, 1))));
		}
		return json;
	}

	public JSONObject CalSjTx(int dataid) {
		JSONObject json = new JSONObject();
		if (dataid != 0) {
			RecordSet rs = new RecordSet();
			RecordSet rs0 = new RecordSet();
			RecordSet rs1 = new RecordSet();
			String clgqrq = yearAdd(curDate, -1);
			rs.execute("update uf_txxx_dt1 set txsc=0,sysc=jbsc where sjlx=1 and gqrq>'" + clgqrq + "'");

			rs.execute("select * from uf_txxx_dt1 where mainid=" + dataid + " and sjlx=2 and jbrq>'" + clgqrq + "' order by jbrq");
			while (rs.next()) {
				String jbrq = Util.null2String(rs.getString("jbrq"));
				double txsc = Util.getDoubleValue(rs.getString("txsc"), 0.0D);
				String sql1 = "select * from uf_txxx_dt1 where mainid=" + dataid + " and sjlx=1 and gqrq>'" + clgqrq + "' and gqrq>='" + jbrq + "' and sysc>0 order by gqrq";
				rs0.execute(sql1);
				while (rs0.next()) {
					int mxid = Util.getIntValue(rs0.getString("id"));
					double yxsc = 0.0D;
					double jbsc = Util.getDoubleValue(rs0.getString("jbsc"), 0.0D);
					double sysc = Util.getDoubleValue(rs0.getString("sysc"), 0.0D);
					if (txsc >= sysc) {
						yxsc = sysc;
						txsc -= sysc;
					} else {
						yxsc = txsc;
						txsc = 0.0D;
					}
					sysc = jbsc - yxsc;
					rs1.execute("update uf_txxx_dt1 set txsc=" + yxsc + ",sysc=" + sysc + " where id=" + mxid);
					if (txsc == 0.0D) {
						break;
					}
				}
			}
			double txkysc = 0.0D;
			double bnkysc = 0.0D;
			double bntxsc = 0.0D;
			rs.execute("select * from uf_txxx_dt1 where mainid=" + dataid + " and sjlx=1 and gqrq>'" + curDate + "'");
			while (rs.next()) {
				double sysc = Util.getDoubleValue(rs.getString("sysc"), 0.0D);
				txkysc += sysc;
			}
			int bn = Util.getIntValue(curDate.substring(0, 4));
			rs.execute("select sum(txsc) txsc from uf_txxx_dt1 where mainid=" + dataid + " and sjlx=2 and jbrq like '" + bn + "%'");
			if (rs.next()) {
				bntxsc = Util.getDoubleValue(rs.getString("txsc"), 0.0D);
			}
			rs.execute("select sum(jbsc) jbsc from uf_txxx_dt1 where mainid=" + dataid + " and sjlx=1 and gqrq>='" + bn + "-01-01'");
			if (rs.next()) {
				bnkysc = Util.getDoubleValue(rs.getString("jbsc"), 0.0D);
			}
			rs1.execute("update uf_txxx set txkysc=" + Tools.round(txkysc, 0) + ",bnkysc=" + Tools.round(bnkysc, 0) + ",bntxsc=" + Tools.round(bntxsc, 0) + " where id=" + dataid);

			json.put("bntxsc", Double.valueOf(Tools.round(bntxsc, 0)));
			json.put("bnkysc", Double.valueOf(Tools.round(bnkysc, 0)));
			json.put("txkysc", Double.valueOf(Tools.round(txkysc, 0)));
		}
		return json;
	}

	public void CalRyTx(int ryid) {
		RecordSet rs = new RecordSet();
		int dataid = 0;
		rs.execute("select * from uf_txxx where ryid=" + ryid);
		if (rs.next()) {
			dataid = Util.getIntValue(rs.getString("id"), 0);
		}
		CalSjTx(dataid);
	}

	public Map<String, ArrayList<String>> getRyLcjl(int ryid, String ksrq, String jsrq) {
		RecordSet rs = new RecordSet();

		Map rq = new HashMap();
		String sql = "select * from uf_lcjl where ryid=" + ryid + " and lx in('签卡','出差','请假','加班') and (ksrq between '" + ksrq + "' and '" + jsrq + "' or jsrq between '" + ksrq + "' and '" + jsrq + "') and zt='归档'";
		log.info("getRyLcjl sql=" + sql);
		rs.execute(sql);
		while (rs.next()) {
			String lcqk = "";
			String lx = Util.null2String(rs.getString("lx"));
			String lb = Util.null2String(rs.getString("lb"));
			double ts = Util.getDoubleValue(rs.getString("ts"), 0.0D);
			double sc = Util.getDoubleValue(rs.getString("sc"), 0.0D);
			String kssj = Util.null2String(rs.getString("kssj"));
			String jssj = Util.null2String(rs.getString("jssj"));
			String lcksrq = Util.null2String(rs.getString("ksrq"));
			String lcjsrq = Util.null2String(rs.getString("jsrq"));
			ArrayList sj;
			if ("签卡|加班".indexOf(lx) > -1) {
				if (lx.equals("签卡")) {
					lcqk = "签卡" + kssj + " " + lb;
				} else if (lx.equals("加班")) {
					lcqk = "加班" + sc + "小时" + kssj + "-" + jssj;
				}
				if (rq.containsKey(lcksrq)) {
					sj = (ArrayList) rq.get(lcksrq);
				} else {
					sj = new ArrayList();
				}
				if (!(sj.contains(lcqk))) {
					sj.add(lcqk);
					rq.put(lcksrq, sj);
				}
			} else if ("请假|出差".indexOf(lx) > -1) {
				if (lx.equals("请假")) {
					if (ts > 0.0D) {
						lcqk = "请假" + lb + ts + "天";
					} else {
						lcqk = "请假" + lb + sc + "小时";
					}
				} else if (lx.equals("出差")) {
					lcqk = "出差" + ts + "天";
				}
				String calrq = lcksrq;
				do {
					if (rq.containsKey(calrq)) {
						sj = (ArrayList) rq.get(calrq);
					} else {
						sj = new ArrayList();
					}
					if (!(sj.contains(lcqk))) {
						sj.add(lcqk);
						rq.put(calrq, sj);
					}
					calrq = TimeUtil.dateAdd(calrq, 1);
				}
				while (lcjsrq.compareTo(calrq) >= 0);
			}
		}
		return rq;
	}

	public Map<String, ArrayList<String>> getRyDk(int ryid, String ksrq, String jsrq) {
		RecordSet rs = new RecordSet();

		Map rq = new HashMap();
		rs.execute("select dkrq,dksj from uf_dkjl where ryid=" + ryid + " and dkrq between '" + ksrq + "' and '" + jsrq + "'");
		String dkrq;
		String dksj;
		ArrayList sj;
		while (rs.next()) {
			dkrq = Util.null2String(rs.getString("dkrq"));
			dksj = Util.null2String(rs.getString("dksj"));
			if ((dkrq.length() == 10) && (dksj.length() == 5)) {
				if (rq.containsKey(dkrq)) {
					sj = (ArrayList) rq.get(dkrq);
				} else {
					sj = new ArrayList();
				}
				if (!(sj.contains(dksj))) {
					sj.add(dksj);
					rq.put(dkrq, sj);
				}
			}
		}

		rs.execute("select ksrq,kssj from uf_lcjl where ryid=" + ryid + " and lx='批量签卡' and ksrq between '" + ksrq + "' and '" + jsrq + "' and zt='归档'");
		while (rs.next()) {
			dkrq = Util.null2String(rs.getString("ksrq"));
			dksj = Util.null2String(rs.getString("kssj"));
			if ((dkrq.length() == 10) && (dksj.length() == 5)) {
				if (rq.containsKey(dkrq)) {
					sj = (ArrayList) rq.get(dkrq);
				} else {
					sj = new ArrayList();
				}
				if (!(sj.contains(dksj))) {
					sj.add(dksj);
					rq.put(dkrq, sj);
				}
			}
		}
		return rq;
	}

	public static boolean isRqlb(String dq, String rq, int lx) {
		if ((rq.equals("")) || (rq.length() != 10)) { return false; }
		if (gPubHoliDay.isEmpty()) {
			initPubholiDay(rq.substring(0, 4));
		}
		Map pubHoliDay = new HashMap();
		int pubday = 0;
		if (pubHoliDay.containsKey(rq)) {
			pubday = ((Integer) pubHoliDay.get(rq)).intValue();
		}
		int wkday = TimeUtil.dateWeekday(rq);
		if (lx == 0) {
			if (pubday == 1) {
				return true;
			} else if (lx == 1) {
				if ((pubday == 2) || ((wkday >= 1) && (wkday <= 5) && (pubday != 3))) {
					return true;
				} else if ((lx == 2) && (((pubday == 3) || ((((wkday == 0) || (wkday == 6))) && (pubday != 2))))) { return true; }
			}
		}

		log.info("isRqlb =>rq=" + rq + ";lx=" + lx + ";return false");
		return false;
	}

	public static int getJbTxsc(String gzmc, String jbrq, String kssj, String jssj, boolean isjs) {
		if ((gzmc.equals("")) || (jbrq.length() != 10) || (kssj.length() != 5) || (jssj.length() != 5)) { return 0; }

		String dq = ("香港|台湾".indexOf(gzmc) > -1) ? gzmc : "内地";
		if ((!(isjs)) && (isRqlb(dq, jbrq, 0))) { return 0; }
		int jbbxsc = 0;
		if (gzmc.equals("简理财")) {
			if (isRqlb(dq, jbrq, 1)) { return 0; }
			if (isRqlb(dq, jbrq, 2)) {
				jbbxsc = (int) (timediffT(kssj, jssj, "12:00-13:00", "") / 60.0D);
			}
			jbbxsc = (jbbxsc > 8) ? 8 : jbbxsc;
		} else if (isRqlb(dq, jbrq, 1)) {
			kssj = (kssj.compareTo("19:30") > 0) ? kssj : "19:30";
			jbbxsc = (int) (Util.timediff1(jssj, kssj) / 60.0D);
		} else if (isRqlb(dq, jbrq, 2)) {
			JSONObject kqJson = new JSONObject();
			jbbxsc = (int) (timediffT(kssj, jssj, kqJson.optString("zwsj"), kqJson.optString("xwsj")) / 60.0D);
		}

		jbbxsc = (jbbxsc < 0) ? 0 : jbbxsc;
		log.info("getJbTxsc =>jbbxsc=" + jbbxsc + ";gzmc=" + gzmc + ";jbrq=" + jbrq + ";kssj=" + kssj + ";jssj=" + jssj + ";dq=" + dq);
		return jbbxsc;
	}

	public static String[] getCcsc(String gzmc, String ksrq, String jsrq, String kssj, String jssj) {
		double ts = 0.0D;
		double xs = 0.0D;
		if ((gzmc.equals("")) || (ksrq.length() != 10) || (jsrq.length() != 10) || (kssj.length() != 5) || (jssj.length() != 5)) { return new String[] { "0", "0" }; }
		if (ksrq.equals(jsrq)) {
			JSONObject json = getGzTsSc(gzmc, kssj, jssj);
			ts = Util.getDoubleValue(json.optString("ts"), 0.0D);
			xs = Util.getDoubleValue(json.optString("xs"), 0.0D);
		} else {
			int days = TimeUtil.dateInterval(ksrq, jsrq) - 1;
			JSONObject json1 = getGzTsSc(gzmc, kssj, "");
			ts = days + Util.getDoubleValue(json1.optString("ts"), 0.0D);
			xs = Util.getDoubleValue(json1.optString("xs"), 0.0D);
			JSONObject json2 = getGzTsSc(gzmc, "", jssj);
			ts += Util.getDoubleValue(json2.optString("ts"), 0.0D);
			xs += Util.getDoubleValue(json2.optString("xs"), 0.0D);
		}
		if (xs > 0.0D) {
			if (Tools.round(xs, 1) > 0.5D) {
				ts += 1.0D;
			}
			xs = 0.0D;
		}
		ts = getBts(ts);
		log.info("getCcsc =>sc=" + ts + "|" + xs + ";gzmc=" + gzmc + ";ksrq=" + ksrq + ";jsrq=" + jsrq + ";kssj=" + kssj + ";jssj=" + jssj);
		return (ts + "|" + xs).split("\\|", -1);
	}

	public static String[] getQjsc(String gzmc, String ksrq, String jsrq, String kssj, String jssj, String xjlx) {
		double ts = 0.0D;
		double xs = 0.0D;
		double bzs = 8.0D;
		int sclx = 0;
		int zmfdj = 0;
		if ((gzmc.equals("")) || (ksrq.length() != 10) || (jsrq.length() != 10) || (kssj.length() != 5) || (jssj.length() != 5)) { return new String[] { "0", "0" }; }
		if (ksrq.compareTo(jsrq) > 0) { return new String[] { "0", "0" }; }
		String dq = ("香港|台湾".indexOf(gzmc) > -1) ? gzmc : "内地";
		String xjmc = (gXjlx.containsKey(xjlx)) ? (String) gXjlx.get(xjlx) : "";
		if (ksrq.equals(jsrq)) {
			if (("婚假".equals(xjmc)) || (isRqlb(dq, ksrq, 1))) {
				JSONObject json = getXjlbSc(gzmc, kssj, jssj, xjmc);
				ts = Util.getDoubleValue(json.optString("ts"), 0.0D);
				xs = Util.getDoubleValue(json.optString("xs"), 0.0D);
				sclx = Util.getIntValue(json.optString("sclx"), 0);
				bzs = Util.getDoubleValue(json.optString("bzs"), 0.0D);
			}

		} else {
			String tmpDay = ksrq;
			double l_ts = 0.0D;
			double l_xs = 0.0D;
			do {
				l_ts = 0.0D;
				l_xs = 0.0D;
				if (tmpDay.equals(ksrq)) {
					JSONObject json1 = getXjlbSc(gzmc, kssj, "", xjmc);
					l_ts = Util.getDoubleValue(json1.optString("ts"), 0.0D);
					l_xs = Util.getDoubleValue(json1.optString("xs"), 0.0D);
					sclx = Util.getIntValue(json1.optString("sclx"), 0);
					bzs = Util.getDoubleValue(json1.optString("bzs"), 0.0D);
				} else if (tmpDay.equals(jsrq)) {
					JSONObject json2 = getXjlbSc(gzmc, "", jssj, xjmc);
					l_ts = Util.getDoubleValue(json2.optString("ts"), 0.0D);
					l_xs = Util.getDoubleValue(json2.optString("xs"), 0.0D);
					sclx = Util.getIntValue(json2.optString("sclx"), 0);
					bzs = Util.getDoubleValue(json2.optString("bzs"), 0.0D);
				} else {
					l_ts = 1.0D;
				}
				if ((!("婚假".equals(xjmc))) && (!(isRqlb(dq, tmpDay, 1)))) {
					l_ts = 0.0D;
					l_xs = 0.0D;
				}

				ts += l_ts;
				xs += l_xs;
				tmpDay = TimeUtil.dateAdd(tmpDay, 1);
			}
			while (jsrq.compareTo(tmpDay) >= 0);
		}
		if (sclx == 2) {
			if (Tools.round(xs, 1) > 0.5D) {
				ts += 1.0D;
			}
			xs = 0.0D;
			ts = getBts(ts);
		} else if (sclx == 3) {
			xs = Tools.round(ts * bzs + xs, 0);
			ts = 0.0D;
		} else if (sclx == 1) {
			ts = Tools.round(ts, 0);
			xs = 0.0D;
		}
		log.info("getQjsc =>" + ts + "|" + xs + "|" + bzs + ";gzmc=" + gzmc + ";ksrq=" + ksrq + ";jsrq=" + jsrq + ";kssj=" + kssj + ";jssj=" + jssj + ";xjmc=" + xjmc + ";sclx=" + sclx + ";dq=" + dq);
		return (ts + "|" + xs + "|" + bzs + "|" + zmfdj).split("\\|", -1);
	}

	public static JSONObject getXjlbSc(String gzmc, String kssj, String jssj, String xjmc) {
		JSONObject json = new JSONObject();
		json.put("ts", Integer.valueOf(0));
		json.put("xs", Integer.valueOf(0));
		if (xjmc.equals("")) { return json; }
		if ((("维动1|维动2".indexOf(gzmc) > -1) && ("婚假|丧假|陪产假|计划生育假".indexOf(xjmc) > -1)) || (xjmc.equals("产假"))) {
			json.put("ts", Integer.valueOf(1));
			json.put("sclx", Integer.valueOf(1));
		} else if ("事假|病假|哺乳假|调休".indexOf(xjmc) > -1) {
			json = getGzXsSc(gzmc, kssj, jssj);
			json.put("sclx", Integer.valueOf(3));
		} else if ((("菲动|云客|简理财|香港|台湾".indexOf(gzmc) > -1) && ("年休假|工伤假|婚假|丧假|产检假|陪产假|计划生育假|公假|带薪病假".indexOf(xjmc) > -1)) || (("维动1|维动2".indexOf(gzmc) > -1) && ("年休假|工伤假|产检假|公假|带薪病假".indexOf(xjmc) > -1))) {
			json = getGzTsSc(gzmc, kssj, jssj);
			json.put("sclx", Integer.valueOf(2));
		}
		log.info("getXjlbSc =>json=" + json.toString() + ";gzmc=" + gzmc + ";kssj=" + kssj + ";jssj=" + jssj + ";xjmc=" + xjmc);
		return json;
	}

	public static JSONObject getGzTsSc(String gzmc, String kssj, String jssj) {
		JSONObject json = new JSONObject();
		json.put("ts", Integer.valueOf(0));
		json.put("xs", Integer.valueOf(0));
		if (gzmc.equals("简理财")) {
			kssj = (kssj.equals("")) ? "08:00" : kssj;
			jssj = (jssj.equals("")) ? "18:00" : jssj;
			double sc = Tools.round(timediffT(kssj, jssj, "12:00-13:00", "") / 60.0D, 3);
			if (sc >= 8.0D) {
				json.put("ts", Integer.valueOf(1));
			} else if (sc > 4.0D) {
				json.put("ts", Integer.valueOf(1));
			} else {
				json.put("ts", Double.valueOf(0.5D));
			}

			json.put("bzs", Integer.valueOf(8));
		} else {
			JSONObject kqJson = new JSONObject();
			String sbsj = kqJson.optString("sbsj");
			String xbsj = kqJson.optString("xbsj");
			kssj = ((kssj.equals("")) || (kssj.compareTo(sbsj) < 0)) ? sbsj : kssj;
			jssj = ((jssj.equals("")) || (jssj.compareTo(xbsj) > 0)) ? xbsj : jssj;
			double bzs = 8.0D;

			if ((Util.timediff1(kqJson.optString("sbsj"), kssj) >= 0) && (Util.timediff1(jssj, kqJson.optString("xbsj")) >= 0)) {
				json.put("ts", Integer.valueOf(1));
			} else {
				double xs = Tools.round(timediffT(kssj, jssj, kqJson.optString("zwsj"), "") / 60.0D, 3);
				if (Tools.round(xs / bzs, 3) > 0.5D) {
					json.put("ts", Integer.valueOf(1));
				} else {
					json.put("ts", Double.valueOf(0.5D));
				}
			}
			json.put("bzs", Double.valueOf(bzs));
			json.put("sbsj", kqJson.optString("sbsj"));
			json.put("xbsj", kqJson.optString("xbsj"));
			json.put("zwsj", kqJson.optString("zwsj"));
		}
		log.info("getGzTsSc =>json=" + json.toString() + ";gzmc=" + gzmc + ";kssj=" + kssj + ";jssj=" + jssj);
		return json;
	}

	public static JSONObject getGzXsSc(String gzmc, String kssj, String jssj) {
		JSONObject json = new JSONObject();
		json.put("ts", Integer.valueOf(0));
		json.put("xs", Integer.valueOf(0));
		if (gzmc.equals("简理财")) {
			kssj = (kssj.equals("")) ? "08:00" : kssj;
			jssj = (jssj.equals("")) ? "18:00" : jssj;
			double sc = Tools.round(timediffT(kssj, jssj, "12:00-13:00", "") / 60.0D, 3);
			if (sc >= 8.0D) {
				json.put("ts", Integer.valueOf(1));
			} else {
				int ms = (int) (sc * 10.0D) / 10;
				ms += (((int) (sc * 10.0D) % 10 >= 1) ? 1 : 0);
				json.put("xs", Integer.valueOf(ms));
			}
			json.put("bzs", Integer.valueOf(8));
		} else {
			JSONObject kqJson = new JSONObject();
			String sbsj = kqJson.optString("sbsj");
			String xbsj = kqJson.optString("xbsj");
			kssj = ((kssj.equals("")) || (kssj.compareTo(sbsj) < 0)) ? sbsj : kssj;
			jssj = ((jssj.equals("")) || (jssj.compareTo(xbsj) > 0)) ? xbsj : jssj;
			double bzs = 8.0D;

			if ((Util.timediff1(kqJson.optString("sbsj"), kssj) >= 0) && (Util.timediff1(jssj, kqJson.optString("xbsj")) >= 0)) {
				json.put("ts", Integer.valueOf(1));
			} else {
				double xs = Tools.round(timediffT(kssj, jssj, kqJson.optString("zwsj"), "") / 60.0D, 3);
				if (xs >= bzs) {
					json.put("ts", Integer.valueOf(1));
				} else {
					int ms = (int) (xs * 10.0D) / 10;
					ms += (((int) (xs * 10.0D) % 10 >= 1) ? 1 : 0);
					json.put("xs", Integer.valueOf(ms));
				}
			}
			json.put("bzs", Double.valueOf(bzs));
			json.put("sbsj", kqJson.optString("sbsj"));
			json.put("xbsj", kqJson.optString("xbsj"));
			json.put("zwsj", kqJson.optString("zwsj"));
		}
		log.info("getGzXsSc =>json=" + json.toString() + ";gzmc=" + gzmc + ";kssj=" + kssj + ";jssj=" + jssj);
		return json;
	}

	public static double getBts(double ts) {
		int ts01 = (int) ts;
		int ts02 = (int) (ts * 100.0D % 100.0D);
		if (ts02 > 50) { return Tools.round(ts01 + 1, 1); }
		if (ts02 > 0) { return Tools.round(ts01 + 0.5D, 1); }
		return Tools.round(ts01, 1);
	}

	public static int timediffT(String kssj, String jssj, String zwsj, String xwsj) {
		if ((kssj.length() != 5) || (jssj.length() != 5)) { return 0; }
		int sjc = Util.timediff1(jssj, kssj);
		if (sjc <= 0) { return 0; }
		int kczwsjc = 0;
		int kcxwsjc = 0;
		if (!(zwsj.equals(""))) {
			if (zwsj.length() != 11) {
				zwsj = "";
			} else {
				kczwsjc = getTcsc(kssj, jssj, zwsj.substring(0, 5), zwsj.substring(6));
			}
		}
		if (!(xwsj.equals(""))) {
			if (xwsj.length() != 11) {
				xwsj = "";
			} else {
				kcxwsjc = getTcsc(kssj, jssj, xwsj.substring(0, 5), xwsj.substring(6));
			}
		}
		return (sjc - kczwsjc - kcxwsjc);
	}

	public static int getTcsc(String kssj, String jssj, String tckssj, String tcjssj) {
		if ((kssj.length() != 5) || (jssj.length() != 5) || (tckssj.length() != 5) || (tcjssj.length() != 5) || (Util.timediff1(jssj, kssj) < 0) || (Util.timediff1(tcjssj, tckssj) < 0)) { return 0; }
		if (Util.timediff1(tckssj, kssj) > 0) {
			if ((Util.timediff1(tckssj, jssj) <= 0) && (Util.timediff1(tcjssj, jssj) >= 0)) { return Util.timediff1(jssj, tckssj); }
			if (Util.timediff1(tcjssj, jssj) < 0) { return Util.timediff1(tcjssj, tckssj); }
		} else if ((Util.timediff1(tckssj, kssj) <= 0) && (Util.timediff1(tcjssj, kssj) >= 0)) { return Util.timediff1(tcjssj, kssj); }
		return 0;
	}

	public static String monthAdd(String rq, int month) {
		Calendar cal = TimeUtil.getCalendar(rq);
		if (cal == null) { return null; }
		cal.add(2, month);
		return TimeUtil.getDateString(cal);
	}

	public static String yearAdd(String rq, int year) {
		Calendar cal = TimeUtil.getCalendar(rq);
		if (cal == null) { return null; }
		cal.add(1, year);
		return TimeUtil.getDateString(cal);
	}

	public ArrayList<JSONObject> getRpt01(String ryids, String bmids, String fbids, String ksrq, String jsrq) throws ParseException {
		log.info("getRpt01 输入条件：ryids=" + ryids + ";bmids=" + bmids + ";fbids=" + fbids + ";ksrq=" + ksrq + ";jsrq=" + jsrq);
		ArrayList arrRpt = new ArrayList();
		ksrq = (ksrq.equals("")) ? curDate.substring(0, 8) + "01" : ksrq;
		jsrq = (jsrq.equals("")) ? curDate : jsrq;
		if ((ksrq.compareTo(jsrq) > 0) || ((ryids.equals("")) && (fbids.equals("")) && (bmids.equals("")))) { return arrRpt; }

		RecordSet rs = new RecordSet();
		String sql = "select * from hrmresource where status in(0,1,2,3) ";
		if (!(ryids.equals(""))) {
			sql = sql + " and id in(" + ryids + ")";
		}
		if (!(bmids.equals(""))) {
			sql = sql + " and departmentid in(" + bmids + ")";
		}
		if (!(fbids.equals(""))) {
			sql = sql + " and subcompanyid1 in(" + fbids + ")";
		}
		rs.execute(sql);

		jsrq = (jsrq.compareTo(curDate) > 0) ? curDate : jsrq;
		log.info("getRpt01 ksrq=" + ksrq + ";jsrq=" + jsrq + ";sql=" + sql);

		while (rs.next()) {
			int ryid = Util.getIntValue(rs.getString("id"));
			JSONObject ryJson = new JSONObject();
			Map kaMap = getRyDk(ryid, ksrq, jsrq);
			Map lcMap = getRyLcjl(ryid, ksrq, jsrq);
			String kqgzMc = Util.null2String(ryJson.optString("kqgzMc"));
			String calrq = ksrq;
			do {
				JSONObject rptJson = new JSONObject();
				rptJson.put("员工编号", Util.null2String(ryJson.optString("gh")));
				rptJson.put("员工姓名", Util.null2String(ryJson.optString("xm")));
				rptJson.put("所属公司", Util.null2String(ryJson.optString("fbMc")));
				rptJson.put("一级部门", Util.null2String(ryJson.optString("sjbmMc")));
				rptJson.put("二级部门", Util.null2String(ryJson.optString("bmMc")));
				rptJson.put("职位", Util.null2String(ryJson.optString("zwMc")));
				rptJson.put("日期", calrq);
				String dq = ("香港|台湾".indexOf(kqgzMc) > -1) ? kqgzMc : "内地";

				boolean isgzr = isRqlb(dq, calrq, 1);
				if (isgzr) {
					rptJson.put("班值", kqgzMc + "正常班");
				} else {
					rptJson.put("班值", "");
				}
				String sbk = "";
				String xbk = "";
				String kzjl = "";
				if (kaMap.containsKey(calrq)) {
					ArrayList kaList = (ArrayList) kaMap.get(calrq);
					String[] kaArr = (String[]) kaList.toArray(new String[kaList.size()]);
					Arrays.sort(kaArr);
					if (kaArr.length > 0) {
						sbk = kaArr[0];
						xbk = kaArr[(kaArr.length - 1)];
						kzjl = Arrays.toString(kaArr);
					}
					log.info("getRpt01 ryid=" + ryid + ";calrq=" + calrq + ";sbk=" + sbk + ";xbk=" + xbk + ";卡钟记录：" + kzjl + ";dq=" + dq);
				}
				rptJson.put("上班", sbk);
				rptJson.put("下班", xbk);
				rptJson.put("卡钟记录", kzjl);
				String xjqk = "";
				String jbqk = "";
				String qkqk = "";
				if (lcMap.containsKey(calrq)) {
					ArrayList<String> lcList = (ArrayList) lcMap.get(calrq);
					for (String lcqk : lcList) {
						if (lcqk.startsWith("签卡")) {
							qkqk = qkqk + ";" + lcqk;
							qkqk = qkqk.replaceAll("签卡", "");
						} else if (lcqk.startsWith("加班")) {
							jbqk = jbqk + " " + lcqk;
							qkqk = qkqk.replaceAll("加班", "");
						} else if (lcqk.startsWith("请假")) {
							xjqk = xjqk + " " + lcqk;
							xjqk = xjqk.replaceAll("请假", "");
						} else if (lcqk.startsWith("出差")) {
							xjqk = xjqk + " " + lcqk;
						}
					}
					log.info("getRpt01 ryid=" + ryid + ";calrq=" + calrq + " 流程数据：" + Arrays.toString(lcList.toArray()));
				}
				rptJson.put("休假情况", xjqk);
				rptJson.put("签卡情况", qkqk);
				rptJson.put("加班计划", jbqk);

				double sc = 0.0D;
				int i = 0;
				int zt = 0;
				String ycqk = "";
				if (kqgzMc.equals("简理财")) {
					sc = Tools.round(Util.timediff1(xbk, sbk) / 60.0D, 2);
					if ((sc < 9.0D) && (isgzr)) {
						ycqk = "时长不足";
					}
				} else {
					String zwsj = "";
					String xwsj = "";
					JSONObject pbJson = new JSONObject();
					zwsj = Util.null2String(pbJson.optString("zwsj"));
					xwsj = Util.null2String(pbJson.optString("xwsj"));
					sc = Tools.round(timediffT(sbk, xbk, zwsj, xwsj) / 60.0D, 2);
					if (isgzr) {
						String cdsj = Util.null2String(pbJson.optString("cdsj"));
						String xbsj = Util.null2String(pbJson.optString("xbsj"));
						if ((sbk.equals("")) && (xbk.equals("")) && (xjqk.equals("")) && (qkqk.equals(""))) {
							ycqk = "";
						}
						if ((!(xbk.equals(""))) && (xbk.compareTo(xbsj) < 0)) {
							zt = Util.timediff1(xbsj, xbk);
							ycqk = "早退" + zt + "分钟";
						}
						if ((!(sbk.equals(""))) && (sbk.compareTo(cdsj) > 0)) {
							i = Util.timediff1(sbk, cdsj);
							ycqk = "迟到" + i + "分钟";
						}
					}
				}
				rptJson.put("时长", Double.valueOf(sc));
				rptJson.put("异常考勤提示", ycqk);
				rptJson.put("备注", "");

				calrq = TimeUtil.dateAdd(calrq, 1);
				arrRpt.add(rptJson);
			}
			while (jsrq.compareTo(calrq) >= 0);
		}
		return arrRpt;
	}

}
