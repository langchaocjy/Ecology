package com.fdrj.services.wsbasedataqueryfacade.client;

public class BudgetEntryDTO {

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
