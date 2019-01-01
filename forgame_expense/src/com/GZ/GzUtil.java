package com.GZ;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hr.bean.ForgameSalayTemplate;
import com.hr.bean.JlcSalaryTemplate;
import com.hr.util.SalayCalculator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class GzUtil{
	private static BaseBean basebean=new BaseBean();
	
    public String[] GzName1 = "工号|分部|部门|姓名|入职日期|离职日期|月薪总额|基本工资|岗位工资|绩效奖金基数|全勤奖|餐费补贴|其他补贴|考核/奖金|加班费|其他补款|请假|迟到|其他|应发工资|社保|公积金|其他专项附加扣除|个税|实发工资|赔偿金|入账合计|服务费|导入日期|年月|备注".split("\\|", -1);
    public String[] GzName2 = "工号|分部|部门|姓名|入职日期|离职日期|月薪总额|基本工资|岗位工资|绩效奖金基数|电脑补贴|餐费补贴|出差补贴|考核/奖金|加班费|其他补款|事假|病假|其他|应发工资|社保|公积金|其他专项附加扣除|个税|实发工资|离职补偿|入账合计|服务费|绩效预估|导入日期|年月|备注".split("\\|", -1);
    
    public String[] GzName1_WQ="工号|姓名|月薪总额|基本工资|岗位工资|绩效奖金基数|全勤奖|餐费补贴|其他补贴|考核/奖金|加班费|其他补款|请假|迟到|其他|应发工资|社保|公积金|个税|实发工资|备注|导入日期|年月".split("\\|", -1);
    public String[] GzName2_WQ="工号|姓名|月薪总额|电脑补贴|餐费补贴|出差补贴|考核/奖金|加班费|其他|事假|病假|其他专项附加扣除|应发工资|社保|公积金|个税|实发工资|备注|导入日期|年月".split("\\|", -1);
    
   //搜索条件
   public static JSONArray getGzb(String ryids,String bmids,String fbids,String rq,String bm){
	   RecordSet rs = new RecordSet();
       JSONArray jsons=new JSONArray();
	   if (!ryids.equals("") && !rq.equals("")) //如果姓名不为空，就查这个人
	   {
		   String[] ryid = ryids.split(",");
		   for (int i = 0; i < ryid.length; i++) {
			   jsons.addAll(getGzb_rs("select * from "+bm+" where gh in(select workcode from hrmresource where id='"+ryid[i]+"') and drrq='"+rq+"'",bm));
		   }
	   }
	   else if(!ryids.equals("") && rq.equals(""))
	   {
		   String[] ryid = ryids.split(",");
		   for (int i = 0; i < ryid.length; i++) {
			   jsons.addAll(getGzb_rs("select * from "+bm+" where gh in(select workcode from hrmresource where id='"+ryid[i]+"')",bm));
		   }
	   }
	   else if(ryids.equals("") && fbids.equals("") && !bmids.equals("")) //分部为空，部门不为空，用部门
	   {
		   String[] bmid=bmids.split(",");
		   for (int i = 0; i < bmid.length; i++) {
			   rs.execute("select * from hrmresource where departmentid='"+bmid[i]+"'");
			   while(rs.next()){
				   String workcode = rs.getString("workcode");
				   jsons.addAll(getGzb_rs("select * from "+bm+" where gh = '"+workcode+"' and drrq='"+rq+"'",bm));
			   }
		   }
	   }
	   else if(ryids.equals("") && !fbids.equals("") && !bmids.equals(""))//两个都不为空，用部门
	   {
		   String[] bmid=bmids.split(",");
		   for (int i = 0; i < bmid.length; i++) {
			   rs.execute("select * from hrmresource where departmentid='"+bmid[i]+"'");
			   while(rs.next()){
				   String workcode = rs.getString("workcode");
				   jsons.addAll(getGzb_rs("select * from "+bm+" where gh = '"+workcode+"' and drrq='"+rq+"'",bm));
			   }
		   }
	   }
	   else if(ryids.equals("") && !fbids.equals("") && bmids.equals(""))//分部不为空，部门空了，查分部下的所有人
	   {
		   String[] fbid=fbids.split(",");
		   for (int i = 0; i < fbid.length; i++) {
			   rs.execute("select * from hrmresource where subcompanyid1='"+fbid[i]+"'");
			   while(rs.next()){
				   String workcode = rs.getString("workcode");
				   jsons.addAll(getGzb_rs("select * from "+bm+" where gh = '"+workcode+"' and drrq='"+rq+"'",bm));
			   }
     	   }
	   }
	   else if(ryids.equals("") && fbids.equals("") && bmids.equals("")&&!rq.equals(""))//两个都为空
	   {
			   jsons.addAll(getGzb_rs("select * from "+bm+" where drrq='"+rq+"'",bm));
	   }else if(ryids.equals("") && fbids.equals("") && bmids.equals("")&&rq.equals("")){
		   jsons.addAll(getGzb_rs("select * from "+bm+"",bm));
	   }
	   basebean.writeLog(jsons);
	return jsons;
   }
   public static JSONArray getHuizong123(String fbids,String bmids,String ksrq,String jsrq,String bm){
	   RecordSet rs = new RecordSet(),rs1=new RecordSet();
       JSONArray jsons=new JSONArray();
       if(  ( fbids.equals("") && !bmids.equals("") && !ksrq.equals("") && !jsrq.equals("")) 
    	 || (!fbids.equals("") && !bmids.equals("") && !ksrq.equals("") && !jsrq.equals("")) ) 
	   {
		   String[] bmid=bmids.split(",");
		   for (int i = 0; i < bmid.length; i++) {
			   rs.execute("select workcode from hrmresource where departmentid='"+bmid[i]+"'");
			   while(rs.next()){
				   String workcode = rs.getString("workcode");
				   rs1.execute("select drrq from "+bm+" where gh = '"+workcode+"'");
				   while(rs1.next()){
					   String drrq = rs1.getString("drrq");
					   boolean flag = judgeData(drrq,ksrq,jsrq);
					   if (flag) {
						   jsons.addAll(getGzb_rs("select * from "+bm+" where gh = '"+workcode+"' and drrq='"+drrq+"'",bm));
					   }
				   }
			   }
		   }
	   }
	   else if(!fbids.equals("") && bmids.equals("") && !ksrq.equals("") && !jsrq.equals(""))//分部不为空，部门空了，查分部下的所有人
	   {
		   String[] fbid=fbids.split(",");
		   for (int i = 0; i < fbid.length; i++) {
			   rs.execute("select * from hrmresource where subcompanyid1='"+fbid[i]+"'");
			   while(rs.next()){
				   String workcode = rs.getString("workcode");
				   rs1.execute("select drrq from "+bm+" where gh = '"+workcode+"'");
				   while(rs1.next()){
					   String drrq = rs1.getString("drrq");
					   boolean flag = judgeData(drrq,ksrq,jsrq);
					   if (flag) {
						   jsons.addAll(getGzb_rs("select * from "+bm+" where gh = '"+workcode+"' and drrq='"+drrq+"'",bm));
					   }
				   }
			   }
     	   }
	   }

	   basebean.writeLog(jsons);
	return jsons;
   }
   public static JSONArray getBeiquYunke(String bmids,String ksrq,String jsrq,String bm){//北区云客广州的搜索条件
	   RecordSet rs = new RecordSet(),rs1=new RecordSet();
	   JSONArray jsons = new JSONArray();
	   if (!bmids.equals("") &&!ksrq.equals("")&&!jsrq.equals("")) {//如果日期和部门不为空，按日期+部门查询
		   String[] bmid = bmids.split("");
		   for (int i = 0; i < bmid.length; i++) {
			   rs.execute("select * from hrmresource where departmentid='"+bmid[i]+"'");
			   while(rs.next()){
				   String workcode = rs.getString("workcode");
				   if (!workcode.equals("000100") && !workcode.equals("000160")) {
					   rs1.execute("select drrq from "+bm+" where gh = '"+workcode+"'");
					   while(rs1.next()){
						   String drrq = rs1.getString("drrq");
						   boolean flag = judgeData(drrq,ksrq,jsrq);
						   if (flag) {
							   jsons.addAll(getGzb_rs("select * from "+bm+" where gh = '"+workcode+"' and drrq='"+drrq+"'",bm));
						   }
					   }
				   }
			   }
		   }
	   }
	   return jsons;
   }
   public static boolean judgeData(String a, String b,String c){
	   boolean flag=false;
	   SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");     
	   try {
		Date date1 = sd.parse(a),date2=sd.parse(b),date3=sd.parse(c);
		boolean aa = date1.after(date2);
		boolean bb = date1.before(date3);
	
		if (aa && bb) {
			flag=true;
		} else {
            flag=false;
		}
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return flag;
   }
   //获取搜索后的导入基础数据并返回给前端

   public static JSONArray getGzb_rs(String sql,String biaoming){
	   JSONArray jsons = new JSONArray();
	   JSONObject json = new JSONObject();
	   RecordSet rs =new RecordSet();
	   rs.execute(sql);
	   while(rs.next()){
		   if (biaoming.equals("uf_gzb2")) {//简理财SBU
			   String gh = Util.null2String(rs.getString("gh"));
			   json.put("工号", gh);
			   String bm = getName("departmentname","hrmdepartment","id",gh);
			   json.put("部门", bm);
			   String sub = getName("subcompanyname","hrmsubcompany","id",gh);
			   json.put("分部", sub);
			   String xm = getName("lastname","hrmresource","workcode",gh);
			   json.put("姓名", xm);
			   String rzrq=Util.null2String(rs.getString("rzrq"));
			   json.put("入职日期", rzrq);
			   String lzrq=Util.null2String(rs.getString("lzrq"));
			   json.put("离职日期", lzrq);
			   String yxze = Util.null2o(rs.getString("yxze"));
			   json.put("月薪总额", yxze);
			   String jbgz=Util.null2o(rs.getString("jbgz"));
			   json.put("基本工资", jbgz);
			   String gwgz=Util.null2o(rs.getString("gwgz"));
			   json.put("岗位工资", gwgz);
			   String jxjjjs=Util.null2o(rs.getString("jxjjjs"));
			   json.put("绩效奖金基数", jxjjjs);
			   String dnbt = Util.null2o(rs.getString("dnbt"));
			   json.put("电脑补贴", dnbt);
			   String cfbt =Util.null2o(rs.getString("cfbt"));
			   json.put("餐费补贴", cfbt);
			   String ccbt = Util.null2o(rs.getString("ccbt"));
			   json.put("出差补贴", ccbt);
			   String khjj = Util.null2o(rs.getString("khjj"));
			   json.put("考核/奖金", khjj);
			   String jbf = Util.null2o(rs.getString("jbf"));
			   json.put("加班费", jbf);
			   String qtbk = Util.null2o(rs.getString("qtbk"));
			   json.put("其他补款", qtbk);
			   String sj=Util.null2o(rs.getString("sj"));
			   json.put("事假", sj);
			   String bj = Util.null2o(rs.getString("bj"));
			   json.put("病假", bj);
			   String qt = Util.null2o(rs.getString("qt"));
			   json.put("其他", qt);
			   String yfgz=Util.null2o(rs.getString("yfgz"));
			   json.put("应发工资", yfgz);
			   String sb=Util.null2o(rs.getString("sb"));
			   json.put("社保", sb);
			   String gjj=Util.null2o(rs.getString("gjj"));
			   json.put("公积金", gjj);
			   String qtzxfjkc=Util.null2o(rs.getString("qtzxfjkc"));
			   json.put("其他专项附加扣除", qtzxfjkc);
			   String gs=Util.null2o(rs.getString("gs"));
			   json.put("个税", gs);
			   String sfgz=Util.null2o(rs.getString("sfgz"));
			   json.put("实发工资", sfgz);
			   String lzbc=Util.null2o(rs.getString("lzbc"));
			   json.put("离职补偿", lzbc);
			   String rzhj=Util.null2o(rs.getString("rzhj"));
			   json.put("入账合计", rzhj);
			   String fwf=Util.null2o(rs.getString("fwd"));
			   json.put("服务费", fwf);
			   String drrq=Util.null2String(rs.getString("drrq"));
			   json.put("导入日期", drrq);
			   String nianyue=Util.null2String(rs.getString("ny"));
			   json.put("年月", nianyue);
			   String jxyg=Util.null2o(rs.getString("jxyg"));
			   json.put("绩效预估", jxyg);
			   String bz=Util.null2String(rs.getString("bz"));
			   json.put("备注", bz);
			   jsons.add(json);
		   }else{
			   String gh = Util.null2String(rs.getString("gh"));
			   json.put("工号", gh);
			   String bm = getName("departmentname","hrmdepartment","id",gh);
			   json.put("部门", bm);
			   String sub = getName("subcompanyname","hrmsubcompany","id",gh);
			   json.put("分部", sub);
			   String xm = getName("lastname","hrmresource","workcode",gh);
			   json.put("姓名", xm);
			   String rzrq=Util.null2String(rs.getString("rzrq"));
			   json.put("入职日期", rzrq);
			   String lzrq=Util.null2String(rs.getString("lzrq"));
			   json.put("离职日期", lzrq);
			   String yxze = Util.null2o(rs.getString("yxze"));
			   json.put("月薪总额", yxze);
			   String jbgz =Util.null2o(rs.getString("jbgz"));
			   json.put("基本工资", jbgz);
			   String gwgz=Util.null2o(rs.getString("gwgz"));
			   json.put("岗位工资", gwgz);
			   String jxjjjs=Util.null2o(rs.getString("jxjjjs"));
			   json.put("绩效奖金基数", jxjjjs);
			   String qqj = Util.null2o(rs.getString("qqj"));
			   json.put("全勤奖", qqj);
			   String cfbt =Util.null2o(rs.getString("cfbt"));
			   json.put("餐费补贴", cfbt);
			   String qtbt = Util.null2o(rs.getString("qtbt"));
			   json.put("其他补贴", qtbt);
			   String khjj = Util.null2o(rs.getString("khjj"));
			   json.put("考核/奖金", khjj);
			   String jbf = Util.null2o(rs.getString("jbf"));
			   json.put("加班费", jbf);
			   String qtbk = Util.null2o(rs.getString("qtbk"));
			   json.put("其他补款", qtbk);
			   String qj=Util.null2o(rs.getString("qj"));
			   json.put("请假", qj);
			   String cd = Util.null2o(rs.getString("cd"));
			   json.put("迟到", cd);
			   String qt=Util.null2o(rs.getString("qt"));
			   json.put("其他", qt);
			   String yfgz=Util.null2o(rs.getString("yfgz"));
			   json.put("应发工资", yfgz);
			   String sb=Util.null2o(rs.getString("sb"));
			   json.put("社保", sb);
			   String gjj=Util.null2o(rs.getString("gjj"));
			   json.put("公积金", gjj);
			   String qtzxfj=Util.null2o(rs.getString("qtzxfj"));
			   json.put("其他专项附加扣除", qtzxfj);
			   String gs=Util.null2o(rs.getString("gs"));
			   json.put("个税", gs);
			   String sfgz=Util.null2o(rs.getString("sfgz"));
			   json.put("实发工资", sfgz);
			   String pcj=Util.null2o(rs.getString("pcj"));
			   json.put("赔偿金", pcj);
			   String rzhj=Util.null2o(rs.getString("rzhj"));
			   json.put("入账合计", rzhj);
			   String fwf=Util.null2o(rs.getString("fwf"));
			   json.put("服务费", fwf);
			   String drrq=Util.null2String(rs.getString("drrq"));
			   json.put("导入日期", drrq);
			   String ny=Util.null2String(rs.getString("ny"));
			   json.put("年月", ny);
			   String bz=Util.null2String(rs.getString("bz"));
			   json.put("备注", bz);
			   jsons.add(json);
		   }
	   }
	return jsons;
   }
   //获取搜索后的基数数据导入计算器并返回给前端
   public JSONArray getGzJs(List<JSONObject> jsons,String subname){
	   basebean.writeLog("传入用来计算的jsons:");
	   basebean.writeLog(jsons);
	   JSONArray afterjsons = new JSONArray();
	   JSONObject afterjson = new JSONObject();
	   RecordSet rs = new RecordSet();
	   SalayCalculator salayCalculator = new SalayCalculator();
	   ForgameSalayTemplate forgameSalayTemplate=new ForgameSalayTemplate();
	   JlcSalaryTemplate jlcSalaryTemplate=new JlcSalaryTemplate();
	   if (jsons.size()>0) {
		   for (int i = 0; i < jsons.size(); i++) {
			   JSONObject json=jsons.get(i);
			   if (subname.equals("jlc")) {
				   String sub = Util.null2String(json.getString("分部"));
				   String bm = Util.null2String(json.getString("部门"));
				   String gh = Util.null2String(json.getString("工号"));
				   String xm = Util.null2String(json.getString("姓名"));
				   String rzrq=Util.null2String(json.getString("入职日期"));
				   String lzrq=Util.null2String(json.getString("离职日期"));
			       String jxyg=Util.null2String(json.getString("绩效预估"));
				   String bz = Util.null2String(json.getString("备注"));
				   String drrq=Util.null2String(json.getString("导入日期"));
				   String nianyue=json.getString("年月");
				   Map<String,String> map1 = new HashMap<String,String>();
				   for (int j = 6; j < GzName2.length-4; j++) {
					    map1.put(subname+","+GzName2[j], Util.null2o(json.getString(GzName2[j])));
				   }
				   try {
					   jlcSalaryTemplate = SalayCalculator.getJlcSalary((JlcSalaryTemplate) getBDecimal(map1));		
					   afterjson.put("分部", Util.null2String(sub));
					   afterjson.put("部门", Util.null2String(bm));
					   afterjson.put("工号", Util.null2String(gh));
					   afterjson.put("姓名", Util.null2String(xm));
					   afterjson.put("入职日期", Util.null2String(rzrq));
					   afterjson.put("离职日期", Util.null2String(lzrq));
					   afterjson.put("月薪总额", Util.null2String(jlcSalaryTemplate.getStandardSalary()));
					   afterjson.put("基本工资", Util.null2String(jlcSalaryTemplate.getBaseSalary()));
					   afterjson.put("岗位工资", Util.null2String(jlcSalaryTemplate.getStationSalary()));
					   afterjson.put("绩效奖金基数", Util.null2String(jlcSalaryTemplate.getPerformanceSalary()));
					   afterjson.put("电脑补贴", Util.null2String(jlcSalaryTemplate.getComputerSubsidy()));
					   afterjson.put("餐费补贴", Util.null2String(jlcSalaryTemplate.getMealSubsidy()));
					   afterjson.put("出差补贴", Util.null2String(jlcSalaryTemplate.getBusinessSubsidy()));
					   afterjson.put("考核/奖金", Util.null2String(jlcSalaryTemplate.getReward()));
					   afterjson.put("加班费", Util.null2String(jlcSalaryTemplate.getOvertimePayment()));
					   afterjson.put("其他补款", Util.null2String(jlcSalaryTemplate.getOtherPayment()));
					   afterjson.put("事假", Util.null2String(jlcSalaryTemplate.getAbsenceCut()));
					   afterjson.put("病假", Util.null2String(jlcSalaryTemplate.getSickCut()));
					   afterjson.put("其他", Util.null2String(jlcSalaryTemplate.getOtherCut()));
					   afterjson.put("应发工资", Util.null2String(jlcSalaryTemplate.getPlanSalary()));
					   afterjson.put("社保", Util.null2String(jlcSalaryTemplate.getSocialSecurity()));
					   afterjson.put("公积金", Util.null2String(jlcSalaryTemplate.getAccumulationFund()));
					   afterjson.put("其他专项附加扣除", Util.null2String(jlcSalaryTemplate.getOtherAdditionalCut()));
					   afterjson.put("个税", Util.null2String(jlcSalaryTemplate.getPersonalTax()));
					   afterjson.put("实发工资", Util.null2String(jlcSalaryTemplate.getRealSalary()));
					   afterjson.put("备注", Util.null2String(bz));
					   afterjson.put("绩效预估", Util.null2String(jxyg));
					   afterjson.put("离职补偿", Util.null2String(jlcSalaryTemplate.getQuitPayment()));
					   afterjson.put("入账合计", Util.null2String(jlcSalaryTemplate.getEnterAccount()));
					   afterjson.put("服务费", Util.null2String(jlcSalaryTemplate.getServicePayment()));
					   afterjson.put("导入日期", Util.null2String(drrq));
					   afterjson.put("年月", Util.null2String(nianyue));
					   afterjsons.add(afterjson);
					   rs.execute("select * from uf_gzt1 where ygh='"+gh+"' and nianyue='"+nianyue+"'");
					   boolean flag=false;
					   if (rs.next()) {
						   flag=true;
					   }
					   if (flag) {
						   rs.execute("update uf_gzt1 set xingming='"+xm+"',bumen='"+bm+"',fenbu='"+sub+"',"
						   		+ "yxze='"+jlcSalaryTemplate.getStandardSalary()+"',dnbt='"+jlcSalaryTemplate.getComputerSubsidy()+"',"
						   		+ "cfbt='"+jlcSalaryTemplate.getMealSubsidy()+"',ccbt='"+jlcSalaryTemplate.getBusinessSubsidy()+"',khjj='"+jlcSalaryTemplate.getReward()+"',"
						   	    + "jbf='"+jlcSalaryTemplate.getOvertimePayment()+"',qita='"+jlcSalaryTemplate.getOtherPayment()+"',"
						   	    + "shijia='"+jlcSalaryTemplate.getAbsenceCut()+"',bingjia='"+jlcSalaryTemplate.getSickCut()+"',"
						   	    + "qtzxfjkc='"+jlcSalaryTemplate.getOtherAdditionalCut()+"',yfgz='"+jlcSalaryTemplate.getPlanSalary()+"',"
						   	    + "shebao='"+jlcSalaryTemplate.getSocialSecurity()+"',gjj='"+jlcSalaryTemplate.getAccumulationFund()+"',"
						   	    + "geshui='"+jlcSalaryTemplate.getPersonalTax()+"',sfgz='"+jlcSalaryTemplate.getRealSalary()+"',"
						   	    + "beizhu='"+bz+"',drrq='"+drrq+"' where ygh='"+gh+"' and nianyue='"+nianyue+"'");
					   }else{
						   rs.execute("insert into uf_gzt1 "
								   + "(ygh,xingming,fenbu,bumen,yxze,dnbt,cfbt,ccbt,khjj,jbf,qita,shijia,bingjia,qtzxfjkc,yfgz,shebao,gjj,geshui,sfgz,beizhu,drrq,nianyue,"
								   + "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)"
								   + "values('"+gh+"','"+xm+"','"+sub+"','"+bm+"','"+jlcSalaryTemplate.getStandardSalary()+"','"+jlcSalaryTemplate.getComputerSubsidy()+"',"
								   		+ "'"+jlcSalaryTemplate.getMealSubsidy()+"','"+jlcSalaryTemplate.getBusinessSubsidy()+"','"+jlcSalaryTemplate.getReward()+"',"
								   		+ "'"+jlcSalaryTemplate.getOvertimePayment()+"','"+jlcSalaryTemplate.getOtherPayment()+"','"+jlcSalaryTemplate.getAbsenceCut()+"',"
								   		+ "'"+jlcSalaryTemplate.getSickCut()+"','"+jlcSalaryTemplate.getOtherAdditionalCut()+"','"+jlcSalaryTemplate.getPlanSalary()+"',"
								   		+ "'"+jlcSalaryTemplate.getSocialSecurity()+"','"+jlcSalaryTemplate.getAccumulationFund()+"','"+jlcSalaryTemplate.getPersonalTax()+"',"
								   		+ "'"+jlcSalaryTemplate.getRealSalary()+"','"+bz+"','"+drrq+"','"+nianyue+"','1','"+GzUtil.returnYmd()+"','00:00','38','0')");					
					   }
				   } catch (Exception e) {
					   e.getMessage();
				   }
			   }else{
				   String sub= Util.null2String(json.getString("分部"));
				   String bm = Util.null2String(json.getString("部门"));
				   String gh = Util.null2String(json.getString("工号"));
				   String xm = Util.null2String(json.getString("姓名"));
				   String rzrq=Util.null2String(json.getString("入职日期"));
				   String lzrq=Util.null2String(json.getString("离职日期"));
				   String bz = Util.null2String(json.getString("备注"));
				   String drrq=Util.null2String(json.getString("导入日期"));
				   String ny=Util.null2String(json.getString("年月"));
				   Map<String,String> map1 = new HashMap<String,String>();
				   for (int j = 6; j < GzName1.length-3; j++) {
					   basebean.writeLog("===========>"+j);
					   basebean.writeLog("===========>"+j);
					   basebean.writeLog(subname+","+GzName1[j]+"json.getString(GzName1[j]):"+json.getString(GzName1[j]));
					   basebean.writeLog("===========>"+j);
					   map1.put(subname+","+GzName1[j], Util.null2o(json.getString(GzName1[j])));
					   basebean.writeLog("===========>"+j);
				   } 
				   try {
					   forgameSalayTemplate = SalayCalculator.getForgameSalay((ForgameSalayTemplate)getBDecimal(map1));
					   afterjson.put("分部", Util.null2String(sub));
					   afterjson.put("部门", Util.null2String(bm));
					   afterjson.put("工号", Util.null2String(gh));
					   afterjson.put("姓名", Util.null2String(xm));
					   afterjson.put("入职日期", Util.null2String(rzrq));
					   afterjson.put("离职日期", Util.null2String(lzrq));
					   afterjson.put("月薪总额", Util.null2String(forgameSalayTemplate.getStandardSalary()));
					   afterjson.put("基本工资", Util.null2String(forgameSalayTemplate.getBaseSalary()));
					   afterjson.put("岗位工资", Util.null2String(forgameSalayTemplate.getStationSalary()));
					   afterjson.put("绩效奖金基数", Util.null2String(forgameSalayTemplate.getPerformanceSalary()));
					   afterjson.put("全勤奖", Util.null2String(forgameSalayTemplate.getFullServiceSubsidy()));
					   afterjson.put("餐费补贴", Util.null2String(forgameSalayTemplate.getMealSubsidy()));
					   afterjson.put("其他补贴", Util.null2String(forgameSalayTemplate.getOtherSubsidy()));
					   afterjson.put("考核/奖金", Util.null2String(forgameSalayTemplate.getReward()));
					   afterjson.put("加班费",Util.null2String(forgameSalayTemplate.getOvertimePayment()));
					   afterjson.put("其他补款", Util.null2String(forgameSalayTemplate.getOtherPayment()));
					   afterjson.put("请假", Util.null2String(forgameSalayTemplate.getLeaveCut()));
					   afterjson.put("迟到", Util.null2String(forgameSalayTemplate.getLateCut()));
					   afterjson.put("其他", Util.null2String(forgameSalayTemplate.getOtherCut()));
					   afterjson.put("应发工资", Util.null2String(forgameSalayTemplate.getPlanSalary()));
					   afterjson.put("社保", Util.null2String(forgameSalayTemplate.getSocialSecurity()));
					   afterjson.put("公积金",Util.null2String(forgameSalayTemplate.getAccumulationFund()));
					   afterjson.put("其他专项附加扣除", Util.null2String(forgameSalayTemplate.getOtherAdditionalCut()));
					   afterjson.put("个税", Util.null2String(forgameSalayTemplate.getPersonalTax()));
					   afterjson.put("实发工资", Util.null2String(forgameSalayTemplate.getRealSalary()));
					   afterjson.put("赔偿金", Util.null2String(forgameSalayTemplate.getCompensatePayment()));
					   afterjson.put("入账合计",Util.null2String(forgameSalayTemplate.getEnterAccount()));
					   afterjson.put("服务费", Util.null2String(forgameSalayTemplate.getServicePayment()));
					   afterjson.put("备注", Util.null2String(bz));
					   afterjson.put("导入日期", Util.null2String(drrq));
					   afterjson.put("年月", Util.null2String(ny));
					   basebean.writeLog("----------->"+i);
					   basebean.writeLog("----------->"+i);
					   basebean.writeLog("afterjson:"+afterjson);
					   basebean.writeLog("----------->"+i);
					   basebean.writeLog("----------->"+i);
					   afterjsons.add(afterjson);
					   rs.execute("select * from uf_gzt2 where ygh='"+gh+"' and nianyue='"+ny+"'");
					   boolean flag=false;
					   if (rs.next()) {
						   flag=true;
					   }
					   if (flag) {
						   rs.execute("update uf_gzt2 set xingming='"+xm+"',bumen='"+bm+"',fenbu='"+sub+"',"
						   		+ "yxze='"+forgameSalayTemplate.getStandardSalary()+"',jbgz='"+forgameSalayTemplate.getBaseSalary()+"',"
						   		+ "gwgz='"+forgameSalayTemplate.getStationSalary()+"',jxjjjs='"+forgameSalayTemplate.getPerformanceSalary()+"',"
						   		+ "qqj='"+forgameSalayTemplate.getFullServiceSubsidy()+"',"
						   	    + "cfbt='"+forgameSalayTemplate.getMealSubsidy()+"',qtbt='"+forgameSalayTemplate.getOtherSubsidy()+"',"
						   	    + "khjj='"+forgameSalayTemplate.getReward()+"',jbf='"+forgameSalayTemplate.getOvertimePayment()+"',"
						   	    + "qtbk='"+forgameSalayTemplate.getOtherPayment()+"',qingjia='"+forgameSalayTemplate.getLeaveCut()+"',"
						   	    + "chidao='"+forgameSalayTemplate.getLateCut()+"',qita='"+forgameSalayTemplate.getOtherCut()+"',"
						   	    + "yfgz='"+forgameSalayTemplate.getPlanSalary()+"',shebao='"+forgameSalayTemplate.getSocialSecurity()+"',"
						   	    + "gjj='"+forgameSalayTemplate.getAccumulationFund()+"',geshui='"+forgameSalayTemplate.getPersonalTax()+"',"
						   	    + "sfgz='"+forgameSalayTemplate.getRealSalary()+"', "
						   	    + "beizhu='"+bz+"',drrq='"+drrq+"',nianyue='"+ny+"' where ygh='"+gh+"' and nianyue='"+ny+"'");
					   }else{
						   rs.execute("insert into uf_gzt2 "
								   + "(ygh,xingming,fenbu,bumen,yxze,jbgz,gwgz,jxjjjs,qqj,cfbt,qtbt,khjj,jbf,qtbk,qingjia,chidao,qita,yfgz,shebao,gjj,geshui,sfgz,beizhu,drrq,nianyue,"
								   + "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)"
								   + "values('"+gh+"','"+xm+"','"+sub+"','"+bm+"','"+forgameSalayTemplate.getStandardSalary()+"','"+forgameSalayTemplate.getBaseSalary()+"',"
								   		+ "'"+forgameSalayTemplate.getStationSalary()+"','"+forgameSalayTemplate.getPerformanceSalary()+"','"+forgameSalayTemplate.getFullServiceSubsidy()+"',"
								   		+ "'"+forgameSalayTemplate.getMealSubsidy()+"','"+forgameSalayTemplate.getOtherSubsidy()+"','"+forgameSalayTemplate.getReward()+"',"
								   		+ "'"+forgameSalayTemplate.getOvertimePayment()+"','"+forgameSalayTemplate.getOtherPayment()+"','"+forgameSalayTemplate.getLeaveCut()+"',"
								   		+ "'"+forgameSalayTemplate.getLateCut()+"','"+forgameSalayTemplate.getOtherCut()+"','"+forgameSalayTemplate.getPlanSalary()+"',"
								   		+ "'"+forgameSalayTemplate.getSocialSecurity()+"','"+forgameSalayTemplate.getAccumulationFund()+"','"+forgameSalayTemplate.getPersonalTax()+"',"
								   		+ "'"+forgameSalayTemplate.getRealSalary()+"','"+bz+"','"+drrq+"','"+ny+"','1','"+GzUtil.returnYmd()+"','00:00','37','0')");					
					   }
				   } catch (Exception e) {
					 e.getMessage();
				   }
			   }
		   }
	   }
	   return afterjsons;
   }
   //获取公司部门岗位人员表的指定字段数据
   public static String getName(String Fn,String Bn,String Bz,String workcode){
	   RecordSet rs = new RecordSet();
	   String name = "";
	   if (Bn.equals("hrmresource")) {
		   rs.execute("select "+Fn+" from "+Bn+" where "+Bz+"='"+workcode+"'");
		   if (rs.next()) {
			   name=rs.getString(Fn);
		   }
	   }else if(Bn.equals("hrmdepartment")){
		   rs.execute("select "+Fn+" from "+Bn+" where "+Bz+" in (select departmentid from hrmresource where workcode='"+workcode+"')");
		   if (rs.next()) {
			   name=rs.getString(Fn);  
		   }
	   }else{
		   rs.execute("select "+Fn+" from "+Bn+" where "+Bz+" in(select subcompanyid1 from hrmresource where workcode='"+workcode+"')");   
		   if (rs.next()) {
			name=rs.getString(Fn);
		   }
	   }
	return name;
   }
   //将基础数据转换成bigdecimal类型并存入javabean返回
   public static Object getBDecimal(Map<String,String> map){
	   ForgameSalayTemplate forgameSalayTemplate = new ForgameSalayTemplate();
	   JlcSalaryTemplate jlcSalaryTemplate=new JlcSalaryTemplate();
	   String flag ="";
	   for (Map.Entry<String, String> entry:map.entrySet()) {
		   flag = entry.getKey().split(",")[0];
		   basebean.writeLog("^^^^^^^^^^^");
		   basebean.writeLog("^^^^^^^^^^^:"+flag+"^^^^^^^^^^:"+entry.getValue());
		   BigDecimal bigDecimal=new BigDecimal(Util.null2o(entry.getValue()).replaceAll(",",""));
		   basebean.writeLog("^^^^^^^^^^^"+bigDecimal);
		   basebean.writeLog("----------------------------------------------------->");
		   if (flag.equals("jlc")) {
			   switch (entry.getKey().split(",")[1]) {
			   case "月薪总额":
				   jlcSalaryTemplate.setStandardSalary(bigDecimal);
				   break;
			   case "基本工资":
				   jlcSalaryTemplate.setBaseSalary(bigDecimal);
				   break;
			   case "岗位工资":
				   jlcSalaryTemplate.setStationSalary(bigDecimal);
				   break;
			   case "绩效奖金基数":
				   jlcSalaryTemplate.setPerformanceSalary(bigDecimal);
				   break;
			   case "电脑补贴":
				   jlcSalaryTemplate.setComputerSubsidy(bigDecimal);
				   break;
			   case "餐费补贴":
				   jlcSalaryTemplate.setMealSubsidy(bigDecimal);
				   break;
			   case "出差补贴":
				   jlcSalaryTemplate.setBusinessSubsidy(bigDecimal);
				   break;
			   case "考核/奖金":
				   jlcSalaryTemplate.setReward(bigDecimal);
				   break;
			   case "加班费":
				   jlcSalaryTemplate.setOvertimePayment(bigDecimal);
				   break;
			   case "其他补款":
				   jlcSalaryTemplate.setOtherPayment(bigDecimal);
				   break;
			   case "事假":
				   jlcSalaryTemplate.setAbsenceCut(bigDecimal);
				   break;
			   case "病假":
				   jlcSalaryTemplate.setSickCut(bigDecimal);
				   break;
			   case "其他":
				   jlcSalaryTemplate.setOtherCut(bigDecimal);
				   break;
			   case "应发工资":
				   jlcSalaryTemplate.setPlanSalary(bigDecimal);
				   break;
			   case "社保":
				   jlcSalaryTemplate.setSocialSecurity(bigDecimal);
				   break;
			   case "公积金":
				   jlcSalaryTemplate.setAccumulationFund(bigDecimal);
				   break;
			   case "其他专项附加扣除":
				   jlcSalaryTemplate.setOtherAdditionalCut(bigDecimal);
				   break;
			   case "个税":
				   jlcSalaryTemplate.setPersonalTax(bigDecimal);
				   break;
			   case "实发工资":
				   jlcSalaryTemplate.setRealSalary(bigDecimal);
				   break;
			   case "离职补偿":
				   jlcSalaryTemplate.setQuitPayment(bigDecimal);
				   break;
			   case "入账合计":
				   jlcSalaryTemplate.setEnterAccount(bigDecimal);
				   break;
			   case "服务费":
				   jlcSalaryTemplate.setServicePayment(bigDecimal);
				   break;
			   default:
				   break;
			   }}
           if (flag.equals("forgame")) {
        	   basebean.writeLog("**************");
        	   basebean.writeLog(flag+"**************"+entry.getKey().split(",")[1]);
        	   basebean.writeLog("**************");
			   switch (entry.getKey().split(",")[1]) {
			   case "月薪总额":
				   forgameSalayTemplate.setStandardSalary(bigDecimal);
				   break;
			   case "基本工资":
				   forgameSalayTemplate.setBaseSalary(bigDecimal);
				   break;
			   case "岗位工资":
				   forgameSalayTemplate.setStationSalary(bigDecimal);
				   break;
			   case "绩效奖金基数":
				   forgameSalayTemplate.setPerformanceSalary(bigDecimal);
				   break;
			   case "全勤奖":
				   forgameSalayTemplate.setFullServiceSubsidy(bigDecimal);
				   break;
			   case "餐费补贴":
				   forgameSalayTemplate.setMealSubsidy(bigDecimal);
				   break;
			   case "其他补贴":
				   forgameSalayTemplate.setOtherSubsidy(bigDecimal);
				   break;
			   case "考核/奖金":
				   forgameSalayTemplate.setReward(bigDecimal);
				   break;
			   case "加班费":
				   forgameSalayTemplate.setOvertimePayment(bigDecimal);
				   break;
			   case "其他补款":
				   forgameSalayTemplate.setOtherPayment(bigDecimal);
				   break;
			   case "请假":
				   forgameSalayTemplate.setLeaveCut(bigDecimal);
				   break;
			   case "迟到":
				   forgameSalayTemplate.setLateCut(bigDecimal);
				   break;
			   case "其他":
				   forgameSalayTemplate.setOtherCut(bigDecimal);
				   break;
			   case "应发工资":
				   forgameSalayTemplate.setPlanSalary(bigDecimal);
				   break;
			   case "社保":
				   forgameSalayTemplate.setSocialSecurity(bigDecimal);
				   break;
			   case "公积金":
				   forgameSalayTemplate.setAccumulationFund(bigDecimal);
				   break;
			   case "其他专项附加扣除":
				   forgameSalayTemplate.setOtherAdditionalCut(bigDecimal);
				   break;
			   case "个税":
				   forgameSalayTemplate.setPersonalTax(bigDecimal);
				   break;
			   case "实发工资":
				   forgameSalayTemplate.setRealSalary(bigDecimal);
				   break;
			   case "赔偿金":
				   forgameSalayTemplate.setCompensatePayment(bigDecimal);
				   break;
			   case "入账合计":
				   forgameSalayTemplate.setEnterAccount(bigDecimal);
				   break;
			   case "服务费":
				   forgameSalayTemplate.setServicePayment(bigDecimal);
				   break;
			   default:
				   break;
			   }
		   }
	   }
	   if (flag.equals("forgame")) {
		   basebean.writeLog("@@@@@@@@@@@@@"+forgameSalayTemplate);
		   return forgameSalayTemplate;
	   } else{
		   basebean.writeLog("@@@@@@@@@@@@@"+jlcSalaryTemplate);
		   return jlcSalaryTemplate;
	   }
   }
   //无权限人员用的util
   public static JSONArray getGZJS_WQ(String id ,String rq,String bm){
	   JSONArray jsons = new JSONArray();
	   RecordSet rs = new RecordSet();
	   rs.execute("select * from "+bm+" where ygh in(select workcode from hrmresource where id='"+id+"') and nianyue='"+rq+"'");
	   while (rs.next())
 	    {
 	      JSONObject json = new JSONObject();
 	      String[] columnName = rs.getColumnName();
 	      for (int i = 0; i < columnName.length; i++)
 	      {
 	        String name = columnName[i].toLowerCase();
 	        String value = rs.getString(name);
 	        json.put(getCHname(name,bm), value);
 	      }
 	      jsons.add(json);
 	    }
	return jsons;
   }
	   
   public static String getCHname(String ENname,String bm){
	   String chname="";
	   if (bm.equals("uf_gzt1")) {//简理财SBU
		  switch (ENname) {
		  case "ygh":
		      chname="工号";
			  break;
		  case "xingming":
			  chname="姓名";
			  break;
		  case "fenbu":
		      chname="分部";
			  break;
		  case "bumen":
			  chname="部门";
			  break;
		  case "yxze":
		      chname="月薪总额";
			  break;
		  case "dnbt":
			  chname="电脑补贴";
			  break;
		  case "cfbt":
		      chname="餐费补贴";
			  break;
		  case "ccbt":
			  chname="出差补贴";
			  break;
		  case "khjj":
		      chname="考核/奖金";
			  break;
		  case "jbf":
			  chname="加班费";
			  break;
		  case "qita":
		      chname="其他";
			  break;
		  case "shijia":
			  chname="事假";
			  break;
		  case "bingjia":
		      chname="病假";
			  break;
		  case "qtzxfjkc":
			  chname="其他专项附加扣除";
			  break;
		  case "yfgz":
		      chname="应发工资";
			  break;
		  case "shebao":
			  chname="社保";
			  break;
		  case "gjj":
		      chname="公积金";
			  break;
		  case "geshui":
			  chname="个税";
			  break;
		  case "sfgz":
		      chname="实发工资";
			  break;
		  case "beizhu":
			  chname="备注";
			  break;
		  case "drrq":
		      chname="导入日期";
			  break;
		  case "nianyue":
			  chname="年月";
			  break;
		default:
			break;
		}
	}else{
		 switch (ENname) {
		  case "ygh":
		      chname="工号";
			  break;
		  case "xingming":
			  chname="姓名";
			  break;
		  case "fenbu":
		      chname="分部";
			  break;
		  case "bumen":
			  chname="部门";
			  break;
		  case "zhiwei":
			  chname="职位";
			  break;
		  case "yxze":
		      chname="月薪总额";
			  break;
		  case "jbgz":
			  chname="基本工资";
			  break;
		  case "gwgz":
		      chname="岗位工资";
			  break;
		  case "jxjjjs":
			  chname="绩效奖金基数";
			  break;
		  case "qqj":
		      chname="全勤奖";
			  break;
		  case "cfbt":
			  chname="餐费补贴";
			  break;
		  case "qtbt":
		      chname="其他补贴";
			  break;
		  case "khjj":
			  chname="考核/奖金";
			  break;
		  case "jbf":
		      chname="加班费";
			  break;
		  case "qtbk":
			  chname="其他补款";
			  break;
		  case "qingjia":
		      chname="请假";
			  break;
		  case "chidao":
			  chname="迟到";
			  break;
		  case "qita":
		      chname="其他";
			  break;
		  case "yfgz":
			  chname="应发工资";
			  break;
		  case "shebao":
		      chname="社保";
			  break;
		  case "gjj":
			  chname="公积金";
			  break;
		  case "geshui":
		      chname="个税";
			  break;
		  case "sfgz":
			  chname="实发工资";
			  break;
		  case "beizhu":
			  chname="备注";
			  break;
		  case "drrq":
		      chname="导入日期";
			  break;
		  case "nianyue":
			  chname="年月";
			  break;
		default:
			break;
	}
  }
	     return chname;
   }
   public static String returnYmd()
   {
	   Date date = new Date();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   String dateNowStr = sdf.format(date);
	return dateNowStr;
   }

}
