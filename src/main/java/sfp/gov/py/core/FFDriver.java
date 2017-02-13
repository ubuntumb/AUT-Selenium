package sfp.gov.py.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
public class FFDriver {

	private static WebDriver driver;
	private static FirefoxProfile profile;
	private static DesiredCapabilities capability;
	private static Properties properties;

	private FFDriver() {
		loadProperties();
		loadCapabilities();
		createRemoteWebDriver();
	}

	public static WebDriver getDriver() {

		if (driver == null) {
			new FFDriver();
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
		profile = new FirefoxProfile();
		capability = DesiredCapabilities.firefox();
		capability.setCapability(FirefoxDriver.PROFILE, profile);
		capability.setPlatform(Platform.WIN10);
		capability.setVersion("51.0.1");
	}

}
