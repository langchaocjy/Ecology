package com.hr.util;


import java.math.BigDecimal;


import com.alibaba.fastjson.JSON;
import com.hr.bean.ForgameSalayTemplate;
import com.hr.bean.JlcSalaryTemplate;

public class SalayCalculator {

	private static BigDecimal TAXTHRESHOLD = new BigDecimal(5000); //税收起征点
	
	
	public static ForgameSalayTemplate getForgameSalay(ForgameSalayTemplate template) throws Exception{//fedong 
		if(template==null){
			throw new Exception("工资模块为空");
		}
		BigDecimal standardSalary= template.getStandardSalary();
		if(standardSalary==null || standardSalary.compareTo(new BigDecimal(0))<=0){
			throw new Exception("月薪总额不能为空");
		}
		BigDecimal baseSalary = standardSalary.multiply(new BigDecimal(0.4)).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal stationSalary = standardSalary.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal performanceSalary = standardSalary.subtract(baseSalary).subtract(stationSalary);
		template.setBaseSalary(baseSalary); //基本工资
		template.setStationSalary(stationSalary); //绩效工资
		template.setPerformanceSalary(performanceSalary); //绩效工资
		
		/**
		 * 补助性工资
		 */
		BigDecimal fullServiceSubsidy = template.getFullServiceSubsidy()==null? new BigDecimal(0):template.getFullServiceSubsidy();
		BigDecimal mealSubsidy = template.getMealSubsidy()==null? new BigDecimal(0):template.getMealSubsidy();
		BigDecimal otherSubsidy = template.getOtherSubsidy()==null? new BigDecimal(0):template.getOtherSubsidy();
		BigDecimal subsidy = fullServiceSubsidy.add(mealSubsidy).add(otherSubsidy);
		
		/**
		 * 加项
		 */
		BigDecimal reward = template.getReward()==null?new BigDecimal(0):template.getReward();
		BigDecimal overtimePayment = template.getOvertimePayment()==null?new BigDecimal(0):template.getOvertimePayment();
		BigDecimal otherPayment = template.getOtherPayment()==null?new BigDecimal(0):template.getOtherPayment();
		BigDecimal addition = reward.add(overtimePayment).add(otherPayment);
		
		/**
		 * 减项
		 */
		BigDecimal leaveCut = template.getLeaveCut()==null?new BigDecimal(0):template.getLeaveCut();
		BigDecimal lateCut = template.getLateCut()==null?new BigDecimal(0):template.getLateCut();
		BigDecimal otherCut = template.getOtherCut()==null?new BigDecimal(0):template.getOtherCut();
		BigDecimal cut = leaveCut.add(lateCut).add(otherCut);
		//应发工资
		BigDecimal planSalary = standardSalary.add(subsidy).add(addition).subtract(cut).setScale(2, BigDecimal.ROUND_HALF_UP);
		template.setPlanSalary(planSalary);
		
		/**
		 * 应扣款
		 */
		BigDecimal socialSecurity = template.getSocialSecurity()==null?new BigDecimal(0):template.getSocialSecurity();
		BigDecimal accumulationFund = template.getAccumulationFund()==null?new BigDecimal(0):template.getAccumulationFund();
		BigDecimal otherAdditionalCut = template.getOtherAdditionalCut()==null?new BigDecimal(0):template.getOtherAdditionalCut();
		
		BigDecimal taxableIncome = planSalary.subtract(socialSecurity).subtract(accumulationFund)
									 .subtract(otherAdditionalCut).subtract(TAXTHRESHOLD); //应税工资
		
		BigDecimal personalTax = getTax(taxableIncome);
		template.setPersonalTax(personalTax);
		BigDecimal realSalary = planSalary.subtract(socialSecurity).subtract(accumulationFund).subtract(personalTax).setScale(2, BigDecimal.ROUND_HALF_UP);
		template.setRealSalary(realSalary);
		
		BigDecimal compensatePayment = template.getCompensatePayment()==null?new BigDecimal(0):template.getCompensatePayment();
		BigDecimal enterAccount = realSalary.add(compensatePayment);
		template.setEnterAccount(enterAccount);
		return template;
		
	}
	
	
	/**
	 * 获取简理财工资模板
	 * @param template
	 * @return
	 * @throws Exception
	 */
	public static JlcSalaryTemplate getJlcSalary(JlcSalaryTemplate template) throws Exception{
		if(template==null){
			throw new Exception("工资模块为空");
		}
		BigDecimal standardSalary= template.getStandardSalary();
		if(standardSalary==null || standardSalary.compareTo(new BigDecimal(0))<=0){
			throw new Exception("月薪总额不能为空");
		}
		BigDecimal baseSalary = standardSalary.multiply(new BigDecimal(0.4)).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal stationSalary = standardSalary.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal performanceSalary = standardSalary.subtract(baseSalary).subtract(stationSalary);
		template.setBaseSalary(baseSalary); //基本工资
		template.setStationSalary(stationSalary); //绩效工资
		template.setPerformanceSalary(performanceSalary); //绩效工资
		
		BigDecimal computerSubsidy = template.getComputerSubsidy()==null? new BigDecimal(0):template.getComputerSubsidy();
		BigDecimal mealSubsidy = template.getMealSubsidy()==null? new BigDecimal(0):template.getMealSubsidy();
		BigDecimal businessSubsidy = template.getBusinessSubsidy()==null? new BigDecimal(0):template.getBusinessSubsidy();
		BigDecimal subsidy = computerSubsidy.add(mealSubsidy).add(businessSubsidy);
		
		/**
		 * 加项
		 */
		BigDecimal reward = template.getReward()==null?new BigDecimal(0):template.getReward();
		BigDecimal overtimePayment = template.getOvertimePayment()==null?new BigDecimal(0):template.getOvertimePayment();
		BigDecimal otherPayment = template.getOtherPayment()==null?new BigDecimal(0):template.getOtherPayment();
		BigDecimal addition = reward.add(overtimePayment).add(otherPayment);
		
		/**
		 * 减项
		 */
		BigDecimal absenceCut = template.getAbsenceCut()==null?new BigDecimal(0):template.getAbsenceCut();
		BigDecimal sickCut = template.getSickCut()==null?new BigDecimal(0):template.getSickCut();
		BigDecimal otherCut = template.getOtherCut()==null?new BigDecimal(0):template.getOtherCut();
		BigDecimal cut = absenceCut.add(sickCut).add(otherCut);
		//应发工资
		BigDecimal planSalary = standardSalary.add(subsidy).add(addition).subtract(cut);
		template.setPlanSalary(planSalary);
		
		/**
		 * 应扣款
		 */
		BigDecimal socialSecurity = template.getSocialSecurity()==null?new BigDecimal(0):template.getSocialSecurity();
		BigDecimal accumulationFund = template.getAccumulationFund()==null?new BigDecimal(0):template.getAccumulationFund();
		BigDecimal otherAdditionalCut = template.getOtherAdditionalCut()==null?new BigDecimal(0):template.getOtherAdditionalCut();
		
		BigDecimal taxableIncome = planSalary.subtract(socialSecurity).subtract(accumulationFund)
									 .subtract(otherAdditionalCut).subtract(TAXTHRESHOLD); //应税工资
		
		BigDecimal personalTax = getTax(taxableIncome);
		template.setPersonalTax(personalTax);
		BigDecimal realSalary = planSalary.subtract(socialSecurity).subtract(accumulationFund).subtract(personalTax).setScale(2, BigDecimal.ROUND_HALF_UP);
		template.setRealSalary(realSalary);
		BigDecimal quitPayment = template.getQuitPayment()==null?new BigDecimal(0):template.getQuitPayment();
		BigDecimal enterAccount = realSalary.add(quitPayment);
		template.setEnterAccount(enterAccount);
		return template;
	}
	
