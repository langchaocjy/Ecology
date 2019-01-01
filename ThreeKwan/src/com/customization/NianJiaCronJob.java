package com.customization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.schedule.BaseCronJob;

public class NianJiaCronJob
  extends BaseCronJob
{
  private Logger logger = LoggerFactory.getLogger(QuartzTestJob.class);
  private String cronExpr;
  
  public void setCronExpr(String cronExpr)
  {
    this.cronExpr = cronExpr;
  }
  
  public String getCronExpr()
  {
    return this.cronExpr;
  }
  
  public void execute()
  {
      this.logger.info("=============================run " + getClass().getName() + " job...");
      JSONArray jsons = new JSONArray();
      JSONObject json = new JSONObject();
      RecordSet rs = new RecordSet();
      String sql1 = "select id,field2,field3,field4 from cus_fielddata";//2入职日期  3开始享有年假日期  4参加工作日
      rs.execute(sql1);
      while (rs.next()) 
      {
    if (!rs.getString("field4").equals("")) {
    	  String id = rs.getString("id");
		  String[] eald = rs.getString("field4").split("-");
		  String[] dateNowStr = AllUtil.returnYmd().split("-");
		  int returnYear = AllUtil.returnInteger(dateNowStr[0])-AllUtil.returnInteger(eald[0]);
		  int returnMonth = AllUtil.returnInteger(dateNowStr[1])-AllUtil.returnInteger(eald[1]);
		  int returnDay = AllUtil.returnInteger(dateNowStr[2])-AllUtil.returnInteger(eald[2]);
		  int annualVacation = 0;
          if (returnYear == 10) 
          {
			  if (returnMonth == 0) 
			  {
                  annualVacation = returnDay >= 0 ? 10:5; 
			  }
			  else if(returnMonth > 0)//10年-20年的区间
			  {
				  annualVacation = 10;
			  }
			  else
			  {
				  annualVacation = 5 ;
			  }
		  }
          else if(10 < returnYear && returnYear <= 20)
          {
        	  annualVacation = 10;
          }
          else if( returnYear > 20) //20年以上
          {
        	  annualVacation = 15;
          }
          else // 十年以内
          {
        	  annualVacation = 5;
          }
          json.put("id", id);
		  json.put("nianjia", annualVacation);
		  json.put("nianfen", dateNowStr[0]);
		  jsons.add(json);
    }
	  }
      if (jsons.size() > 0) 
      {
		  for (int i = 0; i < jsons.size(); i++) 
		  {
			  JSONObject data = jsons.getJSONObject(i);
			  String id = data.getString("id");
			  String nianjia = data.getString("nianjia");
			  String nianfen = data.getString("nianfen");
			  String sql2 = "update HrmAnnualManagement set annualyear= '"+nianfen+"',annualdays='"+nianjia+"' where resourceid ='"+id+"'";
			  rs.execute(sql2);
		  }
	  }
	  this.logger.info("=============================jieshu " + getClass().getName() + " job...");
  }
}
