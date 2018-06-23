package lovepets.bean;

public class image {

	private int image_id;
	private int found_id;
	private String image_path;
	private int user_id;
	public image(){
        
    }
	public int getFound_id() {
		return found_id;
	}
	public void setFound_id(int found_id) {
		this.found_id = found_id;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	
	 public image(int foundid,int userid,String imagepath){
	        found_id=foundid;
	        user_id=userid;
	        image_path=imagepath;
	    }
}
