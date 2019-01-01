package com.fdrj;

import com.alibaba.fastjson.JSON;
import com.eas.util.ConfigUtil;
import com.fdrj.services.EASLogin.EASLoginProxyProxy;
import com.fdrj.services.EASLogin.client.WSContext;
import com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxyProxy;
import com.fdrj.services.WSWSPaymentFacade.WSWSPaymentFacadeSrvProxyProxy;
import com.fdrj.services.wsbasedataqueryfacade.client.BudgetEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.BudgetRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.RequestBudgetRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.ReturnBudgetRequest;
import com.fdrj.services.wsbasedataqueryfacade.client.UpdatePaymentBillRequest;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.hrm.company.DepartmentComInfo;

public class EasService
{
  public static BaseBean baseBean = new BaseBean();
  
  public static JSONObject generateVoucher(UpdatePaymentBillRequest req)
  {
    JSONObject resultObj = new JSONObject();
    try
    {
      login();
      WSWSPaymentFacadeSrvProxyProxy client = new WSWSPaymentFacadeSrvProxyProxy();
      
      String str = JSON.toJSONString(req);
      baseBean.writeLog("[EasService.generateVoucher] req:" + str);
      

      String result = client.generateVoucher(str);
      baseBean.writeLog("[EasService.generateVoucher] result:" + result);
      
      resultObj = JSONObject.fromObject(result);
    }
    catch (Exception e)
    {
      baseBean.writeLog("[EasService.generateVoucher] 付款单生成凭据失败 error:" + e.toString());
      baseBean.writeLog(e);
      
      resultObj.put("code", Integer.valueOf(-1));
      resultObj.put("msg", "付款单生成凭据失败 error:" + e.toString());
    }
    resultObj.put("params", JSON.toJSONString(req));
    return resultObj;
  }
  
  public static JSONObject createPaymentBill(PaymentBillRequest req)
  {
    JSONObject resultObj = new JSONObject();
    try
    {
      login();
      WSWSPaymentFacadeSrvProxyProxy client = new WSWSPaymentFacadeSrvProxyProxy();
      
      String str = JSON.toJSONString(req);
      baseBean.writeLog("[EasService.createPaymentBill] req:" + str);
      String result = client.insertPaymentBill(str);
      
      baseBean.writeLog("[EasService.createPaymentBill] result:" + result);
      
      resultObj = JSONObject.fromObject(result);
    }
    catch (Exception e)
    {
      baseBean.writeLog("[EasService.createPaymentBill] 预算退回失败 error:" + e.toString());
      baseBean.writeLog(e);
      
      resultObj.put("code", Integer.valueOf(-1));
      resultObj.put("msg", "创建付款单失败 error:" + e.toString());
    }
    resultObj.put("params", JSON.toJSONString(req));
    return resultObj;
  }
  
  public static JSONObject returnBudget(ReturnBudgetRequest req)
  {
    JSONObject resultObj = new JSONObject();
    try
    {
      login();
      WSWSBudgetFacadeSrvProxyProxy client = new WSWSBudgetFacadeSrvProxyProxy();
      
      String str = JSON.toJSONString(req);
      
      baseBean.writeLog("[EasService.returnBudget] req:" + str);
      
      String result = client.returnBudget(str);
      
      baseBean.writeLog("[EasService.returnBudget] result:" + result);
      
      resultObj = JSONObject.fromObject(result);
    }
    catch (Exception e)
    {
      baseBean.writeLog("[EasService.returnBudget] 预算退回失败 error:" + e.toString());
      baseBean.writeLog(e);
      
      resultObj.put("code", Integer.valueOf(-1));
      resultObj.put("msg", "预算退回失败 error:" + e.toString());
    }
    resultObj.put("params", JSON.toJSONString(req));
    return resultObj;
  }
  
  public static JSONObject requestBudget(RequestBudgetRequest req)
  {
    JSONObject resultObj = new JSONObject();
    try
    {
      login();
      WSWSBudgetFacadeSrvProxyProxy client = new WSWSBudgetFacadeSrvProxyProxy();
      
      String str = JSON.toJSONString(req);
      baseBean.writeLog("[EasService.requestBudget] req:" + str);
      
      String result = client.requestBudget(str);
      
      baseBean.writeLog("[EasService.requestBudget] result:" + result);
      
      resultObj = JSONObject.fromObject(result);
    }
    catch (Exception e)
    {
      baseBean.writeLog("[EasService.requestBudget] 预算扣减失败 error:" + e.toString());
      baseBean.writeLog(e);
      
      resultObj.put("code", Integer.valueOf(-1));
      resultObj.put("msg", "预算扣减失败 error:" + e.toString());
    }
    resultObj.put("params", JSON.toJSONString(req));
    
    return resultObj;
  }
  
