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
	//film 테이블에 영화 입력
	public int insertFilmList(Film film) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int row = 0;
		String sql="insert into film("
				+"title,description,release_year"
				+",language_id,original_language_id,"
				+"rental_duration,rental_rate,length,"
				+"replacement_cost,rating,special_features)"
				+"values(?,?,?,?,?,?,?,?,?,?,?)";
		String features="";
			try{
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, film.getTitle());
				stmt.setString(2, film.getDescription());
				stmt.setString(3, film.getReleaseYear());
				stmt.setInt(4,film.getLanguage().getLanguageId());
				stmt.setInt(5,film.getLanguage().getLanguageId());
				stmt.setInt(6,film.getRentalDuration());
				stmt.setInt(7, (int) film.getRentalRate());
				stmt.setInt(8, film.getLength());
				stmt.setInt(9, (int) film.getReplacementCost());
				stmt.setString(10, film.getRating());
				stmt.setString(11, film.getSpecialFeatures());
				row = stmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
					conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
			return row;
	}
	
	//선택한 가게에따라 존재하는 영화 리스트 출력
	public List<Map<String,Object>> selectFilmByStore(int storeId,String rating,String category){
		System.out.println("값 확인"+storeId+"\n-"+rating+"\n-"+category);
		List<Map<String, Object>> list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql;
		
		if(rating.contentEquals("All")) {
			System.out.println("all & allstore");
			sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" group by f.title";
		}else {
			System.out.println("rating & allstore");
			sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" where f.rating=?"
					+" group by f.title";
		}
	if(storeId!=0) {
		if(rating.contentEquals("All")) {
		 sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
				+" from store s inner join inventory i inner join film f"
				+" on s.store_id=i.store_id and i.film_id=f.film_id"
				+" where s.store_id=?"
				+" group by f.title";
		}else {
			sql="select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating" 
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" where s.store_id=? and f.rating=?"
					+" group by f.title";
		}
	}
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
			  if(storeId!=0) {
				  stmt.setInt(1, storeId); 
				  if(rating!="All") {
					  stmt.setString(2, rating);
				  }
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
					map.put("rating", rs.getString("f.rating"));
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
