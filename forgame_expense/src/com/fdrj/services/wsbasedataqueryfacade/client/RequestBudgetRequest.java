package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;

public class RequestBudgetRequest {
	
	/**
	 * ����
	 */
	private String bizType;
	
	/**
	 * ����
	 */
	private String number;

	/**
	 * ��˾����
	 */
	private String orgUnitNumber;

	/**
	 * ���ű���
	 */
	private String departmentNumber;

	/**
	 * ҵ������
	 */
	private String bizDate;

	/**
	 * �ұ����
	 */
	private String currencyTypeNumber;

	/**
	 * ����
	 */
	private String exchangeRate;

	/**
	 * ������
	 */
	private String personNumber;

	/**
	 * �����˶�ӦEAS�˺�
	 */
	private String userNumber;

	/**
	 * �����˲���
	 */
	private String personDeptNumber;

	/**
	 * ������Ŀ����
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
