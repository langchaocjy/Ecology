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
	private static BaseBean basebean = new BaseBean();
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
    	RecordSet rs = new RecordSet();
    	rs.execute("update uf_kgbd set status = '0',dongjie = '3' where sqr = '"+st+"'");//0:不显示，表示不是这个部门下的岗位   3：冻结状态的岗位
    	RecordSet rs3 = new RecordSet();
    	RecordSet rs4 = new RecordSet();
    	rs.execute("select departmentcode from hrmdepartment where id = '"+bm+"'");
    	if(rs.next()){
    		String departmentcode = rs.getString("departmentcode");
    		JSONArray jsonarray = new rs018().setRs018(sqrq, departmentcode);
    		if(jsonarray.size()>0){
    			for(int i = 0 ; i < jsonarray.size(); i++ ){
    				JSONObject j = jsonarray.getJSONObject(i);
    				String ckgw = j.getString("参考岗位");//ckgw
        			String gwbm = j.getString("岗位编码");//gwbm
        			String shorts = j.getString("岗位名称");//kxgw
        			rs3.execute("SELECT COUNT(REQUESTID) AS NUM FROM GWSD WHERE STATE= 1 AND GWBM = '" + gwbm + "'");
        			String dj = "2",dj1="2",dj2="2";
        			if(rs3.next())
        			{
        				dj1 = rs3.getString("num");//GWSD的1是冻结，0是解冻
        			}
        			rs3.execute("SELECT COUNT(REQUESTID) AS NUM FROM GWSD WHERE STATE= 1 AND GWBM = '" + bm + "'");
        			if (rs3.next()) 
        			{
    					dj2 = rs3.getString("num");
    				}
        			
        			
           			if (dj1.equals("0") && dj2.equals("0")){
    					dj="0";
    				}else if(dj1.equals("1") || dj2.equals("1")){
    					dj="1";
    				}
        			rs3.execute("select * from uf_kgbd where gwbm = '"+gwbm+"' and sqr = '"+st+"'");
        			if(rs3.next())
        			{
        				String sql1 = "update uf_kgbd set status = '1',dongjie = '3' where gwbm = '"+gwbm+"' and sqr = '"+st+"'";
        				String sql2 = "update uf_kgbd set status = '1',dongjie = '4' where gwbm = '"+gwbm+"' and sqr = '"+st+"'";

        					if(dj == "1" || dj.equals("1"))
        					{
        						rs4.execute(sql1);
        					}
        					if(dj == "0" || dj.equals("0"))
        					{
        						rs4.execute(sql2);
        					}
        					if(dj == "2" || dj.equals("2"))
        				    {
        					    rs4.execute(sql2);
        				    }
        			}
        			else
        			{
    					String sql4 = "insert into uf_kgbd (ckgw,kxgw,gwbm,sqr,status,dongjie,"
        						+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
        						+ "values ('"+ckgw+"','"+shorts+"','"+gwbm+"','"+st+"','1','3','1','2018-07-10','12:00','312','0')";
    					String sql5 = "insert into uf_kgbd (ckgw,kxgw,gwbm,sqr,status,dongjie,"
        						+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
        						+ "values ('"+ckgw+"','"+shorts+"','"+gwbm+"','"+st+"','1','4','1','2018-07-10','12:00','312','0')";
        					if(dj == "1" || dj.equals("1"))
        					{
        						rs4.execute(sql4);
        					}
        					if(dj == "0" || dj.equals("0"))
        					{
        						rs4.execute(sql5);
        					}
        				    if(dj == "2" || dj.equals("2"))
        				    {
        					    rs4.execute(sql5);
        				    }
        			}
        		}
    		}
    	}
    }
}
