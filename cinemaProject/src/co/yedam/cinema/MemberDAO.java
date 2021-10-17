package co.yedam.cinema;

import java.sql.SQLException;

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
}
