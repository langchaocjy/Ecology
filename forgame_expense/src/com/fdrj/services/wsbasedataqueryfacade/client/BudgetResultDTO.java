package com.fdrj.services.wsbasedataqueryfacade.client;


public class BudgetResultDTO {

	/**
	 * 预算申请项目
	 */
	private String bgItem;
	/**
	 * 组织
	 */
	private String orgUnit;
	/**
	 * 预算要素
	 */
	private String element;
	/**
	 * 预算期间
	 */
	private String period;
	/**
	 * 本期预算额
	 */
	private String budget;
	/**
	 * 实际发生额
	 */
	private String actual;
	/**
	 * 预算期间编码
	 */
	private String periodNum;
	/**
	 * 可用预算
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
