package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.WaitTool;

public class RolePage extends PageBase {

	private String addRole;
	private String btnUpdate;

	public RolePage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public RoleFormPage clickRoleAddButton() {
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_PAGE);
		getElementSearch(By.cssSelector(addRole)).click();
		return new RoleFormPage(driver, url);
	}
	
	public void clickBtnUpdate(){
		WaitTool.waitForElementPresent(driver, By.cssSelector(btnUpdate), WaitTool.DEFAULT_WAIT_4_ELEMENT);
		getElementSearch(By.cssSelector(btnUpdate)).click();
	}
	
	public RoleSearchPage searchPage() {
		return new RoleSearchPage(driver, url);
	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(), this.getClass().getName());
		addRole = propertieValue.get("addRole").toString();
		btnUpdate = propertieValue.get("btnUpdate").toString();
	}
	
	

}
