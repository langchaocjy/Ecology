package com.hr.bean;

import java.math.BigDecimal;

public class ForgameSalayTemplate{

	/**
	 * 正常工作时间工资
	 */
	private BigDecimal standardSalary;  //月薪
	
	private BigDecimal baseSalary;	//基本工资
	
	private BigDecimal stationSalary; //岗位工资
	
	private BigDecimal performanceSalary; //绩效工资
	
	/**
	 * 补助性工资
	 */
	private BigDecimal fullServiceSubsidy; //全勤奖
	
	private BigDecimal mealSubsidy; //餐费补贴
	
	private BigDecimal otherSubsidy; //其他补贴
	
	/**
	 * 加项
	 */
	private BigDecimal reward; //考核奖金
	
	private BigDecimal overtimePayment; //加班费
	
	private BigDecimal otherPayment; //其他补款
	
	/**
	 * 减项
	 */
	private BigDecimal leaveCut; //请假
	
	private BigDecimal lateCut; //迟到
	
	private BigDecimal otherCut; //其他扣款
	
	private BigDecimal planSalary; //应发工资
	
	private BigDecimal socialSecurity; //社保
	
	private BigDecimal accumulationFund; //公积金
	
	private BigDecimal otherAdditionalCut; //其他专项附加扣除
	
	private BigDecimal personalTax; //个税
	
	private BigDecimal realSalary; //实发工资
	
	private BigDecimal compensatePayment; //赔偿金
	
	private BigDecimal enterAccount; //入账合计
	
	private BigDecimal servicePayment; //服务费

	public BigDecimal getStandardSalary() {
		return standardSalary;
	}

	public void setStandardSalary(BigDecimal standardSalary) {
		this.standardSalary = standardSalary;
	}

	public BigDecimal getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}

	public BigDecimal getStationSalary() {
		return stationSalary;
	}

	public void setStationSalary(BigDecimal stationSalary) {
		this.stationSalary = stationSalary;
	}

	public BigDecimal getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(BigDecimal performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public BigDecimal getFullServiceSubsidy() {
		return fullServiceSubsidy;
	}

	public void setFullServiceSubsidy(BigDecimal fullServiceSubsidy) {
		this.fullServiceSubsidy = fullServiceSubsidy;
	}

	public BigDecimal getMealSubsidy() {
		return mealSubsidy;
	}

	public void setMealSubsidy(BigDecimal mealSubsidy) {
		this.mealSubsidy = mealSubsidy;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public BigDecimal getOvertimePayment() {
		return overtimePayment;
	}

	public void setOvertimePayment(BigDecimal overtimePayment) {
		this.overtimePayment = overtimePayment;
	}

	public BigDecimal getOtherPayment() {
		return otherPayment;
	}

	public void setOtherPayment(BigDecimal otherPayment) {
		this.otherPayment = otherPayment;
	}

	public BigDecimal getLeaveCut() {
		return leaveCut;
	}

	public void setLeaveCut(BigDecimal leaveCut) {
		this.leaveCut = leaveCut;
	}

	public BigDecimal getLateCut() {
		return lateCut;
	}

	public void setLateCut(BigDecimal lateCut) {
		this.lateCut = lateCut;
	}

	public BigDecimal getOtherCut() {
		return otherCut;
	}

	public void setOtherCut(BigDecimal otherCut) {
		this.otherCut = otherCut;
	}

	public BigDecimal getPlanSalary() {
		return planSalary;
	}

	public void setPlanSalary(BigDecimal planSalary) {
		this.planSalary = planSalary;
	}

	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public BigDecimal getAccumulationFund() {
		return accumulationFund;
	}

	public void setAccumulationFund(BigDecimal accumulationFund) {
		this.accumulationFund = accumulationFund;
	}

	public BigDecimal getOtherAdditionalCut() {
		return otherAdditionalCut;
	}

	public void setOtherAdditionalCut(BigDecimal otherAdditionalCut) {
		this.otherAdditionalCut = otherAdditionalCut;
	}

	public BigDecimal getPersonalTax() {
		return personalTax;
	}

	public void setPersonalTax(BigDecimal personalTax) {
		this.personalTax = personalTax;
	}

	public BigDecimal getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(BigDecimal realSalary) {
		this.realSalary = realSalary;
	}

	public BigDecimal getCompensatePayment() {
		return compensatePayment;
	}

	public void setCompensatePayment(BigDecimal compensatePayment) {
		this.compensatePayment = compensatePayment;
	}

	public BigDecimal getEnterAccount() {
		return enterAccount;
	}

	public void setEnterAccount(BigDecimal enterAccount) {
		this.enterAccount = enterAccount;
	}

	public BigDecimal getServicePayment() {
		return servicePayment;
	}

	public void setServicePayment(BigDecimal servicePayment) {
		this.servicePayment = servicePayment;
	}
	
	
}
