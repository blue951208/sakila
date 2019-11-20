package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActorController
 */
@WebServlet("/sakila/getActorList")
public class ActorController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actor Controller 확인");
		//데이터 타입 지정
		response.setContentType("application/json;charset=utf=8");
		//list 생성
		
		//service 호출,list 에 값 복사
		
		//gson 객체 생성
		
		//view 로 list를 gson 타입으로 전송
	}
}
