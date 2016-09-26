package com.generator.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.generator.util.model.Column;



public class ColumnUtil {
	public static String getColumnAttribute(Column column){
		StringBuilder sb = TemplateUtil.getTemplate("ColumnAttributeTemplate");
		Map<String,String> valuesMap = new HashMap(); 
		valuesMap.put("attributeName", column.getAttributeName());
		valuesMap.put("attributeType", column.getAttributeType());
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		return sub.replace(sb.toString());
	}
	
	public static String getColumnGetterAndSetter(Column column){
		StringBuilder sb = TemplateUtil.getTemplate("GetterSetterTemplate");
		if(column.isPk() && "yes".equals(column.getIsAutoincrement().trim().toLowerCase())){
			sb.insert(0, "\t@PrimaryKey(isAutoincrement = true)" + System.getProperty("line.separator"));
		}else if(column.isPk()){
			sb.insert(0, "\t@PrimaryKey" + System.getProperty("line.separator"));
		}
		Map<String,String> valuesMap = new HashMap(); 
		valuesMap.put("attributeName", column.getAttributeName());
		valuesMap.put("columnName", column.getColumnName());
		valuesMap.put("attributeType", column.getAttributeType());
		valuesMap.put("attributeSetter", "set" + Comm.toCamelCase(column.getColumnName()));
		valuesMap.put("attributeGetter", "get" + Comm.toCamelCase(column.getColumnName()));
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		return sub.replace(sb.toString());
	}
}
