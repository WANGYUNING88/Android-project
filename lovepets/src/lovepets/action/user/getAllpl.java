package lovepets.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import lovepets.bean.pinglun;
import lovepets.bean.pinglunMessage;
import lovepets.dao.FoundDao;
import lovepets.dao.PinglunDao;
import lovepets.dao.UserDao;

/**
 * Servlet implementation class getAllpl
 */
@WebServlet("/getAllpl")
public class getAllpl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllpl() {
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
		
		String foundStr = stringBuffer.toString();
		
		if(foundStr!=null) {
			System.out.println(foundStr);
		}
		 Gson gson = new Gson();
		int i = gson.fromJson(foundStr, int.class);
		FoundDao foundDao = new FoundDao();
		PinglunDao pinglunDao = new PinglunDao();
		List<pinglun> pls = new ArrayList<>();
		pls = pinglunDao.selectByFoundid(i);
		List<pinglunMessage> plm = new ArrayList<>();
		User user = new User();
		
		for(int j = 0;j<pls.size();j++) {
			UserDao userdao = new UserDao();
			user = userdao.found_user(pls.get(j).getUser_id());
			pinglunMessage plms = new pinglunMessage();
			plms.setUser_name(user.getUser_name());
			plms.setUser_img(user.getUser_image());
			plms.setPinglun_content(pls.get(j).getPinglun_content());
			plm.add(plms);
		}
		String mlistStr = gson.toJson(plm);
        response.getWriter().append(mlistStr);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
