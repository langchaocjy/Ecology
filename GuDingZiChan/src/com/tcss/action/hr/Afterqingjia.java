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
				manager.setMessagecontent("请假时间必须是0.5的整数倍或请假天数大于等于1");
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
						manager.setMessagecontent("当前日期是新年的1月1号，只能申请去年12月以后的假期");
						manager.setMessageid(System.currentTimeMillis()+"");
					}
				}else{
					if (nfyf < (intsqrq-1)) {
						manager.setMessagecontent("只能申请上月开始后的假期");
						manager.setMessageid(System.currentTimeMillis()+"");
					}
				}
                
			}
			if (Util.getIntValue(sqrq.substring(6,8)) >= 2) {
				if (nfyf < intsqrq) {
					manager.setMessagecontent("当月已超过2号，只能申请本月以后的假期");
					manager.setMessageid(System.currentTimeMillis()+"");
				}
			}	
		}
		
		/**
		 * 主表  ： formtable_main_218
                       第二 ：byyqnjss + bcnjss  必须 小于等于 qnnjss + jnnjss 的合计。
                       否则报错，年假超过可用假期。
         **/
		double byyqnjss = Util.getDoubleValue(mainData.getString("byyqnjss"), 0.00);
		double bcnjss = Util.getDoubleValue(mainData.getString("bcnjss"), 0.00);
		
		double qnnjss = Util.getDoubleValue(mainData.getString("qnnjss"),0.00);
		double jnnjss = Util.getDoubleValue(mainData.getString("jnnjss"),0.00);
		
		if ((byyqnjss+bcnjss) > (qnnjss+jnnjss)) {
			manager.setMessagecontent("年假超过可用假期");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**
		 * 第三：bctxss 必须 小于等于 qntxss + jntxss 的合计
                       否则报错，调休时数超过可用调休。
		 * */
		double bctxss = Util.getDoubleValue(mainData.getString("bctxss"), 0.00);
		double qntxss = Util.getDoubleValue(mainData.getString("qntxss"), -1);
		double jntxss = Util.getDoubleValue(mainData.getString("jntxss"), -1);
		if (bctxss > (qntxss+jntxss)) {
			  manager.setMessagecontent("调休时数超过可用调休");
			  manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**第四：当本次其余假期时数合计 bcqyjqss  大于0 时，则  qntxss+ jntxss 必须小于等于0 。
                      否则报错，请先用完调休。
		 * */
		double bcqyjqss = Util.getDoubleValue(mainData.getString("bcqyjqss"), 0.00);
		if (bcqyjqss > 0 && (qntxss+jntxss) > 0) {
			  manager.setMessagecontent("请先用完调休！");
			  manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		 /**第五：当本次其余假期时数合计 bcqyjqss  大于0 时，则 qnnjss+ jnnjss 必须小于等于0 ，
		   *否则报错，请先用完年假。
           */
		if (bcqyjqss > 0 && (qnnjss+jnnjss) > 0) {
			manager.setMessagecontent("请先用完年假!");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**第六：当本次调休时数合计 bctxss 大于qntxss时，则 byyqnjss + bcnjss 的合计必须 大于等于 qnnjss,否则报错，请先用完年假。
		 * 
		 * */
		if (bctxss > 0 && (byyqnjss+bcnjss) < qnnjss) {
			manager.setMessagecontent("请先用完年假!");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		/**
		  *第七：当本次调休时数合计 bctxss 大于jntxss时，则 byyqnjss + bcnjss 的合计必须 大于等于 jnnjss，否则报错，请先用完年假。
          **/
		if (bctxss > qntxss && (byyqnjss+bcnjss) < jnnjss) {
			manager.setMessagecontent("超过去年调休时间，请先用完去年年假");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		/**
		  * 第八：当年假时数 byyqnjss + bcnjss 大于qnnjss时，则 bctxss 必须 大于等于  qntxss ，否则报错，请先休完去年调休。
		  */
		if ((byyqnjss+bcnjss) > qnnjss && bctxss < qntxss) {
			manager.setMessagecontent("请先休完去年调休");
			manager.setMessageid(System.currentTimeMillis()+"");
		}
		
		if ((byyqnjss+bcnjss) > qnnjss && bctxss < qntxss) {
			manager.setMessagecontent("超过去年年假，请先用完去年调休");
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
