package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import Model.BEAN.Room;

public class RoomDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "07062020";
    
    private static final String SELECT_ALL_ROOMS = "SELECT * FROM rooms";
    private static final String SELECT_ROOM_BY_NUMBER = "SELECT * FROM rooms WHERE room_number=?";
    private static final String INSERT_ROOM = "INSERT INTO rooms (room_number, room_type_id, is_booked, room_des) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ROOM = "UPDATE rooms SET room_type_id=?, is_booked=?, room_des=? WHERE room_number=?";
    private static final String DELETE_ROOM = "DELETE FROM rooms WHERE room_number=?";

    // Phương thức lấy danh sách tất cả các phòng
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Room room = mapResultSetToRoom(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Phương thức lấy thông tin một phòng dựa trên ID
    public Room getRoomByNumber(int roomNumber) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_BY_NUMBER)) {
            preparedStatement.setInt(1, roomNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRoom(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm một phòng mới
    public void addRoom(Room room) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM)) {
            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setInt(2, room.getRoomTypeId());
            preparedStatement.setBoolean(3, room.isIsbooked());
            preparedStatement.setString(4, room.getRoom_des());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Phương thức cập nhật thông tin một phòng
    public boolean updateRoom(Room room) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROOM)) {

            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setInt(2, room.getRoomTypeId());
            preparedStatement.setBoolean(3, room.isIsbooked());
            preparedStatement.setString(4, room.getRoom_des());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa một phòng dựa trên ID
    public void deleteRoom(int roomId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROOM)) {   	
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
        }
    }

    // Phương thức chuyển đổi ResultSet thành đối tượng RoomBean    
    private Room mapResultSetToRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setRoomNumber(resultSet.getInt("room_number"));
        room.setRoomTypeId(resultSet.getInt("room_type_id"));
        room.setIsbooked(resultSet.getBoolean("is_booked"));
        room.setRoom_des(resultSet.getString("is_booked"));
        return room;
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
