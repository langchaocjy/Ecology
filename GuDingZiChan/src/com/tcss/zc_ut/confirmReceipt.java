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
		writeLog("[----开始确认收货----]");
		String requestid = ri.getRequestid();
		zc038 zc038 = new zc038();
		WISap_2_util sap_2_util = new WISap_2_util();
		try 
		{
			JSONObject mainData = sap_2_util.getMainData(requestid);
			mainData.put("requestid", requestid);
			String sqrq = mainData.getString("sqrq");//上传日期   sqrq
			sqrq = sqrq.substring(0,4)+sqrq.substring(5,7)+sqrq.substring(8);
			mainData.put("sqrq_", sqrq);//上传和起草日期
			String date = sap_2_util.date();
	       	mainData.put("scsjc", date);//上传时间戳
	       	String tdr = mainData.getString("tdr");
	       	String sqr = mainData.getString("sqr");
	       	Map map = null;
	       	try {
	       		map = sap_2_util.seek(tdr);//填单人
	       		mainData.put("tdr_code", map.get("code"));
	       		mainData.put("tdr_name",map.get("name"));
				map = sap_2_util.seek(sqr);//申请人
				mainData.put("sqr_code", map.get("code"));
				mainData.put("sqr_name", map.get("name"));
			} catch (Exception e) {
				writeLog("[038尝试获取填单人和申请人失败，可能是空指针]");
				e.printStackTrace();
			}
			writeLog("[038获取主字段数据结束--》]"+mainData);
			
			JSONArray detailData = sap_2_util.getDetailData(requestid, 1);
			writeLog("[038获取明细字段结束--》]"+detailData);
			JSONObject message = zc038.notarize(mainData, detailData);
				String msg = message.getString("e_msg");
				String subrc = message.getString("e_subrc");
				if(!subrc.equals("S")){
					ri.getRequestManager().setMessageid(System.currentTimeMillis()+"");
					ri.getRequestManager().setMessagecontent("确认收货失败："+msg);
				}
				else{
					writeLog("确认收货成功");
				}
		} 
		catch (Exception e) 
		{
             writeLog("[---运行出错了---]");
             e.printStackTrace();
		}
		
		writeLog("[----结束确认收货----]");
				return SUCCESS;
	}
	
}
