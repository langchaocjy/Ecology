package com.fdrj.gysfk;

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
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.social.SocialUtil;
import weaver.workflow.request.RequestManager;

public class FYBXAction
  extends BaseBean
  implements Action
{
  public String execute(RequestInfo request)
  {
    String formtable = request.getRequestManager().getBillTableName();
    String requestid = request.getRequestid();
    RequestManager requestManager = request.getRequestManager();
    JSONObject resultObj = executeGysfk(request);//供应商付款
    int code = 0;//{"code":0,"msg":"预算扣减成功"}
    if (resultObj != null)
    {
      code = resultObj.getInt("code");
      String msg = resultObj.getString("msg");
      String params = resultObj.getString("params");
      if (code != 0)
      {
        requestManager.setMessageid(requestid);
        requestManager.setMessage("EAS接口调用失败");
        requestManager.setMessagecontent("EAS接口调用失败 code:" + code + " msg:" + msg + " params:" + params);
      }
    }
    return code == 0 ? "1" : "0";
  }
  
  public JSONObject executeGysfk(RequestInfo request)
  {
    RequestManager requestManager = request.getRequestManager();
    String formtable = requestManager.getBillTableName();
    String requestname = requestManager.getRequestname();
    String requestid = request.getRequestid();
    String workflowid = request.getWorkflowid();
    String lastoperator = request.getLastoperator();
    String src = requestManager.getSrc();
    int nodeid = requestManager.getNodeid();
    
    writeLog("[FYBXAction.executeGysfk] workflowid:" + workflowid + " formtable:" + formtable + " requestid:" + requestid + " nodeid:" + nodeid + " src:" + src);
    
    JSONObject resultObj = null;
    ResourceComInfo resourceComInfo = SocialUtil.getResourceComInfo();
    DepartmentComInfo departmentComInfo = EasService.getDepartmentComInfo();
    
    String lcbh = "";
    String createrName = "单秋燕";
    
    String bxr = "";
    String sqrq = "";
    int bizhong = 0;
    String ywrq = "";
    
    RecordSet recordSet = new RecordSet();
    RecordSet recordSet2 = new RecordSet();
    
    String sql = "select * from " + formtable + " where requestid=" + requestid;
    recordSet.execute(sql);
    if (recordSet.next())
    {
      bxr = recordSet.getString("sqr");
      sqrq = recordSet.getString("sqrq");
//      bizhong = recordSet.getInt("skbb");
      lcbh = recordSet.getString("lsh");
      ywrq = recordSet.getString("ywrq");
    }
    String currencyTypeNumber = EasService.getCurrencyTypeNumber(bizhong);
    

    boolean isCreateNode = false;
    sql = "select * from workflow_flownode where workflowid=" + workflowid + " and nodeid=" + nodeid + " and nodetype=0";
    recordSet.execute(sql);
    if (recordSet.next()) {
      isCreateNode = true;
    }
    writeLog("[FYBXAction.executeGysfk] isCreateNode:" + isCreateNode);
    if (src.equals("reject"))
    {
      ReturnBudgetRequest req = new ReturnBudgetRequest();
      req.setNumber(requestid);
      resultObj = EasService.returnBudget(req);
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
      String orgUnitNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr_s));
      String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr_s));
      String bizDate = sqrq;
      
      String exchangeRate = "1";
      String personNumber = resourceComInfo.getWorkcode(bxr_s);
      String userNumber = resourceComInfo.getLastname(bxr_s);
      String personDeptNumber = orgUnitNumber;
      
      RequestBudgetRequest req = new RequestBudgetRequest();
      List<RequestBudgetEntryDTO> entryList = new ArrayList();
      
      req.setBizDate(bizDate);
      req.setCurrencyTypeNumber(currencyTypeNumber);
      req.setDepartmentNumber(departmentNumber);
      req.setExchangeRate(exchangeRate);
      req.setNumber(requestid);
      req.setOrgUnitNumber(orgUnitNumber);
      req.setPersonDeptNumber(personDeptNumber);
      req.setPersonNumber(personNumber);
      req.setUserNumber(userNumber);
      

      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      while (recordSet.next())
      {
        String fycdbm = recordSet.getString("fycdbm");
        
        RequestBudgetEntryDTO entry = new RequestBudgetEntryDTO();
        entry.setReqBizDate(bizDate);
        entry.setExpenseTypeNumber(recordSet.getString("fykm"));
        entry.setAmount(getNum(recordSet.getString("rmbje")));
        entry.setAmountOri(getNum(recordSet.getString("rmbje")));
        entry.setCurrencyTypeNumber(currencyTypeNumber);
        entry.setExchangeRate(recordSet.getString("skhl"));
        entry.setCompanyNumber(EasService.getSubCode(departmentComInfo.getSubcompanyid1(fycdbm)));
        entry.setDepartmentNumber(EasService.getDeptCode(fycdbm));
        entryList.add(entry);
      }
      req.setEntryList(entryList);
      
      resultObj = EasService.requestBudget(req);
    }
    else
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
    	String zfdep = "",zfgs="";
    	rs.execute("select zfbm,zfgs from " + formtable + " where requestid='" + requestid + "'");
    	if (rs.next()) {
			zfdep=rs.getString("zfbm");
			zfgs=rs.getString("zfgs");
		}
      String bizDate = ywrq;//申请日期业务日期
