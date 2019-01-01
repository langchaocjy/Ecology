package com.fdrj;

import java.io.PrintStream;
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

public class FYBXAction
  extends BaseBean
  implements Action
{
  public String execute(RequestInfo request)
  {
    String formtable = request.getRequestManager().getBillTableName();
    String requestid = request.getRequestid();
    RequestManager requestManager = request.getRequestManager();
    
    String bxlx = "";//报销类型
    String sfcxjk = "";//是否冲销借款
    
    RecordSet recordSet = new RecordSet();
    
    String sql = "select * from " + formtable + " where requestid=" + requestid;
    recordSet.execute(sql);
    if (recordSet.next())
    {
      bxlx = recordSet.getString("bxlx");
      sfcxjk = recordSet.getString("sfcxjk");
    }
    JSONObject resultObj = null;
    if (bxlx.equals("0"))//报销类型
    {
      if (!sfcxjk.equals("0")) {//冲销借款
        resultObj = executeRcbx(request);//日常报销
      }
    }
    else 
    {
      resultObj = executeGysfk(request);//供应商付款
    }
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
    if ((code == 0) && (sfcxjk.equals("0"))) {//预算扣减成功  是冲销借款
      executeCxjk(request);
    }
    return code == 0 ? "1" : "0";
  }
  
  public JSONObject executeCxjk(RequestInfo request)//冲销借款
  {
    RequestManager requestManager = request.getRequestManager();
    String formtable = requestManager.getBillTableName();
    String requestid = request.getRequestid();
    String workflowid = request.getWorkflowid();
    String src = requestManager.getSrc();
    int nodeid = requestManager.getNodeid();
    
    writeLog("[FYBXAction.executeCxjk] workflowid:" + workflowid + " formtable:" + formtable + " requestid:" + requestid + " nodeid:" + nodeid + " src:" + src);
    
    JSONObject resultObj = null;
    
    RecordSet recordSet = new RecordSet();
    RecordSet recordSet2 = new RecordSet();
    

    boolean isCreateNode = false;
    String sql = "select * from workflow_flownode where workflowid=" + workflowid + " and nodeid=" + nodeid + " and nodetype=0";
    recordSet.execute(sql);
    if (recordSet.next()) {
      isCreateNode = true;
    }
    writeLog("[FYBXAction.executeCxjk] isCreateNode:" + isCreateNode);
    
    String detailTableName = "formtable_main_88_dt1";
    if (src.equals("reject"))
    {
      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt4 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      while (recordSet.next())
      {
        String detailid = recordSet.getString("detailid");
        String bccxje = getNum(recordSet.getString("bccxje"));
        
        sql = "update " + detailTableName + " set syje=syje+" + bccxje + ",spzje=spzje-" + bccxje + " where id=" + detailid;
        recordSet2.execute(sql);
        
        writeLog("[FYBXAction.executeCxjk] 退回 sql0:" + sql);
      }
    }
    else if (isCreateNode)
    {
      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt4 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      while (recordSet.next())
      {
        String detailid = recordSet.getString("detailid");
        String bccxje = getNum(recordSet.getString("bccxje"));
        
        sql = "update " + detailTableName + " set syje=syje-" + bccxje + ",spzje=spzje+" + bccxje + " where id=" + detailid;
        recordSet2.execute(sql);
        writeLog("[FYBXAction.executeCxjk] 冻结 sql1:" + sql);
      }
    }
    else
    {
      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt4 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      while (recordSet.next())
      {
        String detailid = recordSet.getString("detailid");
        String bccxje = getNum(recordSet.getString("bccxje"));
        
        sql = "update " + detailTableName + " set spzje=spzje-" + bccxje + ",ycxje=ycxje+" + bccxje + " where id=" + detailid;
        recordSet2.execute(sql);
        writeLog("[FYBXAction.executeCxjk] 扣减 sql2:" + sql);
      }
    }
    return resultObj;
  }
  
  public JSONObject executeRcbx(RequestInfo request)//日常报销
  {
    RequestManager requestManager = request.getRequestManager();
    String formtable = requestManager.getBillTableName();
    String requestid = request.getRequestid();
    String workflowid = request.getWorkflowid();
    String requestName = requestManager.getRequestname();
    String lastoperator = request.getLastoperator();
    String src = requestManager.getSrc();
    int nodeid = requestManager.getNodeid();
    
    writeLog("[FYBXAction.executeRcbx] workflowid:" + workflowid + " formtable:" + formtable + " requestid:" + requestid + " nodeid:" + nodeid + " src:" + src);
    
    JSONObject resultObj = null;
    ResourceComInfo resourceComInfo = SocialUtil.getResourceComInfo();
    DepartmentComInfo departmentComInfo = EasService.getDepartmentComInfo();
    
    String lcbh = "";//流程编号
    String createrName = "单秋燕"; 
    String bizType = "acc";
    String bxr = "";//报销人
    String sqrq = "";//申请日期
    int bizhong = 0;//币种
    String ywrq = "";//业务日期
    RecordSet recordSet = new RecordSet();
    
    String sql = "select * from " + formtable + " where requestid=" + requestid;
    recordSet.execute(sql);
    if (recordSet.next())
    {
      bxr = recordSet.getString("bxr");//报销人
      sqrq = recordSet.getString("sqrq");//申请日期
      bizhong = recordSet.getInt("bizhong");//币种
      lcbh = recordSet.getString("lcbh");//流程编号
      ywrq = recordSet.getString("ywrq");//业务日期
    }
    String currencyTypeNumber = EasService.getCurrencyTypeNumber(bizhong);//返还币种类别
    
    boolean isCreateNode = false;//是否创建节点
    sql = "select * from workflow_flownode where workflowid=" + workflowid + " and nodeid=" + nodeid + " and nodetype=0";
    recordSet.execute(sql);
    if (recordSet.next()) {
      isCreateNode = true;
    }
    writeLog("[FYBXAction.executeRcbx] isCreateNode:" + isCreateNode);
    if (src.equals("reject"))//如果是退回，则调用返回预算接口
    {
      ReturnBudgetRequest req = new ReturnBudgetRequest();
      req.setNumber(requestid);
      req.setBizType(bizType);
      resultObj = EasService.returnBudget(req);
    }
    else if (isCreateNode)
    {
      String orgUnitNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr));//公司编码   -- 报销人公司
      String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr));//部门编码 -- 报销人部门
      String bizDate = sqrq;//业务日期 -- 申请日期
      String exchangeRate = "1";//汇率 -- 明细表1汇率
      String personNumber = resourceComInfo.getWorkcode(bxr);//报销人 -- 报销人编号
      String userNumber = resourceComInfo.getLastname(bxr);//报销人对应EAS账号 -- 报销人姓名
      String personDeptNumber = orgUnitNumber;//申请人公司编码 -- 申请人公司编码
      
      RequestBudgetRequest req = new RequestBudgetRequest();
      List<RequestBudgetEntryDTO> entryList = new ArrayList();
      
      req.setBizType(bizType);
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
        entry.setExchangeRate(recordSet.getString("huilv"));
        entry.setCompanyNumber(EasService.getSubCode(departmentComInfo.getSubcompanyid1(fycdbm)));
        entry.setDepartmentNumber(EasService.getDeptCode(fycdbm));
        
        entryList.add(entry);
      }
      req.setEntryList(entryList);
      
      resultObj = EasService.requestBudget(req);
    }
    else
    {
      String bizDate = ywrq;
      String companyNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr));
      String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr));
      String creatorNumber = createrName;
      

      PaymentBillRequest req = new PaymentBillRequest();
      List<PaymentBillEntryDTO> entryList = new ArrayList();
      
      req.setActPayAmt("2");
      req.setActPayLocAmt("2");
      req.setBizDate(bizDate);
      req.setCompanyNumber(companyNumber);
      req.setDepartmentNumber(departmentNumber);
      req.setCountryNumber("C01");
      req.setCreatorNumber(creatorNumber);
      req.setCurrencyTypeNumber(currencyTypeNumber);
      req.setExchangeRate("1");
      req.setName(requestName);
      req.setNumber(lcbh);
      req.setSettlementTypeNumber("02");
      req.setSourceBillType("0");
      req.setPaymentTypeNumber("004");
      if ("0".equals(req.getSourceBillType()))
      {
        req.setPayeeType("00004");
        req.setPayeeNumber("999");
        req.setPayTypeNumber("999");
      }
      else if ("1".equals(req.getSourceBillType()))
      {
        req.setPayeeType("00002");
        req.setPayeeNumber("0100127");
        req.setPayTypeNumber("201");
      }
      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      int seq = 0;
      while (recordSet.next())
      {
        seq++;
        PaymentBillEntryDTO entry = new PaymentBillEntryDTO();
        
        entry.setCompanyNumber(EasService.getSubCode(recordSet.getString("fycdgs")));
        entry.setDepartmentNumber(EasService.getDeptCode(recordSet.getString("fycdbm")));
        entry.setExpenseTypeNumber(recordSet.getString("fykm"));
        entry.setAmount(getNum(recordSet.getString("rmbje")));
        entry.setLocalAmt(getNum(recordSet.getString("bwbje")));
//        entry.setSeq(seq);
        entry.setMaterialNumber("");
        entry.setPlatformNumber("");
        entry.setRemark(recordSet.getString("zhaiyao"));
        
        entryList.add(entry);
      }
      req.setEntryList(entryList);
      

      resultObj = EasService.createPaymentBill(req);
      
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
      bxr = recordSet.getString("bxr");
      sqrq = recordSet.getString("sqrq");
      bizhong = recordSet.getInt("bizhong");
      lcbh = recordSet.getString("lcbh");
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
      String orgUnitNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr));
      String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr));
      String bizDate = sqrq;
      
      String exchangeRate = "1";
      String personNumber = resourceComInfo.getWorkcode(bxr);
      String userNumber = resourceComInfo.getLastname(bxr);
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
      

      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt2 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
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
        entry.setExchangeRate(recordSet.getString("huilv"));
        entry.setCompanyNumber(EasService.getSubCode(departmentComInfo.getSubcompanyid1(fycdbm)));
        entry.setDepartmentNumber(EasService.getDeptCode(fycdbm));
        entryList.add(entry);
      }
      req.setEntryList(entryList);
      
      resultObj = EasService.requestBudget(req);
    }
    else
    {
      String bizDate = ywrq;
      String companyNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr));
      String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr));
      String creatorNumber = "钟洁晴";
      

      PaymentBillRequest req = new PaymentBillRequest();
      
      req.setActPayAmt("2");
      req.setActPayLocAmt("2");
      req.setBizDate(bizDate);
      req.setCompanyNumber(companyNumber);
      req.setDepartmentNumber(departmentNumber);
      req.setCountryNumber("C01");
      req.setCreatorNumber(creatorNumber);
      req.setCurrencyTypeNumber(currencyTypeNumber);
      req.setExchangeRate("1");
      req.setName(requestname);
      req.setSettlementTypeNumber("02");
      req.setSourceBillType("1");
      req.setPaymentTypeNumber("004");
      if ("0".equals(req.getSourceBillType()))
      {
        req.setPayeeType("00004");
        req.setPayeeNumber("999");
        req.setPayTypeNumber("999");
      }
      else if ("1".equals(req.getSourceBillType()))
      {
        req.setPayeeType("00002");
        req.setPayTypeNumber("201");
      }
      sql = "select t2.* from " + formtable + " t1," + formtable + "_dt2 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
      recordSet.execute(sql);
      while (recordSet.next())
      {
        List<PaymentBillEntryDTO> entryList = new ArrayList();
        PaymentBillEntryDTO entry = new PaymentBillEntryDTO();
        
        String detailNumber = lcbh + "_" + System.currentTimeMillis();
        req.setNumber(detailNumber);
        req.setPayeeNumber(recordSet.getString("gys"));
        
        String detailid = recordSet.getString("id");
        entry.setCompanyNumber(EasService.getSubCode(recordSet.getString("fycdgs")));
        entry.setDepartmentNumber(EasService.getDeptCode(recordSet.getString("fycdbm")));
        entry.setExpenseTypeNumber(recordSet.getString("fykm"));
        entry.setAmount(getNum(recordSet.getString("rmbje")));
        entry.setLocalAmt(getNum(recordSet.getString("bwbje")));
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
        sql = "update " + formtable + "_dt2 set dh='" + detailNumber + "' where id=" + detailid;
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
  
  public static void main(String[] args)
  {
    try
    {
      String pid = "�´���";
      String str = new String(pid.getBytes("iso-8801"));
      System.out.println("str:" + str);
    }
    catch (Exception localException) {}
  }
}
