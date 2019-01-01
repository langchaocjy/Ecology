package com.tcss.hr;

import weaver.general.BaseBean;

/**
 * SAP信息
 * 
 * @author lianghui梁辉
 * @date 2017-11-14下午4:19:45
 */
public class SapPiInfo {

	public static String null2String(String s) {
		return s == null ? "" : s;
	}

	public static String getUrl_Hr() {
		String s = new BaseBean().getPropValue("tcsssaphr", "url_hr");
		System.out.println("url-->"+s);
		return null2String(s);
	}

	public static String getUsername_Hr() {
		String s = new BaseBean().getPropValue("tcsssaphr", "username_hr");
		System.out.println("user-->"+s);
		return null2String(s);
	}

	public static String getPassword_Hr() {
		String s = new BaseBean().getPropValue("tcsssaphr", "password_hr");
		System.out.println("password-->"+s);
		return null2String(s);
	}

	public static String getFrstdate_Hr() {
		String s = new BaseBean().getPropValue("tcsssaphr", "firstdate");
		return null2String(s);
	}

}
