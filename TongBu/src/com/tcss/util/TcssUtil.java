package com.tcss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import out.deal.afs.kq009.DT_OA_AFS_OA009;
import out.deal.afs.kq009.DT_OA_AFS_OA009_REP;
import out.deal.afs.kq009.MT_OA_AFS_OA009;
import out.deal.afs.kq009.MT_OA_AFS_OA009_REP;
import out.deal.afs.kq009.RESULT_type0;
import out.deal.afs.kq009.SIO_OA_AFS_OA009Service;
import out.deal.afs.kq009.SIO_OA_AFS_OA009ServiceStub;
import out.deal.afs.kq009.Sheet_type0;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 
 * @author lianghui梁辉
 * @date 2017-11-8下午1:48:03
 */
public class TcssUtil extends BaseBean {

	/**
	 * 日志输出记录
	 */
	public void write(String log) {
		StackTraceElement[] temp = Thread.currentThread().getStackTrace();
		StackTraceElement a = (StackTraceElement) temp[2];
		// if (!a.getClassName().equals("com.hy.util.HyUtil")) {
		writeLog("类:" + a.getClassName() + "方法:" + a.getMethodName() + "【" + log + "】", a.getClass());
		// }
		// System.out.println(log);
	}

	public String null2String(String s) {
		return s == null ? "" : s;
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param type
	 * @param data
	 * @return
	 */
	public String getDateString(String type, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateTime = sdf.format(data);
		return dateTime;
	}

	/**
	 * 获取时间对象
	 * 
	 * @param shiftdate
	 * @param btime
	 * @param dksj
	 * @return
	 */
	public Date getDate(String day, String time, String type) {
		SimpleDateFormat from = new SimpleDateFormat(type); // 这里的格式可以自己设置
		Date d1 = null;
		try {
			d1 = from.parse(day + " " + time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1;
	}

	/**
	 * 根据请求id获取表单table名称
	 * 
	 * @param requestid
	 * @return
	 */
	public String getTableName(String requestid) {
		String tablename = "";
		if (!"".equals(requestid)) {
			String sql = "select tablename from workflow_bill where id in(select formid from workflow_base where id in(select workflowid from workflow_requestbase where requestid=" + requestid + "))";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			if (rs.next()) {
				tablename = rs.getString("tablename");
			}
		}
		return tablename;
	}

	/**
	 * 消息文本转换
	 * 
	 * @param msg
	 * @return
	 */
	public String getMessage(String msg) {
		return msg + "<script language='javascript'>window.top.Dialog.alert('" + msg + "')</script>";
	}

	/**
	 * 获取主表数据
	 * 
	 * @param requestid
	 * @return
	 */
	public JSONObject getMainData(String requestid) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		String tableName = getTableName(requestid);
		String sql = "select * from " + tableName + " where requestid='" + requestid + "' ";
		rs.execute(sql);
		if (rs.next()) {
			String[] columnName = rs.getColumnName();
			for (int i = 0; i < columnName.length; i++) {
				String name = columnName[i].toLowerCase();
				String value = rs.getString(name);
				json.put(name, value);
			}
		}
		return json;
	}

	/**
	 * 获取明细表表数据
	 * 
	 * @param requestid
	 * @param index
	 * @return
	 */
	public JSONArray getDetailData(String requestid, int index) {
		JSONArray jsons = new JSONArray();
		RecordSet rs = new RecordSet();
		String tableName = getTableName(requestid);
		String sql = "select * from " + tableName + "_dt" + index + " where mainid in( select id from " + tableName + " where  requestid='" + requestid + "') order by id ";
		rs.execute(sql);
		while (rs.next()) {
			JSONObject json = new JSONObject();
			String[] columnName = rs.getColumnName();
			for (int i = 0; i < columnName.length; i++) {
				String name = columnName[i].toLowerCase();
				String value = rs.getString(name);
				json.put(name, value);
			}
			jsons.add(json);
		}
		return jsons;
	}

	/**
	 * 通过id获取table对象
	 * 
	 * @param code
	 * @return
	 */
	public JSONObject getObjectById(String Td, String tablename, String fieldname) {
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		String sql = "select * from " + tablename + " where " + fieldname + "='" + Td + "'";
		write("sql:" + sql);
		rs.executeSql(sql);
		if (rs.next()) {
			String[] columnName = rs.getColumnName();
			for (int i = 0; i < columnName.length; i++) {
				String name = columnName[i].toLowerCase();
				String value = null2String(rs.getString(name));
				json.put(name, value);
			}
		}
		return json;
	}

	/**
	 * 转换SAP日期格式
	 * 
	 * @param day
	 * @return
	 */
	public String getSapDate(String day) {
		return day.replaceAll("-", "");
	}

	/**
	 * 转换SAP时间格式
	 * 
	 * @param time
	 * @return
	 */
	public String getSapTime(String time) {
		return time.replaceAll(":", "") + "00";
	}

	/**
	 * 比较日期
	 * 
	 * @param begindate
	 * @param begintime
	 * @param enddate
	 * @param endtime
	 * @return
	 */
	public boolean checkDate(String begindate, String begintime, String enddate, String endtime) {
		Date bdate = getDate(begindate, begintime, "yyyy-MM-dd HH:mm");
		Date edate = getDate(enddate, endtime, "yyyy-MM-dd HH:mm");
		return edate.after(bdate);
	}

	/**
	 * 获取两个日期之间的所有日期（yyyy-MM-dd）
	 * 
	 * @param begindate
	 * @param enddate
	 * @param type
	 * @return
	 */
	public JSONArray getBetweenDates(String begindate, String enddate, String type) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		JSONArray result = new JSONArray();
		try {
			Date begin = sdf.parse(begindate);
			Date end = sdf.parse(enddate);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(begin);
			while (begin.getTime() <= end.getTime()) {
				result.add(getDateString(type, tempStart.getTime()));
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
				begin = tempStart.getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 选择框值的转换
	 * 
	 * @param id
	 * @param name
	 * @param formid
	 * @return
	 */
	public String getSelectValue(String id, String name, String formid) {
		String value = "";
		if (!"".equals(name) && !"".equals(id) && !"".equals(formid)) {
			String sql_fieldid = "select id from workflow_billfield where billid='" + formid + "' and upper(fieldname)='" + name.toUpperCase() + "'";
			String sql_value = "select selectname from workflow_SelectItem where fieldid in(" + sql_fieldid + ") and selectvalue='" + id + "'";

			RecordSet rs = new RecordSet();
			rs.executeSql(sql_value);
			if (rs.next()) {
				value = rs.getString("selectname");
			}
		}
		return value;
	}

	/**
	 * 记录集成状态
	 * 
	 * @param requestid
	 * @param sign
	 * @param msg
	 * @return
	 */
	public boolean markSapBack(String requestid, String sign, String msg) {
		boolean b = false;
		RecordSet rs = new RecordSet();
		String tableName = getTableName(requestid);
		String sql = "update " + tableName + " set xxnr='" + msg + "' ,xxlx='" + sign + "' where requestid='" + requestid + "' ";
		write("记录集成状态：" + sql);
		b = rs.execute(sql);
		return b;
	}

	/**
	 * 指定年月第一天 2018-01
	 * 
	 * @param date
	 * @2018-01
	 * @return
	 */
	public String getFirstDay(String date, String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);// "yyyy-MM"
		// 获取前月的第一天

		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cale = new GregorianCalendar();
		cale.setTime(d);
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());

		return firstday;
	}

	/**
	 * 指定年月最后一天 2018-01
	 * 
	 * @param date
	 * @2018-01
	 * @return
	 */
	public String getLastDay(String date, String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);// "yyyy-MM"
		// 获取前月的最后一天
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cale = new GregorianCalendar();
		cale.setTime(d);
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		String lastday = new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
		return lastday;
	}

	/**
	 * 获取排班信息
	 * 
	 * @param userid
	 * @param date
	 * @return
	 */
	public JSONObject getPbInfo(String userid, String date) throws Exception {
		JSONObject json = new JSONObject();

		SIO_OA_AFS_OA009Service stub = new SIO_OA_AFS_OA009ServiceStub();
		MT_OA_AFS_OA009 mT_OA_AFS_OA009 = new MT_OA_AFS_OA009();
		DT_OA_AFS_OA009 param = new DT_OA_AFS_OA009();
		JSONObject user = getObjectById(userid, "hrmresource", "id");
		Sheet_type0 s[] = new Sheet_type0[1];
		for (int i = 0; i < s.length; i++) {
			s[i] = new Sheet_type0();
			s[i].setGh(user.getString("workcode"));
			s[i].setWdkrq(getSapDate(date));
		}
		param.setSheet(s);
		mT_OA_AFS_OA009.setMT_OA_AFS_OA009(param);
		MT_OA_AFS_OA009_REP sIO_OA_AFS_OA009 = stub.sIO_OA_AFS_OA009(mT_OA_AFS_OA009);
		DT_OA_AFS_OA009_REP mt_OA_AFS_OA009_REP = sIO_OA_AFS_OA009.getMT_OA_AFS_OA009_REP();
		RESULT_type0[] result = mt_OA_AFS_OA009_REP.getRESULT();
		if (null != result) {
			for (int i = 0; i < result.length; i++) {
				RESULT_type0 result_type0 = result[i];

				String sbsj = null2String(result_type0.getSBSJ());
				String wdkrq = null2String(result_type0.getWDKRQ());
				String xbsj = null2String(result_type0.getXBSJ());
				String ygh = null2String(result_type0.getYGH());
				String sfdk = null2String(result_type0.getSFDK());
				String pbsj = null2String(result_type0.getPBSJ());
				json.put("sbsj", sbsj);
				json.put("wdkrq", wdkrq);
				json.put("xbsj", xbsj);
				json.put("ygh", ygh);
				json.put("sfdk", sfdk);
				json.put("pbsj", pbsj);
			}
		}
		return json;
	}

	/**
	 * 得到指定月的天数
	 * */
	public int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取上一个月
	 * 
	 * @param type
	 * @return
	 */
	public String getPrevMonth(String type) {
		String month = "";
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		month = getDateString(type, c.getTime());
		return month;
	}

}
