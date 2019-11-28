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
	public Map<String,Object> selectFilmByStore(int storeId){
		Map<String,Object> map = new HashMap<String,Object>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select s.store,f.title,f.description,f.release_year,f.rental_rate"
					+"from store s inner join inventory i inner join film f"
					+"on s.store_id=i.store_id and i.film_id=f.film_id"
					+"where s.store_id=?";
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Store store = new Store();
					store.setStoreId(rs.getInt("s.store_id"));
					Film film = new Film();
					film.setTitle(rs.getString("f.title"));
					film.setDescription(rs.getString("f.description"));
					film.setReleaseYear(rs.getString("f.release_year"));
					film.setRentalRate(rs.getDouble("f.rental_rate"));
					map.put("film",film);
					map.put("store",store);
					System.out.println("map>>"+map);
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
			return map;
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
