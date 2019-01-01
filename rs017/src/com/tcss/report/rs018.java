package com.tcss.report;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.rs018.DTOAAFSOA018EXT.SHEET;
import out.deal.afs.rs018.SIOOAAFSOA018;
import out.deal.afs.rs018.*;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class rs018 extends BaseBean{
	public JSONArray setRs018(String sqrq,String bm){
	String url="http://58.62.17.77:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA018&interfaceNamespace=urn:bk2:afs:deal:out";
	String username = new BaseBean().getPropValue("tcsssaphr", "username_hr");
    String password = new BaseBean().getPropValue("tcsssaphr", "password_hr");
    JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
    factory.setAddress(url);
    factory.setUsername(username);
    factory.setPassword(password);
    factory.setServiceClass(SIOOAAFSOA018.class);
    SIOOAAFSOA018 service=(SIOOAAFSOA018) factory.create();
    
    ObjectFactory objectFactory=new ObjectFactory();
    SHEET sheet = objectFactory.createDTOAAFSOA018EXTSHEET();
    sheet.setSQRQ(sqrq);
    sheet.setXQBM(bm);
    
    DTOAAFSOA018EXT dtoaafsoa018ext = objectFactory.createDTOAAFSOA018EXT();
    dtoaafsoa018ext.setSHEET(sheet);
    
    DTOAAFSOA018EXTREP dtoaafsoa018extrep = service.sioOAAFSOA018(dtoaafsoa018ext);
    List<DTOAAFSOA018EXTREP.SHEET> sheets = dtoaafsoa018extrep.getSHEET();
    JSONArray  jsonarray =new JSONArray();
    JSONObject json = new JSONObject();
    if(!sheets.isEmpty()){
    	 for(int i = 0; i<sheets.size();i++){
    		 DTOAAFSOA018EXTREP.SHEET rs = sheets.get(i);
    		    String ckgw = Util.null2String(rs.getCKGW());//018参考岗位
    	    	String gwbm = Util.null2String(rs.getGWBM());//018空岗  岗位编码   10000555
    	    	String shorts = Util.null2String(rs.getSHORT());//018空岗  中文名称   成本会计
    	    	json.put("参考岗位", ckgw);
    	    	json.put("岗位编码", gwbm);
    	    	json.put("岗位名称", shorts);
    	    	jsonarray.add(json);
    	 }
    }else{
    	writeLog("该部门没有空岗数据");
    }
	return jsonarray;
}

}

