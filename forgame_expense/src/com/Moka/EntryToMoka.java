package com.Moka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hr.util.MokaUtil;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class EntryToMoka extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo request) {
		String requestid  = request.getRequestid();
		JSONObject mainData = getMainData(requestid);
		int sqid = mainData.getInt("sqid");
		int hcid = mainData.getInt("hcid");
		String rzrq = mainData.getString("rzrq");
		int syq = mainData.getInt("syq");
		
		MokaUtil mokaUtil = new MokaUtil();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(rzrq);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mokaUtil.hired(sqid, hcid, syq, date);
		return SUCCESS;
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
