package co.yedam.admin;

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

@WebServlet("/LoadSeatServ")
public class LoadSeatServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadSeatServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String location = request.getParameter("location");
		String time = request.getParameter("time");
		FileDAO dao=new FileDAO();
		System.out.println("서블릿쪽임");
		List<FileVO> list=new ArrayList<>();
		list=dao.loadSeatInfo(title, date, location, time);
		Gson gson = new GsonBuilder().create();
		System.out.println(list);
		response.getWriter().println(gson.toJson(list));
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
