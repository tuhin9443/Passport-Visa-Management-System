package com.project.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(LogoutServlet.class);
	RequestDispatcher requestDispatcher = null;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			LOG.info("Control enters doGet() in LogoutServlet");
			
			HttpSession session = request.getSession(false);
		    if(session != null)
		    {
		        session.invalidate();
		        
		        LOG.info("User logged out successfully");
		        
		        requestDispatcher = request.getRequestDispatcher("logoutSuccessful.jsp");
				requestDispatcher.forward(request, response);

		    }
	}

	

}
