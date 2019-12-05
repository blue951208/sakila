package sakila.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.CategoryDao;
import sakila.vo.Category;

/**
 * Servlet implementation class GetCategoryList
 */
@WebServlet("/GetCategoryList")
public class GetCategoryList extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("Application/json;charset=utf=8");
			System.out.println("카테고리 목록");
			CategoryDao categoryDao = new CategoryDao();
			List<Category> list = categoryDao.selectCategoryList();
			System.out.println("servlet List:"+list);
			
			Gson gson = new Gson();
			//view 로 list를 gson 타입으로 전송
			String json = gson.toJson(list);
			
			response.getWriter().write(json);
		}

}
