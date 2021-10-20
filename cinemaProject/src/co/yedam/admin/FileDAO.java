package co.yedam.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;

public class FileDAO extends DAO {

	public List<FileVO> searchScreen(){
		connect();
		List<FileVO> list=new ArrayList<>();
		String sql = "select * from movieupload order by 1";
		
		try {
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				FileVO vo = new FileVO();
				vo.setTitle(rs.getString("title"));
				vo.setLocation(rs.getString("location"));
				vo.setStartdate(rs.getString("startdate"));
				vo.setScreentime(rs.getString("screentime"));
				vo.setSeatcnt(rs.getInt("seatcnt"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	public List<FileVO> loadSeatInfo(String title,String ticketdate,String location,String time) {
		connect();
		List<FileVO> list=new ArrayList<>();
		String sql ="select * from ticketing where title=? and ticketdate=? and location=? and time=?";
		System.out.println(title+","+ticketdate+","+location+","+time);
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, ticketdate);
			psmt.setString(3, location);
			psmt.setString(4, time);
			rs=psmt.executeQuery();
			while(rs.next()) {
				FileVO vo=new FileVO();
				vo.setSeatnum(rs.getString("seatnum"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public int TicketingUp(String id , String title , String ticketdate ,String location , String time,String seatnum) {
		connect();
		String sql = "insert into ticketing values(TICKETNUM.nextval,?,?,?,?,?,?)";
		
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, ticketdate);
			psmt.setString(4, location);
			psmt.setString(5, time);
			psmt.setString(6, seatnum);
			rs=psmt.executeQuery();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	//일반사용자가 볼 리스트
	public List<FileVO> GetScreenUser(){
		connect();
		List<FileVO> list=new ArrayList<>();
		String sql = "select * from movieupload order by 1";
		
		try {
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				FileVO vo = new FileVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartdate(rs.getString("startdate"));
				vo.setLocation(rs.getString("location"));
				vo.setScreentime(rs.getString("screentime"));
				vo.setSeatcnt(rs.getInt("seatcnt"));
				vo.setImg(rs.getString("img"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
		
	}
	public List<FileVO> ScreenList(String title , String date , String location){
		connect();
		List<FileVO> list=new ArrayList<>();
		String sql = "select * from movieupload where title=? and startdate=? and location=?";
		
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, date);
			psmt.setString(3, location);
			rs=psmt.executeQuery();
			while(rs.next()) {
				FileVO vo = new FileVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartdate(rs.getString("startdate"));
				vo.setLocation(rs.getString("location"));
				vo.setScreentime(rs.getString("screentime"));
				vo.setImg(rs.getString("img"));
				vo.setSeatcnt(rs.getInt("seatcnt"));
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
		
		
	}
	public void deleteScreen(String title,String startdate,String location,String screentime)
	{
		connect();
		String sql="delete from movieupload where title=? and startdate=? and location=? and screentime=?";
		try {
			System.out.println("작동됨?");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, startdate);
			psmt.setString(3, location);
			psmt.setString(4, screentime);
			int r=psmt.executeUpdate();
			System.out.println(r+"건삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}

	public int seatInfo(String id ,String title,String startdate,String  location,String screentime,int seatCnt)
	{
		connect();
		String sql="insert into seatinfo values(?,?,?,?,?,?)";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,id );
			psmt.setString(2,title );
			psmt.setString(3,startdate );
			psmt.setString(4,location );
			psmt.setString(5,screentime );
			psmt.setInt(6,seatCnt );
			int r =psmt.executeUpdate();
			System.out.println(r+"건 입력.");
			return seatCnt;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			disconnect();
		}
	}
	
	//파일 업로드 처리.
	//서블릿에서 파일 업로드 , db저장.
	public FileVO uploadFile(String title,String startdate,String screentime,String  location,String img,int seatCnt) {
		connect();
		System.out.println("요기1");
		String sql="insert into movieupload values(?,?,?,?,?,?)";
		try {
			System.out.println(title+','+startdate+','+location+','+screentime+','+img);
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, startdate);
			psmt.setString(3, screentime);
			psmt.setString(4, location);
			psmt.setString(5, img);
			psmt.setInt(6, seatCnt);
			int r =psmt.executeUpdate();
			System.out.println(r+"건 입력.");
			FileVO vo= new FileVO();
			vo.setTitle(title);
			vo.setStartdate(startdate);
			vo.setScreentime(screentime);
			vo.setLocation(location);
			vo.setImg(img);
			vo.setSeatcnt(seatCnt);
			System.out.println("요기2");
			System.out.println(vo);
			return vo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			disconnect();
		}
		
		
		
	}
	
}
