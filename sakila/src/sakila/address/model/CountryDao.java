package sakila.address.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CountryDao {
	//list를 리턴하는 메소드
	public List<Country> selectCountryList(){
		List<Country> list = new ArrayList<Country>(); 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select country_id,country,last_update from country limit 10 order by country_id desc";
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
					while(rs.next()) {
						Country country = new Country();
						country.setCountry(rs.getString("country"));
						country.setCountryId(rs.getInt("country_id"));
						country.setLastUpdate(rs.getString("last_update"));
						list.add(country);
					}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBHelper.close(rs, stmt, conn);
			}
		return list;
	}
	
	public void insertCountry(Country country){
		//객체 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into country(country,last_update) value(?,now())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
				stmt.setString(1, country.getCountry());
			stmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(null, stmt, conn);
		}
	}
}
