package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.dao.ActorDao;
import sakila.film.dao.FilmDao;
import sakila.film.dao.RentalDao;
import sakila.vo.Paging;

/**
 * Servlet implementation class GetLastPage
 */
@WebServlet("/getLastPage")
public class GetLastPage extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int menuNo = Integer.parseInt(request.getParameter("menuNo"));
		System.out.println("전체 페이지 확인"+menuNo);
		response.setContentType("Application/json;charset=utf=8");
		int count=0;
			if(menuNo==3) {
				ActorDao actorDao = new ActorDao();
				count = actorDao.selectCount();
			}else if(menuNo==2) {
				FilmDao filmDao = new FilmDao();
				//count = FilmDao.selectCount();
			}else if(menuNo==1) {
				RentalDao rentalDao = new RentalDao();
				String name = request.getParameter("name");
				count = rentalDao.selectCount(name);
			}
		int rowPerPage =10;
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		System.out.println("last:"+lastPage);
		Paging paging = new Paging();
		paging.setLastPage(lastPage);
		Gson gson = new Gson();
		//view 로 list를 gson 타입으로 전송
		String json = gson.toJson(paging);
		
		response.getWriter().write(json);
	}

}