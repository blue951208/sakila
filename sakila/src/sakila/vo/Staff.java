package sakila.vo;

public class Staff {
	private int staffId;
	private String password;
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", password=" + password + "]";
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
