package com.fdrj.services.wsbasedataqueryfacade.client;

public class ReturnBudgetRequest {
	
	/**
	 * ����
	 */
	private String bizType;
	
	/**
	 * ���ݱ���
	 */
	private String number;

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
