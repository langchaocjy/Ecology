package com.hr.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eas.util.ConfigUtil;
import com.eas.util.DateUtils;
import com.hr.bean.HeadCount;
import com.hr.bean.MokaDataPush;

import weaver.conn.RecordSet;

public class MokaUtil {

	private static Logger logger = LoggerFactory.getLogger(MokaUtil.class);
	private final static String MOKA_URL = ConfigUtil.getProperty("moka.api.url");
	private final static String FEEDBACKS_URL=MOKA_URL+"/v1/data/interviewer_feedbacks/";  //面试反馈接口
	private final static String ADD_HEADCOUNT_URL = MOKA_URL+"/v1/headcount/";
	private final static String OFFER_APPROVAL_URL = MOKA_URL+"/v1/applications/offerApproval/";
	private final static String HIRED_URL = MOKA_URL+"/v1/applications/{applicationId}/hired/";
	private final static String EHR_APPLICATIONS_URL = MOKA_URL+"/v1/data/ehrApplications";
	
	
	/**
	 * 新增moka 的hc职位
	 * @param headCount hc对象
	 * @param currentHireMode 招聘模式1	社招。2	校招
	 * @return
	 */
	public static JSONObject addHeadCount(HeadCount headCount,int currentHireMode){
		JSONObject jsonObject = new JSONObject();
		if(currentHireMode!=1 && currentHireMode!=2){
			jsonObject.put("data", new JSONObject());
			jsonObject.put("code", 1);
			jsonObject.put("message", "请输入正确的招聘模式");
			return jsonObject;
		}
		
		String param= JSON.toJSONString(headCount);
		String url = ADD_HEADCOUNT_URL +"?currentHireMode="+currentHireMode;
		System.out.println(param);
		String retJsonStr = MokaHttpUtil.doPostForJson(url, param);
		JSONObject json=(JSONObject) JSONObject.parse(retJsonStr);
		if(json!=null){
			String isFalse = json.getString("success");
			if(!"false".equals(isFalse)){ //请求成功
				jsonObject.put("data", json);
				jsonObject.put("code", 0);
				jsonObject.put("message", "请求成功");
				
			}else{ //请求失败
				String message = json.getString("errorMessage");
				jsonObject.put("data", new JSONObject());
				jsonObject.put("code", 1);
				jsonObject.put("message", message);
			}
		}else{
			jsonObject.put("data", new JSONObject());
			jsonObject.put("code", 1);
			jsonObject.put("message", "请求出错");
		}
		
		return jsonObject;
	}
		
