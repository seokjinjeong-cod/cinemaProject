package co.yedam.cinema;

public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private String gen;
	private String birth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "memberVO [name=" + name + ", id=" + id + ", pw=" + pw + ", gen=" + gen + ", birth=" + birth + "]";
	}
	
	
}
