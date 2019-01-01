package com.customization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.schedule.BaseCronJob;

public class QuartzTestJob
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
      RecordSet rs = new RecordSet();
	  String sql2 = "select * from uf_kqxg";
	  rs.execute(sql2);
	  while(rs.next()) 
	  {
		  String id = Util.null2String(rs.getString("id"));
		  String sqr = Util.null2String(rs.getString("sqr"));
		  String bm = Util.null2String(rs.getString("bm"));
		
		  this.logger.info("uf_kqxg的数据： "+id+" "+sqr+" "+bm);
		  AllUtil allutiil = new AllUtil();
		  for (int i = 1; i < 5; i++) 
		  {
			boolean flag=false;
			if (i==1) 
			{
				flag = allutiil.ScreenAndUpdate(id, sqr, bm,1, "ksrq", "kssj", "jsrq", "jssj", "xjlx");//查请假表
			}
			if (i==2) 
			{
				flag = allutiil.ScreenAndUpdate(id, sqr, bm,2, "ksrq", "kssj", "jsrq", "jssj", "");//查出差表
			}
			if (i==3) 
			{
				flag = allutiil.ScreenAndUpdate(id, sqr, bm,3, "ksrq", "wcsj", "jsrq", "fhsj", "");//查外出表
			}
			if (i==4) 
			{
				flag = allutiil.ScreenAndUpdate(id, sqr, bm,4, "qklx", "qkrq", "qkshijian", "", "");//查补签表
			}
			this.logger.info(i+"---"+flag);
		  }
	  }
	  
	  //连接外部考勤机，同步数据到uf_kqsj
	  
	  this.logger.info("=============================jieshu " + getClass().getName() + " job...");
  }
}
