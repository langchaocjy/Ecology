package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.ArrayList;
import java.util.List;

public class BudgetRequest {
	
	/**
	 * 业务类型
	 */
	private String bizType;

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
	 * 费用项目编码
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
