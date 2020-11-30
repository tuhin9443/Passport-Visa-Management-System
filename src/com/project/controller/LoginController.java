package com.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.dispatcher.LoginDelegate;
import com.project.dto.UserDTO;



public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	public LoginController() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("Control enters doPost() in LoginController");
		
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		try {
			UserDTO user = new UserDTO();

			user.setUserId(request.getParameter("userid"));

			user.setPassword(request.getParameter("password"));

			LoginDelegate loginDelegate = new LoginDelegate();

			if (loginDelegate.userLogin(user)) {

				LOG.info("User Logged in Successfully");
				HttpSession session = request.getSession();
				session.setAttribute("LOGIN_ID", user.getUserId().toUpperCase());
				requestDispatcher = request.getRequestDispatcher("loginWelcomePage.jsp");
				requestDispatcher.forward(request, response);
			} else {
				LOG.info("Invalid credentials entered while login");
				response.sendRedirect("login.jsp?errmsg=invalid");
			}
		} catch (Exception ex) {
			LOG.error("LoginController.class : " + ex.getMessage());
			requestDispatcher = request.getRequestDispatcher("error.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
