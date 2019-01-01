package com.tcss.action.pr;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcss.hr.SapHrData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class zenggang_zengliangtongbu extends BaseBean implements Action{
	private Zengliang_001 zl001 = null;
	@Override
	public String execute(RequestInfo requestinfo) {
		int formid=requestinfo.getRequestManager().getFormid();
		String requestid = requestinfo.getRequestid();
		JSONObject json=getMainData(requestid);
		writeLog("formid:  "+formid);
		writeLog("�����ֶΣ�"+json);
		if (formid==-173) {
			if (json.getString("zplb").equals("0")) {
				zl001 = new Zengliang_001();
				JSONArray hrmjob=zl001.getZengliang001();
				writeLog("hrmjob:"+hrmjob);
				insertCode(hrmjob);
			}
		}else{
			zl001 = new Zengliang_001();
			JSONArray hrmjob=zl001.getZengliang001();
			writeLog("hrmjob:"+hrmjob);
			insertCode(hrmjob);
		}
		return SUCCESS;
	}
	public void insertCode(JSONArray jsons){
		RecordSet rs=new RecordSet();
		boolean flag= false;
		if (jsons.size()>0) {
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject json = jsons.getJSONObject(i);
				rs.execute("select * from uf_bmgwxx where jobtitlecode='"+json.getString("��λ����")+"'");
				if (rs.next()) {
					flag=true;
				}
				
				if (flag) {
					rs.execute("update uf_bmgwxx set jobtitlemark='"+json.getString("��λ���")+"',jobtitlename='"+json.getString("��λ����")+"',"
							+ "departmentnamebm='"+json.getString("���ű���")+"',kgbs='"+json.getString("�ոڱ�ʶ")+"',"
									+ "bukrs='"+json.getString("��˾����")+"',bk1='"+json.getString("Ԥ���ֶ�1")+"',bk2='"+json.getString("Ԥ���ֶ�2")+"'"
											+ "where jobtitlecode='"+json.getString("��λ����")+"'");
				}else{
					String sql = "insert into uf_bmgwxx(jobtitlemark,jobtitlename,jobtitlecode,departmentnamebm,kgbs,bukrs,bk1,bk2,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)values('" + 
							json.getString("��λ���") + 
							"','" + 
							json.getString("��λ����") + 
							"','" + 
							json.getString("��λ����") + 
							"','" + 
							json.getString("���ű���") + 
							"','" + 
							json.getString("�ոڱ�ʶ") + 
							"','" + 
							json.getString("��˾����") + 
							"','" + 
							json.getString("Ԥ���ֶ�1") + 
							"','" + 
							json.getString("Ԥ���ֶ�2") + 
							"','1','" + 
							getDateString("yyyy-MM-dd", new Date()) + 
							"','" + 
							getDateString("HH:mm:ss", new Date()) + 
							"','6381','0')  ";
					rs.execute(sql);
				}
			}
		}
	}
	public String getDateString(String type, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateTime = sdf.format(data);
		return dateTime;
	}
	public JSONObject getMainData(String requestid)
	  {
	    JSONObject json = new JSONObject();
	    RecordSet rs = new RecordSet();
	    String tableName = getTableName(requestid);
	    String sql = "select * from " + tableName + " where requestid='" + requestid + "' ";
	    rs.execute(sql);
	    if (rs.next())
	    {
	      String[] columnName = rs.getColumnName();
	      for (int i = 0; i < columnName.length; i++)
	      {
	        String name = columnName[i].toLowerCase();
	        String value = rs.getString(name);
	        json.put(name, value);
	      }
	    }
	    return json;
	  }
	public String getTableName(String requestid)
	  {
	    String tablename = "";
	    if (!"".equals(requestid))
	    {
	      String sql = "select tablename from workflow_bill where id in(select formid from workflow_base where id in(select workflowid from workflow_requestbase where requestid=" + requestid + "))";
	      RecordSet rs = new RecordSet();
	      rs.execute(sql);
	      if (rs.next()) {
	        tablename = rs.getString("tablename");
	      }
	    }
	    return tablename;
	  }
}
