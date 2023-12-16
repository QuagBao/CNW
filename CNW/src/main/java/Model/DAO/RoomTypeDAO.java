package Model.DAO;

import Model.BEAN.RoomType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "07062020";
    
    private static final String SELECT_ALL_ROOM_TYPES = "SELECT * FROM room_types";
    private static final String SELECT_ROOM_TYPE_BY_ID = "SELECT * FROM room_types WHERE room_type_id=?";
    private static final String INSERT_ROOM_TYPE = "INSERT INTO room_types (room_type, price, image) VALUES (?, ?, ?)";
    private static final String UPDATE_ROOM_TYPE = "UPDATE room_types SET room_type=?, price=?, image=? WHERE room_type_id=?";
    private static final String DELETE_ROOM_TYPE = "DELETE FROM room_types WHERE room_type_id=?";

    // Phương thức lấy danh sách tất cả các loại phòng
    public List<RoomType> getAllRoomTypes() {
        List<RoomType> roomTypes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOM_TYPES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RoomType roomType = mapResultSetToRoomType(resultSet);
                roomTypes.add(roomType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomTypes;
    }

    // Phương thức lấy thông tin một loại phòng dựa trên ID
    public RoomType getRoomTypeById(int roomTypeId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_TYPE_BY_ID)) {
            preparedStatement.setInt(1, roomTypeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRoomType(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm một loại phòng mới
    public void addRoomType(RoomType roomType) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM_TYPE)) {
        	preparedStatement.setString(2, roomType.getRoomType());
            preparedStatement.setDouble(3, roomType.getPrice());
            preparedStatement.setString(4, roomType.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức cập nhật thông tin một loại phòng
    public boolean updateRoomType(RoomType roomType) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROOM_TYPE)) {
            preparedStatement.setString(2, roomType.getRoomType());
            preparedStatement.setDouble(3, roomType.getPrice());
            preparedStatement.setString(4, roomType.getImage());
            
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa một loại phòng dựa trên ID
    public void deleteRoomType(int roomTypeId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROOM_TYPE)) {
            preparedStatement.setInt(1, roomTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức chuyển đổi ResultSet thành đối tượng RoomType
    private RoomType mapResultSetToRoomType(ResultSet resultSet) throws SQLException {
        RoomType roomType = new RoomType();
        roomType.setRoomtypeId(resultSet.getInt("room_type_id"));
        roomType.setRoomType(resultSet.getString("room_type"));
        roomType.setRoomType(resultSet.getString("image"));
        roomType.setPrice(resultSet.getDouble("price"));
        return roomType;
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
