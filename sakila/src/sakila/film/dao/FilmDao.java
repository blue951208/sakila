package sakila.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.db.DBHelper;
import sakila.vo.Actor;
import sakila.vo.Film;
import sakila.vo.Store;

public class FilmDao {
	//선택한 가게에따라 존재하는 영화 리스트 출력
	public List<Map<String,Object>> selectFilmByStore(int storeId,String rating){
		List<Map<String, Object>> list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate"
				+" from store s inner join inventory i inner join film f"
				+" on s.store_id=i.store_id and i.film_id=f.film_id"
				+" group by f.title";
	if(storeId!=0) {
		 sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate"
				+" from store s inner join inventory i inner join film f"
				+" on s.store_id=i.store_id and i.film_id=f.film_id"
				+" where s.store_id=?"
				+" group by f.title";
	}
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
			  if(storeId!=0) {
				  stmt.setInt(1, storeId); 
			  }
				rs = stmt.executeQuery();
				System.out.println(">"+rs);
				while(rs.next()) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("storeId",rs.getInt("s.store_id"));
					map.put("title",rs.getString("f.title"));
					map.put("description", rs.getString("f.description"));
					map.put("releaseYear", rs.getString("f.release_year"));
					map.put("rentalRate", rs.getDouble("f.rental_rate"));
					list.add(map);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
			
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("map>>"+list);
			return list;
	}
	
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
			while(rs.next()) {
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
