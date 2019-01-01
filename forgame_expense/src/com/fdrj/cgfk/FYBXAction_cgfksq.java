package com.fdrj.cgfk;

import java.util.ArrayList;
import java.util.List;
import com.fdrj.EasService;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillEntryDTO;
import com.fdrj.services.wsbasedataqueryfacade.client.PaymentBillRequest;
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

public class FYBXAction_cgfksq extends BaseBean implements Action{

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
	    String ywrq = "";
	    String createrName = "单秋燕";
	    String bizType = "acc";
	    RecordSet recordSet = new RecordSet();
	    
	    String sql = "select * from "+formtable+" where requestid ='"+requestid+"'";
	    recordSet.execute(sql);
	    if (recordSet.next()) 
	    {
			bxr = recordSet.getString("sqr");//报销人
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
    	String a_zfcompany = "",splcbm="";
    	rs.execute("select zfgs,splcbm from "+formtable+" where requestid = '"+requestid+"'");
    	if (rs.next()) {
			a_zfcompany=rs.getString("zfgs");
			splcbm=rs.getString("splcbm");
		}
	    	 String bizDate = ywrq;
//	         String companyNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr_s));
	    	 String companyNumber = EasService.getSubCode(a_zfcompany);
//	         String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr_s));
	    	 String departmentNumber = EasService.getDeptCode(splcbm);
	         String creatorNumber = createrName;
	         
	         PaymentBillRequest req = new PaymentBillRequest();
	         List<PaymentBillEntryDTO> entryList = new ArrayList();
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
	         req.setAdminOrgUnitNumber(departmentNumber);
	         if ("0".equals(req.getSourceBillType()))
	         {
	           req.setPayeeType("00002");
	           req.setPayeeNumber("");
	           req.setPayTypeNumber("999");
	         }
	         else if ("1".equals(req.getSourceBillType()))
	         {
	           req.setPayeeType("00002");
	           req.setPayeeNumber("");
	           req.setPayTypeNumber("201");
	         }
	         String allskr = "";
	         sql = "select t2.skr from " + formtable + " t1," + formtable + "_dt2 t2 where requestid=" + requestid + " and t1.id=t2.mainid";
	         recordSet.execute(sql);
	         if(recordSet.next()){
	        	 allskr = recordSet.getString("skr");
	         }
	         req.setAcctBankName(allskr);
	         req.setAcctBankNumber("1");
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
	           entry.setAmount(getNum(recordSet.getString("rmbje")));
	           entry.setLocalAmt(getNum(recordSet.getString("rmbje")));
	           entry.setCompanyNumber(EasService.getSubCode(recordSet.getString("yscdgs")));//预算承担公司
	           entry.setDepartmentNumber(EasService.getDeptCode(recordSet.getString("yscdbm")));//预算承担部门
	           writeLog("[摘要--》？]"+recordSet.getString("zy"));
	           entry.setRemark(recordSet.getString("zy"));//摘要
	           writeLog("[收款人是谁--》？]"+allskr);
	   
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
	         return resultObj;
	    }
	
	  public String getNum(String num)
	  {
	    return num.replaceAll(",", "");
	  }
}
