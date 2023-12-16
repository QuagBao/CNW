package Controller;

import Model.BO.RoomBO;
import Model.BEAN.Room;
import Model.BEAN.RoomType;
import Model.BO.RoomTypeBO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomBO roomBO = new RoomBO();
    private RoomTypeBO roomTypeBO = new RoomTypeBO();

    @Override
    public void init() throws ServletException {
        super.init(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showBookingForm(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý đặt phòng
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        LocalDate checkInDate = LocalDate.parse(request.getParameter("checkInDate"));
        LocalDate checkOutDate = LocalDate.parse(request.getParameter("checkOutDate"));

        // Kiểm tra và xử lý logic đặt phòng tại đây

        // (Ví dụ: Hiện thông tin đặt phòng hoặc chuyển hướng đến trang thanh toán)

        // Ví dụ chuyển hướng đến trang thanh toán
//        response.sendRedirect("payment.jsp");
    }

    private void showBookingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy roomId từ query parameter
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));

        // Truy vấn thông tin phòng từ cơ sở dữ liệu
        Room room = roomBO.getRoomByNumber(roomNumber);

        // Lấy danh sách loại phòng để hiển thị thông tin
        List<RoomType> roomTypes = roomTypeBO.getAllRoomTypes();

        request.setAttribute("room", room);
        request.setAttribute("roomTypes", roomTypes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
        dispatcher.forward(request, response);
    }
}
