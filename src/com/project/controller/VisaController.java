package com.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.dispatcher.VisaDelegate;
import com.project.dto.VisaDTO;
import com.project.exceptions.BusinessException;
import com.project.exceptions.DatabaseException;

public class VisaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(VisaController.class);
	RequestDispatcher requestDispatcher = null;

	public VisaController() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("Control enters doPost() in VisaController");

		String loginId = "";
		HttpSession session = request.getSession(false);
		if (session.getAttribute("LOGIN_ID") != null) {
			loginId = (String) session.getAttribute("LOGIN_ID");
		} else {
			requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
		
		VisaDelegate visaDelegate = new VisaDelegate();
		VisaDTO visa = new VisaDTO();
		visa.setLoginId(loginId);
		
		try {
			if (request.getParameter("option").equalsIgnoreCase("applyVisa")) {

				visa.setPassportId(request.getParameter("passportId"));
				
				if (visaDelegate.passportVerification(visa)) {

					LOG.info("Passport id verified");
					
					if (request.getParameter("country").equalsIgnoreCase("c1")) {
						visa.setCountry("USA");
					} else if (request.getParameter("country")
							.equalsIgnoreCase("c2")) {
						visa.setCountry("CHINA");
					} else if (request.getParameter("country")
							.equalsIgnoreCase("c3")) {
						visa.setCountry("JAPAN");
					}

					if (request.getParameter("occupation").equalsIgnoreCase(
							"ST")) {
						visa.setOccupation("Student");
					} else if (request.getParameter("occupation")
							.equalsIgnoreCase("PE")) {
						visa.setOccupation("Private Employee");
					} else if (request.getParameter("occupation")
							.equalsIgnoreCase("GE")) {
						visa.setOccupation("Government Employee");
					} else if (request.getParameter("occupation")
							.equalsIgnoreCase("SE")) {
						visa.setOccupation("Self Employee");
					} else if (request.getParameter("occupation")
							.equalsIgnoreCase("RE")) {
						visa.setOccupation("Retired Employee");
					}

					visa.setVisaDateOfApplication(request
							.getParameter("dateOfApplication"));

					if (visaDelegate.applyVisa(visa)) {
						
						LOG.info("Apply Visa Successful");
						requestDispatcher = request
								.getRequestDispatcher("visaApplicationSuccessful.jsp");
						request.setAttribute("visa", visa);
						requestDispatcher.forward(request, response);
					} else {
						LOG.info("Apply Visa Unsuccessful");
					}

				} else {
					LOG.info("Wrong passport id entered");
					requestDispatcher = request
							.getRequestDispatcher("error.jsp");
					requestDispatcher.forward(request, response);
				}

			} else if (request.getParameter("option").equalsIgnoreCase("validation")) {

				visa.setLoginId(loginId);
				

				String hintQuestion = null;
				if (request.getParameter("hintquestion").equals("A")) {
					hintQuestion = "Who is my best friend ?";
				} else if (request.getParameter("hintquestion").equals("B")) {
					hintQuestion = "Who is my mentor ?";
				} else if (request.getParameter("hintquestion").equals("C")) {
					hintQuestion = "Which is my favorite english movies series ?";
				} else if (request.getParameter("hintquestion").equals("D")) {
					hintQuestion = "Which is the most boring hindi daily soap ?";
				}
				String hintAnswer = request.getParameter("hintanswer");

				if (visaDelegate.visaUserValidation(visa.getLoginId(),
						hintQuestion, hintAnswer)) {

					LOG.info("Visa user validated for visa cancellation");
					requestDispatcher = request.getRequestDispatcher("visaCancel.jsp");
					request.setAttribute("visa", visa);
					requestDispatcher.forward(request, response);
				} else {
					LOG.info("Visa user invalid");
					requestDispatcher = request.getRequestDispatcher("visaCancellationError.jsp");
					requestDispatcher.forward(request, response);
				}

			} else if (request.getParameter("option").equalsIgnoreCase("cancelVisa")) {

				visa.setPassportId(request.getParameter("passportId"));
				
				if (visaDelegate.passportVerification(visa)) {
                 
					LOG.info("Passport id validated");

					visa.setVisaId(request.getParameter("visaId"));
					
					visa.setVisaIssueDate(request.getParameter("issueDate"));
					
					if (visaDelegate.hasActiveVisa(visa)) {
						
						LOG.info("Active visa requested for cancellation");

						if (visaDelegate.visaCancellation(visa)) {
							LOG.info("Visa Cancelled Successfully");
							requestDispatcher = request.getRequestDispatcher("visaCancellationSuccessful.jsp");
							request.setAttribute("visa", visa);
							requestDispatcher.forward(request, response);
						} else {
							LOG.info("Visa Cancellation Unsuccessful");
							requestDispatcher = request.getRequestDispatcher("error.jsp");
							requestDispatcher.forward(request, response);
						}
					} else {
						LOG.info("Inactive Visa");
						requestDispatcher = request.getRequestDispatcher("visaCancellationError.jsp");
						requestDispatcher.forward(request, response);
					}

				} else {
					LOG.info("Passport id invalid");
					requestDispatcher = request.getRequestDispatcher("error.jsp");
					requestDispatcher.forward(request, response);
				}

			}
		} catch (DatabaseException e) {
			requestDispatcher = request.getRequestDispatcher("visaCancellationError.jsp");
			String message = e.getMessage();
			request.setAttribute("error", message);
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			requestDispatcher = request.getRequestDispatcher("visaCancellationError.jsp");
			String message = e.getMessage();
			request.setAttribute("error", message);
			requestDispatcher.forward(request, response);
		}

	}
}