//      String companyNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr_s));
      String companyNumber = EasService.getSubCode(zfgs);
//      String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr_s));
      String departmentNumber = EasService.getDeptCode(zfdep);
      String creatorNumber = "单秋燕";//报销人

      PaymentBillRequest req = new PaymentBillRequest();
      
      req.setActPayAmt("2");//
      req.setActPayLocAmt("2");
      req.setBizDate(bizDate);//业务日期
      req.setCompanyNumber(companyNumber);//公司编码
      req.setDepartmentNumber(departmentNumber);//部门编码
      req.setCountryNumber("C01");//收款人国家
      req.setCreatorNumber(creatorNumber);//制单人账号
      req.setCurrencyTypeNumber(currencyTypeNumber);//币别
      req.setExchangeRate("1");//汇率
      req.setName(requestname);//姓名
      req.setSettlementTypeNumber("02");//结算方式
      req.setSourceBillType("1");//付款单类型
      req.setPaymentTypeNumber("004");//付款方式
      req.setAdminOrgUnitNumber(departmentNumber);
      if ("0".equals(req.getSourceBillType()))
      {
        req.setPayeeType("00004");//往来户类型
        req.setPayeeNumber("");//往来户编号
        req.setPayTypeNumber("999");//付款类型编码
      }
      else if ("1".equals(req.getSourceBillType()))
      {
        req.setPayeeType("00002");
        req.setPayTypeNumber("201");
      }
      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      while (recordSet.next())
      {
        List<PaymentBillEntryDTO> entryList = new ArrayList();
        PaymentBillEntryDTO entry = new PaymentBillEntryDTO();
        
        String detailNumber = lcbh + "_" + System.currentTimeMillis();
        req.setNumber(detailNumber);//单号
        req.setPayeeNumber(recordSet.getString("gys"));//往来户编号
        
        String detailid = recordSet.getString("id");
        entry.setCompanyNumber(EasService.getSubCode(recordSet.getString("fycdgs")));//公司编码
        entry.setDepartmentNumber(EasService.getDeptCode(recordSet.getString("fycdbm")));//部门编码
        entry.setExpenseTypeNumber(recordSet.getString("fykm"));
        entry.setAmount(getNum(recordSet.getString("rmbje")));
        entry.setLocalAmt(getNum(recordSet.getString("rmbje")));
        entry.setSeq("1");
        entry.setMaterialNumber(recordSet.getString("youxi"));
        entry.setPlatformNumber(recordSet.getString("pingtai"));
        entry.setRemark(recordSet.getString("zhaiyao"));
        
        entryList.add(entry);
        req.setEntryList(entryList);
        
        resultObj = EasService.createPaymentBill(req);
        
        int code = resultObj.getInt("code");
        if (code != 0) {
          break;
        }
        sql = "update " + formtable + "_dt1 set dh='" + detailNumber + "' where id=" + detailid;
        recordSet2.execute(sql);
        

        UpdatePaymentBillRequest req2 = new UpdatePaymentBillRequest();
        List<String> seqList = new ArrayList();
        req2.setNumber(detailNumber);
        req2.setSourceBillType("1");
        req2.setAuditorNumber(createrName);
        
        seqList.add("1");
        
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
