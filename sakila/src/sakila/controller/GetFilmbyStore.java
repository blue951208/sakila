package sakila.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.film.dao.FilmDao;

/**
 * Servlet implementation class GetFilmbyStore
 */
@WebServlet("/getFilmbyStore")
public class GetFilmbyStore extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");
		
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		System.out.println("controlller store film:"+storeId);
		
		FilmDao filmDao = new FilmDao();
		Map<String,Object> map = filmDao.selectFilmByStore(storeId);
		
	}

}
