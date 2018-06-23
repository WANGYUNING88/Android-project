package lovepets.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lovepets.bean.User;
import lovepets.dao.UserDao;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		System.out.println(123);
		System.out.println(name);
		User user = null;
		UserDao userDao = new UserDao();
		user = userDao.Login(name,password);
		Gson gson = new Gson();
		String userStr = gson.toJson(user);
		System.out.println(userStr);
		response.getWriter().append(userStr);
       // response.setCharacterEncoding("utf-8");
		
//		String name = request.getParameter("username");
//		String password = request.getParameter("password");
//		System.out.println(name);
//		if(name.equals("aa") && password.equals("aa")){
//		    response.getWriter().append("成功");
//		}else{
//			response.getWriter().append("失败");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