	/**
	 * 根据applicationId获取面试人员信息
	 * @param applicationId
	 * @return
	 */
	public static Map<String, Object> getUserInfo(String applicationId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("applicationId", applicationId);
		String retJsonStr = "";
		try {
			retJsonStr = MokaHttpUtil.doGet(EHR_APPLICATIONS_URL,parmMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(retJsonStr);
		JSONObject json=(JSONObject) JSONObject.parse(retJsonStr);
		if(json!=null) {
			JSONArray dataArray = (JSONArray) json.get("data");
			if(dataArray!=null && !dataArray.isEmpty()) {
				JSONObject userObject = (JSONObject) dataArray.get(0);
				String name = userObject.getString("name");
				String gender = userObject.getString("gender");
				retMap.put("name", name);
				retMap.put("gender", gender);
			}else {
				retMap.put("name", "候选人不存在");
			}
		}
		return retMap;
	}
	
	/**
	 * 获取面试反馈
	 * @param fromDate  开始时间
	 * @return
	 */
	public static JSONObject getFeedbacks(Date fromDate){
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			boolean isNext = true;
			String next = "";
			while (isNext) {
				String fromTime = DateUtils.formatDate(fromDate);
				Map<String, Object> parmMap = new HashMap<String, Object>();
				parmMap.put("fromTime", fromTime);
				if(!StringUtils.isBlank(next)){
					parmMap.put("next", next);
				}
				parmMap.put("limit", 100);
				String retJsonStr = MokaHttpUtil.doGet(FEEDBACKS_URL,parmMap);
				JSONObject itemJson=(JSONObject) JSONObject.parse(retJsonStr);
				if(itemJson!=null){
					String isFalse = itemJson.getString("success");
					if(!"false".equals(isFalse)){ //请求成功
						JSONArray itemArray = (JSONArray) itemJson.get("data");
						for(int i=0;i<itemArray.size();i++){
							JSONObject itemObject = itemArray.getJSONObject(i); 
							jsonArray.add(itemObject);
						}
						if(itemJson.get("next")!=null){
							next = (String) itemJson.get("next");
						}else{
							isNext = false;
							next = "";
						}
					}else{ //请求失败
						String message = itemJson.getString("errorMessage");
						jsonObject.put("data", jsonArray);
						jsonObject.put("code", 1);
						jsonObject.put("message", message);
						return jsonObject;
					}
				}else{
					next = "";
					isNext = false;
					jsonObject.put("data", jsonArray);
					jsonObject.put("code", 1);
					jsonObject.put("message", "请求出错");
					return jsonObject;
				}
				if(StringUtils.isBlank(next)){
					isNext = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject.put("data", jsonArray);
		jsonObject.put("code", 0);
		jsonObject.put("message", "请求成功");
		return jsonObject;
	}
	
	/**
	 * offer审批
	 * @param applicationId 要更新offer审批结果的申请id
	 * @param status 审批结果更新状态，可选值：{ 1: 审批通过，2: 审批驳回 }
	 * @return
	 */
	public static Map<String, Object> offerApproval(Integer applicationId,Integer status){
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject paramObject =new JSONObject();
		paramObject.put("applicationId", applicationId);
		paramObject.put("status", status);
		try {
			String retJsonStr = MokaHttpUtil.doPutForJson(OFFER_APPROVAL_URL,paramObject.toJSONString());
			JSONObject retJson=(JSONObject) JSONObject.parse(retJsonStr);
			if(retJson==null){
				retMap.put("success", false);
				retMap.put("message", "返回异常");
				return retMap;
			}
			boolean isSuccess = retJson.getBooleanValue("success");
			String message = retJson.getString("errorMessage");
			message = StringUtils.isBlank(message) ? "提交成功":message;
			retMap.put("success", isSuccess);
			retMap.put("message", message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	/**
	 * 标记候选人已入职
	 * @param applicationId 要标记已入职的候选人申请id
	 * @param hcId 该候选人对应职位的对应headcount id
	 * @param probation 实际入职时间试用期，可选值0-6，0为无试用期，1-6对应几个月试用期
	 * @param hiredAt 实际入职时间
	 * @return
	 */
	public static Map<String, Object> hired(Integer applicationId,Integer hcId,Integer probation,Date hiredAt){
		Map<String, Object> retMap = new HashMap<String, Object>();
		JSONObject paramObject =new JSONObject();
		if(hcId!=null && hcId>0){
			paramObject.put("hcId", hcId);
		}
		
		paramObject.put("hiredAt", DateUtils.formatDate(hiredAt));
		paramObject.put("probation", probation);
		String url = HIRED_URL.replace("{applicationId}", applicationId+"");
		try {
			String retJsonStr = MokaHttpUtil.doPutForJson(url,paramObject.toJSONString());
			JSONObject retJson=(JSONObject) JSONObject.parse(retJsonStr);
			if(retJson==null){
				retMap.put("success", false);
				retMap.put("message", "返回异常");
				return retMap;
			}
			boolean isSuccess = retJson.getBooleanValue("success");
			String message = retJson.getString("errorMessage");
			message = StringUtils.isBlank(message) ? "提交成功":message;
			retMap.put("success", isSuccess);
			retMap.put("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	public static void insertMokaData(MokaDataPush mokaDataPush){
		   String xm=mokaDataPush.getName();
		   String zwmc=mokaDataPush.getJobtitle();
		   String zwszbm=mokaDataPush.getPositionDep();
		   String lxdh=mokaDataPush.getPhone();
		   String yx=mokaDataPush.getEmail();
		   String xb=mokaDataPush.getSex();
		   String csny=mokaDataPush.getBirthday();
		   String jg=mokaDataPush.getNativePlace();
		   String zgxl=mokaDataPush.getHighestEducation();
		   String hyzt=mokaDataPush.getMaritalStatus();
		   String tjrxm=mokaDataPush.getReferrerName();
		   String tjryx=mokaDataPush.getReerrerEmail();
		   RecordSet rs = new RecordSet();
		   rs.execute("insert into uf_mktsjlb(xm,zwmc,zwszbm,lxdh,yx,xb,csny,jg,zgxl,hyzt,tjrxm,tjryx,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)"
		   		+ "values('"+xm+"','"+zwmc+"','"+zwszbm+"','"+lxdh+"','"+yx+"','"+xb+"','"+csny+"','"+jg+"','"+zgxl+"','"+hyzt+"','"+tjrxm+"','"+tjryx+"',"
		   				+ "'1','"+getDateString("yyyy-MM-dd", new Date())+"','"+getDateString("HH:mm:ss", new Date())+"','47','0')");
		   
		   
	}
	
	public static String getDateString(String type, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateTime = sdf.format(data);
		return dateTime;
	}
	
	public static void main(String[] args) throws ParseException {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2018-10-21");
		JSONObject object=getFeedbacks(date);
		System.out.println(object.toJSONString());*/
		Map<String, Object> map = getUserInfo("44492");
		System.out.println(map.get("name"));
		
		/*HeadCount hc = new HeadCount();
		hc.setNeedNumber(1);
		hc.setJobName("技术总监助理");
		hc.setNumber("0033");
		hc.setStartDate("2018-11-28");
		hc.setDescription("带领员工");
		JSONObject object2= addHeadCount(hc,1);
		System.out.println(object2.toJSONString());*/
		
		/*Map<String, Object> bMap= offerApproval(44536,1);
		System.out.println(bMap.get("success"));
		System.out.println(bMap.get("message"));*/
		
		/*Map<String, Object> bMap= hired(44536,476,3,new Date());
		System.out.println(bMap.get("success"));
		System.out.println(bMap.get("message"));*/
	}
	
}
