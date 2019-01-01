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
      write("SAP���ݷֲ�������" + hrmsubcompanys.size());
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
          json.put("�ֲ�����", subcompanydesc);
          json.put("�ֲ����", subcompanyname);
          json.put("�ֲ�����", subcompanycode);
          subCompanys.add(json);
        }
        else
        {
          write(++e + "�ֲ���ȱ���ݣ�subcompanycode:" + subcompanycode + "  " + "subcompanydesc:" + subcompanydesc + "  " + "subcompanyname:" + subcompanyname);
        }
      }
      List<DTOAAFSOA001REP.SHEET.Hrmdepartment> hrmdepartments = sheet.getHrmdepartment();
      write("SAP���ݲ���������" + hrmdepartments.size());
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
          json.put("��������", departmentname);
          json.put("���ż��", departmentmark);
          json.put("���ű���", departmentcode);
          json.put("�ϼ����ű���", departmentnamesjbm);
          json.put("�ֲ�����", subcompanydescbm);
          json.put("�㼶����", cjsx);
          json.put("����", gh);
          json.put("����", xm);
          departments.add(json);
        }
        else
        {
          write(++e + "���Ų�ȱ���ݣ�departmentcode:" + departmentcode + "  " + "departmentmark:" + departmentmark + "  " + "departmentname:" + departmentname + " " + "departmentnamesjbm:" + 
            departmentnamesjbm + " " + "subcompanydescbm:" + subcompanydescbm + "cjsx: " + cjsx);
        }
      }
      List<DTOAAFSOA001REP.SHEET.Hrmjobtitles> hrmjobtitles = sheet.getHrmjobtitles();
      write("SAP���ݸ�λ������" + hrmjobtitles.size());
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
          json.put("��λ����", jobtitlemark);
          json.put("��λ���", jobtitlemark);
          json.put("��λ����", jobtitlecode);
          json.put("���ű���", departmentnamebm);
			json.put("�ոڱ�ʶ", kgbs);
			json.put("��˾����",bukrs);
			json.put("Ԥ���ֶ�1", bk1);
			json.put("Ԥ���ֶ�2", bk2);
          jobtitles.add(json);
        }
        else
        {
          write(++e + "��λ��ȱ���ݣ�departmentnamebm:" + departmentnamebm + "  " + "jobtitlecode:" + jobtitlecode + "  " + "jobtitlemark:" + jobtitlemark + " ");
        }
      }
      List<DTOAAFSOA001REP.SHEET.FormtableMain30> formtableMain30s = sheet.getFormtableMain30();
      write("SAP���ݳɱ�����������" + formtableMain30s.size());
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
        json.put("��������", zzmc);
        json.put("���ű���", zzbm);
        json.put("�ɱ���������", cbzxmc);
        json.put("�ɱ����ı���", cbzxbm);
        json.put("�ɱ�����������", cbfzrmc);
        json.put("�ɱ������˱���", cbfzrbm);
        json.put("���˹�˾����", frgsmc);
        json.put("���˹�˾����", frgsbm);
        json.put("���γɱ�����", zccbzx);
        json.put("�㼶����", cjsx);
		json.put("��ʼ����", bedat);
		json.put("��������", endat);
        cbzx.add(json);
      }
    }
    catch (Exception e)
    {
      write("��֯��" + e.getMessage());
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
        
        String ansvh=null2String(user.getANSVH());//��ʱ��
        String zterf=null2String(user.getZTERF());//���ڷ�ʽ
        String schkz=null2String(user.getSCHKZ());//�Ű�
        if (location.equals("")) {
          locationid = insertLocation(locationid);
        } else {
          locationid = location;
        }
        json.put("����", birthday);
        json.put("���ű���", departmentid);
        json.put("����", email);
        json.put("��λ����", jobtitle);
        json.put("����", lastname);
        json.put("�����ص�", locationid);
        json.put("ֱ���ϼ�����", managerid);
        json.put("�ֻ�����", mobile);
        json.put("�Ա�", sex);
        json.put("״̬", status);
        json.put("�ֲ�����", subcompanyid1);
        json.put("�绰����", telephone);
        json.put("Ա�����", workcode);
        json.put("һ������", field2);
        json.put("���·�Χ", field8);
        json.put("�����ӷ�Χ", field9);
        json.put("Ա����", field10);
        json.put("Ա������", field11);
        json.put("��ְ����", field12);
        
		json.put("��ʱ��", ansvh);
		json.put("���ڷ�ʽ", zterf);
		json.put("�Ű�", schkz);
        if ((!departmentid.equals("")) && (!jobtitle.equals("")) && (!lastname.equals("")) && (!status.equals("")) && (!subcompanyid1.equals("")) && (!workcode.equals(""))) {
          this.hrmResources.add(json);
        } else if (status.equals("5")) {
          this.hrmResources.add(json);
        } else {
          write(++e + "��Ա��ȱ���ݣ�lastname:" + lastname + " workcode:" + workcode + " subcompanyid1:" + subcompanyid1 + " departmentid:" + departmentid + " jobtitle:" + jobtitle + " email:" + 
            email + " locationid:" + locationid + " managerid:" + managerid + " sex:" + sex + " status:" + status + " mobile:" + mobile + " telephone:" + telephone + 
            " birthday:" + birthday);
        }
      }
    }
    catch (Exception e)
    {
      write("��Ա:" + e.getMessage());
    }
    setHrmResources(this.hrmResources);
  }

 public static void main(String[] args){}
}
