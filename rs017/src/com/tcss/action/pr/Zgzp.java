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
 * ������Ƹ����ְԱ
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
								json.put("����", rs.getString("departmentcode"));
						    }
						}else if(name1.equals("gwsxrq")){
							String b = Util.null2String(properties[j].getValue());//2018-07-11
							String date = b.substring(0, 4)+b.substring(5,7)+b.substring(8);
							json.put("��λ��Ч����", date);
						}else if(name1.equals("zpgw2")){
							String c = Util.null2String(properties[j].getValue());
							json.put("��Ƹ��λ", c);
						}else if(name1.equals("hxzz")){
							String d = Util.null2String(properties[j].getValue());
							json.put("����ְ��", d);
						}else if(name1.equals("rztj")){
							String e = Util.null2String(properties[j].getValue());
							json.put("��ְ����", e);
						}else{
						}
				 }
				 String requestid = ri.getRequestid();
				 json.put("����id", requestid);
				 rs019.setFPost(json);
				 break;
			}
		}
		return SUCCESS;
	}
}
