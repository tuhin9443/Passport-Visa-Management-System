<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Registration</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css" />
<link href="CSS/pulse-photo-news.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
<script type="text/javascript" src="js/pulse-photo-news.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
</head>

<body>

	<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
	<jsp:useBean id="sdf" class="java.text.SimpleDateFormat"></jsp:useBean>
	${sdf.applyPattern("yyyy-MM-dd")}
	<div align="center">
		<div class="container">
			<div class="top">
				<div class="header">
					<div id="logo">
						<a href="index.html"><img src="images/logo.jpg" width="107"
							height="106" /> </a>
					</div>
					<div class="cap">
						<h1>
							<font color="BLACK">ABROAD_INDIA</font>
						</h1>


						<h4>
							<p>
								<font color="286E87">Passport & Visa Services</font>
							</p>
						</h4>
					</div>
					<div id="nav">
						<iframe
							src="http://free.timeanddate.com/clock/i3zo86jr/n54/tlin/fs11/tct/pct/ftbu/bas2/pa10/tt0/tw1/tm1/th1/ta1"
							style="border: 1px double #286E87; border-radius: 10px; float: right;"
							frameborder="0" width="186" height="38" allowTransparency="true">
						</iframe>
		<ul>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="applyPassport.jsp" class="disabled">Apply Passport</a></li>
					<li><a href="applyVisa.jsp" class="disabled">Apply Visa</a></li>
					<li><a href="passportReissue.jsp" class="disabled">Reissue</a></li>
				        <li><a href="visaCancel.jsp" class="disabled">Cancel Visa</a></li>
					</ul>
					</div>
				</div>
			</div>





			<div class="content2">
				<div id="about"
					style="margin-left: 135px; background-color: #D6D6D6; text-align: left; overflow: auto; margin-top: 5px; border-radius: 10px; border: 2px double #286E87;">
					  <form name="myForm" action="register" onsubmit="return validateForm()" method="post">
						<table width="70%" border="0" cellpadding="1" cellspacing="0"
							align="center">
							<tr>
								<td colspan="2" align="center" cellpadding="2" height=100><h1
										style="text-align: center; color: #338080; font-size: 40px; text-align: center; float: absolute; margin: 0px 0px 0px 150px; float: absolute; text-shadow: 5px 5px 5px #9A9A9A;">User
										Registration</h1>
								</td>
							</tr>
							
							<tr>

								<td></td>
							</tr>
							<tr>
								<td></td>
							</tr>
							
							<tr>
							<span id="errorSpan" style="color: red;"></span>
								<td height=50 width=400
									style="text-align: left; padding-left: 89px;">First Name<r> * </r></td>
								<td><input type="text" name="firstname" id="firstname"
									maxlength="15" required></td>
								<td colspan="2" align="center" cellpadding="" height=50>
								</td>
							</tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Surname<r> * </r></td>
								<td><input type="text" name="lastname" id="lastname"
									maxlength=" 15" required></td>
							</tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Date Of
									Birth<r> * </r></td>
								<c:set var="maxDate" value="${sdf.format(now)}"></c:set>

								<td><input type="date" id="date" name="date"
									max="${maxDate}" required>
								</td>
							</tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Address<r> * </r></td>
								<td><input type="text" name="address" id="address"
									maxlength="100" required></td>
							</tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Contact
									Number<r> * </r></td>
								<td><input type="digit" name="contact" id="contact"
									placeholder="+91-" maxlength="10" required></td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Email
									Address<r> * </r></td>
								<td><input type="text" autocomplete="false" name="email" id="email" required>
								</td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Qualification<r> * </r></td>
								<td><select name="qualification" id="qualification" required>
										<option value="">Select</option>
										<option value="UG">Under Graduate</option>
										<option value="G">Graduate</option>
										<option value="PG">Post Graduate</option>
								</select></td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px; ">Gender<r> * </r></td>
								<td><input type="radio" name="gender" id="gender" required
									value="male" > Male &nbsp; <input type="radio"
									name="gender" id="gender" value="female"> Female</td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px; ">Apply Type<r> * </r></td>
								<td><input type="radio" name="applytype" id="applytype" value="passport">
									Passport &nbsp; <input type="radio" name="applytype" id="applytype" 
									value="visa" required> Visa</td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px; ">Hint
									Question<r> * </r></td>
								<td><select name="hintquestion"  id="hintquestion" required >
										<option value="" >Select</option>

										<option value="A" >Who is my best friend ?</option>
										<option value="B">Who is my mentor ?</option>
										<option value="C">Which is my favorite english movies
											series ?</option>

										<option value="D">Which is the most boring hindi
											daily soap ?</option>
								</select></td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;" >Hint Answer<r> * </r></td>
								<td><input type="password" name="hintanswer" id="name=" hintanswer"" required></td>
							</tr>
							<tr>
								<td height=50 width=200 align="center"></td>
								<td><input type="submit" name="submit" value="Register"
									style="width: 84px; height: 34px; border-radius: 9px; background-color: #ccdfd0;">
									<input type="Reset" value="Reset"
									style="width: 84px; height: 34px; border-radius: 9px; background-color: #ccdfd0;">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

			<div align="center" id="footer"
				style="background: rgba(255, 255, 255, 0.9); clear: both; text-align: center; width: 980px; height: 130px; float: left; margin-top: 20px;">

				<div
					style="width: 940px; height: 90px; background-color: #286E87; text-align: center; margin: 10px 0px 0px 10px; padding: 10px; clear: both; color: #FFF;">
					<br /> Room-7(Carbuncle), B1 building, Candor(Unitech-Infospace) <br />
					<br>Kolkata-700156

				</div>

			</div>
		</div>
	</div>

</body>
</html>