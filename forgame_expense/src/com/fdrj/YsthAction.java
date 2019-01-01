package com.fdrj;

import com.alibaba.fastjson.JSON;
import com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxyProxy;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillRequest;

import net.sf.json.JSONObject;
import weaver.interfaces.workflow.action.Action;
import weaver.interfaces.workflow.action.BaseAction;
import weaver.soa.workflow.request.RequestInfo;

public class YsthAction  extends BaseAction implements Action {
	public String execute(RequestInfo info) {
		EasService.login();
		try{             
		PaymentBillRequest pbr = new PaymentBillRequest();
            pbr.setNumber(info.getRequestid());
	    JSONObject resultObj = new JSONObject();
	    String str = JSON.toJSONString(pbr);
	    WSWSBudgetFacadeSrvProxyProxy client = new WSWSBudgetFacadeSrvProxyProxy();
	    String result = client.returnBudget(str);
	    resultObj = JSONObject.fromObject(result);
	    
	    this.getLog().error("Ô¤ËãÍË»Ø½á¹û:"+ resultObj);
		EasService.logOut();
		}
	    catch (Exception e)
	    {
           e.printStackTrace();
	    }
		return SUCCESS;
	}
}
