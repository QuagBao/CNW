package Model.BEAN;

import java.util.Date;

public class Booking {
	private int bookingId;
	private int userId;
	private int roomNumber;
	private int roomtypeId;
	private Date checkinDate;
	private Date checkoutDate;
	private Double price;
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(int bookingId, int userId, int roomNumber, int roomtypeId, Date checkinDate, Date checkoutDate,
			Double price) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.roomNumber = roomNumber;
		this.roomtypeId = roomtypeId;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.price = price;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(int roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	

}
