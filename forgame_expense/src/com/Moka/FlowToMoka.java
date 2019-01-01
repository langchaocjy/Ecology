package com.Moka;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hr.bean.HeadCount;
import com.hr.enums.CommimentEnum;
import com.hr.enums.HcStatusEnum;
import com.hr.util.MokaUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class FlowToMoka extends BaseBean implements Action{
	@Override
	public String execute(RequestInfo requestinfo) {
		String requestid = requestinfo.getRequestid();
		RecordSet rs = new RecordSet();
		HeadCount headcount = new HeadCount();
		MokaUtil mokaUtil = new MokaUtil();
		JSONObject mainData = null;
		try {
			mainData = getMainData(requestid);
			writeLog("mainDATA:"+mainData);
			String hcbm = mainData.getString("hcbm");//hc编号
			String xqzwmc=mainData.getString("xqzwmc");//hc名称
			String zprs=mainData.getString("zprs");//招聘人数
			Integer zwsx=mainData.getInteger("zwsx");//职位属性
			Integer zhuangtai=mainData.getInteger("zhuangtai");//状态
			String xwdgrq=mainData.getString("xwdgrq");//期望到岗日期
			String zpms=mainData.getString("zpms");//招聘模式
			String gwzz=mainData.getString("gwzz");//岗位职责
			String commitment = CommimentEnum.getMkValue(zwsx);
            String status = HcStatusEnum.getMkValue(zhuangtai);
			
			headcount.setNumber(hcbm);
			headcount.setJobName(xqzwmc);
			headcount.setNeedNumber(Integer.parseInt(zprs));
			headcount.setCommitment(commitment);
			headcount.setStatus(status);
			headcount.setStartDate(xwdgrq);
			headcount.setCompleteDate(xwdgrq);
			headcount.setDescription(gwzz);
			JSONObject mokajson=mokaUtil.addHeadCount(headcount, (Integer.parseInt(zpms)+1));
			writeLog("mokajson-->"+mokajson);
			String code = mokajson.getString("code");
			String data = mokajson.getString("code");
			String message = mokajson.getString("message");
			if (code.equals("0")) {
				String id = mokajson.getJSONObject("data").getJSONObject("headcount").getString("id");
				writeLog("jsonsid-->"+id);
				rs.execute("update formtable_main_164 set hcid='"+id+"' where requestid = '"+requestid+"'");
			}else{
				requestinfo.getRequestManager().setMessageid(System.currentTimeMillis()+"");
				requestinfo.getRequestManager().setMessagecontent("新增岗位失败：[code:]"+code+"[data:]"+data+"[message:]"+message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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
 	public String getTableName(String requestid)
 	  {
 	    String tablename = "";
 	    if (!"".equals(requestid))
 	    {
 	      String sql = "select tablename from workflow_bill where id in(select formid from workflow_base where id in(select workflowid from workflow_requestbase where requestid=" + requestid + "))";
 	      RecordSet rs = new RecordSet();
 	      rs.execute(sql);
 	      if (rs.next()) {
 	        tablename = rs.getString("tablename");
 	      }
 	    }
 	    return tablename;
 	  }
 	}
