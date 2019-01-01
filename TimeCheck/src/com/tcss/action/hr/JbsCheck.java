package com.tcss.action.hr;

import com.tcss.dwr.HrDwr;
import com.tcss.util.TcssUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestManager;

public class JbsCheck
  extends TcssUtil
  implements Action
{
  public String execute(RequestInfo request)
  {
    HrDwr hr = new HrDwr();
    String requestid = request.getRequestid();
    write(requestid + "加班检查开始...........");
    boolean b = true;
    try
    {
      JSONObject mainData = getMainData(requestid);
      write("mainData:" + mainData.toString());
      JSONArray detailData = getDetailData(requestid, 1);
      write("detailData:" + detailData.toString());
      
      String jbksrq = mainData.getString("jbksrq");
      String jbkssj = mainData.getString("jbkssj");
      String jbjssj = mainData.getString("jbjssj");
      boolean checkDate = checkDate(jbksrq, jbkssj, jbksrq, jbjssj);
      String msg = "";
      if (checkDate)
      {
        for (int i = 0; i < detailData.size(); i++)
        {
          JSONObject json = detailData.getJSONObject(i);
          String xm = json.getString("xm");
          double yjbxss = hr.getYjbxss(xm, mainData.getString("jbksrq"));
          double jbxshByOa = hr.getJbxshByOa(xm, mainData.getString("jbksrq"));
          double gjjbxss = mainData.getDouble("gjjbxss");
          write("yjbxss:" + yjbxss + ",jbxshByOa:" + jbxshByOa + ",gjjbxss:" + gjjbxss);
          if (jbxshByOa + gjjbxss > 36.0D)
          {
            JSONObject user = getObjectById(xm, "hrmresource", "id");
            msg = user.getString("lastname") + "在途审批的加班小时数:" + jbxshByOa + ",本次加班时间:" + gjjbxss + ".已经超过了36小时";
            write(msg);
            b = false;
            break;
          }
        }
        if (!b)
        {
          request.getRequestManager().setMessageid(requestid);
          request.getRequestManager().setMessagecontent(getMessage(msg));
        }
      }
      else
      {
        write("加班数据异常：加班数据异常：加班时间不合法！开始时间小于结束时间！");
        request.getRequestManager().setMessageid(requestid);
        request.getRequestManager().setMessagecontent(getMessage("加班数据异常：加班数据异常：加班时间不合法！开始时间小于结束时间！"));
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
