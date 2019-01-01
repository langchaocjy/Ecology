package com.tcss.action.pr;

import com.tcss.report.rs019;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 增岗招聘——普工
 * */
public class zgzp_pg extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo ri) {
		JSONObject json = new JSONObject();
		String formtable = ri.getRequestManager().getBillTableName();
		json.put("formtable", formtable);
		RecordSet rs = new RecordSet();
		Property[] properties = ri.getMainTableInfo().getProperty();
		for(int i = 0; i < properties.length; i++){
			String name = properties[i].getName();
			if(name.equals("cj")){
				String a = Util.null2String(properties[i].getValue());
				rs.executeSql("select * from hrmdepartment where id = '"+a+"'");
				if(rs.next()){
					json.put("部门", rs.getString("departmentcode"));
				}
			}else if(name.equals("xzgw")){
				String b = Util.null2String(properties[i].getValue());
				json.put("招聘岗位", b);
			}else if(name.equals("gwsxrq")){
				String c = Util.null2String(properties[i].getValue());
				String date = c.substring(0, 4)+c.substring(5,7)+c.substring(8);
				json.put("岗位生效日期", date);
			}else if(name.equals("zyzz")){
				String d = Util.null2String(properties[i].getValue());
				json.put("核心职责", d);
			}else if(name.equals("rzyq")){
				String e = Util.null2String(properties[i].getValue());
				json.put("任职条件", e);
			}else{
				
			}
		}
		 String requestid = ri.getRequestid();
		 json.put("请求id", requestid);
		rs019.setFPost(json);
		return SUCCESS;
	}

}
