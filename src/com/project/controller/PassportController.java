package com.project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.dispatcher.PassportDelegate;
import com.project.dto.PassportDTO;
import com.project.exceptions.BusinessException;
import com.project.exceptions.DatabaseException;

public class PassportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PassportController.class);
	RequestDispatcher requestDispatcher = null;

	public PassportController() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LOG.info("Control enters doPost() in PassportController");
		
		try {
			
			String loginId = null;

			HttpSession session = request.getSession(false);

			if (session.getAttribute("LOGIN_ID") != null) {
				loginId = (String) session.getAttribute("LOGIN_ID");
			} else {
				requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);
			}

			PassportDTO passport = new PassportDTO();
			passport.setOption(request.getParameter("option"));

			passport.setLoginId(loginId);

			if (request.getParameter("option").equalsIgnoreCase(
					"reissuePassport")) {
				if (request.getParameter("remarks").equals("Lost")) {
					passport.setRemarks("Lost");
				} else if (request.getParameter("remarks").equals("Damaged")) {
					passport.setRemarks("Damaged");
				}
			}

			if (request.getParameter("country").equalsIgnoreCase("india")) {
				passport.setCountry("India");
			}

			if (request.getParameter("stateName").equalsIgnoreCase("s1")) {

				passport.setState("Orissa");
				if (request.getParameter("cityName").equalsIgnoreCase("c1")) {
					passport.setCity("Bhubaneswar");
				} else if (request.getParameter("cityName").equalsIgnoreCase(
						"c2")) {
					passport.setCity("Cuttack");
				} else if (request.getParameter("cityName").equalsIgnoreCase(
						"c3")) {
					passport.setCity("Jajpur");
				}

			} else if (request.getParameter("stateName").equalsIgnoreCase("s2")) {

				passport.setState("West Bengal");
				if (request.getParameter("cityName").equalsIgnoreCase("c1")) {
					passport.setCity("Kolkata");
				} else if (request.getParameter("cityName").equalsIgnoreCase(
						"c2")) {
					passport.setCity("Howrah");
				}

			} else if (request.getParameter("stateName").equalsIgnoreCase("s3")) {

				passport.setState("Bihar");
				if (request.getParameter("cityName").equalsIgnoreCase("c1")) {
					passport.setCity("Patna");
				} else if (request.getParameter("cityName").equalsIgnoreCase(
						"c2")) {
					passport.setCity("Gaya");
				}

			} else if (request.getParameter("stateName").equalsIgnoreCase("s4")) {

				passport.setState("Kerala");
				if (request.getParameter("cityName").equalsIgnoreCase("c1")) {
					passport.setCity("Kochi");
				} else if (request.getParameter("cityName").equalsIgnoreCase(
						"c2")) {
					passport.setCity("Kayamkulam");
				}

			} else if (request.getParameter("stateName").equalsIgnoreCase("s5")) {

				passport.setState("Andhra Pradesh");
				if (request.getParameter("cityName").equalsIgnoreCase("c1")) {
					passport.setCity("Hyderabad");
				} else if (request.getParameter("cityName").equalsIgnoreCase(
						"c2")) {
					passport.setCity("Bellampalle");
				}

			}
			
			passport.setPin(request.getParameter("pin"));

			if (request.getParameter("servicetype").equals("Normal")) {
				passport.setServiceType("NORMAL");
				passport.setPassportAmount("1500");
			} else if (request.getParameter("servicetype").equals("Tatkal")) {
				passport.setServiceType("Tatkal");
				passport.setPassportAmount("3000");
			}

			if (request.getParameter("bookletType").equals("30P")) {
				passport.setBookletType(30);
			} else if (request.getParameter("bookletType").equals("60P")) {
				passport.setBookletType(60);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			passport.setIssueDate(sdf.format(date));
			PassportDelegate passportDelegate = new PassportDelegate();

			if (request.getParameter("option")
					.equalsIgnoreCase("applyPassport")) {

				if (passportDelegate.existingPassportVerification(passport)) {

					LOG.info("No existing passport for the given user id");

					if (passportDelegate.applyPassport(passport) == true) {
						LOG.info("Passport Application Successful");
						requestDispatcher = request.getRequestDispatcher("passportApplicationSuccessful.jsp");
						request.setAttribute("passport", passport);
						requestDispatcher.forward(request, response);
					} else {
						LOG.info("Passport Application Unsuccessful");
						requestDispatcher = request.getRequestDispatcher("error.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {
					LOG.info("Passport already exists for the given user id");
					requestDispatcher = request.getRequestDispatcher("error.jsp");
					requestDispatcher.forward(request, response);
				}
			} else if (request.getParameter("option").equalsIgnoreCase("reissuePassport")) {
				
				if (!passportDelegate.existingPassportVerification(passport)) {

					LOG.info("Active passport found");
					if (passportDelegate.reissuePassport(passport)) {
						LOG.info("Passport Re-Issue Successful");
						requestDispatcher = request.getRequestDispatcher("passportReissueSuccessful.jsp");
						request.setAttribute("passport", passport);
						requestDispatcher.forward(request, response);
					} else {
						LOG.info("Passport Re-Issue Unsuccessful");
						requestDispatcher = request.getRequestDispatcher("error.jsp");
						requestDispatcher.forward(request, response);
					}

				}
			}
		} catch (DatabaseException e) {
			LOG.error("PassportController.class : " + e.getMessage());
			requestDispatcher = request.getRequestDispatcher("error.jsp");
			String message = e.getMessage();
			request.setAttribute("error", message);
			requestDispatcher.forward(request, response);
			
		} catch (BusinessException e) {
			LOG.error("PassportController.class : " + e.getMessage());
			requestDispatcher = request.getRequestDispatcher("error.jsp");
			String message = e.getMessage();
			request.setAttribute("error", message);
			requestDispatcher.forward(request, response);
		}
	}
}
