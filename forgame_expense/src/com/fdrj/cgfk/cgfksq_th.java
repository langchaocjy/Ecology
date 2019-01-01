package com.fdrj.cgfk;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class cgfksq_th extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo request) {
		String src = request.getRequestManager().getSrc();
        RecordSet rs = new RecordSet(); 
        JSONArray jsons = new JSONArray();
        if(src.equals("reject"))
        {
        	jsons = executeZYcxje(request);
        	if (jsons.size()>0) {
				for (int i = 0; i < jsons.size(); i++) {
					JSONObject json = jsons.getJSONObject(i);
					String dnxh = json.getString("�������");
					String bccxje = Util.null2String(json.getString("���γ������").replaceAll(",", ""));
					String sywcxje = Util.null2String(json.getString("ʣ��δ�������").replace(",", ""));
					rs.execute("select ztcxje from formtable_main_110_dt1 where id='"+dnxh+"'");
					String yuanshiztcxje = null;
					if (rs.next()) {
						yuanshiztcxje = Util.null2String(rs.getString("ztcxje").replace(",", ""));
					}
					writeLog("��������;��"+(Double.parseDouble(yuanshiztcxje)-Double.parseDouble(bccxje)));
					String sql5 = "update formtable_main_110_dt1 set ztcxje = '"+(double)Math.round((Double.parseDouble(yuanshiztcxje)-Double.parseDouble(bccxje))*100)/100+"'"
		 		            + ",sywcxje='"+(double)Math.round((Double.parseDouble(sywcxje))*100)/100+"' where id = '"+dnxh+"'";
						    writeLog("[sql5�˻�ʱ���²ɹ����뵥------->  ] "+sql5);
						    rs.execute(sql5);
				}
			}
        }
		return SUCCESS;
	}
	public static JSONArray executeZYcxje(RequestInfo request)
	{   
		JSONObject jsonobject = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		String requestid = request.getRequestid();
		String tablename = request.getRequestManager().getBillTableName();
		RecordSet rs = new RecordSet();
        String sql = "select * from "+tablename+"_dt1 a,"+tablename+" b where a.mainid = b.id and requestid = "+requestid;
        new BaseBean().writeLog("[sql-------->   ]   "+sql);
        rs.execute(sql);
        while (rs.next()) 
        {
        		String cgsqje = Util.null2String(rs.getString("cgsqje"));
        		String fkje = Util.null2String(rs.getString("fkje"));
        		String dnxh = Util.null2String(rs.getString("dnxh"));
        		String gllc = Util.null2String(rs.getString("glcgsqd"));
        		String bccxje = Util.null2String(rs.getString("bccxje"));
        		String sywcxje = Util.null2String(rs.getString("sywcxje"));
        		jsonobject.put("ʣ��δ�������", sywcxje);
        		jsonobject.put("���γ������", bccxje);
        		jsonobject.put("�ɹ�������", cgsqje);
        		jsonobject.put("������", fkje);
        		jsonobject.put("�������", dnxh);
        		jsonobject.put("��������", gllc);
        		jsonarray.add(jsonobject);
        		new BaseBean().writeLog("----��"+jsonarray);
		}
        return jsonarray;
	}

}
