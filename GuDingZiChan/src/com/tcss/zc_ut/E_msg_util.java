package com.tcss.zc_ut;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class E_msg_util extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response,String msg,String subrc) throws IOException{
    	doPost(request,response,msg,subrc);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response,String msg,String subrc) throws IOException{
    	JSONObject json = new JSONObject();
    	json.put("msg", msg);
    	json.put("subrc", subrc);
    	response.setCharacterEncoding("UTF-8"); 
   	    response.setContentType("application/json; charset=utf-8");
   		PrintWriter out=response.getWriter();
   		out.println(json.toString());
   		out.flush();
   		out.close();
    }
}
