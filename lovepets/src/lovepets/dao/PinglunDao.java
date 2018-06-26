package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lovepets.bean.found;
import lovepets.bean.pinglun;
import lovepets.common.DbConnection;

public class PinglunDao {
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps = null;
	private ResultSet eq = null;
	private String sql;
	private List<pinglun> pls = new ArrayList<>();
	public boolean pinglunIn(pinglun pinglun) {

	    sql = "insert into pinglun(found_id,user_id,pinglun_content) values(?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1,pinglun.getFound_id());
			ps.setInt(2, pinglun.getUser_id());
			ps.setString(3,pinglun.getPinglun_content());
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
	public List<pinglun> selectByFoundid(int id) {
		sql = "select * from pinglun where found_id= ? order by pinglun_id desc";
		try {
			System.out.println(id);
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			eq = ps.executeQuery();
			while (eq.next()) {
				pinglun pl = new pinglun();
				pl.setPinglun(eq.getInt("pinglun_id"));
				pl.setFound_id(eq.getInt("found_id"));
				
				pl.setUser_id(eq.getInt("user_id"));
				pl.setPinglun_content(eq.getString("pinglun_content"));
				pls.add(pl);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				eq.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return pls;

	}
}
