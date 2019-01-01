package com.gzfd;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.formmode.customjavacode.AbstractModeExpandJavaCode;
import weaver.formmode.interfaces.ModeDataBatchImport;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.common.Tools;
import weaver.soa.workflow.request.RequestInfo;

import com.weaver.general.TimeUtil;

public class modeExpand extends AbstractModeExpandJavaCode {
	private final ModeDataBatchImport MDBI = new ModeDataBatchImport();

	public void doModeExpand(Map<String, Object> param) throws Exception {
		User user = (User) param.get("user");
		int billid = -1;
		int modeid = -1;
		int userid = user.getUID();
		RequestInfo requestInfo = (RequestInfo) param.get("RequestInfo");
		if (requestInfo != null) {
			billid = Util.getIntValue(requestInfo.getRequestid());
			modeid = Util.getIntValue(requestInfo.getWorkflowid());
			KqUtil.log.info("doModeExpand billid=" + billid + ";modeid=" + modeid + ";userid=" + userid);
			if (modeid == 24) {
				DrCopyNj(billid, userid);
			} else if (modeid == 23) {
				CalNj(billid);
			}
		}
	}

	private void DrCopyNj(int billid, int userid) {
		int ryid = 0;
		int lx = -1;
		int zt = -1;
		String gh = "";
		String nd = "";
		String rq1 = "";
		String rq2 = "";
		String bz = "";
		String errors = "";
		float njts = 0.0F;
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		String sql = "select * from uf_njxxdrwhb where id=" + billid;
		rs.execute(sql);
		boolean flag = true;
		if (rs.next()) {
			if (KqUtil.gGh.isEmpty()) {
				KqUtil.initMap("");
			}
			gh = Util.null2String(rs.getString("gonghao"));
			ryid = (KqUtil.gGh.containsKey(gh)) ? KqUtil.gGh.get(gh).intValue() : 0;
			if (ryid == 0) {
				flag = false;
				errors = errors + "DrCopyNj gh=" + gh + " 未找到相应的人员";
			} else {
				nd = Util.null2String(rs.getString("niandu"));
				lx = Util.getIntValue(rs.getString("leixing"), -1);
				rq1 = Util.null2String(rs.getString("sxrq"));
				rq2 = Util.null2String(rs.getString("shixiaorq"));
				njts = Util.getFloatValue(rs.getString("dydfpnjts"), 0.0F);
				zt = Util.getIntValue(rs.getString("zhuangtai"), -1);
				bz = Util.null2String(rs.getString("beizhu"));
				if ((lx == -1) || (zt == -1) || (rq1.equals("")) || (rq2.equals("")) || (nd.equals(""))) {
					flag = false;
					errors = errors + "年度(" + nd + ")|类型(" + lx + ")|生效日期(" + rq1 + ")|失效日期(" + rq2 + ")|状态(" + zt + ")不能空白";
				}
			}
		} else {
			flag = false;
			errors = errors + "DrCopyNj 未找到导入记录 sql=" + sql;
		}
		if (flag) {
			int id = 0;
			rs.execute("select * from uf_njxx where ryid=" + ryid + " and formmodeid=" + 23);
			if (rs.next()) {
				id = Util.getIntValue(rs.getString("id"), 0);
			} else {
				id = MDBI.getMainId("uf_njxx", 23, userid, 0);
				JSONObject json = new JSONObject();
				String rzrq = Util.null2String(json.optString("rzrq"));
				int ssbm = Util.getIntValue(json.optString("bmId"), 0);
				int ssgs = Util.getIntValue(json.optString("fbId"), 0);
				double gl = (rzrq.equals("")) ? 0.0D : Tools.round(Util.dayDiff(rzrq, KqUtil.curDate) / 365.0D, 1);
				rs0.execute("update uf_njxx set gh='" + gh + "',rzrq='" + rzrq + "',ssbm=" + ssbm + ",ssgs=" + ssgs + ",gl=" + gl + ",ryid=" + ryid + " where id=" + id);
			}

			String curDateTime = TimeUtil.getCurrentTimeString();
			rs.execute("select * from uf_njxx_dt1 where mainid=" + id + " and nd='" + nd + "' and lx=" + lx);
			if (rs.next()) {
				int mxid = Util.getIntValue(rs.getString("id"), 0);
				rs0.execute("update uf_njxx_dt1 set rq1='" + rq1 + "',rq2='" + rq2 + "',njts=" + KqUtil.getBts(njts) + ",zt=" + zt + ",bz='" + bz + "',gxsj='" + curDateTime + "' where id=" + mxid);
			} else {
				rs0.execute("insert into uf_njxx_dt1(mainid,nd,lx,rq1,rq2,njts,zt,bz,gxsj) values(" + id + ",'" + nd + "'," + lx + ",'" + rq1 + "','" + rq2 + "'," + KqUtil.getBts(njts) + ",'" + zt + "','" + bz + "','" + curDateTime + "')");
			}
			CalNj(id);
		}
		if (!(flag)) {
			KqUtil.log.error(errors);
		}
	}

