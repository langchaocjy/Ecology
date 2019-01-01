package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.ArrayList;
import java.util.List;

public class PaymentBillRequest {

	/**
	 * 单据编号
	 */
	private String number;

	/**
	 * 单据名称
	 */
	private String name;

	/**
	 * 公司编码
	 */
	private String companyNumber;
	
	private String departmentNumber;

	/**
	 * 单据日期
	 */
	private String bizDate;

	/**
	 * 付款类型编码，采购付款默认201，报销付款默认999
	 */
	private String payTypeNumber;

	/**
	 * 付款方式，默认赊购（004）
	 */
	private String paymentTypeNumber;

	/**
	 * 币别
	 */
	private String currencyTypeNumber;

	/**
	 * 实付金额合计（分录实付金额合计）
	 */
	private String actPayAmt;

	/**
	 * 汇率
	 */
	private String exchangeRate;

	/**
	 * 折本位币
	 */
	private String actPayLocAmt;

	/**
	 * 付款科目
	 */
	// private String payerAccountNumber;
	/**
	 * 结算方式，默认电汇（02）
	 */
	private String settlementTypeNumber;

	/**
	 * 往来户类型（00004-公司；00001-客户；00002-供应商），采购付款默认00002，报销付款默认00004
	 */
	private String payeeType;

	/**
	 * 往来户编号
	 */
	private String payeeNumber;

	/**
	 * 付款单类型（0-报销付款；1-采购付款）
	 */
	private String sourceBillType;

	/**
	 * 收款人地区
	 */
	private String countryNumber;

	/**
	 * 制单人账号
	 */
	private String creatorNumber;
    private String acctBankName;
    private String acctBankNumber;
    
	private List<PaymentBillEntryDTO> entryList;
    
	private String adminOrgUnitNumber;
	
	public String getAdminOrgUnitNumber(){
		return adminOrgUnitNumber;
	}
	
	public void setAdminOrgUnitNumber(String adminOrgUnitNumber){
		this.adminOrgUnitNumber = adminOrgUnitNumber;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getBizDate() {
		return bizDate;
	}

	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	public String getPayTypeNumber() {
		return payTypeNumber;
	}

	public void setPayTypeNumber(String payTypeNumber) {
		this.payTypeNumber = payTypeNumber;
	}

	public String getPaymentTypeNumber() {
		return paymentTypeNumber;
	}

	public void setPaymentTypeNumber(String paymentTypeNumber) {
		this.paymentTypeNumber = paymentTypeNumber;
	}

	public String getCurrencyTypeNumber() {
		return currencyTypeNumber;
	}

	public void setCurrencyTypeNumber(String currencyTypeNumber) {
		this.currencyTypeNumber = currencyTypeNumber;
	}

	public String getActPayAmt() {
		return actPayAmt;
	}

	public void setActPayAmt(String actPayAmt) {
		this.actPayAmt = actPayAmt;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getActPayLocAmt() {
		return actPayLocAmt;
	}

	public void setActPayLocAmt(String actPayLocAmt) {
		this.actPayLocAmt = actPayLocAmt;
	}

	public String getSettlementTypeNumber() {
		return settlementTypeNumber;
	}

	public void setSettlementTypeNumber(String settlementTypeNumber) {
		this.settlementTypeNumber = settlementTypeNumber;
	}

	public String getPayeeType() {
		return payeeType;
	}

	public void setPayeeType(String payeeType) {
		this.payeeType = payeeType;
	}

	public String getPayeeNumber() {
		return payeeNumber;
	}

	public void setPayeeNumber(String payeeNumber) {
		this.payeeNumber = payeeNumber;
	}

	public String getSourceBillType() {
		return sourceBillType;
	}

	public void setSourceBillType(String sourceBillType) {
		this.sourceBillType = sourceBillType;
	}

	public String getCreatorNumber() {
		return creatorNumber;
	}

	public void setCreatorNumber(String creatorNumber) {
		this.creatorNumber = creatorNumber;
	}

	public List<PaymentBillEntryDTO> getEntryList() {
		return entryList == null ? new ArrayList<PaymentBillEntryDTO>()
				: entryList;
	}

	public void setEntryList(List<PaymentBillEntryDTO> entryList) {
		this.entryList = entryList;
	}

	public String getCountryNumber() {
		return countryNumber;
	}

	public void setCountryNumber(String countryNumber) {
		this.countryNumber = countryNumber;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getAcctBankName() {
		return acctBankName;
	}

	public void setAcctBankName(String acctBankName) {
		this.acctBankName = acctBankName;
	}

	public String getAcctBankNumber() {
		return acctBankNumber;
	}

	public void setAcctBankNumber(String acctBankNumber) {
		this.acctBankNumber = acctBankNumber;
	}
	
	

}
