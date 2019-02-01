package com.bbs.test;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class DataSourceTest {
	@Inject
	private DataSource ds;
	
	@Test
	public void test() {
		try {
			Connection con = ds.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
