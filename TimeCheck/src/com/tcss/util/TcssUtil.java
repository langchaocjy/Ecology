package com.tcss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.kq009.DT_OA_AFS_OA009;
import out.deal.afs.kq009.DT_OA_AFS_OA009_REP;
import out.deal.afs.kq009.MT_OA_AFS_OA009;
import out.deal.afs.kq009.MT_OA_AFS_OA009_REP;
import out.deal.afs.kq009.RESULT_type0;
import out.deal.afs.kq009.SIO_OA_AFS_OA009Service;
import out.deal.afs.kq009.SIO_OA_AFS_OA009ServiceStub;
import out.deal.afs.kq009.Sheet_type0;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class TcssUtil
  extends BaseBean
{
  public void write(String log)
  {
    StackTraceElement[] temp = Thread.currentThread().getStackTrace();
    StackTraceElement a = temp[2];
    
    writeLog("类:" + a.getClassName() + "方法:" + a.getMethodName() + "【" + log + "】", a.getClass());
  }
  
  public String null2String(String s)
  {
    return s == null ? "" : s;
  }
  
  public String getDateString(String type, Date data)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(type);
    String dateTime = sdf.format(data);
    return dateTime;
  }
  
  public Date getDate(String day, String time, String type)
  {
    SimpleDateFormat from = new SimpleDateFormat(type);
    Date d1 = null;
    try
    {
      d1 = from.parse(day + " " + time);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return d1;
  }
  
  public String getTableName(String requestid)
  {
    String tablename = "";
    if (!"".equals(requestid))
    {
      String sql = "select tablename from workflow_bill where id in(select formid from workflow_base where id in(select workflowid from workflow_requestbase where requestid=" + requestid + "))";
      RecordSet rs = new RecordSet();
      rs.executeSql(sql);
      if (rs.next()) {
        tablename = rs.getString("tablename");
      }
    }
    return tablename;
  }
  
  public String getMessage(String msg)
  {
    return msg + "<script language='javascript'>window.top.Dialog.alert('" + msg + "')</script>";
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
  
  public JSONArray getDetailData(String requestid, int index)
  {
    JSONArray jsons = new JSONArray();
    RecordSet rs = new RecordSet();
    String tableName = getTableName(requestid);
    String sql = "select * from " + tableName + "_dt" + index + " where mainid in( select id from " + tableName + " where  requestid='" + requestid + "') order by id ";
    rs.execute(sql);
    while (rs.next())
    {
      JSONObject json = new JSONObject();
      String[] columnName = rs.getColumnName();
      for (int i = 0; i < columnName.length; i++)
      {
        String name = columnName[i].toLowerCase();
        String value = rs.getString(name);
        json.put(name, value);
      }
      jsons.add(json);
    }
    return jsons;
  }
  
  public JSONObject getObjectById(String Td, String tablename, String fieldname)
  {
    JSONObject json = new JSONObject();
    RecordSet rs = new RecordSet();
    String sql = "select * from " + tablename + " where " + fieldname + "='" + Td + "'";
    write("sql:" + sql);
    rs.executeSql(sql);
    if (rs.next())
    {
      String[] columnName = rs.getColumnName();
      for (int i = 0; i < columnName.length; i++)
      {
        String name = columnName[i].toLowerCase();
        String value = null2String(rs.getString(name));
        json.put(name, value);
      }
    }
    return json;
  }
  
  public String getSapDate(String day)
  {
    return day.replaceAll("-", "");
  }
  
  public String getSapTime(String time)
  {
    return time.replaceAll(":", "") + "00";
  }
  
  public boolean checkDate(String begindate, String begintime, String enddate, String endtime)
  {
    Date bdate = getDate(begindate, begintime, "yyyy-MM-dd HH:mm");
    Date edate = getDate(enddate, endtime, "yyyy-MM-dd HH:mm");
    return edate.after(bdate);
  }
  
  public JSONArray getBetweenDates(String begindate, String enddate, String type)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(type);
    JSONArray result = new JSONArray();
    try
    {
      Date begin = sdf.parse(begindate);
      Date end = sdf.parse(enddate);
      Calendar tempStart = Calendar.getInstance();
      tempStart.setTime(begin);
      while (begin.getTime() <= end.getTime())
      {
        result.add(getDateString(type, tempStart.getTime()));
        tempStart.add(6, 1);
        begin = tempStart.getTime();
      }
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return result;
  }
  
  public String getSelectValue(String id, String name, String formid)
  {
    String value = "";
    if ((!"".equals(name)) && (!"".equals(id)) && (!"".equals(formid)))
    {
      String sql_fieldid = "select id from workflow_billfield where billid='" + formid + "' and upper(fieldname)='" + name.toUpperCase() + "'";
      String sql_value = "select selectname from workflow_SelectItem where fieldid in(" + sql_fieldid + ") and selectvalue='" + id + "'";
      
      RecordSet rs = new RecordSet();
      rs.executeSql(sql_value);
      if (rs.next()) {
        value = rs.getString("selectname");
      }
    }
    return value;
  }
  
  public boolean markSapBack(String requestid, String sign, String msg)
  {
    boolean b = false;
    RecordSet rs = new RecordSet();
    String tableName = getTableName(requestid);
    String sql = "update " + tableName + " set xxnr='" + msg + "' ,xxlx='" + sign + "' where requestid='" + requestid + "' ";
    write("记录集成状态：" + sql);
    b = rs.execute(sql);
    return b;
  }
  
  public String getFirstDay(String date, String type)
  {
    SimpleDateFormat df = new SimpleDateFormat(type);
    

    Date d = null;
    try
    {
      d = df.parse(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    Calendar cale = new GregorianCalendar();
    cale.setTime(d);
    cale.add(2, 0);
    cale.set(5, 1);
    String firstday = new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    
    return firstday;
  }
  
  public String getLastDay(String date, String type)
  {
    SimpleDateFormat df = new SimpleDateFormat(type);
    
    Date d = null;
    try
    {
      d = df.parse(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    Calendar cale = new GregorianCalendar();
    cale.setTime(d);
    cale.add(2, 1);
    cale.set(5, 0);
    String lastday = new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    return lastday;
  }
  
  public JSONObject getPbInfo(String userid, String date)
    throws Exception
  {
    JSONObject json = new JSONObject();
    
    SIO_OA_AFS_OA009Service stub = new SIO_OA_AFS_OA009ServiceStub();
    MT_OA_AFS_OA009 mT_OA_AFS_OA009 = new MT_OA_AFS_OA009();
    DT_OA_AFS_OA009 param = new DT_OA_AFS_OA009();
    JSONObject user = getObjectById(userid, "hrmresource", "id");
    Sheet_type0[] s = new Sheet_type0[1];
    for (int i = 0; i < s.length; i++)
    {
      s[i] = new Sheet_type0();
      s[i].setGh(user.getString("workcode"));
      s[i].setWdkrq(getSapDate(date));
    }
    param.setSheet(s);
    mT_OA_AFS_OA009.setMT_OA_AFS_OA009(param);
    MT_OA_AFS_OA009_REP sIO_OA_AFS_OA009 = stub.sIO_OA_AFS_OA009(mT_OA_AFS_OA009);
    DT_OA_AFS_OA009_REP mt_OA_AFS_OA009_REP = sIO_OA_AFS_OA009.getMT_OA_AFS_OA009_REP();
    RESULT_type0[] result = mt_OA_AFS_OA009_REP.getRESULT();
    if (result != null) {
      for (int i = 0; i < result.length; i++)
      {
        RESULT_type0 result_type0 = result[i];
        
        String sbsj = null2String(result_type0.getSBSJ());
        String wdkrq = null2String(result_type0.getWDKRQ());
        String xbsj = null2String(result_type0.getXBSJ());
        String ygh = null2String(result_type0.getYGH());
        String sfdk = null2String(result_type0.getSFDK());
        String pbsj = null2String(result_type0.getPBSJ());
        json.put("sbsj", sbsj);
        json.put("wdkrq", wdkrq);
        json.put("xbsj", xbsj);
        json.put("ygh", ygh);
        json.put("sfdk", sfdk);
        json.put("pbsj", pbsj);
      }
    }
    return json;
  }
  
  public int getMonthLastDay(int year, int month)
  {
    Calendar a = Calendar.getInstance();
    a.set(1, year);
    a.set(2, month - 1);
    a.set(5, 1);
    a.roll(5, -1);
    int maxDate = a.get(5);
    return maxDate;
  }
  
  public String getPrevMonth(String type)
  {
    String month = "";
    Calendar c = Calendar.getInstance();
    c.add(2, -1);
    month = getDateString(type, c.getTime());
    return month;
  }
}
