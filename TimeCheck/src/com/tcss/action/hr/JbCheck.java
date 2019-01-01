package com.tcss.action.hr;

import com.tcss.dwr.HrDwr;
import com.tcss.util.TcssUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestManager;

public class JbCheck
  extends TcssUtil
  implements Action
{
  public String execute(RequestInfo request)
  {
    HrDwr hr = new HrDwr();
    
    String requestid = request.getRequestid();
    write(requestid + "加班检查开始...........");
    boolean b = true;
    String msg = "";
    try
    {
      JSONObject mainData = getMainData(requestid);
      write("mainData:" + mainData.toString());
      JSONArray detailData = getDetailData(requestid, 1);
      write("detailData:" + detailData.toString());
      for (int i = 0; i < detailData.size(); i++)
      {
        JSONObject json = detailData.getJSONObject(i);
        String jbksrq = json.getString("jbksrq");
        String jbkssj = json.getString("jbkssj");
        String jbjssj = json.getString("jbjssj");
        b = checkDate(jbksrq, jbkssj, jbksrq, jbjssj);
        if (!b)
        {
          msg = "加班数据异常：加班时间不合法！开始时间小于结束时间！";
          break;
        }
//        if (!jbksrq.substring(0, 7).equals(mainData.getString("sqrq").substring(0, 7)))
//        {
//          b = false;
//          msg = "加班数据异常：加班时间不合法！加班日期要与申请日期同月！";
//          break;
//        }
        String d_year = jbksrq.substring(0,4);//2018-09-29
        String d_month = jbksrq.substring(5,7);
        String e_year = mainData.getString("sqrq").substring(0,4);
        String e_month = mainData.getString("sqrq").substring(5,7);
        if(Integer.parseInt(d_year) < Integer.parseInt(e_year))
        {
        	b =false;
        	msg = "加班数据异常：加班时间不合法！加班年份要大于等于申请日期年份！";
        	break;
        }
        else if(Integer.parseInt(d_year) == Integer.parseInt(e_year))
        {
        	if (Integer.parseInt(d_month) < Integer.parseInt(e_month)) {
        		b = false;
        		msg = "加班数据异常：加班时间不合法！加班月份要大于等于申请日期月份！";
        		break;
			}
        }
        else 
        {
        	writeLog("加班数据通过检查");
        }
      }
      if (b)
      {
        double yjbxss = hr.getYjbxss(mainData.getString("xm"), mainData.getString("sqrq"));
        //double jbxshByOa = hr.getJbxshByOa(mainData.getString("xm"), mainData.getString("sqrq"));
        double jbxshByOa = mainData.getDouble("ztspxss");
        double gjjbxss = mainData.getDouble("gjjbxss");
        write("yjbxss:" + yjbxss + ",jbxshByOa:" + jbxshByOa + ",gjjbxss:" + gjjbxss);
        if (jbxshByOa + gjjbxss > 36.0D)
        {
          msg = "在途审批的加班小时数:" + jbxshByOa + ",本次加班时间:" + gjjbxss + ".已经超过了36小时";
          write(msg);
          request.getRequestManager().setMessageid(requestid);
          request.getRequestManager().setMessagecontent(getMessage(msg));
        }
      }
      else
      {
        write(msg);
        request.getRequestManager().setMessageid(requestid);
        request.getRequestManager().setMessagecontent(getMessage(msg));
      }
    }
    catch (Exception e)
    {
      write("加班数据异常：" + e.getMessage());
      request.getRequestManager().setMessageid(requestid);
      request.getRequestManager().setMessagecontent(getMessage("加班数据异常：" + e.getMessage()));
    }
    write(requestid + "加班检查结束..........." + b);
    return "1";
  }
}
