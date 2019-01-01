package com.hr.bean;

import java.util.ArrayList;

public class MokaDataPush {
   private String name;//姓名
   private String jobtitle;//ְ职位名称
   private String positiondep;//ְ职位所在部门
   private String phone;//联系电话
   private String email;//邮箱
   private String sex;//性别
   private String birthday;//出生年月
   private String nativeplace;//籍贯
   private String highesteducation;//最高学历
   private String maritalstatus;//婚姻状态
   private String referrername;//推荐人姓名
   private String reerreremail;//推荐人邮箱
   private String applicationid;//申请id
   private String updatedat;//最近更新时间
   private ArrayList<Interview> ivlist;
   
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJobtitle() {
	return jobtitle;
}
public void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
public String getNativeplace() {
	return nativeplace;
}
public void setNativeplace(String nativeplace) {
	this.nativeplace = nativeplace;
}
public String getPositiondep() {
	return positiondep;
}
public void setPositiondep(String positiondep) {
	this.positiondep = positiondep;
}
public String getHighesteducation() {
	return highesteducation;
}
public void setHighesteducation(String highesteducation) {
	this.highesteducation = highesteducation;
}
public String getMaritalstatus() {
	return maritalstatus;
}
public void setMaritalstatus(String maritalstatus) {
	this.maritalstatus = maritalstatus;
}
public String getReferrername() {
	return referrername;
}
public void setReferrername(String referrername) {
	this.referrername = referrername;
}
public String getReerreremail() {
	return reerreremail;
}
public void setReerreremail(String reerreremail) {
	this.reerreremail = reerreremail;
}
public String getApplicationid() {
	return applicationid;
}
public void setApplicationid(String applicationid) {
	this.applicationid = applicationid;
}
public String getUpdatedat() {
	return updatedat;
}
public void setUpdatedat(String updatedat) {
	this.updatedat = updatedat;
}
public ArrayList<Interview> getIvlist() {
	return ivlist;
}
public void setIvlist(ArrayList<Interview> ivlist) {
	this.ivlist = ivlist;
}

}
