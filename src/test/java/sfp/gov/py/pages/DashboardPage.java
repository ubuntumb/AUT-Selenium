package sfp.gov.py.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class DashboardPage extends PageBase {
	
	private String username;
	private String exitButtonLink;
	

	public DashboardPage(WebDriver driver,String url) {
		super(driver,url);
		loadPropertiesValues();
	}

	public boolean isUserNameLinkPresented() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return isElementPresent(By.cssSelector(username));
	}

	public boolean isExitButtonLinkPresented() {
		WaitTool.waitForElement(driver, By.cssSelector(exitButtonLink),WaitTool.DEFAULT_WAIT_4_PAGE);
		return isElementPresent(By.cssSelector(exitButtonLink));
	}

	public void showExitButtonLink() {
		driver.manage().timeouts().implicitlyWait(WaitTool.DEFAULT_WAIT_4_ELEMENT, TimeUnit.SECONDS);
		getElementSearch(By.cssSelector(username)).click();
	}

	public HomePage clickToExitButton() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(exitButtonLink);
		return new HomePage(driver,url);
	}
	
	private void loadPropertiesValues(){
		propertieValue = initElementAttributes(this.getClass().getSimpleName());
		username = propertieValue.get("username").toString();
		exitButtonLink = propertieValue.get("exitButtonLink").toString();
	}

}
