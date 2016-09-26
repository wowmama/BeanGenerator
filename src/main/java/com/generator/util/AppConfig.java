package com.generator.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
	static{
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(System.getProperty("user.dir") + "./db.ini"));
			String dbip = properties.getProperty("dbip");
			String dbport = properties.getProperty("dbport");
			String dbname = properties.getProperty("dbname");
			
			catalog = properties.getProperty("dbname");
			username = properties.getProperty("username");
			psw = properties.getProperty("psw");
			url = "jdbc:sqlserver://" + dbip + ":"+ dbport +";DatabaseName=" + dbname;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String url;
	public static String username;
	public static String psw;
	public static String catalog;
	
	public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String schemaPattern = null;
	public static String tableNamePattern = null;
	public static String[] types = new String[] {"TABLE"};
	public static String outputPath = ".";
	public static String rootPackageName = "com.cimforce";
}
