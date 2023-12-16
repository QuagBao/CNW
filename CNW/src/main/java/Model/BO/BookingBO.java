package Model.BO;

import Model.BEAN.Booking;
import Model.DAO.BookingDAO;

import java.util.List;

public class BookingBO {
    private BookingDAO bookingDAO;

    public BookingBO() {
        bookingDAO = new BookingDAO();
    }

    // Phương thức lấy danh sách tất cả các đặt phòng
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    // Phương thức lấy thông tin một đặt phòng dựa trên ID
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    // Phương thức thêm một đặt phòng mới
    public void addBooking(Booking booking) {
        bookingDAO.addBooking(booking);
    }

    // Phương thức cập nhật thông tin một đặt phòng
    public boolean updateBooking(Booking booking) {
        return bookingDAO.updateBooking(booking);
    }

    // Phương thức xóa một đặt phòng dựa trên ID
    public void deleteBooking(int bookingId) {
        bookingDAO.deleteBooking(bookingId);
    }
}
