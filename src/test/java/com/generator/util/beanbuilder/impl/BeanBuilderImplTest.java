package com.generator.util.beanbuilder.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.generator.util.AppConfig;
import com.generator.util.Comm;
import com.generator.util.model.Table;


public class BeanBuilderImplTest {
	Connection connection = null;

//	@Before
//	public void beforeTest(){
//		try{
//			Class.forName(AppConfig.driverName);
//			connection = DriverManager.getConnection(AppConfig.url, AppConfig.username, AppConfig.psw);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@After
//	public void afterTest(){
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testBuildBean(){
//		List<Table> tableNameList = null;
//		try {
//			tableNameList = Comm.getAllTableName(connection);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		BeanBuilderImpl beanBuilderImpl = new BeanBuilderImpl();
//		for(Table table : tableNameList){
//			beanBuilderImpl.buildBean(table);
//		}
	}
}
