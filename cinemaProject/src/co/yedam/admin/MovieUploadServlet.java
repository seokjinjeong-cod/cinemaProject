package co.yedam.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

@WebServlet("/MovieUploadServlet")
public class MovieUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MovieUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int seatCnt = 80; //좌석수
		ServletContext context = getServletContext();
		String saveDir = context.getRealPath("upload");
		int maxSize = 1024 * 1024 * 30;
		String encoding = "utf-8";
		System.out.println(saveDir);
		MultipartRequest multi = //
				new MultipartRequest(request, saveDir, maxSize//
						, encoding, new DefaultFileRenamePolicy());
		String title = multi.getParameter("title");
		String startdate = multi.getParameter("startdate");
		String enddate = multi.getParameter("enddate");
		System.out.println(title+seatCnt);
		Date sdate = null, edate = null;
		try {
			sdate = simpleDateFormat.parse(startdate);
			edate = simpleDateFormat.parse(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long minus = edate.getTime() - sdate.getTime();
		long d = minus / 1000 / 60 / 60 / 24;// 두날짜차이
		String location = multi.getParameter("location");
		String img = multi.getFilesystemName("file");
		String[] screenTimes = multi.getParameterValues("screentime"); // 체크박스 배열로 입력받아옴
		for (int k = 0; k < screenTimes.length; k++) 
		{
			calendar.setTime(sdate);
			String screenTime=(screenTimes[k]);
			for (int i = 0; i <= d; i++) 
			{
				if (i == 0) 
				{
					simpleDateFormat.format(calendar.getTime());
				} else {
					calendar.add(Calendar.DAY_OF_MONTH, +1);
					simpleDateFormat.format(calendar.getTime());
				}
				String a = String.format("%1$tY-%1$tm-%1$td", calendar);

				FileDAO dao = new FileDAO();
				System.out.println(title+ a+  screenTime+location+ img+seatCnt);
				FileVO vo = dao.uploadFile(title, a,  screenTime,location, img,seatCnt);
//				Gson gson = new GsonBuilder().create();

			}
		}
		out.println(1);
		System.out.println(saveDir);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		String title=request.getParameter("name");
//		System.out.println(title);
//		System.out.println("여기11");
		doGet(request, response);
	}

}
