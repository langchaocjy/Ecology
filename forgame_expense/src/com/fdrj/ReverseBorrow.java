package com.fdrj;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class ReverseBorrow extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		String src = request.getRequestManager().getSrc();
		String requestidid = request.getRequestid();
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
                    String sql5 = "update formtable_main_104_dt1 set ztcxje = '"+(Double.parseDouble(yuanshiztcxje)-Double.parseDouble(bccxje))+"'"
 		            + ",sywcxje='"+(Double.parseDouble(sywcxje))+"' where id = '"+dnxh+"'";
				    writeLog("[sql2------->  ] "+sql5);
				    rs.execute(sql5);
				}
			}
        }
        else
        {
        	jsons = executeZYcxje(request);
        	writeLog("[jsons -------> ]  "+jsons);
        	if (jsons.size() > 0) {
        		for (int i = 0; i < jsons.size(); i++) {
        			JSONObject json = jsons.getJSONObject(i);
        			String dnxh = Util.null2String(json.getString("单内序号"));
        			String jkje = Util.null2String(json.getString("借款金额"));
        			String bxje = Util.null2String(json.getString("报销金额"));
        			String requestid = Util.null2String(json.getString("关联流程"));
        			String sywcxje = Util.null2String(json.getString("剩余未冲销金额"));
        			writeLog("[value ------》 ] " +dnxh+" "+jkje+" "+bxje+" "+requestid+"  "+sywcxje);
        			
        			//日常个人费用报销单的单内序号是借款申请单的明细id
					rs.execute("select ztcxje from formtable_main_104_dt1 where id = '"+dnxh+"'");
					String yuanshiztcxje = "0.00";
					if(rs.next())
					{  //从借款申请单获取在途冲销金额，后面需要相加
						yuanshiztcxje = Util.null2String(rs.getString("ztcxje"));//获取在途冲销金额
						if(yuanshiztcxje == "" || yuanshiztcxje.equals(""))//如果在途为0
						{
							yuanshiztcxje = "0.00";
						}
					}
        			if (jkje != "" && !jkje.equals("")) 
        			{
        				String gather  = Double.parseDouble(jkje) > Double.parseDouble(bxje) ? bxje : jkje;//判断是用报销金额还是借款金额
        				Double gather2 = Double.parseDouble(sywcxje)-Double.parseDouble(gather);//剩余未冲销金额和本次冲销金额对比
        				if ( gather2 <= 0 ) 
        				{
							gather2 = 0.00;//如果小于等于0，借款申请单那边的剩余未冲销就为0
						}
        				Double gather3 = 0.00;
        				if ( gather2 > 0) 
        				{
        					 //因为借款单那边的在途冲销金额+剩余未冲销=借款金额，gather2>0的情况下相加不会超过借款金额
        					 gather3 = Double.parseDouble(gather)+Double.parseDouble(yuanshiztcxje);//新的在途冲销金额
						}
        				else
        				{
        					//gather2<0,如果用gather+yuanshiztcxje肯定就超过借款金额了，这样的“本次冲销金额”最多也只能冲销“剩余未冲销金额”这么多钱
        					 gather3 = Double.parseDouble(sywcxje)+Double.parseDouble(yuanshiztcxje);
        				}
        				
        				//相加的在途冲销金额不能大于借款总金额
        				if (gather3 > Double.parseDouble(jkje)) 
        				{
							gather3 = Double.parseDouble(jkje);
						}

        					String sql4 = "update formtable_main_104_dt1 set ztcxje = '"+(double)Math.round(gather3*100)/100+"',sywcxje='"+(double)Math.round(gather2*100)/100+"' where id = '"+dnxh+"'";
        					writeLog("[sql4------->  ] "+sql4);
        					rs.execute(sql4);
        					String sql5 = "update formtable_main_106_dt1 set sjbxje='"+(double)Math.round((gather3-Double.parseDouble(yuanshiztcxje))*100)/100+"' where dlxh='"+dnxh+"' and mainid in(select id from formtable_main_106 where requestid='"+requestidid+"')";
        					rs.execute(sql5);
        			}
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
