package com.bbs.test;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbs.domain.MemberVO;
import com.bbs.persistence.MemberDAO;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class MemberDAOTest {

	@Autowired
	private MemberDAO dao;
	
	@Test
	public void testTime() {
		System.out.println(dao.getTime());
	}
	@Test
	public void testInsertMember() throws Exception{
//		MemberVO vo = new MemberVO("user","user","uu","qwqw@naemver.com");
	//	dao.create(vo);
	}

}
