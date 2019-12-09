package sakila.film.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.db.DBHelper;
import sakila.vo.Rental;

public class RentalDao {
	public int selectCount(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int row=0;
		String sql ="select count(*)"
				+" FROM inventory i INNER JOIN film f INNER JOIN rental r INNER JOIN customer c"
				+" ON i.inventory_id =r.inventory_id AND f.film_id=i.film_id AND c.customer_id=r.customer_id" 
				+" where concat(first_name,'',last_name) like concat('%',?,'%')";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row=rs.getInt("count(*)");
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
		return row;
	}
	
	public List<Map<String,Object>> getRentalList(String name,int beginRow,int rowPerPage){
		System.out.println("name 확인:"+name);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//이름에 해당하는 렌탈 리스트 출력 sql
		String sql = "SELECT CONCAT(first_name,' ',last_name),f.title,r.rental_date,r.return_date"
					+" FROM inventory i INNER JOIN film f INNER JOIN rental r INNER JOIN customer c"
					+" ON i.inventory_id =r.inventory_id AND f.film_id=i.film_id AND c.customer_id=r.customer_id"
					+" where concat(first_name,'',last_name) like concat('%',?,'%')"
					+" limit ?,?";
		//예외 처리
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				//map 생성 후 저장
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("name",rs.getString("CONCAT(first_name,' ',last_name)"));
				map.put("title", rs.getString("f.title"));
				map.put("rentalDate", rs.getString("r.rental_date"));
				map.put("returnDate", rs.getString("r.return_date"));
				//map을 list에 추가
				list.add(map);
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
		System.out.println("list>"+list);
		return list;
	}
	public int insertRental(Rental rental){
		Connection conn = null;
		PreparedStatement stmt = null;
		int row=0;
		String sql ="insert into rental("
				+"inventory_id,customer_id,staff_id)"
				+ "values(?,?,?)";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rental.getInventoryId());
			stmt.setInt(2, rental.getCustomerId());
			stmt.setInt(3, rental.getStaffId());
			row = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		return row;
	}
}
