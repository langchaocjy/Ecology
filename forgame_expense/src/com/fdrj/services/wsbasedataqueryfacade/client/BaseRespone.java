package com.fdrj.services.wsbasedataqueryfacade.client;


public class BaseRespone {

	/**
	 * 返回状态码：0-成功；1-失败
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
