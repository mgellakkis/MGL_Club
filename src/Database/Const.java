package Database;


public class Const {

	//st clair college params
	public static final String url = "jdbc:mysql://php.scweb.ca/";
	public static final String DB_NAME = "mellakis654db";
	public static final String DB_USER = "mellakis654";
	public static final String DB_PASS = "cg2w5cg2w53q31c3q31c";
	
	//local params
//	public static final String DB_NAME = "mellakis654db";
//	public static final String DB_USER = "root";
//	public static final String DB_PASS = "root";
//	public static final String url = "jdbc:mysql://localhost:8889/";
	
	//Members Table
	public static final String TABLE_MEMBERS = "Members";
	public static final String MEMBERS_COLUMN_MEMBER_ID = "memberId";
	public static final String MEMBERS_COLUMN_FNAME = "fname";
	public static final String MEMBERS_COLUMN_LNAME = "lname";
	public static final String MEMBERS_COLUMN_GENDER = "gender";
	public static final String MEMBERS_COLUMN_PHONE = "phone";
	
	//Memberships Table
	public static final String TABLE_MEMBERSHIPS = "Memberships";
	public static final String MEMBERSHIPS_COLUMN_ID = "id";
	public static final String MEMBERSHIPS_COLUMN_MEMBER_ID = "memberId";
	public static final String MEMBERSHIPS_COLUMN_TYPE = "type";
}
