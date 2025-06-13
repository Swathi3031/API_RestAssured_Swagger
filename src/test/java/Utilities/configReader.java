package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {


	public class ConfigReader {

	    private static Properties prop;

	    public static Properties initProperties() {
	        if (prop == null) {
	            prop = new Properties();
	            try {
	                FileInputStream ip = new FileInputStream("src/test/resources/config/config.properties");
	                prop.load(ip);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return prop;
	    }

	    public static String get(String key) {
	        if (prop == null) {
	            initProperties();
	        }
	        return prop.getProperty(key);
	    }
	}
}
