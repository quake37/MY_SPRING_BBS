package com.bbs.persistence;

import java.util.List;
import java.util.Map;

import com.bbs.domain.BoardVO;
import com.bbs.domain.Criteria;
import com.bbs.domain.SearchCriteria;

public interface BoardDAO {
	public void create(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(Map map) throws Exception;

	public void delete(Map map) throws Exception;

	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int countPaging(Criteria cri) throws Exception;

	public List<BoardVO> listSearch(SearchCriteria cri);

	public int listSearchCount(SearchCriteria cri);

	public void setViewCnt(int bno);

}
