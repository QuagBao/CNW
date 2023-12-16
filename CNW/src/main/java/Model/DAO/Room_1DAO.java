package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.RoomType;
import Model.BEAN.Room_1;

public class Room_1DAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "07062020";
    
    private static final String SHOW_ALL_ROOM = "SELECT *\r\n"
    		+ "FROM room_types\r\n"
    		+ "INNER JOIN rooms ON room_types.room_type_id = rooms.room_type_id;\r\n"
    		+ "";
    private static final String SELECT_ROOM_TYPE_BY_NUMBER = "SELECT *\r\n"
    		+ "FROM room_types\r\n"
    		+ "INNER JOIN rooms ON room_types.room_type_id = rooms.room_type_id\r\n"
    		+ "WHERE rooms.room_number = ?;";
    private static final String INSERT_ROOM_TYPE = "INSERT INTO room_types (room_type, price) VALUES (?, ?)";
    private static final String UPDATE_ROOM_TYPE = "UPDATE room_types SET room_type=?, price=? WHERE room_type_id=?";
    private static final String DELETE_ROOM_TYPE = "DELETE FROM room_types WHERE room_type_id=?";
	private static final boolean Room_1 = false;
    
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }
 // Phương thức chuyển đổi ResultSet thành đối tượng RoomType
    private Room_1 mapResultSetToRoomType(ResultSet resultSet) throws SQLException {
    	Room_1 room_1 = new Room_1();
    	room_1.setRoomNumber(resultSet.getInt("room_number"));
    	room_1.setRoomTypeId(resultSet.getInt("room_type_id"));
    	room_1.setImage(resultSet.getString("image"));
    	room_1.setRoomType(resultSet.getString("room_type"));
    	room_1.setPrice(resultSet.getDouble("price"));
    	room_1.setIsbooked(resultSet.getBoolean("is_booked"));
    	room_1.setRoom_des(resultSet.getString("room_des"));
    	return room_1;
    }
 // Phương thức lấy danh sách tất cả các loại phòng
    public List<Room_1> getAllRooms() {
        List<Room_1> rooms = new ArrayList<>();
        try (Connection connection = getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_ROOM);
        		ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Room_1 room = mapResultSetToRoomType(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    
 // Phương thức lấy thông tin một phòng dựa trên room_number
    public Room_1 getRoomByNumber (int roomNumber) {
    	try (Connection connection = getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_TYPE_BY_NUMBER)) {
           preparedStatement.setInt(1, roomNumber);
           try( ResultSet rs = preparedStatement.executeQuery()){
        	   if (rs.next()) {
        		   return mapResultSetToRoomType(rs);
        	   }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 
//    public static void main(String[] args) {
//		Room_1 room= new Room_1();
//		Room_1DAO room_1DAO = new Room_1DAO();
//		room = room_1DAO.getRoomByNumber(102);
//		System.out.println(room);
//	}
}
