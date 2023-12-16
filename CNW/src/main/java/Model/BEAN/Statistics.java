package Model.BEAN;

public class Statistics {
	private int statisticId;
	private int totalRoom;
	private int bookedRoom;
	private double revenue;
	
	public int getStatisticId() {
		return statisticId;
	}
	public void setStatisticId(int statisticId) {
		this.statisticId = statisticId;
	}
	public int getTotalRoom() {
		return totalRoom;
	}
	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}
	public int getBookedRoom() {
		return bookedRoom;
	}
	public void setBookedRoom(int bookedRoom) {
		this.bookedRoom = bookedRoom;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
}
