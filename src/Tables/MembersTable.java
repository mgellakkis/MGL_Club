package Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DAOS.MemberDAO;
import Database.Const;
import Database.Database;
import JavaBean.Member;
import javafx.collections.ObservableList;

public class MembersTable implements MemberDAO{

	Database db = Database.getInstance();
	
	public ArrayList<Member> getAllMembers() {
		String query = "SELECT * FROM " + Const.TABLE_MEMBERS;
		ArrayList<Member> member = new ArrayList<Member>();
		try {
			//Use the singleton an grab its connection to create a statement
			Statement getMembers = db.getConnection().createStatement();
			ResultSet data = getMembers.executeQuery(query);
			
			//This while loop will run once for each item in the result set
			while(data.next()) {
				//System.out.println(data.getString(Const.MEMBERS_COLUMN_MEMBER_ID));
				
				//Build each item and add it to the ArrayList
				member.add(new Member(
								   data.getString(Const.MEMBERS_COLUMN_MEMBER_ID),
								   data.getString(Const.MEMBERS_COLUMN_FNAME),
								   data.getString(Const.MEMBERS_COLUMN_LNAME),
								   data.getString(Const.MEMBERS_COLUMN_GENDER),
								   data.getString(Const.MEMBERS_COLUMN_PHONE)
								   ));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(member);
		return member;
	}

	public Member getMember(int memberID) {
		String query = "SELECT FROM " + Const.TABLE_MEMBERS + " WHERE " + 
					   Const.MEMBERS_COLUMN_MEMBER_ID + " = " + memberID;
		Member member = null;
		try {
			Statement getMember = db.getConnection().createStatement();
			ResultSet data = getMember.executeQuery(query);
			data.next();
			member = new Member(data.getString(Const.MEMBERS_COLUMN_MEMBER_ID),
					   data.getString(Const.MEMBERS_COLUMN_FNAME),
					   data.getString(Const.MEMBERS_COLUMN_LNAME),
					   data.getString(Const.MEMBERS_COLUMN_GENDER),
					   data.getString(Const.MEMBERS_COLUMN_PHONE));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	public void updateMember(Member member) {

	}

	public void deleteMember(Member member) {
		String query = "DELETE FROM " + Const.TABLE_MEMBERS + " WHERE " +
					   Const.MEMBERS_COLUMN_MEMBER_ID + " = " + member.getId();
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println("Deleted Record");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void createMember(Member member) {
		String query = "INSERT INTO " + Const.TABLE_MEMBERS + 
					   "(" + Const.MEMBERS_COLUMN_MEMBER_ID + "," +
					   Const.MEMBERS_COLUMN_FNAME + "," +
					   Const.MEMBERS_COLUMN_LNAME + "," +
					   Const.MEMBERS_COLUMN_GENDER + "," +
					   Const.MEMBERS_COLUMN_PHONE + ") values ('"+
					   member.getName() + "','" + member.getGender() + "','" +
					   member.getPhone() + "')";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println("Inserted Record");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getMembersCount(Member member) {
		String query = "SELECT * FROM " + Const.TABLE_MEMBERS;
		ArrayList<Member> members = new ArrayList<Member>();
		try {
			Statement getMembers = db.getConnection().createStatement();
			ResultSet data = getMembers.executeQuery(query);
			while(data.next()) {
				//Build each item and add it to the ArrayList
				members.add(new Member(data.getString(Const.MEMBERS_COLUMN_MEMBER_ID),
								   data.getString(Const.MEMBERS_COLUMN_FNAME),
								   data.getString(Const.MEMBERS_COLUMN_LNAME),
								   data.getString(Const.MEMBERS_COLUMN_GENDER),
								   data.getString(Const.MEMBERS_COLUMN_PHONE)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return members.size();
	}	
	
	
}
