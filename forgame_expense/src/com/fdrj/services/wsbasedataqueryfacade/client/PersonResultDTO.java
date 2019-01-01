package com.fdrj.services.wsbasedataqueryfacade.client;

public class PersonResultDTO {
	/**
	 * 员工编号
	 */
	private String number;
	/**
	 * 员工姓名
	 */
	private String name;
	/**
	 * 直接上级编号(多个用,拼接)
	 */
	private String parentNumber;
	/**
	 * 直接上级姓名(多个用,拼接)
	 */
	private String parentName;
	/**
	 * 员工状态编码 1-正式员工 2-试用员工 3-停薪留职 4-出国 5-下岗 6-待岗 7-长期病假 9-辞职 10-辞退 11-退休 12-离休
	 * 13- 返聘 20-开除 21-解聘 30-失踪 31-死亡
	 */
	private String employeeTypeNumber;
	/**
	 * 员工状态
	 */
	private String employeeTypeName;
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
	/**
	 * 性别 1-男 2-女
	 */
	private String gender;
	/**
	 * 生日(yyyy-MM-dd)
	 */
	private String birthday;
	/**
	 * email
	 */
	private String email;
	/**
	 * 公司电话
	 */
	private String officePhone;
	/**
	 * 手机
	 */
	private String cell;
	/**
	 * 职位编码
	 */
	private String positionNumber;
	/**
	 * 职位
	 */
	private String positionName;
	/**
	 * 登陆账号
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
