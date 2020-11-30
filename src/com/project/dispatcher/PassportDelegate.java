package com.project.dispatcher;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.project.controller.PassportController;
import com.project.dao.PassportDAO;
import com.project.dto.PassportDTO;
import com.project.exceptions.BusinessException;
import com.project.exceptions.DatabaseException;

public class PassportDelegate {
	
	private static final Logger LOG = Logger.getLogger(PassportController.class);

	public boolean existingPassportVerification(PassportDTO passport)
			throws DatabaseException, BusinessException {
		
		LOG.info("Control enters existingPassportVerification() in PassportDelegate");

		PassportDAO passportDao = new PassportDAO();
		boolean isPassportExisting = false;
		try {
			isPassportExisting = passportDao
					.existingPassportVerification(passport);
		} catch (SQLException e) {
			LOG.error("Database Error");
			throw new DatabaseException("Database Error");

		} catch (BusinessException e) {
			LOG.error("Buisness Exception");
			throw e;
		}
		return isPassportExisting;
	}

	public boolean applyPassport(PassportDTO passport)
			throws DatabaseException, BusinessException {
		
		LOG.info("Control enters applyPassport() in PassportDelegate");

		PassportDAO passportDao = new PassportDAO();
		boolean isApplyPassportSuccessful;
		try {
			isApplyPassportSuccessful = passportDao.applyPassport(passport);
		} catch (SQLException e) {
			LOG.error("Database Error");
			throw new DatabaseException("Database Error");

		} catch (Exception e) {
			LOG.error("Passport application not successful");
			throw new BusinessException("Passport application not successful");
		}
		return isApplyPassportSuccessful;
	}

	public boolean reissuePassport(PassportDTO passport)
			throws DatabaseException, BusinessException {
		
		LOG.info("Control enters reissuePassport() in PassportDelegate");

		PassportDAO passportDao = new PassportDAO();
		boolean isReissuePassportSuccessful;
		try {
			isReissuePassportSuccessful = passportDao.reissuePassport(passport);
		} catch (SQLException e) {
			LOG.error("Database Error");
			throw new DatabaseException("Database Error");

		} catch (Exception e) {
			LOG.error("Reissue Passport not successful");
			throw new BusinessException("Reissue Passport not successful");
		}
		return isReissuePassportSuccessful;
	}

}
