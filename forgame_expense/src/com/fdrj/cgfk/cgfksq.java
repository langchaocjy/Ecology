package com.fdrj.cgfk;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * �ɹ��������뵥108
 * �ɹ����뵥110
 * */
public class cgfksq extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		String rid = request.getRequestid();
        RecordSet rs = new RecordSet();
        JSONArray jsons = executeZYcxje(request);
        	if (jsons.size() > 0) {
        		for (int i = 0; i < jsons.size(); i++) {
        			JSONObject json = jsons.getJSONObject(i);
        			String dnxh = Util.null2String(json.getString("�������"));
        			String cgsqje = Util.null2String(json.getString("�ɹ�������").replace(",","")); // �����Ӳɹ����뵥������������ҽ��
        			String sywcxje = Util.null2String(json.getString("ʣ��δ�������").replace(",",""));
        			String rmbje = Util.null2String(json.getString("����ҽ��").replace(",", ""));
        			
        			writeLog("[dnxh:]"+dnxh+"[cgsqje:]"+cgsqje+"[sywcxje:]"+sywcxje+"[rmbje:]"+rmbje);
        			
					rs.execute("select ztcxje from formtable_main_110_dt1 where id = '"+dnxh+"'");//�Ӳɹ����뵥��ԭʼ��;�������
					String yuanshiztcxje = null;
					if(rs.next())
					{
						yuanshiztcxje = Util.null2String(rs.getString("ztcxje"));
						//��ȡ�ɹ����뵥����;�������
						if(yuanshiztcxje == "" || yuanshiztcxje.equals(""))
						{
							yuanshiztcxje = "0.00";
						}
					}
					
					writeLog("[yuanshiztcxje:]"+yuanshiztcxje);
					
        			if (cgsqje != "" && !cgsqje.equals("")) {
        				String gather = Double.parseDouble(sywcxje) > Double.parseDouble(rmbje) ? rmbje : sywcxje;//���γ���������ܴ���ʣ��δ�������
        				Double gather2= Double.parseDouble(sywcxje) - Double.parseDouble(gather);//Ҫ���µ��ɹ����뵥��ʣ��δ�������
        				
        				writeLog("[gather:]"+gather+"[gather2:]"+gather2);
        				
        				if (gather2 <= 0) {
							gather2 = 0.00;
						}
        				Double gather3 = 0.00;//�µ���;�������
        				if (gather2 > 0) {//˵������ʣ��δ�������
							gather3 = Double.parseDouble(gather) + Double.parseDouble(yuanshiztcxje);//���γ������+�ɹ����뵥ԭ�е���;�������
						} else {	//��ʣ��δ����-Ҫ�����ý��<0ʱ��������ܳ�����ʣ��δ�������Ľ��������ʱ����ԭʼ��;���+ʣ��δ����������׼ȷ��
                            gather3 = Double.parseDouble(sywcxje) + Double.parseDouble(yuanshiztcxje);
						}
        				if (gather3 > Double.parseDouble(cgsqje)) {
							gather3 = Double.parseDouble(cgsqje);
						}
        				
        				writeLog("[gather3:]"+gather3);
        				
        				String sql4 = "update formtable_main_110_dt1 set ztcxje = '"+(double)Math.round(gather3*100)/100+"',sywcxje = '"+(double)Math.round(gather2*100)/100+"' where id ='"+dnxh+"'";//���²ɹ����뵥
        				writeLog("[���²ɹ����뵥--��]"+sql4);
        				rs.execute(sql4);
        				String sql5 = "update formtable_main_108_dt1 set sjbxje='"+(double)Math.round((gather3-Double.parseDouble(yuanshiztcxje))*100)/100+"' "
        						+ "where dnxh='"+dnxh+"' and mainid in(select id from formtable_main_108 where requestid ='"+rid+"')";
        				writeLog("[���²ɹ��������뵥--��]"+sql5);
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
        rs.execute(sql);
        while (rs.next()) 
        {
        		String cgsqje = Util.null2String(rs.getString("cgsqje"));//�ɹ�������
        		String dnxh = Util.null2String(rs.getString("dnxh"));//�������
        		String gllc = Util.null2String(rs.getString("glcgsqd"));//�����ɹ����뵥
        		String bccxje = Util.null2String(rs.getString("bccxje"));//���γ������
        		String sywcxje = Util.null2String(rs.getString("sywcxje"));//ʣ��δ�������
        		String rmbje = Util.null2String(rs.getString("rmbje"));//����ҽ��
        		jsonobject.put("����ҽ��", rmbje);
        		jsonobject.put("ʣ��δ�������", sywcxje);
        		jsonobject.put("���γ������", bccxje);
        		jsonobject.put("�ɹ�������", cgsqje);
        		jsonobject.put("�������", dnxh);
        		jsonobject.put("��������", gllc);
        		jsonarray.add(jsonobject);
		}
        return jsonarray;
	}
}
