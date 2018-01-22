package DAOS;

import java.util.ArrayList;

import JavaBean.Member;

public interface MemberDAO {
	public ArrayList<Member> getAllMembers();
	public Member getMember(int memberID);
	public void updateMember(Member member);
	public void deleteMember(Member member);
	public void createMember(Member member);
}
