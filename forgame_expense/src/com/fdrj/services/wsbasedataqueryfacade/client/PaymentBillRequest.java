package com.fdrj.services.wsbasedataqueryfacade.client;

import java.util.ArrayList;
import java.util.List;

public class PaymentBillRequest {

	/**
	 * ���ݱ��
	 */
	private String number;

	/**
	 * ��������
	 */
	private String name;

	/**
	 * ��˾����
	 */
	private String companyNumber;
	
	private String departmentNumber;

	/**
	 * ��������
	 */
	private String bizDate;

	/**
	 * �������ͱ��룬�ɹ�����Ĭ��201����������Ĭ��999
	 */
	private String payTypeNumber;

	/**
	 * ���ʽ��Ĭ���޹���004��
	 */
	private String paymentTypeNumber;

	/**
	 * �ұ�
	 */
	private String currencyTypeNumber;

	/**
	 * ʵ�����ϼƣ���¼ʵ�����ϼƣ�
	 */
	private String actPayAmt;

	/**
	 * ����
	 */
	private String exchangeRate;

	/**
	 * �۱�λ��
	 */
	private String actPayLocAmt;

	/**
	 * �����Ŀ
	 */
	// private String payerAccountNumber;
	/**
	 * ���㷽ʽ��Ĭ�ϵ�㣨02��
	 */
	private String settlementTypeNumber;

	/**
	 * ���������ͣ�00004-��˾��00001-�ͻ���00002-��Ӧ�̣����ɹ�����Ĭ��00002����������Ĭ��00004
	 */
	private String payeeType;

	/**
	 * ���������
	 */
	private String payeeNumber;

	/**
	 * ������ͣ�0-�������1-�ɹ����
	 */
	private String sourceBillType;

	/**
	 * �տ��˵���
	 */
	private String countryNumber;

	/**
	 * �Ƶ����˺�
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
