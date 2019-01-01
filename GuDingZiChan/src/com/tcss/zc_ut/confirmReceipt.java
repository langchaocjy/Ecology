package com.tcss.zc_ut;

import java.util.Map;
import com.tcss.zc.zc038;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class confirmReceipt extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo ri) {
		writeLog("[----��ʼȷ���ջ�----]");
		String requestid = ri.getRequestid();
		zc038 zc038 = new zc038();
		WISap_2_util sap_2_util = new WISap_2_util();
		try 
		{
			JSONObject mainData = sap_2_util.getMainData(requestid);
			mainData.put("requestid", requestid);
			String sqrq = mainData.getString("sqrq");//�ϴ�����   sqrq
			sqrq = sqrq.substring(0,4)+sqrq.substring(5,7)+sqrq.substring(8);
			mainData.put("sqrq_", sqrq);//�ϴ����������
			String date = sap_2_util.date();
	       	mainData.put("scsjc", date);//�ϴ�ʱ���
	       	String tdr = mainData.getString("tdr");
	       	String sqr = mainData.getString("sqr");
	       	Map map = null;
	       	try {
	       		map = sap_2_util.seek(tdr);//���
	       		mainData.put("tdr_code", map.get("code"));
	       		mainData.put("tdr_name",map.get("name"));
				map = sap_2_util.seek(sqr);//������
				mainData.put("sqr_code", map.get("code"));
				mainData.put("sqr_name", map.get("name"));
			} catch (Exception e) {
				writeLog("[038���Ի�ȡ��˺�������ʧ�ܣ������ǿ�ָ��]");
				e.printStackTrace();
			}
			writeLog("[038��ȡ���ֶ����ݽ���--��]"+mainData);
			
			JSONArray detailData = sap_2_util.getDetailData(requestid, 1);
			writeLog("[038��ȡ��ϸ�ֶν���--��]"+detailData);
			JSONObject message = zc038.notarize(mainData, detailData);
				String msg = message.getString("e_msg");
				String subrc = message.getString("e_subrc");
				if(!subrc.equals("S")){
					ri.getRequestManager().setMessageid(System.currentTimeMillis()+"");
					ri.getRequestManager().setMessagecontent("ȷ���ջ�ʧ�ܣ�"+msg);
				}
				else{
					writeLog("ȷ���ջ��ɹ�");
				}
		} 
		catch (Exception e) 
		{
             writeLog("[---���г�����---]");
             e.printStackTrace();
		}
		
		writeLog("[----����ȷ���ջ�----]");
				return SUCCESS;
	}
	
}
