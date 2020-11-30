package com.project.dto;


public class VisaDTO {
	private String loginId;
	private String passportId;
	private String country;
	private String visaDateOfApplication;
	private int visaAmount;
	private String visaId;
	private String visaExpiryDate;
	private String Remarks;
	private String hintQuestion;
	private String hintAnswer;
	private String visaIssueDate;
	private int cancellationAmount;
	private String passportExpiryDate;
	private String occupation;
	private String visaStatus;
	private String option;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassportId() {
		return passportId;
	}
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getVisaDateOfApplication() {
		return visaDateOfApplication;
	}
	public void setVisaDateOfApplication(String visaDateOfApplication) {
		this.visaDateOfApplication = visaDateOfApplication;
	}
	public int getVisaAmount() {
		return visaAmount;
	}
	public void setVisaAmount(int visaAmount) {
		this.visaAmount = visaAmount;
	}
	public String getVisaId() {
		return visaId;
	}
	public void setVisaId(String visaId) {
		this.visaId = visaId;
	}
	public String getVisaExpiryDate() {
		return visaExpiryDate;
	}
	public void setVisaExpiryDate(String visaExpiryDate) {
		this.visaExpiryDate = visaExpiryDate;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getHintQuestion() {
		return hintQuestion;
	}
	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}
	public String getHintAnswer() {
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}
	public String getVisaIssueDate() {
		return visaIssueDate;
	}
	public void setVisaIssueDate(String visaIssueDate) {
		this.visaIssueDate = visaIssueDate;
	}
	public int getCancellationAmount() {
		return cancellationAmount;
	}
	public void setCancellationAmount(int cancellationAmount) {
		this.cancellationAmount = cancellationAmount;
	}
	public String getPassportExpiryDate() {
		return passportExpiryDate;
	}
	public void setPassportExpiryDate(String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public VisaDTO(String loginId, String passportId, String country,
			String visaDateOfApplication, int visaAmount, String visaId,
			String visaExpiryDate, String remarks, String hintQuestion,
			String hintAnswer, String visaIssueDate, int cancellationAmount,
			String passportExpiryDate, String occupation) {
		super();
		this.loginId = loginId;
		this.passportId = passportId;
		this.country = country;
		this.visaDateOfApplication = visaDateOfApplication;
		this.visaAmount = visaAmount;
		this.visaId = visaId;
		this.visaExpiryDate = visaExpiryDate;
		Remarks = remarks;
		this.hintQuestion = hintQuestion;
		this.hintAnswer = hintAnswer;
		this.visaIssueDate = visaIssueDate;
		this.cancellationAmount = cancellationAmount;
		this.passportExpiryDate = passportExpiryDate;
		this.occupation = occupation;
	}
	public VisaDTO() {
		super();
		
	}
	public String getVisaStatus() {
		return visaStatus;
	}
	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}
	@Override
	public String toString() {
		return "VisaDTO [loginId=" + loginId + ", passportId=" + passportId
				+ ", country=" + country + ", visaDateOfApplication="
				+ visaDateOfApplication + ", visaAmount=" + visaAmount
				+ ", visaId=" + visaId + ", visaExpiryDate=" + visaExpiryDate
				+ ", Remarks=" + Remarks + ", hintQuestion=" + hintQuestion
				+ ", hintAnswer=" + hintAnswer + ", visaIssueDate="
				+ visaIssueDate + ", cancellationAmount=" + cancellationAmount
				+ ", passportExpiryDate=" + passportExpiryDate
				+ ", occupation=" + occupation + ", visaStatus=" + visaStatus
				+ "]";
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}

	
	
}
