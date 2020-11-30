package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.project.constants.QueryConstants;
import com.project.dto.UserDTO;
import com.project.util.DbUtil;

public class RegisterDAO {

	private static final Logger LOG = Logger.getLogger(RegisterDAO.class);

	private String generateUserID(String applyType) throws SQLException {

		LOG.info("Control enters generateUserID() in RegisterDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		String id = null;
		if (applyType.equalsIgnoreCase("passport")) {
			try {
				String passportSequence = QueryConstants.USERID_PASS_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(passportSequence);

				result = statement.executeQuery();
				result.next();
				id = "PASS-" + result.getInt(1);
			} catch (SQLException ex) {
				LOG.error("Generate User Id : " + ex.getMessage());
			} finally {
				if (result != null) {
					result.close();
				}
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}
		} else if (applyType.equalsIgnoreCase("visa")) {
			try {
				String visaSequence = QueryConstants.USERID_VISA_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(visaSequence);

				result = statement.executeQuery();
				result.next();
				String val = Integer.toString(result.getInt(1));
				id = "VISA-" + val.substring(0, 4) + "." + val.substring(4);
			} catch (SQLException ex) {
				LOG.error("Generate User Id : " + ex.getMessage());
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

		return id;
	}

	private String generatePassword() {

		LOG.info("Control enters generatePassword() in RegisterDAO");
		
		Date date = new Date();
		String currentDate = date.toString().substring(8, 10)
				+ date.toString().toLowerCase().substring(4, 7);

		int randomNumber = (int) (Math.random() * 1000);

		if (randomNumber > 001 && randomNumber <= 299) {
			return (currentDate + "$" + randomNumber);
		} else if (randomNumber > 300 && randomNumber <= 599) {
			return (currentDate + "#" + randomNumber);
		} else {
			return (currentDate + "@" + randomNumber);
		}
	}

	public boolean userRegistration(UserDTO user) throws SQLException {
		
		LOG.info("Control enters userRegistration() in RegisterDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		if (connection != null) {
			try {
				String sql = QueryConstants.REGISTER_QUERY;
				statement = connection.prepareStatement(sql);

				String systemGeneratedUserId = generateUserID(user.getApplyType());
				String systemGeneratedPassword = generatePassword();

				statement.setString(1, systemGeneratedUserId);
				statement.setString(2, user.getFirstName());
				statement.setString(3, user.getLastName());
				statement.setString(4, user.getDob());
				statement.setString(5, user.getAddress());
				statement.setString(6, user.getContact());
				statement.setString(7, user.getEmail());
				statement.setString(8, user.getQualification());
				statement.setString(9, user.getGender());
				statement.setString(10, user.getApplyType());
				statement.setString(11, user.getHintQuestion());
				statement.setString(12, user.getHintAnswer());
				statement.setString(13, user.getCitizenType());
				statement.setString(14, systemGeneratedPassword);

				LOG.info(user + "[User Id=" + systemGeneratedUserId
						+ " Password=" + systemGeneratedPassword + "]");

				int countOfRowsAffected = statement.executeUpdate();

				LOG.info("Count Of Rows Affected : " + countOfRowsAffected);

				if (countOfRowsAffected == 1) {
					user.setUserId(systemGeneratedUserId);
					user.setPassword(systemGeneratedPassword);
					return true;
				} else {
					return false;
				}
			} catch (SQLException ex) {
				LOG.error("RegisterDAO.class " + ex.getMessage());
				
				throw ex;
			} finally {
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}
		}
		return false;
	}
}
