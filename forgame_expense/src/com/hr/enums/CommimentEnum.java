package com.hr.enums;

public enum CommimentEnum {
	PARTTIME(0,"parttime", "兼职"),
	FULLTIME(1,"fulltime", "全职"),
	INTERN(2,"intern","实习");
	
	private Integer oaValue;
	private String value;
	private String message;
	
	private CommimentEnum(Integer oaValue, String value, String message) {
		this.oaValue = oaValue;
		this.value = value;
		this.message = message;
	}
	
	public Integer getOaValue() {
		return oaValue;
	}

	public void setOaValue(Integer oaValue) {
		this.oaValue = oaValue;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static String getMkValue(int oaValue){
		if(oaValue == CommimentEnum.PARTTIME.getOaValue()){
			return CommimentEnum.PARTTIME.getValue();
		}else if (oaValue == CommimentEnum.FULLTIME.getOaValue()) {
			return CommimentEnum.FULLTIME.getValue();
		}else{
			return CommimentEnum.INTERN.getValue();
		}
	}
}
