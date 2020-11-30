package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.project.constants.QueryConstants;
import com.project.dto.UserDTO;
import com.project.util.DbUtil;

public class LoginDAO {
	private static final Logger LOG=Logger.getLogger(LoginDAO.class);
	
	public boolean userLogin(UserDTO user) throws SQLException {
		
		LOG.info("Control enters userLogin() in LoginDAO");
		
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		if (connection != null) {
			String sql = QueryConstants.LOGIN_QUERY;
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, user.getUserId());
				statement.setString(2, user.getPassword());
				
				LOG.info("[User Id=" + user.getUserId() + " Password=" + user.getPassword()+"]" );
				
				result = statement.executeQuery();
				result.next();
				int count = result.getInt(1);
				
				LOG.info("COUNT : " + count);
				
				if (count == 1){
					return true;
				}else{
					return false;
				}
			} catch (SQLException ex) {
				LOG.error("LoginDAO.class : " + ex.getMessage());
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

}
