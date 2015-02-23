package fr.uds.info901.rumoryap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {
	
	private static String FILE_PATH = Thread.currentThread().getContextClassLoader().getResource("config.properties").getFile();
	
	public static Map<String, String> getMapProperty(){

		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			 
			input = new FileInputStream(FILE_PATH);
			prop.load(input);
			for (final String name: prop.stringPropertyNames())
			    map.put(name, prop.getProperty(name));
			
		}catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		return map;
	}
	
	public static void addProperty(String key, String value){
		Properties prop = new Properties();
		InputStream input = null;
		OutputStream output = null;
		
		try {
			output = new FileOutputStream(FILE_PATH);
			input = new FileInputStream(FILE_PATH);
			prop.load(input);
			input.close();
			
			prop.put(key, value);
			
			prop.store(output, "");			
			output.close();
		}catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}		
	}
}