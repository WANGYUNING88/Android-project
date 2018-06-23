package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lovepets.bean.MessageBean;
import lovepets.common.DbConnection;

public class MessageDao {
	
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	
	//添加聊天记录
	public boolean addMessage(MessageBean messageBean) {
		
		sql = "insert into message(message_content,send_id,receive_id) "
				+ "values(?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, messageBean.getMessage_content());
			ps.setInt(2, messageBean.getSend_id());
			ps.setInt(3, messageBean.getReceive_id());
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	
}
