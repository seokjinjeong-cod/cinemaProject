package co.yedam.cinema;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.DAO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		
		String cmd = request.getParameter("cmd");
		MemberDAO dao = MemberDAO.getInstance();
		
		if(cmd.equals("join")) {			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String birth = request.getParameter("birth");
			
			MemberVO member = new MemberVO();
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setGen(gender);
			member.setBirth(birth);
			
			if(dao.join(member) != null) {
				// {"retCode":"fail"} {"retCode":"success"}
//				out.println(gson.toJson(member));
				System.out.println("success");
				out.println("{\"retCode\":\"success\"}");
				System.out.println("success");
				return;
			} else {
				out.println("{\"retCode\":\"fail\"}");
			}
		} else if(cmd.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			if(dao.login(pw, id) == 2) {
				out.println("{\"retCode\":\"admin\"}");
				
			} else if(dao.login(pw, id) == 1) {
				out.println("{\"retCode\":\"member\"}");
				
			} else if(dao.login(pw, id) == 0) {
				out.println("{\"retCode\":\"fail\"}");
				
			}
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
