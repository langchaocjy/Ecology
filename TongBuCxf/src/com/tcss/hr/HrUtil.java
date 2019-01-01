package com.tcss.hr;

import com.tcss.util.TcssUtil;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;

public class HrUtil
  extends TcssUtil
{
  public void execCbzx(JSONArray cbzx,JSONArray hrmjob2)
  {
    for (int i = 0; i < cbzx.size(); i++)
    {
      JSONObject json = cbzx.getJSONObject(i);
      if (selectCbzx(json)) {
        updateCbzx(json);
      } else {
        insertCbzx(json);
      }
    }
    
    for (int i = 0; i < hrmjob2.size(); i++) {
		JSONObject json=hrmjob2.getJSONObject(i);
		if (selectHrmjob2(json)) {
			updateHrmjob2(json);
		}else{
			insertHrmjob2(json);
		}
	}
  }
  
  public Document getSubCompany(JSONArray subCompanys)
  {
    write(subCompanys.size() + "分部完整数据：" + subCompanys.toString());
    Document doc = DocumentHelper.createDocument();
    Element rootElement = DocumentHelper.createElement("root");
    doc.setRootElement(rootElement);
    Element orglistElement = rootElement.addElement("orglist");
    for (int i = 0; i < subCompanys.size(); i++)
    {
      Element orgelement = orglistElement.addElement("org");
      orgelement.addAttribute("action", "add");
      Element codeelement = orgelement.addElement("code");
      codeelement.setText(subCompanys.getJSONObject(i).getString("分部编码"));
      Element shortnameelement = orgelement.addElement("shortname");
      shortnameelement.setText(subCompanys.getJSONObject(i).getString("分部简称"));
      Element fullnameelement = orgelement.addElement("fullname");
      fullnameelement.setText(subCompanys.getJSONObject(i).getString("分部名称"));
      Element parent_codeelement = orgelement.addElement("parent_code");
      parent_codeelement.setText("0");
      Element orderelement = orgelement.addElement("order");
      orderelement.setText(Integer.toString(i));
    }
    return doc;
  }
  
  public Document getDepartment(JSONArray departments)
  {
    Document doc = DocumentHelper.createDocument();
    Element rootElement = DocumentHelper.createElement("root");
    doc.setRootElement(rootElement);
    Element orglistElement = rootElement.addElement("orglist");
    for (int i = 0; i < departments.size(); i++)
    {
      Element orgelement = orglistElement.addElement("org");
      orgelement.addAttribute("action", "add");
      Element codeelement = orgelement.addElement("code");
      codeelement.setText(departments.getJSONObject(i).getString("部门编码"));
      Element shortnameelement = orgelement.addElement("shortname");
      shortnameelement.setText(departments.getJSONObject(i).getString("部门名称"));
      Element fullnameelement = orgelement.addElement("fullname");
      fullnameelement.setText(departments.getJSONObject(i).getString("部门简称"));
      Element org_codeelement = orgelement.addElement("org_code");
      org_codeelement.setText(departments.getJSONObject(i).getString("分部编码"));
      Element parent_codeelement = orgelement.addElement("parent_code");
      parent_codeelement.setText(departments.getJSONObject(i).getString("上级部门编码"));
      Element orderelement = orgelement.addElement("order");
      orderelement.setText(Integer.toString(i));
    }
    return doc;
  }
  
  public Document getHrmResource(JSONArray hrmResources)
  {
    Document doc = DocumentHelper.createDocument();
    Element rootElement = DocumentHelper.createElement("root");
    doc.setRootElement(rootElement);
    Element orglistElement = rootElement.addElement("hrmlist");
    for (int i = 0; i < hrmResources.size(); i++)
    {
      Element hrmelement = orglistElement.addElement("hrm");
      hrmelement.addAttribute("action", "add");
      Element workcodeelement = hrmelement.addElement("workcode");
      workcodeelement.setText(hrmResources.getJSONObject(i).getString("员工编号"));
      Element departmentelement = hrmelement.addElement("departmentcode");
      departmentelement.setText(hrmResources.getJSONObject(i).getString("部门编码"));
      Element lastnameelement = hrmelement.addElement("lastname");
      lastnameelement.setText(hrmResources.getJSONObject(i).getString("姓名"));
      Element loginidelement = hrmelement.addElement("loginid");
      loginidelement.setText(hrmResources.getJSONObject(i).getString("员工编号"));
      Element jobtitleelement = hrmelement.addElement("jobtitlecode");
      jobtitleelement.setText(hrmResources.getJSONObject(i).getString("岗位编码"));
      Element manageridelement = hrmelement.addElement("managercode");
      manageridelement.setText(hrmResources.getJSONObject(i).getString("直接上级编码"));
      Element statuselement = hrmelement.addElement("status");
      statuselement.setText(hrmResources.getJSONObject(i).getString("状态"));
      Element sexelement = hrmelement.addElement("sex");
      sexelement.setText(hrmResources.getJSONObject(i).getString("性别"));
      Element mobileelement = hrmelement.addElement("mobile");
      mobileelement.setText(hrmResources.getJSONObject(i).getString("手机号码"));
      Element telephoneelement = hrmelement.addElement("telephone");
      telephoneelement.setText(hrmResources.getJSONObject(i).getString("电话号码"));
      Element birthdayelement = hrmelement.addElement("birthday");
      birthdayelement.setText(hrmResources.getJSONObject(i).getString("生日"));
      Element emailelement = hrmelement.addElement("email");
      emailelement.setText(hrmResources.getJSONObject(i).getString("邮箱"));
      Element systemlanguageelement = hrmelement.addElement("systemlanguage");
      systemlanguageelement.setText("7");
    }
    return doc;
  }
  
  public String selectLocation(String locationname)
  {
    RecordSet rs = new RecordSet();
    String id = "";
    String sql = "select * from hrmlocations where locationname='" + locationname + "'";
    
    rs.execute(sql);
    if (rs.next()) {
      id = null2String(rs.getString("id"));
    }
    return id;
  }
  
  public String insertLocation(String locationname)
  {
    RecordSet rs = new RecordSet();
    String sql = "insert into hrmlocations(locationname,locationdesc,countryid)values('" + locationname + "','" + locationname + "','1')";
    
    rs.execute(sql);
    String id = selectLocation(locationname);
    return id;
  }
  
  public String getDepartmentid(RecordSet rs, String code)
  {
    String deptid = "";
    String sql = "select id from HrmDepartment where departmentcode ='" + code + "'";
    
    rs.execute(sql);
    if (rs.next()) {
      deptid = rs.getString("id");
    }
    return deptid;
  }
  
  public String getJobtitleid(RecordSet rs, String code)
  {
    String id = "";
    String sql = "select id from hrmjobtitles where jobtitlecode ='" + code + "'";
    
    rs.execute(sql);
    if (rs.next()) {
      id = rs.getString("id");
    }
    return id;
  }
  
  public boolean updateHrmresource(String workcode, String locationid, String jobtitleid, String status)
  {
	write(workcode+locationid+jobtitleid+status);
    RecordSet rs = new RecordSet();
    jobtitleid = getJobtitleid(rs, jobtitleid);
    String sql = "update hrmresource set locationid='" + locationid + "',jobtitle='" + jobtitleid + "',status='" + status + "' where workcode='" + workcode + "' ";
    write("更新人员信息sql:  " + sql);
    return rs.executeSql(sql);
  }
  
  public boolean updateField(String code, String field2,String field8,String field9,String field10,String field11,String field12,
		  String field13,String field14,String field15)//员工编号workcode，一级部门field2
  {
    RecordSet rs = new RecordSet();
    boolean b = false;
    if (!field2.equals("")&&!field8.equals("")&&!field9.equals("")&&!field10.equals("")&&!field11.equals(""))
    { 
      JSONObject user = getObjectById(code, "HrmResource", "workcode");//json.getString("");通过员工编号查找到对应的id
      JSONObject dept = getObjectById(field2, "hrmdepartment", "departmentcode");//用一级部门查出deptid
      if(!dept.isEmpty()&& !user.isEmpty()){
      String sql = "select * from  cus_fielddata where scopeid=-1 and id='" + user.getString("id") + "'";//根据userid找出对应的数据
      rs.execute(sql);
      if (rs.next()){
        sql = "update cus_fielddata set field2='" + dept.getString("id") + "',field8='"+field8+"',field9='"+field9+"',field10='"+field10+"',field11='"+field11+"',field12='"+field12+"',"
        		+ "field13='"+field13+"',field14='"+field14+"',field15='"+field15+"' where scopeid=-1 and id='" + user.getString("id") + "'";
        write("更新的自定义字段信息： "+sql);
        b = rs.execute(sql);
      }else{
        sql = "insert into cus_fielddata(scope,scopeid,id,field2,field8,field9,field10,field11,field12,field13,field14,field15,field16,field17,field18,field19)"
        		+ "values('HrmCustomFieldByInfoType','-1','" + user.getString("id") + "','" + dept.getString("id") + "','"+field8+"','"+field9+"','"+field10+"','"+field11+"','"+field12+"',"
        				+ "'"+field13+"','"+field14+"','"+field15+"')";
        b = rs.execute(sql);
      }
      }
    }
    return b;
  }
  
  public boolean syncJobTitle(JSONArray jobtitles)
  {
    RecordSet rs = new RecordSet();
    boolean booleantmp = false;
    try
    {
      for (int i = 0; i < jobtitles.size(); i++)
      {
        JSONObject jobtitle = jobtitles.getJSONObject(i);
        
        String jobtitlecode = jobtitle.getString("岗位编码");
        String jobtitlename = jobtitle.getString("岗位名称");
        String jobtitleremark = jobtitle.getString("岗位名称");
        String jobtitledept = jobtitle.getString("部门编码");
        int activityid = 21;
        
        String deptid = "0";
        String sql = "select id from hrmdepartment where departmentcode='" + jobtitledept + "'";
        rs.executeSql(sql);
        if (rs.next()) {
          deptid = rs.getString("id");
        }
        sql = "select id from hrmjobtitles where jobtitlecode='" + jobtitlecode + "'";
        rs.executeSql(sql);
        if (rs.next())
        {
          sql = 
            "update hrmjobtitles set jobtitlename='" + jobtitlename + "',jobtitlemark='" + jobtitleremark + "',jobdepartmentid='" + deptid + "' where jobtitlecode='" + jobtitlecode + "'";
          booleantmp = rs.executeSql(sql);
        }
        else
        {
          sql = 
            "insert into hrmjobtitles(jobtitlecode,jobtitlename,jobtitlemark,jobdepartmentid,jobactivityid) values ('" + jobtitlecode + "','" + jobtitlename + "','" + jobtitleremark + "','" + deptid + "','" + activityid + "')";
          booleantmp = rs.executeSql(sql);
        }
      }
    }
    catch (Exception e)
    {
      write("新增岗位失败," + e);
    }
    return booleantmp;
  }
  
  public boolean syncDepartment(JSONArray departments)
  {
    boolean booleantmp = false;
    RecordSet rs = new RecordSet();
    try
    {
      for (int i = 0; i < departments.size(); i++)
      {
        JSONObject department = departments.getJSONObject(i);
        String departmentname = department.getString("部门名称");
        String departmentmark = department.getString("部门简称");
        String code = department.getString("部门编码");
        String parent_code = department.getString("上级部门编码");
        String org_code = department.getString("分部编码");
        String cjsx = department.getString("层级属性");
        String gh = department.getString("工号");
        String xm = department.getString("姓名");
        String subcomid = "0";
        String supdeptid = "0";
        
        rs.executeSql("select id from hrmdepartment where departmentcode='"+code+"'");
        if(rs.next()){
        	String dId=rs.getString("id");
        	String sql="update HRMDEPARTMENTDEFINED set cjsx='"+cjsx+"',gh = '"+gh+"', xm = '"+xm+"' where deptid='"+dId+"'";
        	booleantmp=rs.executeSql(sql);
        }
        rs.executeSql("select id from HrmSubCompany where subcompanycode = '" + org_code + "'");
        if (rs.next()) {
          subcomid = rs.getString("id");
        }
        rs.executeSql("select id from hrmdepartment where departmentcode = '" + parent_code + "'");
        if (rs.next()) {
          supdeptid = rs.getString("id");
        }
        String order = Integer.toString(i);
        
        rs.executeSql("select id from hrmdepartment where departmentcode = '" + code + "'");
        if (rs.next())
        {
          String sql = "update hrmdepartment set departmentname='" + departmentname + "',departmentmark='" + departmentmark + "',subcompanyid1='" + subcomid + "',supdepid='" + supdeptid + 
            "',showorder='" + order + "',canceled='0' where departmentcode='" + code + "'";
          booleantmp = rs.executeSql(sql);
        }
        else
        {
          String sql = "insert into hrmdepartment(departmentname,departmentmark,subcompanyid1,supdepid,showorder,departmentcode) values ('" + departmentname + "','" + departmentmark + 
            "','" + subcomid + "','" + supdeptid + "','" + order + "','" + code + "')";
          booleantmp = rs.executeSql(sql);
        }
      }
    }
    catch (Exception e)
    {
      write("新增部门失败," + e);
    }
    return booleantmp;
  }
  
  public boolean selectCbzx(JSONObject json)
  {
    RecordSet rs = new RecordSet();
    String sql = "select * from formtable_main_30 where zzbm='" + json.getString("部门编码") + "' and cbzxbm='" + json.getString("成本中心编码") + "' ";
    
    rs.execute(sql);
    return rs.next();
  }
  
  public boolean updateCbzx(JSONObject json)
  {
    RecordSet rs = new RecordSet();
    String sql = "update formtable_main_30 set zzmc='" + json.getString("部门名称") + "' ,cbzxmc='" + json.getString("成本中心名称") + "' ,cbfzrmc='" + json.getString("成本负责人名称") + "' ,cbfzrbm='" + 
      json.getString("成本负责人编码") + "' ,frgsmc='" + json.getString("法人公司名称") + "' ,frgsbm='" + json.getString("法人公司编码") + "',zccbzx='" + json.getString("主次成本中心") + "',cjsx='"+json.getString("层级属性")+"',"
      		+ "bedat='"+json.getString("开始日期")+"',endat='"+json.getString("结束日期")+"'  where zzbm='" + 
      json.getString("部门编码") + "' and cbzxbm='" + json.getString("成本中心编码")+"'";
    
    return rs.execute(sql);
  }
  
  public boolean insertCbzx(JSONObject json)
  {
    RecordSet rs = new RecordSet();
    String sql = "insert into formtable_main_30(zzmc,zzbm,cbzxmc,cbzxbm,cbfzrmc,cbfzrbm,frgsmc,frgsbm,zccbzx,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype,cjsx,bedat,endat)values('" + 
      json.getString("部门名称") + 
      "','" + 
      json.getString("部门编码") + 
      "','" + 
      json.getString("成本中心名称") + 
      "','" + 
      json.getString("成本中心编码") + 
      "','" + 
      json.getString("成本负责人名称") + 
      "','" + 
      json.getString("成本负责人编码") + 
      "','" + 
      json.getString("法人公司名称") + 
      "','" + 
      json.getString("法人公司编码") + 
      "','" + 
      json.getString("主次成本中心") + 
      "','1','" + 
      getDateString("yyyy-MM-dd", new Date()) + 
      "','" + 
      getDateString("HH:mm:ss", new Date()) + 
      "','1','0','"+json.getString("层级属性")+"','"+json.getString("开始日期")+"','"+json.getString("结束日期")+"')  ";
    
    return rs.execute(sql);
  }
  
  public boolean selectHrmjob2(JSONObject json){
	  RecordSet rs = new RecordSet();
	  String sql = "select * from uf_bmgwxx where jobtitlecode='" + json.getString("岗位编码") + "' and departmentnamebm='" + json.getString("部门编码")+"' ";
	  rs.execute(sql);
	return rs.next();
  }
  
  public boolean updateHrmjob2(JSONObject json){
	  RecordSet rs = new RecordSet();
	    String sql = "update uf_bmgwxx set jobtitlemark='" + json.getString("岗位简称") + "' ,jobtitlename='" + json.getString("岗位名称") + "' ,"
	    		+ "kgbs='" + json.getString("空岗标识") + "' ,bukrs='" + json.getString("公司代码") + "' ,bk1='" + json.getString("预留字段1") + "' ,"
	    		+ "bk2='" + json.getString("预留字段2") + "' where jobtitlecode='" + json.getString("岗位编码") + "' and departmentnamebm='" + json.getString("部门编码")+"'";
	    return rs.execute(sql);
  }
  
  public boolean insertHrmjob2(JSONObject json)
  {
    RecordSet rs = new RecordSet();
    String sql = "insert into uf_bmgwxx(jobtitlemark,jobtitlename,jobtitlecode,departmentnamebm,kgbs,bukrs,bk1,bk2,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)values('" + 
      json.getString("岗位简称") + 
      "','" + 
      json.getString("岗位名称") + 
      "','" + 
      json.getString("岗位编码") + 
      "','" + 
      json.getString("部门编码") + 
      "','" + 
      json.getString("空岗标识") + 
      "','" + 
      json.getString("公司代码") + 
      "','" + 
      json.getString("预留字段1") + 
      "','" + 
      json.getString("预留字段2") + 
      "','1','" + 
      getDateString("yyyy-MM-dd", new Date()) + 
      "','" + 
      getDateString("HH:mm:ss", new Date()) + 
      "','921','0')  ";
    
    return rs.execute(sql);
  }
}
