package com.fdrj;

import java.util.ArrayList;
import java.util.List;

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

public class FYBXAction_p extends BaseBean implements Action{
    
	@Override
	public String execute(RequestInfo request) {
		String formtable = request.getRequestManager().getBillTableName();
		String requestid = request.getRequestid();
		RequestManager requestManager = request.getRequestManager();
		RecordSet rs = new RecordSet();
		
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
			if (code != 0) {
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
	    String ywrq = "";
	    String createrName = "单秋燕";
	    String bizType = "acc";
	    RecordSet recordSet = new RecordSet();
	    
	    String sql = "select * from "+formtable+" where requestid ='"+requestid+"'";
	    recordSet.execute(sql);
	    if (recordSet.next()) {
			bxr = recordSet.getString("bxr");//报销人
			sqrq = recordSet.getString("sqrq");//申请日期
//			bizhong = recordSet.getInt("skbb");//币种
			lcbh = recordSet.getString("lsh");//流程编号
			ywrq = recordSet.getString("ywrq");//业务日期
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
	    	 writeLog("[bxr_s ---------- >  ] "+bxr_s);
	    	 String orgUnitNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr_s));//公司编码   -- 报销人公司
	         String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr_s));//部门编码 -- 报销人部门
	         String bizDate = sqrq;//业务日期 -- 申请日期
	         String exchangeRate = "1";//汇率 -- 明细表1汇率
	         
	         String personNumber = resourceComInfo.getWorkcode(bxr_s);//报销人 -- 报销人编号
	         writeLog("[personNumber------> ] "+personNumber);
	         String userNumber = resourceComInfo.getLastname(bxr_s);//报销人对应EAS账号 -- 报销人姓名
	         writeLog("[bxr账号 -------- >  ] "+userNumber);
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
	             String fycdbm = recordSet.getString("yscdbm");
	             RequestBudgetEntryDTO entry = new RequestBudgetEntryDTO();
	             entry.setExpenseTypeNumber(recordSet.getString("fykm"));//费用项目编码--费用科目
	             entry.setReqBizDate(bizDate);//业务发生日期
	             entry.setCurrencyTypeNumber(currencyTypeNumber);//明细币别
	             entry.setExchangeRate(recordSet.getString("skhl"));//明细汇率--收款汇率
	             entry.setAmount(getNum(recordSet.getString("rmbje")));//支付金额--人民币金额
	             entry.setAmountOri(getNum(recordSet.getString("rmbje")));//支付本位币金额--人民币金额
	             entry.setCompanyNumber(EasService.getSubCode(departmentComInfo.getSubcompanyid1(fycdbm)));
	             entry.setDepartmentNumber(EasService.getDeptCode(fycdbm));
	             entryList.add(entry);
	           }
	           req.setEntryList(entryList);
	           resultObj = EasService.requestBudget(req);//扣减预算
	    }
	    else
	    {
	    	 String bizDate = ywrq;
	         RecordSet rs = new RecordSet();
	         rs.execute("select accounttype,belongto from hrmresource where id = '"+bxr+"'");
	         if (rs.next()) 
	         {
				 String accounttype = rs.getString("accounttype");
				 if (accounttype == "1" || accounttype.equals("1")) 
				 {
					bxr = rs.getString("belongto");
				 }
				 else
				 {
					 bxr = bxr;
				 }
			 } 
		    	String zfgs="";
		    	rs.execute("select zfgs from " + formtable + " where requestid='" + requestid + "'");
		    	if (rs.next()) {
					zfgs=rs.getString("zfgs");
				}
	         writeLog("[生成付款单的报销人id---------------》] " +bxr);
//	         String companyNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr));
	         String companyNumber = EasService.getSubCode(zfgs);
	         String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr));
	         String creatorNumber = createrName;
	         
	         PaymentBillRequest req = new PaymentBillRequest();
	         List<PaymentBillEntryDTO> entryList = new ArrayList();
	         writeLog("[流水号----------》  ] "+lcbh);
	         req.setNumber(lcbh);
	         req.setCompanyNumber(companyNumber);
	         req.setBizDate(bizDate);
	         req.setPaymentTypeNumber("004");
	         req.setCurrencyTypeNumber(currencyTypeNumber);
	         req.setExchangeRate("1");
	         req.setSettlementTypeNumber("02");
	         req.setCountryNumber("C01");
	         req.setCreatorNumber(creatorNumber);
	         req.setSourceBillType("0");
	         req.setActPayAmt("2");
	         req.setActPayLocAmt("2");
	         req.setDepartmentNumber(departmentNumber);
	         req.setName(requestName);
	         req.setAdminOrgUnitNumber(departmentNumber);//传公司带部门
	         
	         if ("0".equals(req.getSourceBillType()))
	         {
	           req.setPayeeType("00002");
//	           req.setPayeeNumber("999");
	           req.setPayeeNumber("");
	           req.setPayTypeNumber("999");//报销付款默认999
	         }
	         else if ("1".equals(req.getSourceBillType()))
	         {
	           req.setPayeeType("00002");
//	           req.setPayeeNumber("0100127");
	           req.setPayeeNumber("");
	           req.setPayTypeNumber("201");//采购付款默认201
	         }
	         sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
	         recordSet.execute(sql);
	         int seq = 0;
	         while (recordSet.next())
	         {
	           seq++;
	           PaymentBillEntryDTO entry = new PaymentBillEntryDTO();
	           
	           entry.setSeq(String.valueOf(seq));
	           entry.setExpenseTypeNumber(recordSet.getString("fykm"));
	           entry.setMaterialNumber("");
	           entry.setPlatformNumber("");
	           entry.setAmount(getNum(recordSet.getString("bxjeskbb")));
	           entry.setLocalAmt(getNum(recordSet.getString("bxjeskbb")));
	           entry.setCompanyNumber(EasService.getSubCode(recordSet.getString("yscdgs")));
	           entry.setDepartmentNumber(EasService.getDeptCode(recordSet.getString("yscdbm")));
	           entry.setRemark(recordSet.getString("zy"));
	           
	           entryList.add(entry);
	         }
	         req.setEntryList(entryList);
	         

	         resultObj = EasService.createPaymentBill(req);//创建订单
	         
	         int code = resultObj.getInt("code");
	         if (code == 0)
	         {
	           UpdatePaymentBillRequest req2 = new UpdatePaymentBillRequest();
	           List<String> seqList = new ArrayList();
	           req2.setNumber(lcbh);
	           req2.setSourceBillType("0");
	           req2.setAuditorNumber(createrName);
	           
	           sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
	           recordSet.execute(sql);
	           
	           int seq1 = 0;
	           while (recordSet.next())
	           {
	             seq1++;
	             seqList.add(String.valueOf(seq1));
	           }
	           req2.setSeqList(seqList);
	           resultObj = EasService.generateVoucher(req2);
	         }
	    }
		return resultObj;
	}
	  public String getNum(String num)
	  {
	    return num.replaceAll(",", "");
	  }
}
