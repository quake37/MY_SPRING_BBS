package com.bbs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.domain.MemberVO;
import com.bbs.persistence.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;

	public void create(MemberVO vo) throws Exception {
		dao.create(vo);
	}

	public MemberVO getMember(String userid) throws Exception {

		return dao.getmember(userid);
	}

	public int checkMemberId(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkMemberId(userid);
	}

	public MemberVO login(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	public void keepLogin(String uid, String sessionId, Date next) {

		dao.keepLogin(uid, sessionId, next);
	}

	public MemberVO checkUserWithSessionKey(String sessionId) {
		return dao.checkUserWithSessionKey(sessionId);
	}

}
