package com.rs;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;

public class RsUtil {
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
	  
	  public JSONArray getDetailData(String requestid, int index)
	  {
	    JSONArray jsons = new JSONArray();
	    RecordSet rs = new RecordSet();
	    String tableName = getTableName(requestid);
	    String sql = "select * from " + tableName + "_dt" + index + " where mainid in( select id from " + tableName + " where  requestid='" + requestid + "') order by id ";
	    rs.execute(sql);
	    while (rs.next())
	    {
	      JSONObject json = new JSONObject();
	      String[] columnName = rs.getColumnName();
	      for (int i = 0; i < columnName.length; i++)
	      {
	        String name = columnName[i].toLowerCase();
	        String value = rs.getString(name);
	        json.put(name, value);
	      }
	      jsons.add(json);
	    }
	    return jsons;
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
