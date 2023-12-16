package Model.DAO;

import Model.BEAN.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "07062020";

    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD = "SELECT * FROM users WHERE user_name=? AND password=?";
    private static final String INSERT_USER = "INSERT INTO users (full_name, user_name, password, role) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET full_name=?, user_name=?, password=?, role=? WHERE user_id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";

    // Phương thức lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Phương thức kiểm tra đăng nhập
    public User authenticateUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức lấy thông tin một người dùng dựa trên ID
    public User getUserById(int userId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm một người dùng mới
    public void addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRole());
            preparedStatement.executeUpdate();

            // Lấy giá trị tự động tăng của user_id (nếu cần)
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức cập nhật thông tin một người dùng
    public boolean updateUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRole());
            preparedStatement.setInt(5, user.getUserId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa một người dùng dựa trên ID
    public void deleteUser(int userId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức chuyển đổi ResultSet thành đối tượng User
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getInt("role"));
        return user;
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
