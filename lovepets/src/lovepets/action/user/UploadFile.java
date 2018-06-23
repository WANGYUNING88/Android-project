package lovepets.action.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lovepets.common.DateStringUtil;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream is = request.getInputStream();
		Calendar now = Calendar.getInstance();
		String fileName = DateStringUtil.getTimeString(now)+".jpg";
		File file= new File(getServletContext().getRealPath("upload/")+fileName);
		FileOutputStream fos = new FileOutputStream(file);
		
		int len;
		byte[] buffer = new byte[1024];
		while((len = is.read(buffer)) != -1){
			fos.write(buffer, 0, len);
		}
		
		is.close();
		fos.close();
		Gson gson = new Gson();
		String uploadGson = gson.toJson(fileName);
		System.out.println(fileName);
		response.getWriter().append(uploadGson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
