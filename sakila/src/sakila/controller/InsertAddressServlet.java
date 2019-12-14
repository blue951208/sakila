package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.film.dao.AddressDao;
import sakila.vo.Address;

/**
 * Servlet implementation class InsertAddressServlet
 */
@WebServlet("/insertAddressServlet")
public class InsertAddressServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("입력 확인");
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String address=request.getParameter("address");
		String address2=request.getParameter("address2");
		String district=request.getParameter("district");
		String postalCode=request.getParameter("postalCode");
		String phone=request.getParameter("phone");
		Address addressOne = new Address();
		addressOne.setCityId(cityId);
		addressOne.setAddress(address2);
		addressOne.setAddress2(address2);
		addressOne.setDistrict(district);
		addressOne.setDistrict(district);
		addressOne.setPostalCode(postalCode);
		addressOne.setPhone(phone);
		System.out.println("값 체크"+addressOne);
		
		AddressDao addressDao = new AddressDao();
		int row = addressDao.insertAddress(addressOne);
		System.out.println("입력 확인?"+row);
	}

}
