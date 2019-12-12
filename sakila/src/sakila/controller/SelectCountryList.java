package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.AddressDao;
import sakila.vo.Country;

/**
 * Servlet implementation class SelectCountryList
 */
@WebServlet("/selectCountryList")
public class SelectCountryList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");
		
		System.out.println("나라 목록 출력");
		List<Country> list = new ArrayList<Country>();
		
		AddressDao addressDao = new AddressDao();
		list = addressDao.selectCountry();
		System.out.println("list >>"+list);
		
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}

}