	/**
	 * 获取个人所得税
	 * @param taxableIncome
	 * @return
	 */
	public static BigDecimal getTax(BigDecimal taxableIncome){
		if(taxableIncome.compareTo(new BigDecimal(0))<=0){
			return new BigDecimal(0);
		}
		if(taxableIncome.compareTo(new BigDecimal(3000))<=0){
			return taxableIncome.multiply(new BigDecimal(0.03)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(taxableIncome.compareTo(new BigDecimal(3000))==1 && taxableIncome.compareTo(new BigDecimal(12000))<=0){
			return taxableIncome.multiply(new BigDecimal(0.1)).subtract(new BigDecimal(210)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(taxableIncome.compareTo(new BigDecimal(12000))==1 && taxableIncome.compareTo(new BigDecimal(25000))<=0){
			return taxableIncome.multiply(new BigDecimal(0.2)).subtract(new BigDecimal(1410)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(taxableIncome.compareTo(new BigDecimal(25000))==1 && taxableIncome.compareTo(new BigDecimal(35000))<=0){
			return taxableIncome.multiply(new BigDecimal(0.25)).subtract(new BigDecimal(2660)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(taxableIncome.compareTo(new BigDecimal(35000))==1 && taxableIncome.compareTo(new BigDecimal(55000))<=0){
			return taxableIncome.multiply(new BigDecimal(0.3)).subtract(new BigDecimal(4410)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(taxableIncome.compareTo(new BigDecimal(55000))==1 && taxableIncome.compareTo(new BigDecimal(80000))<=0){
			return taxableIncome.multiply(new BigDecimal(0.35)).subtract(new BigDecimal(7160)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else{
			return taxableIncome.multiply(new BigDecimal(0.45)).subtract(new BigDecimal(15160)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
	}
	
	public static void main(String[] args) {
		JlcSalaryTemplate jlcSalaryTemplate = new JlcSalaryTemplate();
		jlcSalaryTemplate.setStandardSalary(new BigDecimal(5000));
		jlcSalaryTemplate.setMealSubsidy(new BigDecimal(370));
		jlcSalaryTemplate.setReward(new BigDecimal(100));
		jlcSalaryTemplate.setAbsenceCut(new BigDecimal(233));
		jlcSalaryTemplate.setSocialSecurity(new BigDecimal("424.6"));
		jlcSalaryTemplate.setAccumulationFund(new BigDecimal(1200));
		jlcSalaryTemplate.setOtherAdditionalCut(new BigDecimal(1000));
		try {
			jlcSalaryTemplate = getJlcSalary(jlcSalaryTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSON(jlcSalaryTemplate) );

		
		ForgameSalayTemplate forgameSalayTemplate = new ForgameSalayTemplate();
		forgameSalayTemplate.setStandardSalary(new BigDecimal(4000));
		forgameSalayTemplate.setMealSubsidy(new BigDecimal(360));
//		forgameSalayTemplate.setReward(new BigDecimal(100));
//		forgameSalayTemplate.setAbsenceCut(new BigDecimal(233));
		
		forgameSalayTemplate.setSocialSecurity(new BigDecimal("426.62"));
		forgameSalayTemplate.setAccumulationFund(new BigDecimal(480));
//		forgameSalayTemplate.setOtherAdditionalCut(new BigDecimal(1000));
		try {
			getForgameSalay(forgameSalayTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSON(forgameSalayTemplate) );
	}
}
