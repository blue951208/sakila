package sakila.db;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws Exception {
		//db접속
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://umj7-009.cafe24.com:3306/blue951208","blue951208","ehdgus6949");
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
