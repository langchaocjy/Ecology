package com.tcss.zc_ut;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class Control_sql_Status_sqr extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		writeLog("[---��ʼ�ж����ύ�����˻أ�ִ�иı�ױ�״̬����---]");
		WISap_2_util sap_2_util=new WISap_2_util();
		String requestid = request.getRequestid();
		String src = request.getRequestManager().getSrc();
		JSONArray detailData = sap_2_util.getDetailData(requestid, 1);
		RecordSet rs = new RecordSet();
		for (int i = 0; i < detailData.size(); i++) 
		{
			JSONObject json = detailData.getJSONObject(i);
			String zcbm = json.getString("zcbm");
			    if(!src.equals("reject"))
			    {
					String sql1 = "update uf_zcsqcgcx_dt1 set cgbs=1 where zcbm='"+zcbm+"'";
					writeLog("[�ύʱ���µױ��sql--��]"+sql1);
					rs.execute(sql1);
			    }
			    if (src.equals("reject")) 
			    {
					String sql2 = "update uf_zcsqcgcx_dt1 set cgbs=0 where zcbm='"+zcbm+"'";
					writeLog("[�˻�ʱ���µױ��sql--��]"+sql2);
					rs.execute(sql2);
				}
		}
        writeLog("[---�����ı�ױ�״̬����---]");
		return SUCCESS;
	}
}
