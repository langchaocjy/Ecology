package com.tcss.zc_ut;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class ReadFlow extends HttpServlet{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
	  doPost(request,response);
  }
  
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
	  String hh = request.getParameter("hh");
	  String lsh = request.getParameter("lsh_f");
	  String rybh = request.getParameter("rybh_f");
	  JSONObject json = new JSONObject();
	  RecordSet rs = new RecordSet();
	  String sql = "select * from uf_fhdxxbd where hh='"+hh+"' and oadh = '"+lsh+"' and sqr = '"+rybh+"'";
	  rs.executeSql(sql);
	  if(rs.next()){
		  String zcbm = rs.getString("zcbm");
		  json.put("zcbm", zcbm);
		  String sql2= "update FORMTABLE_MAIN_170_DT1 set zcbm = '"+zcbm+"' where hh ='"+hh+"' and mxsqr ='"+rybh+"'";
		  rs.executeSql(sql2);
	  }
	    response.setCharacterEncoding("UTF-8"); 
	    response.setContentType("application/json; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(json.toString());
		out.flush();
		out.close();
  }
}
