package DAOS;

import java.util.ArrayList;

import JavaBean.Memberships;

public interface MembershipsDAO {
//interfaces allow us to provide method signatures
//anything implementing this interface must define those methods
//this is used to outline CRUD operations
	public ArrayList<Memberships> getAllMemberships();
	public Memberships getMembership(int membershipID);
	public void updateMembership(Memberships membership);
	public void deleteMembership(Memberships membership);
	public void createMembership(Memberships membership);
}
