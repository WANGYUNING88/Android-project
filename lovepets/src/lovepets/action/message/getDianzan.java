package lovepets.action.message;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lovepets.bean.dianzan;
import lovepets.dao.dianzanDao;

/**
 * Servlet implementation class getDianzan
 */
@WebServlet("/getDianzan")
public class getDianzan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDianzan() {
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
		
		String foundid = request.getParameter("found_id");
		String userid = request.getParameter("user_id");
		int found_id=Integer.parseInt(foundid);	
		int user_id=Integer.parseInt(userid);	
		
		dianzanDao dianzandao=new dianzanDao();
		dianzan dianzan = dianzandao.chaxun(user_id, found_id);
		if(dianzan == null) {
			int i=1;
			Gson gson = new Gson();
			String str=gson.toJson(i) ;
			response.getWriter().append("1");
		}else {
			int i=2;
			Gson gson = new Gson();
			String str=gson.toJson(i) ;
			response.getWriter().append("2");
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
