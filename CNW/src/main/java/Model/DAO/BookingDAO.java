package Model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import Model.BEAN.Booking;

public class BookingDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "07062020";
    
    private static final String SELECT_ALL_BOOKINGS = "SELECT * FROM bookings";
    private static final String SELECT_BOOKING_BY_ID = "SELECT * FROM bookings WHERE booking_id=?";
    private static final String SELECT_BOOKING_BY_USERID = "SELECT * FROM bookings WHERE user_id=?";
    private static final String INSERT_BOOKING = "INSERT INTO bookings (user_id, room_number, room_type_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOKING = "UPDATE bookings SET user_id=?, room_id=?, room_type_id=?, check_in_date=?, check_out_date=? WHERE booking_id=?";
    private static final String DELETE_BOOKING = "DELETE FROM bookings WHERE booking_id=?";

    // Phương thức lấy danh sách tất cả các đặt phòng
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKINGS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Booking booking = mapResultSetToBooking(resultSet);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Phương thức lấy thông tin một đặt phòng dựa trên ID
    public Booking getBookingById(int bookingId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_BY_ID)) {
            preparedStatement.setInt(1, bookingId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBooking(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 // Phương thức thêm một đặt phòng mới
    public void addBooking(Booking booking) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(2, booking.getUserId());
            preparedStatement.setInt(3, booking.getRoomNumber());
            preparedStatement.setInt(4, booking.getRoomtypeId());

            // Chuyển đổi từ java.util.Date sang java.sql.Date
            Date checkinDate = new Date(booking.getCheckinDate().getTime());
            Date checkoutDate = new Date(booking.getCheckoutDate().getTime());

            preparedStatement.setDate(5, checkinDate);
            preparedStatement.setDate(6, checkoutDate);
            preparedStatement.setDouble(7, booking.getPrice());
            
            preparedStatement.executeUpdate();

            // Lấy giá trị tự động tăng của bookingId (nếu cần)
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Phương thức cập nhật thông tin một đặt phòng
    public boolean updateBooking(Booking booking) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING)) {
        	preparedStatement.setInt(1, booking.getBookingId());
        	preparedStatement.setInt(2, booking.getUserId());
            preparedStatement.setInt(3, booking.getRoomNumber());
            preparedStatement.setInt(4, booking.getRoomtypeId());

            // Chuyển đổi từ java.util.Date sang java.sql.Date
            java.sql.Date checkinDate = new java.sql.Date(booking.getCheckinDate().getTime());
            java.sql.Date checkoutDate = new java.sql.Date(booking.getCheckoutDate().getTime());

            preparedStatement.setDate(5, checkinDate);
            preparedStatement.setDate(6, checkoutDate);
            preparedStatement.setDouble(7, booking.getPrice());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Phương thức xóa một đặt phòng dựa trên ID
    public void deleteBooking(int bookingId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING)) {
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức chuyển đổi ResultSet thành đối tượng Booking
    private Booking mapResultSetToBooking(ResultSet resultSet) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(resultSet.getInt("booking_id"));
        booking.setUserId(resultSet.getInt("user_id"));
        booking.setRoomNumber(resultSet.getInt("room_number"));
        booking.setRoomtypeId(resultSet.getInt("room_type_id"));
        booking.setCheckinDate(resultSet.getDate("check_in_date"));
        booking.setCheckoutDate(resultSet.getDate("check_out_date"));
        return booking;
    }
    
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }
}
