package com.customization;

import com.weaver.general.Util;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class ComboCode extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		JSONObject mainData = getMainData(requestid);
		int wfqyzt = Util.getIntValue(mainData.getString("wfqyzt"), -1);
		int	htlx= Util.getIntValue(mainData.getString("htlx"), -1);
		String name1 = getSwi(htlx,1);
		String name2 = getSwi(wfqyzt,2);
		String sqrq = Util.null2String(mainData.getString("sqrq")).replace("-", "").substring(0,6);
		String lcbh = Util.null2String(mainData.getString("lcbh"));
		int len = lcbh.length();//222-333-222-003
		String ends = lcbh.substring(len-3, len);
		String code = name1+"-"+name2+"-"+sqrq+"-"+ends;
		
		RecordSet rs = new RecordSet();
		rs.execute("update formtable_main_39 set lcbh = '"+code+"' where requestid='"+requestid+"'");
		return SUCCESS;
	}
    public static String getSwi(int i,int k){
    	String name = "";
    	if (k==2) {
        	switch (i) {
    		case 0:
    			name="YM";
    			break;

    		case 1:
    			name="YMGZ";
    			break;

    		case 2:
    			name="BJ";
    			break;

    		case 3:
    			name="BJGZ";
    			break;

    		case 4:
    			name="FM";
    			break;

    		case 5:
    			name="YG";
    			break;

    		case 6:
    			name="LXJ";
    			break;

    		case 7:
    			name="MY";
    			break;

    		case 8:
    			name="GZLXJ";
    			break;

    		case 9:
    			name="KY";
    			break;

    		case 10:
    			name="JB";
    			break;

    		case 11:
    			name="BY";
    			break;

    		case 12:
    			name="BYGZ";
    			break;

    		default:
    			break;
    		}
		}else{
			switch (i) {
			case 0:
				name="1";
				break;
			case 1:
				name="2";
				break;
			case 2:
				name="3";
				break;
			case 3:
				name="4";
				break;
			case 4:
				name="0";
				break;
			default:
				break;
			}
		}

		return name;
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
