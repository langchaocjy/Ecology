package com.fdrj.cgfk;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class cgfksq_gd 
extends BaseBean 
implements Action{

	@Override
	public String execute(RequestInfo request) {
        RecordSet rs1 = new RecordSet(),rs2 = new RecordSet();
        JSONArray jsons = cgfksq_gd.executeZYcxje(request);
        if (jsons.size() > 0) {
			for (int i = 0; i < jsons.size(); i++) {
				JSONObject json = jsons.getJSONObject(i);
				String dnxh = Util.null2String(json.getString("�������"));
				String cgsqje=Util.null2String(json.getString("�ɹ�������").replace(",",""));//�����
				String sjbxje=Util.null2String(json.getString("ʵ�ʱ������").replace(",", ""));
				
				String sql1 = "select ztcxje,ycxje from formtable_main_110_dt1 where id = '"+dnxh+"'";
				writeLog("[sql1��ɹ����뵥������--��]"+sql1);
				rs1.execute(sql1);
				while(rs1.next()) {
					String ztcxje = Util.null2String(rs1.getString("ztcxje").replace(",",""));
					String ycxje = Util.null2String(rs1.getString("ycxje").replace(",", ""));
					writeLog("[�鵵�ڵ�zaitu+yichongxiao   ]  "+ztcxje+"---"+ycxje+"====");
					if (ycxje == "" || ycxje.equals("")) {
						ycxje="0.00";
					}
					writeLog("[ʵ�ʵı������---�� ] " +sjbxje);
					Double gather1 = Double.parseDouble(sjbxje) + Double.parseDouble(ycxje);//Ҫ���µ��ѳ�������ֶε�ֵ,ʵ�ʱ������+�ѳ������
                    Double gather2 = Double.parseDouble(ztcxje)	- Double.parseDouble(sjbxje);//Ҫ���µ���;��������ֶε�ֵ����;�������-ʵ�ʱ������
                    if (gather1 >= Double.parseDouble(cgsqje)) 
                    {
						gather1 = Double.parseDouble(cgsqje);  //����ѳ��������ڽ����͵��ڽ���
					}
                    if (gather2 <= 0) 
                    {
						gather2 = 0.00;
					}
                    String sql2 = "update formtable_main_110_dt1 set ycxje = '"+(double)Math.round(gather1*100)/100+"',ztcxje = '"+(double)Math.round(gather2*100)/100+"'  where id = '"+dnxh+"'";
        			writeLog("[sql2------->]"+sql2);
        			rs2.execute(sql2);
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
        new BaseBean().writeLog("[��ȡ�ɹ��������뵥������-------->   ]   "+sql);
        rs.execute(sql);
        while (rs.next()) 
        {
        		String cgsqje = Util.null2String(rs.getString("cgsqje"));
        		String fkje = Util.null2String(rs.getString("fkje"));
        		String dnxh = Util.null2String(rs.getString("dnxh"));
        		String gllc = Util.null2String(rs.getString("glcgsqd"));
        		String sjbxje = Util.null2String(rs.getString("sjbxje"));
        		jsonobject.put("ʵ�ʱ������", sjbxje);
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
