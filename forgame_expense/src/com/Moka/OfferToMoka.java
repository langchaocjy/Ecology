package com.Moka;

import java.util.Map;

import com.hr.util.MokaUtil;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.email.EmailWorkRunnable;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class OfferToMoka extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo request) {
		RecordSet rs = new RecordSet();
		String requestid = request.getRequestid();
		String src = request.getRequestManager().getSrc();
		MokaUtil mokaUtil = new MokaUtil();
		
		 JSONObject mainData = getMainData(requestid);
		 writeLog("mainData:"+mainData);
		 String mailtoaddress=mainData.getString("yx");
		 String sqid=mainData.getString("sqid");
		 
		 String title="云游控股面试邮箱";
		 String message = getMessage(mainData);
		 
	        if (src.equals("reject")) {
			    Map<String, Object> map=mokaUtil.offerApproval(Integer.parseInt(sqid), 2);
		    } else if(!src.equals("reject")) {
			    Map<String,Object> map=mokaUtil.offerApproval(Integer.parseInt(sqid), 1);
			    new Thread(new EmailWorkRunnable(mailtoaddress, title, message)).start();
			    rs.execute("update uf_msjlb set stauts='1' where applicationId='"+sqid+"'");
		    }
	        
		return SUCCESS;
	}
	public String getMessage(JSONObject mainData){
		   StringBuffer theMessage = new StringBuffer();
		   String znbmpd= mainData.getString("znbmpd");
		   if (znbmpd.equals("0")) {//通用
			    theMessage.append("<h3>致:"+mainData.getString("ypzxm")+"女士/先生</h3>");
		        theMessage.append("<h3>由："+mainData.getString("ssgs")+"</h3>");
		        theMessage.append("<h3>主题：聘用信</h3>");
		        theMessage.append("<p>您好！我是Forgame集团人力资源部HR，我谨代表"+mainData.getString("ssgs")+",通知您已通过我司的招聘选拔程序，公司拟录用您为正式员工。很高兴能与您成为同事！<br/>"
		        		+ "以下为录用条款，请您仔细阅读并于收到本通知书之日起三日之内给予回复。</p>");
		        theMessage.append("<h4>一、职位信息</h4>");
		        theMessage.append("<p>录用部门："+mainData.getString("ypzbm")+"</p>");
		        theMessage.append("<p>录用职位："+mainData.getString("ypzzw")+"</p>");
		        theMessage.append("<p>汇报对象："+mainData.getString("ypzxm")+"</p>");
		        
		        theMessage.append("<h4>二、入职办理</h4>");
		        theMessage.append("<p>报到时间:"+mainData.getString("bdsj")+"</p>");
		        theMessage.append("<p>报到地点："+mainData.getString("bddd")+"</p>");
		        theMessage.append("<p>具体地址："+mainData.getString("jtdz")+"</p>");
		        theMessage.append("<p>联  系 人 ："+mainData.getString("lxr")+"</p>");
	     
		        theMessage.append("<h4>三、试用期</h4>");
		        theMessage.append("<p>试用期为 "+mainData.getString("syqys")+"个月，具体试用期起止时间以劳动合同约定为准。本公司将在试用期内对您工作职责履行情况、绩效表现及是否符合岗位录用条件作出评估，"
		        		+ "并以此作为确定您是否通过试用期的依据；如未能在约定试用期内通过考核的，可经双方协商解决或者按照《劳动合同法》相关规定处理。</p>");
		        
		        theMessage.append("<h4>四、基本工资</h4>");
		        theMessage.append("<p>试用基本工资（税前）："+mainData.getString("syxz")+"</p>");
		        theMessage.append("<p>转正基本工资（税前）："+mainData.getString("zzxz")+"</p>");
		        theMessage.append("<p>公司每年将结合个人绩效考核结果、岗位调整、市场薪酬水平进行相应薪酬调整。</p>");
		        
		        theMessage.append("<h4>五、福利</h4>");
		        theMessage.append("<p>公司将为您缴纳：养老保险、失业保险、医疗保险、生育险、工伤保险、商业保险和住房公积金</p>");
		        
		        theMessage.append("<h4>六、报到所需材料</h4>");
		        theMessage.append("<p>近期的小一寸白底彩色免冠照片2张（每张后面用签字笔写上姓名）</p>");
		        theMessage.append("<p>身份证原件及身份证正反面复印件3份</p>");
		        theMessage.append("<p>户口本首页和本人页复印件1份</p>");
		        theMessage.append("<p>最高学历学位证书原件及复印件1份</p>");
		        theMessage.append("<p>相关资质证书原件及复印件1份</p>");
		        theMessage.append("<p>原单位加盖公章的离职证明原件（适用于有工作经验者）</p>");
		        theMessage.append("<p>个人收入证明（或薪资的银行流水单）</p>");
		        theMessage.append("<p>二甲级别以上医院在3个月内开具的体检报告原件（常规项目、胸透、心电图）</p>");
		        theMessage.append("<p>失业证或就业失业手册原件（如有）</p>");
		        theMessage.append("<p>另，入职后8日内请提供广州市开户招行卡号及开户行信息至集团人力资源部范江帆处，作为发放工资的账号</p>");
		        
		        theMessage.append("<h4>七、报到后，公司与您签订劳动合同，劳动合同期限为"+mainData.getString("htqx")+"个月，其中试用期为"+mainData.getString("syqys")+"个月，具体以最终签署的劳动合同为准。</h4>");
		        
		        theMessage.append("<h4>八、其他重要条款</h4>");
		        theMessage.append("<p>公司实施薪酬保密制度，您有义务对薪酬资料保密，不在公司内外讨论，透露个人薪酬资料，如有违反薪酬保密制度，将按照公司相关制度严肃处理</p>");
		        theMessage.append("<p>本通知书的生效是以您提供的信息全面、真实、有效为前提，若发生以下情形：您向我公司提供的学历、工作经历等信息不真实，或向公司隐瞒了其他不良记录，或未向我公司披露与其他用人单位尚未解除的劳动关系，或对前用人单位仍然负有竞业限制义务等情形，则本通知书不发生法律效力且公司有权立即解除与您的劳动合同；</p>");
		        theMessage.append("<p>您对本通知书的确认并不表示双方建立了劳动合同关系，双方劳动合同关系的建立以及具体的权利义务最终将以我司与您签订的劳动合同为准</p>");
		        theMessage.append("<p>本聘用信经应聘人邮件回复确认同意后立即生效，后续双方据此聘用信条款签订劳动合同。请三日之内以邮件形式回复公司，正式表明你是否愿意接受此聘用。</p>");
			    
		        theMessage.append("<h4>受聘人声明：</h4>");
		        theMessage.append("<p>本人同意上述聘用及相关安排，且本人确认在回复本函件时并未与其他用人单位同时建立双重劳动（合同）关系，亦没有受竞业限制协议之约束或限制。本人确认并承诺对本聘用信负有严格的保密义务不得对外披露。</p>");
			    
		        theMessage.append("<h4>公司："+mainData.getString("ssgs")+"</h4>");
		        theMessage.append("<h4>受聘人签字：</h4>");
		        theMessage.append("<h4>日期：     年       月        日</h4>");
		   }else{//简理财
			   theMessage.append("<h3>致:"+mainData.getString("ypzxm")+"女士/先生</h3>");
		        theMessage.append("<h3>由："+mainData.getString("ssgs")+"</h3>");
		        theMessage.append("<h3>主题：聘用信</h3>");
		        theMessage.append("<p>您好！我是Forgame集团人力资源部HR，我谨代表"+mainData.getString("ssgs")+",通知您已通过我司的招聘选拔程序，公司拟录用您为正式员工。很高兴能与您成为同事！<br/>"
		        		+ "以下为录用条款，请您仔细阅读并于收到本通知书之日起三日之内给予回复。</p>");
		        theMessage.append("<h4>一、职位信息</h4>");
		        theMessage.append("<p>录用部门："+mainData.getString("ypzbm")+"</p>");
		        theMessage.append("<p>录用职位："+mainData.getString("ypzzw")+"</p>");
		        theMessage.append("<p>汇报对象："+mainData.getString("ypzxm")+"</p>");
		        
		        theMessage.append("<h4>二、入职办理</h4>");
		        theMessage.append("<p>报到时间:"+mainData.getString("bdsj")+"</p>");
		        theMessage.append("<p>报到地点："+mainData.getString("bddd")+"</p>");
		        theMessage.append("<p>具体地址："+mainData.getString("jtdz")+"</p>");
		        theMessage.append("<p>联  系 人 ："+mainData.getString("lxr")+"</p>");
	     
		        theMessage.append("<h4>三、试用期</h4>");
		        theMessage.append("<p>试用期为 "+mainData.getString("syqys")+"个月，具体试用期起止时间以劳动合同约定为准。本公司将在试用期内对您工作职责履行情况、绩效表现及是否符合岗位录用条件作出评估，"
		        		+ "并以此作为确定您是否通过试用期的依据；如未能在约定试用期内通过考核的，可经双方协商解决或者按照《劳动合同法》相关规定处理。</p>");
		        
		        theMessage.append("<h4>四、基本工资</h4>");
		        theMessage.append("<p>试用基本工资（税前）："+mainData.getString("syxz")+"</p>");
		        theMessage.append("<p>转正基本工资（税前）："+mainData.getString("zzxz")+"</p>");
		        theMessage.append("<p>公司每年将结合个人绩效考核结果、岗位调整、市场薪酬水平进行相应薪酬调整。</p>");
		        
		        theMessage.append("<h4>五、福利</h4>");
		        theMessage.append("<p>公司将为您缴纳：养老保险、失业保险、医疗保险、生育险、工伤保险、商业保险和住房公积金</p>");
		        
		        theMessage.append("<h4>六、报到所需材料</h4>");
		        theMessage.append("<p>近期的小一寸白底彩色免冠照片2张（每张后面用签字笔写上姓名）</p>");
		        theMessage.append("<p>身份证原件及身份证正反面复印件3份</p>");
		        theMessage.append("<p>户口本首页和本人页复印件1份</p>");
		        theMessage.append("<p>最高学历学位证书原件及复印件1份</p>");
		        theMessage.append("<p>相关资质证书原件及复印件1份</p>");
		        theMessage.append("<p>原单位加盖公章的离职证明原件（适用于有工作经验者）</p>");
		        theMessage.append("<p>个人收入证明（或薪资的银行流水单）</p>");
		        theMessage.append("<p>二甲级别以上医院在3个月内开具的体检报告原件（常规项目、胸透、心电图）</p>");
		        theMessage.append("<p>失业证或就业失业手册原件（如有）</p>");
		        theMessage.append("<p>另，入职后8日内请提供广州市开户招行卡号及开户行信息至集团人力资源部范江帆处，作为发放工资的账号</p>");
		        
		        theMessage.append("<h4>七、报到后，公司与您签订劳动合同，劳动合同期限为"+mainData.getString("htqx")+"个月，其中试用期为"+mainData.getString("syqys")+"个月，具体以最终签署的劳动合同为准。</h4>");
		        
		        theMessage.append("<h4>八、其他重要条款</h4>");
		        theMessage.append("<p>公司实施薪酬保密制度，您有义务对薪酬资料保密，不在公司内外讨论，透露个人薪酬资料，如有违反薪酬保密制度，将按照公司相关制度严肃处理</p>");
		        theMessage.append("<p>本通知书的生效是以您提供的信息全面、真实、有效为前提，若发生以下情形：您向我公司提供的学历、工作经历等信息不真实，或向公司隐瞒了其他不良记录，或未向我公司披露与其他用人单位尚未解除的劳动关系，或对前用人单位仍然负有竞业限制义务等情形，则本通知书不发生法律效力且公司有权立即解除与您的劳动合同；</p>");
		        theMessage.append("<p>您对本通知书的确认并不表示双方建立了劳动合同关系，双方劳动合同关系的建立以及具体的权利义务最终将以我司与您签订的劳动合同为准</p>");
		        theMessage.append("<p>本聘用信经应聘人邮件回复确认同意后立即生效，后续双方据此聘用信条款签订劳动合同。请三日之内以邮件形式回复公司，正式表明你是否愿意接受此聘用。</p>");
			    
		        theMessage.append("<h4>受聘人声明：</h4>");
		        theMessage.append("<p>本人同意上述聘用及相关安排，且本人确认在回复本函件时并未与其他用人单位同时建立双重劳动（合同）关系，亦没有受竞业限制协议之约束或限制。本人确认并承诺对本聘用信负有严格的保密义务不得对外披露。</p>");
			    
		        theMessage.append("<h4>公司："+mainData.getString("ssgs")+"</h4>");
		        theMessage.append("<h4>受聘人签字：</h4>");
		        theMessage.append("<h4>日期：     年       月        日</h4>");
		   }
		return theMessage.toString();
	}

	public JSONObject getMainData(String requestid)
	  {
	    JSONObject json = new JSONObject();
	    RecordSet rs = new RecordSet();
	    String tableName = getTableName(requestid);
	    String sql = "select * from " + tableName + " where requestid='" + requestid + "' ";
	    rs.execute(sql);
	    if (rs.next())
	    {
	      String[] columnName = rs.getColumnName();
	      for (int i = 0; i < columnName.length; i++)
	      {
	        String name = columnName[i].toLowerCase();
	        String value = rs.getString(name);
	        json.put(name, value);
	      }
	    }
	    return json;
	  }
	public String getTableName(String requestid)
	  {
	    String tablename = "";
	    if (!"".equals(requestid))
	    {
	      String sql = "select tablename from workflow_bill where id in(select formid from workflow_base where id in(select workflowid from workflow_requestbase where requestid=" + requestid + "))";
	      RecordSet rs = new RecordSet();
	      rs.execute(sql);
	      if (rs.next()) {
	        tablename = rs.getString("tablename");
	      }
	    }
	    return tablename;
	  }
}
