package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;

public class UpdatePaymentBillRequest {

	/**
	 * 单据编号
	 */
	private String number;

	/**
	 * 付款单类型（0-报销付款；1-采购付款）
	 */
	private String sourceBillType;

	/**
	 * 审核人账号
	 */
	private String auditorNumber;
	
	/**
	 * 审核时间
	 */
	private String auditDate;

	/**
	 * 分录序列号列表
	 */
	private List<String> seqList;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSourceBillType() {
		return sourceBillType;
	}

	public void setSourceBillType(String sourceBillType) {
		this.sourceBillType = sourceBillType;
	}

	public String getAuditorNumber() {
		return auditorNumber;
	}

	public void setAuditorNumber(String auditorNumber) {
		this.auditorNumber = auditorNumber;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public List<String> getSeqList() {
		return seqList;
	}

	public void setSeqList(List<String> seqList) {
		this.seqList = seqList;
	}
	
	

}
