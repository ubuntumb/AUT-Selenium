/**
 * 
 */
package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;

/**
 * @author mbenitez Copyright [2017] [Marcos Benitez] Licensed under the Apache
 *         Open Source License, Version 2.0
 *         http://www.apache.org/licenses/LICENSE-2.0
 */
public class AuditoriaPage extends PageBase {

	private String selectTablaDiv;
	private String selectTablaFilter;
	private String selectTabla;
	private String btnConfigurar;

	public AuditoriaPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void clickSelectTablaDiv() {
		waitForElementPresent(By.cssSelector(selectTablaDiv));
		getElementSearch(By.cssSelector(selectTablaDiv)).click();
	}

	public void limpiarSelectTablaFilter() {
		waitForElementPresent(By.cssSelector(selectTablaFilter));
		getElementSearch(By.cssSelector(selectTablaFilter)).clear();
	}

	public void cargarSelectTablaFilter(String filter) {

		String script = "$(\"" + selectTablaFilter + "\").val('" + filter + "')";
		executeJavascript(script);

		script = "$(\"" + selectTablaFilter + "\").trigger('keyup')";
		executeJavascript(script);

	}

	public void clickSelectTabla() {
		waitForElementPresent(By.cssSelector(selectTabla));
		getElementSearch(By.cssSelector(selectTabla)).click();
	}

	public AuditoriaFormPage clickBtnAddTabla() {
		waitForElementPresent(By.cssSelector(btnConfigurar));
		getElementSearch(By.cssSelector(btnConfigurar)).click();
		return new AuditoriaFormPage(driver, url);
	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		selectTablaDiv = propertieValue.get("selectTablaDiv").toString();
		selectTablaFilter = propertieValue.get("selectTablaFilter").toString();
		selectTabla = propertieValue.get("selectTabla").toString();
		btnConfigurar = propertieValue.get("btnConfigurar").toString();

	}

}
