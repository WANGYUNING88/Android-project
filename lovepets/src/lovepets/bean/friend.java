package lovepets.bean;

public class friend {
    private int friend_id;
    private int user_id;
    private int friend_user_id;
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFriend_user_id() {
		return friend_user_id;
	}
	public void setFriend_user_id(int friend_user_id) {
		this.friend_user_id = friend_user_id;
	}
	public friend() {
		
	}
	public friend(int userid,int frienduserid) {
		user_id=userid;
		friend_user_id=frienduserid;
		
	}
	public friend(int friendid,int userid,int frienduserid) {
		user_id=userid;
		friend_user_id=frienduserid;
		
	}
}
