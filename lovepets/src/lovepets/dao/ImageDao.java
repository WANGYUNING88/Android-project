package lovepets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lovepets.bean.image;
import lovepets.common.DbConnection;

public class ImageDao {

	private Connection connection = DbConnection.getConnection();
	private PreparedStatement ps;
	private ResultSet rs = null;
	private String sql;
	private List<String> images = new ArrayList<>();
    public boolean insertImage(image image) {
    	sql = "insert into image(found_id,image_path,user_id) values(?,?,?)";
    	try {
    		ps = connection.prepareStatement(sql);
    		ps.setInt(1,image.getFound_id());
    		ps.setString(2,image.getImage_path());
    		ps.setInt(3,image.getUser_id());
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
    public List<String> selectByUser_id(int user_id) {
		// TODO Auto-generated method stub
		
		try {
			 ps = connection.prepareStatement("select * from image where user_id= ?");			 
			 ps.setInt(1,user_id);
			 rs = ps.executeQuery();
			while(rs.next()) {
				image image = new image();
				image.setImage_id(rs.getInt("image_id"));
				image.setFound_id(rs.getInt("found_id"));
				
				image.setImage_path(rs.getString("image_path"));
				image.setUser_id(rs.getInt("user_id"));
		
				images.add(image.getImage_path());
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
		return images;
	}
	public List<String> selectByFound_id(int found_id) {
		// TODO Auto-generated method stub
		
		try {
			 ps = connection.prepareStatement("select * from image where found_id= ?");			 
			 ps.setInt(1,found_id);
			 rs = ps.executeQuery();
			while(rs.next()) {
				image image = new image();
				image.setImage_id(rs.getInt("image_id"));
				image.setFound_id(rs.getInt("found_id"));
				
				image.setImage_path(rs.getString("image_path"));
				image.setUser_id(rs.getInt("user_id"));
		
				images.add(image.getImage_path());
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
		return images;
	}
	
}
