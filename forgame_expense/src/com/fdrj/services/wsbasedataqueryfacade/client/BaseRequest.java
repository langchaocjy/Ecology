package com.fdrj.services.wsbasedataqueryfacade.client;

public class BaseRequest {

	/**
	 * ���ű���
	 */
	private String orgUnitLongNumber;
	/**
	 * ��ʼʱ��(yyyy-MM-dd)
	 */
	private String beginDate;

	/**
	 * ����ʱ��(yyyy-MM-dd)
	 */
	private String endDate;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrgUnitLongNumber() {
		return orgUnitLongNumber;
	}

	public void setOrgUnitLongNumber(String orgUnitLongNumber) {
		this.orgUnitLongNumber = orgUnitLongNumber;
	}

}
