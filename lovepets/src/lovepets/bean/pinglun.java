package lovepets.bean;

public class pinglun {
    private int pinglun;
    private int found_id;
    private int user_id;
    private String pinglun_content;
	public int getPinglun() {
		return pinglun;
	}
	public void setPinglun(int pinglun) {
		this.pinglun = pinglun;
	}
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
	public String getPinglun_content() {
		return pinglun_content;
	}
	public void setPinglun_content(String pinglun_content) {
		this.pinglun_content = pinglun_content;
	}
	public pinglun() {
		
	}
	public pinglun(int foundid,int userid,String pingluncontent) {
		
		found_id=foundid;
		user_id=userid;
		pinglun_content=pingluncontent;
	}
}
