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
 * @author lianghui����
 * @date 2017-11-9����6:40:34
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
			basicauth.setUsername(username); // �����������û���
			basicauth.setPassword(password); // ��������������
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
				write("SAP���ݷֲ�������" + hrmsubcompany.length);
				int e = 0;
				for (int i = 0; i < hrmsubcompany.length; i++) {
					JSONObject json = new JSONObject();
					Hrmsubcompany_type0 hrmsubcompany_type0 = hrmsubcompany[i];
					String subcompanycode = null2String(hrmsubcompany_type0.getSubcompanycode());
					String subcompanydesc = null2String(hrmsubcompany_type0.getSubcompanydesc());
					String subcompanyname = null2String(hrmsubcompany_type0.getSubcompanyname());
					if (!subcompanycode.equals("") && !subcompanydesc.equals("") && !subcompanyname.equals("")) {
						json.put("�ֲ�����", subcompanydesc);
						json.put("�ֲ����", subcompanyname);
						json.put("�ֲ�����", subcompanycode);
						subCompanys.add(json);
					} else {
						write(++e + "�ֲ���ȱ���ݣ�subcompanycode:" + subcompanycode + "  " + "subcompanydesc:" + subcompanydesc + "  " + "subcompanyname:" + subcompanyname);
					}
				}
			} else {
				write("SAP���ݷֲ�������null");
			}

			Hrmdepartment_type0[] hrmdepartment = s.getHrmdepartment();
			if (hrmdepartment != null) {
				write("SAP���ݲ���������" + hrmdepartment.length);
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
						json.put("��������", departmentname);
						json.put("���ż��", departmentmark);
						json.put("���ű���", departmentcode);
						json.put("�ϼ����ű���", departmentnamesjbm);
						json.put("�ֲ�����", subcompanydescbm);
					    json.put("�㼶����", cjsx);
					    json.put("����", gh);
					    json.put("����", xm);
						departments.add(json);
					} else {
						write(++e + "���Ų�ȱ���ݣ�departmentcode:" + departmentcode + "  " + "departmentmark:" + departmentmark + "  " + "departmentname:" + departmentname + " " + "departmentnamesjbm:"
								+ departmentnamesjbm + " " + "subcompanydescbm:" + subcompanydescbm+" "+"cjsx:" + cjsx + " gh : " + gh + " xm: " + xm );
					}
				}
			} else {
				write("SAP���ݲ���������null");
			}

			Hrmjobtitles_type0[] hrmjobtitles = s.getHrmjobtitles();
			if (hrmjobtitles != null) {
				write("SAP���ݸ�λ������" + hrmjobtitles.length);
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
						json.put("��λ����", jobtitlemark);
						json.put("��λ���", jobtitlemark);
						json.put("��λ����", jobtitlecode);
						json.put("���ű���", departmentnamebm);
						json.put("�ոڱ�ʶ", kgbs);
						json.put("��˾����", bukrs);
						json.put("Ԥ���ֶ�1", bk1);
						json.put("Ԥ���ֶ�2", bk2);
						jobtitles.add(json);
					} else {
						write(++e + "��λ��ȱ���ݣ�departmentnamebm:" + departmentnamebm + "  " + "jobtitlecode:" + jobtitlecode + "  " + "jobtitlemark:" + jobtitlemark + " ");
					}
				}
			} else {
				write("SAP���ݸ�λ������null");
			}

			Formtable_main_30_type0[] formtable_main_30 = s.getFormtable_main_30();
			if (formtable_main_30 != null) {
				write("SAP���ݳɱ�����������" + formtable_main_30.length);
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
					json.put("��������", zzmc);
					json.put("���ű���", zzbm);
					json.put("�ɱ���������", cbzxmc);
					json.put("�ɱ����ı���", cbzxbm);
					json.put("�ɱ�����������", cbfzrmc);
					json.put("�ɱ������˱���", cbfzrbm);
					json.put("���˹�˾����", frgsmc);
					json.put("���˹�˾����", frgsbm);
					json.put("���γɱ�����", zccbzx);
					json.put("�㼶����", cjsx);
					json.put("��ʼ����", bedat);
					json.put("��������", endat);
					cbzx.add(json);
				}
			} else {
				write("SAP���ݳɱ�����������null");
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
		calendar.add(Calendar.DATE, -1);// ��������������һ��.����������,������ǰ�ƶ�
		d = calendar.getTime(); // ���ʱ���������������һ��Ľ��
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
	 * ��ȡSAP��Ա����
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
			basicauth.setUsername(username); // �����������û���
			basicauth.setPassword(password); // ��������������
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
				write("SAP������Ա������" + s.length);
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
					String sex = null2String(user.getSEX()).equals("1") ? "0" : "1";// 1���У�2:Ů
					String status = null2String(user.getSTATUS()).equals("3") ? "1" : "5";// 0:��ְ��3������
					String subcompanyid1 = null2String(user.getSUBCOMPANYID1());
					String telephone = null2String(user.getTELEPHONE());
					String workcode = null2String(user.getWORKCODE());
					String field2 = null2String(user.getField2());// һ������
					
                    String field8 = null2String(user.getWERKS());//���·�Χ
                    String field9 = null2String(user.getBTRTL());//�����ӷ�Χ
                    String field10 = null2String(user.getPERSG());//Ա����
                    String field11 = null2String(user.getPERSK());//Ա������
                    String field12 = null2String(user.getBEGDA());//��ְ����
                    
                    String ansvh=null2String(user.getANSVH());//��ʱ��
                    String zterf=null2String(user.getZTERF());//���ڷ�ʽ
                    String schkz=null2String(user.getSCHKZ());//�Ű�
					String location = selectLocation(locationid);
					if (location.equals("")) {
						locationid = insertLocation(locationid);
					} else {
						locationid = location;
					}
					json.put("����", birthday);
					json.put("���ű���", departmentid);
					json.put("����", email);
					json.put("��λ����", jobtitle);
					json.put("����", lastname);
					json.put("�����ص�", locationid);// ����
					json.put("ֱ���ϼ�����", managerid);
					json.put("�ֻ�����", mobile);
					json.put("�Ա�", sex);
					json.put("״̬", status);
					json.put("�ֲ�����", subcompanyid1);
					json.put("�绰����", telephone);
					json.put("Ա�����", workcode);
					json.put("һ������", field2);
					json.put("���·�Χ",field8);
					json.put("�����ӷ�Χ",field9);
					json.put("Ա����",field10);
					json.put("Ա������",field11);
					json.put("��ְ����",field12);
					json.put("��ʱ��", ansvh);
					json.put("���ڷ�ʽ", zterf);
					json.put("�Ű�", schkz);
					if (!departmentid.equals("") && !jobtitle.equals("") && !lastname.equals("") && !status.equals("") && !subcompanyid1.equals("") && !workcode.equals("")) {
						hrmResources.add(json);
					} else {
						if (status.equals("5")) {
							hrmResources.add(json);
						} else {
								write(++e + "��Ա��ȱ���ݣ�lastname:" + lastname + " workcode:" + workcode + " subcompanyid1:" + subcompanyid1 + " departmentid:" + departmentid + " jobtitle:" + jobtitle
										+ " email:" + email + " locationid:" + locationid + " managerid:" + managerid + " sex:" + sex + " status:" + status + " mobile:" + mobile + " telephone:"
										+ telephone + " birthday:" + birthday );
						}
					}
				}
			} else {
				write("SAP������Ա������null");
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
