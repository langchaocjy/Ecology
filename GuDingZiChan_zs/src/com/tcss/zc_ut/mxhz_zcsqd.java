package com.tcss.zc_ut;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
/**
 * ��ϸ����-�ʲ����뵥
 * author:�¼�Դ
 * */
public class mxhz_zcsqd extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
        try {
            RecordSet rs=new RecordSet();
            String requestid = request.getRequestid();
            String formtable = request.getRequestManager().getBillTableName();
            String alldepartment = "";
            String allsjbm = "";
			int rowindex = 0; 

			String sql1 = "select lcspbm,sjbm from "+formtable+"_dt1 where mainid in(select id from "+formtable+" where requestid='"+requestid+"')";
            rs.execute(sql1);
			writeLog("[������ϸ��lcspbm����ֶ�===========> sql1: ]"+sql1);
            while(rs.next()){
            	String bumen =  Util.null2String(rs.getString("lcspbm"));
            	String sjbm = Util.null2String(rs.getString("sjbm"));
            	if (rowindex == 0 && bumen != "") 
            	{
					alldepartment = bumen ; 
				}
            	if (rowindex != 0 && bumen != "")
            	{
                    alldepartment = alldepartment + "," +bumen;
				}
            	if (rowindex == 0 && sjbm != "") 
            	{
				    allsjbm = sjbm;	
				}
            	if (rowindex != 0 && sjbm != "") 
            	{
					allsjbm = allsjbm + "," + sjbm;
				}
            	rowindex ++ ;
            }
            String sql2 = "update "+formtable+" set mxbmhz = '"+alldepartment+"',sjbmhz='"+allsjbm+"' where requestid='"+requestid+"'";
            writeLog("[������������ֶ�============> sql2: ]"+sql2);
            rs.execute(sql2);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return SUCCESS;
	}

}
