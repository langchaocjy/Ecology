package com.fdrj.services.wsbasedataqueryfacade.client;

public class ReturnBudgetRequest {
	
	/**
	 * µ¥ºÅ
	 */
	private String bizType;
	
	/**
	 * µ¥¾İ±àÂë
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
