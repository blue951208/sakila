package sakila.film.dao;

import java.sql.*;

import sakila.db.DBHelper;
import sakila.vo.Staff;

public class StaffDao {
	public Staff selectStaffOne(int staffId){
		System.out.println("dao:"+staffId);
		int row=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select staff_id,password"
				+" from staff"
				+" where staff_id=?";
		Staff staff = new Staff();
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, staffId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setPassword(rs.getString("password"));
				System.out.println("log"+staff);
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
		return staff; 
	}
}
