package lovepets.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import lovepets.bean.User;
import lovepets.bean.found;
import lovepets.common.DbConnection;


public class FoundDao {
	private int found_id;
	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps = null;
	private ResultSet eq = null;
	private String sql;
	private found found;
	
	

	public found selectByFound(found found) {
		sql = "select * from found order by found_id desc limit 1";
		try {
			ps = connection.prepareStatement(sql);
			eq = ps.executeQuery();
			if (eq.next()) {
				
	
			 found = new found();
				found.setFound_id(eq.getInt("found_id"));
				found.setUser_id(eq.getInt("user_id"));
				found.setFound_date(eq.getDate("found_date"));
				found.setFound_content(eq.getString("found_content"));
				
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
		return found;

	}
	public List<found> selectAllfound(){
		List<found> list = new ArrayList<found>();
		try {
			ps = connection.prepareStatement("select * from found order by found_id desc");
			eq = ps.executeQuery();
			while(eq.next()) {
				found found = new found();
				found.setFound_id(eq.getInt("found_id"));
				found.setUser_id(eq.getInt("user_id"));
				found.setFound_date(eq.getDate("found_date"));
				found.setFound_content(eq.getString("found_content"));
				
				list.add(found);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				eq.close();
				ps.close();
				 
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

//found发布
public boolean fabu(found found) {

    sql = "insert into found(user_id,found_date,found_content) values(?,?,?)";
	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1,found.getUser_id());
		ps.setDate(2, found.getFound_date());
		ps.setString(3,found.getFound_content());
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