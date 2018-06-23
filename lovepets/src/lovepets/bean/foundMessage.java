package lovepets.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class foundMessage {

	private List<String> imageurl=new ArrayList<>();
	private String username;
	private String userimage;
	private Date date;
	private String fconnect;
	public List<String> getImageurl() {
		return imageurl;
	}
	public void setImageurl(List<String> imageurl) {
		this.imageurl = imageurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFconnect() {
		return fconnect;
	}
	public void setFconnect(String fconnect) {
		this.fconnect = fconnect;
	}
	

}
