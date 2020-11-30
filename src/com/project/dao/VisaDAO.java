package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.project.constants.QueryConstants;
import com.project.dto.VisaDTO;
import com.project.exceptions.BusinessException;
import com.project.util.DbUtil;

public class VisaDAO {

	private static final Logger LOG = Logger.getLogger(VisaDAO.class);

	public boolean passportVerification(VisaDTO visa) throws SQLException,
			BusinessException {
		
		LOG.info("Control enters passportVerification() in VisaDAO");

		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean isPassportAvailable = false;
		if (connection != null) {
			String sql = QueryConstants.PASSPORT_VERIFICATION_QUERY;
			try {
				
				statement = connection.prepareStatement(sql);
				statement.setString(1, visa.getLoginId());
				result = statement.executeQuery();
				if (!result.next())
					throw new BusinessException("Please apply for passport first");
				if (visa.getPassportId().equalsIgnoreCase(result.getString(1))) {
					isPassportAvailable = true;
				} else
					throw new BusinessException("Passport does not match");
			} catch (SQLException e) {
				LOG.error("VisaDAO.class : " + e.getMessage());
				throw e;
			} finally {
				if (result != null) {
					result.close();
				}
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}
		}
		return isPassportAvailable;
	}

	public static String calculateIssueDate(VisaDTO visa) throws ParseException {
		
		LOG.info("Control enters calculateIssueDate() in VisaDAO");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(visa.getVisaDateOfApplication());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 10);
		String issueDate = Integer.toString(cal.get(Calendar.DATE));
		String issueMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String issueYear = Integer.toString(cal.get(Calendar.YEAR));

