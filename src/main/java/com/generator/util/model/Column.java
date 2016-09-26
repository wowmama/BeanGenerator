package com.generator.util.model;

import java.sql.Types;

import com.generator.util.Comm;


public class Column {
	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String columnName;
	private int dataType;
	private String typeName;
	private int columnSize;
	private int decimalDigits;
	private int numPrecRadix;
	private int nullable;
	private String remarks;
	private String columnDef;
	private int sqlDataType;
	private int sqlDatetimeSub;
	private int charOctetLength;
	private int ordinalPosition;
	private String isNullable;
	private String scopeCatalog;
	private String scopeSchema;
	private String scopeTable;
	private short sourceDataType;
	private String isAutoincrement;
	private String isGeneratedcolumn;
	private boolean isPk = false;
	
	
	public boolean isPk() {
		return isPk;
	}
	
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
	public String getTableCat() {
		return tableCat;
	}

	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}

	public String getTableSchem() {
		return tableSchem;
	}

	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public int getNumPrecRadix() {
		return numPrecRadix;
	}

	public void setNumPrecRadix(int numPrecRadix) {
		this.numPrecRadix = numPrecRadix;
	}

	public int getNullable() {
		return nullable;
	}

	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	public int getSqlDataType() {
		return sqlDataType;
	}

	public void setSqlDataType(int sqlDataType) {
		this.sqlDataType = sqlDataType;
	}

	public int getSqlDatetimeSub() {
		return sqlDatetimeSub;
	}

	public void setSqlDatetimeSub(int sqlDatetimeSub) {
		this.sqlDatetimeSub = sqlDatetimeSub;
	}

	public int getCharOctetLength() {
		return charOctetLength;
	}

	public void setCharOctetLength(int charOctetLength) {
		this.charOctetLength = charOctetLength;
	}

	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getScopeCatalog() {
		return scopeCatalog;
	}

	public void setScopeCatalog(String scopeCatalog) {
		this.scopeCatalog = scopeCatalog;
	}

	public String getScopeSchema() {
		return scopeSchema;
	}

	public void setScopeSchema(String scopeSchema) {
		this.scopeSchema = scopeSchema;
	}

	public String getScopeTable() {
		return scopeTable;
	}

	public void setScopeTable(String scopeTable) {
		this.scopeTable = scopeTable;
	}

	public short getSourceDataType() {
		return sourceDataType;
	}

	public void setSourceDataType(short sourceDataType) {
		this.sourceDataType = sourceDataType;
	}

	public String getIsAutoincrement() {
		return isAutoincrement;
	}

	public void setIsAutoincrement(String isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}

	public String getIsGeneratedcolumn() {
		return isGeneratedcolumn;
	}

	public void setIsGeneratedcolumn(String isGeneratedcolumn) {
		this.isGeneratedcolumn = isGeneratedcolumn;
	}

	public String getAttributeName() {
		return Comm.toHeadLowerCase(Comm.toCamelCase(columnName));
	}
	
	public static void main(String[] args){
		Comm.toHeadLowerCase(Comm.toCamelCase("COM_BLADE_SUR__ID"));
	}
	
	public String getAttributeType(){
		String attributeType = "String";
		if(dataType == Types.BIGINT){
			attributeType =  "Long";
		}else if(dataType == Types.SMALLINT || dataType == Types.INTEGER){
			attributeType =  "Integer";
		}else if(dataType == Types.NUMERIC || dataType == Types.DECIMAL ||  dataType == Types.DOUBLE ){
			attributeType =  "BigDecimal";
		}else if(dataType == Types.CHAR || dataType == Types.VARCHAR|| dataType == Types.NVARCHAR || dataType == Types.NCHAR){
			attributeType =  "String";
		}else if(dataType == Types.DATE || dataType == Types.TIMESTAMP){
			attributeType =  "Date";
		}
		return attributeType;
	}
}
