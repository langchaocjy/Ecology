package com.tcss.report;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class TiTopReturnAction extends BaseBean implements Action {
    /**
     * 
     */
    public String execute(RequestInfo reqInfo) {
        try {
            RecordSet rs=new RecordSet();
            String alldepartment = "";
			int mainid = 0;
			int rowindex = 0; 
			rs.execute("select id from formtable_main_157 where requestid='"+reqInfo.getRequestid()+"'");
			writeLog("czblog==sql11:"+"select id from formtable_main_74 where requestid='"+reqInfo.getRequestid()+"'");
			if(rs.next()){
                mainid = Util.getIntValue("id");
            }
            rs.execute("select bumen from formtable_main_157_dt1 where mainid = "+mainid);
			writeLog("czblog==sql22:"+"select bumen from formtable_main_157_dt1 where mainid = "+mainid);
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
                writeLog("czblog==alldepartment:"+alldepartment);
            }
            
            writeLog("czblog==sql33:"+"update  formtable_main_74 set mxbmhz = "+alldepartment+" where requestid='"+reqInfo.getRequestid()+"'");
            rs.execute("update  formtable_main_157 set mxbmhz = "+alldepartment+" where requestid='"+reqInfo.getRequestid()+"'");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }
}
