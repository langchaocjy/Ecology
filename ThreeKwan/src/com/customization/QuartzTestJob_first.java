package com.customization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.schedule.BaseCronJob;

public class QuartzTestJob_first
  extends BaseCronJob
{
  private Logger logger = LoggerFactory.getLogger(QuartzTestJob_first.class);
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
      RecordSet rs = new RecordSet(),rs2 = new RecordSet();
      JSONArray root_jsons = new JSONArray();
      JSONObject root_json = new JSONObject();
      String a = "���Ű���Ա",b = "�Ű���Ա";
      //-----����һ��ʱ������°�ʱ��-----
	  String sbsj=AllUtil.getHrmschedule("monstarttime1");
	  String xbsj=AllUtil.getHrmschedule("monendtime2");
	  String zoumos=AllUtil.getHrmschedule("satstarttime1");//���������翪ʼʱ��
	  String zoumox=AllUtil.getHrmschedule("sunstarttime1");//���������翪ʼʱ��
	  String sxbsj = "";
	  //-----����һ��ʱ������°�ʱ��-----
	  //=====�����洢���ڻ����ݵ�ϵͳ��=====
      rs.execute("select * from HRMSCHEDULESIGNIMP");
      while(rs.next())
      {
    	  String signdate = rs.getString("signdate");//����
    	  String userid = rs.getString("userid");//��Աid
    	  String departmentid=AllUtil.sreachDepratment(userid);
    	  String signtime12="", minTime=null,maxTime=null;
    	  
    	  boolean weekOrNo = AllUtil.getWeek(signdate);//�ж��Ƿ�����ĩ
    	  String importsql = Util.null2String(rs.getString("importsql"));
    	  
          boolean isHave = AllUtil.getTf(root_jsons, userid, signdate);
          if (!isHave) {//������ظ�
      		          boolean sftj = false;
                	  if (!importsql.equals("")) 
                	  {
                		  if (a.equals(importsql.substring(0, 5)) && !departmentid.equals("18"))//���Ű���Ա��һ�㹤��ʱ��������ʱ����� 
                		  {
                			  //----��ʼɸѡȡ�����ʱ��������ʱ��----
                			  if (!weekOrNo) {
                				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
                				  sxbsj="D��["+sbsj+" "+xbsj+"]";
                			  } else {
                				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
                				  sxbsj=zoumos+" "+zoumox;
                			  }
                			  //----����ɸѡȡ�����ʱ��������ʱ��----
                		  }
                		  else if (b.equals(importsql.substring(0, 4)) && departmentid.equals("18"))//����ǿͷ�����Ա����importsql���Ű���Ա��˵���������ϰ�
                		  {
                			  /*
                			   * 2018-10-10 14:00:00-----2018-10-11 02:59:00
                			   * 2018-10-10 22:00:00-----2018-10-11 11:30:00
                			   * 2018-10-10 12:00:00-----2018-10-11 01:00:00
                			   * */
                			  String[] arr1 = importsql.split(";");//�Ű���Ա;�ϰ࿪ʼʱ��:2018-10-10 14:00:00;�°����ʱ��:2018-10-11 02:59:00
                			  String[] arr2 = arr1[1].split(" ");
                			  String[] arr3 = arr1[2].split(" ");
                			  String symd = arr2[0].substring(7, 17);//2018-10-10
                			  String xymd = arr3[0].substring(7, 17);//2018-10-11
                			  String ssfm = arr2[1].substring(0, 5);//14:00
                			  String xsfm = arr3[1].substring(0, 5);//02:59
                			  String sql6="";
                			  if (ssfm.equals("06:00") || ssfm=="06:00") {
                				  sxbsj="A��[08:00 16:00]";
                			  }else if(ssfm.equals("14:00") || ssfm=="14:00"){
                				  sxbsj="B��[16:00 23:59]";
                			  }else if(ssfm.equals("22:00") || ssfm=="22:00"){
                				  sxbsj="C��[00:00 08:30]";
                			  }else if(ssfm.equals("07:30") || ssfm=="07:30"){
                				  sxbsj="D��[09:30 18:00]";
                			  }else{
                				  sxbsj="E��[14:00 22:00]";
                			  }
                			  /*
                			   * �Ű���Ա�Ľ�Ϊ���⣬���ﲻ��signtype=1��2���ж����°��ʱ�䣬дһ�������࣬ͨ����ȡ��importsql����Ч��ʱ��
                			   * �������Χ��ȡ��С������ʱ�������һ��
                			   * */
                			  if (symd.equals(xymd))//�����ȣ�ȡ��Чʱ���ڵ�ʱ�䣬���ܿ粻���죬�����ϰ�ʱ�伴��
                			  {
                				  sql6="select signtime from HRMSCHEDULESIGNIMP where signdate='"+symd+"' and userid='"+userid+"'";
                				  rs2.execute(sql6);
                				  while (rs2.next()) 
                				  {
                					  String s2 = rs2.getString("signtime").substring(0,5);
//                					  String flag1 = AllUtil.getZzZw(ssfm, s2, "max");//��������ص�flag������ssfm��˵�����s2�ڷ�Χ�ڣ�����Ҫ��
//                					  String flag2 = AllUtil.getZzZw(xsfm, s2, "min");
//                					  if (flag1.equals(s2) && flag2.equals(s2)) {
//                						  String flag3 = AllUtil.getZzZw(minTime,s2, "min");
//                						  String flag4 = AllUtil.getZzZw(maxTime,s2, "max");
//                						  minTime=flag3;
//                						  maxTime=flag4;
//                					  } else {
//                						  this.logger.info("[ɸѡ���������С��ʱ��ʱ----�������ϴ򿨷�Χ��ʱ��:]"+s2+" ��Ӧ�ô��ڣ�"+ssfm+" С�ڣ�"+xsfm);
//                					  }
                					  
                					  String flag1 = AllUtil.getZzZw(ssfm, s2, "max");
                					  if (flag1.equals(s2)) {
										 String flag3 = AllUtil.getZzZw(minTime,s2, "min");
										 minTime=flag3;
									  }
                					  
                					  String flag4=AllUtil.getZzZw(maxTime, s2, "max");
                					  maxTime=flag4;
                				  }
                			  }
                			  else //�������ȣ�˵��ʱ������ˣ���ȥ�ڶ����������°�ʱ�� �����û�У�ȡ���������ʱ��
                			  {
                				  sql6="select signtime from HRMSCHEDULESIGNIMP where signdate='"+symd+"' and userid='"+userid+"'";
                				  rs2.execute(sql6);
                				  while (rs2.next()) 
                				  {
                					  String s2 = rs2.getString("signtime").substring(0,5);
                					  String flag1 = AllUtil.getZzZw(ssfm, s2, "max");//��������ص�flag������ssfm��˵�����s2�ڷ�Χ�ڣ�����Ҫ��
                					  String flag2 = AllUtil.getZzZw("23:59", s2, "min");
                					  if (flag1.equals(s2) && flag2.equals(s2)) {
                						  String flag3 = AllUtil.getZzZw(minTime,s2, "min");
                						  String flag4 = AllUtil.getZzZw(maxTime,s2, "max");
                						  minTime=flag3;
                						  maxTime=flag4;
                					  }
                				  }
                				  sql6="select signtime from HRMSCHEDULESIGNIMP where signdate='"+xymd+"' and userid='"+userid+"'";
                				  rs2.execute(sql6);
                				  if (rs2.next()) {
                					  String aa = null;
                					  while(rs2.next()){
                						  String s2 = rs2.getString("signtime").substring(0,5);
                						  String flag1 = AllUtil.getZzZw(xsfm, s2, "min");
                						  if (flag1.equals(s2)) {
                							  String flag2 = AllUtil.getZzZw(aa, s2, "max");
                							  aa=flag2;
                						  }
                					  }
                					  maxTime=aa;
                				  }
                			  }
                			  if (minTime!=null && maxTime!=null) {
                				  signtime12=minTime.substring(0,5)+" "+maxTime.substring(0, 5); 
                			  }else if(minTime!=null && maxTime==null){
                				  signtime12=minTime.substring(0,5); 
                			  }else if(minTime==null && maxTime!=null){
                				  signtime12="--:-- "+" "+maxTime.substring(0,5); 
                			  }else{
                				  signtime12="";
                			  }
//                          this.logger.info("[�Ű���Ա�����°��ʱ�䣺]"+signtime12);
                		  }else if(a.equals(importsql.substring(0, 5)) && departmentid.equals("18")){//����ǿͷ����ģ�importsql�Ƿ��Ű���Ա��˵��������û���Ű࣬��Ϣ
                			  signtime12=" ";
                			  sxbsj="R��";
                		  }
             	  }else if (importsql.equals("") && !departmentid.equals("18")) {
        			  if (!weekOrNo) {//���������ĩ
        				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
        				  sxbsj="D��["+sbsj+" "+xbsj+"]";
        			  }else{
        				  signtime12=AllUtil.getFpbZzZw(signdate, userid);
        				  sxbsj="��Ϣ"+zoumos+" "+zoumox;
        			  }
        		  }else if (importsql.equals("") && departmentid.equals("18")) {
             		  rs2.execute("select importsql from HRMSCHEDULESIGNIMP where userid='"+userid+"' and signdate='"+signdate+"'");
             		  while(rs2.next()){
             			  String importsql2 = Util.null2String(rs2.getString("importsql"));
             			  if (!importsql2.equals("")) {
       						  sftj=true;//˵����һ�����滹�в�Ϊ�յ����ݣ�ֻ��û�б�����������������
                              break;
						  }
             		  }
             	  }
            	
                	  if (!sftj) {
                		  root_json.put("����", userid);//�����������id���õ��ױ���Զ������id������
                		  root_json.put("����", departmentid);
                		  root_json.put("����", signdate);
                		  root_json.put("��ʱ��", signtime12);
                		  root_json.put("�Ű�ʱ��", sxbsj);
                		  root_json.put("importsql",importsql);
                		  root_jsons.add(root_json);
					  }
        			  
        				String sql66 = "";
        				rs2.execute("select * from uf_kqsj where xm='"+userid+"' and bm='"+departmentid+"' and rq='"+signdate+"'");
        				if (rs2.next()) 
        				{
        					sql66="update uf_kqsj set dksj='"+signtime12+"' where xm='"+userid+"' and bm='"+departmentid+"' and rq='"+signdate+"'";
        				}
        				else
        				{
        				    sql66 = "insert into uf_kqsj (xm,bm,rq,dksj,pbsj,"
        				    		+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
        						    + "values ('"+userid+"','"+departmentid+"','"+signdate+"','"+signtime12+"','"+sxbsj+"',"
        						    + "'1','"+AllUtil.returnYmd()+"','00:00','5','0')";
        				}
        				rs2.execute(sql66);
        		  }

      }
      //�����������ڻ�ϵͳ����������
	  this.logger.info("=============================jieshu " + getClass().getName() + " job...");
  }
}
