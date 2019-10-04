package sakila.db;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws Exception {
		//db접속
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/sakila","root","java1234");
		return conn;
	}
	//종료method
	public static void close(ResultSet rs,PreparedStatement stmt,Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
