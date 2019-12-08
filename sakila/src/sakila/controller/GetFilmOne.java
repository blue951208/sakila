package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.FilmDao;
import sakila.vo.Inven;

/**
 * Servlet implementation class GetFilmOne
 */
@WebServlet("/GetFilmOne")
public class GetFilmOne extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		System.out.println("영화 재고 확인"+title);
		FilmDao filmDao = new FilmDao();
		
		Inven inven = new Inven();
		
		inven = filmDao.selectInventory(title);
		System.out.println("재고"+inven);
		
		Gson gson = new Gson();
		String json = gson.toJson(inven);
		
		response.getWriter().write(json);
	}

}
