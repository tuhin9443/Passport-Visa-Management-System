package com.project.dispatcher;

import org.apache.log4j.Logger;

import com.project.dao.LoginDAO;
import com.project.dto.UserDTO;

public class LoginDelegate {
	
	private static final Logger LOG=Logger.getLogger(LoginDelegate.class);
	
	public boolean userLogin(UserDTO user) throws Exception{
	
		LOG.info("Control enters userLogin() in LoginDelegate");
		
		LoginDAO loginDao= new LoginDAO();
		boolean isValidUser=loginDao.userLogin(user);
		return isValidUser;
	}
  
}
