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

import sakila.film.dao.RentalDao;

/**
 * Servlet implementation class SelectRentalList
 */
@WebServlet("/SelectRentalList")
public class SelectRentalList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Application/json");
		//이름 입력값 받기
		String name = request.getParameter("name");
		System.out.println("name>>"+name);
		//이름값을 dao에 매게변수로 메소드 호출,list에 저장
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		RentalDao rentalDao = new RentalDao();
		list = rentalDao.getRentalList(name);
		System.out.println("controller ck:"+list);
		//list를 gson타입으로 html에 전송
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}

}
