package com.fdrj.services.wsbasedataqueryfacade.client;


public class BudgetResultDTO {

	/**
	 * Ԥ��������Ŀ
	 */
	private String bgItem;
	/**
	 * ��֯
	 */
	private String orgUnit;
	/**
	 * Ԥ��Ҫ��
	 */
	private String element;
	/**
	 * Ԥ���ڼ�
	 */
	private String period;
	/**
	 * ����Ԥ���
	 */
	private String budget;
	/**
	 * ʵ�ʷ�����
	 */
	private String actual;
	/**
	 * Ԥ���ڼ����
	 */
	private String periodNum;
	/**
	 * ����Ԥ��
	 */
	private String balance;

	public String getBgItem() {
		return bgItem;
	}

	public void setBgItem(String bgItem) {
		this.bgItem = bgItem;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}
