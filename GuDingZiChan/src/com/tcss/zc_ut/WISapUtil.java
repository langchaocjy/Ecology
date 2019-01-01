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
    	 if(name.equals("lsh")){//��ˮ��
    		 jsonobject.put("lsh", value);
    	 }else if(name.equals("gsdm")){//��˾����
    		 jsonobject.put("gsdm", value);
    	 }else if(name.equals("bdlx")){//oa������
    		 jsonobject.put("bdlx", value);
    	 }else if(name.equals("bdmc")){//oa������
    		 jsonobject.put("bdmc", value);
    	 }else if(name.equals("cwczr")){//�ʲ�����Ա������
    		 Map map = WISapUtil.seek(value);
    		 jsonobject.put("czz_code", map.get("code"));
    		 jsonobject.put("czz_name", map.get("name"));
    	 }else if(name.equals("sqrq")){//��������   //�������
    		 String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
    		 jsonobject.put("sqrq", sqrq);
    		 String date = WISapUtil.date();//�ϴ�ʱ���
    		 jsonobject.put("scsjc", date);
    	 }else if(name.equals("tdr")){//���
    		 Map map = WISapUtil.seek(value);
    		 jsonobject.put("tdr_code", map.get("code"));
    		 jsonobject.put("tdr_name", map.get("name"));
    	 }//-------------->��ϸ��
    	 else if(name.equals("zclx")){//�ʲ����
    		 jsonobject.put("zclx", value);
    	 }else if(name.equals("zcmc")){//�ʲ�����
    		 jsonobject.put("zcmc", value);
    	 }else if(name.equals("xlhmj")){//���к�
    		 jsonobject.put("xlhmj", value);
    	 }else if(name.equals("mj")){//���
    		 jsonobject.put("mj", value);
    	 }else if(name.equals("yt")){//��;
    		 jsonobject.put("yt", value);
    	 }else if(name.equals("ydzj")){//�¶����
    		 jsonobject.put("ydzj", value);
    	 }else if(name.equals("gg")){//���
    		 jsonobject.put("gg", value);
    	 }else if(name.equals("dz")){//����ַ
    		 jsonobject.put("dz", value);
    	 }else if(name.equals("cs1")){//����
    		 jsonobject.put("cs1", value);
    	 }else if(name.equals("zzbh")){//ִ�ձ��
    		 jsonobject.put("zzbh", value);
    	 }else if(name.equals("nbdd")){//�ڲ�����
    		 jsonobject.put("nbdd", value);
    	 }else if(name.equals("zrcbzx")){//���γɱ�����
    		 jsonobject.put("zrcbzx", value);
    	 }else if(name.equals("cbzx")){//�ɱ�����
    		 jsonobject.put("cbzx", value);
    	 }else if(name.equals("lrzx")){//��������
    		 jsonobject.put("lrzx", value);
    	 }else if(name.equals("ywfw")){//ҵ��Χ
    		 jsonobject.put("ywfw", value);
    	 }else if(name.equals("rybh")){//��Ա���
    		 jsonobject.put("rybh", value);
    	 }else if(name.equals("times")){//�ϴ�����
    		 jsonobject.put("times", value);
    	 }else if(name.equals("jldw")){//������λ
    		 jsonobject.put("jldw", value);
    	 }else if(name.equals("ywlx")){//ҵ������
    		 jsonobject.put("ywlx", value);
    	 }else if(name.equals("cfdd")){//��ŵص�
    		 jsonobject.put("cfdd", value);
    	 }else if(name.equals("hh")){//�к�
    		 jsonobject.put("hh", value);
    	 }else if(name.equals("kzzjnx")){//����۾�����
    		 jsonobject.put("kzzjnx", value);
    	 }else if(name.equals("kjzjyf")){//����۾��·�
    		 jsonobject.put("kjzjyf", value);
    	 }else if(name.equals("sfzjnx")){//˰���۾�����
    		 jsonobject.put("sfzjnx", value);
    	 }else if(name.equals("sfzjyf")){//˰���۾��·�
    		 jsonobject.put("sfzjyf", value);
    	 }else if(name.equals("yzjje")){
    		 jsonobject.put("yzjje", value);
    	 }
		return jsonobject;
     }
     
     public static Map<String,String> seek(String id){//����Աid����Ա�˺ź�����
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
