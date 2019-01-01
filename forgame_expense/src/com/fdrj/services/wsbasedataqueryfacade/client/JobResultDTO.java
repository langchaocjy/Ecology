package com.fdrj.services.wsbasedataqueryfacade.client;

public class JobResultDTO {

	/**
	 * 职位编号
	 */
	private String number;
	/**
	 * 职位
	 */
	private String name;

	/**
	 * 所属部门编码
	 */
	private String adminOrgUnitNumber;
	/**
	 * 所属部门长编码
	 */
	private String adminOrgUnitLongNumber;
	/**
	 * 所属部门
	 */
	private String adminOrgUnitName;

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

	public String getAdminOrgUnitNumber() {
		return adminOrgUnitNumber;
	}

	public void setAdminOrgUnitNumber(String adminOrgUnitNumber) {
		this.adminOrgUnitNumber = adminOrgUnitNumber;
	}

	public String getAdminOrgUnitName() {
		return adminOrgUnitName;
	}

	public void setAdminOrgUnitName(String adminOrgUnitName) {
		this.adminOrgUnitName = adminOrgUnitName;
	}

	public String getAdminOrgUnitLongNumber() {
		return adminOrgUnitLongNumber;
	}

	public void setAdminOrgUnitLongNumber(String adminOrgUnitLongNumber) {
		this.adminOrgUnitLongNumber = adminOrgUnitLongNumber;
	}

}
