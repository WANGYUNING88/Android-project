package lovepets.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lovepets.bean.User;
import lovepets.dao.UserDao;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		InputStream is = request.getInputStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is, "utf-8"));
		
		StringBuffer stringBuffer = new StringBuffer();
		String str = null;
		while((str = reader.readLine()) != null){
			stringBuffer.append(str);
		}
		
		String userStr = stringBuffer.toString();
		if(userStr!=null) {
			System.out.println(userStr);
		}
		Gson gson = new Gson();
		User user = gson.fromJson(userStr, User.class);
		boolean result = false;
		UserDao userDao = new UserDao();
		int id = user.getUser_id();
		result = userDao.Update(id,user);
		System.out.println(result);
	
		if(result == true) {
			User user1 = new User();
			UserDao userDao1=new UserDao();
			user1 = userDao1.found_user(id);
			Gson gson1 = new Gson();
			String str1 = gson1.toJson(user1);
			System.out.println("111111111"+str1);
			response.getWriter().append(str1);
		}else {
			response.getWriter().append("失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
