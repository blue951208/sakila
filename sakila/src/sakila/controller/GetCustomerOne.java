package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.CustomerDao;

/**
 * Servlet implementation class GetCustomerOne
 */
@WebServlet("/GetCustomerOne")
public class GetCustomerOne extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");//charset=utf=8");
		
		System.out.println("고객 확인servlet");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		System.out.println("고객 확인:"+firstName+"-"+lastName);
		CustomerDao customerDao = new CustomerDao();
		int customerId=customerDao.selectCustomerId(firstName, lastName);
		System.out.println("ID 확인"+customerId);
		
		Gson gson = new Gson();
		String json = gson.toJson(customerId);
		response.getWriter().write(json);
	}

}
