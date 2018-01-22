package Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DAOS.MembershipsDAO;
import Database.Const;
import Database.Database;
import ENUMS.MembershipTypes;
import JavaBean.Memberships;

public class MembershipsTable implements MembershipsDAO{

	Database db = Database.getInstance();
	ArrayList<Memberships> memberships = new ArrayList<Memberships>();
	
	public MembershipsTable() {
		
	}
	

	public ArrayList<Memberships> getAllMemberships() {
		String query = "SELECT * FROM " + Const.TABLE_MEMBERSHIPS;
		memberships = new ArrayList<Memberships>();
		try {
			Statement getLocations = db.getConnection().createStatement();
			ResultSet data = getLocations.executeQuery(query);
			while(data.next()){
				memberships.add(new Memberships(data.getInt(Const.MEMBERSHIPS_COLUMN_ID), 
						data.getString(Const.MEMBERSHIPS_COLUMN_MEMBER_ID), 
						data.getString(Const.MEMBERSHIPS_COLUMN_TYPE)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberships;
	}

	public Memberships getMembership(int membershipID) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM " + Const.TABLE_MEMBERSHIPS + " where " 
		+ Const.MEMBERSHIPS_COLUMN_ID + " = " + "'" + membershipID + "'";
		Memberships membership = null;
		try {
			Statement getMembership = db.getConnection().createStatement();
			ResultSet data = getMembership.executeQuery(query);
			if(data.next()){
				membership = new Memberships(data.getInt(Const.MEMBERSHIPS_COLUMN_ID), 
						data.getString(Const.MEMBERSHIPS_COLUMN_MEMBER_ID), 
						data.getString(Const.MEMBERSHIPS_COLUMN_TYPE));
			}		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return membership;
	}

	
	public void createMembership(Memberships membership) {
		String query = "insert into " + Const.TABLE_MEMBERSHIPS 
				+ "(" + Const.MEMBERSHIPS_COLUMN_ID + "," 
				+ Const.MEMBERSHIPS_COLUMN_MEMBER_ID + "," 
				+ Const.MEMBERSHIPS_COLUMN_TYPE + ") values ('"
				   + membership.getId() + "','" + membership.getMember_id() + "','" 
				   + membership.getType() + "');";
		try {
			db.getConnection().createStatement().execute(query);
			System.out.println("Inserted record");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void updateMembership(Memberships membershipID) {
		
	}


	@Override
	public void deleteMembership(Memberships membership) {
		// TODO Auto-generated method stub
		
	}
	
	public int getMembershipTypeCount(MembershipTypes type) {
		String query = "SELECT * FROM " + Const.TABLE_MEMBERSHIPS + " WHERE "
						+ Const.MEMBERSHIPS_COLUMN_TYPE + " = '" + type + "'";
		ArrayList<Memberships> types = new ArrayList<Memberships>();
		try {
			Statement getTypes = db.getConnection().createStatement();
			ResultSet data = getTypes.executeQuery(query);
			while(data.next()) {
				//Build each item and add it to the ArrayList
				types.add(new Memberships(data.getInt(Const.MEMBERSHIPS_COLUMN_ID),
								   data.getString(Const.MEMBERSHIPS_COLUMN_MEMBER_ID),
								   data.getString(Const.MEMBERSHIPS_COLUMN_TYPE)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return types.size();
	}

}