  public static String getBudget(BudgetRequest req)
  {
    String balance = "";
    try
    {
      login();
      WSWSBudgetFacadeSrvProxyProxy client = new WSWSBudgetFacadeSrvProxyProxy();
      
      String str = JSON.toJSONString(req);
      baseBean.writeLog("[EasService.getBudget] req:" + str);
      
      String result = client.getBudget(str);
      baseBean.writeLog("[EasService.getBudget] result:" + result);
      
      JSONObject resultObj = JSONObject.fromObject(result);
      balance = ((JSONObject)JSONArray.fromObject(resultObj.getString("list")).get(0)).getString("balance");
      
      baseBean.writeLog("[EasService.getBudget] 可用预算 balance:" + balance);
    }
    catch (Exception e)
    {
      baseBean.writeLog("[EasService.getBudget] 获取预算异常 error:" + e.toString());
      baseBean.writeLog(e);
    }
    return balance;
  }
  
  public static void login()
  {
    EASLoginProxyProxy client = new EASLoginProxyProxy();
    try
    {
      WSContext ctx = client.login(ConfigUtil.getProperty("eas.user.name"), 
				ConfigUtil.getProperty("eas.user.password"), ConfigUtil.getProperty("eas.slnName"), 
				ConfigUtil.getProperty("eas.dcName"), ConfigUtil.getProperty("eas.language"), 
				Integer.parseInt(ConfigUtil.getProperty("eas.dbType")));
      baseBean.writeLog("[EasService.login] sessionid:" + ctx.getSessionId());
      baseBean.writeLog("[EasService.login] username:" + ctx.getUserName());
    }
    catch (Exception e)
    {
      baseBean.writeLog("[EasService.login] 登录失败 error:" + e.toString());
      baseBean.writeLog(e);
    }
  }
  
  public static void logOut()
  {
    EASLoginProxyProxy client = new EASLoginProxyProxy();
    try
    {
      boolean b = client.logout(ConfigUtil.getProperty("eas.user.name"), ConfigUtil.getProperty("eas.slnName"), 
				ConfigUtil.getProperty("eas.dcName"), ConfigUtil.getProperty("eas.language"));
      System.out.println(b);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static String getDeptCode(String deptid)
  {
    String departmentcode = "";
    RecordSet recordSet = new RecordSet();
    String sql = "select * from hrmdepartment where id=" + deptid;
    recordSet.execute(sql);
    if (recordSet.next()) {
      departmentcode = recordSet.getString("departmentcode");
    }
    return departmentcode;
  }
  
  public static DepartmentComInfo getDepartmentComInfo()
  {
    DepartmentComInfo departmentComInfo = null;
    try
    {
      departmentComInfo = new DepartmentComInfo();
    }
    catch (Exception localException) {}
    return departmentComInfo;
  }
  
  public static String getSubCode(String subcomid)
  {
    String subcompanycode = "";
    RecordSet recordSet = new RecordSet();
    String sql = "select * from hrmsubcompany where id='"+ subcomid+"'";
    recordSet.execute(sql);
    if (recordSet.next()) 
    {
      subcompanycode = recordSet.getString("subcompanycode");
    }
    return subcompanycode;
  }
  
  public static String getWorkCode(String loginid)
  {
    String workcode = "";
    RecordSet recordSet = new RecordSet();
    String sql = "select workcode from HrmResource where loginid = '" + loginid +"'";
    recordSet.execute(sql);
    if (recordSet.next()) {
    	workcode = recordSet.getString("workcode");
    }
    return workcode;
  }
  
  public static String getCurrencyTypeNumber(int bz)
  {
    String bzbm = "";
    switch (bz)
    {
    case 0: 
      bzbm = "BB01";
      break;
    case 1: 
      bzbm = "BB02";
      break;
    case 2: 
      bzbm = "BB04";
      break;
    case 3: 
      bzbm = "BB05";
      break;
    case 4: 
      bzbm = "BB03";
      break;
    case 5: 
      bzbm = "BB06";
      break;
    }
    return bzbm;
  }

  }
