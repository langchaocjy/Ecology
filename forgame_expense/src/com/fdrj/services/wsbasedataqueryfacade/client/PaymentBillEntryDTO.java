package com.fdrj.services.wsbasedataqueryfacade.client;

public class PaymentBillEntryDTO {
	
	/**
	 * ��ϸ���ݵڼ��У����ڲ�ʱ���ɶ�����
	 */
	private String seq;
	
	/**
	 * 
	 */
	private String companyNumber;
	
	/**
	 * 
	 */
	private String departmentNumber;
	
	
	/**
	 * �������ͣ�����������
	 */
	private String expenseTypeNumber;
	
	/**
	 * ƽ̨���
	 */
	private String platformNumber;
	
	/**
	 * ���ϱ���
	 */
	private String materialNumber;
	
	/**
	 * ������
	 */
	private String amount;
	
	/**
	 * ���λ�ҽ��
	 */
	private String localAmt;
	
	/**
	 * ��ע
	 */
	private String remark;
    
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

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

	public String getLocalAmt() {
		return localAmt;
	}

	public void setLocalAmt(String localAmt) {
		this.localAmt = localAmt;
	}

	public String getPlatformNumber() {
		return platformNumber;
	}

	public void setPlatformNumber(String platformNumber) {
		this.platformNumber = platformNumber;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
