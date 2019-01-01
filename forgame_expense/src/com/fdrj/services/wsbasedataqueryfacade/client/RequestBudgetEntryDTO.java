package com.fdrj.services.wsbasedataqueryfacade.client;

public class RequestBudgetEntryDTO {

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

	/**
	 * 明细汇率
	 */
	private String exchangeRate;

	/**
	 * 支付金额
	 */
	private String amount;

	/**
	 * 支付本位币金额
	 */
	private String amountOri;

	public String getExpenseTypeNumber() {
		return expenseTypeNumber;
	}

	public void setExpenseTypeNumber(String expenseTypeNumber) {
		this.expenseTypeNumber = expenseTypeNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountOri() {
		return amountOri;
	}

	public void setAmountOri(String amountOri) {
		this.amountOri = amountOri;
	}

	public String getReqBizDate() {
		return reqBizDate;
	}

	public void setReqBizDate(String reqBizDate) {
		this.reqBizDate = reqBizDate;
	}

	public String getCurrencyTypeNumber() {
		return currencyTypeNumber;
	}

	public void setCurrencyTypeNumber(String currencyTypeNumber) {
		this.currencyTypeNumber = currencyTypeNumber;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
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

}
