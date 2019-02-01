package com.bbs.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbs.domain.BoardVO;
import com.bbs.persistence.BoardDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml" })
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Autowired
	private BoardDAO dao;

	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로운글입니다.");
		board.setContent("새로운글넣습니다.");
		board.setWriter("user00");
		board.setImg_file_name("aaa.gif");
		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {
		logger.info(dao.read(1).toString());
	}

	

	
}
