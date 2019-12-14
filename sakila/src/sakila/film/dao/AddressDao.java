package sakila.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.db.DBHelper;
import sakila.vo.Address;
import sakila.vo.City;
import sakila.vo.Country;

public class AddressDao {
	//도시 목록 출력
	public List<City> selectCiyt(int countryId){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select city_id,city,country_id from city where country_id=?";
		//예외 처리
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, countryId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCity(rs.getString("city"));
				city.setCityId(rs.getInt("city_id"));
				list.add(city);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	
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
	
	//주소 입력
	public int insertAddress(Address address) {
		System.out.println("dao 값 확인"+address);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//입력
		String sql = "insert into address("
		+"address,address2,district,city_id,postal_code,phone"
		+")values(?,?,?,?,?,?)";
		int row = 0; //리턴 값 저장용
		//예외처리
		try {
			//접속 & 쿼리 실행
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, address.getAddress());
			stmt.setString(2, address.getAddress2());
			stmt.setString(3, address.getDistrict());
			stmt.setInt(4, address.getCityId());
			stmt.setString(5, address.getPostalCode());
			stmt.setString(6, address.getPhone());
			row = stmt.executeUpdate();
			System.out.println("입력 행:"+row);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		return row;
	}
	
}
