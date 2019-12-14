package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.film.dao.CustomerDao;
import sakila.film.dao.FilmDao;
import sakila.film.dao.RentalDao;
import sakila.vo.Rental;

/**
 * Servlet implementation class InsertRentalServlet
 */
@WebServlet("/InsertRentalServlet")
public class InsertRentalServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("rental 입력");
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		System.out.println("고객 번호"+customerId);
		int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		Rental rental = new Rental();
		
		CustomerDao customerDao = new CustomerDao();
		
		rental.setCustomerId(customerId);
		rental.setInventoryId(inventoryId);
		rental.setStaffId(staffId);
		
		RentalDao rentalDao = new RentalDao();
		int row = rentalDao.insertRental(rental);
		System.out.println("확인:"+row);
	}

}
