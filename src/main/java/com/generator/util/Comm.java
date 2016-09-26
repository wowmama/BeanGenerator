package com.generator.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.generator.util.model.Column;
import com.generator.util.model.PrimaryKey;
import com.generator.util.model.Table;


public class Comm {
	
	public static List<Table> getAllTableName(Connection connection) throws SQLException{
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet result = databaseMetaData.getTables(
				AppConfig.catalog, 
				AppConfig.schemaPattern, 
				AppConfig.tableNamePattern, 
				AppConfig.types);
		List<Table> tableList = new ArrayList<Table>();
		while(result.next()){
			Table table = new Table();
			table.setTableCat(result.getString(1));
			table.setTableSchem(result.getString(2));
			table.setTableName(result.getString(3));
			table.setTableType(result.getString(4));
			table.setRemarks(result.getString(5));
//			table.setTypeCat(result.getString(6));
//			table.setTypeSchem(result.getString(7));
//			table.setTypeName(result.getString(8));
//			table.setSelfReferencingColName(result.getString(9));
//			table.setRefGeneration(result.getString(10));
			tableList.add(table);
		}
		setAllTableColumns(connection, tableList);
		setAllTablePk(connection, tableList);
		return tableList;
	}
	
	private static List<Table> setAllTableColumns(Connection connection, List<Table> tableList) throws SQLException{
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		for(Table table : tableList){
			List<Column> columnList = new ArrayList<Column>();
			ResultSet result = databaseMetaData.getColumns(table.getTableCat(), table.getTableSchem(), table.getTableName(), null);
			while(result.next()){
				Column column = new Column();
				column.setTableCat(result.getString(1));
				column.setTableSchem(result.getString(2));
				column.setTableName(result.getString(3));
				column.setColumnName(result.getString(4));
				column.setDataType(result.getInt(5));
				column.setTypeName(result.getString(6));
				column.setColumnSize(result.getInt(7));
				column.setDecimalDigits(result.getInt(9));
				column.setNumPrecRadix(result.getInt(10));
				column.setNullable(result.getInt(11));
				column.setRemarks(result.getString(12));
				column.setColumnDef(result.getString(13));
				column.setSqlDataType(result.getInt(14));
				column.setSqlDatetimeSub(result.getInt(15));
				column.setCharOctetLength(result.getInt(16));
				column.setOrdinalPosition(result.getInt(17));
				column.setIsNullable(result.getString(18));
				column.setScopeCatalog(result.getString(19));
				column.setScopeSchema(result.getString(20));
				column.setScopeTable(result.getString(21));
//				column.setSourceDataType(result.getShort(22));
				column.setIsAutoincrement(result.getString("IS_AUTOINCREMENT"));
				columnList.add(column);
			}
			table.setColumnList(columnList);
		}
		return tableList;
	}
	
	private static List<Table> setAllTablePk(Connection connection, List<Table> tableList) throws SQLException{
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		for(Table table : tableList){
			List<PrimaryKey> primaryKeyList = new ArrayList<PrimaryKey>();
			ResultSet result = databaseMetaData.getPrimaryKeys(table.getTableCat(), table.getTableSchem(), table.getTableName());
			while(result.next()){
				PrimaryKey primaryKey = new PrimaryKey();
				primaryKey.setTableCat(result.getString(1));
				primaryKey.setTableSchem(result.getString(2));
				primaryKey.setTableName(result.getString(3));
				primaryKey.setColumnName(result.getString(4));
				primaryKey.setKeySeq(result.getShort(5));
				primaryKey.setPkName(result.getString(6));
				primaryKeyList.add(primaryKey);
				for(Column column :table.getColumnList()){
					if(column.getColumnName().equals(primaryKey.getColumnName())){
						column.setPk(true);
					}
				}
			}
			table.setPrimaryKeyList(primaryKeyList);
		}
		return tableList;
	}
	
	public static String getOutputPath(){
//		return AppConfig.outputPath;
		return System.getProperty("user.dir") + "/bean";
	}
	
	public static String toUnderscore(String s){
		String regex = "([A-Z])";
        String replacement = "_$1";
        s = s.replaceAll(regex, replacement).toUpperCase();
        if(s.startsWith("_")){
        	s = s.substring(1);
        }
        return s;
	}
	
	public static String toCamelCase(String s){
		   String[] parts = s.split("_");
		   String camelCaseString = "";
		   for (String part : parts){
			  if("".equals(part)){
				  continue;
			  }
		      camelCaseString = camelCaseString + toProperCase(part);
		   }
		   return camelCaseString;
		}

	public static String toProperCase(String s) {
	    return s.substring(0, 1).toUpperCase() +
	               s.substring(1).toLowerCase();
	}
	
	public static String toHeadLowerCase(String s) {
	    return s.substring(0, 1).toLowerCase() +
	               s.substring(1);
	}
}
