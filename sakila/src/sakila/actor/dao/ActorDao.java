package sakila.actor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;
import sakila.vo.Actor;

public class ActorDao {
	public Actor selectActorOne() {
		Actor actor = new Actor(); 
		return actor;
	}
	
	public List<Actor> selectActorList(){
		System.out.println("actor dao select List");
		List<Actor> list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT actor_id,CONCAT(first_name,' ',last_name) as name"
				+" FROM actor";
		//오류 체크
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
				while(rs.next()) {
					Actor actor = new Actor();
					actor.setActorId(rs.getInt("actor_id"));
					actor.setName(rs.getString("name"));
					list.add(actor);
					System.out.println("list값>>:"+list);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	} 
}
