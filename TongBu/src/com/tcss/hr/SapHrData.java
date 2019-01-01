package com.tcss.hr;

import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;
import out.deal.afs.company.DT_OA_AFS_OA001;
import out.deal.afs.company.DT_OA_AFS_OA001_REP;
import out.deal.afs.company.Formtable_main_30_type0;
import out.deal.afs.company.Hrmdepartment_type0;
import out.deal.afs.company.Hrmjobtitles_type0;
import out.deal.afs.company.Hrmsubcompany_type0;
import out.deal.afs.company.MT_OA_AFS_OA001;
import out.deal.afs.company.MT_OA_AFS_OA001_REP;
import out.deal.afs.company.SHEET_type0;
import out.deal.afs.company.SIO_OA_AFS_OA001Service;
import out.deal.afs.company.SIO_OA_AFS_OA001ServiceStub;
import out.deal.afs.user.DT_OA_AFS_OA002;
import out.deal.afs.user.DT_OA_AFS_OA002_REP;
import out.deal.afs.user.MT_OA_AFS_OA002;
import out.deal.afs.user.MT_OA_AFS_OA002_REP;
import out.deal.afs.user.SIO_OA_AFS_OA002Service;
import out.deal.afs.user.SIO_OA_AFS_OA002ServiceStub;

/**
 * @author lianghui梁辉
 * @date 2017-11-9下午6:40:34
 */
public class SapHrData extends HrUtil {
	public JSONArray subCompanys = new JSONArray();
	public JSONArray departments = new JSONArray();
	public JSONArray jobtitles = new JSONArray();
	public JSONArray hrmResources = new JSONArray();
	public JSONArray cbzx = new JSONArray();

	public JSONArray getSubCompanys() {
		return subCompanys;
	}

	public void setSubCompanys(JSONArray subCompanys) {
		this.subCompanys = subCompanys;
	}

	public JSONArray getDepartments() {
		return departments;
	}

	public void setDepartments(JSONArray departments) {
		this.departments = departments;
	}

	public JSONArray getJobtitles() {
		return jobtitles;
	}

	public void setJobtitles(JSONArray jobtitles) {
		this.jobtitles = jobtitles;
	}

	public JSONArray getHrmResources() {
		return hrmResources;
	}

	public void setHrmResources(JSONArray hrmResources) {
		this.hrmResources = hrmResources;
	}

	public JSONArray getCbzx() {
		return cbzx;
	}

	public void setCbzx(JSONArray cbzx) {
		this.cbzx = cbzx;
	}
    
