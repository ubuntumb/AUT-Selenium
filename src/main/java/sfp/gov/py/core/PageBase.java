package sfp.gov.py.core;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sfp.gov.py.util.WaitTool;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez] Licensed under the Apache
 *         Open Source License, Version 2.0
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class PageBase {

	protected WebDriver driver;
	protected String url;
	protected String title;
	protected Map<String, String> propertieValue;

	public PageBase(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
	}

	protected void setUrl(String url) {
		this.url = url;
	}

	protected String getUrl() {
		return this.url;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	protected String getTitle() {
		return this.title;
	}

	protected WebDriver getDriver() {
		return this.driver;
	}

	public void open() {
		driver.get(url);
	}

	protected WebElement getElementSearchById(String id) {
		return driver.findElement(By.id(id));
	}

	protected WebElement getElementSearchByLinkText(String text) {
		return driver.findElement(By.linkText(text));
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected WebElement getElementSearch(By by) {
		return driver.findElement(by);
	}

	protected List<WebElement> getElementsSearch(By by) {
		return driver.findElements(by);
	}

	public void waitForElementPresent(By selector) {
		WaitTool.waitForElementPresent(driver, selector, WaitTool.DEFAULT_WAIT_4_ELEMENT);
	}

	public void executeJavascript(String javascript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javascript);
	}

}
