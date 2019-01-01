package com.fdrj;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class ReverseBorrow_TH extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		String src = request.getRequestManager().getSrc();
        RecordSet rs = new RecordSet(); 
        JSONArray jsons = new JSONArray();
        if(src.equals("reject"))
        {//退回的时候返回在途审批金额
			jsons = executeZYcxje(request);
			if (jsons.size() > 0) {
				for (int i = 0; i < jsons.size(); i++) 
				{
					JSONObject json = jsons.getJSONObject(i);
					String dnxh = json.getString("单内序号");
					String bccxje = Util.null2String(json.getString("本次冲销金额"));
					String sywcxje = Util.null2String(json.getString("剩余未冲销金额"));
					rs.execute("select ztcxje from formtable_main_104_dt1 where id = '"+dnxh+"'");
					String yuanshiztcxje = null;
					if (rs.next()) 
					{
						yuanshiztcxje = Util.null2String(rs.getString("ztcxje"));
					}
					writeLog("相减后的在途金额："+(Double.parseDouble(yuanshiztcxje)-Double.parseDouble(bccxje)));
                    String sql5 = "update formtable_main_104_dt1 set ztcxje = '"+(double)Math.round((Double.parseDouble(yuanshiztcxje)-Double.parseDouble(bccxje))*100)/100+"'"
 		            + ",sywcxje='"+(double)Math.round((Double.parseDouble(sywcxje))*100)/100+"' where id = '"+dnxh+"'";
				    writeLog("[sql2------->  ] "+sql5);
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
        	String cxjk = Util.null2String(rs.getString("sfcxjksq"));
        	if (cxjk == "0" || cxjk.equals("0")) 
        	{
        		String jkje = Util.null2String(rs.getString("jkje"));
        		String bxje = Util.null2String(rs.getString("bxjeskbb"));
        		String dnxh = Util.null2String(rs.getString("dlxh"));
        		String gllc = Util.null2String(rs.getString("glxcxjksqd"));
        		String bccxje = Util.null2String(rs.getString("bccxje"));
        		String sywcxje = Util.null2String(rs.getString("sywcxje"));
        		jsonobject.put("剩余未冲销金额", sywcxje);
        		jsonobject.put("本次冲销金额", bccxje);
        		jsonobject.put("借款金额", jkje);
        		jsonobject.put("报销金额", bxje);
        		jsonobject.put("单内序号", dnxh);
        		jsonobject.put("关联流程", gllc);
        		jsonarray.add(jsonobject);
        		new BaseBean().writeLog("----》"+jsonarray);
			}
        	else
        	{
        		new BaseBean().writeLog("不需要冲销借款");
        	}
		}
        return jsonarray;
	}
}
