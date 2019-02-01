package com.bbs.test;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class MyBatisTest {

	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void test() {
		System.out.println(sqlFactory);
	}
	
	@Test
	public void testSession() throws Exception{
		try {
			SqlSession session = sqlFactory.openSession();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
