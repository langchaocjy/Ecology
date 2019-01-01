package com.tcss.action.pr;


import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class zenggang_zengliangtongbu extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		int formid=requestinfo.getRequestManager().getFormid();
		String requestid = requestinfo.getRequestid();
		JSONObject json=getMainData(requestid);
		writeLog("formid:  "+formid);
		writeLog("Ö÷±í×Ö¶Î£º"+json);
		if (formid==-55) {
			if (json.getString("zplb").equals("0")) {
				String sql=insertCode(json,"zpgw2","gwbm");
				writeLog(formid+"sql: "+sql);
			}
		}else if(formid==-152){
			String sql = insertCode(json,"xzgw","gwbm");
			writeLog(formid+"sql: "+sql);
		}else{
			String sql = insertCode(json,"zpgw","gwbm");
			writeLog(formid+"sql: "+sql);
		}
		return SUCCESS;
	}
	public String insertCode(JSONObject json,String zpgw2,String gwbm){
		RecordSet rs=new RecordSet();
		String name = json.getString(zpgw2);
		String code  = json.getString(gwbm);
		String sql="insert into uf_bmgwxx(jobtitlemark,jobtitlename,jobtitlecode,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)"
				+ "values('"+name+"','"+name+"','"+code+"','1','" +getDateString("yyyy-MM-dd", new Date()) + "','" +getDateString("HH:mm:ss", new Date()) + "','921','0')";
		rs.execute(sql);
		return sql;
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
