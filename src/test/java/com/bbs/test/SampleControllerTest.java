package com.bbs.test;

import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class SampleControllerTest {
	private static final Logger logger =LoggerFactory.getLogger(SampleControllerTest.class);
	
	@Autowired
	private WebApplicationContext ac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ac).build();
		logger.info("Before......");
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/doJSON"));
	}

}
