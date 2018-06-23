package lovepets.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lovepets.bean.User;
import lovepets.bean.found;
import lovepets.dao.FoundDao;
import lovepets.dao.UserDao;

/**
 * Servlet implementation class found_userServert
 */
@WebServlet("/found_userServert")
public class found_userServert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public found_userServert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				List<found> found=null;
		        FoundDao founddao=new FoundDao();
		        found =founddao.selectAllfound();
		        
		        List<User> users=null;
		        UserDao userDao=new UserDao();
		        for(int i=0;i<found.size();i++) {
		            User user=userDao.found_user(found.get(i).getUser_id());
		            users.add(user);
		        }
		        
		        Gson gson = new Gson();
				String mlistusers = gson.toJson(users);
				System.out.println("cha"+mlistusers);
				response.getWriter().append(mlistusers);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
