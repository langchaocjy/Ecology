package com.tcss.zc_ut;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class WISap_2_util extends BaseBean{
        public JSONObject put(String name,String value){
        	JSONObject json = new JSONObject();
        	if(name.equals("gsdm")){//公司代码
        		json.put("gsdm", value);
        	}else if(name.equals("gys")){//供应商编码
        		json.put("gys", value);
        	}else if(name.equals("lsh")){//oa单号----流水号
        		json.put("lsh", value);
        	}else if(name.equals("djlx")){//oa单据类型
        		json.put("djlx", value);
        	}else if(name.equals("djlxms")){//oa单据类型描述
        		json.put("djlxms", value);
        	}else if(name.equals("times")){//上传次数
        		json.put("times", value);
        	}else if(name.equals("tdr")){//上传人账号姓名------填单人
        		Map map = WISap_2_util.seek(value);
        		json.put("tdr_code", map.get("code"));
        		json.put("tdr_name", map.get("name"));
        	}else if(name.equals("sqrq")){//上传日期，起草日期，时间戳
        		String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
       		    json.put("sqrq", sqrq);
       		    String date = WISapUtil.date();//上传时间戳
       		    json.put("scsjc", date);
        	}else if(name.equals("sqr")){//申请人账号姓名--------主表申请人
        		Map map = WISap_2_util.seek(value);
        		json.put("sqr_code", map.get("code"));
        		json.put("sqr_name", map.get("name"));
        	}else if(name.equals("ywlx")){//业务类型
        		json.put("ywlx", value);
        	}else if(name.equals("ywlx038")){
        		json.put("ywlx038", value);
        	}else if(name.equals("ywfw")){
        		if (!value.equals("")||value!="") 
        		{
        			json.put("ywfw", value.substring(0, 2)+"00");
				}
        		else
        		{
        			json.put("ywfw", "");
        		}
        	}//明细表
        	else if(name.equals("oazcsqdh")){//oa资产申请单号
        		json.put("oazcsqdh", value);
        	}else if(name.equals("oazcsqdhh")){//oa资产申请单行号
        		json.put("oazcsqdhh", value);
        	}else if(name.equals("dwb")){//短文本
        		json.put("dwb", value);
        	}else if(name.equals("jhrq")){//交货日期
        		String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
        		json.put("jhrq", sqrq);
        	}else if(name.equals("jingjia")){//含税金额
        		json.put("jingjia", value);
        	}else if(name.equals("sapzcbm")){//资产编码
        		json.put("sapzcbm", value);
        	}else if(name.equals("shuima")){//税码
        		json.put("shuima", value);
        	}else if(name.equals("shrq")){//收货日期
        		String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
        		json.put("shrq", sqrq);
        	}else if(name.equals("cgddh")){//采购订单号
        		json.put("cgddh", value);
        	}else if(name.equals("sapcgddhh")){//采购订单行号
        		json.put("sapcgddhh", value);
        	}
			return json;
        } 
        public static Map<String,String> seek(String id){//用人员id搜人员账号和姓名
       	 Map map = new HashMap<String, String>();
       	 RecordSet rs = new RecordSet();
       	 String sql = "select * from hrmresource where id= '"+id+"'";
       	 rs.execute(sql);
       	 if(rs.next()){
       		 String code = rs.getString("loginid");
   			 String name = rs.getString("lastname");
   			 map.put("code", code);
   			 map.put("name", name);
       	 }
   		 return map;
        }
        public static String date(){
       	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
       	 String date = sdf.format(new Date());
   		 return date;
        }
        public JSONObject getMainData(String requestid)
  	  {
  	    JSONObject json = new JSONObject();
  	    RecordSet rs = new RecordSet();
  	    String tableName = getTableName(requestid);
  	    String sql = "select * from " + tableName + " where requestid='" + requestid + "' ";
  	    rs.execute(sql);
  	    if (rs.next())
  	    {
  	      String[] columnName = rs.getColumnName();
  	      for (int i = 0; i < columnName.length; i++)
  	      {
  	        String name = columnName[i].toLowerCase();
  	        String value = rs.getString(name);
  	        json.put(name, value);
  	      }
  	    }
  	    return json;
  	  }
  	  
  	  public JSONArray getDetailData(String requestid, int index)
  	  {
  	    JSONArray jsons = new JSONArray();
  	    RecordSet rs = new RecordSet();
  	    String tableName = getTableName(requestid);
  	    String sql = "select * from " + tableName + "_dt" + index + " where mainid in( select id from " + tableName + " where  requestid='" + requestid + "') order by id ";
  	    rs.execute(sql);
  	    while (rs.next())
  	    {
  	      JSONObject json = new JSONObject();
  	      String[] columnName = rs.getColumnName();
  	      for (int i = 0; i < columnName.length; i++)
  	      {
  	        String name = columnName[i].toLowerCase();
  	        String value = rs.getString(name);
  	        json.put(name, value);
  	      }
  	      jsons.add(json);
  	    }
  	    return jsons;
  	  }
  	  
  	  public String getTableName(String requestid)
  	  {
  	    String tablename = "";
  	    if (!"".equals(requestid))
  	    {
  	      String sql = "select tablename from workflow_bill where id in(select formid from workflow_base where id in(select workflowid from workflow_requestbase where requestid=" + requestid + "))";
  	      RecordSet rs = new RecordSet();
  	      rs.execute(sql);
  	      if (rs.next()) {
  	        tablename = rs.getString("tablename");
  	      }
  	    }
  	    return tablename;
  	  }
  	  
  	  //用来做正向逆向判断
  	  public static boolean setSE(){
		return false;
  	  }
}
