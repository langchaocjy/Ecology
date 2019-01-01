package com.tcss.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import net.sf.json.JSONObject;
import out.deal.afs.rs017.DTOAAFSOA017;
import out.deal.afs.rs017.DTOAAFSOA017REP;
import out.deal.afs.rs017.ObjectFactory;
import out.deal.afs.rs017.SIOOAAFSOA017;
import out.deal.afs.rs017.DTOAAFSOA017.SHEET;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class rs017 extends HttpServlet{
    @Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		BaseBean b = new BaseBean();
		RecordSet rs = new RecordSet();
		String ckgw=Util.null2String(request.getParameter("ckgw"));//参考岗位id
		String gwbm=Util.null2String(request.getParameter("gwbm"));//岗位编码
		String date=Util.null2String(request.getParameter("date"));//20180601
		
		String url="http://172.16.44.21:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA017&interfaceNamespace=urn:bk2:afs:deal:out";
		String username = b.getPropValue("tcsssaphr", "username_hr");
	    String password = b.getPropValue("tcsssaphr", "password_hr");
	    JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
	    factory.setAddress(url);
	    factory.setUsername(username);
	    factory.setPassword(password);
	    factory.setServiceClass(SIOOAAFSOA017.class);
	    SIOOAAFSOA017 service=(SIOOAAFSOA017) factory.create();
	    ObjectFactory objectFactory=new ObjectFactory();
	    SHEET sheet=objectFactory.createDTOAAFSOA017SHEET();
	    sheet.setCKGW(ckgw);//参考岗位code
	    sheet.setGWBM(gwbm);
	    sheet.setSQRQ(date);
	    DTOAAFSOA017 dtoaafsoa017=objectFactory.createDTOAAFSOA017();
	    List<DTOAAFSOA017.SHEET> sheets=dtoaafsoa017.getSHEET();
	    sheets.add(sheet);
	    DTOAAFSOA017REP dtoaafsoa017rep=service.sioOAAFSOA017(dtoaafsoa017);
	    List<DTOAAFSOA017REP.RETURN> returns=dtoaafsoa017rep.getRETURN();
	    JSONObject json=new JSONObject();
	    for (int i = 0; i < returns.size(); i++) {
			DTOAAFSOA017REP.RETURN dr=returns.get(i);
			String zhxzz=dr.getHXZZ();
			String zrztj=dr.getRZTJ();
			json.put("zhxzz", zhxzz);
			json.put("zrztj", zrztj);
		}
	    response.setCharacterEncoding("UTF-8"); 
	    response.setContentType("application/json; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(json.toString());
		out.flush();
		out.close();
	}
}
