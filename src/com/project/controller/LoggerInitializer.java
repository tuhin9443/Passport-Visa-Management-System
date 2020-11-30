package com.project.controller;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	private static final Logger LOG=Logger.getLogger(LoggerInitializer.class);
    
	public LoggerInitializer() {
        super();
       
    }
	
	public void init(ServletConfig config) throws ServletException {
		ServletContext context=config.getServletContext();
		String path=context.getRealPath("/");
		String logPropFile=path+"WEB-INF\\log4j.properties";
		PropertyConfigurator.configure(logPropFile);
		LOG.info("Application Initialized");
				
	}

}
