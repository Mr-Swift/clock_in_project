package com.njusc.npm.utils.test;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.njusc.npm.utils.exception.ResultCodeException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 将固定格式的地区数据，生成固定的地区citys.js数据 文件
 * */
public class AddressFreemarkerTest {
	
	public static void main(String []args) throws ResultCodeException, IOException, ClassNotFoundException, SQLException, TemplateException{
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator\\Desktop\\"));
		configuration.setEncoding(Locale.CHINA, "utf-8");
		Template t = configuration.getTemplate("city_picker_data.ftl");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\city.js")), "UTF-8"));
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("ChineseDistricts", Addressfreemarker.getAllAddress());
		t.process(dataMap, out);
		out.flush();
		out.close();
	}

}
