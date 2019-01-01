package com.tcss.report;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class bx_action extends BaseBean implements Action{
	@Override
    public String execute(RequestInfo reqInfo) {
        try {
            RecordSet rs=new RecordSet();
            String alldepartment = "";
			String mainid = null;
			int rowindex = 0; 
			rs.execute("select id from formtable_main_112 where requestid='"+reqInfo.getRequestid()+"'");
			writeLog("czblog==sql11:"+"select id from formtable_main_112 where requestid='"+reqInfo.getRequestid()+"'");
			if(rs.next()){
                mainid = rs.getString("id");
            }
            rs.execute("select bumen from formtable_main_112_dt1 where mainid = "+mainid);
			writeLog("czblog==sql22:"+"select bumen from formtable_main_112_dt1 where mainid = "+mainid);
            while(rs.next()){
            	String bumen =  Util.null2String(rs.getString("bumen"));
            	if (rowindex == 0 && bumen != "") 
            	{
					alldepartment = bumen ; 
				}
            	if (rowindex != 0 && bumen != "")
            	{
                    alldepartment = alldepartment + "," +bumen;
				}
            	rowindex ++ ;
            }
            writeLog("czblog==sql33:"+"update  formtable_main_112 set mxbmhz = "+alldepartment+" where requestid='"+reqInfo.getRequestid()+"'");
            rs.execute("update formtable_main_112 set mxbmhz = '"+alldepartment+"' where requestid='"+reqInfo.getRequestid()+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }

}