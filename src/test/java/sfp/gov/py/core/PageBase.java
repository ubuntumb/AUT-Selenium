package sfp.gov.py.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sfp.gov.py.util.ConnectionUtil;
import sfp.gov.py.util.Operation;

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

	protected Map<String, String> initElementAttributes(String className) {
		Operation operation = new Operation(ConnectionUtil.getInstance().getConnection());
		Map<String, String> params = new HashMap<String, String>();
		params.put("className", className);
		ArrayList<Map> listValues = operation.getArrayDataFromParams("", params);

		return copyDataIntoEntity(className, listValues);
	}

	private Map<String, String> copyDataIntoEntity(String className, ArrayList<Map> listValues) {
		Map<String, String> valuesToReturn = new HashMap<>();
		try {
			Class clazz = Class.forName("sfp.gov.py.pages." + className);
			Field[] listProperties = clazz.getDeclaredFields();
			for (Field field : listProperties) {
				valuesToReturn.put(field.getName(), "");
			}
			for (Map value : listValues) {
				if (valuesToReturn.containsKey(value.get("ELEMENT_NAME").toString())) {
					valuesToReturn.remove(value.get("ELEMENT_NAME").toString());
					valuesToReturn.put(value.get("ELEMENT_NAME").toString(), value.get("ELEMENT_INPUT").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valuesToReturn;
	}

}
