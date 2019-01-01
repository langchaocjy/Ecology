  package com.tcss.report;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import net.sf.json.JSONObject;
import out.deal.afs.rs019.DTOAAFSOA019EXT;
import out.deal.afs.rs019.DTOAAFSOA019EXT.SHEET;
import out.deal.afs.rs019.DTOAAFSOA019EXTREP;
import out.deal.afs.rs019.ObjectFactory;
import out.deal.afs.rs019.SIOOAAFSOA019;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class rs019{
    public static void setFPost(JSONObject json){
    	String url = "http://172.16.44.21:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA019&interfaceNamespace=urn:bk2:afs:deal:out";
    	String username = new BaseBean().getPropValue("tcsssaphr", "username_hr");
        String password = new BaseBean().getPropValue("tcsssaphr", "password_hr");
        BaseBean b = new BaseBean();
    	JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
    	    factory.setAddress(url);
    	    factory.setUsername(username);
    	    factory.setPassword(password);
    	    factory.setServiceClass(SIOOAAFSOA019.class);
    	    SIOOAAFSOA019 service = (SIOOAAFSOA019)factory.create();
    	ObjectFactory objectfactory = new ObjectFactory();
    	SHEET sheet = objectfactory.createDTOAAFSOA019EXTSHEET();
    	sheet.setGWSXRQ(json.getString("岗位生效日期"));//岗位生效日期
    	sheet.setHXZZ(json.getString("核心职责"));//核心职责
    	sheet.setRZTJ(json.getString("任职条件"));//任职条件
    	sheet.setXQBM(json.getString("部门"));//部门
    	sheet.setZPGW(json.getString("招聘岗位"));//招聘岗位
    	
    	DTOAAFSOA019EXT dtoaafsoa019ext = objectfactory.createDTOAAFSOA019EXT();
    	dtoaafsoa019ext.setSHEET(sheet);
    	DTOAAFSOA019EXTREP dtoaafsoa019extrep =service.sioOAAFSOA019(dtoaafsoa019ext);
    	DTOAAFSOA019EXTREP.SHEET sheets = dtoaafsoa019extrep.getSHEET();
    	String gwbm = sheets.getGWBM(); 
    	String code = sheets.getCODE();
    	String mesg = sheets.getMESG();
    	rs019.setsql(gwbm,json.getString("请求id"),json.getString("formtable"));
    	b.writeLog("岗位编码     "+gwbm+"  状态   "+code+"  mesg  "+mesg);
    }
    private static void setsql(String gwbm,String requestid,String formtable){
    	 RecordSet rs = new RecordSet();
    	 String sql = "update "+formtable+" set gwbm = '"+gwbm+"' where requestid = '"+requestid+"'";
    	 rs.executeSql(sql);
    }
}

