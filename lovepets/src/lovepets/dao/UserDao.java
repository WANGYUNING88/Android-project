package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lovepets.common.DbConnection;

import lovepets.bean.User;

public class UserDao {

	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private User usered = null;
	private User user;

	// login
	public User Login(String userName,String password) {

		sql = "select * from User where user_name= ? and user_password= ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {

				usered = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_password"),rs.getString("user_tel"),rs.getString("user_email"),rs.getString("user_sex"),rs.getString("user_image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return usered;

	}
    //通过user_id查找user
	public User found_user(int user_id) {

		sql = "select * from User where user_id= ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				 user = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_password"),rs.getString("user_tel"),rs.getString("user_email"),rs.getString("user_sex"),rs.getString("user_image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return user;

	}
	// register
	public boolean Register(User user) {

		sql = "insert into user(user_name,user_password,user_sex,user_tel,user_email,user_image) values(?,?,?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getUser_password());
			ps.setString(3, user.getUser_sex());
			ps.setString(4, user.getUser_tel());
			ps.setString(5, user.getUser_email());
			ps.setString(6, user.getUser_image());
			ps.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}
	public boolean Update(int id,User user) {
		sql = "update user set user_name=?,user_password=?,user_tel=?,user_email=?,user_sex=?,user_image=? where user_id=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getUser_password());
			ps.setString(3, user.getUser_tel());
			ps.setString(4, user.getUser_email());
			ps.setString(5, user.getUser_sex());
			ps.setString(6, user.getUser_image());
			ps.setInt(7, id);
			ps.execute();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

//FIND USER
	public User findUserByUsername(String username) {
		sql = "select * from User where user_name= ?";
		try {
			usered = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {

				usered = new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_password"),rs.getString("user_tel"),rs.getString("user_email"),rs.getString("user_sex"),rs.getString("user_image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return usered;

	}
}
