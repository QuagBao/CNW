package Model.BEAN;

public class Room_1 {
	private int roomNumber;
	private int roomTypeId;
	private boolean isbooked;
	private String roomType;
	private String image;
	private double price;
	private String room_des;
	
	public Room_1() {
		super();
	}

	public Room_1(int roomNumber, int roomTypeId, boolean isbooked, String roomType, String image, double price,
			String room_des) {
		super();
		this.roomNumber = roomNumber;
		this.roomTypeId = roomTypeId;
		this.isbooked = isbooked;
		this.roomType = roomType;
		this.image = image;
		this.price = price;
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
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRoom_des() {
		return room_des;
	}
	public void setRoom_des(String room_des) {
		this.room_des = room_des;
	}
	
	
}
