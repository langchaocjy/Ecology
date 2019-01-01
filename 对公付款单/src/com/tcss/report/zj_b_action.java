package com.tcss.report;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class zj_b_action extends BaseBean implements Action{
	@Override
	  public String execute(RequestInfo reqInfo) {
	  new BaseBean().writeLog("[------------------------>]  "+reqInfo.getRequestManager().getBillTableName());
      try {
          RecordSet rs=new RecordSet();
          String requestid = reqInfo.getRequestid();
          String formtable = reqInfo.getRequestManager().getBillTableName();
          String alldepartment = "";
			String mainid = null;
			int rowindex = 0; 
			rs.execute("select id from "+formtable+" where requestid='"+requestid+"'");
			new BaseBean().writeLog("[czblog==sql11:  ]  "+"select id from "+formtable+" where requestid='"+requestid+"'");
			if(rs.next()){
              mainid = Util.null2String(rs.getString("id"));
          }
          rs.execute("select lcspbm from "+formtable+"_dt1 where mainid = "+mainid);
	      new BaseBean().writeLog("[czblog==sql22:  ]  "+"select lcspbm from "+formtable+"_dt1 where mainid = "+mainid);
          while(rs.next()){
          	String bumen =  Util.null2String(rs.getString("lcspbm"));
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
          new BaseBean().writeLog("[czblog==sql33:  ]  "+"update  "+formtable+" set mxbmhz = "+alldepartment+" where requestid='"+requestid+"'");
          rs.execute("update "+formtable+" set mxbmhz = '"+alldepartment+"' where requestid='"+requestid+"'");
      } catch (Exception e) {
          e.printStackTrace();
      }
      return "1";
  }
}
