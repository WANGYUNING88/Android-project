package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lovepets.bean.dianzan;
import lovepets.bean.friend;
import lovepets.common.DbConnection;

public class dianzanDao {
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private dianzan dianzan;
	private List<String> list = new ArrayList<>();
	public List<String> selectfidByuid(int user_id) {

		sql = "select * from dianzan where user_id= ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				dianzan dianzan = new dianzan();
				dianzan.setFound_id(rs.getInt("found_id"));
				String str = String.valueOf(dianzan.getFound_id());
				list.add(str);
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

		return list;
	}
	// chaxun
	public dianzan chaxun(int user_id, int found_id) {

		sql = "select * from dianzan where user_id= ? and found_id= ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, found_id);
			rs = ps.executeQuery();
			if (rs.next()) {

				dianzan = new dianzan(rs.getInt("user_id"), rs.getInt("found_id"));
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

		return dianzan;
	}

	// adddianzan
	public boolean add(int user_id, int found_id) {

		sql = "insert into dianzan(user_id,found_id) values(?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, found_id);

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

	//delete dianzan
	public boolean delete(int user_id,int found_id) {

		
		try {
			ps = connection.prepareStatement("delete from dianzan where user_id=? and found_id=?");
			ps.setInt(1, user_id);
			ps.setInt(2, found_id);
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
}
