package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.WaitTool;

public class RoleSearchPage extends PageBase {
	private String nombre;
	private String estado;
	private String btnEdit;
	private String table;

	public RoleSearchPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void findByEstado(String fillEstado) {
		getElementSearch(By.cssSelector(estado)).sendKeys(fillEstado);
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_PAGE);
	}

	public void findByNombre(String fillNombre) {
		WaitTool.waitForElementPresent(driver, By.cssSelector(nombre), WaitTool.DEFAULT_WAIT_4_ELEMENT);
		WebElement elem = getElementSearch(By.cssSelector(nombre));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "$(\""+nombre+"\").val('"+fillNombre+"')";
		js.executeScript(script);
		elem.sendKeys("");
	}

	public RoleFormPage clickLinkEdit() {
		WaitTool.waitForElementPresent(driver, By.cssSelector(btnEdit), WaitTool.DEFAULT_WAIT_4_ELEMENT);
		getElementSearch(By.cssSelector(btnEdit)).click();
		return new RoleFormPage(driver, url);
	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(), this.getClass().getName());
		nombre = propertieValue.get("nombre").toString();
		estado = propertieValue.get("estado").toString();
		table = propertieValue.get("table").toString();
		btnEdit = propertieValue.get("btnEdit").toString();
	}

	public boolean isNewRole() {
		return isElementPresent(By.cssSelector(table));
	}

}
