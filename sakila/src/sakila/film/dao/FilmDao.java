package sakila.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;
import sakila.vo.Actor;
import sakila.vo.Film;

public class FilmDao {
	//선택한 배우번호에 따른 영화리스트 출력
	public List<Film> selectFilmbyActor(String name){
		System.out.println("dao film:"+name);
	
		List<Film> list = new ArrayList<Film>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select "
				+"f.title,f.description,f.release_year "
				+"from film f inner join film_actor fa inner join "
				+"actor a  on a.actor_id=fa.actor_id and f.film_id=fa.film_id "
				+"where concat(first_name,'',last_name) like concat('%',?,'%')";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,name);
			System.out.println("sql:>"+sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Film film = new Film();
				film.setDescription(rs.getString("f.description"));
				film.setTitle(rs.getString("f.title"));
				film.setReleaseYear(rs.getString("f.release_year"));
				
				list.add(film);
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
		System.out.println("film list>>"+list);
		return list;
	}
}
