package Model.BO;

import Model.BEAN.User;
import Model.DAO.UserDAO;

import java.util.List;

public class UserBO {
    private UserDAO userDAO;

    public UserBO() {
        this.userDAO = new UserDAO();
    }

    // Phương thức lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Phương thức kiểm tra đăng nhập và trả về thông tin người dùng nếu đăng nhập thành công
    public User authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }

    // Phương thức lấy thông tin một người dùng dựa trên ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Phương thức thêm một người dùng mới
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    // Phương thức cập nhật thông tin một người dùng
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    // Phương thức xóa một người dùng dựa trên ID
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}
