package Model.BO;

import java.util.List;

import Model.BEAN.Room;
import Model.DAO.RoomDAO;

public class RoomBO {
	private RoomDAO roomDAO;
	
	public RoomBO() {
		this.roomDAO = new RoomDAO();
	}
	
	public List<Room> getAllRooms(){
		return roomDAO.getAllRooms();
	}
	public Room getRoomByNumber(int roomId) {
        return roomDAO.getRoomByNumber(roomId);
    }
	
	
}
