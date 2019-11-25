package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.FilmDao;
import sakila.vo.Film;

/**
 * Servlet implementation class SelectActorOne
 */
@WebServlet("/SelectActorOne")
public class SelectActorOne extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("Name:"+name);
		List<Film> list = new ArrayList<Film>();
		
		FilmDao filmDao = new FilmDao();
		list = filmDao.selectFilmbyActor(name);
		
		System.out.println("film by Actor List >>"+list);
		//gson 객체 생성
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
