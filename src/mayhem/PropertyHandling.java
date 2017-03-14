package mayhem;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyHandling {
	
	public void CreateDatabaseProperties() {
		Properties prop = new Properties();
    	OutputStream output = null;
    	try {

    		output = new FileOutputStream("config.properties");

    		// set the properties value
    		prop.setProperty("dbdriver", "javax.sql.DataSource");
    		prop.setProperty("dbURL", "jdbc:mysql://192.168.38.196/project2?autoReconnect=true&useSSL=false");
    		prop.setProperty("dbuser", "rall");
    		prop.setProperty("dbpassword", "rallstad");

    		// save properties to project root folder
    		prop.store(output, null);

    	} catch (IOException io) {
    		io.printStackTrace();
    	} finally {
    		if (output != null) {
    			try {
    				output.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}

    	}
	}
	public Properties LoadDatabaseProperies () {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
