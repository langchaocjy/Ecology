package com.fdrj.services.wsbasedataqueryfacade.client;

public class DepartmentResultDTO {
	/**
	 * ���ű��
	 */
	private String number;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ���ų�����
	 */
	private String longNumber;
	/**
	 * ����ʶ
	 */
	private String sealUp;
	/**
	 * �ϼ����ű���
	 */
	private String parentNumber;
	/**
	 * �ϼ�����
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
