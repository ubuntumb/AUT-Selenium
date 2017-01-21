package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.TestBase;
import sfp.gov.py.pages.DashboardPage;
import sfp.gov.py.pages.HomePage;
import sfp.gov.py.pages.LoginPage;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */

public class LoginTest extends TestBase {

	private LoginPage login;
	private DashboardPage dashboard;
	private HomePage home;

	@BeforeMethod
	public void beforeMethod() {
		login = new LoginPage(driver, baseUrl);
	}

	@Test(description = "Go to Login and try access control", groups = "acceso", dataProvider = "loginCredential")
	public void verifyAccessControl(String username, String password, boolean valid) {
		System.out.println("username: " + username + " password: " + password);
		login.open();
		dashboard = login.loginAs(username, password);
		Assert.assertEquals(dashboard.isUserNameLinkPresented(), valid);
	}

	@Test(description = "Go to Logout", dependsOnMethods = "verifyAccessControl", groups = "standalone")
	public void verifyLogout() {
		dashboard.showExitButtonLink();
		home = dashboard.clickToExitButton();
		Assert.assertTrue((home.isAccessButtonPresent() || login.isUserNamePresented()));
	}

	@DataProvider(name = "loginCredential")
	private Object[][] loadTestData() {
		ArrayList<Map> listValues = initElementAttributes("LoginTest");
		listValues.forEach(System.out::println);
		Integer countRecord = listValues.size() / 2;
		Object[][] valueToReturn = new Object[countRecord][3];
		String username = "";
		String password = "";
		String valid = "";
		Integer index = 0;
		for (Map value : listValues) {
			if (value.get("ELEMENT_NAME").toString().equals("username")) {
				username = (String) value.get("ELEMENT_INPUT");
				valid = (String) value.get("ELEMENT_OUPUT");
			}
			if (value.get("ELEMENT_NAME").toString().equals("password")) {
				password = (String) value.get("ELEMENT_INPUT");
			}

			if (!username.equals("") && !password.equals("")) {
				valueToReturn[index][0] = username.substring(0);
				valueToReturn[index][1] = password.substring(0);
				valueToReturn[index][2] = Boolean.parseBoolean(valid);

				username = "";
				password = "";
				valid = "";
				index++;
			}

		}
		return valueToReturn;
	}

}
