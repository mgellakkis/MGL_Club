package JavaBean;

public class Memberships {
	private int id;
	private String member_id;
	private String type;
	
	public Memberships() {
		
	}

	public Memberships(int id, String member_id, String type) {
		this.id = id;
		this.member_id = member_id;
		this.type = type;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return this.type;
	}
}
