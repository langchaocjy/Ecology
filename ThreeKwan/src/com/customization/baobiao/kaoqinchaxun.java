package com.customization.baobiao;

import com.customization.AllUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

public class kaoqinchaxun {
   private static BaseBean basebean = new BaseBean();
   public static JSONArray getData(String nameid,String sub,String dep,String ym,String status)
   {
	   RecordSet rs = new RecordSet(),rs1 = new RecordSet(),rs2 = new RecordSet();
	   String sql1 = "";
	   if (!nameid.equals("")) //如果姓名不为空，就查这个人
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t1.id='"+nameid+"'";
	   }
	   else if(nameid.equals("") && sub.equals("") && !dep.equals("")) //分部为空，部门不为空，用部门
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.id='"+dep+"'";
	   }
	   else if(nameid.equals("") && !sub.equals("") && !dep.equals(""))//两个都不为空，用部门
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.id='"+dep+"'";
	   }
	   else if(nameid.equals("") && !sub.equals("") && dep.equals(""))//分部不为空，部门空了，查分部下的所有人
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id and t2.subcompanyid1='"+sub+"'";
	   }
	   else if(nameid.equals("") && sub.equals("") && dep.equals(""))//两个都为空
	   {
		   sql1 = "select t1.id,t1.lastname,t1.departmentid,t2.departmentname from hrmresource t1,hrmdepartment t2 where t1.departmentid=t2.id";
	   }
	   basebean.writeLog("[sql1----->]"+sql1);
	   rs.execute(sql1);
	   JSONArray jsons_sqr = new JSONArray();
	   JSONObject json_sqr = new JSONObject();
	   while(rs.next())
	   {
		   String ryid = Util.null2String(rs.getString("id"));//hrmresource
		   String lastname = Util.null2String(rs.getString("lastname"));//hrmresource
		   String departmentid = Util.null2String(rs.getString("departmentid"));//hrmresource
		   String departmentname = Util.null2String(rs.getString("departmentname"));//hrmdepartment
		   
		   rs1.execute("select id from uf_kqxg where sqr='"+ryid+"' ");
		   if (rs1.next()) {
			   while(rs1.next()){
				   String zhubiaoid = Util.null2String(rs1.getString("id"));
				   json_sqr.put("id",zhubiaoid);
				   json_sqr.put("姓名", lastname);
				   json_sqr.put("部门", departmentname);
				   json_sqr.put("部门id", departmentid);
				   json_sqr.put("sqr", ryid);
				   jsons_sqr.add(json_sqr);
			   }
		   } else {
			   json_sqr.put("id","0");
			   json_sqr.put("姓名", lastname);
			   json_sqr.put("部门", departmentname);
			   json_sqr.put("部门id", departmentid);
			   json_sqr.put("sqr", ryid);
			   jsons_sqr.add(json_sqr);
		   }
	   }
	   
	   JSONArray return_alljson = null;
	   JSONArray jsons_tongji = new JSONArray();
	   JSONObject json_tongji = new JSONObject();

		  for (int i = 0; i < jsons_sqr.size(); i++) 
		  {
			  //休假类型统计开始
			int shijia = 0,bingjia = 0,nianjia = 0,hunjia=0,sangjia=0,chanjianjia=0,chanjia=0,peichanjia=0,burujia=0,chuchaitianshu=0,waichutianshu=0,qingjiacishu=0,qingjiatianshu=0;
			   JSONObject j_s1 = jsons_sqr.getJSONObject(i);
			  String id = Util.null2String(j_s1.getString("id"));
			 String sqr = Util.null2String(j_s1.getString("sqr"));
			String name = Util.null2String(j_s1.getString("姓名"));
			String department = Util.null2String(j_s1.getString("部门"));
			String departmentid=Util.null2String(j_s1.getString("部门id"));
			rs.execute("select * from uf_kqxg_dt1 where mainid = '"+id+"'");
			if (rs.next()) {qingjiacishu+=1;}
			while (rs.next()) 
			{
				String xjlx = Util.null2String(rs.getString("xjlx"));
				String ksrq = Util.null2String(rs.getString("ksrq"));
				String jsrq = Util.null2String(rs.getString("jsrq"));
				/*
				 * 情况1：用户搜索10月份的数据，有些请假日期是9月份申请的，请假日期全部在10月份。  只需要判断搜索年月跟开始日期是否匹配
				 * 情况2：用户搜索10月份的数据，9月份申请了9月27号到10月7号的请假记录，这个时候就要截取10月1号到7号的天数
				 * 情况3：用户搜索10月份的数据，有10月27到11月3号的请假记录，截取10月27到11月1号之前的天数
				 */
				if (ym.equals(ksrq.substring(0, 7)) || ym.equals(jsrq.substring(0, 7))) 
				{
					int days = AllUtil.Numberofstatistical(ym,ksrq, jsrq);
					if (xjlx.equals("")) {
						xjlx = "9";
					}
					if (Integer.parseInt(xjlx)==0){
						shijia += days;
					}else if(Integer.parseInt(xjlx)==1){
						bingjia += days;
					}else if(Integer.parseInt(xjlx)==2){
						nianjia += days;
					}else if(Integer.parseInt(xjlx)==3){
						hunjia += days;
					}else if(Integer.parseInt(xjlx)==4){
						sangjia += days;
					}else if(Integer.parseInt(xjlx)==5){
						chanjianjia += days;
					}else if(Integer.parseInt(xjlx)==6){
						chanjia += days;
					}else if(Integer.parseInt(xjlx)==7){
						peichanjia+=days;
					}else if(Integer.parseInt(xjlx)==8){
						burujia+=days;
					}
				}
			 }
			qingjiatianshu=shijia+bingjia+nianjia+hunjia+sangjia+chanjianjia+chanjia+peichanjia+burujia;
			json_tongji.put("姓名", name);
			json_tongji.put("部门", department);
			json_tongji.put("sqr", sqr);
			json_tongji.put("事假", shijia);
			json_tongji.put("病假", bingjia);
			json_tongji.put("年假", nianjia);
			json_tongji.put("婚假", hunjia);
			json_tongji.put("丧假", sangjia);
			json_tongji.put("产检假", chanjianjia);
			json_tongji.put("产假", chanjia);
			json_tongji.put("陪产假", peichanjia);
			json_tongji.put("哺乳假", burujia);
			json_tongji.put("请假次数", qingjiacishu);
			json_tongji.put("请假天数", qingjiatianshu);
			//出差天数统计开始
			rs.execute("select * from uf_kqxg_dt2 where mainid = '"+id+"'");
			while(rs.next())
			{
				String ksrq = Util.null2String(rs.getString("ksrq"));
				String jsrq = Util.null2String(rs.getString("jsrq"));
				String ccts = Util.null2String(rs.getString("xglc"));
				if (ym.equals(ksrq.substring(0, 7)) || ym.equals(jsrq.substring(0, 7))) 
			    {
					int days = AllUtil.Numberofstatistical(ym,ksrq, jsrq);
			    	chuchaitianshu += days;
				}	
			}
			json_tongji.put("出差天数", chuchaitianshu);
			//外出统计开始
			rs.execute("select * from uf_kqxg_dt3 where mainid = '"+id+"'");
			while(rs.next())
			{
				String ksrq = Util.null2String(rs.getString("ksrq"));
				String jsrq = Util.null2String(rs.getString("jsrq"));
				if (ym.equals(ksrq.substring(0, 7)) || ym.equals(jsrq.substring(0, 7))) 
			    {
					int days = AllUtil.Numberofstatistical(ym,ksrq, jsrq);
					waichutianshu += days;
			    }
			}
			json_tongji.put("外出天数", waichutianshu);
		
			//迟到早退旷工统计开始
			int kgts=0,cdcs=0,cdfz=0,ztcs=0,ztfz=0,sjcqts=0,ldkcs=0,zonggongshi=0;
			int x1=0,x2=0,x3=0,x4=0,jbsc=0;
			boolean isHave = AllUtil.getTf2s(jsons_tongji, sqr);
			if (!isHave) {
				rs.execute("select * from uf_kqsj where xm='"+sqr+"'");
				while (rs.next()) {
					String rq =Util.null2String(rs.getString("rq"));//日期2018-11-18
					if (rq.substring(0, 7).equals(ym)) {
						String dksj=Util.null2String(rs.getString("dksj"));//打卡时间  "08:00 16:00";
						String pbsjs=Util.null2String(rs.getString("pbsj"));//排班时间  "A班[08:00 16:00]" "R班";
						String bq=Util.null2String(rs.getString("bq"));    //补签
						if (!pbsjs.equals("R班") && !pbsjs.equals("") && !pbsjs.trim().equals("休息")) {
							int x = pbsjs.indexOf("[");
							int y = pbsjs.indexOf("]");
							String pbsj=pbsjs.substring(x+1, y);
							if (!dksj.equals("")) {sjcqts+=1;}//实际出勤天数
							if (dksj.length()>7) {//正常情况,统计加班次数,时长
								String bOrs1=Util.null2String(AllUtil.getZzZw(dksj.split(" ")[0], pbsj.split(" ")[0], "max"));//判断是否迟到
								String bOrs2=Util.null2String(AllUtil.getZzZw(dksj.split(" ")[1], pbsj.split(" ")[1], "max"));//判断是否早退
								if (bOrs1.equals(dksj.split(" ")[0])) //迟到
								{
									cdcs+=1;
									cdfz+=Integer.parseInt(AllUtil.dateDiff(pbsj.split(" ")[0], dksj.split(" ")[0], "hh:mm"));
								}
								if (bOrs2.equals(pbsj.split(" ")[1])) //早退
								{
									ztcs+=1;
									ztfz+=Integer.parseInt(AllUtil.dateDiff(dksj.split(" ")[1], pbsj.split(" ")[1], "hh:mm"));
								}

								if (!departmentid.equals("18")) {//加班次数等字段只计算非客服部人员
										int flag = AllUtil.compartToTime(dksj.split(" ")[1], "19:30");
										if (flag==-1||flag==0) {//19:30
											x1+=1;
										}else{
											flag = AllUtil.compartToTime(dksj.split(" ")[1], "20:30");
											if (flag==-1 || flag==0) {//19:30-20:30
												x2+=1;
											}else{
												flag = AllUtil.compartToTime(dksj.split(" ")[1], "22:00");
												if (flag==-1 || flag==0) {//20:30-22:00
													x3+=1;
													jbsc+=Integer.parseInt(AllUtil.dateDiff("20:30", dksj.split(" ")[1], "hh:mm"));
												}else{
													x4+=1;
													jbsc+=Integer.parseInt(AllUtil.dateDiff("20:30", dksj.split(" ")[1], "hh:mm"));
												}
											}
										}
										zonggongshi+=Integer.parseInt(AllUtil.dateDiff("18:00", dksj.split(" ")[1], "hh:mm"));//下班卡到18：00之间的时间
								}
							}else if(!dksj.equals("")&& dksj.length()<7){//这个是没有下班卡的情况
								if (bq.equals("")) {//漏打卡的情况，指的是没有打卡，也没有申请补签
									ldkcs+=1;//漏打卡次数
								}else{//补签后就不存在迟到早退，因为用户肯定会把时间设置成下班时间，就算没有也默认。
									zonggongshi+=Integer.parseInt(AllUtil.dateDiff("18:00", dksj.split(" ")[1], "hh:mm"));
								}
							}else{//这个是旷工的情况,包括漏打卡
								if (bq.equals("")) {
									ldkcs+=1;
									kgts+=1;
								}
							}
						}
					}
				}
				
				int shijichuqintianshu = sjcqts+chuchaitianshu+waichutianshu;
				int pingjunrgs = shijichuqintianshu==0?0:((shijichuqintianshu*7*60+zonggongshi)/shijichuqintianshu);
				int pjjbsc=(x3+x4)==0?0:(jbsc/(x3+x4));
				json_tongji.put("漏打卡天数", ldkcs);
				json_tongji.put("实际出勤天数", shijichuqintianshu);
				json_tongji.put("早退分钟", ztfz);
				json_tongji.put("迟到分钟", cdfz);
				json_tongji.put("早退次数", ztcs);
				json_tongji.put("迟到次数", cdcs);
				json_tongji.put("旷工天数", kgts);
				json_tongji.put("19:30前下班",x1);
				json_tongji.put("19:30-20:30加班", x2);
				json_tongji.put("20:30-22:00加班", x3);
				json_tongji.put("22:00后加班", x4);
				json_tongji.put("加班次数", (x3+x4));
				json_tongji.put("加班时长", jbsc);
				json_tongji.put("平均加班时长", pjjbsc);
				json_tongji.put("总工时", (shijichuqintianshu*7*60+zonggongshi));
				json_tongji.put("平均日工时", pingjunrgs);
				jsons_tongji.add(json_tongji);
			}else{
				json_tongji.put("漏打卡天数", "0");
				json_tongji.put("实际出勤天数", "0");
				json_tongji.put("早退分钟", "0");
				json_tongji.put("迟到分钟", "0");
				json_tongji.put("早退次数", "0");
				json_tongji.put("迟到次数", "0");
				json_tongji.put("旷工天数", "0");
				json_tongji.put("19:30前下班","0");
				json_tongji.put("19:30-20:30加班", "0");
				json_tongji.put("20:30-22:00加班", "0");
				json_tongji.put("22:00后加班", "0");
				json_tongji.put("加班次数", "0");
				json_tongji.put("加班时长", "0");
				json_tongji.put("平均加班时长", "0");
				json_tongji.put("总工时", "0");
				json_tongji.put("平均日工时", "0");
				jsons_tongji.add(json_tongji);
			}
		  }
		  return_alljson = AllUtil.returnJSON(jsons_tongji);
	return return_alljson;
   }
}
