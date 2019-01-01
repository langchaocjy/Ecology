package com.hr.enums;

public enum HcStatusEnum {

	UNSTART(0, "unstart","未进行"),
	ONGING(1, "ongoing","进行中"),
	COMPLETE(2,"complete","已完成"),
	SUSPEND(3,"suspend","已暂停"),
	CANCELED(4,"canceled","已取消"),
	TIMEOUT(5,"timeout","已超期");
	
	private Integer key;
	private String value;
	private String message;
	
	private HcStatusEnum(Integer key, String value, String message) {
		this.key = key;
		this.value = value;
		this.message = message;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
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
	
	public static String getMkValue(int key){
		if(key == HcStatusEnum.UNSTART.getKey()){
			return HcStatusEnum.UNSTART.getValue();
		}else if (key == HcStatusEnum.ONGING.getKey()) {
			return HcStatusEnum.ONGING.getValue();
		}else if(key == HcStatusEnum.COMPLETE.getKey()){
			return HcStatusEnum.COMPLETE.getValue();
		}else if (key == HcStatusEnum.SUSPEND.getKey()) {
			return HcStatusEnum.SUSPEND.getValue();
		}else if (key == HcStatusEnum.CANCELED.getKey()) {
			return HcStatusEnum.CANCELED.getValue();
		}else{
			return HcStatusEnum.TIMEOUT.getValue();
		}
	}
}
