package co.yedam.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ShowScreenServ")
public class ShowScreenServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowScreenServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String title=request.getParameter("title");
        String date=request.getParameter("date");
        String location=request.getParameter("location");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().create();
		FileDAO dao=new FileDAO();
		List<FileVO> list =dao.ScreenList(title, date, location);
		out.println(gson.toJson(list));
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
