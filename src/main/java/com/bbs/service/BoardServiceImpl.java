package com.bbs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.domain.BoardVO;
import com.bbs.domain.Criteria;
import com.bbs.domain.SearchCriteria;
import com.bbs.persistence.BoardDAO;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	@Autowired
	private ServletContext application;
	
	@Override
	public void regist(BoardVO board) throws Exception {
		
//		String path = application.getRealPath("/resources/upload");
//		System.out.println(path);
//		String img = board.getUpload_f().getOriginalFilename();
//		File file = new File(path, img);
//
//		try {
//			// dto.getUpload_f().transferTo(file);
//			FileCopyUtils.copy(board.getUpload_f().getBytes(), file);
//			File target = new File(path, img);
//			FileCopyUtils.copy(board.getUpload_f().getBytes(), target);
//			board.getUpload_f().transferTo(target);
//		} catch (Exception ex) {
//		}
//
//		board.setImg_file_name(img);
		
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno); 
	}

	@Override
	public void modify(Map map) throws Exception {
		dao.update(map);
		
	}

	@Override
	public void remove(Map map) throws Exception {
		dao.delete(map);
		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public void setViewCnt(int bno) {
		dao.setViewCnt(bno);
		
	}
	
}
