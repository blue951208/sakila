package sakila.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		List<Map<String, Object>> list = filmDao.selectFilmByStore(storeId);
		
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}

}
