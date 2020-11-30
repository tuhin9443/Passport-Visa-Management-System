package com.project.dto;

public class PassportDTO{
	
	private String passportId;
	private String remarks;
	private String country;
	private String state;
	private String city;
	private String pin;
	private String serviceType;
	private int bookletType;
	private String issueDate;
	private String expiryDate;
	private String passportAmount;
	private String applicationStatus;
	private String loginId;
	private String option;
	public String getPassportId() {
		return passportId;
	}
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public int getBookletType() {
		return bookletType;
	}
	public void setBookletType(int bookletType) {
		this.bookletType = bookletType;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getPassportAmount() {
		return passportAmount;
	}
	public void setPassportAmount(String passportAmount) {
		this.passportAmount = passportAmount;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public PassportDTO(String passportId, String remarks, String country,
			String state, String city, String pin, String serviceType,
			int bookletType, String issueDate, String expiryDate,
			String passportAmount, String applicationStatus, String loginId) {
		super();
		this.passportId = passportId;
		this.remarks = remarks;
		this.country = country;
		this.state = state;
		this.city = city;
		this.pin = pin;
		this.serviceType = serviceType;
		this.bookletType = bookletType;
		this.issueDate = issueDate;
		this.expiryDate = expiryDate;
		this.passportAmount = passportAmount;
		this.applicationStatus = applicationStatus;
		this.loginId = loginId;
	}
	public PassportDTO() {
		super();
		
	}
	@Override
	public String toString() {
		return "PassportDTO [passportId=" + passportId + ", remarks=" + remarks
				+ ", country=" + country + ", state=" + state + ", city="
				+ city + ", pin=" + pin + ", serviceType=" + serviceType
				+ ", bookletType=" + bookletType + ", issueDate=" + issueDate
				+ ", expiryDate=" + expiryDate + ", passportAmount="
				+ passportAmount + ", applicationStatus=" + applicationStatus
				+ ", loginId=" + loginId + "]";
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}

	
	
}
