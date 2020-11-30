package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.project.constants.QueryConstants;
import com.project.dto.PassportDTO;
import com.project.dto.VisaDTO;
import com.project.exceptions.BusinessException;
import com.project.util.DbUtil;

public class PassportDAO {

	private static final Logger LOG = Logger.getLogger(PassportDAO.class);

	private String generatePassportId(PassportDTO passport) throws SQLException {
		
		LOG.info("Control enters generatePassportId() in PassportDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		String id = null;
		if (passport.getBookletType() == 30) {
			try {
				String sql = QueryConstants.PASSPORTID_30PAGE_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				id = "FPS-30" + result.getInt(1);
			} catch (SQLException ex) {
				LOG.error("Generate passport id : " + ex.getMessage());
			} finally {
				if (result != null) {
					result.close();
				}
				statement.close();
				if (connection != null) {
					DbUtil.closeConnection(connection);
				}
			}

		} else if (passport.getBookletType() == 60) {
			try {
				String sql = QueryConstants.PASSPORTID_60PAGE_SEQUENCE_GENERATOR;
				statement = connection.prepareStatement(sql);
				result = statement.executeQuery();
				result.next();
				id = "FPS-60" + result.getInt(1);
			} catch (SQLException ex) {
				LOG.error("Generate passport id : " + ex.getMessage());
				throw ex;
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
		passport.setPassportId(id);

		return id;
	}

	private String calculateExpiryDate(PassportDTO passport)
			throws BusinessException {
		
		LOG.info("Control enters calculateExpiryDate() in PassportDAO");

		String expiryDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			sdf.setLenient(false);
			Date date = sdf.parse(passport.getIssueDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, 10);
			expiryDate = sdf.format(cal.getTime());
		} catch (Exception ex) {
			LOG.error("Expiry date calculation : " + ex.getMessage());
			throw new BusinessException("Date Error");
		}
		passport.setExpiryDate(expiryDate);
		return expiryDate;

	}

	public boolean existingPassportVerification(PassportDTO passport)
			throws SQLException, BusinessException {

		LOG.info("Control enters existingPassportVerification() in PassportDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connection != null) {
			try {
				String sql = QueryConstants.EXISTING_PASSPORT_QUERY;
				statement = connection.prepareStatement(sql);

				statement.setString(1, passport.getLoginId());

				result = statement.executeQuery();
				result.next();

				if (result.getInt(1) == 0) {
					if (passport.getOption().equals("reissuePassport"))
						throw new BusinessException("Passport not found");
					else
						return true;
				} else {
					if (passport.getOption().equals("applyPassport"))
						throw new BusinessException("Passport already exists");
					else
					{
						return false;
					}
				}
			} catch (SQLException ex) {
				LOG.error("PassportDAO.class : " + ex.getMessage());
				throw ex;
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

	public boolean applyPassport(PassportDTO passport) throws SQLException,
			BusinessException {

		LOG.info("Control enters applyPassport() in PassportDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;

		if (connection != null) {
			try {
				String sql = QueryConstants.APPLY_PASSPORT_QUERY;
				statement = connection.prepareStatement(sql);
				passport.setPassportId(generatePassportId(passport));
				try {
					passport.setExpiryDate(calculateExpiryDate(passport));
				} catch (Exception e) {
					LOG.error("PassportDAO.class : " + e.getMessage());
				}

				statement.setString(1, passport.getPassportId());
				statement.setString(2, "Null");
				statement.setString(3, passport.getCountry());
				statement.setString(4, passport.getState());
				statement.setString(5, passport.getCity());
				statement.setString(6, passport.getPin());
				statement.setString(7, passport.getServiceType());
				statement.setInt(8, passport.getBookletType());
				statement.setString(9, passport.getIssueDate());
				statement.setString(10, passport.getExpiryDate());
				statement.setString(11, passport.getPassportAmount());
				statement.setString(12, "Applied");
				statement.setString(13, passport.getLoginId());

				LOG.info(passport);

				int countOfRowsAffected = statement.executeUpdate();

				LOG.info("Count of Rows Affected : " + countOfRowsAffected);

				if (countOfRowsAffected == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException ex) {
				LOG.error("PassportDAO.class : " + ex.getMessage());
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

	public boolean reissuePassport(PassportDTO passport) throws SQLException, BusinessException {
		
		LOG.info("Control enters reissuePassport() in PassportDAO");

		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;

		if (connection != null) {
			try {

				VisaDTO visa = new VisaDTO();
				fetchpassportDetails(passport);

				visa.setPassportId(passport.getPassportId());
			
				visa.setLoginId(passport.getLoginId());
				
				VisaDAO visadao = new VisaDAO();
                 visa.setOption(passport.getOption());
				if (visadao.hasActiveVisa(visa)) {
					
					LOG.info("Visa Cancellation for the earlier passport");
					visadao.passportVisaCancellation(visa);
					
				}
				connection.setAutoCommit(false);
				String sql = QueryConstants.EXPIRE_PASSPORT_QUERY;
				statement = connection.prepareStatement(sql);

				statement.setString(1, passport.getRemarks());
				statement.setString(2, "EXPIRED");
				statement.setString(3, passport.getLoginId());

				LOG.info(passport);

				int countOfRowsAffected = statement.executeUpdate();
				
				LOG.info("Count of Rows Affected : " + countOfRowsAffected);

				if (countOfRowsAffected == 1) {

					LOG.info("Old passport marked expired");
					if (applyPassport(passport)) {
						connection.commit();
						return true;
					} else {
						connection.rollback();
						return false;
					}

				} else {
					connection.rollback();
					return false;
				}

			} catch (SQLException ex) {
				LOG.error("PassportDAO.class : " + ex.getMessage());
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

	public static void fetchpassportDetails(PassportDTO passport)
			throws SQLException {
		
		LOG.info("Control enters fetchpassportDetails() in PassportDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		if (connection != null) {
			try {
				String sql = QueryConstants.FETCH_PASSPORT_QUERY;
				statement = connection.prepareStatement(sql);
				statement.setString(1, passport.getLoginId());
				result = statement.executeQuery();

				if (result.next()) {

					String expiryDate = result.getString(1);
					String passportId = result.getString(2);
					passport.setExpiryDate(expiryDate);
					passport.setPassportId(passportId);

				} else {
					LOG.info("Result Set Empty");
				}

			} catch (SQLException ex) {
				LOG.error("PassportDAO.class : " + ex.getMessage());
				throw ex;
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

}
