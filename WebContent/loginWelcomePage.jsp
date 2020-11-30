<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    color: black;">logout</a>
			<div id="nav">
				<iframe src="http://free.timeanddate.com/clock/i3zo86jr/n54/tlin/fs11/tct/pct/ftbu/bas2/pa10/tt0/tw1/tm1/th1/ta1" style="border:1px double #286E87;border-radius:10px;float:right;" 
					frameborder="0" width="186" height="38"  allowTransparency="true">
				</iframe>
					<ul>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="applyPassport.jsp" >Apply Passport</a></li>
					<li><a href="applyVisa.jsp" >Apply Visa</a></li>
					<li><a href="passportReissue.jsp" >Reissue</a></li>
				        <li><a href="userValidationVisaCancel.jsp" >Cancel Visa</a></li>
					</ul>
			</div>
		</div>
	</div>
	
	


<div id="gallery">

	<a href="#" class="show">
		<img src="images/slider/slider1.jpg" alt="image1" width="970" height="350" title="" alt="" rel="<h3>P3</h3>P4"/>
	</a>
	
	<a href="#" class="show">
		<img src="images/slider/slider2.jpg" alt="image2" width="970" height="350" title="" alt="" rel="<h3>P1</h3>P2"/>
	</a>
    <a href="#" class="show">
		<img src="images/slider/slider3.jpg" alt="image3" width="970" height="350" title="" alt="" rel="<h3>P5</h3>P6"/>
	</a>
	<a href="#" class="show">
		<img src="images/slider/slider4.jpg" alt="image4" width="970" height="350" title="" alt="" rel="<h3>P7</h3>P8"/>
	</a>
	
		
</div>
<div class="content1">

<div id="about" style="background-color:	
#286E87;text-align:left;overflow:auto;margin-top:10px;border-radius:10px;border:2px double #286E87;">
<table border="2" 
style="float:absolute"
cellpadding="100" 
cellspacing="20"
background color="#286E87">

<h1 style="text-align:center;color:#29999;font-size:20px;text-align:center;float:absolute;margin:0px 0px 0px 0px;float:absolute;">NOTICES AND UPDATES</h1>
                       
  
  </tr></table>
<marquee  behavior="scroll" 
direction="up" scrollamount="03" scrolldelay="0" onmouseover="this.setAttribute('scrollamount', 0, 0);" onmouseout="this.setAttribute('scrollamount', 3, 0);">
 <font color="white">
 
 <p align="center">&nbsp;</p>
 <li>Aadhaar Integration with Passport Seva (an update)<br></li>
 <p align="center">&nbsp;</p>
 <li>Ministry of External Affairs further liberalizes Police Verification procedure for passport issuance and launches a Mobile App to cut delays in submission of Police Verification Report</li>
 <p align="center">&nbsp;</p>
 <li>Caution for Public</li>
 <p align="center">&nbsp;</p>
 <li>Information of Lost , Stolen and Revoked Indian Passports is being shared with INTERPOL by MEA. Any person using such Passport is liable to be prosecuted. </li>
 <p align="center">&nbsp;</p>
 <li>Passport India E-Book</li>
 <p align="center">&nbsp;</p>
 <li>Gazette Notification - Mandatory requirement of Passport for Seamen before departing from India</li>
 <p align="center">&nbsp;</p>
 <li>Simplifying passport application process: Self-attestation of documents</li>
 <p align="center">&nbsp;</p>
 <li>Birth Certificate Exemption for Orphan/Abandoned Children</li>
 <p align="center">&nbsp;</p>

 </marquee>
</div>


<div id="news"style="float:left;width:330px;height:288px;padding:5px;background-color:#286E87; border-radius:10px;margin-top:10px;font-family:arial;">
<font color="white">
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
<p align="center">&nbsp;</p>
 <p>            
	<h4 align=center>Welcome User</h4>
	</br>
	<h4 align=center>The functionalities are enabled now.....</h4>
	</br>
	<h4 align=center>Kindly choose your desired service.!</h4>
 </p>

 </font>
</div>
</div>


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

