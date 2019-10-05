package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;

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
	}

}
