package com.tcss.hr;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;

import weaver.formmode.setup.ModeRightInfo;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.hrm.DepartmentBean;
import weaver.interfaces.hrm.HrmSynService;
import weaver.interfaces.hrm.HrmSynTask;
import weaver.interfaces.hrm.JobTitleBean;
import weaver.interfaces.hrm.SubCompanyBean;
import weaver.interfaces.hrm.UserBean;
import weaver.matrix.MatrixUtil;

/**
 * SAP��֯�ܹ�ͬ��
 * 
 * @author ����
 * 
 */
public class HrSync extends HrUtil implements HrmSynService {
	private HrmSynTask hst = new HrmSynTask();
	private SapHrData hrData = null;

	/**
	 * ��ʱͬ���ֲ� ������ϵͳͬ����OA
	 */
	public String SynTimingToOASubCompany() {
		write("�ֲ�ͬ����ʼ......");
		if (null == hrData) {
			hrData = new SapHrData();
			write(hrData.toString());
		}
		JSONArray subCompanys = hrData.getSubCompanys();//��SapHrData��getXXX��ȡjson����
		Document subCompany = hrData.getSubCompany(subCompanys);//��SapHrData()�������HrUtil��ͬ������
		String asXML = subCompany.asXML();
		try {
			hst.SynSubCompany(asXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("�ֲ�ͬ������......");
		return "";
	}

	/**
	 * ��ʱͬ������ ������ϵͳͬ����OA
	 */
	public String SynTimingToOADepartment() {
		write("����ͬ����ʼ......");
		if (null == hrData) {
			hrData = new SapHrData();
		}
		JSONArray departments = hrData.getDepartments();
		boolean syncDepartment = hrData.syncDepartment(departments);
		MatrixUtil.sysDepartmentData();
		MatrixUtil.sysSubcompayData();
		
		try {
			DepartmentComInfo departmentComInfo=new DepartmentComInfo();
			departmentComInfo.removeCompanyCache();
		} catch (Exception e) {
			e.printStackTrace();
		}

		write("����ͬ������......" + syncDepartment);
		return "";
	}

	/**
	 * ��ʱͬ����λ ������ϵͳͬ����OA
	 */
	public String SynTimingToOAJobtitle() {
		write("��λͬ����ʼ......");
		if (null == hrData) {
			hrData = new SapHrData();
		}
		JSONArray jobtitles = hrData.getJobtitles();
		boolean syncJobTitle = hrData.syncJobTitle(jobtitles);
		try {
			JobTitlesComInfo jobTitlesComInfo = new JobTitlesComInfo();
			jobTitlesComInfo.removeJobTitlesCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("��λͬ������......" + syncJobTitle);
		return "";
	}

	/**
	 * ��ʱͬ����Ա ������ϵͳͬ����OA
	 */
	public String SynTimingToOAHrmResource() {
		write("��Աͬ����ʼ......");
		if (null == hrData) {
			hrData = new SapHrData();
		}
		JSONArray hrmResources = hrData.getHrmResources();
		Document hrmResource = hrData.getHrmResource(hrmResources);

		int pageno = 2000;
		int size = hrmResources.size();//2484
		int count = size / pageno;//     2484/2000
		for (int i = 0; i < count; i++) {
			JSONArray jsons1 = new JSONArray();
			for (int j = pageno * i; j < pageno * (i + 1); j++) {
				jsons1.add(hrmResources.getJSONObject(j));
			}
			write(i + "��Ա��" + jsons1.size());
			String asXML = hrmResource.asXML(); // write(i + "��Ա��" + asXML);
		
			try {
	
				hst.SynHrmResource(asXML);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONArray jsons2 = new JSONArray();
		int other = size - (count * pageno);//2484-( 1.2 x 2000 )
		for (int i = size - other; i < size; i++) {
			jsons2.add(hrmResources.getJSONObject(i));
		}
		if (jsons2.size() > 0) {
		    write("other��Ա��" + jsons2.size());
			String asXML = hrmResource.asXML(); // write("other��Ա��" + asXML);
			try {
				hst.SynHrmResource(asXML);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < hrmResources.size(); i++) {
			JSONObject json = hrmResources.getJSONObject(i);
			hrData.updateHrmresource(json.getString("Ա�����"), json.getString("�����ص�"), json.getString("��λ����"), json.getString("״̬"));
			hrData.updateField(json.getString("Ա�����"), json.getString("һ������"),json.getString("���·�Χ"),json.getString("�����ӷ�Χ"),
					json.getString("Ա����"),json.getString("Ա������"),json.getString("��ְ����"),
					json.getString("��ʱ��"),json.getString("���ڷ�ʽ"),
					json.getString("�Ű�"));
		}
		try {
			ResourceComInfo resourcecominfo = new ResourceComInfo();
			resourcecominfo.removeResourceCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("��Աͬ������......");
		int i=0;
		i=hrmResources.size();
		String a=Integer.toString(i);
		return a;
	}

	public void SynInstantDepartment(DepartmentBean arg0) {
		// TODO Auto-generated method stub

	}

	public void SynInstantHrmResource(UserBean arg0) {
		// TODO Auto-generated method stub

	}

	public void SynInstantJobtitle(JobTitleBean arg0) {
		// TODO Auto-generated method stub

	}

	public void SynInstantSubCompany(SubCompanyBean arg0) {
		// TODO Auto-generated method stub

	}

	public boolean SynSendMessage(String arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	public void SynTimingFromOADepartment(DepartmentBean[] arg0) {
		// TODO Auto-generated method stub

	}

	public void SynTimingFromOAHrmResource(UserBean[] arg0) {
		// TODO Auto-generated method stub

	}

	public void SynTimingFromOAJobtitle(JobTitleBean[] arg0) {
		// TODO Auto-generated method stub

	}

	public void SynTimingFromOASubCompany(SubCompanyBean[] arg0) {
		// TODO Auto-generated method stub

	}

	public HashMap getSynResult() {
		write("getSynResult................................");
		hrData = null;
		ModeRightInfo modeRightInfo = new ModeRightInfo();
		modeRightInfo.setModeId(1);
		modeRightInfo.resetModeRight();
		return null;
	}

	public void removeSynResult() {
		write("removeSynResult................................");
		hrData = new SapHrData();
		write("HrUtilͬ���ɱ����Ŀ�ʼ....");
		JSONArray cbzx = hrData.getCbzx();
		JSONArray hrmjob2=hrData.getJobtitles();
		execCbzx(cbzx,hrmjob2);
		write("HrUtilͬ���ɱ����Ľ���....");
	}
public static void main(String[] args){}
}
