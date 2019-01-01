package com.tcss.hr;

import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;

import out.deal.afs.kq001.DTOAAFSOA001;
import out.deal.afs.kq001.DTOAAFSOA001REP;
import out.deal.afs.kq001.DTOAAFSOA001REP.SHEET;
import out.deal.afs.kq001.DTOAAFSOA001REP.SHEET.FormtableMain30;
import out.deal.afs.kq001.DTOAAFSOA001REP.SHEET.Hrmdepartment;
import out.deal.afs.kq001.DTOAAFSOA001REP.SHEET.Hrmjobtitles;
import out.deal.afs.kq001.DTOAAFSOA001REP.SHEET.Hrmsubcompany;
import out.deal.afs.kq001.SIOOAAFSOA001;
import out.deal.afs.kq002.DTOAAFSOA002;
import out.deal.afs.kq002.DTOAAFSOA002REP;
import out.deal.afs.kq002.ObjectFactory;
import out.deal.afs.kq002.SIOOAAFSOA002;

public class SapHrDataCxf
  extends HrUtil
{
  public JSONArray subCompanys = new JSONArray();
  public JSONArray departments = new JSONArray();
  public JSONArray jobtitles = new JSONArray();
  public JSONArray hrmResources = new JSONArray();
  public JSONArray cbzx = new JSONArray();
  
  public JSONArray getSubCompanys()
  {
    return this.subCompanys;
  }
  
  public void setSubCompanys(JSONArray subCompanys)
  {
    this.subCompanys = subCompanys;
  }
  
  public JSONArray getDepartments()
  {
    return this.departments;
  }
  
  public void setDepartments(JSONArray departments)
  {
    this.departments = departments;
  }
  
  public JSONArray getJobtitles()
  {
    return this.jobtitles;
  }
  
  public void setJobtitles(JSONArray jobtitles)
  {
    this.jobtitles = jobtitles;
  }
  
  public JSONArray getHrmResources()
  {
    return this.hrmResources;
  }
  
  public void setHrmResources(JSONArray hrmResources)
  {
    this.hrmResources = hrmResources;
  }
  
  public JSONArray getCbzx()
  {
    return this.cbzx;
  }
  
  public void setCbzx(JSONArray cbzx)
  {
    this.cbzx = cbzx;
  }
  
  public SapHrDataCxf()
  {
    String date = getDateString("yyyyMMdd", new Date());
    write("date:" + date);
    JSONArray subCompanys = new JSONArray();
    JSONArray departments = new JSONArray();
    JSONArray jobtitles = new JSONArray();
    JSONArray cbzx = new JSONArray();
    
    String username = SapPiInfo.getUsername_Hr();
    String password = SapPiInfo.getPassword_Hr();
    try
    {
      String url = "http://" + SapPiInfo.getUrl_Hr() + 
        ":50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA001&interfaceNamespace=urn:bk2:afs:deal:out";
      
      JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
      factory.setAddress(url);
      factory.setUsername(username);
      factory.setPassword(password);
      factory.setServiceClass(SIOOAAFSOA001.class);
      SIOOAAFSOA001 service = (SIOOAAFSOA001)factory.create();
      out.deal.afs.kq001.ObjectFactory objectFactory = new out.deal.afs.kq001.ObjectFactory();
      DTOAAFSOA001 dTOAAFSOA001 = objectFactory.createDTOAAFSOA001();
      
      dTOAAFSOA001.setBEGDA(date);
      dTOAAFSOA001.setENDDA(date);
      
      DTOAAFSOA001REP sioOAAFSOA001 = service.sioOAAFSOA001(dTOAAFSOA001);
      DTOAAFSOA001REP.SHEET sheet = sioOAAFSOA001.getSHEET();
      
      List<DTOAAFSOA001REP.SHEET.Hrmsubcompany> hrmsubcompanys = sheet.getHrmsubcompany();
      write("SAP传递分部数量：" + hrmsubcompanys.size());
      int e = 0;
      for (int i = 0; i < hrmsubcompanys.size(); i++)
      {
        JSONObject json = new JSONObject();
        DTOAAFSOA001REP.SHEET.Hrmsubcompany hrmsubcompany = (DTOAAFSOA001REP.SHEET.Hrmsubcompany)hrmsubcompanys.get(i);
        String subcompanycode = null2String(hrmsubcompany.getSubcompanycode());
        String subcompanydesc = null2String(hrmsubcompany.getSubcompanydesc());
        String subcompanyname = null2String(hrmsubcompany.getSubcompanyname());
        if ((!subcompanycode.equals("")) && (!subcompanydesc.equals("")) && (!subcompanyname.equals("")))
        {
          json.put("分部名称", subcompanydesc);
          json.put("分部简称", subcompanyname);
          json.put("分部编码", subcompanycode);
          subCompanys.add(json);
        }
        else
        {
          write(++e + "分部残缺数据：subcompanycode:" + subcompanycode + "  " + "subcompanydesc:" + subcompanydesc + "  " + "subcompanyname:" + subcompanyname);
        }
      }
      List<DTOAAFSOA001REP.SHEET.Hrmdepartment> hrmdepartments = sheet.getHrmdepartment();
      write("SAP传递部门数量：" + hrmdepartments.size());
      for (int i = 0; i < hrmdepartments.size(); i++)
      {
        JSONObject json = new JSONObject();
        DTOAAFSOA001REP.SHEET.Hrmdepartment hrmdepartment = (DTOAAFSOA001REP.SHEET.Hrmdepartment)hrmdepartments.get(i);
        String departmentcode = null2String(hrmdepartment.getDepartmentcode());
        String departmentmark = null2String(hrmdepartment.getDepartmentmark());
        String departmentname = null2String(hrmdepartment.getDepartmentname());
        String departmentnamesjbm = null2String(hrmdepartment.getDepartmentnamesjbm()).equals("") ? "0" : null2String(hrmdepartment.getDepartmentnamesjbm());
        String subcompanydescbm = null2String(hrmdepartment.getSubcompanydescbm());
        String cjsx= null2String(hrmdepartment.getCjsx());
        String gh = null2String(hrmdepartment.getGh());
        String xm = null2String(hrmdepartment.getName());
        if ((!departmentcode.equals("")) && (!departmentmark.equals("")) && (!departmentname.equals("")) && (!subcompanydescbm.equals("")))
        {
          json.put("部门名称", departmentname);
          json.put("部门简称", departmentmark);
          json.put("部门编码", departmentcode);
          json.put("上级部门编码", departmentnamesjbm);
          json.put("分部编码", subcompanydescbm);
          json.put("层级属性", cjsx);
          json.put("工号", gh);
          json.put("姓名", xm);
          departments.add(json);
        }
        else
        {
          write(++e + "部门残缺数据：departmentcode:" + departmentcode + "  " + "departmentmark:" + departmentmark + "  " + "departmentname:" + departmentname + " " + "departmentnamesjbm:" + 
            departmentnamesjbm + " " + "subcompanydescbm:" + subcompanydescbm + "cjsx: " + cjsx);
        }
      }
      List<DTOAAFSOA001REP.SHEET.Hrmjobtitles> hrmjobtitles = sheet.getHrmjobtitles();
      write("SAP传递岗位数量：" + hrmjobtitles.size());
      for (int i = 0; i < hrmjobtitles.size(); i++)
      {
        JSONObject json = new JSONObject();
        DTOAAFSOA001REP.SHEET.Hrmjobtitles hrmjobtitles2 = (DTOAAFSOA001REP.SHEET.Hrmjobtitles)hrmjobtitles.get(i);
        String departmentnamebm = null2String(hrmjobtitles2.getDepartmentnamebm());
        String jobtitlecode = null2String(hrmjobtitles2.getJobtitlecode());
        String jobtitlemark = null2String(hrmjobtitles2.getJobtitlemark());
		String kgbs=null2String(hrmjobtitles2.getKgbs());
		String bukrs=null2String(hrmjobtitles2.getBukrs());
		String bk1=null2String(hrmjobtitles2.getBk1());
		String bk2=null2String(hrmjobtitles2.getBk2());
        if ((!jobtitlecode.equals("")) && (!jobtitlemark.equals("")))
        {
          json.put("岗位名称", jobtitlemark);
          json.put("岗位简称", jobtitlemark);
          json.put("岗位编码", jobtitlecode);
          json.put("部门编码", departmentnamebm);
			json.put("空岗标识", kgbs);
			json.put("公司代码",bukrs);
			json.put("预留字段1", bk1);
			json.put("预留字段2", bk2);
          jobtitles.add(json);
        }
        else
        {
          write(++e + "岗位残缺数据：departmentnamebm:" + departmentnamebm + "  " + "jobtitlecode:" + jobtitlecode + "  " + "jobtitlemark:" + jobtitlemark + " ");
        }
      }
      List<DTOAAFSOA001REP.SHEET.FormtableMain30> formtableMain30s = sheet.getFormtableMain30();
      write("SAP传递成本中心数量：" + formtableMain30s.size());
      for (int i = 0; i < formtableMain30s.size(); i++)
      {
        JSONObject json = new JSONObject();
        DTOAAFSOA001REP.SHEET.FormtableMain30 formtableMain30 = (DTOAAFSOA001REP.SHEET.FormtableMain30)formtableMain30s.get(i);
        String cbfzrbm = null2String(formtableMain30.getCbfzrbm());
        String cbfzrmc = null2String(formtableMain30.getCbfzrmc());
        String cbzxbm = null2String(formtableMain30.getCbzxbm());
        String cbzxmc = null2String(formtableMain30.getCbzxmc());
        String frgsbm = null2String(formtableMain30.getFrgsbm());
        String frgsmc = null2String(formtableMain30.getFrgsmc());
        String zccbzx = null2String(formtableMain30.getZccbzx());
        String zzbm = null2String(formtableMain30.getZzbm());
        String zzmc = null2String(formtableMain30.getZzmc());
        String cjsx= null2String(formtableMain30.getCjsx());
		String bedat=null2String(formtableMain30.getBedat());
		String endat=null2String(formtableMain30.getEndat());
        json.put("部门名称", zzmc);
        json.put("部门编码", zzbm);
        json.put("成本中心名称", cbzxmc);
        json.put("成本中心编码", cbzxbm);
        json.put("成本负责人名称", cbfzrmc);
        json.put("成本负责人编码", cbfzrbm);
        json.put("法人公司名称", frgsmc);
        json.put("法人公司编码", frgsbm);
        json.put("主次成本中心", zccbzx);
        json.put("层级属性", cjsx);
		json.put("开始日期", bedat);
		json.put("结束日期", endat);
        cbzx.add(json);
      }
    }
    catch (Exception e)
    {
      write("组织：" + e.getMessage());
    }
    setSubCompanys(subCompanys);
    setDepartments(departments);
    setJobtitles(jobtitles);
    setCbzx(cbzx);
    






    String begindate = date;
    String enddate = date;
    String frstdate_Hr = SapPiInfo.getFrstdate_Hr();
    write("frstdate_Hr: "+frstdate_Hr);
    if (!frstdate_Hr.equals("")) {
      begindate = frstdate_Hr;
    }
    write("begindate:" + begindate + " enddate:" + enddate);
    try
    {
      String url = "http://" + SapPiInfo.getUrl_Hr() + 
        ":50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA002&interfaceNamespace=urn:bk2:afs:deal:out";
      
      JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
      factory.setAddress(url);
      factory.setUsername(username);
      factory.setPassword(password);
      factory.setServiceClass(SIOOAAFSOA002.class);
      SIOOAAFSOA002 service = (SIOOAAFSOA002)factory.create();
      
      ObjectFactory objectFactory = new ObjectFactory();
      DTOAAFSOA002 dTOAAFSOA002 = objectFactory.createDTOAAFSOA002();
      dTOAAFSOA002.setBEGDA(date);
      dTOAAFSOA002.setENDDA(date);
      DTOAAFSOA002REP sioOAAFSOA002 = service.sioOAAFSOA002(dTOAAFSOA002);
      List<DTOAAFSOA002REP.SHEET> sheets = sioOAAFSOA002.getSHEET();
      int e = 0;
      for (int i = 0; i < sheets.size(); i++)
      {
        JSONObject json = new JSONObject();
        DTOAAFSOA002REP.SHEET user = (DTOAAFSOA002REP.SHEET)sheets.get(i);
        String birthday = null2String(user.getBIRTHDAY()).equals("") ? "" : getDateString("yyyy-MM-dd", getDate(null2String(user.getBIRTHDAY()), "00:00", "yyyyMMdd"));
        String departmentid = null2String(user.getDEPARTMENTID());
        String email = null2String(user.getEMAIL());
        String jobtitle = null2String(user.getJOBTITLE());
        String lastname = null2String(user.getLASTNAME());
        String locationid = null2String(user.getLOCATIONID());
        String managerid = null2String(user.getMANAGERID());
        String mobile = null2String(user.getMOBILE());
        String sex = null2String(user.getSEX()).equals("1") ? "0" : "1";
        String status = null2String(user.getSTATUS()).equals("3") ? "1" : "5";
        String subcompanyid1 = null2String(user.getSUBCOMPANYID1());
        String telephone = null2String(user.getTELEPHONE());
        String workcode = null2String(user.getWORKCODE());
        String field2 = null2String(user.getField2());
        
        String field8 = null2String(user.getWERKS());
        String field9 = null2String(user.getBTRTL());
        String field10 = null2String(user.getPERSG());
        String field11 = null2String(user.getPERSK());
        String field12 = null2String(user.getBEGDA());
        String location = selectLocation(locationid);
        
        String ansvh=null2String(user.getANSVH());//工时制
        String zterf=null2String(user.getZTERF());//考勤方式
        String schkz=null2String(user.getSCHKZ());//排班
        if (location.equals("")) {
          locationid = insertLocation(locationid);
        } else {
          locationid = location;
        }
        json.put("生日", birthday);
        json.put("部门编码", departmentid);
        json.put("邮箱", email);
        json.put("岗位编码", jobtitle);
        json.put("姓名", lastname);
        json.put("工作地点", locationid);
        json.put("直接上级编码", managerid);
        json.put("手机号码", mobile);
        json.put("性别", sex);
        json.put("状态", status);
        json.put("分部编码", subcompanyid1);
        json.put("电话号码", telephone);
        json.put("员工编号", workcode);
        json.put("一级部门", field2);
        json.put("人事范围", field8);
        json.put("人事子范围", field9);
        json.put("员工组", field10);
        json.put("员工子组", field11);
        json.put("入职日期", field12);
        
		json.put("工时制", ansvh);
		json.put("考勤方式", zterf);
		json.put("排班", schkz);
        if ((!departmentid.equals("")) && (!jobtitle.equals("")) && (!lastname.equals("")) && (!status.equals("")) && (!subcompanyid1.equals("")) && (!workcode.equals(""))) {
          this.hrmResources.add(json);
        } else if (status.equals("5")) {
          this.hrmResources.add(json);
        } else {
          write(++e + "人员残缺数据：lastname:" + lastname + " workcode:" + workcode + " subcompanyid1:" + subcompanyid1 + " departmentid:" + departmentid + " jobtitle:" + jobtitle + " email:" + 
            email + " locationid:" + locationid + " managerid:" + managerid + " sex:" + sex + " status:" + status + " mobile:" + mobile + " telephone:" + telephone + 
            " birthday:" + birthday);
        }
      }
    }
    catch (Exception e)
    {
      write("人员:" + e.getMessage());
    }
    setHrmResources(this.hrmResources);
  }

 public static void main(String[] args){}
}
