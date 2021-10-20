package co.yedam.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/BuyMovieTicket")
public class BuyMovieTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyMovieTicket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String location = request.getParameter("location");
		String time = request.getParameter("time");
		String seatNum = request.getParameter("seatNum");
		System.out.println(id+title+date+location+time+seatNum);
		FileDAO dao=new FileDAO();
		int a=dao.TicketingUp(id, title, date, location, time, seatNum);
		Gson gson = new GsonBuilder().create();
		response.getWriter().println(gson.toJson(a));


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
