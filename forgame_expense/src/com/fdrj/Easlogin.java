package com.fdrj;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.fdrj.services.EASLogin.EASLoginProxyProxy;
import com.fdrj.services.EASLogin.client.WSContext;
import com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxyProxy;
import com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException;
import com.fdrj.services.wsbasedataqueryfacade.client.BudgetEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.BudgetRequest;

public class Easlogin {
  public static void main(String[] args){
	  login();
	  getBudget();
  }
  
  public static void login(){
	  EASLoginProxyProxy client = new EASLoginProxyProxy();
	  try {
		WSContext ctx = client.login("wsoa", "wsoa", "eas", "EASTEST0801", "L2", 2);
		System.out.println(ctx.getSessionId());
		System.out.println(ctx.getUserName());
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  public static void logOut(){
	  EASLoginProxyProxy client = new EASLoginProxyProxy();
	  try{
		  boolean s =client.logout("wsoa", "eas", "EASTEST0801", "l2");
		  System.out.println(s);
	  }catch(RemoteException e){
		  e.printStackTrace();
	  }catch (Exception e) {
          e.printStackTrace();
	  }
  }
  public static void getBudget(){
	  try {
	  BudgetRequest req = new BudgetRequest();
		req.setBizDate("2018-08-24");
		req.setCurrencyTypeNumber("BB04");
		req.setOrgUnitNumber("004");
		req.setDepartmentNumber("004CW001");
		req.setBizType("req");
		
		List<BudgetEntryDTO> list = new ArrayList<BudgetEntryDTO>();
		BudgetEntryDTO entryDTO = new BudgetEntryDTO();
		entryDTO.setExpenseTypeNumber("01002");
		entryDTO.setReqBizDate("2018-08-24");
		entryDTO.setCurrencyTypeNumber("BB04");
		entryDTO.setCompanyNumber("004");
		entryDTO.setDepartmentNumber("004CW001");
		list.add(entryDTO);
		
		req.setEntryList(list);
	    WSWSBudgetFacadeSrvProxyProxy client = new WSWSBudgetFacadeSrvProxyProxy();
        String str = JSON.toJSONString(req);
        System.out.println("-->"+str);
        String resp = client.getBudget(str);
        System.out.println("-->"+resp);
		} catch (WSInvokeException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
  }
}
