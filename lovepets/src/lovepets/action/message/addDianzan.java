package lovepets.action.message;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lovepets.bean.dianzan;
import lovepets.bean.friend;
import lovepets.dao.dianzanDao;
import lovepets.dao.friendDao;

/**
 * Servlet implementation class addDianzan
 */
@WebServlet("/addDianzan")
public class addDianzan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addDianzan() {
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
		
		String foundid = request.getParameter("found_id");
		String userid = request.getParameter("user_id");
		int found_id=Integer.parseInt(foundid);	
		int user_id=Integer.parseInt(userid);	
		
		dianzanDao dianzandao=new dianzanDao();
		dianzan dianzan = dianzandao.chaxun(user_id, found_id);
		if(dianzan==null) {
			dianzanDao dianzandao1=new dianzanDao();
			boolean result = false;
			result=dianzandao1.add(user_id, found_id);
			System.out.println(result);
			if(result==true) {
				int i=1;
				Gson gson = new Gson();
				String istr=gson.toJson(i);
				response.getWriter().append("1");
			}
	    }else {
	    	dianzanDao dianzandao2=new dianzanDao();
			boolean result = false;
			result=dianzandao2.delete(user_id, found_id);
			
			if(result==true) {
				int i=2;
				Gson gson1 = new Gson();
				String istr=gson1.toJson(i);
				response.getWriter().append("2");
			}	    
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
