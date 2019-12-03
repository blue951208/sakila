package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectRentalList
 */
@WebServlet("/SelectRentalList")
public class SelectRentalList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이름 입력값 받기
		String name = request.getParameter("name");
		System.out.println("name>>"+name);
		//이름값을 dao에 매게변수로 메소드 호출,list에 저장
		
		//list를 gson타입으로 html에 전송
	}

}
