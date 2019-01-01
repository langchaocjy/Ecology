package com.GZ;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.general.Util;

public class HzUtil {
	public String[] huiz1="部门|月薪总额|电脑补贴|餐费补贴|出差补贴|考核/奖金|加班费|其他|事假|病假|其他专项附加扣除|应发工资|社保|公积金|个税|实发工资".split("\\|", -1);
	public String[] huiz2="部门|月薪总额|基本工资|岗位工资|绩效奖金基数|全勤奖|餐费补贴|其他补贴|考核/奖金|加班费|其他补款|请假|迟到|其他|应发工资|社保|公积金|个税|实发工资|单位缴纳工会经费".split("\\|", -1);
	public String[] huiz3="部门|基本工资|岗位工资|绩效奖金基数|全勤奖|餐费补贴|其他补贴|考核/奖金|加班费|其他补款|请假|迟到|其他|应发工资|社保|公积金|个税|实发工资|服务费".split("\\|", -1);
	public String[] huiz4="部门|基本工资|岗位工资|绩效奖金基数|全勤奖|餐费补贴|其他补贴|考核/奖金|加班费|其他补款|请假|迟到|其他|应发工资|社保|公积金|个税|实发工资|赔偿金|服务费".split("\\|", -1);
	public static DecimalFormat df = new DecimalFormat("#.00");
    public static JSONArray returnJSON1(JSONArray jsonsarray)
	   {
		  JSONArray arrayTemp = new JSONArray();
		  int num = 0;
		  for (int i = 0; i < jsonsarray.size(); i++) 
		  {
			 if (num == 0) 
			 {
			    arrayTemp.add(jsonsarray.get(i));	
			 } 
			 else
			 {
				 int numJ = 0;
				for (int j = 0; j < arrayTemp.size(); j++) 
				{
					JSONObject newJsonObjectI = (JSONObject)jsonsarray.get(i);
					JSONObject newJsonObjectJ = (JSONObject)arrayTemp.get(j);
					String depI = Util.null2o(newJsonObjectI.getString("部门"));
					String subI = Util.null2o(newJsonObjectI.getString("分部"));
					String yxzeI = Util.null2o(newJsonObjectI.getString("月薪总额"));
					String dnbtI = Util.null2o(newJsonObjectI.getString("电脑补贴"));
					String cfbtI = Util.null2o(newJsonObjectI.getString("餐费补贴"));
					String ccbtI = Util.null2o(newJsonObjectI.getString("出差补贴"));
					String khjjI = Util.null2o(newJsonObjectI.getString("考核/奖金"));
					String jbfI = Util.null2o(newJsonObjectI.getString("加班费"));
					String qtI=Util.null2o(newJsonObjectI.getString("其他"));
					String sjI=Util.null2o(newJsonObjectI.getString("事假"));
					String bjI = Util.null2o(newJsonObjectI.getString("病假"));
					String qtzxfjkcI = Util.null2o(newJsonObjectI.getString("其他专项附加扣除"));
					String yfgzI = Util.null2o(newJsonObjectI.getString("应发工资"));
					String sbI = Util.null2o(newJsonObjectI.getString("社保"));
					String gjjI = Util.null2o(newJsonObjectI.getString("公积金"));
					String gsI = Util.null2o(newJsonObjectI.getString("个税"));
					String sfgzI = Util.null2o(newJsonObjectI.getString("实发工资"));
					
					String depJ = Util.null2o(newJsonObjectJ.getString("部门"));
					String subJ = Util.null2o(newJsonObjectJ.getString("分部"));
					String yxzeJ =Util.null2o( newJsonObjectJ.getString("月薪总额"));
					String dnbtJ = Util.null2o(newJsonObjectJ.getString("电脑补贴"));
					String cfbtJ = Util.null2o(newJsonObjectJ.getString("餐费补贴"));
					String ccbtJ = Util.null2o(newJsonObjectJ.getString("出差补贴"));
					String khjjJ = Util.null2o(newJsonObjectJ.getString("考核/奖金"));
					String jbfJ = Util.null2o(newJsonObjectJ.getString("加班费"));
					String qtJ=Util.null2o(newJsonObjectJ.getString("其他"));
					String sjJ=Util.null2o(newJsonObjectJ.getString("事假"));
					String bjJ = Util.null2o(newJsonObjectJ.getString("病假"));
					String qtzxfjkcJ = Util.null2o(newJsonObjectJ.getString("其他专项附加扣除"));
					String yfgzJ = Util.null2o(newJsonObjectJ.getString("应发工资"));
					String sbJ = Util.null2o(newJsonObjectJ.getString("社保"));
					String gjjJ = Util.null2o(newJsonObjectJ.getString("公积金"));
					String gsJ = Util.null2o(newJsonObjectJ.getString("个税"));
					String sfgzJ = Util.null2o(newJsonObjectJ.getString("实发工资"));
					
					if (depI.equals(depJ)) 
					{
						String new1 = df.format(getDoublenumber(yxzeI,yxzeJ));
						String new2 = df.format(getDoublenumber(dnbtI,dnbtJ));
						String new3 = df.format(getDoublenumber(cfbtI,cfbtJ));
						String new4 = df.format(getDoublenumber(ccbtI,ccbtJ));
						String new5 = df.format(getDoublenumber(khjjI,khjjJ));
						String new6 = df.format(getDoublenumber(jbfI,jbfJ));
						String new7 = df.format(getDoublenumber(qtI,qtJ));
						String new8 = df.format(getDoublenumber(sjI,sjJ));
						String new9 =  df.format(getDoublenumber(bjI,bjJ));
						String new10=df.format(getDoublenumber(qtzxfjkcI,qtzxfjkcJ));
						
						String new11 = df.format(getDoublenumber(yfgzI,yfgzJ));
						String new12 = df.format(getDoublenumber(sbI,sbJ));
						String new13 = df.format(getDoublenumber(gjjI,gjjJ));
						String new14 = df.format(getDoublenumber(gsI,gsJ));
						String new15 = df.format(getDoublenumber(sfgzI,sfgzJ));
						arrayTemp.remove(j);
						JSONObject newJSONobject = new JSONObject();
						newJSONobject.put("部门", depI);
						newJSONobject.put("分部", subI);
						newJSONobject.put("月薪总额", new1);
						newJSONobject.put("电脑补贴", new2);
						newJSONobject.put("餐费补贴", new3);
						newJSONobject.put("出差补贴", new4);
						newJSONobject.put("考核/奖金", new5);
						newJSONobject.put("加班费", new6);
						newJSONobject.put("其他", new7);
						newJSONobject.put("事假", new8);
						newJSONobject.put("病假", new9);
						newJSONobject.put("其他专项附加扣除", new10);
						newJSONobject.put("应发工资", new11);
						newJSONobject.put("社保", new12);
						newJSONobject.put("公积金", new13);
						newJSONobject.put("个税", new14);
						newJSONobject.put("实发工资", new15);
						
						arrayTemp.add(newJSONobject);
						break;
					}
					numJ++;
				}  
				if (numJ-1 == arrayTemp.size() - 1) 
				{
					arrayTemp.add(jsonsarray.get(i));
				}
			 }
			 num++;
		  }
		   return arrayTemp;
	   }
	public static JSONArray returnJSON2(JSONArray jsonsarray)
	   {
		  JSONArray arrayTemp = new JSONArray();
		  int num = 0;
		  for (int i = 0; i < jsonsarray.size(); i++) 
		  {
			 if (num == 0) 
			 {
			    arrayTemp.add(jsonsarray.get(i));	
			 } 
			 else
			 {
				 int numJ = 0;
				for (int j = 0; j < arrayTemp.size(); j++) 
				{
					JSONObject newJsonObjectI = (JSONObject)jsonsarray.get(i);
					JSONObject newJsonObjectJ = (JSONObject)arrayTemp.get(j);
					String depI = Util.null2o(newJsonObjectI.getString("部门"));
					String yxzeI = Util.null2o(newJsonObjectI.getString("月薪总额"));
					String jbgzI = Util.null2o(newJsonObjectI.getString("基本工资"));
					String gwgzI = Util.null2o(newJsonObjectI.getString("岗位工资"));
					String jxjjjsI = Util.null2o(newJsonObjectI.getString("绩效奖金基数"));
					String qqI = Util.null2o(newJsonObjectI.getString("全勤奖"));
					String cfbtI = Util.null2o(newJsonObjectI.getString("餐费补贴"));
					String qtbtI = Util.null2o(newJsonObjectI.getString("其他补贴"));
					String khjjI=Util.null2o(newJsonObjectI.getString("考核/奖金"));
					String jbfI=Util.null2o(newJsonObjectI.getString("加班费"));
					String qtbkI = Util.null2o(newJsonObjectI.getString("其他补款"));
					String qjI = Util.null2o(newJsonObjectI.getString("请假"));
					String cdI = Util.null2o(newJsonObjectI.getString("迟到"));
					String qtI = Util.null2o(newJsonObjectI.getString("其他"));
					String yfgzI = Util.null2o(newJsonObjectI.getString("应发工资"));
					String sbI = Util.null2o(newJsonObjectI.getString("社保"));
					String gjjI = Util.null2o(newJsonObjectI.getString("公积金"));
					String gsI = Util.null2o(newJsonObjectI.getString("个税"));
					String sfgzI = Util.null2o(newJsonObjectI.getString("实发工资"));
					String fwfI = Util.null2o(newJsonObjectI.getString("服务费"));
					String pcjI = Util.null2o(newJsonObjectI.getString("赔偿金"));
					
					String depJ = Util.null2o(newJsonObjectJ.getString("部门"));
					String yxzeJ = Util.null2o(newJsonObjectJ.getString("月薪总额"));
					String jbgzJ = Util.null2o(newJsonObjectJ.getString("基本工资"));
					String gwgzJ = Util.null2o(newJsonObjectJ.getString("岗位工资"));
					String jxjjjsJ = Util.null2o(newJsonObjectJ.getString("绩效奖金基数"));
					String qqJ = Util.null2o(newJsonObjectJ.getString("全勤奖"));
					String cfbtJ = Util.null2o(newJsonObjectJ.getString("餐费补贴"));
					String qtbtJ = Util.null2o(newJsonObjectJ.getString("其他补贴"));
					String khjjJ=Util.null2o(newJsonObjectJ.getString("考核/奖金"));
					String jbfJ=Util.null2o(newJsonObjectJ.getString("加班费"));
					String qtbkJ = Util.null2o(newJsonObjectJ.getString("其他补款"));
					String qjJ = Util.null2o(newJsonObjectJ.getString("请假"));
					String cdJ = Util.null2o(newJsonObjectJ.getString("迟到"));
					String qtJ = Util.null2o(newJsonObjectJ.getString("其他"));
					String yfgzJ = Util.null2o(newJsonObjectJ.getString("应发工资"));
					String sbJ = Util.null2o(newJsonObjectJ.getString("社保"));
					String gjjJ = Util.null2o(newJsonObjectJ.getString("公积金"));
					String gsJ = Util.null2o(newJsonObjectJ.getString("个税"));
					String sfgzJ = Util.null2o(newJsonObjectJ.getString("实发工资"));
					String fwfJ = Util.null2o(newJsonObjectJ.getString("服务费"));
					String pcjJ = Util.null2o(newJsonObjectJ.getString("赔偿金"));
					
					if (depI.equals(depJ)) 
					{
						String new1 =  df.format(getDoublenumber(yxzeI,yxzeJ));
						String new2 = df.format(getDoublenumber(jbgzI,jbgzJ));
						String new3 = df.format(getDoublenumber(gwgzI,gwgzJ));
						String new4 = df.format(getDoublenumber(jxjjjsI,jxjjjsJ));
						String new5 = df.format(getDoublenumber(qqI,qqJ));
						String new6 = df.format(getDoublenumber(cfbtI,cfbtJ));
						String new7 = df.format(getDoublenumber(qtbtI,qtbtJ));
						String new8 = df.format(getDoublenumber(khjjI,khjjJ));
						String new9 = df.format(getDoublenumber(jbfI,jbfJ)); 
						String new10 = df.format(getDoublenumber(qtbkI,qtbkJ));
						String new11 = df.format(getDoublenumber(qjI,qjJ));
						String new12 = df.format(getDoublenumber(cdI,cdJ));
						String new13 = df.format(getDoublenumber(qtI,qtJ));
						String new14 = df.format(getDoublenumber(yfgzI,yfgzJ));
						String new15 = df.format(getDoublenumber(sbI,sbJ));
						String new16 = df.format(getDoublenumber(gjjI,gjjJ));
						String new17 = df.format(getDoublenumber(gsI,gsJ));
						String new18 = df.format(getDoublenumber(sfgzI,sfgzJ));
						
						String new19=df.format(getDoublenumber(fwfI,fwfJ));
						String new20=df.format(getDoublenumber(pcjI,pcjJ));
						String new21 = df.format(Double.valueOf(new14)*0.02);
						arrayTemp.remove(j);
						JSONObject newJSONobject = new JSONObject();
						newJSONobject.put("部门", depI);
						newJSONobject.put("月薪总额", new1);
						newJSONobject.put("基本工资", new2);
						newJSONobject.put("岗位工资", new3);
						newJSONobject.put("绩效奖金基数", new4);
						newJSONobject.put("全勤奖", new5);
						newJSONobject.put("餐费补贴", new6);
						newJSONobject.put("其他补贴", new7);
						newJSONobject.put("考核/奖金", new8);
						newJSONobject.put("加班费", new9);
						newJSONobject.put("其他补款", new10);
						newJSONobject.put("请假", new11);
						newJSONobject.put("迟到", new12);
						newJSONobject.put("其他", new13);
						newJSONobject.put("应发工资", new14);
						newJSONobject.put("社保", new15);
						newJSONobject.put("公积金", new16);
						newJSONobject.put("个税", new17);
						newJSONobject.put("实发工资", new18);
						newJSONobject.put("服务费", new19);
						newJSONobject.put("赔偿金", new20);
						newJSONobject.put("单位缴纳工会经费", new21);
						arrayTemp.add(newJSONobject);
						break;
					}
					numJ++;
				}  
				if (numJ-1 == arrayTemp.size() - 1) 
				{
					arrayTemp.add(jsonsarray.get(i));
				}
			 }
			 num++;
		  }
		   return arrayTemp;
	   }
	public static Double getDoublenumber(String number1,String number2){
		   BigDecimal bigDecimal1 = new BigDecimal(Util.null2o(number1));
		   BigDecimal bigDecimal2 = new BigDecimal(Util.null2o(number2));
		   Double n1 = bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		   Double n2 = bigDecimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		   Double n12 = n1+n2;
		   return n12;
	}

}
