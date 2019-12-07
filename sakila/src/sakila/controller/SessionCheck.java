package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class SessionCheck
 */
@WebServlet("/SessionCheck")
public class SessionCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그인 여부 확인");
		
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
		
		//String sessionInfo = (String) session.getAttribute("sessionInfo");
		
		Gson gson = new Gson();
		String json = gson.toJson(session.getAttribute("sessionInfo"));
		
		response.getWriter().write(json);
	}

}
