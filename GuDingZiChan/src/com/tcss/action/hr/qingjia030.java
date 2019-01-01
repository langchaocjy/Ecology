package com.tcss.action.hr;
import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import com.tcss.zc_ut.WISap_2_util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.kq030.DETAIL_type0;
import out.deal.afs.kq030.DT_OA_AFS_OA030;
import out.deal.afs.kq030.DT_OA_AFS_OA030_REP;
import out.deal.afs.kq030.HEAD_type0;
import out.deal.afs.kq030.ITEM_type0;
import out.deal.afs.kq030.MT_OA_AFS_OA030;
import out.deal.afs.kq030.MT_OA_AFS_OA030_REP;
import out.deal.afs.kq030.SIO_OA_AFS_OA030Service;
import out.deal.afs.kq030.SIO_OA_AFS_OA030ServiceStub;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class qingjia030 extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		
		User usr = requestinfo.getRequestManager().getUser();//获取当前操作用户对象
		int id = usr.getUID();
		RecordSet rs = new RecordSet();
		rs.execute("select workcode from hrmresource  where id = '"+id+"'");
		String workcode="";
		if (rs.next()) {
			workcode = rs.getString("workcode");
		}
		writeLog("id:"+id+"  workcode:"+workcode);
		
		WISap_2_util sap_2_util = new WISap_2_util();
		JSONObject mainData=sap_2_util.getMainData(requestid);
		JSONArray detailData=sap_2_util.getDetailData(requestid, 1);
		String flag = inJiaban030(mainData,detailData,workcode,requestid);
		writeLog("flag:"+flag);
		String[] arr = flag.split("-");
		if (!arr[0].equals("S")) {
			requestinfo.getRequestManager().setMessageid(System.currentTimeMillis()+"");
			requestinfo.getRequestManager().setMessagecontent("请假单写入失败："+flag);
		}
		return SUCCESS;
	}
	public static String deleteH(String date){
		String[] arr = date.split("-");
		StringBuffer str5 = new StringBuffer();
		for (String s : arr) {
		    str5.append(s);
		}
		return str5.toString();
	}
	public String inJiaban030(JSONObject mainData,JSONArray detailData,String workcode,String requestid){
		writeLog("qingjia  mainData: "+mainData);
		writeLog("qingjia  detailData: " +detailData);
		String username=getPropValue("tcsssaphr", "username_hr");
    	String password=getPropValue("tcsssaphr", "password_hr");
    	SIO_OA_AFS_OA030Service stub;
    	String flag = "";
    	try {
			stub = new SIO_OA_AFS_OA030ServiceStub();
			HttpTransportPropertiesImpl.Authenticator basicauthor = new HttpTransportPropertiesImpl.Authenticator();
			basicauthor.setUsername(username);
			basicauthor.setPassword(password);
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicauthor);
			
			DT_OA_AFS_OA030 afs_OA030=new DT_OA_AFS_OA030();
			HEAD_type0 head_type0 = new HEAD_type0();
			afs_OA030.addHEAD(head_type0);
			HEAD_type0[] head_type0s=afs_OA030.getHEAD();
			for (int i = 0; i < head_type0s.length; i++) {
				HEAD_type0 head_type02=head_type0s[i];
				head_type02.setBdlb(mainData.getString("bdlb"));//表单类别
				head_type02.setBdMS(mainData.getString("bdmc"));//表单描述
				head_type02.setZOANU(mainData.getString("lsh"));//流水号
				head_type02.setZOAUU(workcode);//上传人工号
				afs_OA030.setHEAD(head_type0s);
			}
			
			int index = 0;
			for (int i = 0; i < detailData.size(); i++) {
				index = detailData.size();
				ITEM_type0 item_type0=new ITEM_type0();
				afs_OA030.addITEM(item_type0);
			}
			ITEM_type0[] item_type0s = afs_OA030.getITEM();
			for (int i = 0; i < item_type0s.length; i++) {
				ITEM_type0 item_type02=item_type0s[i];
				JSONObject js = detailData.getJSONObject(i);
				item_type02.setZOANU(mainData.getString("lsh"));//oa单号 主表
				item_type02.setZITEM(String.valueOf(i+1));//oa行号 明细表
				item_type02.setPERNR(mainData.getString("sqrgh"));//员工工号  主表
				item_type02.setBEGDA(deleteH(mainData.getString("sqrq")));//开始日期 主表
				item_type02.setLGART(js.getString("qjlxmz"));//事项类别  明细表
				item_type02.setZPAYH(js.getString("bcqjss"));//时数  明细表
				item_type02.setZPAYD(js.getString("ts"));//天数,加班天数在主表，请假在明细表 
				item_type02.setZYEARMONTH(js.getString("nf")+js.getString("yf"));//考勤月份  明细表
				item_type02.setAEDAT(deleteH(mainData.getString("scrq")));//更改日期  主表
				item_type02.setZXJBS(js.getString("xjbs"));// 明细表
				item_type02.setBK1(js.getString("yl1"));//预留字段1  明细表 
				item_type02.setBK2(js.getString("yl2"));//预留字段2  明细表
			    afs_OA030.setITEM(item_type0s);
			}
			
			MT_OA_AFS_OA030 afs_OA0302=new MT_OA_AFS_OA030();
			afs_OA0302.setMT_OA_AFS_OA030(afs_OA030);
			
			MT_OA_AFS_OA030_REP afs_OA030_REP=stub.sIO_OA_AFS_OA030(afs_OA0302);
			DT_OA_AFS_OA030_REP afs_OA030_REP2=afs_OA030_REP.getMT_OA_AFS_OA030_REP();
			String msg = afs_OA030_REP2.getMSG();//返回的消息
			String type = afs_OA030_REP2.getTYPE();//消息类型
			writeLog("msg: "+msg+"  type:"+type);
			
			RecordSet rs = new RecordSet();
			if (type.equals("S")) {
				flag = type;
				writeLog("type====S:   "+"update formtable_main_218 set xxlxhead='"+type+"' where requestid='"+requestid+"'");
				rs.execute("update formtable_main_218 set xxlxhead='"+type+"' where requestid='"+requestid+"'");
			} else if(type.equals("E")){
				for (int i = 0; i < index; i++) {
					DETAIL_type0 detail_type0=new DETAIL_type0();
					afs_OA030_REP2.addDETAIL(detail_type0);
				}
				DETAIL_type0[] detail_type0s = afs_OA030_REP2.getDETAIL();
				for (int i = 0; i < detail_type0s.length; i++) {
					DETAIL_type0 detail_type02=detail_type0s[i];
					String msg_s = detail_type02.getMSG();//内容
					String type_s = detail_type02.getTYPE();//类型
					String zitem = detail_type02.getZITEM();//oa行号
					String zoanu=detail_type02.getZOANU();//oa单号
					writeLog("msg_s: "+msg_s+" type_s:"+type_s+" zitem:"+zitem+" zoanu:"+zoanu);
					flag=type_s+"-"+msg_s+"-"+zitem+"-"+zoanu;
					JSONObject j_item = detailData.getJSONObject(i);
					rs.execute("update formtable_main_218 set xxlxhead='"+type+"' where requestid='"+requestid+"'");
					rs.execute("update formtable_main_218_dt1 set xxlx='"+type_s+"', xxnr='"+msg_s+"' where id='"+j_item.getString("id")+"'");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
