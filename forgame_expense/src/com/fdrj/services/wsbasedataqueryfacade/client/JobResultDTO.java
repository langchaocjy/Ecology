package com.fdrj.services.wsbasedataqueryfacade.client;

public class JobResultDTO {

	/**
	 * ְλ���
	 */
	private String number;
	/**
	 * ְλ
	 */
	private String name;

	/**
	 * �������ű���
	 */
	private String adminOrgUnitNumber;
	/**
	 * �������ų�����
	 */
	private String adminOrgUnitLongNumber;
	/**
	 * ��������
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
