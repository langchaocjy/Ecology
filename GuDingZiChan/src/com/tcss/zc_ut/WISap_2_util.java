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
        	if(name.equals("gsdm")){//��˾����
        		json.put("gsdm", value);
        	}else if(name.equals("gys")){//��Ӧ�̱���
        		json.put("gys", value);
        	}else if(name.equals("lsh")){//oa����----��ˮ��
        		json.put("lsh", value);
        	}else if(name.equals("djlx")){//oa��������
        		json.put("djlx", value);
        	}else if(name.equals("djlxms")){//oa������������
        		json.put("djlxms", value);
        	}else if(name.equals("times")){//�ϴ�����
        		json.put("times", value);
        	}else if(name.equals("tdr")){//�ϴ����˺�����------���
        		Map map = WISap_2_util.seek(value);
        		json.put("tdr_code", map.get("code"));
        		json.put("tdr_name", map.get("name"));
        	}else if(name.equals("sqrq")){//�ϴ����ڣ�������ڣ�ʱ���
        		String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
       		    json.put("sqrq", sqrq);
       		    String date = WISapUtil.date();//�ϴ�ʱ���
       		    json.put("scsjc", date);
        	}else if(name.equals("sqr")){//�������˺�����--------����������
        		Map map = WISap_2_util.seek(value);
        		json.put("sqr_code", map.get("code"));
        		json.put("sqr_name", map.get("name"));
        	}else if(name.equals("ywlx")){//ҵ������
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
        	}//��ϸ��
        	else if(name.equals("oazcsqdh")){//oa�ʲ����뵥��
        		json.put("oazcsqdh", value);
        	}else if(name.equals("oazcsqdhh")){//oa�ʲ����뵥�к�
        		json.put("oazcsqdhh", value);
        	}else if(name.equals("dwb")){//���ı�
        		json.put("dwb", value);
        	}else if(name.equals("jhrq")){//��������
        		String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
        		json.put("jhrq", sqrq);
        	}else if(name.equals("jingjia")){//��˰���
        		json.put("jingjia", value);
        	}else if(name.equals("sapzcbm")){//�ʲ�����
        		json.put("sapzcbm", value);
        	}else if(name.equals("shuima")){//˰��
        		json.put("shuima", value);
        	}else if(name.equals("shrq")){//�ջ�����
        		String sqrq = value.substring(0,4)+value.substring(5,7)+value.substring(8);
        		json.put("shrq", sqrq);
        	}else if(name.equals("cgddh")){//�ɹ�������
        		json.put("cgddh", value);
        	}else if(name.equals("sapcgddhh")){//�ɹ������к�
        		json.put("sapcgddhh", value);
        	}
			return json;
        } 
        public static Map<String,String> seek(String id){//����Աid����Ա�˺ź�����
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
  	  
  	  //���������������ж�
  	  public static boolean setSE(){
		return false;
  	  }
}
