package com.tcss.action.pr;
import com.tcss.report.rs016;
import com.weaver.general.BaseBean;

import net.sf.json.JSONObject;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @author �¼�Դ
 *д�����ְ�����ְ����
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
						json.put("��λ����", value);
					}else if(name.equals("sqrq")){
						String value = Util.null2String(properties[i].getValue());
						String date = value.substring(0,4)+value.substring(5, 7)+value.substring(8);
						json.put("��������", date);
					}else if(name.equals("hxzz")){
						String value = Util.null2String(properties[i].getValue());
						json.put("����ְ��", value);
					}else if(name.equals("rztj")){
						String value = Util.null2String(properties[i].getValue());
						json.put("��ְ����", value);
					}
				}
				new rs016().setRs016(json);
			}
		}
		return SUCCESS;
	}
}
