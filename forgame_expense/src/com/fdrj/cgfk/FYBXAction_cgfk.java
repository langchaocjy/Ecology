package com.fdrj.cgfk;

import java.util.ArrayList;
import java.util.List;
import com.fdrj.EasService;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.RequestBudgetEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.RequestBudgetRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.ReturnBudgetRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.UpdatePaymentBillRequest;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.social.SocialUtil;
import weaver.workflow.request.RequestManager;

public class FYBXAction_cgfk extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo request) {
		String formtable = request.getRequestManager().getBillTableName();
		String requestid = request.getRequestid();
		RequestManager requestManager = request.getRequestManager();
		
		JSONObject resultObj = null;
		try {
			resultObj = executeRcbx(request);
		} catch (Exception e) {
			writeLog("[resultObj]--->没有执行日常报销接口");
			e.printStackTrace();
		}
		int code = 0;
		if (resultObj != null) 
		{
			code = resultObj.getInt("code");
			String msg = resultObj.getString("msg");
			String params = resultObj.getString("params");
			if (code != 0) 
			{
				requestManager.setMessageid(requestid);
				requestManager.setMessage("EAS接口调用失败");
				requestManager.setMessagecontent("EAS接口调用失败 code："+code+" msg: "+msg+" params: "+params);
			}
		}
		return code== 0 ? "1":"0";
	}

	private JSONObject executeRcbx(RequestInfo request) {
		RequestManager requestManager = request.getRequestManager();
		String formtable = requestManager.getBillTableName();
		String requestid = request.getRequestid();
		String workflowid = request.getWorkflowid();
		String requestName = requestManager.getRequestname();
		String lastoperator = request.getLastoperator();
		String src = requestManager.getSrc();
		int nodeid = requestManager.getNodeid();

	    JSONObject resultObj = null;
	    ResourceComInfo resourceComInfo = SocialUtil.getResourceComInfo();
	    DepartmentComInfo departmentComInfo = EasService.getDepartmentComInfo();
	    
	    String bxr = "";
	    String sqrq = "";
	    int bizhong = 0;
	    String lcbh = "";
	    String createrName = "单秋燕";
	    String bizType = "acc";
	    RecordSet recordSet = new RecordSet();
	    
	    String sql = "select * from "+formtable+" where requestid ='"+requestid+"'";
	    recordSet.execute(sql);
	    if (recordSet.next()) 
	    {
			bxr = recordSet.getString("sqr");//报销人
			sqrq = recordSet.getString("sqrq");//申请日期
//			bizhong = recordSet.getInt("sqbb");//币种
			lcbh = recordSet.getString("lsh");//流程编号
		}
	    String currencyTypeNumber = EasService.getCurrencyTypeNumber(bizhong);
	    
	    boolean isCreateNode = false;
	    sql = "select * from workflow_flownode where workflowid=" + workflowid + " and nodeid=" + nodeid + " and nodetype=0";
	    recordSet.execute(sql);
	    if (recordSet.next()) 
	    {
			isCreateNode = true;
		}
	 
	    if (src.equals("reject"))
	    {
			ReturnBudgetRequest req = new ReturnBudgetRequest();
			req.setNumber(requestid);
			req.setBizType(bizType);
			resultObj = EasService.returnBudget(req);//返还预算
		}
	    else if (isCreateNode)
	    {
	    	String bxr_s = "";
	    	RecordSet rs = new RecordSet();
	    	rs.execute("select accounttype,belongto from hrmresource where id = '"+bxr+"'");
	    	if (rs.next()) 
	    	{
	    		String accounttype = rs.getString("accounttype");
	    		if (accounttype == "1" || accounttype.equals("1")) 
	    		{
	    			bxr_s = rs.getString("belongto");
	    		}
	    		else
	    		{
	    			bxr_s = bxr;
	    		}
	    	} 
	    	 String orgUnitNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr_s));//公司编码   -- 报销人公司
	         String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr_s));//部门编码 -- 报销人部门
	         String bizDate = sqrq;//业务日期 -- 申请日期
	         String exchangeRate = "1";//汇率 -- 明细表1汇率
	         
	         String personNumber = resourceComInfo.getWorkcode(bxr_s);//报销人 -- 报销人编号
	         String userNumber = resourceComInfo.getLastname(bxr_s);//报销人对应EAS账号 -- 报销人姓名
	         String personDeptNumber = orgUnitNumber;//申请人公司编码 -- 申请人公司编码
	         
	         RequestBudgetRequest req = new RequestBudgetRequest();
	         List<RequestBudgetEntryDTO> entryList = new ArrayList();
	         
	         req.setBizType(bizType);//单号
	         req.setNumber(requestid);//单号
	         req.setOrgUnitNumber(orgUnitNumber);//公司编码
	         req.setDepartmentNumber(departmentNumber);//部门编码
	         req.setBizDate(bizDate);//业务日期
	         req.setCurrencyTypeNumber(currencyTypeNumber);//币别编码
	         req.setExchangeRate(exchangeRate);//汇率
	         req.setPersonNumber(personNumber);//报销人
	         req.setUserNumber(userNumber);//报销人对应eas账号
	         req.setPersonDeptNumber(personDeptNumber);//申请人公司编码
	         

	         sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
	         recordSet.execute(sql);
	         while (recordSet.next())
	         {
	             String fycdbm = recordSet.getString("fycdbm");
	             RequestBudgetEntryDTO entry = new RequestBudgetEntryDTO();
	             
	             entry.setExpenseTypeNumber(recordSet.getString("fykm"));//费用项目编码--费用科目
	             entry.setReqBizDate(bizDate);//业务发生日期
	             entry.setCurrencyTypeNumber(currencyTypeNumber);//明细币别
	             entry.setExchangeRate("1");//明细汇率--收款汇率
	             entry.setAmount(getNum(recordSet.getString("rmbje")));//支付金额--人民币金额
	             entry.setAmountOri(getNum(recordSet.getString("rmbje")));//支付本位币金额--人民币金额
	             entry.setCompanyNumber(EasService.getSubCode(departmentComInfo.getSubcompanyid1(fycdbm)));
	             entry.setDepartmentNumber(EasService.getDeptCode(fycdbm));
	             
	             entryList.add(entry);
	           }
	           req.setEntryList(entryList);
	           
	           resultObj = EasService.requestBudget(req);//扣减预算
	    }
		return resultObj;
	}
	  public String getNum(String num)
	  {
	    return num.replaceAll(",", "");
	  }
}
