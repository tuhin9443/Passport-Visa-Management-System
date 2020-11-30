package com.project.dto;

public class UserDTO {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String dob;
	private String citizenType;
	private String address;
	private String contact;
	private String email;
	private String qualification;
	private String gender;
	private String applyType;
	private String hintQuestion;
	private String hintAnswer;
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCitizenType() {
		return citizenType;
	}
	public void setCitizenType(String citizenType) {
		this.citizenType = citizenType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDTO(String userId, String firstName, String lastName, String dob,
			String citizenType, String address, String contact, String email,
			String qualification, String gender, String applyType,
			String hintQuestion, String hintAnswer, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.citizenType = citizenType;
		this.address = address;
		this.contact = contact;
		this.email = email;
		this.qualification = qualification;
		this.gender = gender;
		this.applyType = applyType;
		this.hintQuestion = hintQuestion;
		this.hintAnswer = hintAnswer;
		this.password = password;
	}
	public UserDTO() {
		super();
	
	}
	
	
	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName
				+ ", lastName=" + lastName + ", dob=" + dob + ", citizenType="
				+ citizenType + ", address=" + address + ", contact=" + contact
				+ ", email=" + email + ", qualification=" + qualification
				+ ", gender=" + gender + ", applyType=" + applyType
				+ ", hintQuestion=" + hintQuestion + ", hintAnswer="
				+ hintAnswer + "]";
	}
	
	
	

}