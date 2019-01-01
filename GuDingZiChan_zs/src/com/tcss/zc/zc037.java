package com.tcss.zc;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.zc037.DT_FI_OA_AFS_OA037;
import out.deal.afs.zc037.DT_FI_OA_AFS_OA037_REP;
import out.deal.afs.zc037.ITEM_type0;
import out.deal.afs.zc037.ITEM_type1;
import out.deal.afs.zc037.MT_FI_OA_AFS_OA037;
import out.deal.afs.zc037.MT_FI_OA_AFS_OA037_REP;
import out.deal.afs.zc037.SIO_FI_OA_AFS_OA037Service;
import out.deal.afs.zc037.SIO_FI_OA_AFS_OA037ServiceStub;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class zc037 extends BaseBean{
   public JSONObject createOrder(JSONObject mainData,JSONArray detailData){
	   JSONObject jsonobject = new JSONObject();
	   String username=getPropValue("tcsssaphr", "username_hr");
   	   String password=getPropValue("tcsssaphr", "password_hr");
	   SIO_FI_OA_AFS_OA037Service stub;
	   try {
		  stub = new SIO_FI_OA_AFS_OA037ServiceStub();
		  HttpTransportPropertiesImpl.Authenticator bas = new HttpTransportPropertiesImpl.Authenticator();
		  bas.setUsername(username);
		  bas.setPassword(password);
		  ((Stub) stub)._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, bas);
		  DT_FI_OA_AFS_OA037 dt_FI_OA_AFS_OA037 = new DT_FI_OA_AFS_OA037();
		  dt_FI_OA_AFS_OA037.setBUKRS(mainData.getString("gsdm"));//公司代码
		  dt_FI_OA_AFS_OA037.setLIFNR(mainData.getString("gys"));//供应商编码
		  dt_FI_OA_AFS_OA037.setEKORG(mainData.getString("ywfw"));//品牌业务范围
		  dt_FI_OA_AFS_OA037.setZOANU(mainData.getString("lsh"));//本资产采购单的oa单号
		  dt_FI_OA_AFS_OA037.setZOAST(mainData.getString("zoast"));//状态为1，正向上传。状态为2，逆向上传
		  dt_FI_OA_AFS_OA037.setZOATP(mainData.getString("djlx"));//oa单据类型
		  dt_FI_OA_AFS_OA037.setZOATN(mainData.getString("djlxms"));//oa单据类型描述
		  if (mainData.getString("zoast").equals("1")) 
		  {
			  dt_FI_OA_AFS_OA037.setZOANO(mainData.getString("times"));//上传次数
      	  }
		  else
      	  {
      		  dt_FI_OA_AFS_OA037.setZOANO(mainData.getString("times2"));//上传次数
      	  }
		  dt_FI_OA_AFS_OA037.setZZBST(mainData.getString("ywlx"));//业务类型
		  dt_FI_OA_AFS_OA037.setZOAUU(mainData.getString("tdr_code"));//上传人账号
		  dt_FI_OA_AFS_OA037.setZOAUM(mainData.getString("tdr_name"));//上传人姓名
		  dt_FI_OA_AFS_OA037.setZDATE(mainData.getString("sqrq_"));//上传日期
		  dt_FI_OA_AFS_OA037.setZTIMES(mainData.getString("scsjc"));//上传时间戳
		  dt_FI_OA_AFS_OA037.setZOAUR(mainData.getString("sqr_code"));//申请人账号
		  dt_FI_OA_AFS_OA037.setZOATU(mainData.getString("sqr_name"));//申请人姓名
		  dt_FI_OA_AFS_OA037.setZOAUN(mainData.getString("sqrq_"));//起草日期

		  dt_FI_OA_AFS_OA037.setEKGRP("X02");//采购组
		  dt_FI_OA_AFS_OA037.setBSART("P002");//订单类型
		  writeLog("[-----主表数据装载结束-----] "+mainData);
		  int index = 0;
		  for (int a = 0; a < detailData.size(); a++) 
		  {
			  index =detailData.size();
			  ITEM_type1 item_type1 = new ITEM_type1();
			  dt_FI_OA_AFS_OA037.addITEM(item_type1);
		  }
		  
			  ITEM_type1[] datas = dt_FI_OA_AFS_OA037.getITEM();
			  for (int b = 0; b < datas.length; b++) 
			  {
				      ITEM_type1 data = datas[b];
					  JSONObject js = detailData.getJSONObject(b);
					  data.setZITEM(String.valueOf(b+1));//本资产采购单的明细oa单行号
					  data.setZOASQ(js.getString("oazcsqdh"));//OA资产申请单号
					  data.setZITSQ(js.getString("oazcsqdhh"));//oa资产申请单行号
					  data.setTXZ01(js.getString("dwb"));//短文本资产描述
					  String jhrq = js.getString("jhrq");
					  data.setEEIND(jhrq.substring(0,4)+jhrq.substring(5,7)+jhrq.substring(8));//交货日期
					  data.setNETWR(js.getString("jingjia").replace(",", ""));//含税金额
					  data.setANLN1(js.getString("sapzcbm"));//资产编码
					  data.setMWSKZ(js.getString("shuima"));//税码
		
					  data.setKNTTP("A");//科目类别
					  data.setMENGE("1");//采购订单数量
					  data.setMEINS("PC");//订单单位
					  data.setWGBEZ("A01");//物料组
					  data.setWERKS(mainData.getString("gongchang"));//工厂
					  data.setLGORT("C017");//库存地点
					  dt_FI_OA_AFS_OA037.setITEM(datas);
			  }
		  writeLog("[-----明细表数据装载结束------] "+detailData);
		  MT_FI_OA_AFS_OA037 mt_FI_OA_AFS_OA037 = new MT_FI_OA_AFS_OA037();
		  mt_FI_OA_AFS_OA037.setMT_FI_OA_AFS_OA037(dt_FI_OA_AFS_OA037);
		  MT_FI_OA_AFS_OA037_REP mt_FI_OA_AFS_OA037_REP = stub.sIO_FI_OA_AFS_OA037(mt_FI_OA_AFS_OA037);
		  DT_FI_OA_AFS_OA037_REP dt_FI_OA_AFS_OA037_REP = mt_FI_OA_AFS_OA037_REP.getMT_FI_OA_AFS_OA037_REP();
		  String e_msg = Util.null2String(dt_FI_OA_AFS_OA037_REP.getE_MSG());//消息
		  String e_subrc = dt_FI_OA_AFS_OA037_REP.getE_SUBRC();//0-成功，1-失败\
		  writeLog("037_e_msg--》"+e_msg+"  e_subrc:"+e_subrc);
		  if(!e_subrc.equals("S")){
			  writeLog("[037接口失败后返回的信息---》  ]"+e_msg+"--"+e_subrc);
			  jsonobject.put("e_msg", e_msg);
			  jsonobject.put("e_subrc", e_subrc);
		  }
		  if (e_subrc.equals("S"))
		  {
			  writeLog("[037接口成功后返回的信息---》  ]"+e_msg+"--"+e_subrc);
			  jsonobject.put("e_msg", e_msg);
			  jsonobject.put("e_subrc", e_subrc);
		  }
		  
		  RecordSet rs = new RecordSet();
		  for(int d = 0; d < index;d++)
		  {
			  ITEM_type0 item_t = new ITEM_type0();
			  dt_FI_OA_AFS_OA037_REP.addITEM(item_t);
		  }
		  ITEM_type0[] item_type0s = dt_FI_OA_AFS_OA037_REP.getITEM();
		  for (int e = 0; e < item_type0s.length; e++) {
			  ITEM_type0 item_type0 = item_type0s[e];
			  String zoanu = item_type0.getZOANU();//oa单号
			  String zitem = item_type0.getZITEM();//oa单行号
			  String ebeln = item_type0.getEBELN();//采购订单号
			  String ebelp = item_type0.getEBELP();//采购订单行号
			  writeLog("[037接口返回的具体信息---》  ]  oa单号："+zoanu+"  oa单行号："+zitem+"  采购订单号："+ebeln+"  采购订单行号："+ebelp);
			  if (e_subrc.equals("S")) {
				JSONObject j_item = detailData.getJSONObject(e);
			    String sql2 = "update formtable_main_187_dt1 set sapcgddhh='"+ebelp+"',cgdh037='"+ebeln+"',sapoadh='"+zoanu+"'"
					 		+ "where id='"+j_item.getString("id")+"'";
			    writeLog("[sql2-->]"+sql2);
				rs.execute(sql2);
			  } 
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return jsonobject;
   }
}
