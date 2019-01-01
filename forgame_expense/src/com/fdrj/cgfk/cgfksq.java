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
 * 采购付款申请单108
 * 采购申请单110
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
        			String dnxh = Util.null2String(json.getString("单内序号"));
        			String cgsqje = Util.null2String(json.getString("采购申请金额").replace(",","")); // 借款金额，从采购申请单带过来的人民币金额
        			String sywcxje = Util.null2String(json.getString("剩余未冲销金额").replace(",",""));
        			String rmbje = Util.null2String(json.getString("人民币金额").replace(",", ""));
        			
        			writeLog("[dnxh:]"+dnxh+"[cgsqje:]"+cgsqje+"[sywcxje:]"+sywcxje+"[rmbje:]"+rmbje);
        			
					rs.execute("select ztcxje from formtable_main_110_dt1 where id = '"+dnxh+"'");//从采购申请单拿原始在途冲销金额
					String yuanshiztcxje = null;
					if(rs.next())
					{
						yuanshiztcxje = Util.null2String(rs.getString("ztcxje"));
						//获取采购申请单的在途冲销金额
						if(yuanshiztcxje == "" || yuanshiztcxje.equals(""))
						{
							yuanshiztcxje = "0.00";
						}
					}
					
					writeLog("[yuanshiztcxje:]"+yuanshiztcxje);
					
        			if (cgsqje != "" && !cgsqje.equals("")) {
        				String gather = Double.parseDouble(sywcxje) > Double.parseDouble(rmbje) ? rmbje : sywcxje;//本次冲销金额，最大不能大于剩余未冲销金额
        				Double gather2= Double.parseDouble(sywcxje) - Double.parseDouble(gather);//要更新到采购申请单的剩余未冲销金额
        				
        				writeLog("[gather:]"+gather+"[gather2:]"+gather2);
        				
        				if (gather2 <= 0) {
							gather2 = 0.00;
						}
        				Double gather3 = 0.00;//新的在途冲销金额
        				if (gather2 > 0) {//说明还有剩余未冲销金额
							gather3 = Double.parseDouble(gather) + Double.parseDouble(yuanshiztcxje);//本次冲销金额+采购申请单原有的在途冲销金额
						} else {	//当剩余未冲销-要冲销得金额<0时，它最多能冲销“剩余未冲销”的金额，所以这个时候是原始在途金额+剩余未冲销金额才是准确的
                            gather3 = Double.parseDouble(sywcxje) + Double.parseDouble(yuanshiztcxje);
						}
        				if (gather3 > Double.parseDouble(cgsqje)) {
							gather3 = Double.parseDouble(cgsqje);
						}
        				
        				writeLog("[gather3:]"+gather3);
        				
        				String sql4 = "update formtable_main_110_dt1 set ztcxje = '"+(double)Math.round(gather3*100)/100+"',sywcxje = '"+(double)Math.round(gather2*100)/100+"' where id ='"+dnxh+"'";//更新采购申请单
        				writeLog("[更新采购申请单--》]"+sql4);
        				rs.execute(sql4);
        				String sql5 = "update formtable_main_108_dt1 set sjbxje='"+(double)Math.round((gather3-Double.parseDouble(yuanshiztcxje))*100)/100+"' "
        						+ "where dnxh='"+dnxh+"' and mainid in(select id from formtable_main_108 where requestid ='"+rid+"')";
        				writeLog("[更新采购付款申请单--》]"+sql5);
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
        		String cgsqje = Util.null2String(rs.getString("cgsqje"));//采购申请金额
        		String dnxh = Util.null2String(rs.getString("dnxh"));//单内序号
        		String gllc = Util.null2String(rs.getString("glcgsqd"));//关联采购申请单
        		String bccxje = Util.null2String(rs.getString("bccxje"));//本次冲销金额
        		String sywcxje = Util.null2String(rs.getString("sywcxje"));//剩余未冲销金额
        		String rmbje = Util.null2String(rs.getString("rmbje"));//人民币金额
        		jsonobject.put("人民币金额", rmbje);
        		jsonobject.put("剩余未冲销金额", sywcxje);
        		jsonobject.put("本次冲销金额", bccxje);
        		jsonobject.put("采购申请金额", cgsqje);
        		jsonobject.put("单内序号", dnxh);
        		jsonobject.put("关联流程", gllc);
        		jsonarray.add(jsonobject);
		}
        return jsonarray;
	}
}
