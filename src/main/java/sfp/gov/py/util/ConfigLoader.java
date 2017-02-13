package sfp.gov.py.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache 
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class ConfigLoader {
	private Properties properties;
	private static ConfigLoader config;

	private ConfigLoader() {
	}

	public static ConfigLoader getInstance() {
		if (config == null) {
			config = new ConfigLoader();
		}

		return config;
	}

	public Properties getConfigResourceConf() {
		
		if (properties == null) {
			try {
				properties = new Properties();
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
				properties.load(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return properties;
	}

}
