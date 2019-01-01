package com.rs;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class pyrysplc extends RsUtil implements Action{
	
	public static BaseBean basebean = new BaseBean();
	@Override
	public String execute(RequestInfo requestinfo) {
		basebean.writeLog("---------开始流程写入底表----------");
		String requestid = requestinfo.getRequestid();
		RecordSet rs1 = new RecordSet();
		
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nyr = dateFormat.format(date).substring(0,10);
		String sf = dateFormat.format(date).substring(11,16);
		
		try {
			JSONObject mainData = getMainData(requestid);
			basebean.writeLog("mainData:  "+mainData);
			String WID=mainData.getString("wid");//
			String sqbm=getDepartmentid(mainData.getString("bh"));//申请部门
			String xygxm=mainData.getString("xygxm");//新员工姓名
			String bh=mainData.getString("bh");//编号
			String xb = mainData.getString("xb");//性别
			String jg = mainData.getString("jg");//籍贯
			String csny = mainData.getString("csny");//出生年月
			String sg = mainData.getString("sg");//身高
			String rdsj = mainData.getString("rdsj");//入党时间
			String zc = mainData.getString("zhicheng");//ְ职称
			String zw =mainData.getString("zw");//ְ职位
			String sj = mainData.getString("sj");//手机
			String sfzhm = mainData.getString("sfzhm");//身份证
			String email = mainData.getString("email");//email
			String xqjah=mainData.getString("xqjah");//兴趣及爱好
			String xli=mainData.getString("xli");//全日制学历
			String xl = mainData.getString("xwwb");//学位文本
			String htksrq=mainData.getString("htksrq");//合同开始日期
			String htjsrq=mainData.getString("htjsrq");//合同结束日期
			String zzmm_wb=mainData.getString("zzmm_wb");//政治面貌
			String mz_wb=mainData.getString("mz_wb");//民族
			String lysj=mainData.getString("lysj");//来院时间
			String cjgzsj=mainData.getString("cjgzsj");//参加工作时间
			String hy = mainData.getString("hy");//婚育
			String hjdz = mainData.getString("hjdz");//户籍地址ַ
			String txdz = mainData.getString("txdd");//通信地址ַ
			String wycd = mainData.getString("wycd");//外语程度
			String qrzzy=mainData.getString("qrzzy");//全日制专业
			String qrzbysj=mainData.getString("qrzbysj");//全日制毕业时间
			String qrzxxxz=mainData.getString("qrzxxxz");//全日制学校性质
			String gwrq=mainData.getString("gwrq");//岗位日期
			String zcpyrq=mainData.getString("zcpyrq");//ְ职称聘用日期
			String zcdj=mainData.getString("zcdj");//ְ职称等级
			String pdsj = mainData.getString("pdsj");//评定时间
			String password=mainData.getString("password");//密码
			String gzddian=mainData.getString("ggddian");//工作地点
			String ryzt=mainData.getString("ryzt");//人员状态״̬
			String sqgw=mainData.getString("sqgw");//申请职位
			String glbm=mainData.getString("glbm");//
			String hkxz=mainData.getString("hkxz");//
			String sql10="update hrmresource set"
					+ "sex='"+xb+"',nativeplace='"+jg+"',birthday='"+csny+"',"
					+ "height='"+sg+"',bepartydate='"+rdsj+"',jobcall='"+zc+"',mobile='"+sj+"',certificatenum='"+sfzhm+"',"
				    + "email='"+email+"',textfield2='"+xqjah+"',educationlevel='"+xli+"',degree='"+xl+"',startdate='"+htksrq+"',enddate='"+htjsrq+"',"
				    + "policy='"+zzmm_wb+"',folk='"+mz_wb+"' where lastname ='"+xygxm+"' and loginid='"+bh+"'";
			String sql11="insert into uf_hrminfo"
			   		+ "(WID,DEPARTMENT_ID,PERSON_NO,PERSON_NAME,TECH_POST,"
			   		+ "DUTY,BIRTHDAY,ADDRESS,DEGREE,PHONE,SPECIALTY,PASSWORD,CULTURE,BEGINDATE,"
			   		+ "NATIVE_PLACE,IMMUREDATE,AUDIT_DATE,E_MAIL,IDNO,REGION,GW_DATE,ZCPY_DATE,"
			   		+ "ZCPD_DATE,LS_DATE,POLITY,HANDCOLL,CONTRACT_BEGIN,CONTRACT_END,CONTRACT_NO,"
			   		+ "PART_FILE,TECH_CLASS,WORKKIND,NATION,MARRYTYPE,SEX,POSITIONID,SUPER_ID,"
			   		+ "HUKOU,ZCPY_DEPLABEL,SCHOOLNATURE,"
			   		+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
			   		+ "values('"+WID+"','"+sqbm+"','"+bh+"','"+xygxm+"','"+zc+"','"+zw+"',"
			   				+ "'"+csny+"','"+txdz+"','"+xl+"','"+sj+"','"+qrzzy+"','"+password+"',"
			   				+ "'"+xli+"','"+cjgzsj+"','"+jg+"','"+rdsj+"','"+qrzbysj+"',"
			   				+ "'"+email+"','"+sfzhm+"','"+gzddian+"','"+gwrq+"','"+zcpyrq+"','"+pdsj+"',"
			   				+ "'"+lysj+"','"+zzmm_wb+"','"+sj+"','"+htksrq+"','"+htjsrq+"',"
			   				+ "'"+xqjah+"','"+wycd+"','"+zcdj+"','"+ryzt+"','"+mz_wb+"','"+hy+"',"
			   				+ "'"+xb+"','"+sqgw+"','"+glbm+"','"+hkxz+"','"+sqbm+"','"+qrzxxxz+"',"
			   				+ "'1','2018-12-05','00:00','381','0')";
//			String sql11="insert into uf_hrminfo"
//			   		+ "(WID,DEPARTMENT_ID,PERSON_NO,PERSON_NAME,TECH_POST,"
//			   		+ "DUTY,BIRTHDAY,ADDRESS,DEGREE,PHONE,SPECIALTY,PASSWORD,BEGINDATE,"
//			   		+ "NATIVE_PLACE,IMMUREDATE,AUDIT_DATE,E_MAIL,IDNO,REGION,GW_DATE,ZCPY_DATE,"
//			   		+ "ZCPD_DATE,LS_DATE,POLITY,HANDCOLL,CONTRACT_BEGIN,CONTRACT_END,CONTRACT_NO,"
//			   		+ "TECH_CLASS,NATION,SEX,POSITIONID,"
//			   		+ "HUKOU,ZCPY_DEPLABEL,SCHOOLNATURE,"
//			   		+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
//			   		+ "values('"+WID+"','"+sqbm+"','"+bh+"','"+xygxm+"','"+zc+"','"+zw+"',"
//			   				+ "'"+csny+"','"+txdz+"','"+xl+"','"+sj+"','"+qrzzy+"','"+password+"',"
//			   				+ "'"+cjgzsj+"','"+jg+"','"+rdsj+"','"+qrzbysj+"',"
//			   				+ "'"+email+"','"+sfzhm+"','"+gzddian+"','"+gwrq+"','"+zcpyrq+"','"+pdsj+"',"
//			   				+ "'"+lysj+"','"+zzmm_wb+"','"+sj+"','"+htksrq+"','"+htjsrq+"',"
//			   				+ "'"+xqjah+"','"+zcdj+"','"+mz_wb+"',"
//			   				+ "'"+xb+"','"+sqgw+"','"+hkxz+"','"+sqbm+"','"+qrzxxxz+"',"
//			   				+ "'1','2018-12-05','00:00','381','0')";

			String sql12="select id from hrmresource where lastname='"+xygxm+"' and loginid='"+bh+"'";
			rs1.execute(sql10);
			basebean.writeLog("[sql10-->]"+sql10);
			rs1.execute(sql11) ;
			basebean.writeLog("[sql11-->]"+sql11);
			rs1.execute("select id from uf_hrminfo where PERSON_NO='"+bh+"'");
			if (rs1.next()) {
				ModeRightInfo modeRightInfo=new ModeRightInfo();
				modeRightInfo.setNewRight(true);
				modeRightInfo.editModeDataShare(1,381,Integer.parseInt(rs1.getString("id")));
			}
			rs1.execute(sql12);
			basebean.writeLog("[sql12-->]"+sql12);
			String id ="";
			if (rs1.next()) {
				id=rs1.getString("id");
			}
			String sql13="select * from cus_fielddata where id='"+id+"'";
			String sql14="update cus_fielddata set field6='"+lysj+"',field10='"+cjgzsj+"',field11='"+hy+"',field13='"+hjdz+"',"
					+ "field14='"+txdz+"',field15='"+wycd+"',field16='"+qrzzy+"',field17='"+qrzbysj+"',field18='"+qrzxxxz+"',"
					+ "field19='"+gwrq+"',field20='"+zcpyrq+"' where scopeid='1' and id='"+id+"'";
			String sql15="update cus_fielddata set field0='"+zcdj+"',field12='"+pdsj+"' where scopeid='-1' and id='"+id+"'";
			String sql16="insert into hrmeducationinfo(id,field0,field6,field10,field11,field12,field13,field14,field15,field16,field17,field18,field19,field20)"
					+ "values('"+id+"','"+zcdj+"','"+lysj+"','"+cjgzsj+"','"+hy+"','"+pdsj+"','"+hjdz+"','"+txdz+"','"+wycd+"','"+qrzzy+"','"+qrzbysj+"','"+qrzxxxz+"',"
					+ "'"+gwrq+"','"+zcpyrq+"')";
			boolean isHave=false;
			rs1.execute(sql13);
			basebean.writeLog("[sql13-->]"+sql13);
			if (rs1.next()) {
				isHave=true;
			}else{
				isHave=false;
			}
			if (isHave) {
				rs1.execute(sql14);
				basebean.writeLog("[sql14-->]"+sql14);
				rs1.execute(sql15);
				basebean.writeLog("[sql15-->]"+sql15);
			}else{
				basebean.writeLog("[sql16-->]"+sql16);
				rs1.execute(sql16);
			}
			JSONArray detailData1 = getDetailData(requestid, 1);
			basebean.writeLog("detaildata1 "+detailData1);
			for (int i = 0; i < detailData1.size(); i++) 
			{
				JSONObject json = detailData1.getJSONObject(i);
				String byyx=json.getString("byyx");
				String kssj=json.getString("kssj");
				String jssj=json.getString("jssj");
				String zy=json.getString("zy");
				String shzs=json.getString("shzs");
				String fj=json.getString("fj");
				
				String sql1="insert into hrmeducationinfo(resourceid,school,startdate,enddate,speciality) "
						+ "values('"+id+"','"+byyx+"','"+kssj+"','"+jssj+"','"+zy+"')";
				basebean.writeLog("[sql1--------> ]"+sql1);
				rs1.execute(sql1);
			}
			JSONArray detailData2=getDetailData(requestid,2);
			for (int i = 0; i < detailData2.size(); i++) {
				JSONObject json = detailData2.getJSONObject(i);
				String gzdwmc=json.getString("gzdwmc");
				String zw1=json.getString("zw");
				String gzkssj=json.getString("gzkssj");
				String gzjssj=json.getString("gzjssj");
				String lzyy=json.getString("lzyy");
				String zc1=json.getString("zc");
				String sql2="insert into hrmworkresume(resourceid,company,jobtitle,startdate,enddate,leavereason)"
						+ "values('"+id+"','"+gzdwmc+"','"+zw1+"','"+gzkssj+"','"+gzjssj+"','"+lzyy+"')";
				basebean.writeLog("[sql2->]"+sql2);
				rs1.execute(sql2);
			}
			
			JSONArray detailData3=getDetailData(requestid,3);
			for (int i = 0; i < detailData3.size(); i++) {
				JSONObject json=detailData3.getJSONObject(i);
				String gx=json.getString("gx");
				String xm=json.getString("xm");
				String csny1=json.getString("csny");
				String gzdw=json.getString("gzdw");
				String lxdh=json.getString("lxdh");
				String sql3="insert into hrmfamilyinfo(resourceid,title,member,company,address)"
						+ "values('"+id+"','"+gx+"','"+xm+"','"+gzdw+"','"+lxdh+"')";
				basebean.writeLog("[sql3->]"+sql3);
				rs1.execute(sql3);
			}
			
			JSONArray detailData4=getDetailData(requestid,4);
			for (int i = 0; i < detailData4.size(); i++) {
				JSONObject json=detailData4.getJSONObject(i);
				String lszc=json.getString("lszc");
				String pdsj1=json.getString("pdsj");
				String bz=json.getString("bz");
				String fj1=json.getString("fj");
			}
			basebean.writeLog("---------结束----------");
		} 
		catch (Exception e) 
		{
            e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getDepartmentid(String workcode){
		String departmentid="";
		RecordSet rs = new RecordSet();
		rs.execute("select departmentid from hrmresource where workcode='"+workcode+"'");
		if (rs.next()) {
			departmentid=rs.getString("departmentid");
		}
		return departmentid;
	}
}
