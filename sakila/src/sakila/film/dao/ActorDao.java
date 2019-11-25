package sakila.film.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;
import sakila.vo.Actor;

public class ActorDao {
	public Actor selectActorOne() {
		Actor actor = new Actor(); 
		return actor;
	}
	
	public int selectCount() {
		System.out.println("Count dao");
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from actor";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
				System.out.println("행수:"+count);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//actor 리스트 출력
	public List<Actor> selectActorList(){//int beginRow, int rowPerPage){
		System.out.println("actor dao select List,page>");//+ beginRow);
		List<Actor> list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT actor_id,CONCAT(first_name,' ',last_name) as name"
				+" FROM actor limit 10";
		//오류 체크
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
	/*		stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);*/
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
