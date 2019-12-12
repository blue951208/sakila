package sakila.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.db.DBHelper;
import sakila.vo.Country;

public class AddressDao {
	//나라 목록 출력
	public List<Country> selectCountry(){
		List<Country> list = new ArrayList<Country>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String sql="select country,country_id from country";
		try {
			//접속
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			//map으로 저장 후, list 추가
				while(rs.next()) {
					Country country = new Country();
					country.setCountry(rs.getString("country"));
					country.setCountryId(rs.getInt("country_id"));
					list.add(country);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		System.out.println("값 확인"+list);
		return list;
	}
	//도시목록 출력
	
	//주소 입력
}
