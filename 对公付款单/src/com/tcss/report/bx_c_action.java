package com.tcss.report;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class bx_c_action extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		writeLog("[------------------------>]  "+request.getRequestManager().getBillTableName());
        try {
            RecordSet rs=new RecordSet();
            String requestid = request.getRequestid();
            String formtable = request.getRequestManager().getBillTableName();
            String alldepartment = "";
            String allsjbm = "";
			String mainid = null;
			int rowindex = 0; 
			rs.execute("select id from "+formtable+" where requestid='"+requestid+"'");
			writeLog("[msg ===========> sql1: ]"+"select id from "+formtable+" where requestid='"+requestid+"'");
			if(rs.next()){
                mainid = Util.null2String(rs.getString("id"));
            }
            rs.execute("select lcspbm,sjbm from "+formtable+"_dt1 where mainid = "+mainid);
			writeLog("[msg ===========> sql2: ]"+"select lcspbm,sjbm from "+formtable+"_dt1 where mainid = "+mainid);
            while(rs.next()){
            	String bumen =  Util.null2String(rs.getString("lcspbm"));
            	String sjbm = Util.null2String(rs.getString("sjbm"));
            	if (rowindex == 0 && bumen != "" && sjbm != "") 
            	{
					alldepartment = bumen ; 
					allsjbm = sjbm ;
				}
            	if (rowindex != 0 && bumen != "" && sjbm != "")
            	{
                    alldepartment = alldepartment + "," +bumen;
                    allsjbm = allsjbm + "," + sjbm;
				}
            	rowindex ++ ;
            }
            writeLog("[msg ============> sql3: ]"+"update  "+formtable+" set mxbmhz = "+alldepartment+",mxsjbmhz='"+allsjbm+"' where requestid='"+requestid+"'");
            rs.execute("update "+formtable+" set mxbmhz = '"+alldepartment+"',mxsjbmhz='"+allsjbm+"' where requestid='"+requestid+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return SUCCESS;
	}
}
