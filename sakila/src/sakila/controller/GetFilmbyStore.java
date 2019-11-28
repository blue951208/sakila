package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetFilmbyStore
 */
@WebServlet("/getFilmbyStore")
public class GetFilmbyStore extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");
		
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		System.out.println("controlller store film:"+storeId);
		
		
	}

}
