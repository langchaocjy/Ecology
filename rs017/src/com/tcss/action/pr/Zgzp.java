package com.tcss.action.pr;

import com.tcss.report.rs019;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;

/*
 * 增岗招聘——职员
 * */
public class Zgzp extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo ri) {
		JSONObject json = new JSONObject();
		String formtable = ri.getRequestManager().getBillTableName();
		json.put("formtable", formtable);
		RecordSet rs = new RecordSet();
		Property[] properties = ri.getMainTableInfo().getProperty();
		for(int i = 0; i < properties.length; i++){
			if(properties[i].getName().equals("zplb") && properties[i].getValue().equals("0")){	
				 for(int j = 0 ; j < properties.length; j++){
					 String name1 = properties[j].getName();
					    if(name1.equals("xqbm")){
							String a = Util.null2String(properties[j].getValue());
							rs.executeSql("select * from hrmdepartment where id = '"+a+"'");
							if(rs.next()) {
								json.put("部门", rs.getString("departmentcode"));
						    }
						}else if(name1.equals("gwsxrq")){
							String b = Util.null2String(properties[j].getValue());//2018-07-11
							String date = b.substring(0, 4)+b.substring(5,7)+b.substring(8);
							json.put("岗位生效日期", date);
						}else if(name1.equals("zpgw2")){
							String c = Util.null2String(properties[j].getValue());
							json.put("招聘岗位", c);
						}else if(name1.equals("hxzz")){
							String d = Util.null2String(properties[j].getValue());
							json.put("核心职责", d);
						}else if(name1.equals("rztj")){
							String e = Util.null2String(properties[j].getValue());
							json.put("任职条件", e);
						}else{
						}
				 }
				 String requestid = ri.getRequestid();
				 json.put("请求id", requestid);
				 rs019.setFPost(json);
				 break;
			}
		}
		return SUCCESS;
	}
}
