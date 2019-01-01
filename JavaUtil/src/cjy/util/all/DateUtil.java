package weaver.general;

import java.util.Calendar;
import java.util.Date;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.hrm.resource.ResourceComInfo;
import weaver.systeminfo.SystemEnv;

public class DateUtil
{
  public Date getMonday()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    localCalendar.set(7, 2);
    return localCalendar.getTime();
  }
  
  public Date getSunday()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    if (7 == localCalendar.getFirstDayOfWeek()) {
      return localDate;
    }
    localCalendar.add(6, 7);
    localCalendar.set(7, 1);
    
    return localCalendar.getTime();
  }
  
  public Date getFirstDayOfMonth()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    localCalendar.set(5, 1);
    
    return localCalendar.getTime();
  }
  
  public Date getLastDayOfMonth()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    localCalendar.set(5, localCalendar.getActualMaximum(5));
    

    return localCalendar.getTime();
  }
  
  public Date getFirstDayOfQuarter()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    int i = localCalendar.get(2);
    if ((i >= 0) && (i <= 2)) {
      localCalendar.set(2, 0);
    }
    if ((i >= 3) && (i <= 5)) {
      localCalendar.set(2, 3);
    }
    if ((i >= 6) && (i <= 7)) {
      localCalendar.set(2, 6);
    }
    if ((i >= 9) && (i <= 11)) {
      localCalendar.set(2, 9);
    }
    localCalendar.set(5, localCalendar.getActualMinimum(5));
    

    return localCalendar.getTime();
  }
  
  public Date getLastDayOfQuarter()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    int i = localCalendar.get(2);
    if ((i >= 0) && (i <= 2)) {
      localCalendar.set(2, 2);
    }
    if ((i >= 3) && (i <= 5)) {
      localCalendar.set(2, 5);
    }
    if ((i >= 6) && (i <= 7)) {
      localCalendar.set(2, 7);
    }
    if ((i >= 9) && (i <= 11)) {
      localCalendar.set(2, 11);
    }
    localCalendar.set(5, localCalendar.getActualMaximum(5));
    

    return localCalendar.getTime();
  }
  
  public String getYearDateStart()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    int i = localCalendar.get(1);
    
    return i + "-01-01";
  }
  
  public String getYearDateEnd()
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    int i = localCalendar.get(1);
    return i + "-12-31";
  }
  
  public String getWFNodename(String paramString1, String paramString2)
  {
    RecordSet localRecordSet = new RecordSet();
    localRecordSet.executeSql("select * from workflow_nodebase where id=" + paramString1);
    String str = "";
    if (localRecordSet.next()) {
      str = Util.null2String(localRecordSet.getString("nodename"));
    }
    return str;
  }
  
  public String getWFOpener(String paramString1, String paramString2)
  {
    String str = "";
    RecordSet localRecordSet = new RecordSet();
    localRecordSet.executeSql("select * from hrmresource where id=" + paramString1);
    if (localRecordSet.next()) {
      str = Util.null2String(localRecordSet.getString("lastname"));
    }
    if (paramString1.equals("1")) {
      str = "系统管理员";
    }
    return str;
  }
  
  public String getWFPnumber(String paramString1, String paramString2)
  {
    return SystemEnv.getHtmlLabelName(15323, Util.getIntValue(paramString2, 7)) + paramString1 + SystemEnv.getHtmlLabelName(124829, Util.getIntValue(paramString2, 7));
  }
  
  public String getWFTitle(String paramString1, String paramString2, String paramString3)
  {
    RecordSet localRecordSet1 = new RecordSet();
    RecordSet localRecordSet2 = new RecordSet();
    
    Object localObject = "";
    try
    {
      ResourceComInfo localResourceComInfo = new ResourceComInfo();
      Calendar localCalendar = Calendar.getInstance();
      int i = localCalendar.get(1);
      int j = localCalendar.get(2) + 1;
      int k = localCalendar.get(5);
      String str1 = "";
      String str2 = "";
      if (j < 10) {
        str1 = "0" + j;
      } else {
        str1 = "" + j;
      }
      if (k < 10) {
        str2 = "0" + k;
      } else {
        str2 = "" + k;
      }
      String str3 = "";
      String str4 = "";
      String str5 = "";
      String str6 = "";
      localRecordSet1.executeSql("select workflowname,defaultName from workflow_base where id='" + paramString1 + "'");
      if (localRecordSet1.next())
      {
        str5 = Util.null2String("" + localRecordSet1.getString("workflowname"));
        str6 = Util.null2String("" + localRecordSet1.getString("defaultName"));
      }
      if (str6.equals("1"))
      {
        String str7 = str5 + "-" + paramString3 + "-" + i + "-" + str1 + "-" + str2;
        if (localRecordSet1.getDBType().equals("oracle")) {
          str3 = "select * from Workflow_SetTitle   where workflowid='" + paramString1 + "'  order by   to_number(xh)  asc";
        } else {
          str3 = "select  * from Workflow_SetTitle where workflowid='" + paramString1 + "'  order by  convert(int,xh) asc";
        }
        localRecordSet1.executeSql(str3);
        while (localRecordSet1.next())
        {
          str4 = Util.null2String(localRecordSet1.getString("txtUserUse"));
          String str8 = Util.null2String(localRecordSet1.getString("xh"));
          String str9 = Util.null2String(localRecordSet1.getString("fieldtype"));
          String str10 = Util.null2String(localRecordSet1.getString("fieldvalue"));
          String str11 = Util.null2String(localRecordSet1.getString("fieldlevle"));
          String str12 = Util.null2String(localRecordSet1.getString("fieldname"));
          String str13 = Util.null2String(localRecordSet1.getString("fieldzx"));
          String str14 = Util.null2String(localRecordSet1.getString("trrowid"));
          String str15 = Util.null2String(localRecordSet1.getString("showhtml"));
          if (str9.equals("main"))
          {
            if ((str12.toLowerCase().equals("txtusertitle")) && 
              (!str10.equals(""))) {
              localObject = (String)localObject + str5;
            }
            String str16;
            String str17;
            String str18;
            String str19;
            if (str12.toLowerCase().equals("subtype"))
            {
              str16 = "";
              if (str13.equals("1")) {
                str16 = str11;
              }
              if (str13.equals("2")) {
                str16 = "" + Util.null2String(localResourceComInfo.getSubCompanyID(paramString2));
              }
              if (!str10.equals(""))
              {
                str17 = "";
                str18 = "";
                str19 = "";
                localRecordSet2.executeSql("select subcompanyname,subcompanydesc,subcompanycode from HrmSubCompanyAllView where id='" + str16 + "'");
                if (localRecordSet2.next())
                {
                  str17 = Util.null2String(localRecordSet2.getString("subcompanyname"));
                  str18 = Util.null2String(localRecordSet2.getString("subcompanydesc"));
                  str19 = Util.null2String(localRecordSet2.getString("subcompanycode"));
                }
                if (str10.equals("0")) {
                  localObject = (String)localObject + str17;
                }
                if (str10.equals("1")) {
                  localObject = (String)localObject + str18;
                }
                if (str10.equals("2")) {
                  localObject = (String)localObject + str19;
                }
              }
            }
            if (str12.toLowerCase().equals("depmarttype"))
            {
              str16 = "";
              if (str13.equals("1")) {
                str16 = str11;
              }
              if (str13.equals("2")) {
                str16 = "" + Util.null2String(localResourceComInfo.getDepartmentID(paramString2));
              }
              if (!str10.equals(""))
              {
                str17 = "";
                str18 = "";
                str19 = "";
                localRecordSet2.executeSql("select departmentcode,departmentname,departmentmark from HrmDepartmentAllView where id='" + str16 + "'");
                if (localRecordSet2.next())
                {
                  str17 = Util.null2String(localRecordSet2.getString("departmentname"));
                  str18 = Util.null2String(localRecordSet2.getString("departmentmark"));
                  str19 = Util.null2String(localRecordSet2.getString("departmentcode"));
                }
                if (str10.equals("0")) {
                  localObject = (String)localObject + str17;
                }
                if (str10.equals("1")) {
                  localObject = (String)localObject + str18;
                }
                if (str10.equals("2")) {
                  localObject = (String)localObject + str19;
                }
              }
            }
            if (str12.toLowerCase().equals("resourcetype"))
            {
              str16 = "";
              if (str13.equals("1")) {
                str16 = str11;
              }
              if (str13.equals("2")) {
                str16 = "" + paramString2;
              }
              if (!str10.equals(""))
              {
                str17 = "";
                str18 = "";
                str19 = "";
                localRecordSet2.executeSql("select lastname,workcode from hrmresource where id='" + str16 + "'");
                if (localRecordSet2.next())
                {
                  str17 = Util.null2String(localRecordSet2.getString("lastname"));
                  str18 = Util.null2String(localRecordSet2.getString("workcode"));
                }
                if (str16.equals("1"))
                {
                  localObject = (String)localObject + "系统管理员";
                }
                else
                {
                  if (str10.equals("0")) {
                    localObject = (String)localObject + str17;
                  }
                  if (str10.equals("1")) {
                    localObject = (String)localObject + str18;
                  }
                }
              }
            }
            if ((str12.toLowerCase().equals("txtuseryear")) && 
              (!str10.equals(""))) {
              localObject = (String)localObject + i;
            }
            if ((str12.toLowerCase().equals("txtusermouth")) && 
              (!str10.equals(""))) {
              localObject = (String)localObject + str1;
            }
            if ((str12.toLowerCase().equals("txtuserdate")) && 
              (!str10.equals(""))) {
              localObject = (String)localObject + str2;
            }
          }
          else
          {
            localObject = (String)localObject + str10;
          }
        }
        if (str4.equals("1")) {
          localObject = localObject;
        } else {
          localObject = str7;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    BaseBean localBaseBean = new BaseBean();
    return localObject;
  }
  
  public String getWFTitleNew(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    RecordSet localRecordSet1 = new RecordSet();
    RecordSet localRecordSet2 = new RecordSet();
    
    Object localObject = "";
    try
    {
      ResourceComInfo localResourceComInfo = new ResourceComInfo();
      Calendar localCalendar = Calendar.getInstance();
      int i = localCalendar.get(1);
      int j = localCalendar.get(2) + 1;
      int k = localCalendar.get(5);
      String str1 = "";
      String str2 = "";
      if (j < 10) {
        str1 = "0" + j;
      } else {
        str1 = "" + j;
      }
      if (k < 10) {
        str2 = "0" + k;
      } else {
        str2 = "" + k;
      }
      String str3 = "";
      String str4 = "";
      String str5 = "";
      String str6 = "";
      localRecordSet1.executeSql("select workflowname,defaultName from workflow_base where id='" + paramString1 + "'");
      if (localRecordSet1.next())
      {
        str5 = Util.null2String("" + localRecordSet1.getString("workflowname"));
        str6 = Util.null2String("" + localRecordSet1.getString("defaultName"));
      }
      if (str6.equals("1"))
      {
        String str7 = str5 + "-" + paramString3 + "-" + i + "-" + str1 + "-" + str2;
        if (paramString4.equals("1"))
        {
          if (localRecordSet1.getDBType().equals("oracle")) {
            str3 = "select * from Workflow_SetTitle   where workflowid='" + paramString1 + "'  order by   to_number(xh)  asc";
          } else {
            str3 = "select  * from Workflow_SetTitle where workflowid='" + paramString1 + "'  order by  convert(int,xh) asc";
          }
          localRecordSet1.executeSql(str3);
          while (localRecordSet1.next())
          {
            str4 = Util.null2String(localRecordSet1.getString("txtUserUse"));
            String str8 = Util.null2String(localRecordSet1.getString("xh"));
            String str9 = Util.null2String(localRecordSet1.getString("fieldtype"));
            String str10 = Util.null2String(localRecordSet1.getString("fieldvalue"));
            String str11 = Util.null2String(localRecordSet1.getString("fieldlevle"));
            String str12 = Util.null2String(localRecordSet1.getString("fieldname"));
            String str13 = Util.null2String(localRecordSet1.getString("fieldzx"));
            String str14 = Util.null2String(localRecordSet1.getString("trrowid"));
            String str15 = Util.null2String(localRecordSet1.getString("showhtml"));
            if (str9.equals("main"))
            {
              if ((str12.toLowerCase().equals("txtusertitle")) && 
                (!str10.equals(""))) {
                localObject = (String)localObject + str5;
              }
              String str16;
              String str17;
              String str18;
              String str19;
              if (str12.toLowerCase().equals("subtype"))
              {
                str16 = "";
                if (str13.equals("1")) {
                  str16 = str11;
                }
                if (str13.equals("2")) {
                  str16 = "" + Util.null2String(localResourceComInfo.getSubCompanyID(paramString2));
                }
                if (!str10.equals(""))
                {
                  str17 = "";
                  str18 = "";
                  str19 = "";
                  localRecordSet2.executeSql("select subcompanyname,subcompanydesc,subcompanycode from HrmSubCompanyAllView where id='" + str16 + "'");
                  if (localRecordSet2.next())
                  {
                    str17 = Util.null2String(localRecordSet2.getString("subcompanyname"));
                    str18 = Util.null2String(localRecordSet2.getString("subcompanydesc"));
                    str19 = Util.null2String(localRecordSet2.getString("subcompanycode"));
                  }
                  if (str10.equals("0")) {
                    localObject = (String)localObject + str17;
                  }
                  if (str10.equals("1")) {
                    localObject = (String)localObject + str18;
                  }
                  if (str10.equals("2")) {
                    localObject = (String)localObject + str19;
                  }
                }
              }
              if (str12.toLowerCase().equals("depmarttype"))
              {
                str16 = "";
                if (str13.equals("1")) {
                  str16 = str11;
                }
                if (str13.equals("2")) {
                  str16 = "" + Util.null2String(localResourceComInfo.getDepartmentID(paramString2));
                }
                if (!str10.equals(""))
                {
                  str17 = "";
                  str18 = "";
                  str19 = "";
                  localRecordSet2.executeSql("select departmentcode,departmentname,departmentmark from HrmDepartmentAllView where id='" + str16 + "'");
                  if (localRecordSet2.next())
                  {
                    str17 = Util.null2String(localRecordSet2.getString("departmentname"));
                    str18 = Util.null2String(localRecordSet2.getString("departmentmark"));
                    str19 = Util.null2String(localRecordSet2.getString("departmentcode"));
                  }
                  if (str10.equals("0")) {
                    localObject = (String)localObject + str17;
                  }
                  if (str10.equals("1")) {
                    localObject = (String)localObject + str18;
                  }
                  if (str10.equals("2")) {
                    localObject = (String)localObject + str19;
                  }
                }
              }
              if (str12.toLowerCase().equals("resourcetype"))
              {
                str16 = "";
                if (str13.equals("1")) {
                  str16 = str11;
                }
                if (str13.equals("2")) {
                  str16 = "" + paramString2;
                }
                if (!str10.equals(""))
                {
                  str17 = "";
                  str18 = "";
                  str19 = "";
                  localRecordSet2.executeSql("select lastname,workcode from hrmresource where id='" + str16 + "'");
                  if (localRecordSet2.next())
                  {
                    str17 = Util.null2String(localRecordSet2.getString("lastname"));
                    str18 = Util.null2String(localRecordSet2.getString("workcode"));
                  }
                  if (paramString4.equals("2")) {
                    str17 = paramString3;
                  }
                  if (str16.equals("1"))
                  {
                    localObject = (String)localObject + "系统管理员";
                  }
                  else
                  {
                    if (str10.equals("0")) {
                      localObject = (String)localObject + str17;
                    }
                    if (str10.equals("1")) {
                      localObject = (String)localObject + str18;
                    }
                  }
                }
              }
              if ((str12.toLowerCase().equals("txtuseryear")) && 
                (!str10.equals(""))) {
                localObject = (String)localObject + i;
              }
              if ((str12.toLowerCase().equals("txtusermouth")) && 
                (!str10.equals(""))) {
                localObject = (String)localObject + str1;
              }
              if ((str12.toLowerCase().equals("txtuserdate")) && 
                (!str10.equals(""))) {
                localObject = (String)localObject + str2;
              }
            }
            else
            {
              localObject = (String)localObject + str10;
            }
          }
          if (str4.equals("1")) {
            localObject = localObject;
          } else {
            localObject = str7;
          }
        }
        else
        {
          localObject = str7;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    BaseBean localBaseBean = new BaseBean();
    return localObject;
  }
  
  public void insertWFTileSet(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
  {
    RecordSet localRecordSet = new RecordSet();
    ConnStatement localConnStatement = null;
    try
    {
      String str = "";
      localConnStatement = new ConnStatement();
      if (localConnStatement.getDBType().equals("oracle")) {
        str = "insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse,showhtml)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
      } else {
        str = "insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse,showhtml)values(?,?,?,?,?,?,?,?,?,?)";
      }
      localConnStatement.setStatementSql(str);
      localConnStatement.setString(1, paramString1);
      localConnStatement.setString(2, paramString2);
      localConnStatement.setString(3, paramString3);
      localConnStatement.setString(4, paramString4);
      localConnStatement.setString(5, paramString5);
      localConnStatement.setString(6, paramString6);
      localConnStatement.setString(7, paramString7);
      localConnStatement.setString(8, paramString8);
      localConnStatement.setString(9, paramString9);
      localConnStatement.setString(10, paramString10);
      
      localConnStatement.executeUpdate();
    }
    catch (Exception localException)
    {
      localException.getMessage();
    }
    finally
    {
      localConnStatement.close();
    }
  }
  
  public boolean isCurrendUserid(String paramString1, String paramString2)
  {
    RecordSet localRecordSet = new RecordSet();
    localRecordSet.executeSql(" select 1 from workflow_currentoperator where requestid='" + paramString1 + "' and userid='" + paramString2 + "' and isremark in(0,1,8,9) ");
    if (localRecordSet.next()) {
      return true;
    }
    return false;
  }
  
  public void InitializationWFTitle(String paramString)
  {
    RecordSet localRecordSet = new RecordSet();
    

    localRecordSet.executeSql("select 1 from Workflow_SetTitle where workflowid='" + paramString + "'");
    if (!localRecordSet.next()) {
      if (localRecordSet.getDBType().equals("oracle"))
      {
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'10','sub','-','','txtname','','" + paramString + "','44','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'6','sub','-','','txtname','','" + paramString + "','22','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'5','main','0','','resourcetype','2','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'1','main','1','','txtusertitle','','" + paramString + "','44','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'2','sub','-','','txtname','','" + paramString + "','11','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'3','main','0','','subtype','0','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'4','main','1','','depmarttype','0','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'7','main','1','','txtuseryear','','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'8','sub','-','','txtname','','" + paramString + "','33','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'9','main','1','','txtusermouth','','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'11','main','1','','txtuserdate','','" + paramString + "','','1')");
      }
      else
      {
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('10','sub','-','','txtname','','" + paramString + "','44','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('6','sub','-','','txtname','','" + paramString + "','22','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('5','main','0','','resourcetype','2','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('1','main','1','','txtusertitle','','" + paramString + "','44','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('2','sub','-','','txtname','','" + paramString + "','11','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('3','main','0','','subtype','0','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('4','main','1','','depmarttype','0','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('7','main','1','','txtuseryear','','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('8','sub','-','','txtname','','" + paramString + "','33','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('9','main','1','','txtusermouth','','" + paramString + "','','1')");
        localRecordSet.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('11','main','1','','txtuserdate','','" + paramString + "','','1')");
      }
    }
  }
  
  public String InitializationWFTitleAll()
  {
    RecordSet localRecordSet1 = new RecordSet();
    RecordSet localRecordSet2 = new RecordSet();
    localRecordSet1.executeSql("select distinct id from workflow_base where defaultName=1 and id not in(select workflowid from Workflow_SetTitle) order by id asc");
    while (localRecordSet1.next())
    {
      String str = localRecordSet1.getString("id");
      if (localRecordSet1.getDBType().equals("oracle"))
      {
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'10','sub','-','','txtname','','" + str + "','44','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'6','sub','-','','txtname','','" + str + "','22','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'5','main','0','','resourcetype','2','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'1','main','1','','txtusertitle','','" + str + "','44','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'2','sub','-','','txtname','','" + str + "','11','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'3','main','0','','subtype','0','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'4','main','1','','depmarttype','0','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'7','main','1','','txtuseryear','','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'8','sub','-','','txtname','','" + str + "','33','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'9','main','1','','txtusermouth','','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(id,xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values(WORKFLOW_SETTITLE_SQE.NEXTVAL,'11','main','1','','txtuserdate','','" + str + "','','1')");
      }
      else
      {
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('10','sub','-','','txtname','','" + str + "','44','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('6','sub','-','','txtname','','" + str + "','22','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('5','main','0','','resourcetype','2','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('1','main','1','','txtusertitle','','" + str + "','44','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('2','sub','-','','txtname','','" + str + "','11','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('3','main','0','','subtype','0','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('4','main','1','','depmarttype','0','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('7','main','1','','txtuseryear','','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('8','sub','-','','txtname','','" + str + "','33','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('9','main','1','','txtusermouth','','" + str + "','','1')");
        localRecordSet2.executeSql("insert into Workflow_SetTitle(xh,fieldtype,fieldvalue,fieldlevle,fieldname,fieldzx,workflowid,trrowid,txtUserUse)values('11','main','1','','txtuserdate','','" + str + "','','1')");
      }
    }
    return "" + localRecordSet1.getCounts();
  }
}
