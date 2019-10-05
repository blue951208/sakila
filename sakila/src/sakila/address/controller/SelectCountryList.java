package sakila.address.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/address/selectCountryList")
public class SelectCountryList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list 생성
		List<Country> list = new ArrayList<Country>();
		//list 에 리턴값 복사
		CountryDao countryDao = new CountryDao();
		list = countryDao.selectCountryList();
		//json.toJson() 호출
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		//html로
		response.getWriter().write(gsonList);
	}

}
