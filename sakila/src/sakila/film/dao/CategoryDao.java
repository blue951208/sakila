package sakila.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;
import sakila.vo.Category;

public class CategoryDao {
	public List<Category> selectCategoryList(){
		System.out.println("category Dao");
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select name from category";
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Category category = new Category();
					category.setName(rs.getString("name"));
					list.add(category);
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
			System.out.println("list:"+list);
		return list;
	}
}
