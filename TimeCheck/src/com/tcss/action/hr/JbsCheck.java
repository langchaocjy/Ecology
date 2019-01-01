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
    write(requestid + "�Ӱ��鿪ʼ...........");
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
            msg = user.getString("lastname") + "��;�����ļӰ�Сʱ��:" + jbxshByOa + ",���μӰ�ʱ��:" + gjjbxss + ".�Ѿ�������36Сʱ";
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
        write("�Ӱ������쳣���Ӱ������쳣���Ӱ�ʱ�䲻�Ϸ�����ʼʱ��С�ڽ���ʱ�䣡");
        request.getRequestManager().setMessageid(requestid);
        request.getRequestManager().setMessagecontent(getMessage("�Ӱ������쳣���Ӱ������쳣���Ӱ�ʱ�䲻�Ϸ�����ʼʱ��С�ڽ���ʱ�䣡"));
      }
    }
    catch (Exception e)
    {
      write("�Ӱ������쳣��" + e.getMessage());
      request.getRequestManager().setMessageid(requestid);
      request.getRequestManager().setMessagecontent(getMessage("�Ӱ������쳣��" + e.getMessage()));
    }
    write(requestid + "�Ӱ������..........." + b);
    return "1";
  }
}
