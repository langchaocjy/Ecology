package com.hr.bean;

import java.util.ArrayList;

public class Interview {
    private String resultnmae;//面试反馈结果
    private String resulttype;//面试反馈结果类型
    private String round;//面试轮次
    private String interviewername;//面试官名字
    private String intervieweremail;//面试官邮箱
    private String interviewtype;//面试类型
    private String feedback;//面试反馈内容
    private String intervieweename;//面试人姓名
    private String status;//面试状态
	private ArrayList<detailResult> drlist;
	public String getResultnmae() {
		return resultnmae;
	}
	public void setResultnmae(String resultnmae) {
		this.resultnmae = resultnmae;
	}
	public String getResulttype() {
		return resulttype;
	}
	public void setResulttype(String resulttype) {
		this.resulttype = resulttype;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}

	public ArrayList<detailResult> getDrlist() {
		return drlist;
	}
	public void setDrlist(ArrayList<detailResult> drlist) {
		this.drlist = drlist;
	}
	public String getInterviewername() {
		return interviewername;
	}
	public void setInterviewername(String interviewername) {
		this.interviewername = interviewername;
	}
	public String getIntervieweremail() {
		return intervieweremail;
	}
	public void setIntervieweremail(String intervieweremail) {
		this.intervieweremail = intervieweremail;
	}
	public String getInterviewtype() {
		return interviewtype;
	}
	public void setInterviewtype(String interviewtype) {
		this.interviewtype = interviewtype;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getIntervieweename() {
		return intervieweename;
	}
	public void setIntervieweename(String intervieweename) {
		this.intervieweename = intervieweename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
