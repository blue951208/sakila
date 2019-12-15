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
import sakila.vo.Paging;

/**
 * Servlet implementation class SelectActorOne
 */
@WebServlet("/SelectActorOne")
public class SelectActorOne extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");
		String name = request.getParameter("name");
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println("Name:"+name);
		List<Film> list = new ArrayList<Film>();
		
		FilmDao filmDao = new FilmDao();
		int rowPerPage=10;
		int beginRow=(currentPage-1)*rowPerPage;
		Paging paging = new Paging();
		paging.setBeginRow(beginRow);
		paging.setRowPerPage(rowPerPage);
		list = filmDao.selectFilmbyActor(name,paging);
		
		System.out.println("film by Actor List >>"+list);
		//gson 객체 생성
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}

}
