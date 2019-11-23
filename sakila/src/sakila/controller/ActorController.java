package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.dao.ActorDao;
import sakila.vo.Actor;

/**
 * Servlet implementation class ActorController
 */
@WebServlet("/getActorList")
public class ActorController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actor Controller 확인");
		//데이터 타입 지정
		response.setContentType("Application/json");//charset=utf=8");
		//list 생성
		List<Actor> list = new ArrayList();
		//dao 호출,list 에 값 복사
		ActorDao actorDao = new ActorDao();
		list = actorDao.selectActorList();
		System.out.println("actor List >>"+list);
		//gson 객체 생성
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(list);
		
		response.getWriter().write(json);
	}
}
