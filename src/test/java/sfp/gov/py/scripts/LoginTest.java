package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.CHDriver;
import sfp.gov.py.pages.DashboardPage;
import sfp.gov.py.pages.HomePage;
import sfp.gov.py.pages.LoginPage;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.ConfigLoader;
import sfp.gov.py.util.WaitTool;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache 
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */

public class LoginTest {

	private LoginPage login;
	private DashboardPage dashboard;
	private HomePage home;
	private String username;
	private String password;
	private String valid;
	protected WebDriver driver;
	protected String baseUrl;

	@BeforeTest
	public void beforeSuite() {

		this.driver = CHDriver.getDriver();
		this.driver.manage().timeouts().implicitlyWait(WaitTool.DEFAULT_WAIT_4_ELEMENT, TimeUnit.SECONDS);
		ConfigLoader conf = ConfigLoader.getInstance();
		this.baseUrl = conf.getConfigResourceConf().getProperty("baseUrl");

	}

	@BeforeMethod
	public void beforeMethod() {
		login = new LoginPage(driver, baseUrl);
	}

	@AfterTest
	public void afterSuite() {
		driver.quit();
	}

	@Test(description = "Go to Login and try access control", groups = "acceso", dataProvider = "loginCredential")
	public void verifyAccessControl(Map testValue) {
		
		login.open();
		username = testValue.get("username").toString();
		password = testValue.get("password").toString();
		valid = testValue.get("valid").toString();
		dashboard = login.loginAs(username, password);

		Assert.assertEquals(dashboard.isUserNameLinkPresented(), Boolean.parseBoolean(valid));
	}

	@Test(description = "Go to Logout", dependsOnMethods = "verifyAccessControl", groups = "standalone")
	public void verifyLogout() {
		dashboard.showExitButtonLink();
		home = dashboard.clickToExitButton();
		Assert.assertTrue((home.isAccessButtonPresent() || login.isUserNamePresented()));
	}

	@DataProvider(name = "loginCredential")
	private Object[][] loadTestData() {

		ArrayList listaTestData = CommonUtil.getInstance().getAllElementFromDatabaseByClassName("LoginTest",
				LoginTest.class.getName());
		Object[][] testData = new Object[listaTestData.size()][1];
		for (int i = 0; i < listaTestData.size(); i++) {
			testData[i][0] = listaTestData.get(i);
		}

		return testData;
	}

}
