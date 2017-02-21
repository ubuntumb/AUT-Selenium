package sfp.gov.py.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
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
public class CHDriver{
	private static RemoteWebDriver driver;
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
			driver.setFileDetector(new LocalFileDetector());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static void loadProperties() {
		properties = ConfigLoader.getInstance().getConfigResourceConf();
	}

	/*
	 * http://qavalidation.com/2016/03/selenium-downloadfiles-chrome.html/
	 */
	private static void loadCapabilities() {
		System.setProperty("webdriver.chrome.driver", properties.getProperty("app.chromeDriver"));
		capability = DesiredCapabilities.chrome();

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", properties.getProperty("app.download-path"));
		chromePrefs.put("download.prompt_for_download", "false");
		chromePrefs.put("plugins.plugins_disabled", new String[]{
			    "Adobe Flash Player", "Chrome PDF Viewer"});
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");

		capability.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(ChromeOptions.CAPABILITY, options);
	}
}
