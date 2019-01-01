package com.tcss.zc_ut;

import java.util.Map;

import com.tcss.zc.zc037;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
/*
 * �ʲ��ɹ�����������
 * */
public class createForm extends BaseBean implements Action{
	private static zc037 zc = new zc037();
	private static WISap_2_util wiSap_2_util = new WISap_2_util();
	public String execute(RequestInfo ri) {
		writeLog("[-------037�ӿڿ�ʼ��������-------]");
		String requestid = ri.getRequestid();
			try 
			{
				JSONObject mainData = wiSap_2_util.getMainData(requestid);
				mainData.put("requestid", requestid);
				String sqrq = mainData.getString("sqrq");
				sqrq = sqrq.substring(0,4)+sqrq.substring(5,7)+sqrq.substring(8);
				mainData.put("sqrq_", sqrq);
				String date = wiSap_2_util.date();
				mainData.put("scsjc", date);
				String tdr = mainData.getString("tdr");
				String sqr = mainData.getString("sqr");
				Map map = null;
				try 
				{
					map = wiSap_2_util.seek(tdr);//���
					mainData.put("tdr_code", map.get("code"));
					mainData.put("tdr_name",map.get("name"));
					map = wiSap_2_util.seek(sqr);//������
					mainData.put("sqr_code", map.get("code"));
					mainData.put("sqr_name", map.get("name"));
				}
				catch (Exception e) 
				{
					writeLog("[037���Ի�ȡ��˺�������ʧ�ܣ������ǿ�ָ��]");
					e.printStackTrace();
				}
				mainData.put("zoast", 1);
				int times = Integer.parseInt(mainData.getString("times"));
				times += 1;
				mainData.put("times", times);
				writeLog("[037��ȡ���ֶ����ݽ���--��]"+mainData);
				
				JSONArray detailData = wiSap_2_util.getDetailData(requestid, 1);
				writeLog("[037��ȡ��ϸ�ֶ����ݽ���--��]"+detailData);
				JSONObject message = zc.createOrder(mainData, detailData);
				writeLog("[message===?]"+message);
				String msg = message.getString("e_msg");
				String subrc = message.getString("e_subrc");
				RecordSet rs = new RecordSet();
				if (subrc.equals("S")) 
				{
					String sql3 = "update formtable_main_101 set times='"+mainData.getString("times")+"' "
							+ "where requestid = '"+requestid+"'";
					writeLog("[sql3---<>]"+sql3);
					rs.execute(sql3);
				}
				if (!subrc.equals("S")) 
				{
					ri.getRequestManager().setMessageid(System.currentTimeMillis()+"");
					ri.getRequestManager().setMessagecontent("��������ʧ�ܣ�"+msg);
				} 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			writeLog("[-------037�ӿڴ�����������-------]");
		
		return SUCCESS;
	}

}
