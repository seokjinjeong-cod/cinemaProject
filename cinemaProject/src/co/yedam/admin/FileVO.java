package co.yedam.admin;

public class FileVO {
	private String title;
	private String startdate;
	private String location;
	private String screentime;
	private String img;
	private String seatnum;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSeatnum() {
		return seatnum;
	}
	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}
	private int seatcnt;
	
	public int getSeatcnt() {
		return seatcnt;
	}
	public void setSeatcnt(int seatcnt) {
		this.seatcnt = seatcnt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getScreentime() {
		return screentime;
	}
	public void setScreentime(String screentime) {
		this.screentime = screentime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	

}
