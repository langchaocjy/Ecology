package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;

public class RequestBudgetRequest {
	
	/**
	 * 单号
	 */
	private String bizType;
	
	/**
	 * 单号
	 */
	private String number;

	/**
	 * 公司编码
	 */
	private String orgUnitNumber;

	/**
	 * 部门编码
	 */
	private String departmentNumber;

	/**
	 * 业务日期
	 */
	private String bizDate;

	/**
	 * 币别编码
	 */
	private String currencyTypeNumber;

	/**
	 * 汇率
	 */
	private String exchangeRate;

	/**
	 * 报销人
	 */
	private String personNumber;

	/**
	 * 报销人对应EAS账号
	 */
	private String userNumber;

	/**
	 * 申请人部门
	 */
	private String personDeptNumber;

	/**
	 * 费用项目编码
	 */
	private List<RequestBudgetEntryDTO> entryList;

	public String getOrgUnitNumber() {
		return orgUnitNumber;
	}

	public void setOrgUnitNumber(String orgUnitNumber) {
		this.orgUnitNumber = orgUnitNumber;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getCurrencyTypeNumber() {
		return currencyTypeNumber;
	}

	public void setCurrencyTypeNumber(String currencyTypeNumber) {
		this.currencyTypeNumber = currencyTypeNumber;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getPersonDeptNumber() {
		return personDeptNumber;
	}

	public void setPersonDeptNumber(String personDeptNumber) {
		this.personDeptNumber = personDeptNumber;
	}

	public List<RequestBudgetEntryDTO> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<RequestBudgetEntryDTO> entryList) {
		this.entryList = entryList;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
	

}
