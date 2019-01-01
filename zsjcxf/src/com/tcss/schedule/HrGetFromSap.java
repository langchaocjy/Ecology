package com.tcss.schedule;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import weaver.formmode.setup.ModeRightInfo;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.hrm.HrmSynTask;
import weaver.interfaces.schedule.BaseCronJob;
import weaver.matrix.MatrixUtil;
import com.tcss.hr.HrUtil;
import com.tcss.hr.SapHrDataCxf;
import com.tcss.util.TcssUtil;

/**
 * HRͬ���ƻ�����
 * 
 * @author lianghui����
 * @date 2018-1-15����5:05:32
 */
public class HrGetFromSap extends BaseCronJob {
	private TcssUtil t = new TcssUtil();

	/**
	 * ��־�����¼
	 */
	public void write(String log) {
		t.write(log);
	}

	public void execute() {
		write("HRͬ���ƻ�����ʼ....");
		SapHrDataCxf hrData = new SapHrDataCxf();//sap�ӿ�����
		HrmSynTask hst = new HrmSynTask();//����oa��ϵͳ�ӿ�
		write("�ֲ�ͬ����ʼ......");
		JSONArray subCompanys = hrData.getSubCompanys();//��ȡ�ֲ�����
		Document subCompany = hrData.getSubCompany(subCompanys);
		String asXML = subCompany.asXML();
		try {
			hst.SynSubCompany(asXML);
		} catch (Exception e) {
			write("�ֲ���" + e.getMessage());
		}
		write("�ֲ�ͬ������......");

		write("����ͬ����ʼ......");
		JSONArray departments = hrData.getDepartments();
		boolean syncDepartment = hrData.syncDepartment(departments);
		MatrixUtil.sysDepartmentData();
		MatrixUtil.sysSubcompayData();
		try {
			DepartmentComInfo departmentComInfo = new DepartmentComInfo();
			departmentComInfo.removeCompanyCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("����ͬ������......" + syncDepartment);

		write("��λͬ����ʼ......");
		JSONArray jobtitles = hrData.getJobtitles();
		boolean syncJobTitle = hrData.syncJobTitle(jobtitles);
		try {
			JobTitlesComInfo jobTitlesComInfo = new JobTitlesComInfo();
			jobTitlesComInfo.removeJobTitlesCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("��λͬ������......" + syncJobTitle);

		write("��Աͬ����ʼ......");
		JSONArray hrmResources = hrData.getHrmResources();
		Document hrmResource = hrData.getHrmResource(hrmResources);
		int pageno = 2000;
		int size = hrmResources.size();
		int count = size / pageno;
		for (int i = 0; i < count; i++) {
			JSONArray jsons1 = new JSONArray();
			for (int j = pageno * i; j < pageno * (i + 1); j++) {
				jsons1.add(hrmResources.getJSONObject(j));
			}
			write(i + "��Ա��" + jsons1.size());
			asXML = hrmResource.asXML(); // write(i + "��Ա��" + asXML);
			try {
				hst.SynHrmResource(asXML);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONArray jsons2 = new JSONArray();
		int other = size - (count * pageno);
		for (int i = size - other; i < size; i++) {
			jsons2.add(hrmResources.getJSONObject(i));
		}
		if (jsons2.size() > 0) {
			write("other��Ա��" + jsons2.size());
			asXML = hrmResource.asXML(); // write("other��Ա��" + asXML);
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
					json.getString("��ʱ��"),json.getString("���ڷ�ʽ"),json.getString("�Ű�"));
		}
		try {
			ResourceComInfo resourcecominfo = new ResourceComInfo();
			resourcecominfo.removeResourceCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("��Աͬ������......");
		write("HrUtilͬ���ɱ����Ŀ�ʼ....");
		JSONArray cbzx = hrData.getCbzx();
		JSONArray hrmjob2=hrData.getJobtitles();
		HrUtil hr = new HrUtil();
		hr.execCbzx(cbzx,hrmjob2);
		ModeRightInfo modeRightInfo = new ModeRightInfo();
		modeRightInfo.setModeId(1);
		modeRightInfo.resetModeRight();
		write("HrUtilͬ���ɱ����Ľ���....");
		write("HRͬ���ƻ��������....");

	}
public static void main(String[] args){
	HrGetFromSap hs=new HrGetFromSap();
	 
	 System.out.println(hs);
}
}
