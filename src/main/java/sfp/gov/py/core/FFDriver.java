package sfp.gov.py.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
public class FFDriver {

	private static RemoteWebDriver driver;
	private static FirefoxProfile profile;
	private static DesiredCapabilities capability;
	private static Properties properties;
	private static String[] mimeTypes = { "text/csv", "application/x-msexcel", "application/excel",
										"application/x-excel", "application/vnd.ms-excel", "image/png",
										"image/jpeg,text/html", "text/plain","application/msword",
										"application/xml","application/pdf" };

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
			driver.setFileDetector(new LocalFileDetector());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static void loadProperties() {
		properties = ConfigLoader.getInstance().getConfigResourceConf();
	}
	/**
	 * https://www.seleniumeasy.com/selenium-tutorials/how-to-download-a-file-with-webdriver
	 */
	private static void loadCapabilities() {
		profile = new FirefoxProfile();
		capability = DesiredCapabilities.firefox();
		capability.setCapability(FirefoxDriver.PROFILE, profile);
		capability.setPlatform(Platform.WIN10);
		capability.setVersion("51.0.1");
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", properties.getProperty("app.download-path"));
		profile.setPreference("browser.helperApps.neverAsk.openFile",String.join(",",mimeTypes));
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",String.join(",",mimeTypes));
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
	}

}
