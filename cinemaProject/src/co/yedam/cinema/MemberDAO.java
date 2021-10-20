package co.yedam.cinema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;

public class MemberDAO extends DAO {
	
	private static MemberDAO singleton = new MemberDAO();

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		return singleton;
	}

	public MemberVO join(MemberVO member) {
		connect();
		String sql = "insert into member values(?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPw());
			psmt.setString(3, member.getName());
			psmt.setString(4, member.getGen());
			psmt.setString(5, member.getBirth());
			int r = psmt.executeUpdate();
			System.out.println(r + "명 회원가입 완료");
			return member;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}
	
	public int login(String pw, String id) {
		int loginResult = 0;
		try {
			String sql = "select * from member where id = ?";
			connect();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				if(pw.equals("admin") && id.equals("admin")) {
					loginResult = 2;
					System.out.println("관리자로그인.");
				}
				else if (pw.equals(rs.getString("pw")) && id.equals(rs.getString("id"))) {
					loginResult = 1;
					System.out.println("회원로그인.");
				} 
			}
			if (loginResult == 0) {
				System.out.println("아이디 또는 패스워드가 틀렸습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginResult;
	}
	
	public List<TicketingVO> ticketCheck(String id) {
		connect();
		String sql = "select * from ticketing where id = ?";
		List<TicketingVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				TicketingVO ticket = new TicketingVO();
				
				ticket.setId(rs.getString("id"));
				ticket.setTitle(rs.getString("title"));
				ticket.setTicketDate(rs.getString("ticketdate"));
				ticket.setLocation(rs.getString("location"));
				ticket.setTime(rs.getString("time"));
				ticket.setSeatNum(rs.getString("seatnum"));
				ticket.setNum(rs.getInt("num"));
				
				list.add(ticket);
			}
			System.out.println(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	public boolean cancelTicket(int ticketNum) {
		connect();
		String sql = "delete from ticketing where num = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ticketNum);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 예약취소");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}
}
