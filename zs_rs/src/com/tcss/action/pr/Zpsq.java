package com.tcss.action.pr;
import com.tcss.report.rs016;
import com.weaver.general.BaseBean;

import net.sf.json.JSONObject;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @author 陈嘉源
 *写入核心职责和任职条件
 */
public class Zpsq extends BaseBean implements Action {
	@Override
	public String execute(RequestInfo ri) {
		JSONObject json = new JSONObject();
		Property[] properties=ri.getMainTableInfo().getProperty();
		for(int j =0;j<properties.length;j++){
			String zd =properties[j].getName();
			String zdv = properties[j].getValue();
			if(zd.equals("zplb")&&(zdv.equals("1")||zdv.equals("2"))){
				for(int i =0; i<properties.length;i++){
					String name=properties[i].getName();
					if(name.equals("gwbm")){
						String value = Util.null2String(properties[i].getValue());
						json.put("岗位编码", value);
					}else if(name.equals("sqrq")){
						String value = Util.null2String(properties[i].getValue());
						String date = value.substring(0,4)+value.substring(5, 7)+value.substring(8);
						json.put("申请日期", date);
					}else if(name.equals("hxzz")){
						String value = Util.null2String(properties[i].getValue());
						json.put("核心职责", value);
					}else if(name.equals("rztj")){
						String value = Util.null2String(properties[i].getValue());
						json.put("任职条件", value);
					}
				}
				new rs016().setRs016(json);
			}
		}
		return SUCCESS;
	}
}
