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
				  cgdh = detailData.getString("cgdh037");//�ɹ�������ȡ��ϸ��һ��
			  }

				  item_type02.setZOANU(json.getString("lsh"));//oa����  ����
				  item_type02.setZITEM(String.valueOf(b+1));//oa���к�   ��ϸ��
				  item_type02.setEBELP(detailData.getString("sapcgddhh"));//�ɹ������к�   ��ϸ��
				  
				  item_type02.setZOATP(json.getString("djlx"));//oa��������  ����
				  item_type02.setZOATN(json.getString("djlxms"));//oa������������  ����
				  item_type02.setZOANO(json.getString("times"));//�ϴ�����   ����  
				  item_type02.setZZBST(json.getString("ywlx038"));//ҵ������   ����
				  item_type02.setZOAUU(json.getString("tdr_code"));//�ϴ����˺�   ����
				  item_type02.setZOAUM(json.getString("tdr_name"));//�ϴ�������   ����
				  item_type02.setZDATE(json.getString("sqrq_"));//�ϴ�����    ����
				  item_type02.setZTIMES(json.getString("scsjc"));//�ϴ�ʱ���   ����
				  item_type02.setZOAUR(json.getString("sqr_code"));//�������˺�   ���� 
				  item_type02.setZOATU(json.getString("sqr_name"));//����������  ����
				  item_type02.setZOAUN(json.getString("sqrq_"));//�������   ����
				  dt_FI_OA_AFS_OA038.setITEM(item_type0s); 
			  
		  }
		    HEADER_type0 header_type0 = new HEADER_type0();
		    String shrq = json.getString("shrq");
		    header_type0.setBUDAT(shrq.substring(0,4)+shrq.substring(5,7)+shrq.substring(8));//�ջ�����  ����
		    header_type0.setPO_NUMBER(cgdh);//�ɹ�������  ��ϸ��
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
				String e_msg = return_type0.getE_MSG();//��Ϣ
				String e_subrc = return_type0.getE_SUBRC();//��Ϣ����
				String mblnr = return_type0.getMBLNR();//����ƾ֤��
				String zeile = return_type0.getZEILE();//����ƾ֤�к�
				writeLog("038��Ϣ��"+e_msg+"  ��Ϣ���ͣ�"+e_subrc+"  ����ƾ֤�ţ�"+mblnr+"  ����ƾ֤�кţ�"+zeile);
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
