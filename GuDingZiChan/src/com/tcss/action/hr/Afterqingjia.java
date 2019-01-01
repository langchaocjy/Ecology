package com.tcss.action.hr;

import com.tcss.zc_ut.WISap_2_util;
import com.weaver.general.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestManager;

public class Afterqingjia extends BaseBean implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		RequestManager manager = requestinfo.getRequestManager();
		
		WISap_2_util sap_2_util=new WISap_2_util();
		JSONObject mainData=sap_2_util.getMainData(requestid);
		JSONArray detailData = sap_2_util.getDetailData(requestid, 1);
		
		String sqrq = Util.null2String(mainData.getString("sqrq")).replace("-", "");
		int intsqrq = Util.getIntValue(sqrq.substring(0, 6), 201012);
		boolean flag = sqrq.endsWith("01");
		
		for (int i = 0; i < detailData.size(); i++) {
			JSONObject json = detailData.getJSONObject(i);
			double bcqjss = Util.getDoubleValue(json.getString("bcqjss"), 0.00);
			int ts = Util.getIntValue(json.getString("ts"),0);
			
			double b1 = bcqjss/0.5;
			int index = String.valueOf(b1).indexOf(".");
			int b2 = Util.getIntValue(String.valueOf(b1).substring(0, index));
				
			if ((b1 != b2 || bcqjss < 0.5 )&& ts<1) {
				manager.setMessagecontent("���ʱ�������0.5��������������������ڵ���1");
				manager.setMessageid(System.currentTimeMillis()+"");
			}
			
			String nf = Util.null2String(json.getString("nf"));
			String yf = Util.null2String(json.getString("yf"));
			if (yf.length() == 1) {
				yf = "0"+yf;
			}
			int nfyf = Util.getIntValue(nf+yf);
			if (flag) {
                if (sqrq.substring(4, 6).equals("01") ) {
					if (nfyf < (intsqrq-100)) {
						manager.setMessagecontent("��ǰ�����������1��1�ţ�ֻ������ȥ��12���Ժ�ļ���");
						manager.setMessageid(System.currentTimeMillis()+"");
					}
				}else{
					if (nfyf < (intsqrq-1)) {
						manager.setMessagecontent("ֻ���������¿�ʼ��ļ���");
						manager.setMessageid(System.currentTimeMillis()+"");
					}
				}
                
			}
			if (Util.getIntValue(sqrq.substring(6,8)) >= 2) {
				if (nfyf < intsqrq) {
					manager.setMessagecontent("�����ѳ���2�ţ�ֻ�����뱾���Ժ�ļ���");
					manager.setMessageid(System.currentTimeMillis()+"");
				}
			}	
		}
		
		/**
		 * ����  �� formtable_main_218
                       �ڶ� ��byyqnjss + bcnjss  ���� С�ڵ��� qnnjss + jnnjss �ĺϼơ�
                       ���򱨴���ٳ������ü��ڡ�
         **/
		double byyqnjss = Util.getDoubleValue(mainData.getString("byyqnjss"), 0.00);
		double bcnjss = Util.getDoubleValue(mainData.getString("bcnjss"), 0.00);
		
		double qnnjss = Util.getDoubleValue(mainData.getString("qnnjss"),0.00);
		double jnnjss = Util.getDoubleValue(mainData.getString("jnnjss"),0.00);
		
		if ((byyqnjss+bcnjss) > (qnnjss+jnnjss)) {
			manager.setMessagecontent("��ٳ������ü���");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**
		 * ������bctxss ���� С�ڵ��� qntxss + jntxss �ĺϼ�
                       ���򱨴�����ʱ���������õ��ݡ�
		 * */
		double bctxss = Util.getDoubleValue(mainData.getString("bctxss"), 0.00);
		double qntxss = Util.getDoubleValue(mainData.getString("qntxss"), -1);
		double jntxss = Util.getDoubleValue(mainData.getString("jntxss"), -1);
		if (bctxss > (qntxss+jntxss)) {
			  manager.setMessagecontent("����ʱ���������õ���");
			  manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**���ģ��������������ʱ���ϼ� bcqyjqss  ����0 ʱ����  qntxss+ jntxss ����С�ڵ���0 ��
                      ���򱨴�����������ݡ�
		 * */
		double bcqyjqss = Util.getDoubleValue(mainData.getString("bcqyjqss"), 0.00);
		if (bcqyjqss > 0 && (qntxss+jntxss) > 0) {
			  manager.setMessagecontent("����������ݣ�");
			  manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		 /**���壺�������������ʱ���ϼ� bcqyjqss  ����0 ʱ���� qnnjss+ jnnjss ����С�ڵ���0 ��
		   *���򱨴�����������١�
           */
		if (bcqyjqss > 0 && (qnnjss+jnnjss) > 0) {
			manager.setMessagecontent("�����������!");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**�����������ε���ʱ���ϼ� bctxss ����qntxssʱ���� byyqnjss + bcnjss �ĺϼƱ��� ���ڵ��� qnnjss,���򱨴�����������١�
		 * 
		 * */
		if (bctxss > 0 && (byyqnjss+bcnjss) < qnnjss) {
			manager.setMessagecontent("�����������!");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**
		  *���ߣ������ε���ʱ���ϼ� bctxss ����jntxssʱ���� byyqnjss + bcnjss �ĺϼƱ��� ���ڵ��� jnnjss�����򱨴�����������١�
          **/
		if (bctxss > qntxss && (byyqnjss+bcnjss) < jnnjss) {
			manager.setMessagecontent("����ȥ�����ʱ�䣬��������ȥ�����");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		/**
		  * �ڰˣ������ʱ�� byyqnjss + bcnjss ����qnnjssʱ���� bctxss ���� ���ڵ���  qntxss �����򱨴���������ȥ����ݡ�
		  */
		if ((byyqnjss+bcnjss) > qnnjss && bctxss < qntxss) {
			manager.setMessagecontent("��������ȥ�����");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		if ((byyqnjss+bcnjss) > qnnjss && bctxss < qntxss) {
			manager.setMessagecontent("����ȥ����٣���������ȥ�����");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		return SUCCESS;
	}
	
	public static void main(String[] args){
		String nf = "2018";
	       String yf = "12";
	     int  nfyf = Util.getIntValue(nf+yf);
	       System.out.println(nfyf);
	}
}
