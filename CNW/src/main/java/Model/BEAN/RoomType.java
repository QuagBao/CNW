package Model.BEAN;

public class RoomType {
	private int roomtypeId;
	private String roomType;
	private double price;
	private String image;
	
	public RoomType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomType(int roomtypeId, String roomType, double price, String image) {
		super();
		this.roomtypeId = roomtypeId;
		this.roomType = roomType;
		this.price = price;
		this.image = image;
	}
	public int getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(int roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
