package com.bbs.test;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.loading.PrivateClassLoader;

import org.junit.Test;

/*
 * JDBC.driverClassName=oracle.jdbc.driver.OracleDriver
JDBC.url=jdbc:oracle:thin:@127.0.0.1:1521:XE
JDBC.username=system
JDBC.password=oracle
 * 
 */

public class DBConnectionTest {
	private static final String Driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String USER = "system";
	private static final String PW = "oracle";
	
	@Test
	public void DBConnection() throws Exception{
		Class.forName(Driver);
		try {
			Connection con = DriverManager.getConnection(URL,USER,PW);
			System.out.println(con);
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
			
}
