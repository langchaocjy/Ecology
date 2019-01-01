package com.tcss.zc_ut;

import java.util.Map;

import com.tcss.zc.zc024;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;

public class WriteInSap extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
//		JSONObject jsonobject3 = new JSONObject();
//		WISapUtil wiSapUtil = new WISapUtil();
//		Property[] properties = ri.getMainTableInfo().getProperty();
//		DetailTable[] detailtable = ri.getDetailTableInfo().getDetailTable();
//		String columnvalue = null;
//		     for(int i = 0;i < properties.length; i++)
//		     {
//			     String columnname1 = properties[i].getName();
//			     String columnvalue1 = Util.null2String(properties[i].getValue());
//			     if(columnname1.equals("cghdb") && columnvalue1.equals("0")){
//			    	 columnvalue = columnvalue1;
//			     }
//			     JSONObject jsonobject1 = wiSapUtil.put(columnname1, columnvalue1);
//			     String requestid = ri.getRequestid();
//			     jsonobject1.put("requestid", requestid);
//			     jsonobject3.putAll(jsonobject1);
//		     }
//		     
//		    if(detailtable.length >0){
//				DetailTable dt = detailtable[0];
//				Row[] rows = dt.getRow();
//				for(int j = 0;j < rows.length; j++ ){//循环遍历有多少行明细
//					Row row = rows[j];
//					Cell[] cells = row.getCell();
//					
//							if(columnvalue.equals("0")){
//								for(int l = 0; l<cells.length ;l++){
//									Cell cell2 = cells[l];
//									String columnname2 = Util.null2String(cell2.getName());
//									String columnvalue2 = Util.null2String(cell2.getValue());
//								    JSONObject jsonobject2 = wiSapUtil.put(columnname2, columnvalue2);
//								    jsonobject3.putAll(jsonobject2);
//								}
//								zc024 zc = new zc024();
//								JSONArray jsons = zc.setAssetInformation(jsonobject3);
//								for (int i = 0; i < jsons.size(); i++) {
//									JSONObject json = jsons.getJSONObject(i);
//									String zoanu = json.getString("zoanu");
//									String num = json.getString("num02");
//									String message = json.getString("v_message");
//									String subrc = json.getString("v_subrc");
//									if(subrc.equals("E") && !message.equals("OA单"+zoanu+"行号"+num+"已经建立过资产")){
//										ri.getRequestManager().setMessageid(System.currentTimeMillis()+"");
//										ri.getRequestManager().setMessagecontent("创建资产卡片失败！错误信息："+message+"   在第"+num+"行");
//									}else if(subrc.equals("E") && message.equals("OA单"+zoanu+"行号"+num+"已经建立过资产")){
//										writeLog("将已经成功建立过资产的过滤不输出");
//									}else{
//										writeLog("创建卡片成功"+subrc);
//									}
//								}
//							}else{
//								writeLog("调拨不需要写入sap");
//							}
//						
//					
//			    }
//			}
		    
		    writeLog("[-----开始024接口------]");
		    String requestid = request.getRequestid();
		    zc024 zc024 = new zc024();
		    WISap_2_util sap_2_util = new WISap_2_util();
		    try 
		    {
				JSONObject mainData = sap_2_util.getMainData(requestid);
				mainData.put("requestid", requestid);
				String sqrq = mainData.getString("sqrq");
				sqrq = sqrq.substring(0,4)+sqrq.substring(5, 7)+sqrq.substring(8);
				mainData.put("sqrq_", sqrq);
				String date = sap_2_util.date();
				mainData.put("scsjc", date);
				String tdr = mainData.getString("tdr");
		       	String sqr = mainData.getString("cwczr");
		       	Map map = null;
		       	try {
		       		map = sap_2_util.seek(tdr);//填单人
		       		mainData.put("tdr_code", map.get("code"));
		       		mainData.put("tdr_name",map.get("name"));
					map = sap_2_util.seek(sqr);//申请人
					mainData.put("cwczr_code", map.get("code"));
					mainData.put("cwczr_name", map.get("name"));
				} catch (Exception e) {
					writeLog("[024尝试获取填单人和申请人失败，可能是空指针]");
					e.printStackTrace();
				}
				writeLog("[024获取主字段数据结束--》]"+mainData);
				
				RecordSet rs = new RecordSet();
				String sql1 = "select id from FORMTABLE_MAIN_170_dt1 where mainid in(select id from FORMTABLE_MAIN_170 where requestid='"+requestid+"')";    
				rs.execute(sql1);
				String allid = "";
				while(rs.next()) 
				{
					String id = rs.getString("id");
					if (allid.equals("")) {
						allid = id;
					}else{
						allid = allid + "-" + id;
					}
				}
				String[] idarr = allid.split("-");
				String temp;
				for (int j = 0; j < idarr.length; j++) {
					for (int j2 = j+1; j2 < idarr.length; j2++) {
						if (Integer.parseInt(idarr[j]) > Integer.parseInt(idarr[j2])) {
							temp = idarr[j];
							idarr[j] = idarr[j2];
							idarr[j2] = temp;
						}
					}
				}
				for (int j = 0; j < idarr.length; j++) {
					String sql2 = "update FORMTABLE_MAIN_170_DT1 set mxhh='"+(j+1)+"' where id ='"+idarr[j]+"'";
					writeLog("[sql2--->]"+sql2);
					rs.execute(sql2);
				}
				JSONArray detailData = sap_2_util.getDetailData(requestid, 1);
				for (int i = 0; i < detailData.size(); i++) 
				{
					JSONObject detail_index = (JSONObject) detailData.get(i);
					           detail_index.put("hh",String.valueOf(i+1));
					String sfcg = detail_index.getString("sfcg");
					if (sfcg.equals("1")) 
					{
						mainData.putAll(detail_index);
						JSONObject json = zc024.setAssetInformation(mainData);
						String zoanu = json.getString("zoanu");
						String num = json.getString("num02");
						String message = json.getString("v_message");
						String subrc = json.getString("v_subrc");
						writeLog("[024返回的信息---》]"+zoanu+"  num:"+num+"  message:"+message+"  subrc:"+subrc);
						if(subrc.equals("E") && !message.equals("OA单"+zoanu+"行号"+num+"已经建立过资产")){
							request.getRequestManager().setMessageid(System.currentTimeMillis()+"");
							request.getRequestManager().setMessagecontent("创建资产卡片失败！错误信息："+message+"   在第"+num+"行");
						}else if(subrc.equals("E") && message.equals("OA单"+zoanu+"行号"+num+"已经建立过资产")){
							writeLog("将已经成功建立过资产的过滤不输出");
						}else{
							writeLog("创建卡片成功"+subrc);
						}
					}
					else
					{
						writeLog("调拨不需要写入sap");
					}
				}
			} 
		    catch (Exception e) 
		    {
				e.printStackTrace();
			}
		    writeLog("[-----结束024接口------]");
		return SUCCESS;
	}
	
	

}
