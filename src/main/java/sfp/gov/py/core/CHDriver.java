package sfp.gov.py.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import sfp.gov.py.util.ConfigLoader;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache 
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class CHDriver {
	private static WebDriver driver;
	private static DesiredCapabilities capability;
	private static Properties properties;

	private CHDriver() {
		loadProperties();
		loadCapabilities();
		createRemoteWebDriver();
	}

	public static WebDriver getDriver() {

		if (driver == null) {
			new CHDriver();
		}

		return driver;
	}

	private static void createRemoteWebDriver() {
		try {
			driver = new RemoteWebDriver(new URL(properties.getProperty("app.selenium-server-url")), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static void loadProperties() {
		properties = ConfigLoader.getInstance().getConfigResourceConf();
	}

	private static void loadCapabilities() {
		System.setProperty("webdriver.chrome.driver", properties.getProperty("app.chromeDriver"));
		capability = DesiredCapabilities.chrome();
	}
}
