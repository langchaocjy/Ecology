package com.fdrj.services.wsbasedataqueryfacade.client;

public class PersonResultDTO {
	/**
	 * Ա�����
	 */
	private String number;
	/**
	 * Ա������
	 */
	private String name;
	/**
	 * ֱ���ϼ����(�����,ƴ��)
	 */
	private String parentNumber;
	/**
	 * ֱ���ϼ�����(�����,ƴ��)
	 */
	private String parentName;
	/**
	 * Ա��״̬���� 1-��ʽԱ�� 2-����Ա�� 3-ͣн��ְ 4-���� 5-�¸� 6-���� 7-���ڲ��� 9-��ְ 10-���� 11-���� 12-����
	 * 13- ��Ƹ 20-���� 21-��Ƹ 30-ʧ�� 31-����
	 */
	private String employeeTypeNumber;
	/**
	 * Ա��״̬
	 */
	private String employeeTypeName;
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
	/**
	 * �Ա� 1-�� 2-Ů
	 */
	private String gender;
	/**
	 * ����(yyyy-MM-dd)
	 */
	private String birthday;
	/**
	 * email
	 */
	private String email;
	/**
	 * ��˾�绰
	 */
	private String officePhone;
	/**
	 * �ֻ�
	 */
	private String cell;
	/**
	 * ְλ����
	 */
	private String positionNumber;
	/**
	 * ְλ
	 */
	private String positionName;
	/**
	 * ��½�˺�
	 */
	private String userNumber;

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

	public String getEmployeeTypeNumber() {
		return employeeTypeNumber;
	}

	public void setEmployeeTypeNumber(String employeeTypeNumber) {
		this.employeeTypeNumber = employeeTypeNumber;
	}

	public String getEmployeeTypeName() {
		return employeeTypeName;
	}

	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getParentNumber() {
		return parentNumber;
	}

	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getAdminOrgUnitLongNumber() {
		return adminOrgUnitLongNumber;
	}

	public void setAdminOrgUnitLongNumber(String adminOrgUnitLongNumber) {
		this.adminOrgUnitLongNumber = adminOrgUnitLongNumber;
	}

}