	public SapHrData() {
		String date = getDateString("yyyyMMdd", new Date());
		write("date:" + date);
		JSONArray subCompanys = new JSONArray();
		JSONArray departments = new JSONArray();
		JSONArray jobtitles = new JSONArray();
		JSONArray cbzx = new JSONArray();
		
		 String username = SapPiInfo.getUsername_Hr();
		 String password = SapPiInfo.getPassword_Hr();
		try {
			SIO_OA_AFS_OA001Service stub = new SIO_OA_AFS_OA001ServiceStub();
			HttpTransportPropertiesImpl.Authenticator basicauth = new HttpTransportPropertiesImpl.Authenticator();
			basicauth.setUsername(username); // 服务器访问用户名
			basicauth.setPassword(password); // 服务器访问密码
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicauth);
			DT_OA_AFS_OA001 dt = new DT_OA_AFS_OA001();
			dt.setBEGDA(date);
			dt.setENDDA(date);
			MT_OA_AFS_OA001 mt = new MT_OA_AFS_OA001();
			mt.setMT_OA_AFS_OA001(dt);
			MT_OA_AFS_OA001_REP mt_erp = stub.sIO_OA_AFS_OA001(mt);
			DT_OA_AFS_OA001_REP dt_erp = mt_erp.getMT_OA_AFS_OA001_REP();
			SHEET_type0 s = dt_erp.getSHEET();
			Hrmsubcompany_type0[] hrmsubcompany = s.getHrmsubcompany();
			if (hrmsubcompany != null) {
				write("SAP传递分部数量：" + hrmsubcompany.length);
				int e = 0;
				for (int i = 0; i < hrmsubcompany.length; i++) {
					JSONObject json = new JSONObject();
					Hrmsubcompany_type0 hrmsubcompany_type0 = hrmsubcompany[i];
					String subcompanycode = null2String(hrmsubcompany_type0.getSubcompanycode());
					String subcompanydesc = null2String(hrmsubcompany_type0.getSubcompanydesc());
					String subcompanyname = null2String(hrmsubcompany_type0.getSubcompanyname());
					if (!subcompanycode.equals("") && !subcompanydesc.equals("") && !subcompanyname.equals("")) {
						json.put("分部名称", subcompanydesc);
						json.put("分部简称", subcompanyname);
						json.put("分部编码", subcompanycode);
						subCompanys.add(json);
					} else {
						write(++e + "分部残缺数据：subcompanycode:" + subcompanycode + "  " + "subcompanydesc:" + subcompanydesc + "  " + "subcompanyname:" + subcompanyname);
					}
				}
			} else {
				write("SAP传递分部数量：null");
			}

			Hrmdepartment_type0[] hrmdepartment = s.getHrmdepartment();
			if (hrmdepartment != null) {
				write("SAP传递部门数量：" + hrmdepartment.length);
				int e = 0;
				for (int i = 0; i < hrmdepartment.length; i++) {
					JSONObject json = new JSONObject();
					Hrmdepartment_type0 hrmdepartment_type0 = hrmdepartment[i];
					String departmentcode = null2String(hrmdepartment_type0.getDepartmentcode());
					String departmentmark = null2String(hrmdepartment_type0.getDepartmentmark());
					String departmentname = null2String(hrmdepartment_type0.getDepartmentname());
					String departmentnamesjbm = null2String(hrmdepartment_type0.getDepartmentnamesjbm()).equals("") ? "0" : null2String(hrmdepartment_type0.getDepartmentnamesjbm());
					String subcompanydescbm = null2String(hrmdepartment_type0.getSubcompanydescbm());
                    String cjsx= null2String(hrmdepartment_type0.getCjsx());
                    String gh = null2String(hrmdepartment_type0.getGh());
                    String xm = null2String(hrmdepartment_type0.getName());
					if (!departmentcode.equals("") && !departmentmark.equals("") && !departmentname.equals("") && !subcompanydescbm.equals("")) {
						json.put("部门名称", departmentname);
						json.put("部门简称", departmentmark);
						json.put("部门编码", departmentcode);
						json.put("上级部门编码", departmentnamesjbm);
						json.put("分部编码", subcompanydescbm);
					    json.put("层级属性", cjsx);
					    json.put("工号", gh);
					    json.put("姓名", xm);
						departments.add(json);
					} else {
						write(++e + "部门残缺数据：departmentcode:" + departmentcode + "  " + "departmentmark:" + departmentmark + "  " + "departmentname:" + departmentname + " " + "departmentnamesjbm:"
								+ departmentnamesjbm + " " + "subcompanydescbm:" + subcompanydescbm+" "+"cjsx:" + cjsx + " gh : " + gh + " xm: " + xm );
					}
				}
			} else {
				write("SAP传递部门数量：null");
			}

			Hrmjobtitles_type0[] hrmjobtitles = s.getHrmjobtitles();
			if (hrmjobtitles != null) {
				write("SAP传递岗位数量：" + hrmjobtitles.length);
				int e = 0;
				for (int i = 0; i < hrmjobtitles.length; i++) {
					JSONObject json = new JSONObject();
					Hrmjobtitles_type0 hrmjobtitles_type0 = hrmjobtitles[i];
					String departmentnamebm = null2String(hrmjobtitles_type0.getDepartmentnamebm());
					String jobtitlecode = null2String(hrmjobtitles_type0.getJobtitlecode());
					String jobtitlemark = null2String(hrmjobtitles_type0.getJobtitlemark());
					String kgbs=null2String(hrmjobtitles_type0.getKgbs());
					String bukrs=null2String(hrmjobtitles_type0.getBukrs());
					String bk1=null2String(hrmjobtitles_type0.getBk1());
					String bk2=null2String(hrmjobtitles_type0.getBk2());
					if (!jobtitlecode.equals("") && !jobtitlemark.equals("")) {
						json.put("岗位名称", jobtitlemark);
						json.put("岗位简称", jobtitlemark);
						json.put("岗位编码", jobtitlecode);
						json.put("部门编码", departmentnamebm);
						json.put("空岗标识", kgbs);
						json.put("公司代码", bukrs);
						json.put("预留字段1", bk1);
						json.put("预留字段2", bk2);
						jobtitles.add(json);
					} else {
						write(++e + "岗位残缺数据：departmentnamebm:" + departmentnamebm + "  " + "jobtitlecode:" + jobtitlecode + "  " + "jobtitlemark:" + jobtitlemark + " ");
					}
				}
			} else {
				write("SAP传递岗位数量：null");
			}

			Formtable_main_30_type0[] formtable_main_30 = s.getFormtable_main_30();
			if (formtable_main_30 != null) {
				write("SAP传递成本中心数量：" + formtable_main_30.length);
				for (int i = 0; i < formtable_main_30.length; i++) {
					JSONObject json = new JSONObject();
					Formtable_main_30_type0 formtable_main_30_type0 = formtable_main_30[i];
					String cbfzrbm = null2String(formtable_main_30_type0.getCbfzrbm());
					String cbfzrmc = null2String(formtable_main_30_type0.getCbfzrmc());
					String cbzxbm = null2String(formtable_main_30_type0.getCbzxbm());
					String cbzxmc = null2String(formtable_main_30_type0.getCbzxmc());
					String frgsbm = null2String(formtable_main_30_type0.getFrgsbm());
					String frgsmc = null2String(formtable_main_30_type0.getFrgsmc());
					String zccbzx = null2String(formtable_main_30_type0.getZccbzx());
					String zzbm = null2String(formtable_main_30_type0.getZzbm());
					String zzmc = null2String(formtable_main_30_type0.getZzmc());
					String cjsx= null2String(formtable_main_30_type0.getCjsx());
					String bedat=null2String(formtable_main_30_type0.getBedat());
					String endat=null2String(formtable_main_30_type0.getEndat());
					json.put("部门名称", zzmc);
					json.put("部门编码", zzbm);
					json.put("成本中心名称", cbzxmc);
					json.put("成本中心编码", cbzxbm);
					json.put("成本负责人名称", cbfzrmc);
					json.put("成本负责人编码", cbfzrbm);
					json.put("法人公司名称", frgsmc);
					json.put("法人公司编码", frgsbm);
					json.put("主次成本中心", zccbzx);
					json.put("层级属性", cjsx);
					json.put("开始日期", bedat);
					json.put("结束日期", endat);
					cbzx.add(json);
				}
			} else {
				write("SAP传递成本中心数量：null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setSubCompanys(subCompanys);
		setDepartments(departments);
		setJobtitles(jobtitles);
		setCbzx(cbzx);

		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		d = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		date = getDateString("yyyyMMdd", d);
		String begindate = date;
		String enddate = date;
		String frstdate_Hr = SapPiInfo.getFrstdate_Hr();
		write("frstdate_hr: "+frstdate_Hr);
		if (!frstdate_Hr.equals("")) {
			begindate = frstdate_Hr;
		}
		write("begindate:" + begindate + " enddate:" + enddate);
		JSONArray hrmResources = getHrmResources(begindate, enddate);
		setHrmResources(hrmResources);
	}

	/**
	 * 读取SAP人员数据
	 * 
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	public JSONArray getHrmResources(String begindate, String enddate) {
		JSONArray hrmResources = new JSONArray();
		 String username = SapPiInfo.getUsername_Hr();
		 String password = SapPiInfo.getPassword_Hr();
		try {
			SIO_OA_AFS_OA002Service stub = new SIO_OA_AFS_OA002ServiceStub();
			HttpTransportPropertiesImpl.Authenticator basicauth = new HttpTransportPropertiesImpl.Authenticator();
			basicauth.setUsername(username); // 服务器访问用户名
			basicauth.setPassword(password); // 服务器访问密码
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicauth);
			MT_OA_AFS_OA002 mt = new MT_OA_AFS_OA002();
			DT_OA_AFS_OA002 dt = new DT_OA_AFS_OA002();
			dt.setBEGDA(begindate);
			dt.setENDDA(enddate);
			mt.setMT_OA_AFS_OA002(dt);
			MT_OA_AFS_OA002_REP mt_erp = stub.sIO_OA_AFS_OA002(mt);
			DT_OA_AFS_OA002_REP dt_erp = mt_erp.getMT_OA_AFS_OA002_REP();
			out.deal.afs.user.SHEET_type0[] s = dt_erp.getSHEET();
			if (s != null) {
				write("SAP传递人员数量：" + s.length);
				int e = 0;
				for (int i = 0; i < s.length; i++) {
					JSONObject json = new JSONObject();
					out.deal.afs.user.SHEET_type0 user = s[i];
					String birthday = null2String(user.getBIRTHDAY()).equals("") ? "" : getDateString("yyyy-MM-dd", getDate(null2String(user.getBIRTHDAY()), "00:00", "yyyyMMdd"));
					String departmentid = null2String(user.getDEPARTMENTID());
					String email = null2String(user.getEMAIL());
					String jobtitle = null2String(user.getJOBTITLE());
					String lastname = null2String(user.getLASTNAME());
					String locationid = null2String(user.getLOCATIONID());
					String managerid = null2String(user.getMANAGERID());
					String mobile = null2String(user.getMOBILE());
					String sex = null2String(user.getSEX()).equals("1") ? "0" : "1";// 1：男；2:女
					String status = null2String(user.getSTATUS()).equals("3") ? "1" : "5";// 0:离职；3：激活
					String subcompanyid1 = null2String(user.getSUBCOMPANYID1());
					String telephone = null2String(user.getTELEPHONE());
					String workcode = null2String(user.getWORKCODE());
					String field2 = null2String(user.getField2());// 一级部门
					
                    String field8 = null2String(user.getWERKS());//人事范围
                    String field9 = null2String(user.getBTRTL());//人事子范围
                    String field10 = null2String(user.getPERSG());//员工组
                    String field11 = null2String(user.getPERSK());//员工子组
                    String field12 = null2String(user.getBEGDA());//入职日期
                    
                    String ansvh=null2String(user.getANSVH());//工时制
                    String zterf=null2String(user.getZTERF());//考勤方式
                    String schkz=null2String(user.getSCHKZ());//排班
					String location = selectLocation(locationid);
					if (location.equals("")) {
						locationid = insertLocation(locationid);
					} else {
						locationid = location;
					}
					json.put("生日", birthday);
					json.put("部门编码", departmentid);
					json.put("邮箱", email);
					json.put("岗位编码", jobtitle);
					json.put("姓名", lastname);
					json.put("工作地点", locationid);// 中文
					json.put("直接上级编码", managerid);
					json.put("手机号码", mobile);
					json.put("性别", sex);
					json.put("状态", status);
					json.put("分部编码", subcompanyid1);
					json.put("电话号码", telephone);
					json.put("员工编号", workcode);
					json.put("一级部门", field2);
					json.put("人事范围",field8);
					json.put("人事子范围",field9);
					json.put("员工组",field10);
					json.put("员工子组",field11);
					json.put("入职日期",field12);
					json.put("工时制", ansvh);
					json.put("考勤方式", zterf);
					json.put("排班", schkz);
					if (!departmentid.equals("") && !jobtitle.equals("") && !lastname.equals("") && !status.equals("") && !subcompanyid1.equals("") && !workcode.equals("")) {
						hrmResources.add(json);
					} else {
						if (status.equals("5")) {
							hrmResources.add(json);
						} else {
								write(++e + "人员残缺数据：lastname:" + lastname + " workcode:" + workcode + " subcompanyid1:" + subcompanyid1 + " departmentid:" + departmentid + " jobtitle:" + jobtitle
										+ " email:" + email + " locationid:" + locationid + " managerid:" + managerid + " sex:" + sex + " status:" + status + " mobile:" + mobile + " telephone:"
										+ telephone + " birthday:" + birthday );
						}
					}
				}
			} else {
				write("SAP传递人员数量：null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hrmResources;
	}

public static void main(String[] args){
SapHrData sap=new SapHrData();
JSONArray json=sap.getHrmResources();
}

}
