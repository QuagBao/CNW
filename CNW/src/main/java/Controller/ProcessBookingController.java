package Controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.Booking;
import Model.BEAN.User;
import Model.BO.BookingBO;

/**
 * Servlet implementation class ProcessBookingController
 */
@WebServlet("/ProcessBookingController")
public class ProcessBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessBookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String checkInDateStr = request.getParameter("checkInDate");
		System.out.println(checkInDateStr);
		Date checkInDate = Date.valueOf(request.getParameter("checkInDate")) ;
		Date checkOutDate =  Date.valueOf(request.getParameter("checkOutDate"));
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		int roomTypeId = Integer.parseInt(request.getParameter("roomTypeId"));
		double price = Double.parseDouble(request.getParameter("price"));
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("authenticatedUser");
		int ID = user.getUserId();
		
		Booking booking = new Booking();
		booking.setUserId(ID);
		booking.setRoomtypeId(roomTypeId);
		booking.setRoomNumber(roomNumber);
		booking.setPrice(price);
		booking.setCheckinDate(checkInDate);
		booking.setCheckoutDate(checkOutDate);
		
		BookingBO bookBO = new BookingBO();
		bookBO.addBooking(booking);
	}
	
}
