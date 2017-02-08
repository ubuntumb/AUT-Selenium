package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;
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
		super(driver, url+"public/login.xhtml");
		loadPropertiesValues();
	}

	public DashboardPage loginAs(String user, String pass) {

		clearUserInput();
		getElementSearch(By.cssSelector(username)).sendKeys(user);
		clearPasswordInput();
		getElementSearch(By.cssSelector(password)).sendKeys(pass);
		
		getElementSearch(By.cssSelector(submit)).click();
		
		return new DashboardPage(driver,url);
	}
	
	public void clearUserInput(){
		getElementSearch(By.cssSelector(username)).clear();
	}
	
	public void clearPasswordInput(){
		getElementSearch(By.cssSelector(password)).clear();
	}

	public boolean isUserNamePresented() {
		return isElementPresent(By.cssSelector(username));
	}
	
	private void loadPropertiesValues(){
		
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(), this.getClass().getName());
		username = propertieValue.get("username").toString();
		password = propertieValue.get("password").toString();
		submit = propertieValue.get("submit").toString();
	}

}
