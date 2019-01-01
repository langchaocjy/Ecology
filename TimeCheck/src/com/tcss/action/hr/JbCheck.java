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
    write(requestid + "�Ӱ��鿪ʼ...........");
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
          msg = "�Ӱ������쳣���Ӱ�ʱ�䲻�Ϸ�����ʼʱ��С�ڽ���ʱ�䣡";
          break;
        }
//        if (!jbksrq.substring(0, 7).equals(mainData.getString("sqrq").substring(0, 7)))
//        {
//          b = false;
//          msg = "�Ӱ������쳣���Ӱ�ʱ�䲻�Ϸ����Ӱ�����Ҫ����������ͬ�£�";
//          break;
//        }
        String d_year = jbksrq.substring(0,4);//2018-09-29
        String d_month = jbksrq.substring(5,7);
        String e_year = mainData.getString("sqrq").substring(0,4);
        String e_month = mainData.getString("sqrq").substring(5,7);
        if(Integer.parseInt(d_year) < Integer.parseInt(e_year))
        {
        	b =false;
        	msg = "�Ӱ������쳣���Ӱ�ʱ�䲻�Ϸ����Ӱ����Ҫ���ڵ�������������ݣ�";
        	break;
        }
        else if(Integer.parseInt(d_year) == Integer.parseInt(e_year))
        {
        	if (Integer.parseInt(d_month) < Integer.parseInt(e_month)) {
        		b = false;
        		msg = "�Ӱ������쳣���Ӱ�ʱ�䲻�Ϸ����Ӱ��·�Ҫ���ڵ������������·ݣ�";
        		break;
			}
        }
        else 
        {
        	writeLog("�Ӱ�����ͨ�����");
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
          msg = "��;�����ļӰ�Сʱ��:" + jbxshByOa + ",���μӰ�ʱ��:" + gjjbxss + ".�Ѿ�������36Сʱ";
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
      write("�Ӱ������쳣��" + e.getMessage());
      request.getRequestManager().setMessageid(requestid);
      request.getRequestManager().setMessagecontent(getMessage("�Ӱ������쳣��" + e.getMessage()));
    }
    write(requestid + "�Ӱ������..........." + b);
    return "1";
  }
}
