package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;

/**
 * Servlet implementation class InsertCountry
 */
@WebServlet("/address/InsertCountry")
public class InsertCountry extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//country값 받기
		String country = request.getParameter("country");
		System.out.println("Servlet country:>>"+country);
		
		//받은 country값을 클래스 Country에 객체에 넣기
		Country c = new Country();
		CountryDao countryDao = new CountryDao();
		//country값을 c객체에 set
		c.setCountry(country);
		System.out.println("country객체에 값:>>"+c);
		//데이터베이스에 country값 insert
		countryDao.insertCountry(c);;
	}

}
