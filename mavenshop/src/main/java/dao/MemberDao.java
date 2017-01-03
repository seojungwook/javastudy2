package dao;

import java.util.List;

import logic.Member;

public interface MemberDao {
int memberInsert(Member member);

Member getmemberinfo(String id, String pass);

List<Member> memberList();

Member selectone(String id);

void updatemember(Member member);

void delete(String id);

List<String> getmember(String[] chk);
}
