package lovepets.action.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lovepets.bean.User;
import lovepets.dao.UserDao;
import lovepets.dao.friendDao;

/**
 * Servlet implementation class fensiServlet
 */
@WebServlet("/fensiServlet")
public class fensiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fensiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String user_id_str = request.getParameter("user_id");
		int user_id=Integer.parseInt(user_id_str);
		friendDao frienddao=new friendDao();
		List<String> fids=  frienddao.selectByFriend_user_id(user_id);
		List<User> lusers =new ArrayList<>();
		for(int i=0;i<fids.size();i++) {
			UserDao userDao=new UserDao();
			int ii = Integer.parseInt(fids.get(i));
			lusers.add(userDao.found_user(ii));
		}
		Gson gson = new Gson();
		String guanzhustr = gson.toJson(lusers);
		response.getWriter().append(guanzhustr);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
