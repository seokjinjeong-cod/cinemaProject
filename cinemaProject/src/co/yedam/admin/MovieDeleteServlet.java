package co.yedam.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/MovieDeleteServlet")
public class MovieDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MovieDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title=request.getParameter("title");
        System.out.println("여기"+title);
        String startdate=request.getParameter("startdate");
        String location=request.getParameter("location");
        String screentime=request.getParameter("screentime");
        Gson gson = new GsonBuilder().create();
		FileDAO dao=new FileDAO();
		dao.deleteScreen(title, startdate,location , screentime);
		out.println(gson.toJson(title));
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
