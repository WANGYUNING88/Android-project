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
import com.google.gson.GsonBuilder;

import lovepets.bean.User;
import lovepets.bean.found;
import lovepets.dao.FoundDao;
import lovepets.dao.UserDao;

/**
 * Servlet implementation class foundServlet
 */
@WebServlet("/foundServlet")
public class foundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public foundServlet() {
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
		 Gson gson = new GsonBuilder()
                 .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                 .create();
		found found = gson.fromJson(foundStr, found.class);
		
		boolean result = false;
		FoundDao foundDao = new FoundDao();
		result = foundDao.fabu(found);
		System.out.println(result);
		FoundDao foundDao2 = new FoundDao();
		found f=foundDao2.selectByFound(found);
		int id=f.getFound_id();
//		
		System.out.println(id);
		
		 
		Gson gson2 = new Gson();
		String found_id = gson2.toJson(id);
		System.out.println(found_id);
		if(result == true) {
			response.getWriter().append(found_id);
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
