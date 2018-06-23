package lovepets.bean;

public class User{
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_tel;
	private String user_email;
	private String user_sex;
	private String user_image;


	public User(){

	}

	public User(String username,String userpassword) {

        user_name = username;
        user_password = userpassword;

    }

    public User(String username,String userpassword,String userimage) {

        user_name = username;
        user_password = userpassword;
        user_image = userimage;
    }


    public User(String username,String userpassword,String usersex,String usertel,String useremail,String userimage) {

        user_name = username;
        user_password = userpassword;
        user_tel = usertel;
        user_email= useremail;
        user_sex = usersex;
        user_image = userimage;
    }
    public User(int userid,String username,String userpassword,String usertel,String useremail,String userimage) {
        user_id = userid;
        user_name = username;
        user_password = userpassword;
        user_tel = usertel;
        user_email = useremail;
        user_image = userimage;

    }
    

public User(int userid,String username,String userpassword,String usertel,String useremail,String usersex,String userimage) {
    user_id = userid;
    user_name = username;
    user_password = userpassword;
    user_tel = usertel;
    user_email = useremail;
    user_sex = usersex;
    user_image = userimage;
    
}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}


}
