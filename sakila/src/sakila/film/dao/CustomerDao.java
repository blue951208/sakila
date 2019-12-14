package sakila.film.dao;

import java.sql.*;

import sakila.db.DBHelper;

public class CustomerDao {
	public int selectCustomerId(String firstName,String lastName) {
		System.out.println("이름:"+firstName+"-"+lastName);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select customer_id from customer where first_name=? and last_name=?";
		int customerId = 0;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				customerId = rs.getInt("customer_id");
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
		
		return customerId;
	}
}
