package sfp.gov.py.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import sfp.gov.py.pages.DashboardPage;
import sfp.gov.py.util.ConfigLoader;
import sfp.gov.py.util.ConnectionUtil;
import sfp.gov.py.util.Operation;
import sfp.gov.py.util.WaitTool;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class TestBase {

	protected WebDriver driver;
	protected String baseUrl;
	protected DashboardPage home;

	@BeforeTest
	public void beforeSuite() {

		this.driver = FFDriver.getDriver();
		this.driver.manage().timeouts().implicitlyWait(WaitTool.DEFAULT_WAIT_4_ELEMENT, TimeUnit.SECONDS);
		ConfigLoader conf = ConfigLoader.getInstance();
		this.baseUrl = conf.getConfigResourceConf().getProperty("baseUrl");
	}
	
	protected ArrayList<Map> initElementAttributes(String className) {
		Operation operation = new Operation(ConnectionUtil.getInstance().getConnection());
		Map<String, String> params = new HashMap<String, String>();
		params.put("className", className);
		return operation.getArrayDataFromParams("", params);
	}

	@AfterTest
	public void afterSuite() {
		driver.quit();
	}

}
