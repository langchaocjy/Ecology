package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.List;

public class UpdatePaymentBillRequest {

	/**
	 * ���ݱ��
	 */
	private String number;

	/**
	 * ������ͣ�0-�������1-�ɹ����
	 */
	private String sourceBillType;

	/**
	 * ������˺�
	 */
	private String auditorNumber;
	
	/**
	 * ���ʱ��
	 */
	private String auditDate;

	/**
	 * ��¼���к��б�
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
