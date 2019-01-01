package com.fdrj.services.wsbasedataqueryfacade.client;

public class BudgetEntryDTO {

	/**
	 * 费用项目编码
	 */
	private String expenseTypeNumber;

	/**
	 * 业务发生日期
	 */
	private String reqBizDate;

	/**
	 * 公司编码
	 */
	private String companyNumber;

	/**
	 * 部门编码
	 */
	private String departmentNumber;

	/**
	 * 明细币别
	 */
	private String currencyTypeNumber;

	public String getExpenseTypeNumber() {
		return expenseTypeNumber;
	}

	public void setExpenseTypeNumber(String expenseTypeNumber) {
		this.expenseTypeNumber = expenseTypeNumber;
	}

	public String getReqBizDate() {
		return reqBizDate;
	}

	public void setReqBizDate(String reqBizDate) {
		this.reqBizDate = reqBizDate;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getCurrencyTypeNumber() {
		return currencyTypeNumber;
	}

	public void setCurrencyTypeNumber(String currencyTypeNumber) {
		this.currencyTypeNumber = currencyTypeNumber;
	}

}
