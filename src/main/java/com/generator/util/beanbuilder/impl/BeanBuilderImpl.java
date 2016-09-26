package com.generator.util.beanbuilder.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.generator.util.AppConfig;
import com.generator.util.ColumnUtil;
import com.generator.util.Comm;
import com.generator.util.TemplateUtil;
import com.generator.util.beanbuilder.BeanBuilder;
import com.generator.util.model.Column;
import com.generator.util.model.Table;


public class BeanBuilderImpl implements BeanBuilder{

	@Override
	public void buildBean(Table table) {
		StringBuilder beanTemplate = TemplateUtil.getTemplate("BeanTemplate");
		Map<String,String> valuesMap = new HashMap(); 
		valuesMap.put("beanPackageName", getBeanPackageName(table));
		valuesMap.put("generatedDate", new Date().toLocaleString());
		valuesMap.put("tableName", getTableName(table));
		valuesMap.put("beanClassName", getBeanClassName(table));
		valuesMap.put("keyConstructor", getKeyConstructor(table));
		valuesMap.put("beanAllAttribute", getBeanAllAttribute(table));
		valuesMap.put("beanAllGetterAndSetter", getBeanAllGetterAndSetter(table));
		valuesMap.put("beanFieldToString", getBeanFieldToString(table));
		valuesMap.put("beanFieldToStringArgs", getBeanFieldToStringArgs(table));
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String outputString = sub.replace(beanTemplate);
		File beanFile = getOutputFile(getBeanClassName(table));
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(beanFile), "UTF-8"));
			writer.write(outputString);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private String getTableName(Table table){
		return table.getTableName();
	}
	
	private String getKeyConstructor(Table table){
		if(table.getPrimaryKeyList().size() == 0){
			return System.getProperty("line.separator");
		}
		StringBuilder beanTemplate = TemplateUtil.getTemplate("KeyConstructorTemplate");
		Map<String,String> valuesMap = new HashMap(); 
		valuesMap.put("beanClassName", getBeanClassName(table));
		valuesMap.put("keyConstructorArgs", getKeyConstructorArgs(table));
		valuesMap.put("keyConstructorArgsAssign", getKeyConstructorArgsAssign(table));
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		return sub.replace(beanTemplate);
	}
	private String getKeyConstructorArgsAssign(Table table){
		StringBuilder sb = new StringBuilder();
		for(Column column : table.getColumnList()){
			if(column.isPk()){
				sb.append("\t\tthis." + column.getAttributeName() + " = " + column.getAttributeName() + ";" + System.getProperty("line.separator"));
			}
		}
		return sb.replace(sb.length()-2, sb.length(), "").toString();
	}
	private String getKeyConstructorArgs(Table table){
		StringBuilder sb = new StringBuilder();
		for(Column column : table.getColumnList()){
			if(column.isPk()){
				sb.append(column.getAttributeType() + " " + column.getAttributeName() + ", ");
			}
		}
		return sb.replace(sb.length()-2, sb.length(), "").toString();
	}
	
	private String getBeanPackageName(Table table){
		String beanPackageName = AppConfig.rootPackageName + ".model";
		return beanPackageName;
	}
	private String getBeanClassName(Table table){
		return table.getBeanClassName();
	}
	private String getBeanAllAttribute(Table table){
		StringBuilder sb = new StringBuilder();
		List<Column> columnList = table.getColumnList();
		for(Column column : columnList){
			sb.append(ColumnUtil.getColumnAttribute(column));
		}
		return sb.toString();
	}
	
	private String getBeanAllGetterAndSetter(Table table){
		StringBuilder sb = new StringBuilder();
		List<Column> columnList = table.getColumnList();
		for(Column column : columnList){
			sb.append(ColumnUtil.getColumnGetterAndSetter(column));
		}
		return sb.toString();
	}
	
	private String getBeanFieldToString(Table table){
		StringBuilder sb = new StringBuilder();
		List<Column> columnList = table.getColumnList();
		for(Column column : columnList){
			sb.append(column.getAttributeName() + "=%s, ");
		}
		return sb.replace(sb.length()-2, sb.length(), "").toString();
	}
	
	private String getBeanFieldToStringArgs(Table table){
		StringBuilder sb = new StringBuilder();
		List<Column> columnList = table.getColumnList();
		for(Column column : columnList){
			sb.append(column.getAttributeName() + ", ");
		}
		return sb.replace(sb.length()-2, sb.length(), "").toString();
	}
	
	private File getOutputFile(String className){
		File beanFile = new File(Comm.getOutputPath() + "/" + className + ".java");
		if(beanFile.exists()){
			beanFile.delete();
			try {
				beanFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			new File(Comm.getOutputPath()).mkdirs();
			try {
				beanFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return beanFile;
	}
	
}
