package com.tcss.zc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import com.tcss.zc_ut.E_msg_util;
import com.tcss.zc_ut.ReadCard;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc024.DATA_type0;
import out.deal.afs.zc024.DATA_type1;
import out.deal.afs.zc024.DT_FI_OA_AFS_OA024;
import out.deal.afs.zc024.DT_FI_OA_AFS_OA024_REP;
import out.deal.afs.zc024.MT_FI_OA_AFS_OA024;
import out.deal.afs.zc024.MT_FI_OA_AFS_OA024_REP;
import out.deal.afs.zc024.SIO_FI_OA_AFS_OA024Service;
import out.deal.afs.zc024.SIO_FI_OA_AFS_OA024ServiceStub;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class zc024 extends BaseBean{
    public JSONObject setAssetInformation(JSONObject json){
//    	JSONArray jsonarray = new JSONArray();
    	JSONObject json_return = new JSONObject();
    	writeLog("����zc024��json    "+json);
    	String username=getPropValue("tcsssaphr", "username_hr");
    	String password=getPropValue("tcsssaphr", "password_hr");
    	SIO_FI_OA_AFS_OA024Service stub;
    	try {
    	    stub=new SIO_FI_OA_AFS_OA024ServiceStub();
			HttpTransportPropertiesImpl.Authenticator basicauthor = new HttpTransportPropertiesImpl.Authenticator();
			basicauthor.setUsername(username);
			basicauthor.setPassword(password);
			((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicauthor);
			
			DT_FI_OA_AFS_OA024 dt_FI_OA_AFS_OA024 = new DT_FI_OA_AFS_OA024();
			DATA_type1 da= new DATA_type1();
			dt_FI_OA_AFS_OA024.addDATA(da);
			DATA_type1[] datas = dt_FI_OA_AFS_OA024.getDATA();
			for(int i = 0;i < datas.length; i++){
				DATA_type1 data = datas[i];
					data.setZOANU(json.getString("lsh"));
					data.setBUKRS(json.getString("gsdm"));
					data.setZOATP(json.getString("bdlx"));
					data.setZOATN(json.getString("bdmc"));
					data.setZOAUU(json.getString("cwczr_code"));
					data.setZOAUM(json.getString("cwczr_name"));
					data.setZDATE(json.getString("sqrq_"));
					data.setZTIMES(json.getString("scsjc"));
					data.setZOAUR(json.getString("tdr_code"));
					data.setZOATU(json.getString("tdr_name"));
					data.setZOAUN(json.getString("sqrq_"));
					data.setANLKL(json.getString("zclx"));
					
					data.setTXT50(json.getString("zcmc"));
					if (json.getString("xlhmj").equals("") && !json.getString("mj").equals("") && json.getString("yt").equals("")) {
						data.setSERNR(json.getString("mj"));//���
					}else if(json.getString("mj").equals("") && !json.getString("xlhmj").equals("") && json.getString("yt").equals("")){
						data.setSERNR(json.getString("xlhmj"));//���к�
					}else if(!json.getString("yt").equals("") && json.getString("xlhmj").equals("") && json.getString("mj").equals("")){
						data.setSERNR(json.getString("yt"));//��;
					}else{
						data.setSERNR("");
					}
					if(!json.getString("gg").equals("") && json.getString("ydzj").equals("")){
						data.setTXA50(json.getString("gg"));//���
					}else if(!json.getString("ydzj").equals("") && json.getString("gg").equals("")){
						data.setTXA50(json.getString("ydzj"));//�¶����
					}else{
						data.setTXA50("");
					}
					data.setANLHTXT(json.getString("dz"));//����ַ
					data.setORD42(json.getString("cs"));//����
					data.setKFZKZ(json.getString("zzbh"));//ִ�ձ��
					data.setEAUFN(json.getString("nbdd"));//�ڲ�����
					data.setKOSTLV(json.getString("zrcbzx"));//���γɱ�����
					data.setKOSTL(json.getString("cbzx"));//�ɱ�����
					data.setPRCTR(json.getString("lrzx"));//��������
					data.setGSBER(json.getString("ywfw"));//ҵ��Χ
					data.setPERNR(json.getString("rybh"));//��Ա���
					data.setZOANO(json.getString("times"));//�ϴ�����
					data.setMEINS(json.getString("jldw"));//������λ
					data.setZZBST(json.getString("ywlx"));//ҵ������
					data.setRAUMN(json.getString("cfdd"));//��ŵص�
					data.setNUM02(json.getString("hh"));//�к�
					data.setNDJAR(json.getString("kzzjnx"));//���̯����
					data.setNDPER(json.getString("kjzjyf"));//���̯����
					data.setNDJAR_S(json.getString("sfzjnx"));//˰��̯����
					data.setNDPER_S(json.getString("sfzjyf"));//˰��̯����
					data.setZYZZJ(json.getString("ydzj"));//�������
			}
			dt_FI_OA_AFS_OA024.setDATA(datas);
			MT_FI_OA_AFS_OA024 mt_FI_OA_AFS_OA024 = new MT_FI_OA_AFS_OA024();
			mt_FI_OA_AFS_OA024.setMT_FI_OA_AFS_OA024(dt_FI_OA_AFS_OA024);
			
			MT_FI_OA_AFS_OA024_REP mt_FI_OA_AFS_OA024_REP = stub.sIO_FI_OA_AFS_OA024(mt_FI_OA_AFS_OA024);
			DT_FI_OA_AFS_OA024_REP dt_FI_OA_AFS_OA024_REP = mt_FI_OA_AFS_OA024_REP.getMT_FI_OA_AFS_OA024_REP();
			DATA_type0[] das = dt_FI_OA_AFS_OA024_REP.getDATA();
		    RecordSet rs = new RecordSet();
			for(int j = 0; j<das.length; j++){
				DATA_type0 dat = das[j];
				String num02 = Util.null2String(dat.getNUM02());//�к�
				String v_anln1 = Util.null2String(dat.getV_ANLN1());//�ʲ�����
				String v_message = Util.null2String(dat.getV_MESSAGE());//ʧ����Ϣ
				String v_subrc = Util.null2String(dat.getV_SUBRC());//�ɹ���ʧ��
				String zoanu = Util.null2String(dat.getZOANU());//oa����
				
				if(v_subrc == "S" || v_subrc.equals("S")){
				    String sql2 ="update FORMTABLE_MAIN_170_DT1 set zcbh ='"+v_anln1+"'"
				    + "where mainid in(select id from FORMTABLE_MAIN_170 where requestid = '"+json.getString("requestid")+"') and mxhh='"+json.getString("hh")+"'";
				    writeLog("[zc024-->sql2   ]"+sql2);
				    rs.execute(sql2);
				    json_return.put("zoanu", zoanu);
				    json_return.put("v_subrc", v_subrc);
				    json_return.put("num02", num02);
				    json_return.put("v_message", v_message);
				}else{
					json_return.put("zoanu", zoanu);
					json_return.put("num02", num02);
					json_return.put("v_subrc", v_subrc);
					json_return.put("v_message", v_message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json_return;
    } 
}