		String visaIssueDate = issueYear + "-" + issueMonth + "-" + issueDate;
		return visaIssueDate;
	}

	public static int calculateAmount(VisaDTO visa) throws BusinessException {
		
		LOG.info("Control enters calculateAmount() in VisaDAO");

		int cost = 0;
		if (visa.getOccupation().equalsIgnoreCase("STUDENT")) {
			if (visa.getCountry().equalsIgnoreCase("USA")) {
				cost = 3000;
			} else if (visa.getCountry().equalsIgnoreCase("CHINA")) {
				cost = 1500;
			} else if (visa.getCountry().equalsIgnoreCase("JAPAN")) {
				cost = 3500;
			}
		} else if (visa.getOccupation().equalsIgnoreCase("PRIVATE EMPLOYEE")) {
			if (visa.getCountry().equalsIgnoreCase("USA")) {
				cost = 4500;
			} else if (visa.getCountry().equalsIgnoreCase("CHINA")) {
				cost = 2000;
			} else if (visa.getCountry().equalsIgnoreCase("JAPAN")) {
				cost = 4000;
			}
		} else if (visa.getOccupation().equalsIgnoreCase("GOVERNMENT EMPLOYEE")) {
			if (visa.getCountry().equals("USA")) {
				cost = 5000;
			} else if (visa.getCountry().equalsIgnoreCase("CHINA")) {
				cost = 3000;
			} else if (visa.getCountry().equalsIgnoreCase("JAPAN")) {
				cost = 4500;
			}
		} else if (visa.getOccupation().equalsIgnoreCase("SELF EMPLOYEE")) {
			if (visa.getCountry().equalsIgnoreCase("USA")) {
				cost = 6000;
			} else if (visa.getCountry().equalsIgnoreCase("CHINA")) {
				cost = 4000;
			} else if (visa.getCountry().equalsIgnoreCase("JAPAN")) {
				cost = 9000;
			}
		} else if (visa.getOccupation().equalsIgnoreCase("RETIRED EMPLOYEE")) {
			if (visa.getCountry().equalsIgnoreCase("USA")) {
				cost = 2000;
			} else if (visa.getCountry().equalsIgnoreCase("CHINA")) {
				cost = 2000;
			} else if (visa.getCountry().equalsIgnoreCase("JAPAN")) {
				cost = 1000;
			}
		} else
			throw new BusinessException("Date error");
		return cost;
	}

	public static String generateVisaId(VisaDTO visa) throws Exception {
		
		LOG.info("Control enters generateVisaId() in VisaDAO");

		String visaId = null;
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			
			if (visa.getOccupation().equalsIgnoreCase("STUDENT")) {
				
				String sql = QueryConstants.STUDENT_VISAID_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				visaId = "STU-" + result.getInt(1);
				
			} else if (visa.getOccupation().equalsIgnoreCase("PRIVATE EMPLOYEE")) {
				
				String sql = QueryConstants.PRIVATE_EMPLOYEE_VISAID_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				visaId = "PE-" + result.getInt(1);
				
			} else if (visa.getOccupation().equalsIgnoreCase("GOVERNMENT EMPLOYEE")) {
				
				String sql = QueryConstants.GOVERNMENT_EMPLOYEE_VISAID_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				visaId = "GE-" + result.getInt(1);
				
			} else if (visa.getOccupation().equalsIgnoreCase("SELF EMPLOYEE")) {
				
				String sql = QueryConstants.SELF_EMPLOYEE_VISAID_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				visaId = "SE-" + result.getInt(1);
				
			} else if (visa.getOccupation().equalsIgnoreCase("RETIRED EMPLOYEE")) {
				
				String sql = QueryConstants.RETIRED_EMPLOYEE_VISAID_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				visaId = "RE-" + result.getInt(1);
				
			}
		} catch (SQLException e) {
			LOG.error("Generate visa id : " + e.getMessage());
			throw e;
		} finally {
			if (result != null) {
				result.close();
			}
			statement.close();
			if (connection != null) {
				DbUtil.closeConnection(connection);
			}
		}
		return visaId;

	}

	public static String calculateExpiryDate(VisaDTO visa) throws Exception {
		
		LOG.info("Control enters calculateExpiryDate() in VisaDAO");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(visa.getVisaIssueDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (visa.getOccupation().equalsIgnoreCase("STUDENT")) {
			cal.add(Calendar.MONTH, 24);
		} else if (visa.getOccupation().equalsIgnoreCase("PRIVATE EMPLOYEE")) {
			cal.add(Calendar.MONTH, 36);
		} else if (visa.getOccupation().equalsIgnoreCase("GOVERNMENT EMPLOYEE")) {
			cal.add(Calendar.MONTH, 48);
		} else if (visa.getOccupation().equalsIgnoreCase("SELF EMPLOYEE")) {
			cal.add(Calendar.MONTH, 12);
		} else if (visa.getOccupation().equalsIgnoreCase("RETIRED EMPLOYEE")) {
			cal.add(Calendar.MONTH, 18);
		}

		String expiryDate = Integer.toString(cal.get(Calendar.DATE));
		String expiryMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String expiryYear = Integer.toString(cal.get(Calendar.YEAR));
		String visaExpiryDate = expiryYear + "-" + expiryMonth + "-"
				+ expiryDate;

		return visaExpiryDate;

	}

	public boolean applyVisa(VisaDTO visa) throws BusinessException,
			SQLException {

		LOG.info("Control enters applyVisa() in VisaDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		if (connection != null) {
			try {
				String sql = QueryConstants.APPLY_VISA_QUERY;
				statement = connection.prepareStatement(sql);
				
				visa.setVisaIssueDate(calculateIssueDate(visa));
				visa.setVisaAmount(calculateAmount(visa));
				visa.setVisaExpiryDate(calculateExpiryDate(visa));
				visa.setVisaId(generateVisaId(visa));
				
				statement.setString(1, visa.getVisaId());
				statement.setString(2, visa.getCountry());
				statement.setString(3, visa.getOccupation());
				statement.setString(4, visa.getVisaDateOfApplication());
				statement.setString(5, visa.getVisaIssueDate());
				statement.setString(6, visa.getVisaExpiryDate());
				statement.setInt(7, visa.getVisaAmount());
				statement.setInt(8, 0);
				statement.setString(9, visa.getLoginId());
				statement.setString(10, visa.getPassportId());
				statement.setString(11, "Active");

				LOG.info(visa);

				int countOfRowsAffected = statement.executeUpdate();

				LOG.info("Count of Rows Affected : " + countOfRowsAffected);

				if (countOfRowsAffected == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				LOG.error("VisaDAO.class : " + e.getMessage());
				throw e;
			} catch (Exception e) {
				LOG.error("VisaDAO.class : " + e.getMessage());
				throw new BusinessException("Error in Apply Visa");
			} finally {
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}
		}

		return false;
	}

	public boolean visaCancellation(VisaDTO visa) throws SQLException, BusinessException {

		LOG.info("Control enters visaCancellation() in VisaDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		if (connection != null) {
			try {
				
				String sql = QueryConstants.VISA_CANCELLATION_QUERY;
				statement = connection.prepareStatement(sql);
				statement.setInt(1, visa.getCancellationAmount());
				statement.setString(2, visa.getRemarks());
				statement.setString(3, visa.getLoginId());
				statement.setString(4, visa.getVisaId());
				
				int countOfRowsAffected = statement.executeUpdate();
               
				if (countOfRowsAffected == 1) {
					return true;
				} else {
					
					throw new BusinessException("Visa already cancelled");
				}
			} catch (SQLException e) {
				
				LOG.error("VisaDAO.class" + e.getMessage());
				throw e;
			} finally {
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}

		}
		return false;
	}

	public boolean passportVisaCancellation(VisaDTO visa) throws SQLException {
		
		LOG.info("Control enters passportVisaCancellation() in VisaDAO");

		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		if (connection != null) {
			
			try {
				String sql = QueryConstants.PASSPORT_VISA_CANCELLATION_QUERY;
				statement = connection.prepareStatement(sql);
				statement.setString(1, "Cancelled");
				statement.setString(2, visa.getLoginId());
				statement.setString(3, visa.getPassportId());
				
				int countOfRowsAffected = statement.executeUpdate();
				
				if (countOfRowsAffected == 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				LOG.error("");
			} finally {
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}

		}
		return false;
	}

	public void fetchVisaDetails(VisaDTO visa) throws SQLException {
		
		LOG.info("Control enters fetchVisaDetails() in VisaDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connection != null) {
			try {
				String sql = QueryConstants.FETCH_VISA_QUERY;
				statement = connection.prepareStatement(sql);
				statement.setString(1, visa.getLoginId());
				result = statement.executeQuery();

				if (result.next()) {
					visa.setOccupation(result.getString(1));
					visa.setVisaIssueDate(result.getString(2));
					visa.setVisaExpiryDate(result.getString(3));
					visa.setVisaAmount(result.getInt(4));
					
				}

			} catch (Exception e) {
				LOG.error("Fetch Visa Details (DAO): " + e.getMessage());
			} finally {
				if (result != null) {
					result.close();
				}
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}

		}

	}

	public boolean hasActiveVisa(VisaDTO visa) throws SQLException,
			BusinessException {
		
		LOG.info("Control enters hasActiveVisa() in VisaDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connection != null) {
			try {
				
				String sql = QueryConstants.CHECK_VISA_QUERY;
				statement = connection.prepareStatement(sql);
				statement.setString(1, visa.getLoginId());
				statement.setString(2, visa.getPassportId());
				int count = 0;
				result = statement.executeQuery();
				result.next();
				count = result.getInt(1);
				
				if (count > 0) {
					return true;
				} else {
					if (visa.getOccupation().equals("reissuePassport"))
						return true;
					else
						throw new BusinessException("Visa not active ");

				}
			} catch (Exception e) {
				LOG.error("VisaDAO.class : " + e.getMessage());
			} finally {
				if (result != null) {
					result.close();
				}
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}

		}

		return false;
	}

	public boolean visaUserValidation(String loginId, String hintQuestion,String hintAnswer) throws SQLException, BusinessException {
		
		LOG.info("Control enters visaUserValidation() in VisaDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean isVisaUserValid = false;

		if (connection != null) {
			try {
				String sql = QueryConstants.VISA_USER_VALIDATION_QUERY;
				statement = connection.prepareStatement(sql);
				statement.setString(1, loginId);
				statement.setString(2, hintQuestion);
				statement.setString(3, hintAnswer);
				int count = 0;
				result = statement.executeQuery();
				result.next();
				count = result.getInt(1);
				
				if (count == 1) {
					isVisaUserValid = true;
				} else
					throw new BusinessException("Please give the correct hint answer ");
			} catch (Exception e) {
				LOG.error("VisaDAO.class : " + e.getMessage());
			} finally {
				if (result != null) {
					result.close();
				}
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}

		}

		return isVisaUserValid;

	}

}
