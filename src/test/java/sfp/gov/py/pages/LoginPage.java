package sfp.gov.py.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.WaitTool;
/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class LoginPage extends PageBase {
	
	private String username;
	private String password;
	private String submit;
	
	public LoginPage(WebDriver driver, String url) {
		super(driver, url+"login.xhtml");
		loadPropertiesValues();
	}

	public DashboardPage loginAs(String user, String pass) {

		getElementSearch(By.cssSelector(username)).sendKeys(user);
		getElementSearch(By.cssSelector(password)).sendKeys(pass);
		getElementSearch(By.cssSelector(submit)).click();
		driver.manage().timeouts().implicitlyWait(WaitTool.DEFAULT_WAIT_4_PAGE,TimeUnit.SECONDS);
		return new DashboardPage(driver,url);
	}

	public boolean isUserNamePresented() {
		return isElementPresent(By.cssSelector(username));
	}
	
	private void loadPropertiesValues(){
		propertieValue = initElementAttributes(this.getClass().getSimpleName());
		username = propertieValue.get("username").toString();
		password = propertieValue.get("password").toString();
		submit = propertieValue.get("submit").toString();
	}

}
