package com.tcss.report;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import net.sf.json.JSONObject;
import out.deal.afs.rs017.DTOAAFSOA017;
import out.deal.afs.rs017.DTOAAFSOA017REP;
import out.deal.afs.rs017.ObjectFactory;
import out.deal.afs.rs017.SIOOAAFSOA017;
import out.deal.afs.rs017.DTOAAFSOA017.SHEET;
import weaver.general.BaseBean;

public class test017 {
public void sss(){
	BaseBean b = new BaseBean();
	String url="http://58.62.17.77:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA017&interfaceNamespace=urn:bk2:afs:deal:out";
    JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
    factory.setAddress(url);
    factory.setUsername("IF_OA_QAS");
    factory.setPassword("qaz123,0");
    factory.setServiceClass(SIOOAAFSOA017.class);
    SIOOAAFSOA017 service=(SIOOAAFSOA017) factory.create();
    ObjectFactory objectFactory=new ObjectFactory();
    SHEET sheet=objectFactory.createDTOAAFSOA017SHEET();
    sheet.setCKGW("50003935");
    sheet.setGWBM("");
    sheet.setSQRQ("20180717");
    DTOAAFSOA017 dtoaafsoa017=objectFactory.createDTOAAFSOA017();
    List<DTOAAFSOA017.SHEET> sheets=dtoaafsoa017.getSHEET();
    sheets.add(sheet);
    DTOAAFSOA017REP dtoaafsoa017rep=service.sioOAAFSOA017(dtoaafsoa017);
    List<DTOAAFSOA017REP.RETURN> returns=dtoaafsoa017rep.getRETURN();
    
    for (int i = 0; i < returns.size(); i++) {
       
		DTOAAFSOA017REP.RETURN dr=returns.get(i);
		String zhxzz=dr.getHXZZ();
		String zrztj=dr.getRZTJ();
		System.out.println(zhxzz+"-"+zrztj);

	}
}
public static void main(String[] args){
	test017 rs = new test017();
	rs.sss();
}

}
