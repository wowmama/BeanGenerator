package com.generator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class TemplateUtil {
	
	private static Map<String,StringBuilder> templateMap;
	private static String[] names = {"BeanTemplate.txt","ColumnAttributeTemplate.txt","GetterSetterTemplate.txt","KeyConstructorTemplate.txt"};
	
	static{
		templateMap = new HashMap<String,StringBuilder>();
		for(String templateName : names){
			StringBuilder sb = new StringBuilder();
			BufferedReader  reader = null;
			try{
				reader = new BufferedReader(new InputStreamReader(TemplateUtil.class.getClassLoader().getResourceAsStream("template/" + templateName)));
				String line = null;
				while((line = reader.readLine()) != null){
					sb.append(line + System.getProperty("line.separator"));
				}
				templateMap.put(templateName.replace(".txt", ""), sb);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(reader != null){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static StringBuilder getTemplate(String templateName){
		StringBuilder sb = new StringBuilder(templateMap.get(templateName).toString());
		return sb;
	}
}
