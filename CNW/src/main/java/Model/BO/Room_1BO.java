package Model.BO;

import java.util.List;

import Model.BEAN.Room;
import Model.BEAN.Room_1;
import Model.DAO.Room_1DAO;

public class Room_1BO {
	private Room_1DAO room_1DAO;

	public Room_1BO() {
		this.room_1DAO = new Room_1DAO();
	}
	public List<Room_1> getAllRooms(){
		return room_1DAO.getAllRooms();
	}
	
	public Room_1 getRoomByNumber (int roomNumber) {
		return room_1DAO.getRoomByNumber(roomNumber);
	}
}
