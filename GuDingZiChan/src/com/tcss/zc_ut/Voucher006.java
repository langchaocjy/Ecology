package com.tcss.zc_ut;

import java.util.ArrayList;
import java.util.Map;

import com.tcss.zc.zc006;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class Voucher006 extends BaseBean implements Action{
	private zc006 voucher = null;

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		
		int usrid = requestinfo.getRequestManager().getUserId();
		String workflowid = requestinfo.getWorkflowid();
		RecordSet rs = new RecordSet();
		int nownodeid=-2;
		rs.execute("select nownodeid from workflow_nownode where requestid='"+requestid+"'");
		if (rs.next()) {
			nownodeid=Util.getIntValue(rs.getString("nownodeid"), -2);
		}
		
		writeLog("requestid: "+requestid+"  workflowid:"+workflowid+"  当前节点id:"+nownodeid+"  用户id："+usrid);
		rs.execute("select currentnodeid from workflow_requestbase where requestid='"+requestid+"' and workflowid='"+workflowid+"'");
		boolean flag =false;
		if (rs.next()) {
			int currentnodeid=Util.getIntValue(rs.getString("currentnodeid"), -1);
			writeLog("currentnodeid: "+currentnodeid);
			if (currentnodeid==nownodeid) {
				flag=true;
			}
		}
		String workcode="",currentname="";
		if (flag) {
			rs.execute("select workcode,lastname from hrmresource  where id = '"+usrid+"'");
			if (rs.next()) {
				workcode = Util.null2String(rs.getString("workcode"));
				currentname = Util.null2String(rs.getString("lastname"));
			}
		}
		
		WISap_2_util sap_2_util = new WISap_2_util();
		JSONObject mainData=sap_2_util.getMainData(requestid);
		mainData.put("nodeoperators", workcode);
		mainData.put("currentname", currentname);
		mainData.put("scsjc", sap_2_util.date());
		
		int times006 = Util.getIntValue(mainData.getString("times006"), 0);
		times006+=1;
		rs.execute("update formtable_main_187 set times006='"+times006+"' where requestid='"+requestid+"'");
		mainData.put("times006",times006);
		
		JSONArray detailData=sap_2_util.getDetailData(requestid, 2);
		if (null == voucher) {
			voucher = new zc006(detailData,mainData);
			writeLog(voucher.toString());
		}
		
		ArrayList<Map<String,String>> map = voucher.getList();
		for (Map<String, String> map2 : map) {
		     if (map2.get("v_subrc").equals("N")) {
				requestinfo.getRequestManager().setMessagecontent("生成凭证失败："+map2.get("v_message")+map2.get("message"));
				requestinfo.getRequestManager().setMessageid("消息类："+map2.get("id")+"  消息编号："+map2.get("number"));
				requestinfo.getRequestManager().setMessageType("消息类型："+map2.get("type"));
			}
		}
		
		return SUCCESS;
	}

}
