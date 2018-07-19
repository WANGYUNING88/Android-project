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

import lovepets.bean.friend;
import lovepets.dao.friendDao;

/**
 * Servlet implementation class addguanzhuServlet
 */
@WebServlet("/addguanzhuServlet")
public class addguanzhuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addguanzhuServlet() {
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
		
		String id_str = request.getParameter("id_str");
		String user_id_str = request.getParameter("user_id");
		int friend_user_id=Integer.parseInt(id_str);
		int user_id=Integer.parseInt(user_id_str);
		friendDao frienddao=new friendDao();
		friend friend = frienddao.chaxun(user_id, friend_user_id);
		if(friend==null) {
			friendDao frienddao1=new friendDao();
			boolean result = false;
			result=frienddao1.add(user_id, friend_user_id);
			if(result==true) {
				response.getWriter().append("关注成功");
			}
	    }else {
	    	response.getWriter().append("已关注");
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
