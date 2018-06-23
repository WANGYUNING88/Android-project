package lovepets.bean;

import java.sql.Date;


public class found {

	private int found_id;
	private int user_id;
	private Date found_date;
	private String found_content;
	
	
	
	public int getFound_id() {
		return found_id;
	}
	public void setFound_id(int found_id) {
		this.found_id = found_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getFound_date() {
		return found_date;
	}
	public void setFound_date(Date found_date) {
		this.found_date = found_date;
	}
	public String getFound_content() {
		return found_content;
	}
	public void setFound_content(String found_content) {
		this.found_content = found_content;
	}
	 public found(){
		 
	 }
	 public found(int userid, Date founddate, String foundcontent) {
	        user_id = userid;
	        found_date = founddate;
	        found_content=foundcontent;
	        

	    }
	 public found(int foundid,int userid, Date founddate, String foundcontent) {
	     found_id=foundid;  
		 user_id = userid;
	        found_date = founddate;
	        found_content=foundcontent;
	        

	    }
}
