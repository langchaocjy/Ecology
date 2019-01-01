package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.ArrayList;
import java.util.List;

public class BudgetRequest {
	
	/**
	 * ҵ������
	 */
	private String bizType;

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
	 * ������Ŀ����
	 */
	private List<BudgetEntryDTO> entryList;

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

	public List<BudgetEntryDTO> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<BudgetEntryDTO> entryList) {
		this.entryList = entryList;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
	
}
