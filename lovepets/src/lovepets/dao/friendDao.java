package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lovepets.bean.User;
import lovepets.bean.friend;
import lovepets.bean.image;
import lovepets.common.DbConnection;

public class friendDao {
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private friend friend;
	private List<String> fids =new ArrayList<>();
	private List<String> fidss =new ArrayList<>();
	 
	// chaxun
		public friend chaxun(int user_id,int friend_user_id) {

			sql = "select * from friend where user_id= ? and friend_user_id= ?";
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, friend_user_id);
				rs = ps.executeQuery();
				if (rs.next()) {

				friend= new friend(
						rs.getInt("friend_id"),
						rs.getInt("user_id"),
						rs.getInt("friend_user_id"));
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

			return friend;
}
		
		// addguanzhu
		public boolean add(int user_id,int friend_user_id ) {

			sql = "insert into friend(user_id,friend_user_id) values(?,?)";
			try {
				ps = connection.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, friend_user_id);
				
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
		//查找关注id
		public List<String> selectByUser_id(int user_id) {
			// TODO Auto-generated method stub
			
			try {
				 ps = connection.prepareStatement("select * from friend where user_id= ?");			 
				 ps.setInt(1,user_id);
				 rs = ps.executeQuery();
				while(rs.next()) {
					friend friend = new friend();
					friend.setFriend_user_id(rs.getInt("friend_user_id"));
					String str = String.valueOf(friend.getFriend_user_id());
					
					fids.add(str);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					ps.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("6595   "+fids);
			return fids;
		}
		
		//查找粉丝id
				public List<String> selectByFriend_user_id(int user_id) {
					// TODO Auto-generated method stub
					
					try {
						 ps = connection.prepareStatement("select * from friend where friend_user_id= ?");			 
						 ps.setInt(1,user_id);
						 rs = ps.executeQuery();
						while(rs.next()) {
							friend friend = new friend();
							friend.setUser_id(rs.getInt("user_id"));
							String str = String.valueOf(friend.getUser_id());
							
							fidss.add(str);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							rs.close();
							ps.close();
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("6595   "+fidss);
					return fidss;
				}
}
