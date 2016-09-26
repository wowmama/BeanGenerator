package com.generator.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.generator.util.beanbuilder.impl.BeanBuilderImpl;
import com.generator.util.model.Table;

public class Main {
	public static void main(String[] args){
		
		Connection connection = null;
		List<Table> tableNameList = null;

		
		try{
			
			Class.forName(AppConfig.driverName);
			connection = DriverManager.getConnection(AppConfig.url, AppConfig.username, AppConfig.psw);
			
			tableNameList = Comm.getAllTableName(connection);
			
			BeanBuilderImpl beanBuilderImpl = new BeanBuilderImpl();
			for(Table table : tableNameList){
				System.out.println("Start build "+ table.getTableName() +" bean.");
				beanBuilderImpl.buildBean(table);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
