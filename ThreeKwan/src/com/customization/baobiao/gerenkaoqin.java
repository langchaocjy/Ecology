package com.customization.baobiao;

import com.customization.AllUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class gerenkaoqin {
   private static BaseBean basebean = new BaseBean();
   public static JSONArray getData(String nameid,String sub,String dep,String ym){
	   JSONArray root_jsons = new JSONArray();
	   JSONObject root_json = new JSONObject();
	   RecordSet rs = new RecordSet(),rs2=new RecordSet();
	   String sql1 = "";
	   if (!nameid.equals("")) //如果姓名不为空，就查这个人
	   {
		   sql1 = "select * from uf_kqsj where xm='"+nameid+"'";
	   }
	   else if(nameid.equals("") && sub.equals("") && !dep.equals("")) //分部为空，部门不为空，用部门
	   {
		   sql1 = "select * from uf_kqsj where bm='"+dep+"'";
	   }
	   else if(nameid.equals("") && !sub.equals("") && !dep.equals(""))//两个都不为空，用部门
	   {
		   sql1 = "select * from uf_kqsj where bm='"+dep+"'";
	   }
	   else if(nameid.equals("") && !sub.equals("") && dep.equals(""))//分部不为空，部门空了，查分部下的所有人
	   {
		   sql1 = "select * from uf_kqsj t1,hrmdepartment t2 where t1.bm=t2.id and t2.subcompanyid1='"+sub+"'";
	   }
	   else if(nameid.equals("") && sub.equals("") && dep.equals(""))//两个都为空
	   {
		   sql1 = "select * from uf_kqsj";
	   }
	   rs.execute(sql1);
	   while (rs.next()) 
	   {
		   
		   String bm = Util.null2String(rs.getString("bm"));
		   String xm = Util.null2String(rs.getString("xm"));
		   String departmentname="",lastname="";
		   rs2.execute("select t1.lastname,t2.DEPARTMENTNAME from hrmresource t1 ,hrmdepartment t2 where t1.DEPARTMENTID=t2.id and t1.id='"+xm+"'");
		   if (rs2.next()) 
		   {
			   departmentname=Util.null2String(rs2.getString("departmentname"));
			   lastname=Util.null2String(rs2.getString("lastname"));
		   }
		   String rq = Util.null2String(rs.getString("rq"));//2019-11-15
//		   String xq = rs.getString("xq");
		   String dksj = Util.null2String(rs.getString("dksj"));
		   String ccjl = Util.null2String(rs.getString("ccjl"));
		   String bq = Util.null2String(rs.getString("bq"));
		   String pbsj = Util.null2String(rs.getString("pbsj"));
		   String qjjl =Util.null2String(rs.getString("qjjl"));
		   String wcjl = Util.null2String(rs.getString("wcjl"));
		   boolean isHave = AllUtil.getTf(root_jsons, lastname, rq);
		   if (!isHave && rq.substring(0,7).equals(ym)) {
				   root_json.put("部门", departmentname);
				   root_json.put("姓名", lastname);
				   root_json.put("日期", rq);
//			       root_json.put("星期", xq);
				   root_json.put("打卡时间", dksj);
				   root_json.put("出差记录", ccjl);
				   root_json.put("补签", bq);
				   root_json.put("排班时间", pbsj);
				   root_json.put("请假记录", qjjl);
				   root_json.put("外出记录", wcjl);
				   root_jsons.add(root_json);
		   }
	   }
	return root_jsons;
   }
}
