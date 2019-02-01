package com.bbs.persistence;

import java.util.Date;

import com.bbs.domain.MemberVO;

public interface MemberDAO {
	public String getTime();

	public void create(MemberVO vo);

	public MemberVO getmember(String userid);

	public int checkMemberId(String userid);

	public MemberVO login(MemberVO dto);
	
	   // �ڵ��α��� üũ�� ��쿡 ����� ���̺� ���ǰ� ��ȿ�ð��� �����ϱ� ���� �޼���
    public void keepLogin(String uid, String sessionId, Date next);
     
    // ������ �α����� ���� �ִ���, �� ��ȿ�ð��� ���� ���� ������ ������ �ִ��� üũ�Ѵ�.
    public MemberVO checkUserWithSessionKey(String sessionId);



		
	
}
