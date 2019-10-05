package sakila.address.model;

import java.sql.*;

import sakila.db.DBHelper;

public class CountryDao {
	public void insertCountry(Country country){
		//객체 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into country(country,last_update) value(?,now())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
				stmt.setString(1,country.getCountry());
			stmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(null, stmt, conn);
		}
	}
}
