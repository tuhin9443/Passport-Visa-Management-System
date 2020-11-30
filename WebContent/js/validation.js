function validateForm() {

	var fname = document.forms["myForm"]["firstname"].value;

	var letters = /^[A-Za-z]+$/;
	if (!fname.match(letters)) {
		document.getElementById("errorSpan").innerHTML = "First name must have alphabets only";
		return false;
	}
	
	var sname = document.forms["myForm"]["lastname"].value;

	if (!sname.match(letters)) {
		document.getElementById("errorSpan").innerHTML = "Last name must have alphabets only";
		return false;
	}

	
	var contact = document.forms["myForm"]["contact"].value;
	var number = /^[0-9]+$/;
	if (!contact.match(number)) {
		document.getElementById("errorSpan").innerHTML = "Contact number must have digits only";
		return false;
	}

	var email = document.forms["myForm"]["email"].value;
	var atposition = email.indexOf("@");
	var dotposition = email.lastIndexOf(".");
	if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length) {
		document.getElementById("errorSpan").innerHTML = "Enter a valid email id";
		return false;
	}
	
}

function validateApplyPassport() {

	
	var pin = document.forms["myForm"]["pin"].value;
	var pattern=/^\d{6}$/;
	if (!pattern.test(pin)) {
		document.getElementById("errorSpan").innerHTML = "Enter a valid pin";
		return false;
	}
}

function validateApplyVisa() {

	
	var passportId = document.forms["myForm"]["passportId"].value;
	var alphanumeric=/^\(?(FPS)\)?[-]?([0-9]{6})/;
	if (!passportId.match(alphanumeric)) {
		document.getElementById("errorSpan").innerHTML = "Enter a valid passport number";
		return false;
	}

}

function validatePassportReissue() {
	
	
	var pin= document.forms["myForm"]["pin"].value;
	var pattern=/^\d{6}$/;
	if (!pattern.test(pin)) {
		document.getElementById("errorSpan").innerHTML = "Enter a valid pin";
		return false;
	}

	
}

function validateVisaCancel() {


	
	var passportId = document.forms["myForm"]["passportId"].value;
	var alphanumeric=/^\(?(FPS)\)?[-]?([0-9]{6})/;
	if (!passportId.match(alphanumeric)) {
		document.getElementById("errorSpan").innerHTML = "Enter a valid passport number";
		return false;
	}
	
	var visaId = document.forms["myForm"]["visaId"].value;
	var student = /^\(?(STU)\)?[-]?([0-9]{4})/;
	var PrivateEmployee = /^\(?(PE)\)?[-]?([0-9]{4})/;
	var govEmployee = /^\(?(GE)\)?[-]?([0-9]{4})/;
	var selfEmployee = /^\(?(SE)\)?[-]?([0-9]{4})/;
	var retiredEmployee = /^\(?(RE)\)?[-]?([0-9]{4})/;
	if ((!visaId.match(student))&&(!visaId.match(PrivateEmployee))&&(!visaId.match(govEmployee))&&(!visaId.match(selfEmployee))&&(!visaId.match(retiredEmployee))) {
		document.getElementById("errorSpan").innerHTML = "Enter a valid visa number";
		return false;
	}
}