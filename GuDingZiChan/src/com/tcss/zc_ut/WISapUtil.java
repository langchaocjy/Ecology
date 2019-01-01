package com.tcss.zc_ut;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class WISapUtil extends BaseBean{
     public JSONObject put(String name,String value){
    	 JSONObject jsonobject = new JSONObject();
    	 if(name.equals("lsh")){//流水号
    		 jsonobject.put("lsh", value);
    	 }else if(name.equals("gsdm")){//公司代码
    		 jsonobject.put("gsdm", value);
    	 }else if(name.equals("bdlx")){//oa表单类型
    		 jsonobject.put("bdlx", value);
    	 }else if(name.equals("bdmc")){//oa表单名称
    		 jsonobject.put("bdmc", value);
    	 }else if(name.equals("cwczr")){//资产管理员操作者
    		 Map map = WISapUtil.seek(value);
    		 jsonobject.put("czz_code", map.get("code"));
    		 jsonobject.put("czz_name", map.get("name"));
    	 }else if(name.equals("sqrq")){//申请日期   //起草日期
    		 String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
    		 jsonobject.put("sqrq", sqrq);
    		 String date = WISapUtil.date();//上传时间戳
    		 jsonobject.put("scsjc", date);
    	 }else if(name.equals("tdr")){//填单人
    		 Map map = WISapUtil.seek(value);
    		 jsonobject.put("tdr_code", map.get("code"));
    		 jsonobject.put("tdr_name", map.get("name"));
    	 }//-------------->明细表
    	 else if(name.equals("zclx")){//资产类别
    		 jsonobject.put("zclx", value);
    	 }else if(name.equals("zcmc")){//资产名称
    		 jsonobject.put("zcmc", value);
    	 }else if(name.equals("xlhmj")){//序列号
    		 jsonobject.put("xlhmj", value);
    	 }else if(name.equals("mj")){//面积
    		 jsonobject.put("mj", value);
    	 }else if(name.equals("yt")){//用途
    		 jsonobject.put("yt", value);
    	 }else if(name.equals("ydzj")){//月度租金
    		 jsonobject.put("ydzj", value);
    	 }else if(name.equals("gg")){//规格
    		 jsonobject.put("gg", value);
    	 }else if(name.equals("dz")){//租金地址
    		 jsonobject.put("dz", value);
    	 }else if(name.equals("cs1")){//城市
    		 jsonobject.put("cs1", value);
    	 }else if(name.equals("zzbh")){//执照编号
    		 jsonobject.put("zzbh", value);
    	 }else if(name.equals("nbdd")){//内部订单
    		 jsonobject.put("nbdd", value);
    	 }else if(name.equals("zrcbzx")){//责任成本中心
    		 jsonobject.put("zrcbzx", value);
    	 }else if(name.equals("cbzx")){//成本中心
    		 jsonobject.put("cbzx", value);
    	 }else if(name.equals("lrzx")){//利润中心
    		 jsonobject.put("lrzx", value);
    	 }else if(name.equals("ywfw")){//业务范围
    		 jsonobject.put("ywfw", value);
    	 }else if(name.equals("rybh")){//人员编号
    		 jsonobject.put("rybh", value);
    	 }else if(name.equals("times")){//上传次数
    		 jsonobject.put("times", value);
    	 }else if(name.equals("jldw")){//计量单位
    		 jsonobject.put("jldw", value);
    	 }else if(name.equals("ywlx")){//业务类型
    		 jsonobject.put("ywlx", value);
    	 }else if(name.equals("cfdd")){//存放地点
    		 jsonobject.put("cfdd", value);
    	 }else if(name.equals("hh")){//行号
    		 jsonobject.put("hh", value);
    	 }else if(name.equals("kzzjnx")){//会计折旧年限
    		 jsonobject.put("kzzjnx", value);
    	 }else if(name.equals("kjzjyf")){//会计折旧月份
    		 jsonobject.put("kjzjyf", value);
    	 }else if(name.equals("sfzjnx")){//税法折旧年限
    		 jsonobject.put("sfzjnx", value);
    	 }else if(name.equals("sfzjyf")){//税法折旧月份
    		 jsonobject.put("sfzjyf", value);
    	 }else if(name.equals("yzjje")){
    		 jsonobject.put("yzjje", value);
    	 }
		return jsonobject;
     }
     
     public static Map<String,String> seek(String id){//用人员id搜人员账号和姓名
    	 Map map = new HashMap<String, String>();
    	 RecordSet rs = new RecordSet();
    	 String sql = "select * from hrmresource where id= '"+id+"'";
    	 rs.executeSql(sql);
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
}
