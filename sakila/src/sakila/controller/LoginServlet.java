package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import sakila.film.dao.StaffDao;
import sakila.vo.Staff;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int staffId = Integer.parseInt(request.getParameter("staffId"));
			String password = request.getParameter("password");
			
			response.setContentType("Application/json;charset=utf=8");
			int row = 0;
			//staff table 에서 id번호와 페스워드 일치시 row =1 을 리턴 
			Staff staff = new Staff();
			staff.setStaffId(staffId);
			staff.setPassword(password);
			StaffDao staffDao = new StaffDao();
			
			Staff staffOne = staffDao.selectStaffOne(staff);
			System.out.println("확인:"+staffOne);
			
			HttpSession session = request.getSession();
			 //session 에 값을 저장 , sessionInfo 라는 키워드로 
			session.setAttribute("sessionInfo",staffOne.getStaffId());
			
		}

}
