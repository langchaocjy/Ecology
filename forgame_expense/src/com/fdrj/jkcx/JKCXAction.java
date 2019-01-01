package com.fdrj.jkcx;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class JKCXAction extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo request) {
		try {
            RecordSet rs=new RecordSet();
            RecordSet rs2=new RecordSet();
            String requestid = request.getRequestid();
            String formtable = request.getRequestManager().getBillTableName();
            String mainid = null,glxcxjksqd=null;
            String sql1 = "select id from "+formtable+" where requestid = "+requestid;
            rs.execute(sql1);
            if (rs.next()) 
            {
				mainid = Util.null2String(rs.getString("id"));
			    glxcxjksqd = Util.null2String(rs.getString("glxcxjksqd"));
			}
            String sql2 = "select jkje,bxjeskbb,dlxh from "+formtable+"_dt1 where mainid ='"+mainid+"' and order by dlxh"; 
            rs.execute(sql2);
            while(rs.next())
            {
            	int ztcxje = 0;
            	String dlxh = Util.null2String(rs.getString("dlxh"));
            	String jkje = Util.null2String(rs.getString("jkje").replaceAll(",",""));
            	if(jkje != "" && !jkje.equals(""))
            	{
            		String bxje = Util.null2String(rs.getString("bxjeskbb").replaceAll(",", ""));
            		if(Integer.parseInt(jkje) > Integer.parseInt(bxje))
            		{
            			ztcxje = Integer.parseInt(bxje);
            		}
            		else
            		{
            			ztcxje = Integer.parseInt(jkje);
            		}
            		String sql3 = "select id from formtable_main_104 where mainid ="+glxcxjksqd;
            		rs2.execute(sql3);
            		String sql4 = "update formtable_main_104_dt1 set ztcxje='"+ztcxje+"',ycxje='0' "
            				+ "where dlxh='"+dlxh+"' and mainid"+Util.null2String(rs2.getString("id"));
            		rs2.execute(sql4);
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
