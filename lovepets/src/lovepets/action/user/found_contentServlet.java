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
import com.google.gson.GsonBuilder;

import lovepets.bean.User;
import lovepets.bean.found;
import lovepets.bean.foundMessage;
import lovepets.bean.image;
import lovepets.dao.FoundDao;
import lovepets.dao.ImageDao;
import lovepets.dao.UserDao;

/**
 * Servlet implementation class found_imageServlet
 */
@WebServlet("/found_contentServlet")
public class found_contentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public found_contentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<found> found=null;
        FoundDao founddao=new FoundDao();
        found =founddao.selectAllfound();

        
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                .create();
		
        
        List<User> users=new ArrayList();
        User user = new User();
        
        List<foundMessage> mess = new ArrayList();
        
        
        
//		System.out.println("cha"+mlistStr);
		
        
        
        for(int i=0;i<found.size();i++) {
        	foundMessage mes = new foundMessage();
        	UserDao userDao=new UserDao();
            user=userDao.found_user(found.get(i).getUser_id());
            
            ImageDao imagedao = new ImageDao();
            mes.setImageurl(imagedao.selectByFound_id(found.get(i).getFound_id()));
//            System.out.println("1111"+mes.getImageurl());
            mes.setUsername(user.getUser_name());
            mes.setUserimage(user.getUser_image());
            mes.setFconnect(found.get(i).getFound_content());
            mes.setDate(found.get(i).getFound_date());
            mess.add(mes);
//            System.out.println("sdaagadgadh"+user.getUser_name());
        }
        String mlistStr = gson.toJson(mess);
        response.getWriter().append(mlistStr);
//		response.getWriter().append(mlistStr1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
