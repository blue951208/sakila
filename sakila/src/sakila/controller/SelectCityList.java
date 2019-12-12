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

import sakila.film.dao.AddressDao;
import sakila.vo.City;

/**
 * Servlet implementation class SelectCityList
 */
@WebServlet("/selectCityList")
public class SelectCityList extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//데이터 타입 지정
			response.setContentType("Application/json");//charset=utf=8");
			
			int countryId =Integer.parseInt(request.getParameter("countryId"));
			System.out.println("도시 목록>"+countryId);
	
			//dao 호출
			AddressDao addressDao = new AddressDao();
			List<City> list = new ArrayList<City>();
			list = addressDao.selectCiyt(countryId);
			
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.getWriter().write(json);
		}

}
