package com.fdrj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fdrj.services.WSWSPaymentFacade.WSWSPaymentFacadeSrvProxyProxy;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.RequestBudgetEntryDTO;

import net.sf.json.JSONObject;
import weaver.interfaces.workflow.action.Action;
import weaver.interfaces.workflow.action.BaseAction;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;

public class ScfkdAction  extends BaseAction implements Action {
	public String execute(RequestInfo info) {
		info.getRequestid();
		String ssgs = "";
		String sqrq = "";
		String sqr = "";
		String payTypeNumber = "999"; //采购付款默认201，报销付款默认999 
		String paymentTypeNumber = "004";
		String settlementTypeNumber ="02";
		String payeeType ="00004"; //（00004-公司；00001-客户；00002-供应商），采购付款默认00002，报销付款默认00004
	    String countryNumber = "C01";
	    String sourceBillType = "0"; //0-报销付款；1-采购付款
	    String currencyTypeNumber = "BB01";
	    String exchangeRate = "1";
		Property[] ps = info.getMainTableInfo().getProperty();
   		for (Property p : ps) {
        	//this.getLog().error("property   name:"+p.getName()+"    value:"+p.getValue());
        	if("ssgs".equals(p.getName())){
        		ssgs = p.getValue();
        	}
        	if("sqr".equals(p.getName())){
        		sqr = p.getValue();
        	}
        	if("sqrq".equals(p.getName())){
        		sqrq = p.getValue();
        	}
   		}
   		DetailTable[] dts = info.getDetailTableInfo().getDetailTable();
   		DetailTable detailTable = dts[0];
   		int count = 0;
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
   			//this.getLog().error("detailTable:"+detailTable.getTableDBName());
   			Row[] rows = detailTable.getRow();
   			for (Row row : rows) {
   				count++;
   	   			String youxi = "";
   	   			String fykm  = "";
   	   			String pingtai = "";
   	   			String djyb = "";
   	   			String djbb = "";
   	   			String skhl = "";
   	   			String rmbje = "";
   	   					
   	   			Map<String,String> map = new HashMap<String,String>();
				Cell[] cells = row.getCell();
				for (Cell cell : cells) {
					//this.getLog().error("cell  -- name:"+cell.getName()+"        value:"+cell.getValue());
		        	if("youxi".equals(cell.getName())){
		        		youxi = cell.getValue();
		        	}
		        	if("fykm".equals(cell.getName())){
		        		fykm = cell.getValue();
		        	}
		        	if("pingtai".equals(cell.getName())){
		        		pingtai = cell.getValue();
		        	}
		        	if("djyb".equals(cell.getName())){
		        		djyb = cell.getValue();
		        	}
		        	if("djbb".equals(cell.getName())){
		        		djbb = cell.getValue();
		        	}
		        	if("skhl".equals(cell.getName())){
		        		skhl = cell.getValue();
		        	}
		        	if("rmbje".equals(cell.getName())){
		        		rmbje = cell.getValue();
		        	}
		        	
				}
				map.put("num", String.valueOf(count));
				map.put("youxi", youxi);
				map.put("fykm", fykm);
				map.put("pingtai", pingtai);
				map.put("djyb", djyb);
				map.put("djbb", djbb);
				map.put("skhl", skhl);
				map.put("rmbje", rmbje);
				list.add(map);
			}
	
		try{

      	for (Map<String,String> map : list) {
      		String num = map.get("num");
      		String fykm = map.get("fykm");
      		String youxi = map.get("youxi");
      		String pingtai = map.get("pingtai");
      		String djyb = map.get("djyb");
      		String djbb = map.get("djbb");
      		String skhl = map.get("skhl");
      		String rmbje = map.get("rmbje");
      	//主表
      		PaymentBillRequest req = new PaymentBillRequest();
    		req.setNumber(info.getRequestid());
    		req.setCompanyNumber(EasService.getSubCode(ssgs));
    		req.setBizDate(sqrq);
    		req.setPayTypeNumber(payTypeNumber);
    		req.setPaymentTypeNumber(paymentTypeNumber);
    		req.setCurrencyTypeNumber(djbb);
    		req.setExchangeRate(skhl);
    		req.setSettlementTypeNumber(settlementTypeNumber);
    		req.setPayeeType(payeeType);
    		req.setCountryNumber(countryNumber);
    		req.setCreatorNumber(EasService.getWorkCode(info.getCreatorid()));
    		req.setSourceBillType(sourceBillType);
    		//明细
            List<PaymentBillEntryDTO> entryList = new ArrayList<PaymentBillEntryDTO>();
      		PaymentBillEntryDTO dto = new PaymentBillEntryDTO();
      		dto.setSeq("1");
      		dto.setExpenseTypeNumber(fykm);
      		dto.setMaterialNumber(youxi);
      		dto.setPlatformNumber(pingtai);
      		
      		dto.setAmount(djyb);
      		dto.setLocalAmt(rmbje);
      		entryList.add(dto);
    		req.setEntryList(entryList);
    		String str = JSON.toJSONString(req);
    		WSWSPaymentFacadeSrvProxyProxy client = new WSWSPaymentFacadeSrvProxyProxy();
    		String result = client.insertPaymentBill(str);
    		JSONObject resultObj = new JSONObject();
    		resultObj = JSONObject.fromObject(result);
            String code =resultObj.get("code").toString();
            String msg = resultObj.get("msg").toString();
            if("1".equals(code)){
        		info.getRequestManager().setMessagecontent("生成第"+num+"个付款单失败:"+msg);
             	return FAILURE_AND_CONTINUE;
            }
        	EasService.logOut();
      	}
        }catch(Exception e){
        	info.getRequestManager().setMessagecontent("生成付款单失败!请检查表单数据,或者联系管理员!");
         	return FAILURE_AND_CONTINUE;
        }
		
		return SUCCESS;
	}
}
