package com.project.dispatcher;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.project.controller.RegistrationController;
import com.project.dao.RegisterDAO;
import com.project.dto.UserDTO;
import com.project.exceptions.BusinessException;
import com.project.exceptions.DatabaseException;

public class RegisterDelegate {
	
	private static final Logger LOG = Logger.getLogger(RegistrationController.class);

	public boolean userRegistration(UserDTO user) throws DatabaseException, BusinessException 
	{
		LOG.info("Control enters userRegistration() in RegisterDelegate");
		
		RegisterDAO registerDao=new RegisterDAO();
		boolean isRegisteredSuccessfully = false;
		try {
			isRegisteredSuccessfully = registerDao.userRegistration(user);
		} catch (SQLException e) {
		LOG.error("Database Error");
		throw new DatabaseException("Database Error");
		} catch (Exception e) {
			LOG.error("Check all the credentials properly");
			throw new BusinessException("Check all the credentials properly");
		}
		return isRegisteredSuccessfully;
	}
}
