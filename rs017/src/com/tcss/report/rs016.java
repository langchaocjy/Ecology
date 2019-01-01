package com.tcss.report;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import net.sf.json.JSONObject;
import out.deal.afs.rs016.*;
import out.deal.afs.rs016.DTOAAFSOA016.SHEET;
import weaver.general.BaseBean;

public class rs016 extends BaseBean{
public  void setRs016(JSONObject json){
	String url="http://58.62.17.77:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA016&interfaceNamespace=urn:bk2:afs:deal:out";
	String username = new BaseBean().getPropValue("tcsssaphr", "username_hr");
    String password = new BaseBean().getPropValue("tcsssaphr", "password_hr");
    JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
    factory.setAddress(url);
    factory.setUsername(username);
    factory.setPassword(password);
    factory.setServiceClass(SIOOAAFSOA016.class);
    SIOOAAFSOA016 service=(SIOOAAFSOA016) factory.create();
    ObjectFactory objectFactory=new ObjectFactory();
    SHEET sheet=objectFactory.createDTOAAFSOA016SHEET();
    sheet.setGwsxrq(json.getString("申请日期"));//申请日期
    sheet.setGwbm(json.getString("岗位编码"));//岗位编码
    sheet.setHxzz(json.getString("核心职责"));//核心职责
    sheet.setRztj(json.getString("任职条件"));//任职条件
    DTOAAFSOA016 dtoaafsoa016=objectFactory.createDTOAAFSOA016();
    List<DTOAAFSOA016.SHEET> sheets=dtoaafsoa016.getSHEET();
    sheets.add(sheet);
    DTOAAFSOA016REP dtoaafsoa016rep=service.sioOAAFSOA016(dtoaafsoa016);
    List<DTOAAFSOA016REP.RETURN> returns=dtoaafsoa016rep.getRETURN();
    for (int i = 0; i < returns.size(); i++) {
    	DTOAAFSOA016REP.RETURN dr=returns.get(i);
    	String message=dr.getMESSAGE();
    	String type=dr.getTYPE();
    	writeLog(message+" ,"+type);
	}
    
}

}
