/**
 * 
 */
package sfp.gov.py.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.WaitTool;

/**
 * @author mbenitez Copyright [2017] [Marcos Benitez] Licensed under the Apache
 *         Open Source License, Version 2.0
 *         http://www.apache.org/licenses/LICENSE-2.0
 */
public class AuditoriaFormPage extends PageBase {

	private String checkTodos;
	private String filtro;
	private String check;
	private String btnGuardar;
	private String btnVolver;
	private String cantidadTablasAuditoria;

	public AuditoriaFormPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void clickAddAllTable() {
		waitForElementPresent(By.cssSelector(checkTodos));
		getElementSearch(By.cssSelector(checkTodos)).click();
	}

	public void limpiarFiltro() {
		waitForElementPresent(By.cssSelector(filtro));
		String script = "$(\"" + filtro + "\").val('')";
		executeJavascript(script);
		script = "$(\"" + filtro + "\").trigger('keyup')";
		executeJavascript(script);
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_ELEMENT);
	}

	public void cargarFiltro(String nombreTabla) {

		waitForElementPresent(By.cssSelector(filtro));
		String script = "$(\"" + filtro + "\").val('" + nombreTabla + "')";
		executeJavascript(script);
		script = "$(\"" + filtro + "\").trigger('keyup')";
		executeJavascript(script);
	}

	public void checkFilterResult() {
		waitForElementPresent(By.cssSelector(check));
		getElementSearch(By.cssSelector(check)).click();
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_ELEMENT);
	}

	public AuditoriaSearchPage clickBtnGuardar() {

		executeJavascript(btnGuardar);

		return new AuditoriaSearchPage(driver, url);
	}

	public AuditoriaSearchPage clickBtnVolver() {

		executeJavascript(btnVolver);

		return new AuditoriaSearchPage(driver, url);
	}
	
	public Integer countFilterTable() {
		waitForElementPresent(By.cssSelector(cantidadTablasAuditoria));
		List <WebElement> listTableFilter = getElementsSearch(By.cssSelector(cantidadTablasAuditoria));
		System.out.println("cant:" + listTableFilter.size());
		return listTableFilter.size();
	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		checkTodos = propertieValue.get("checkTodos").toString();
		check = propertieValue.get("check").toString();
		btnGuardar = propertieValue.get("btnGuardar").toString();
		btnVolver = propertieValue.get("btnVolver").toString();
		filtro = propertieValue.get("filtro").toString();
		cantidadTablasAuditoria = propertieValue.get("cantidadTablasAuditoria").toString();
	}

}
