package lovepets.bean;

public class dianzan {
    private int user_id;
    private int found_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFound_id() {
		return found_id;
	}
	public void setFound_id(int found_id) {
		this.found_id = found_id;
	}
	
	public dianzan() {
		
	}
	public dianzan(int userid,int foundid) {
		user_id=userid;
		found_id=foundid;
	}
}
