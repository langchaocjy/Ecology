package com.tcss.action.pr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcss.report.rs018;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class FreePost extends HttpServlet{
	private BaseBean basebean = new BaseBean();
	public FreePost(){
		super();
	}
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	doPost(request,response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response){
    	String bm = request.getParameter("bm");
    	String sqrq = request.getParameter("date");
    	String sqr = request.getParameter("sqr")+"-";
    	String tablename = request.getParameter("tablename");
    	String st = sqr.concat(tablename);
    	
    	RecordSet rs = new RecordSet(),rs4 = new RecordSet();
    	rs.execute("update uf_kgbd set status = '0',dongjie = '3' where sqr = '"+st+"'");//0:����ʾ����ʾ������������µĸ�λ   3������״̬�ĸ�λ
    	rs.execute("select departmentcode from hrmdepartment where id = '"+bm+"'");
    	String departmentcode = "";
    	if(rs.next()){
    		 departmentcode = rs.getString("departmentcode");
    	}
    	
    	JSONArray jsonarray = new rs018().setRs018(sqrq, departmentcode);
    	if(jsonarray.size()>0){
    		for(int i = 0 ; i < jsonarray.size(); i++ ){
    			JSONObject j = jsonarray.getJSONObject(i);
    			String ckgw = j.getString("�ο���λ");//ckgw
    			String gwbm = j.getString("��λ����");//gwbm  ��λ���������Ϊ�գ���ô���ǿոڣ����û�и�λ������Ϊ���˵ĸ�λ
    			String shorts = j.getString("��λ����");//kxgw
    			String dj = "2",dj1="2",dj2="2";
    			rs4.execute("SELECT COUNT(REQUESTID) AS NUM FROM GWSD WHERE STATE= 1 AND GWBM = '" + gwbm + "'");
    			if(rs4.next())
    			{
    				dj1 = rs4.getString("num");//GWSD��1�Ƕ��ᣬ0�ǽⶳ
    			}
    			rs4.execute("SELECT COUNT(REQUESTID) AS NUM FROM GWSD WHERE STATE= 1 AND GWBM in(select id from HRMJOBTITLES where jobtitlecode='"+gwbm+"')");
    			if (rs4.next()) 
    			{
					dj2 = rs4.getString("num");
				}
    			if (dj1.equals("0") && dj2.equals("0")){
					dj="0";
				}else if(dj1.equals("1") || dj2.equals("1")){
					dj="1";
				}
    			basebean.writeLog("[dj1-->]"+dj1+"  dj2:"+dj2+"  dj:"+dj);
    			
    			
    			rs.execute("select * from uf_kgbd where gwbm = '"+gwbm+"' and sqr = '"+st+"'");
    			if(rs.next())
    			{
    				String sql1 = "update uf_kgbd set status = '1',dongjie = '3' where gwbm = '"+gwbm+"' and sqr = '"+st+"'";
    				String sql2 = "update uf_kgbd set status = '1',dongjie = '4' where gwbm = '"+gwbm+"' and sqr = '"+st+"'";
    				if(dj.equals("1"))
    				{
    					rs4.execute(sql1);
    				}
    				if(dj.equals("0"))
    				{
    					rs4.execute(sql2);
    				}
    			}
    			else
    			{
    				String sql4 = "insert into uf_kgbd (ckgw,kxgw,gwbm,sqr,status,dongjie,"
    						+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
    						+ "values ('"+ckgw+"','"+shorts+"','"+gwbm+"','"+st+"','1','3','1','2018-07-10','12:00','541','0')";
    				String sql5 = "insert into uf_kgbd (ckgw,kxgw,gwbm,sqr,status,dongjie,"
    						+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
    						+ "values ('"+ckgw+"','"+shorts+"','"+gwbm+"','"+st+"','1','4','1','2018-07-10','12:00','541','0')";
    				if(dj.equals("1"))
    				{
    					rs4.execute(sql4);
    				}
    				if(dj.equals("0"))
    				{
    					rs4.execute(sql5);
    				}
    			}
    		}
    	}
    }
}
