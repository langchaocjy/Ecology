package com.tcss.action.pr;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
/**
 * 异动审批三个单通用代码
 * */
public class gaibiankonggangbiaozhi extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		FlowUtil flowUtil = new FlowUtil();
		JSONObject mainData = flowUtil.getMainData(requestid);
		int formid=requestinfo.getRequestManager().getFormid();

        if (formid==-205 || formid==-179 || formid==-180) {
        	R_yidong_tochange(requestinfo, mainData, "gwbm", "gwbm1");
		} else if (formid==-173){
			String zplb=mainData.getString("zplb");
			if (zplb.equals("1")||zplb.equals("2")) {
				R_zhaopin_tochange(requestinfo,mainData,"gwbm");
			}
		} else if(formid==-177) {
			R_ruzhi_tochange(requestinfo,mainData,"gwbm");
		}
		return SUCCESS;
	}
	public void R_ruzhi_tochange(RequestInfo requestinfo,JSONObject mainData,String gwbm){
		RecordSet rs = new RecordSet();
		String gb = Util.null2String(mainData.getString(gwbm));
		boolean flag = false;
		if (!gb.equals("")) {
			rs.execute("select kgbs,oasybs from uf_bmgwxx where jobtitlecode='"+gb+"'");
			if (rs.next()) {
				String kgbs = rs.getString("kgbs");
				String oasybs = rs.getString("oasybs");
				if (!kgbs.equals("") && oasybs.equals("")) {
					flag = true;
				}else{
					flag = false;
				    requestinfo.getRequestManager().setMessageid(System.currentTimeMillis()+"");
				    requestinfo.getRequestManager().setMessagecontent("该岗位已被占用");
				}
			}
			
			if (flag) {
				rs.execute("update uf_bmgwxx set oasybs='"+requestinfo.getRequestid()+"' where jobtitlecode='"+gb+"'");
			}
		}
	}
    public void R_zhaopin_tochange(RequestInfo requestinfo,JSONObject mainData,String gwbm){
		int lastid = requestinfo.getRequestManager().getLastNodeid();
		int nowid=  requestinfo.getRequestManager().getNodeid();
		writeLog("招聘  ------》lastid :  "+lastid+ "  nowid:"+nowid);
		RecordSet rs = new RecordSet();
		String gb = Util.null2String(mainData.getString(gwbm));
		
		boolean flag = false;
		if (lastid != nowid && !gb.equals("")) {  //如果操作id不相等，说明是退回到申请人节点
			rs.execute("update uf_bmgwxx set oasybs='' where jobtitlecode='"+gb+"'");
		}
		
		if (lastid == nowid && !gb.equals("")) {
			rs.execute("select kgbs,oasybs from uf_bmgwxx where jobtitlecode='"+gb+"'");
			if (rs.next()) {
				String kgbs = rs.getString("kgbs");
				String oasybs = rs.getString("oasybs");
				if (!kgbs.equals("") && oasybs.equals("")) {
					flag = true;
				}else{
					flag = false;
				    requestinfo.getRequestManager().setMessageid(System.currentTimeMillis()+"");
				    requestinfo.getRequestManager().setMessagecontent("该岗位已被占用");
				}
			}
			
			if (flag) {
				rs.execute("update uf_bmgwxx set oasybs='"+requestinfo.getRequestid()+"' where jobtitlecode='"+gb+"'");
			}
		}
    }
	public void R_yidong_tochange(RequestInfo requestinfo,JSONObject mainData,String gwbm,String gwbm1){
		int lastid = requestinfo.getRequestManager().getLastNodeid();
		int nowid=  requestinfo.getRequestManager().getNodeid();
		writeLog("异动-------》lastid :  "+lastid+ "  nowid:"+nowid);
		
		RecordSet rs = new RecordSet();
		String gb = Util.null2String(mainData.getString(gwbm));//异动前岗位编码
		String gb1= Util.null2String(mainData.getString(gwbm1));//异动后岗位编码
	    writeLog("gb: "+gb+" gb1:"+gb1);
	    
		boolean flag = false;
		if (lastid != nowid && !gb.equals("") && !gb1.equals("")) {  //如果操作id不相等，说明是退回到申请人节点
			rs.execute("update uf_bmgwxx set oasybs='' where jobtitlecode='"+gb1+"'");
		}
		
		if (lastid == nowid && !gb.equals("")) {
			rs.execute("update uf_bmgwxx set oasybs='' where jobtitlecode='"+gb+"'");
			rs.execute("select kgbs,oasybs from uf_bmgwxx where jobtitlecode='"+gb1+"'");
			if (rs.next()) {
				String kgbs = rs.getString("kgbs");
				String oasybs = rs.getString("oasybs");
				if (!kgbs.equals("") && oasybs.equals("")) {
					flag = true;
				}else{
					flag = false;
				    requestinfo.getRequestManager().setMessageid(System.currentTimeMillis()+"");
				    requestinfo.getRequestManager().setMessagecontent("该岗位已被占用");
				}
			}
			
			if (flag) {
				rs.execute("update uf_bmgwxx set oasybs='"+requestinfo.getRequestid()+"' where jobtitlecode='"+gb1+"'");
			}
		}
		
	} 
}
