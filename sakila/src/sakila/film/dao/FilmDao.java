package sakila.film.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.db.DBHelper;
import sakila.vo.Actor;
import sakila.vo.Film;
import sakila.vo.Inven;
import sakila.vo.Paging;
import sakila.vo.Store;

public class FilmDao {
	public int selectCount(int storeId,String rating) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="";
		int row=0;
		System.out.println("값 확인 >"+storeId+","+rating);
		if(storeId==0) {
			if(rating.equals("All")) {//가게 전체일때
				System.out.println("리스트 전체 출력");
				sql = "select count(*) from " 
					+ "(select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating from "
					+"store s inner join inventory i inner join film f "
					+"on s.store_id=i.store_id and i.film_id=f.film_id "
					+"group by f.title) s";
			}else {//가게 전체에서 등급 선택 +rating 값 입력
				System.out.println("리스트 등급값 출력");
				sql = "select count(*) from "
					+"(select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" where f.rating=?"
					+" group by f.title"
					+") s";
			}
		}else {//가게 선택 & 등급 전체 + 가게번호,등급 값 입력
			System.out.println("전체 행구하기 가게번호"+storeId);
			if(rating.equals("All")) {
				System.out.println("가게 리스트  출력");
				sql="select count(*) from "
						+"(select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating" 
						+" from store s inner join inventory i inner join film f"
						+" on s.store_id=i.store_id and i.film_id=f.film_id"
						+" where s.store_id=?"
						+" group by f.title) s";
			}else {//가게 선택 & 등급 선택 +가게 번호 입력
				System.out.println("가게 리스트 등급 출력");
				 sql = "select count(*) from "
						 +"(select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
							+" from store s inner join inventory i inner join film f"
							+" on s.store_id=i.store_id and i.film_id=f.film_id"
							+" where s.store_id=? and f.rating=?"
							+" group by f.title) s";
			}
		}
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
					if(storeId==0) {
							if(rating.equals("All")){
								System.out.println("0 point");		
							}else {
								System.out.println("1");
								stmt.setString(1, rating);
							}
					}else {
						if(rating.equals("All")) {
							System.out.println("3");
							stmt.setInt(1, storeId);
						}else {
							System.out.println("4");
							stmt.setInt(1, storeId);
							stmt.setString(2, rating);
						}
					}
					rs = stmt.executeQuery();
					if(rs.next()) {
						System.out.println("2");
						System.out.println(rs.getInt("count(*)"));
						row=rs.getInt("count(*)");
						System.out.println("행의 수:"+row);
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
			return row;
		}
	
	
	
	public Inven selectInventory(String title){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select f.title,i.inventory_id,count(*) "
				+"from film f inner join inventory i "
				+"on f.film_id=i.film_id "
				+"where title=? "
				+"group by f.film_id";
		Inven inven = new Inven();
		try {
			//db 연결
			conn = DBHelper.getConnection();
			//쿼리 실행
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			//결과
			rs = stmt.executeQuery();
			if(rs.next()) {
				inven.setCount(rs.getInt("count(*)"));
				inven.setInventoryId(rs.getInt("i.inventory_id"));
				inven.setTitle(rs.getString("f.title"));
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
		return inven;
	}
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
	public List<Map<String,Object>> selectFilmByStore(int storeId,String rating,Paging paging){
		System.out.println("값 확인"+storeId+"\n-"+rating+"\n-"+paging);
		List<Map<String, Object>> list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql;
		
	if(storeId!=0) {
		if(rating.equals("All")) {
			//선택한 가게에 리스트 출력
		 sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
				+" from store s inner join inventory i inner join film f"
				+" on s.store_id=i.store_id and i.film_id=f.film_id"
				+" where s.store_id=?"
				+" group by f.title"
				+" limit ?,?";
		}else {
			//선택한 가게에 선택한 등급에 해당하는 리스트 출력
			sql="select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating" 
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" where s.store_id=? and f.rating=?"
					+" group by f.title"
					+" limit ?,?";
		}
	}else {//가게 전체
		if(rating.equals("All")) {
			System.out.println("all & allstore");
			//전체 리스트 출력
			sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" group by f.title"
					+" limit ?,?";
		}else {
			System.out.println("rating & allstore");
			//등급 선택 값과 전체 가게 리스트 출력
			sql = "select s.store_id,f.title,f.description,f.release_year,f.rental_rate,f.rating"
					+" from store s inner join inventory i inner join film f"
					+" on s.store_id=i.store_id and i.film_id=f.film_id"
					+" where f.rating=?"
					+" group by f.title"
					+" limit ?,?";
		}
	}
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
			  if(storeId!=0) {
				  //가게 선택 했을 경우
				  if(rating.equals("All")) {
					  //등급 전체
					  stmt.setInt(1, storeId); 
					  stmt.setInt(2, paging.getBeginRow());
					  stmt.setInt(3, paging.getRowPerPage());
				  }else{
					  //등급 선택
					  stmt.setInt(1, storeId); 
					  stmt.setString(2, rating);
					  stmt.setInt(3, paging.getBeginRow());
					  stmt.setInt(4, paging.getRowPerPage()); 
				  }
			  }else {
				  //가게 선택 안한 경우
				  if(rating.equals("All")) {
					  //등급 전체
					  stmt.setInt(1, paging.getBeginRow());
					  stmt.setInt(2, paging.getRowPerPage()); 					
				  }else {
					//등급 선택
					  stmt.setString(1, rating);
					  stmt.setInt(2, paging.getBeginRow());
					  stmt.setInt(3, paging.getRowPerPage());
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
