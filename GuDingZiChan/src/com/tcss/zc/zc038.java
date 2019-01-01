package com.tcss.zc;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc038.DT_FI_OA_AFS_OA038;
import out.deal.afs.zc038.DT_FI_OA_AFS_OA038_REP;
import out.deal.afs.zc038.HEADER_type0;
import out.deal.afs.zc038.ITEM_type0;
import out.deal.afs.zc038.MT_FI_OA_AFS_OA038;
import out.deal.afs.zc038.MT_FI_OA_AFS_OA038_REP;
import out.deal.afs.zc038.RETURN_type0;
import out.deal.afs.zc038.SIO_FI_OA_AFS_OA038Service;
import out.deal.afs.zc038.SIO_FI_OA_AFS_OA038ServiceStub;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
	
public class zc038 extends BaseBean{
    public JSONObject notarize(JSONObject json,JSONArray jsonarray){
    	 JSONObject jsonobject = new JSONObject();
    	 String username=getPropValue("tcsssaphr", "username_hr");
     	 String password=getPropValue("tcsssaphr", "password_hr");
     	 SIO_FI_OA_AFS_OA038Service stub ;
     	 try {
			stub = new SIO_FI_OA_AFS_OA038ServiceStub();
			HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
			bas.setUsername(username);
			bas.setPassword(password);
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
			DT_FI_OA_AFS_OA038 dt_FI_OA_AFS_OA038 = new DT_FI_OA_AFS_OA038();
			String cgdh = "";
	        for(int a = 0; a < jsonarray.size(); a++)
	        {
		        ITEM_type0 item_type0 = new ITEM_type0();
		        dt_FI_OA_AFS_OA038.addITEM(item_type0);
	        }			

		  ITEM_type0[] item_type0s = dt_FI_OA_AFS_OA038.getITEM();
		  writeLog("[jsonarray.size()--->] "+jsonarray.size());
		  writeLog("[item_type0s.lengt--->] "+item_type0s.length);
		  for (int b = 0; b < item_type0s.length; b++) 
		  {
			  ITEM_type0 item_type02 = item_type0s[b];
			  JSONObject detailData = jsonarray.getJSONObject(b);
			  if (b==0) 
			  {
				  cgdh = detailData.getString("cgdh037");//采购订单号取明细第一行
			  }

				  item_type02.setZOANU(json.getString("lsh"));//oa单号  主表
				  item_type02.setZITEM(String.valueOf(b+1));//oa单行号   明细表
				  item_type02.setEBELP(detailData.getString("sapcgddhh"));//采购订单行号   明细表
				  
				  item_type02.setZOATP(json.getString("djlx"));//oa单据类型  主表
				  item_type02.setZOATN(json.getString("djlxms"));//oa单据类型描述  主表
				  item_type02.setZOANO(json.getString("times"));//上传次数   主表  
				  item_type02.setZZBST(json.getString("ywlx038"));//业务类型   主表
				  item_type02.setZOAUU(json.getString("tdr_code"));//上传人账号   主表
				  item_type02.setZOAUM(json.getString("tdr_name"));//上传人姓名   主表
				  item_type02.setZDATE(json.getString("sqrq_"));//上传日期    主表
				  item_type02.setZTIMES(json.getString("scsjc"));//上传时间戳   主表
				  item_type02.setZOAUR(json.getString("sqr_code"));//申请人账号   主表 
				  item_type02.setZOATU(json.getString("sqr_name"));//申请人姓名  主表
				  item_type02.setZOAUN(json.getString("sqrq_"));//起草日期   主表
				  dt_FI_OA_AFS_OA038.setITEM(item_type0s); 
			  
		  }
		    HEADER_type0 header_type0 = new HEADER_type0();
		    String shrq = json.getString("shrq");
		    header_type0.setBUDAT(shrq.substring(0,4)+shrq.substring(5,7)+shrq.substring(8));//收货日期  主表
		    header_type0.setPO_NUMBER(cgdh);//采购订单号  明细表
		    dt_FI_OA_AFS_OA038.setHEADER(header_type0);
		    
			MT_FI_OA_AFS_OA038 mt_FI_OA_AFS_OA038 = new MT_FI_OA_AFS_OA038();
			mt_FI_OA_AFS_OA038.setMT_FI_OA_AFS_OA038(dt_FI_OA_AFS_OA038);
			
			MT_FI_OA_AFS_OA038_REP mt_FI_OA_AFS_OA038_REP = stub.sIO_FI_OA_AFS_OA038(mt_FI_OA_AFS_OA038);
			DT_FI_OA_AFS_OA038_REP dt_FI_OA_AFS_OA038_REP = mt_FI_OA_AFS_OA038_REP.getMT_FI_OA_AFS_OA038_REP();
			
			
			RecordSet rs = new RecordSet();
				RETURN_type0 return_tpye = new RETURN_type0();
				dt_FI_OA_AFS_OA038_REP.addRETURN(return_tpye);
			RETURN_type0[] return_type0s = dt_FI_OA_AFS_OA038_REP.getRETURN();
			 writeLog("[return_type0s.lengt--->] "+return_type0s.length);
			for (int d = 0; d < return_type0s.length; d++) {
				RETURN_type0 return_type0 = return_type0s[d];
				String e_msg = return_type0.getE_MSG();//消息
				String e_subrc = return_type0.getE_SUBRC();//消息类型
				String mblnr = return_type0.getMBLNR();//物料凭证号
				String zeile = return_type0.getZEILE();//物料凭证行号
				writeLog("038消息："+e_msg+"  消息类型："+e_subrc+"  物料凭证号："+mblnr+"  物料凭证行号："+zeile);
				if(e_subrc.equals("S")){
						String sql1 = "update formtable_main_101 set wlpzh = '"+mblnr+"',wlpzhh = '"+zeile+"',xiaoxi038='"+e_msg+"',xxlx038='"+e_subrc+"' "
								+ "where requestid='"+json.getString("requestid")+"' ";
						rs.execute(sql1);
					jsonobject.put("e_msg", e_msg);
					jsonobject.put("e_subrc", e_subrc);
				}else{
					jsonobject.put("e_msg", e_msg);
					jsonobject.put("e_subrc", e_subrc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonobject;
    }
}