	public void CalNj(int billid) {
		boolean flag = true;
		String errors = "";
		int ryid = 0;
		double jzts = 0.0D;
		String jzsxrq = "";
		RecordSet rs = new RecordSet();
		RecordSet rs0 = new RecordSet();
		rs.execute("select * from uf_njxx where id=" + billid);
		if (rs.next()) {
			ryid = Util.getIntValue(rs.getString("ryid"), 0);
			jzts = KqUtil.getBts(Util.getDoubleValue(rs.getString("jzts"), 0.0D));
			jzsxrq = Util.null2String(rs.getString("jzsxrq"));
			if (jzsxrq.compareTo(KqUtil.curDate) < 0) {
				jzts = 0.0D;
			}
		}
		if (ryid == 0) {
			flag = false;
			errors = errors + "CalNj ryid=0,来源billid=" + billid;
		}
		rs.execute("select * from uf_njxx where id<>" + billid + " and ryid=" + ryid + " and formmodeid=" + 23);
		if (rs.next()) {
			flag = false;
			String gh = Util.null2String(rs.getString("gh"));
			errors = errors + "CalNj ryid=" + ryid + ",billid=" + billid + ";工号(" + gh + ")重复";
			rs0.execute("update uf_njxx set formmodeid=0 where id=" + billid);
		}
		if (flag) {
			String sql = "select * from uf_njxx_dt1 where mainid=" + billid + " order by id";
			Map mxMap = new HashMap();
			rs.execute(sql);
			int nd;
			int lx;
			int id;
			String key;
			while (rs.next()) {
				nd = Util.getIntValue(rs.getString("nd"), 0);
				lx = Util.getIntValue(rs.getString("lx"), -1);
				id = Util.getIntValue(rs.getString("id"));
				key = nd + "_" + lx;
				mxMap.put(key, Integer.valueOf(id));
				double njts = Util.getDoubleValue(rs.getString("njts"), 0.0D);
				rs0.execute("update uf_njxx_dt1 set njts=" + KqUtil.getBts(njts) + " where id=" + id);
			}
			rs.execute(sql);
			while (rs.next()) {
				nd = Util.getIntValue(rs.getString("nd"), 0);
				lx = Util.getIntValue(rs.getString("lx"), -1);
				id = Util.getIntValue(rs.getString("id"));
				key = nd + "_" + lx;
				if ((!(mxMap.containsKey(key))) || (((Integer) mxMap.get(key)).intValue() == id)) {
					continue;
				}
				rs0.execute("update uf_njxx_dt1 set mainid=" + (-1 * billid) + " where id=" + id);
			}

			if (KqUtil.gHrmMap.isEmpty()) {
				KqUtil.initMap("");
			}

			sql = "select * from uf_njxx_dt1 where mainid=" + billid + " and zt=0";
			double bnts = 0.0D;
			double snts = jzts;
			double bnxz = 0.0D;
			int bn = Util.getIntValue(KqUtil.curDate.substring(0, 4));
			rs.execute(sql);
			while (rs.next()) {
				nd = Util.getIntValue(rs.getString("nd"), 0);
				lx = Util.getIntValue(rs.getString("lx"), -1);
				double njts = KqUtil.getBts(Util.getDoubleValue(rs.getString("njts"), 0.0D));
				if ((nd == bn) && (((lx == 0) || (lx == 2)))) {
					bnts += njts;
					if (lx == 2) {
						bnxz = njts;
					}
				}
				if ((nd == bn - 1) && (lx == 1)) {
					snts += njts;
				}
				String rq2 = Util.null2String(rs.getString("rq2"));
				if ((nd == bn) && (lx == 2) && (rq2.compareTo(bn + "-12-31") > 0)) {
					id = Util.getIntValue(rs.getString("id"));
					rs0.execute("update uf_njxx_dt1 set rq2='" + nd + "-12-31' where id=" + id);
				}
			}

			double yxnj = 0.0D;
			String sySql = "select sum(ts) ts from uf_lcjl where ryid=" + ryid + " and lb='年休假' and ksrq like '" + bn + "%'";
			rs.execute(sySql);
			if (rs.next()) {
				yxnj = KqUtil.getBts(Util.getDoubleValue(rs.getString("ts"), 0.0D));
			}
			double bnyxts = 0.0D;
			double snyxts = 0.0D;
			if (snts >= yxnj) {
				snyxts = yxnj;
				yxnj = 0.0D;
			} else {
				snyxts = snts;
				yxnj -= snts;
			}
			if (bnts >= yxnj) {
				bnyxts = yxnj;
				yxnj = 0.0D;
			} else {
				bnyxts = bnts;
				yxnj -= bnts;
			}
			double bnye = KqUtil.getBts(Tools.round(bnts - bnyxts, 1));
			bnye = (bnye < 0.0D) ? 0.0D : bnye;
			double snye = KqUtil.getBts(Tools.round(snts - snyxts, 1));
			snye = (snye < 0.0D) ? 0.0D : snye;
			bnts = KqUtil.getBts(Tools.round(bnts, 1));
			snts = KqUtil.getBts(Tools.round(snts, 1));

			JSONObject json = new JSONObject();
			String rzrq = Util.null2String(json.optString("rzrq"));
			int ssbm = Util.getIntValue(json.optString("bmId"), 0);
			int ssgs = Util.getIntValue(json.optString("fbId"), 0);
			String gh = Util.null2String(json.optString("gh"));
			double gl = (rzrq.equals("")) ? 0.0D : Tools.round(Util.dayDiff(rzrq, KqUtil.curDate) / 365.0D, 1);
			rs0.execute("update uf_njxx set gh='" + gh + "',rzrq='" + rzrq + "',ssbm=" + ssbm + ",ssgs=" + ssgs + ",gl=" + gl + ",bnts=" + bnts + ",bnyxts=" + bnyxts + ",bnye=" + bnye + ",snts=" + snts + ",snyxts=" + snyxts + ",snye=" + snye + ",bnxz=" + bnxz + " where id=" + billid);
		}
		if (!(flag)) {
			KqUtil.log.error(errors);
		}
	}
}
