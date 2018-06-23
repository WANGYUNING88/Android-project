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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
    /**
     * Default constructor. 
     */
    public UserRegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		result = userDao.Register(user);
		System.out.println(result);
	
		if(result == true) {
			response.getWriter().append("成功");
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
