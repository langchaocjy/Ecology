package com.Moka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.hr.util.MokaUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.Util;

public class RequestMs{
	private static BaseBean basebean=new BaseBean();
    public static void getRequestMs(String date){
    	
    	MokaUtil moka =  new MokaUtil();
    	RecordSet rs = new RecordSet();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 com.alibaba.fastjson.JSONObject json = moka.getFeedbacks(sdf.parse(date));
			 basebean.writeLog("moka返回的面试记录++++"+json);
			 com.alibaba.fastjson.JSONArray fastjsons = json.getJSONArray("data");
			    	if (fastjsons.size()>0) {
			    		for (int i = 0; i < fastjsons.size(); i++) {
			    			com.alibaba.fastjson.JSONObject fastjson = fastjsons.getJSONObject(i);
			    			String applicationId = Util.null2String(fastjson.getString("applicationId"));
			    			Map<String,Object> map = MokaUtil.getUserInfo(applicationId);
			    			String name=(String) map.get("name");
			    			String interviewerName=Util.null2String(fastjson.getString("interviewerName"));
			    			String interviewerEmail=Util.null2String(fastjson.getString("interviewerEmail"));
			    			String resultName=Util.null2String(fastjson.getString("resultName"));
			    			String resultType=Util.null2String(fastjson.getString("resultType"));
			    			int interviewType=ScreenName4(Util.null2String(fastjson.getString("interviewType")));
			    			String round=Util.null2String(fastjson.getString("round"));
			    			String feedback=Util.null2String(fastjson.getString("feedback"));
			    			String updatedAt=Util.null2String(fastjson.getString("updatedAt"));
			    			String sql="select * from uf_msjlb where applicationId='"+applicationId+"' and interviewerName='"+interviewerName+"' and interviewerEmail='"+interviewerEmail+"'"
			    					+ " and resultName='"+resultName+"' and resultType='"+resultType+"' and interviewType='"+interviewType+"' and round='"+round+"'"
			    					+ " and feedback='"+feedback+"'";
			    			boolean isHave = false;
			    	        rs.execute(sql);
			    	        if (rs.next()) {
								isHave =true;
							}
			    	        basebean.writeLog("是否有重复记录："+isHave);
			    	        if (!isHave) {
								 rs.execute("insert into uf_msjlb(status,applicationId,intervieweename,interviewerName,interviewerEmail,resultName,resultType,interviewType,round,feedback,updatedAt,"
								 		+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype)"
										 + "values('0','"+applicationId+"','"+name+"','"+interviewerName+"','"+interviewerEmail+"','"+resultName+"','"+resultType+"','"+interviewType+"',"
										 + "'"+round+"','"+feedback+"','"+updatedAt+"','1','"+getDateString("yyyy-MM-dd", new Date())+"','"+getDateString("HH:mm:ss", new Date())+"','39','0')");
							} else {
								rs.execute("update uf_msjlb set intervieweename='"+name+"', interviewerName='"+interviewerName+"',interviewerEmail='"+interviewerEmail+"',"
										+ "resultName='"+resultName+"',resultType='"+resultType+"',interviewType='"+interviewType+"',"
										+ "round='"+round+"',feedback='"+feedback+"',updatedAt='"+updatedAt+"' where applicationId='"+applicationId+"'");
							}
			    	        
			    	        String id="";
			    	        rs.execute(sql);
			    	        if (rs.next()) id=rs.getString("id");
			    	        if (!isHave) {
			    	        	feedback(fastjson,id);
							} else {
                                
							}
			    		}
					}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    public static void feedback(com.alibaba.fastjson.JSONObject fastjson,String id){
    	RecordSet rs = new RecordSet();
    	basebean.writeLog("fastjson++"+fastjson);
    	com.alibaba.fastjson.JSONObject feedjson=com.alibaba.fastjson.JSONObject.parseObject(fastjson.getString("feedbackTemplateResult"));
    	basebean.writeLog("feedjson++"+feedjson);
    	if (feedjson!=null) {
    		com.alibaba.fastjson.JSONArray itemjsons=feedjson.getJSONArray("items");
			for (int i = 0; i < itemjsons.size(); i++) {
				com.alibaba.fastjson.JSONObject itemjson=itemjsons.getJSONObject(i);
				String type=Util.null2String(itemjson.getString("type"));
				String key=Util.null2String(itemjson.getString("key"));
				String title=Util.null2String(itemjson.getString("title"));
				String description=Util.null2String(itemjson.getString("description"));
				String scoreType=Util.null2String(itemjson.getString("scoreType"));
				String calcScoreType=Util.null2String(itemjson.getString("calcScoreType"));
				String needReason=Util.null2String(itemjson.getString("needReason"));
				com.alibaba.fastjson.JSONArray subjsons=itemjson.getJSONArray("subjects");
				basebean.writeLog("subjsons+++"+subjsons);
				for (int k = 0; k < subjsons.size(); k++) {
					com.alibaba.fastjson.JSONObject subjson=subjsons.getJSONObject(k);
					String subjectstitle=subjson.getString("title");
					String subjectsdescription=subjson.getString("description");
					String subjectsresult=subjson.getString("result");
					com.alibaba.fastjson.JSONArray opjsons=feedjson.getJSONArray("options");
					basebean.writeLog("opjsons+++"+opjsons);
					if (opjsons!=null) {
						for (int l = 0; l < opjsons.size(); l++) {
							com.alibaba.fastjson.JSONObject opjson=opjsons.getJSONObject(l);
							String subjectsoptions=opjson.getString("subjectsoptions");
							basebean.writeLog("[1:-->]"+"insert into uf_msjlb_dt1(mainid,type,title,description,calcScoreType,scoreType,needReason,subjectstitle,subjectsdescription,"
									+ "subjectsresult,subjectsoptions,itemskey)values('"+id+"','"+type+"','"+title+"','"+description+"','"+calcScoreType+"',"
									+ "'"+scoreType+"','"+needReason+"','"+subjectstitle+"','"+subjectsdescription+"','"+subjectsresult+"','"+subjectsoptions+"','"+key+"')");
							rs.execute("insert into uf_msjlb_dt1(mainid,type,title,description,calcScoreType,scoreType,needReason,subjectstitle,subjectsdescription,"
									+ "subjectsresult,subjectsoptions,itemskey)values('"+id+"','"+type+"','"+title+"','"+description+"','"+calcScoreType+"',"
									+ "'"+scoreType+"','"+needReason+"','"+subjectstitle+"','"+subjectsdescription+"','"+subjectsresult+"','"+subjectsoptions+"','"+key+"')");
							
						}
					}else{
						basebean.writeLog("[2:-->]"+"insert into uf_msjlb_dt1(mainid,type,title,description,calcScoreType,scoreType,needReason,subjectstitle,subjectsdescription,"
								+ "subjectsresult,itemskey)values('"+id+"','"+type+"','"+title+"','"+description+"','"+calcScoreType+"',"
								+ "'"+scoreType+"','"+needReason+"','"+subjectstitle+"','"+subjectsdescription+"','"+subjectsresult+"','"+key+"')");
						rs.execute("insert into uf_msjlb_dt1(mainid,type,title,description,calcScoreType,scoreType,needReason,subjectstitle,subjectsdescription,"
								+ "subjectsresult,itemskey)values('"+id+"','"+type+"','"+title+"','"+description+"','"+calcScoreType+"',"
								+ "'"+scoreType+"','"+needReason+"','"+subjectstitle+"','"+subjectsdescription+"','"+subjectsresult+"','"+key+"')");
					}
				}
			}
		}
    }
    public static String ScreenName(String Englishname){
    	String name = "";
    	switch (Englishname) {
		case "applicationId":
			name="申请id";
			break;
		case "interviewerName":
			name="面试官名字";
			break;
		case "interviewerEmail":
			name="面试官邮箱";
			break;
		case "resultName":
			name="面试反馈结果";
			break;
		case "resultType":
			name="面试反馈结果类型";
			break;
		case "interviewType":
			name="面试类型";
			break;
		case "round":
			name="面试轮次";
			break;
		case "feedback":
			name="面试反馈内容";
			break;
		case "updatedAt":
			name="最近更新时间";
			break;
		default:
			break;
		}
    	return name;
    }
    public static String ScreenName2(String Englishname){
    	String name="";
    	switch (Englishname) {
		case "type":
			name="题目类型";
			break;
		case "title":
			name="题目标题";
			break;
		case "description":
			name="题目描述";
			break;
		case "calcScoreType":
			name="计算分数规则";
			break;
		case "scoreType":
		    name="分制";
		    break;
		case "needReason":
			name="是否需要打分综述";
			break;
		default:
			break;
		}
    	return name;
    }
    public static String ScreenName3(String Englishname){
    	String name="";
    	switch (Englishname) {
		case "title":
			name="小题标题";
			break;
		case "description":
            name="小题描述";
            break;
		case "result":
			name="小题结果";
			break;
		default:
			break;
		}
    	return name;
    }
    public static Integer ScreenName4(String Chinaname){
    	Integer number=-1;
    	switch (Chinaname) {
		case "电话面试":
			number=0;
			break;
		case "现场面试":
			number=1;
			break;
		case "集体面试":
			number=2;
			break;
		case "视频面试":
			number=3;
			break;
		default:
			break;
		}
    	return number;
    }
	public static String getDateString(String type, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateTime = sdf.format(data);
		return dateTime;
	}
}
