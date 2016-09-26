package com.generator.util.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.generator.util.AppConfig;
import com.generator.util.Comm;
import com.generator.util.model.Column;
import com.generator.util.model.PrimaryKey;
import com.generator.util.model.Table;


public class CommTest {
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
	public void testGetAllTableName(){
//		List<Table> tableNameList = null;
//		try {
//			tableNameList = Comm.getAllTableName(connection);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		for(Table table : tableNameList){
//			System.out.println(table.getTableName());
//			System.out.println(" columns:");
//			for(Column column : table.getColumnList()){
//				System.out.println("  " + column.getColumnName() + " : " + column.getTypeName() + "("+column.getDataType()+")"
//						+ " isPk : " + column.isPk() + ", isAuto : " + column.getIsAutoincrement());
//			}
//			System.out.println(" primaryKeys:");
//			for(PrimaryKey primaryKey : table.getPrimaryKeyList()){
//				System.out.println("  " + primaryKey.getColumnName());
//			}
//		}
	}
}
