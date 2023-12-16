package Model.BEAN;

public class Room{
	private int roomNumber;
	private int roomTypeId;
	private boolean isbooked;
	private String room_des;
	
	
	public Room() {
		super();
	}
	public Room(int roomNumber, int roomTypeId, boolean isbooked, String room_des) {
		super();
		this.roomNumber = roomNumber;
		this.roomTypeId = roomTypeId;
		this.isbooked = isbooked;
		this.room_des = room_des;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public int getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public boolean isIsbooked() {
		return isbooked;
	}
	public void setIsbooked(boolean isbooked) {
		this.isbooked = isbooked;
	}
	public String getRoom_des() {
		return room_des;
	}
	public void setRoom_des(String room_des) {
		this.room_des = room_des;
	}
	
	
}