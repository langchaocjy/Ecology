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
 * SAP组织架构同步
 * 
 * @author 梁辉
 * 
 */
public class HrSync extends HrUtil implements HrmSynService {
	private HrmSynTask hst = new HrmSynTask();
	private SapHrData hrData = null;

	/**
	 * 定时同步分部 从其他系统同步到OA
	 */
	public String SynTimingToOASubCompany() {
		write("分部同步开始......");
		if (null == hrData) {
			hrData = new SapHrData();
			write(hrData.toString());
		}
		JSONArray subCompanys = hrData.getSubCompanys();//从SapHrData的getXXX获取json数据
		Document subCompany = hrData.getSubCompany(subCompanys);//用SapHrData()对象调用HrUtil的同步方法
		String asXML = subCompany.asXML();
		try {
			hst.SynSubCompany(asXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("分部同步结束......");
		return "";
	}

	/**
	 * 定时同步部门 从其他系统同步到OA
	 */
	public String SynTimingToOADepartment() {
		write("部门同步开始......");
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

		write("部门同步结束......" + syncDepartment);
		return "";
	}

	/**
	 * 定时同步岗位 从其他系统同步到OA
	 */
	public String SynTimingToOAJobtitle() {
		write("岗位同步开始......");
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
		write("岗位同步结束......" + syncJobTitle);
		return "";
	}

	/**
	 * 定时同步人员 从其他系统同步到OA
	 */
	public String SynTimingToOAHrmResource() {
		write("人员同步开始......");
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
			write(i + "人员：" + jsons1.size());
			String asXML = hrmResource.asXML(); // write(i + "人员：" + asXML);
		
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
		    write("other人员：" + jsons2.size());
			String asXML = hrmResource.asXML(); // write("other人员：" + asXML);
			try {
				hst.SynHrmResource(asXML);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < hrmResources.size(); i++) {
			JSONObject json = hrmResources.getJSONObject(i);
			hrData.updateHrmresource(json.getString("员工编号"), json.getString("工作地点"), json.getString("岗位编码"), json.getString("状态"));
			hrData.updateField(json.getString("员工编号"), json.getString("一级部门"),json.getString("人事范围"),json.getString("人事子范围"),
					json.getString("员工组"),json.getString("员工子组"),json.getString("入职日期"),
					json.getString("工时制"),json.getString("考勤方式"),
					json.getString("排班"));
		}
		try {
			ResourceComInfo resourcecominfo = new ResourceComInfo();
			resourcecominfo.removeResourceCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("人员同步结束......");
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
		write("HrUtil同步成本中心开始....");
		JSONArray cbzx = hrData.getCbzx();
		JSONArray hrmjob2=hrData.getJobtitles();
		execCbzx(cbzx,hrmjob2);
		write("HrUtil同步成本中心结束....");
	}
public static void main(String[] args){}
}
