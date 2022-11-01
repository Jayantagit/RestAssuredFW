package POjO;

public class UserRole {

	String euid;
	String password;

	public UserRole(String euid, String password) {

		this.euid = euid;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRole [euid=" + euid + ", password=" + password + "]";
	}

	public String getEuid() {
		return euid;
	}

	public void setEuid(String euid) {
		this.euid = euid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
