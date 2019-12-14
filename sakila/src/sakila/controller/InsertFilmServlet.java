package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.film.dao.FilmDao;
import sakila.vo.Film;
import sakila.vo.Language;

/**
 * Servlet implementation class InsertFilmServlet
 */
@WebServlet("/InsertFilmServlet")
public class InsertFilmServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("film입력 servlet");
		//film 객체 생성
		Film film = new Film();
		
		String title = request.getParameter("title");
		film.setTitle(title);
		
		String description = request.getParameter("description");
		film.setDescription(description);
		
		int language = Integer.parseInt(request.getParameter("language"));
		film.setLanguage(new Language());
		film.getLanguage().setLanguageId(language);
		
		int rentalDuration = Integer.parseInt(request.getParameter("rentalDuration"));
		film.setRentalDuration(rentalDuration);
		
		int length = Integer.parseInt(request.getParameter("length"));
		film.setLength(length);
		
		String releaseYear =request.getParameter("releaseYear");
		film.setReleaseYear(releaseYear);

		int rentalRate = Integer.parseInt(request.getParameter("rentalRate"));
		film.setRentalRate(rentalRate);
		 
		int replacementCost = Integer.parseInt(request.getParameter("replacementCost"));
		film.setReplacementCost(replacementCost);
		
		String rating = request.getParameter("rating");
		film.setRating(rating);
		//체크 박스 확인
		String[] checkList = request.getParameterValues("specialFeatures[]");
		String features= checkList[0];
		if(checkList.length!=0) {
		
			for(int i=0;i<checkList.length-1;i++) {
					System.out.println("ck--"+checkList[i]);	
					features += ","+checkList[i+1];
				}
		}
		//features = checkList[];	
		film.setSpecialFeatures(features);
		
		System.out.println("값 확인"+film)	;
		
		FilmDao filmDao = new FilmDao();
		int row = filmDao.insertFilmList(film);
		System.out.println("입력 확인:"+row);
	}

}
