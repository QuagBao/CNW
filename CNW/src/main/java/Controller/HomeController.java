//package Controller;
//
//import Model.BO.RoomBO;
//import Model.BO.UserBO;
//import Model.BEAN.Room;
//import Model.BEAN.User;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/HomeController")
//public class HomeController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private RoomBO roomBO;
//    private UserBO userBO;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        roomBO = new RoomBO();
//        userBO = new UserBO();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("login".equals(action)) {
//            // Nếu người dùng chọn đăng nhập, chuyển hướng đến trang đăng nhập
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
//        } else if ("logout".equals(action)) {
//            // Nếu người dùng chọn đăng xuất, hủy session và chuyển hướng đến trang chính
//            HttpSession session = request.getSession();
//            session.invalidate();
//            response.sendRedirect("HomeController");
//        } else {
//            showRoomList(request, response);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("login".equals(action)) {
//            // Xử lý đăng nhập
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//
//            // Kiểm tra đăng nhập
//            User authenticatedUser = userBO.authenticateUser(username, password);
//
//            if (authenticatedUser != null) {
//                // Đăng nhập thành công, lưu thông tin người dùng vào session
//                HttpSession session = request.getSession();
//                session.setAttribute("authenticatedUser", authenticatedUser);
//
//                // Chuyển hướng đến trang tương ứng với role
//                if (authenticatedUser.getRole() == 1) {
//                    // Role 1: Khách hàng, chuyển hướng đến trang home.jsp
//                    response.sendRedirect("home.jsp");
//                } else if (authenticatedUser.getRole() == 2) {
//                    // Role 2: Admin, chuyển hướng đến trang admin.jsp
//                    response.sendRedirect("admin.jsp");
//                }
//            } else {
//                // Đăng nhập không thành công, chuyển hướng đến trang đăng nhập với thông báo lỗi
//                request.setAttribute("errorMessage", "Invalid username or password");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//                dispatcher.forward(request, response);
//            }
//        } else {
//            showRoomList(request, response);
//        }
//    }
//
//    private void showRoomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	List<Room> listRooms = roomBO.getAllRooms();
//        System.out.println("Number of rooms: " + listRooms.size()); // Log để kiểm tra số lượng phòng
//        request.setAttribute("rooms", listRooms);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//        dispatcher.forward(request, response);
//    }
//}
