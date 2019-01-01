package com.fdrj.services.wsbasedataqueryfacade.client;

public class PaymentBillEntryDTO {
	
	/**
	 * 明细数据第几行，用于拆单时生成订单号
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
	 * 费用类型（报销付款必填）
	 */
	private String expenseTypeNumber;
	
	/**
	 * 平台编号
	 */
	private String platformNumber;
	
	/**
	 * 物料编码
	 */
	private String materialNumber;
	
	/**
	 * 付款金额
	 */
	private String amount;
	
	/**
	 * 付款本位币金额
	 */
	private String localAmt;
	
	/**
	 * 备注
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
