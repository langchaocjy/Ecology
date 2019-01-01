package com.fdrj.services.wsbasedataqueryfacade.client;

public class DepartmentResultDTO {
	/**
	 * 部门编号
	 */
	private String number;
	/**
	 * 部门
	 */
	private String name;
	/**
	 * 部门长编码
	 */
	private String longNumber;
	/**
	 * 封存标识
	 */
	private String sealUp;
	/**
	 * 上级部门编码
	 */
	private String parentNumber;
	/**
	 * 上级部门
	 */
	private String parentName;

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

	public String getLongNumber() {
		return longNumber;
	}

	public void setLongNumber(String longNumber) {
		this.longNumber = longNumber;
	}

	public String getSealUp() {
		return sealUp;
	}

	public void setSealUp(String sealUp) {
		this.sealUp = sealUp;
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

}
