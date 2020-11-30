package com.project.dispatcher;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.project.dao.VisaDAO;
import com.project.dto.VisaDTO;
import com.project.exceptions.BusinessException;
import com.project.exceptions.DatabaseException;

public class VisaDelegate {

	private static final Logger LOG = Logger.getLogger(VisaDelegate.class);

	public boolean passportVerification(VisaDTO visa) throws DatabaseException,
			BusinessException {
		
		LOG.info("Control enters passportVerification() in VisaDelegate");
		
		VisaDAO visaDao = new VisaDAO();
		boolean isPassportAvailable = false;
		try {
			isPassportAvailable = visaDao.passportVerification(visa);
		} catch (SQLException e) {
			LOG.info("Database Error");
			throw new DatabaseException("Database Error");
		} catch (BusinessException e) {
			LOG.info("Business Exception");
			throw e;
		}
		return isPassportAvailable;
	}

	public boolean applyVisa(VisaDTO visa) throws BusinessException,
			DatabaseException {
		
		LOG.info("Control enters applyVisa() in VisaDelegate");
		
		VisaDAO visaDao = new VisaDAO();
		boolean isApplyVisaSuccesfull;
		try {
			isApplyVisaSuccesfull = visaDao.applyVisa(visa);
		} catch (SQLException e) {
			LOG.info("Database Error");
			throw new DatabaseException("Database Error");
		} catch (BusinessException e) {
			LOG.info("Business Exception");
			throw e;
		}
		return isApplyVisaSuccesfull;
	}

	public boolean visaCancellation(VisaDTO visa) throws DatabaseException,
			BusinessException {
		
		LOG.info("Control enters visaCancellation() in VisaDelegate");
		
		VisaDAO visaDao = new VisaDAO();
		try {
			visaDao.fetchVisaDetails(visa);
		} catch (SQLException e) {
			LOG.info("Database Error");
			throw new DatabaseException("Database Error");
		}
		visaCancellationCost(visa);

		visa.setRemarks("Cancelled");

		boolean isVisaCancellationSuccessfull;
		try {
			isVisaCancellationSuccessfull = visaDao.visaCancellation(visa);
		} catch (SQLException e) {
			LOG.info("Database Error");
			throw new DatabaseException("Database Error");

		} catch (BusinessException e) {
			LOG.info("Business Exception");
			throw e;
		}
		return isVisaCancellationSuccessfull;
	}

	public boolean hasActiveVisa(VisaDTO visa) throws BusinessException,
			DatabaseException {
		
		LOG.info("Control enters hasActiveVisa() in VisaDelegate");
		
		VisaDAO visadao = new VisaDAO();
		boolean flag = false;
		try {
			flag = visadao.hasActiveVisa(visa);
		} catch (SQLException e) {
			LOG.info("Database Error");
			throw new DatabaseException("Database Error");
		} catch (BusinessException e) {
			LOG.info("Business Exception");
			throw e;
		}
		return flag;
	}

	public boolean visaUserValidation(String loginId, String hintQuestion,
			String hintAnswer) throws BusinessException, DatabaseException {
		
		LOG.info("Control enters visaUserValidation() in VisaDelegate");
		
		VisaDAO visaDao = new VisaDAO();
		boolean isVisaUserValid = false;
		try {
			isVisaUserValid = visaDao.visaUserValidation(loginId, hintQuestion,
					hintAnswer);
		} catch (SQLException e) {
			LOG.info("Database Error");
			throw new DatabaseException("Database Error");
		} catch (BusinessException e) {
			LOG.info("Business Exception");
			throw e;
		}
		return isVisaUserValid;
	}

	public void visaCancellationCost(VisaDTO visa) {
		
		LOG.info("Control enters visaCancellationCost() in VisaDelegate");
		
		int cancellationCost = 0;
		try {
			int visaAmount = visa.getVisaAmount();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date issueDate = (Date) sdf.parse(visa.getVisaIssueDate());
			Date expiryDate = (Date) sdf.parse(visa.getVisaExpiryDate());

			long dateDiff = expiryDate.getTime() - issueDate.getTime();
			long monthDiff = ((dateDiff / (24 * 60 * 60 * 1000)) / 30);

			if (visa.getOccupation().equalsIgnoreCase("STUDENT")) {
				if (monthDiff < 6) {
					cancellationCost = (visaAmount * 15) / 100;
				} else if (monthDiff >= 6) {
					cancellationCost = (visaAmount * 25) / 100;
				}
			} else if (visa.getOccupation()
					.equalsIgnoreCase("PRIVATE EMPLOYEE")) {
				if (monthDiff < 6) {
					cancellationCost = (visaAmount * 15) / 100;
				} else if (monthDiff >= 6 && monthDiff < 12) {
					cancellationCost = (visaAmount * 25) / 100;
				} else if (monthDiff >= 12) {
					cancellationCost = (visaAmount * 20) / 100;
				}
			} else if (visa.getOccupation().equalsIgnoreCase(
					"GOVERNMENT EMPLOYEE")) {
				if (monthDiff < 6) {
					cancellationCost = (visaAmount * 12) / 100;
				} else if (monthDiff >= 6 && monthDiff < 12) {
					cancellationCost = (visaAmount * 20) / 100;
				} else if (monthDiff >= 12) {
					cancellationCost = (visaAmount * 25) / 100;
				}
			} else if (visa.getOccupation().equalsIgnoreCase("SELF EMPLOYEE")) {
				if (monthDiff < 6)
					cancellationCost = (visaAmount * 15) / 100;
				else if (monthDiff >= 6)
					cancellationCost = (visaAmount * 25) / 100;

			} else if (visa.getOccupation()
					.equalsIgnoreCase("RETIRED EMPLOYEE")) {
				if (monthDiff < 6) {
					cancellationCost = (visaAmount * 10) / 100;
				} else if (monthDiff >= 6) {
					cancellationCost = (visaAmount * 20) / 100;
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occured calculating cancellation cost");
		}
		visa.setCancellationAmount(cancellationCost);

	}

}
