package com.fdrj.services.wsbasedataqueryfacade.client;

public class RequestBudgetEntryDTO {

	/**
	 * ������Ŀ����
	 */
	private String expenseTypeNumber;

	/**
	 * ҵ��������
	 */
	private String reqBizDate;

	/**
	 * ��˾����
	 */
	private String companyNumber;

	/**
	 * ���ű���
	 */
	private String departmentNumber;

	/**
	 * ��ϸ�ұ�
	 */
	private String currencyTypeNumber;

	/**
	 * ��ϸ����
	 */
	private String exchangeRate;

	/**
	 * ֧�����
	 */
	private String amount;

	/**
	 * ֧����λ�ҽ��
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
