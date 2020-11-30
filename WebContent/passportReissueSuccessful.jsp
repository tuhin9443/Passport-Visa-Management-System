<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.project.dto.*,com.project.dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<style>
a:link {
	color: #CCCCCC;
} /* unvisited link */
a:visited {
	color: #CCCCCC;
} /* visited link */
a:hover {
	color: #999999;
} /* mouse over link */
a:active {
	color: #CCCCCC;
} /* selected link */
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome User</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css" />
<link href="CSS/pulse-photo-news.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
<script type="text/javascript" src="js/pulse-photo-news.js"></script>
</head>

<body>

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
					<a href="logout" "margin-left:100px"="" style="
    margin-left: 500px;
 	margin-top:20px;
    color: black;
">logout</a>
					<div id="nav">
						<iframe
							src="http://free.timeanddate.com/clock/i3zo86jr/n54/tlin/fs11/tct/pct/ftbu/bas2/pa10/tt0/tw1/tm1/th1/ta1"
							style="border: 1px double #286E87; border-radius: 10px; float: right;"
							frameborder="0" width="186" height="38" allowTransparency="true">
						</iframe>
						<ul>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="applyPassport.jsp">Apply Passport</a></li>
					<li><a href="applyVisa.jsp">Apply Visa</a></li>
					<li><a href="passportReissue.jsp" class="disabled">Reissue</a></li>
				        <li><a href="userValidationVisaCancel.jsp">Cancel Visa</a></li>
					</ul>
					</div>
				</div>
			</div>




			<div id="gallery">

				<a href="#" class="show"> <img src="images/slider/slider1.jpg"
					alt="image1" width="970" height="350" title="" alt=""
					rel="<h3>P3</h3>P4" /> </a> <a href="#" class="show"> <img
					src="images/slider/slider2.jpg" alt="image2" width="970"
					height="350" title="" alt="" rel="<h3>P1</h3>P2" /> </a> <a href="#"
					class="show"> <img src="images/slider/slider3.jpg" alt="image3"
					width="970" height="350" title="" alt="" rel="<h3>P5</h3>P6" /> </a> <a
					href="#" class="show"> <img src="images/slider/slider4.jpg"
					alt="image4" width="970" height="350" title="" alt=""
					rel="<h3>P7</h3>P8" /> </a>
			</div>
<%
    String LoginID =null;
	RequestDispatcher requestDispatcher;
	HttpSession session1 = request.getSession(false);
	if (session1.getAttribute("LOGIN_ID") != null) {
		LoginID = session1.getAttribute("LOGIN_ID") + "";
	} else {
		requestDispatcher = request.getRequestDispatcher("login1.jsp");
		requestDispatcher.forward(request, response);

	}
		 PassportDTO passport=(PassportDTO)request.getAttribute("passport");

    %>
			<div class="content1">
				<div id="about"
					style="background-color: #D6D6D6; text-align: left; overflow: auto; margin-top: 10px; border-radius: 10px; border: 2px double #286E87; margin-left: 200px;">
					<div class="cap">
						<h1
							style="text-align: center; color: #338080; font-size: 20px; text-align: center; float: absolute; margin: 0px 0px 0px 0px; float: absolute; text-shadow: 5px 5px 5px #9A9A9A;">
							Re-issue Passport Successful!!</h1>

						<p align="center">&nbsp;</p>
						<table width="70%" border="0" cellpadding="0" cellspacing="0"
							align="center">
							<tr>
								<td height=50 width=400
									style="text-align: left; padding-left: 89px;"><h1
										style="text-align: center; color: green; font-size: 20px; text-align: center; float: absolute; margin: 0px 0px 0px 0px; float: absolute; text-shadow: 5px 5px 5px #9A9A9A;">Passport
										Id</h1></td>
								<td>
									<h1
										style="text-align: center; color: green; font-size: 15px; text-align: center; float: absolute; margin: 0px 0px 0px 0px; float: absolute;">
										<%=passport.getPassportId()%></h1>
								</td>
							</tr>
							<tr>
							<tr>
								<td height=50 width=400
									style="text-align: left; padding-left: 89px;"><h1
										style="text-align: center; color: green; font-size: 20px; text-align: center; float: absolute; margin: 0px 0px 0px 0px; float: absolute; text-shadow: 5px 5px 5px #9A9A9A;">Re-issue
										cost</h1></td>
								<td>
									<h1
										style="text-align: center; color: green; font-size: 15px; text-align: center; float: absolute; margin: 0px 0px 0px 0px; float: absolute;">
										<%=passport.getPassportAmount()%></h1>
								</td>
							</tr>
							<tr>
								<td height=50 width=200 align="center"></td>
								<td></td>
							</tr>
						</table>
					</div>
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