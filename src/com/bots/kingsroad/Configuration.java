package com.bots.kingsroad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	public boolean loadProperties() throws IOException{
		
		InputStream inputStream = null;
		try{
			String propFileName = "resources/config/kingsroad.bot.properties";
 
			Properties properties = new Properties();
			inputStream = new FileInputStream(propFileName);
			properties.load(inputStream);
			
		    for (String name : properties.stringPropertyNames()) {
		        String value = properties.getProperty(name);
		        System.setProperty(name, value);
		    }
	
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return false;
		} finally {
			inputStream.close();
			System.out.println("INPUT CLOSED");
		}
		
		return true;
	}
	
}
