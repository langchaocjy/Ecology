package com.tcss.action.pr;

import com.tcss.report.rs019;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
/*
 * Author 陈嘉源
 * 异动审批单——职员的创岗和写入核心职责任职条件
 * 
 * 写入：申请日期，岗位编码，核心职责，任职条件
 * 创岗：岗位生效日期，核心职责，任职条件，部门，招聘岗位
 * */
public class ydspd_zy extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		RecordSet rs = new RecordSet();
		String requestid = request.getRequestid();
		String tablename = request.getRequestManager().getBillTableName();
		String sql1 = "select sfxzgw from "+tablename+" where requestid = '"+requestid+"'";
		rs.execute(sql1);
		String label = null,sqrq=null,bm=null,zpgw=null,hxzz=null,rztj=null,bmcode=null;
        if (rs.next()) {
			label = Util.null2String(rs.getString("sfxzgw"));
			if (label == "0") 
			{
				sqrq = Util.null2String(rs.getString("sqrq"));
				sqrq  = sqrq.substring(0, 4)+sqrq.substring(5,7)+sqrq.substring(8);
				bm = Util.null2String(rs.getString("ydhbm"));
				zpgw = Util.null2String(rs.getString("ydhcg"));
				hxzz = Util.null2String(rs.getString("ydhhxzz"));
				rztj = Util.null2String(rs.getString("ydhrztj"));
			}
		}		
        if (label == "0") {
            String sql2 = "select departmentcode from hrmdepartment where id = '"+bm+"'";
            rs.execute(sql2);
            if(rs.next())
            {
            	bmcode = Util.null2String(rs.getString("departmentcode"));
            }
            JSONObject json =new JSONObject();
            json.put("岗位生效日期", sqrq);
            json.put("核心职责", hxzz);
            json.put("任职条件", rztj);
            json.put("部门", bmcode);
            json.put("招聘岗位", zpgw);
            json.put("请求id", requestid);
            json.put("formtable", tablename);
            rs019.setFPost(json);
		}
		return SUCCESS;
	}

}
