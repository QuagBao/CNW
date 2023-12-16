package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.Room;
import Model.BEAN.Room_1;
import Model.BEAN.User;
import Model.BO.RoomBO;
import Model.BO.Room_1BO;
import Model.BO.UserBO;

@WebServlet("/HomeController_1")
public class HomeController_1 extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private RoomBO roomBO = new RoomBO();
    private Room_1BO room_1BO = new Room_1BO();
    private UserBO userBO = new UserBO();
    
	public HomeController_1() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			// Nếu người dùng chọn đăng nhập, chuyển hướng đến trang đăng nhập
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		//Đăng xuất
        if ("logout".equals(action)) {
            // Nếu người dùng chọn đăng xuất, hủy session và chuyển hướng đến trang chính
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("HomeController_1");
        } else {
        	doPost(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String destination = null;
		
//		Đăng nhập
		if("login".equals(action)) {
			// Xử lý đăng nhập
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			// Kiểm tra đăng nhập
			User authenticatedUser = userBO.authenticateUser(username, password);
			if (authenticatedUser != null) {
				// Đăng nhập thành công, lưu thông tin người dùng vào session
				HttpSession session = request.getSession();
				session.setAttribute("authenticatedUser", authenticatedUser);

				// Chuyển hướng đến trang tương ứng với role
				if (authenticatedUser.getRole() == 1) {
					// Role 1: Khách hàng, chuyển hướng đến trang home.jsp
					destination = "/home.jsp";
					List<Room_1> listRooms = room_1BO.getAllRooms();	
					System.out.println("Number of rooms: " + listRooms.size()); // Log để kiểm tra số lượng phòng
					request.setAttribute("listRooms", listRooms);
					RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
					rd.forward(request, response);
				} else if(authenticatedUser.getRole() == 2) {
					// Role 2: Admin, chuyển hướng đến trang admin.jsp
//					destination = "/home.jsp";
//					List<Room_1> listRooms = room_1BO.getAllRooms();	
//					System.out.println("Number of rooms: " + listRooms.size()); // Log để kiểm tra số lượng phòng
//					request.setAttribute("listRooms", listRooms);
//					RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
//					rd.forward(request, response);
				}
			} else {
//				 Đăng nhập không thành công, chuyển hướng đến trang đăng nhập với thông báo lỗi
				destination = "/login.jsp";
				request.setAttribute("errorMessage", "Invalid username or password");
				RequestDispatcher rd = request.getRequestDispatcher(destination);
				rd.forward(request, response);
			}
		} else {
			destination = "/index.jsp";
			List<Room_1> listRooms_1 = room_1BO.getAllRooms();	
			System.out.println("Number of rooms: " + listRooms_1.size()); // Log để kiểm tra số lượng phòng
			request.setAttribute("listRooms_1", listRooms_1);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}
}
