package com.ibm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class PropertiesFileHandler {

	/*
	 * public Properties getPropertiesObject(String file) throws IOException {
	 * FileInputStream fileIn=new FileInputStream(file); Properties prop=new
	 * Properties(); prop.load(fileIn);
	 * 
	 * return prop; }
	 */

	public String getValue(String file, String key) throws IOException {
		FileInputStream fileIn = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileIn);
		String value = null;
		if (prop.containsKey(key)) {
			value = prop.getProperty(key);
		}
		prop.clear();
		return value;
	}

	public HashMap<String, String> getPropertiesAsMap(String file) throws IOException {
		HashMap<String, String> magentoMap = new HashMap<String, String>();

		FileInputStream fileIn = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileIn);

		Set<Object> keysProp = prop.keySet();
		for (Object key : keysProp) {
			magentoMap.put(key.toString(), prop.getProperty(key.toString()));
		}
		prop.clear();
		return magentoMap;
	}
	
	public void setKeyAndValue(String file,String key,String value) throws IOException
	{
		FileInputStream fileIn = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileIn);

		prop.setProperty(key, value);

		FileOutputStream fOut=new FileOutputStream(file);
		prop.store(fOut, "Test Result");
		fOut.close();
		fileIn.close();
	}
	public void setKeysAndValues(String file,HashMap<String, String> map) throws IOException
	{
		FileInputStream fileIn = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileIn);

		
		Set<String> keys = map.keySet();
		for (String key : keys) {
			prop.setProperty(key, map.get(key));
		}

		FileOutputStream fOut=new FileOutputStream(file);
		prop.store(fOut, "Test Result");
		fOut.close();
		fileIn.close();
	}
}
