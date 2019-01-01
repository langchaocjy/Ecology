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
			writeLog("[resultObj]--->û��ִ���ճ������ӿ�");
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
				requestManager.setMessage("EAS�ӿڵ���ʧ��");
				requestManager.setMessagecontent("EAS�ӿڵ���ʧ�� code��"+code+" msg: "+msg+" params: "+params);
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
	    String createrName = "������";
	    String bizType = "acc";
	    RecordSet recordSet = new RecordSet();
	    
	    String sql = "select * from "+formtable+" where requestid ='"+requestid+"'";
	    recordSet.execute(sql);
	    if (recordSet.next()) 
	    {
			bxr = recordSet.getString("sqr");//������
			sqrq = recordSet.getString("sqrq");//��������
//			bizhong = recordSet.getInt("sqbb");//����
			lcbh = recordSet.getString("lsh");//���̱��
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
			resultObj = EasService.returnBudget(req);//����Ԥ��
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
	    	 String orgUnitNumber = EasService.getSubCode(resourceComInfo.getSubCompanyID(bxr_s));//��˾����   -- �����˹�˾
	         String departmentNumber = EasService.getDeptCode(resourceComInfo.getDepartmentID(bxr_s));//���ű��� -- �����˲���
	         String bizDate = sqrq;//ҵ������ -- ��������
	         String exchangeRate = "1";//���� -- ��ϸ��1����
	         
	         String personNumber = resourceComInfo.getWorkcode(bxr_s);//������ -- �����˱��
	         String userNumber = resourceComInfo.getLastname(bxr_s);//�����˶�ӦEAS�˺� -- ����������
	         String personDeptNumber = orgUnitNumber;//�����˹�˾���� -- �����˹�˾����
	         
	         RequestBudgetRequest req = new RequestBudgetRequest();
	         List<RequestBudgetEntryDTO> entryList = new ArrayList();
	         
	         req.setBizType(bizType);//����
	         req.setNumber(requestid);//����
	         req.setOrgUnitNumber(orgUnitNumber);//��˾����
	         req.setDepartmentNumber(departmentNumber);//���ű���
	         req.setBizDate(bizDate);//ҵ������
	         req.setCurrencyTypeNumber(currencyTypeNumber);//�ұ����
	         req.setExchangeRate(exchangeRate);//����
	         req.setPersonNumber(personNumber);//������
	         req.setUserNumber(userNumber);//�����˶�Ӧeas�˺�
	         req.setPersonDeptNumber(personDeptNumber);//�����˹�˾����
	         

	         sql = "select t2.* from " + formtable + " t1," + formtable + "_dt1 t2 where requestId=" + requestid + " and t1.id=t2.mainid";
	         recordSet.execute(sql);
	         while (recordSet.next())
	         {
	             String fycdbm = recordSet.getString("fycdbm");
	             RequestBudgetEntryDTO entry = new RequestBudgetEntryDTO();
	             
	             entry.setExpenseTypeNumber(recordSet.getString("fykm"));//������Ŀ����--���ÿ�Ŀ
	             entry.setReqBizDate(bizDate);//ҵ��������
	             entry.setCurrencyTypeNumber(currencyTypeNumber);//��ϸ�ұ�
	             entry.setExchangeRate("1");//��ϸ����--�տ����
	             entry.setAmount(getNum(recordSet.getString("rmbje")));//֧�����--����ҽ��
	             entry.setAmountOri(getNum(recordSet.getString("rmbje")));//֧����λ�ҽ��--����ҽ��
	             entry.setCompanyNumber(EasService.getSubCode(departmentComInfo.getSubcompanyid1(fycdbm)));
	             entry.setDepartmentNumber(EasService.getDeptCode(fycdbm));
	             
	             entryList.add(entry);
	           }
	           req.setEntryList(entryList);
	           
	           resultObj = EasService.requestBudget(req);//�ۼ�Ԥ��
	    }
		return resultObj;
	}
	  public String getNum(String num)
	  {
	    return num.replaceAll(",", "");
	  }
}
