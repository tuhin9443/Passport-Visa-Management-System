<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<style>
a:link {color:#CCCCCC;}    /* unvisited link */
a:visited {color:#CCCCCC;} /* visited link */
a:hover {color:#999999;}   /* mouse over link */
a:active {color:#CCCCCC;}  /* selected link */
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Apply Passport</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css" />

</head>


</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Passport Visa Management System</title>
<link href="CSS/style.css" rel="stylesheet" type="text/css" />
<link href="CSS/pulse-photo-news.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
<script type="text/javascript" src="js/pulse-photo-news.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script>
	function getXMLHTTP() {
		var xmlhttp = false;
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e1) {
					xmlhttp = false;
				}
			}
		}

		return xmlhttp;
	}
	function show() {

		var strURL = "city.jsp?stateName="
				+ document.getElementById("stateName").value;

		var req = getXMLHTTP();

		if (req) {
			req.onreadystatechange = function() {

				
				if (req.readyState == 4) {
					
					if (req.status == 200) {						
						document.getElementById('statediv').innerHTML = req.responseText;
						
					} else {
						alert("There was a problem while using XMLHTTP:\n"
								+ req.statusText);
					}
				}
			};
			req.open("GET", strURL, true);
			req.send();
		}
	}
</script>

</head>

<body>
<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
	<jsp:useBean id="sdf" class="java.text.SimpleDateFormat"></jsp:useBean>
	${sdf.applyPattern("yyyy-MM-dd")}
<div align="center">
	<div class="container">
		<div class="top">
			<div class="header">
				<div id="logo"> <a href="index.html"><img src="images/logo.jpg" width="107" height="106" /></a> 
				</div>
				<div class="cap">
					<h1><font color="BLACK">ABROAD_INDIA</font></h1>
						
					
					<h4><p><font color="286E87">Passport & Visa Services</font></p></h4>
					<h1><font color="green">Welcome,<%=session.getAttribute("LOGIN_ID")+""%></font></h1>
				</div>
				<a href="logout" "margin-left:100px"="" style="
    margin-left: 500px;
 	margin-top:20px;
    color: black;
">logout</a>
			<div id="nav">
				<iframe src="http://free.timeanddate.com/clock/i3zo86jr/n54/tlin/fs11/tct/pct/ftbu/bas2/pa10/tt0/tw1/tm1/th1/ta1" style="border:1px double #286E87;border-radius:10px;float:right;" 
					frameborder="0" width="186" height="38"  allowTransparency="true">
				</iframe>
					<ul>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="applyPassport.jsp" class="disabled">Apply Passport</a></li>
					<li><a href="applyVisa.jsp">Apply Visa</a></li>
					<li><a href="passportReissue.jsp">Reissue</a></li>
				        <li><a href="visaCancel.jsp" >Cancel Visa</a></li>
					</ul>
			</div>
		</div>
	</div>


			 <%
    String LoginID =null;
	RequestDispatcher requestDispatcher=null;
	HttpSession session1 = request.getSession(false);
	if (session1.getAttribute("LOGIN_ID") != null) {
		LoginID = session1.getAttribute("LOGIN_ID") + "";
	} else {
		requestDispatcher = request.getRequestDispatcher("login1.jsp");
		requestDispatcher.forward(request, response);

	}%>

<div class="content2">
<div id="about" style="margin-left: 135px;background-color:#D6D6D6;text-align:left;overflow:auto;margin-top:10px;border-radius:10px;border:2px double #286E87;">
<form name="myForm" action="apply" onsubmit="return validateApplyPassport()" method="post">
<table width="70%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
 
  <%-- <td> Welcome <%=session.getAttribute("LOGIN_ID")+""%></td> --%>
  </tr>
  <tr>
    <td colspan="2" align="center" height=120><h1 style="text-align:center;color:#338080;font-size:40px;text-align:center;float:absolute;margin:0px 0px 0px 155px;float:absolute;text-shadow:5px 5px 5px #9A9A9A;">Apply Passport  </h1></td>
  </tr>
<tr><td></td></tr>
  <tr>
  <span id="errorSpan" style="color: red;"></span>
    <td height=50 width=400 style="text-align:left;padding-left:89px; ">User Id<r> * </r></td>
    <td>
      <input type="text" 
	        name="userid" id="userid"
	        maxlength="14" required /> 
    </td>
  </tr>
  <tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Country<r> * </r></td>
								<td><select name="country" >
										
										<option value="india">INDIA</option>
								</select></td>
							</tr>
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">State<r> * </r></td>
								<td><select name="stateName" id="stateName" onchange="show()"
									required>
										<option value="">Select</option>
										<option value="s1">Orissa</option>
										<option value="s2">West Bengal</option>
										<option value="s3">Bihar</option>
										<option value="s4">Kerala</option>
										<option value="s5">Andhra Pradesh</option>
								</select></td>
							</tr>
							<tr>
								<td height =50 width=200
									style="text-align:left;padding-left:89px;">City<r> * </r>
								</td>
								<td>
									<div id="statediv">
										<select name="cityName" >
											<option value="-1">Select Option</option>
										</select><br /> 
									</div></td>
							</tr>

 <tr>
    <td height=50 width=200 style="text-align:left;padding-left:89px;">Pin<r> * </r></td>
    <td>
     <input type="text" name="pin" id="pin" maxlength="10" required>
    </td>
  </tr>
 
 
 
 <tr>
    <td height=50 width=200 style="text-align:left;padding-left:89px;">Type Of Service<r> * </r></td>
    <td>
   		<input type="radio" name="servicetype" id="servicetype" value="Normal" required>
		Normal &nbsp;
		<input type="radio" name="servicetype" id="servicetype" value="Tatkal">
		Tatkal &nbsp;
    </td>
  </tr>
 
 
 
 
 <tr>
    <td height=50 width=200 style="text-align:left;padding-left:89px;">Booklet Type<r> * </r></td>
    <td>
  <input type="radio" name="bookletType" id="bookletType" value="30P" required>
		30 Pages &nbsp;
		<input type="radio" name="bookletType" value="60P">
		60 Pages &nbsp;
    </td>
  </tr>
							<tr>
							
							<tr>
								<td height=50 width=200
									style="text-align: left; padding-left: 89px;">Issue Date<r>
									* </r>
								</td>
								<c:set var="minDate" value="${sdf.format(now)}"></c:set>

								<td><input type="date" id="issueDate" name="issueDate"
									min="${minDate}" required></td>
							</tr>
							</tr>

							<tr>
  <td height=50 width=200 align="center"></td>
    <td>
     <input type="hidden" value="applyPassport" name="option"/>
    <input type="Submit" value="Register" style="width: 84px;
    height: 34px;border-radius: 9px;
    background-color: #ccdfd0;">
		<input type="Reset" value="Reset" style="width: 84px;
    height: 34px;border-radius: 9px;
    background-color: #ccdfd0;">
    </td>
  </tr>
</table>
</form>


</div></div>
<div align="center" id="footer" style="background:rgba(255,255,255,0.9);clear:both;text-align:center;width:980px;height:130px; float:left; margin-top:20px;">

<div style="width:940px; height:90px; background-color:#286E87; text-align:center; margin:10px 0px 0px 10px; padding:10px; clear:both; color:#FFF;">
<br/>
Room-7(Carbuncle), B1 building, Candor(Unitech-Infospace)


<br/>
 <br>Kolkata-700156
    
  </div>
  
</div>
</div>
</div>

</body>
</html>