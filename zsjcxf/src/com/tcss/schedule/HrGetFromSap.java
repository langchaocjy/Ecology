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
 * HR同步计划任务
 * 
 * @author lianghui梁辉
 * @date 2018-1-15下午5:05:32
 */
public class HrGetFromSap extends BaseCronJob {
	private TcssUtil t = new TcssUtil();

	/**
	 * 日志输出记录
	 */
	public void write(String log) {
		t.write(log);
	}

	public void execute() {
		write("HR同步计划任务开始....");
		SapHrDataCxf hrData = new SapHrDataCxf();//sap接口数据
		HrmSynTask hst = new HrmSynTask();//调用oa的系统接口
		write("分部同步开始......");
		JSONArray subCompanys = hrData.getSubCompanys();//获取分部数据
		Document subCompany = hrData.getSubCompany(subCompanys);
		String asXML = subCompany.asXML();
		try {
			hst.SynSubCompany(asXML);
		} catch (Exception e) {
			write("分部：" + e.getMessage());
		}
		write("分部同步结束......");

		write("部门同步开始......");
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
		write("部门同步结束......" + syncDepartment);

		write("岗位同步开始......");
		JSONArray jobtitles = hrData.getJobtitles();
		boolean syncJobTitle = hrData.syncJobTitle(jobtitles);
		try {
			JobTitlesComInfo jobTitlesComInfo = new JobTitlesComInfo();
			jobTitlesComInfo.removeJobTitlesCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("岗位同步结束......" + syncJobTitle);

		write("人员同步开始......");
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
			write(i + "人员：" + jsons1.size());
			asXML = hrmResource.asXML(); // write(i + "人员：" + asXML);
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
			write("other人员：" + jsons2.size());
			asXML = hrmResource.asXML(); // write("other人员：" + asXML);
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
					json.getString("工时制"),json.getString("考勤方式"),json.getString("排班"));
		}
		try {
			ResourceComInfo resourcecominfo = new ResourceComInfo();
			resourcecominfo.removeResourceCache();
		} catch (Exception e) {
			e.printStackTrace();
		}
		write("人员同步结束......");
		write("HrUtil同步成本中心开始....");
		JSONArray cbzx = hrData.getCbzx();
		JSONArray hrmjob2=hrData.getJobtitles();
		HrUtil hr = new HrUtil();
		hr.execCbzx(cbzx,hrmjob2);
		ModeRightInfo modeRightInfo = new ModeRightInfo();
		modeRightInfo.setModeId(1);
		modeRightInfo.resetModeRight();
		write("HrUtil同步成本中心结束....");
		write("HR同步计划任务结束....");

	}
public static void main(String[] args){
	HrGetFromSap hs=new HrGetFromSap();
	 
	 System.out.println(hs);
}
}
