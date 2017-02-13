package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.WaitTool;

public class RoleFormPage extends PageBase {

	private String nombre;
	private String estado;
	private String estadoDiv;
	private String filtro;
	private String linkBuscar;
	private String btnGuardar;
	private String btnVolver;
	private String permisoCheck;

	public RoleFormPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void fillInputName(String roleName) {
		getElementSearch(By.cssSelector(nombre)).sendKeys(roleName);
	}

	public void clearInput() {
		getElementSearch(By.cssSelector(nombre)).clear();
	}

	public void selectState() {
		getElementSearch(By.cssSelector(estadoDiv)).click();
		getElementSearch(By.cssSelector(estado)).click();
	}

	public void fillInputFilter(String filterData) {

		getElementSearch(By.cssSelector(filtro)).clear();
		getElementSearch(By.cssSelector(filtro)).sendKeys(filterData);
	}

	public void clickToFind() {
		getElementSearch(By.cssSelector(linkBuscar)).click();
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_ELEMENT);
	}

	public RoleSearchPage clickToBtnGuardar() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(btnGuardar);

		return new RoleSearchPage(driver, url);
	}

	public RoleSearchPage clickToBtnVolver() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(btnVolver);
		return new RoleSearchPage(driver, url);
	}

	public RoleSearchPage checkPermiso() {
		WaitTool.waitForElementPresent(driver, By.cssSelector(permisoCheck), WaitTool.DEFAULT_WAIT_4_PAGE);
		getElementSearch(By.cssSelector(permisoCheck)).click();
		return clickToBtnGuardar();
	}

	private void loadPropertiesValues() {

		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		nombre = propertieValue.get("nombre").toString();
		estado = propertieValue.get("estado").toString();
		estadoDiv = propertieValue.get("estadoDiv").toString();
		filtro = propertieValue.get("filtro").toString();
		linkBuscar = propertieValue.get("linkBuscar").toString();
		btnGuardar = propertieValue.get("btnGuardar").toString();
		btnVolver = propertieValue.get("btnVolver").toString();
		permisoCheck = propertieValue.get("permisoCheck").toString();

	}
}
