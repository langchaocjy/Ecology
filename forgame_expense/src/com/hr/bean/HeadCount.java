package com.hr.bean;

/**
 * HC bean
 * @author fengguosen
 *
 */
public class HeadCount {

	private String number;  //hc编号，编号全局唯一，且不可修改，
	
	private String jobName; // hc名称，必填
	
	private Integer needNumber; //需求人数，必填
	
	private String commitment ; //职位属性 ,参考枚举CommimentEnum
	
	private String status; // 进行状态 ,参考枚举HcStatusEnum
	
	private String startDate; //开始时间。日期格式为：ISO8601
	
	private String completeDate; // 结束时间。日期格式为：ISO8601
	
	private String description; //需求描述，html字符串

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getNeedNumber() {
		return needNumber;
	}

	public void setNeedNumber(Integer needNumber) {
		this.needNumber = needNumber;
	}

	public String getCommitment() {
		return commitment;
	}

	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
