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
public class HomePage extends PageBase {

	
	private String accessButton;
	
	public HomePage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}
	
	public Boolean isAccessButtonPresent(){
		
		return isElementPresent(By.linkText(accessButton));
	}
	
	private void loadPropertiesValues(){
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(), this.getClass().getName());
		accessButton = propertieValue.get("accessButton").toString();
	}

}
