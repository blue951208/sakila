package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.FilmDao;
import sakila.vo.Paging;

/**
 * Servlet implementation class GetFilmbyStore
 */
@WebServlet("/selectFilmbyStore")
public class SelectFilmByStore extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
	
		System.out.println("page."+currentPage);
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		System.out.println("controlller store film:"+storeId);
		String rating = request.getParameter("rating");
		System.out.println("rating:"+rating);
		String category = request.getParameter("category");
		int rowPerPage=10;
		int beginRow = (currentPage-1)*rowPerPage;
		Paging paging = new Paging();
		paging.setRowPerPage(rowPerPage);
		paging.setBeginRow(beginRow);
		
		FilmDao filmDao = new FilmDao();
		List<Map<String, Object>> list = filmDao.selectFilmByStore(storeId,rating,paging);
		
		System.out.println("확인1"+list);
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}

}
