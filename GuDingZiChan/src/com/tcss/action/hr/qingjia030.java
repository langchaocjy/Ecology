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
		
		User usr = requestinfo.getRequestManager().getUser();//��ȡ��ǰ�����û�����
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
			requestinfo.getRequestManager().setMessagecontent("��ٵ�д��ʧ�ܣ�"+flag);
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
				head_type02.setBdlb(mainData.getString("bdlb"));//�����
				head_type02.setBdMS(mainData.getString("bdmc"));//������
				head_type02.setZOANU(mainData.getString("lsh"));//��ˮ��
				head_type02.setZOAUU(workcode);//�ϴ��˹���
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
				item_type02.setZOANU(mainData.getString("lsh"));//oa���� ����
				item_type02.setZITEM(String.valueOf(i+1));//oa�к� ��ϸ��
				item_type02.setPERNR(mainData.getString("sqrgh"));//Ա������  ����
				item_type02.setBEGDA(deleteH(mainData.getString("sqrq")));//��ʼ���� ����
				item_type02.setLGART(js.getString("qjlxmz"));//�������  ��ϸ��
				item_type02.setZPAYH(js.getString("bcqjss"));//ʱ��  ��ϸ��
				item_type02.setZPAYD(js.getString("ts"));//����,�Ӱ������������������ϸ�� 
				item_type02.setZYEARMONTH(js.getString("nf")+js.getString("yf"));//�����·�  ��ϸ��
				item_type02.setAEDAT(deleteH(mainData.getString("scrq")));//��������  ����
				item_type02.setZXJBS(js.getString("xjbs"));// ��ϸ��
				item_type02.setBK1(js.getString("yl1"));//Ԥ���ֶ�1  ��ϸ�� 
				item_type02.setBK2(js.getString("yl2"));//Ԥ���ֶ�2  ��ϸ��
			    afs_OA030.setITEM(item_type0s);
			}
			
			MT_OA_AFS_OA030 afs_OA0302=new MT_OA_AFS_OA030();
			afs_OA0302.setMT_OA_AFS_OA030(afs_OA030);
			
			MT_OA_AFS_OA030_REP afs_OA030_REP=stub.sIO_OA_AFS_OA030(afs_OA0302);
			DT_OA_AFS_OA030_REP afs_OA030_REP2=afs_OA030_REP.getMT_OA_AFS_OA030_REP();
			String msg = afs_OA030_REP2.getMSG();//���ص���Ϣ
			String type = afs_OA030_REP2.getTYPE();//��Ϣ����
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
					String msg_s = detail_type02.getMSG();//����
					String type_s = detail_type02.getTYPE();//����
					String zitem = detail_type02.getZITEM();//oa�к�
					String zoanu=detail_type02.getZOANU();//oa����
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
