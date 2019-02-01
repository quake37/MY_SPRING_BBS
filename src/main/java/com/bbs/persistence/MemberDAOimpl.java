package com.bbs.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbs.domain.MemberVO;

@Repository
public class MemberDAOimpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.getTime");
	}

	@Override
	public void create(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);

	}

	@Override
	public MemberVO getmember(String userid) {

		return sqlSession.selectOne("member.getMember", userid);

	}

	@Override
	public int checkMemberId(String userid) {

		return sqlSession.selectOne("member.checkMemberId", userid);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		
		return sqlSession.selectOne("member.login", vo);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		Map<String, Object> map = new HashMap<String,Object>();
        map.put("userId", uid);
        map.put("sessionId", sessionId);
        map.put("next", next);
         
        sqlSession.update("member.keepLogin",map);
		
	}

	@Override
	public MemberVO checkUserWithSessionKey(String sessionId) {
		 // 유효시간이 남아있고(>now()) 전달받은 세션 id와 일치하는 사용자 정보를 꺼낸다.
        return sqlSession.selectOne("member.checkUserWithSessionKey",sessionId);


	}

}
