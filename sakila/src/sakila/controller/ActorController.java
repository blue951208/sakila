package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.ActorDao;
import sakila.vo.Actor;

/**
 * Servlet implementation class ActorController
 */
@WebServlet("/getActorList")
public class ActorController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actor Controller 확인");
		//데이터 타입 지정
		response.setContentType("Application/json");//charset=utf=8");
		//list 생성
		List<Actor> list = new ArrayList();
		//dao 호출,list 에 값 복사
		ActorDao actorDao = new ActorDao();
	/*
		//paging-- lastPage,currentPage,rowPerPage 설정
		int count = actorDao.selectCount();
		int rowPerPage = 10;
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage += 1;
		}
		int currentPage =1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage")); 
			System.out.println("page>"+currentPage);
		}
		int beginRow = (currentPage-1)*rowPerPage;
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("currentPage", currentPage);
		map.put("lastPage", lastPage);	*/
		list = actorDao.selectActorList();//beginRow,rowPerPage);
		System.out.println("actor List >>"+list);
		//gson 객체 생성
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}
}
