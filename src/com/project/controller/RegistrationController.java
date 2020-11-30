package com.project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.project.dispatcher.RegisterDelegate;
import com.project.dto.UserDTO;
import com.project.exceptions.BusinessException;
import com.project.exceptions.DatabaseException;

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegistrationController.class);
	RequestDispatcher requestDispatcher = null;

	public RegistrationController() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("Control enters doPost() in RegistrationController");

		try {
			int citizenTypeId = 0;

			UserDTO user = new UserDTO();

			user.setFirstName(request.getParameter("firstname"));
			
			user.setLastName(request.getParameter("lastname"));
			
			user.setDob(request.getParameter("date"));
			
			user.setAddress(request.getParameter("address"));
			
			user.setContact(request.getParameter("contact"));
			
			user.setEmail(request.getParameter("email"));
			
			user.setQualification(request.getParameter("qualification"));
			
			user.setGender(request.getParameter("gender"));
			
			if (request.getParameter("applytype").equalsIgnoreCase("passport")) {
				user.setApplyType("Passport");
			} else if (request.getParameter("applytype").equalsIgnoreCase("visa")) {
				user.setApplyType("Visa");
			}
			
			if (request.getParameter("hintquestion").equals("A")) {
				user.setHintQuestion("Who is my best friend ?");
			} else if (request.getParameter("hintquestion").equals("B")) {
				user.setHintQuestion("Who is my mentor ?");
			} else if (request.getParameter("hintquestion").equals("C")) {
				user.setHintQuestion("Which is my favorite english movies series ?");
			} else if (request.getParameter("hintquestion").equals("D")) {
				user.setHintQuestion("Which is the most boring hindi daily soap ?");
			}
			
			user.setHintAnswer(request.getParameter("hintanswer"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = null;
			try {
				birthDate = sdf.parse(user.getDob());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date currentDate = new Date();
			long timeDiff = currentDate.getTime() - birthDate.getTime();
			long yearDiff = (timeDiff / (24 * 60 * 60 * 1000)) / 365;
			if (yearDiff >= 0 && yearDiff <= 1) {
				citizenTypeId = 1;
			} else if (yearDiff > 1 && yearDiff <= 10) {
				citizenTypeId = 2;
			} else if (yearDiff > 10 && yearDiff <= 20) {
				citizenTypeId = 3;
			} else if (yearDiff > 20 && yearDiff <= 50) {
				citizenTypeId = 4;
			} else if (yearDiff > 50) {
				citizenTypeId = 5;
			}

			switch (citizenTypeId) {
			case 1:
				user.setCitizenType("Infant");
				break;
			case 2:
				user.setCitizenType("Children");
				break;
			case 3:
				user.setCitizenType("Teen");
				break;
			case 4:
				user.setCitizenType("Adult");
				break;
			case 5:
				user.setCitizenType("Senior Citizen");
				break;
			default:
				break;
			}

			RegisterDelegate registerDelegate = new RegisterDelegate();

			if (registerDelegate.userRegistration(user)) {
				LOG.info("User Registered Successfully");
				requestDispatcher = request.getRequestDispatcher("success.jsp");
				request.setAttribute("user", user);
				requestDispatcher.forward(request, response);
			} else{
				LOG.info("User Registration Unsuccessful");
			}
		} catch (DatabaseException e) {
			
			LOG.error("RegistrationController.class : " + e.getMessage());
			requestDispatcher = request.getRequestDispatcher("error.jsp");
			String message = e.getMessage();
			request.setAttribute("error", message);
			requestDispatcher.forward(request, response);

		} catch (BusinessException e) {
			LOG.error("RegistrationController.class : " + e.getMessage());
			requestDispatcher = request.getRequestDispatcher("error.jsp");
			String message = e.getMessage();
			request.setAttribute("error", message);
			requestDispatcher.forward(request, response);

		}
	}
}
