<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String state = request.getParameter("stateName");

		if (state.equalsIgnoreCase("s1")) {
	%>
	<select name="cityName" id="cityName">
		<option value="">Select</option>
		<option value="c1">Bhubaneswar</option>
		<option value="c2">Cuttack</option>
		<option value="c3">Jajpur</option>

	</select>
	<!-- //<input type="text" name="rating" id="rating" value="8" /> -->
	<%
		}

		if (state.equalsIgnoreCase("s2")) {
	%>
	<select name="cityName" id="cityName">
		<option value="">Select</option>
		<option value="c1">Kolkata</option>
		<option value="c2">Howrah</option>


	</select>

	<%
		}
		if (state.equalsIgnoreCase("s3")) {
	%>
	<select name="cityName" id="cityName">
		<option value="">Select</option>
		<option value="c1">Patna</option>
		<option value="c2">Gaya</option>

	</select>

	<%
		}
		if (state.equalsIgnoreCase("s4")) {
	%>
	<select name="cityName" id="cityName">
		<option value="">Select</option>
		<option value="c1">Kochi</option>
		<option value="c2">Kayamkulam</option>


	</select>

	<%
		}
		if (state.equalsIgnoreCase("s5")) {
	%>
	<select name="cityName" id="cityName">
		<option value="">Select</option>
		<option value="c1">Hyderabad</option>
		<option value="c2">Bellampalle</option>

	</select>



	<%
		}
	%>

</body>
</html>