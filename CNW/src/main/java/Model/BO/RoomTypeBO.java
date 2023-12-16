package Model.BO;

import Model.BEAN.RoomType;
import Model.DAO.RoomTypeDAO;

import java.util.List;

public class RoomTypeBO {
    private RoomTypeDAO roomTypeDAO;

    public RoomTypeBO() {
        roomTypeDAO = new RoomTypeDAO();
    }

    // Phương thức lấy danh sách tất cả các loại phòng
    public List<RoomType> getAllRoomTypes() {
        return roomTypeDAO.getAllRoomTypes();
    }

    // Phương thức lấy thông tin một loại phòng dựa trên ID
    public RoomType getRoomTypeById(int roomTypeId) {
        return roomTypeDAO.getRoomTypeById(roomTypeId);
    }

    // Phương thức thêm một loại phòng mới
    public void addRoomType(RoomType roomType) {
        roomTypeDAO.addRoomType(roomType);
    }

    // Phương thức cập nhật thông tin một loại phòng
    public boolean updateRoomType(RoomType roomType) {
        return roomTypeDAO.updateRoomType(roomType);
    }

    // Phương thức xóa một loại phòng dựa trên ID
    public void deleteRoomType(int roomTypeId) {
        roomTypeDAO.deleteRoomType(roomTypeId);
    }
}
