package co.yedam.cinema;

public class TicketingVO {
	
	private String Id;
	private String title;
	private String ticketDate;
	private String location;
	private String time;
	private String seatNum;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	@Override
	public String toString() {
		return "TicketingVO [Id=" + Id + ", title=" + title + ", ticketDate=" + ticketDate + ", location=" + location
				+ ", time=" + time + ", seatNum=" + seatNum + "]";
	}
	
	
}
