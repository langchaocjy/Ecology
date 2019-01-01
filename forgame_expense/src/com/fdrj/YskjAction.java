package com.fdrj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fdrj.services.EASLogin.EASLoginProxyProxy;
import com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxyProxy;
import com.fdrj.services.wsbasedataqueryfacade.client.RequestBudgetEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.RequestBudgetRequest;

import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.interfaces.workflow.action.BaseAction;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import net.sf.json.JSONObject;

public class YskjAction extends BaseAction implements Action {
	public String execute(RequestInfo info) {
		String ssgs = "";
		String ssbm = "";
		String sqrq = "";
   		Property[] ps = info.getMainTableInfo().getProperty();
   		for (Property p : ps) {
        	if("ssgs".equals(p.getName())){
        		ssgs = p.getValue();
        	}
        	if("ssbm".equals(p.getName())){
        		ssbm = p.getValue();
        	}
        	if("sqrq".equals(p.getName())){
        		sqrq = p.getValue();
        	}
   		}
   		DetailTable[] dts = info.getDetailTableInfo().getDetailTable();
   		DetailTable detailTable = dts[0];
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
   			Row[] rows = detailTable.getRow();
   			for (Row row : rows) {
   	   			String rmbje = "";
   	   			String fykm  = "";
   	   			String fycdgs = "";
   	   			String fycdbm = "";
   	 		    String syed = "" ;
   	   			Map<String,String> map = new HashMap<String,String>();
				Cell[] cells = row.getCell();
				for (Cell cell : cells) {
		        	if("rmbje".equals(cell.getName())){
		        		rmbje = cell.getValue();
		        	}
		        	if("fykm".equals(cell.getName())){
		        		fykm = cell.getValue();
		        	}
		        	if("fycdgs".equals(cell.getName())){
		        		fycdgs = cell.getValue();
		        	}
		        	if("fycdbm".equals(cell.getName())){
		        		fycdbm = cell.getValue();
		        	}
		        	if ("syed".equals(cell.getName())) {
						syed = cell.getValue();
					}
				}
				map.put("rmbje", rmbje);
				map.put("fykm", fykm);
				map.put("fycdgs", fycdgs);
				map.put("fycdbm", fycdbm);
				map.put("syed", syed);
				list.add(map);
			}
   			new BaseBean().writeLog("{list          ------------> }  "+list);
   		try
    	{
      	EasService.login();
      	
      	WSWSBudgetFacadeSrvProxyProxy client = new WSWSBudgetFacadeSrvProxyProxy();
      	RequestBudgetRequest rbr = new RequestBudgetRequest();
      	rbr.setNumber(info.getRequestid());
      	rbr.setOrgUnitNumber(EasService.getSubCode(ssgs));
      	rbr.setDepartmentNumber(EasService.getDeptCode(ssbm));
      	rbr.setBizDate(sqrq);
      	rbr.setCurrencyTypeNumber("BB01");
      	rbr.setExchangeRate("1");
      	rbr.setPersonNumber(EasService.getWorkCode(info.getCreatorid()));
      	rbr.setUserNumber(info.getRequestManager().getUser().getUsername());
      	rbr.setPersonDeptNumber(EasService.getSubCode(ssgs));
      	
      	List<RequestBudgetEntryDTO> entryList = new ArrayList<RequestBudgetEntryDTO>();
      	for (Map<String,String> map : list) {
      		String rmbje = map.get("rmbje");
      		String fykm = map.get("fykm");
      		String fycdgs = map.get("fycdgs");
      		String fycdbm = map.get("fycdbm");
      		String syed = map.get("syed");
      		if (syed != "" && !syed.equals("")) {
      			RequestBudgetEntryDTO dto = new RequestBudgetEntryDTO();
      			dto.setExpenseTypeNumber(fykm);//费用科目
      			dto.setReqBizDate(sqrq); //业务日期
      			dto.setCurrencyTypeNumber("BB01");
      			dto.setExchangeRate("1");
      			dto.setAmount(getNum(rmbje)); //申请报销金额
      			dto.setAmountOri(getNum(rmbje));// 申请报销金额公司
      			dto.setCompanyNumber(EasService.getSubCode(fycdgs));
      			dto.setDepartmentNumber(EasService.getDeptCode(fycdbm));
      			entryList.add(dto);
			}
		}
        new BaseBean().writeLog("{entryList------------->  }  "+entryList);
      	rbr.setEntryList(entryList);
      	String str = JSON.toJSONString(rbr);
      	this.getLog().error("json:"+str);
      	String result = client.requestBudget(str);
        JSONObject resultObj = JSONObject.fromObject(result);
        this.getLog().error("预算扣减结果:"+resultObj);
        String code =resultObj.get("code").toString();
        String msg = resultObj.get("msg").toString();
        if("1".equals(code)){
    		info.getRequestManager().setMessagecontent("预算扣减失败:"+msg);
         	return FAILURE_AND_CONTINUE;
        }
        EasService.logOut();
        }
        catch (Exception e)
        {
		info.getRequestManager().setMessagecontent("预算扣减出错!");
     	return FAILURE_AND_CONTINUE;
        }
        return SUCCESS;
	}
	  public String getNum(String num)
	  {
	    return num.replaceAll(",", "");
	  }
	  
	  public static void main(String[] args){
		  
	  }
}
