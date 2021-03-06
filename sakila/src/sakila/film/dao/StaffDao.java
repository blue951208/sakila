package sakila.film.dao;

import java.sql.*;

import sakila.db.DBHelper;
import sakila.vo.Staff;

public class StaffDao {
	//스테프 로그인 할때 확인 
	public Staff selectStaffOne(Staff staff){
		System.out.println("dao:"+staff);
		//입력한 값과 동일하면 row = 1 아니면 0
		int row=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select staff_id,password"
				+" from staff"
				+" where staff_id=? and password=?";
		Staff staffOne = new Staff();
		try {
			//db 접속
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, staff.getStaffId());
			stmt.setString(2, staff.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {//해당 쿼리에 대한 값이 있을때 
				staffOne.setStaffId(rs.getInt("staff_id"));
				staffOne.setPassword(rs.getString("password"));
				System.out.println("log-->"+staffOne);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//종료
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		
		}
		return staffOne; 
	}
}
