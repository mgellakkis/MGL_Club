package JavaBean;

public class Member {
	private String id;
	private String fname;
	private String lname;
	private String gender;
	private String phone;
	
	

	public Member(String id, String fname, String lname, String gender, String phone) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.phone = phone;
	}
	
//	public Member(String fname, String lname, String gender, String phone) {
//		this.fname = fname;
//		this.lname = lname;
//		this.gender = gender;
//		this.phone = phone;
//	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return fname + " " + lname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFname() {
		return fname;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
		return this.id + " | " + this.fname + " " + this.lname + " | " + this.gender + " | " + this.phone; 
	}
	
}
