package com.bbs.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbs.domain.BoardVO;
import com.bbs.domain.Criteria;
import com.bbs.domain.SearchCriteria;

@Repository
public class BoardDAOimpl implements BoardDAO {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public void create(BoardVO vo) throws Exception {
		sqlsession.insert("board.create", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return sqlsession.selectOne("board.read", bno);
	}

	@Override
	public void update(Map map) throws Exception {
		sqlsession.update("board.update", map);

	}

	@Override
	public void delete(Map map) throws Exception {
		sqlsession.delete("board.delete", map);

	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return sqlsession.selectList("board.listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlsession.selectList("board.listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList("board.listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return sqlsession.selectOne("board.countPaging", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("board.listSearch", cri);
		
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("board.listSearchCount", cri);
	}

	@Override
	public void setViewCnt(int bno) {
		sqlsession.update("board.updateViewCnt", bno);
		
	}

}
