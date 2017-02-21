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
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class LogPage extends PageBase {

	private String btnActualizar;
	private String nombreFichero;
	private String fechaFichero;
	private String accion;

	public LogPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void limpiarFiltroNombreFichero() {
		waitForElementPresent(By.cssSelector(nombreFichero));
		getElementSearch(By.cssSelector(nombreFichero)).clear();
	}

	public void cargarFiltroNombreFichero(String filtro) {
		String script = "$(\"" + nombreFichero + "\").val('" + filtro + "')";
		executeJavascript(script);

		script = "$(\"" + nombreFichero + "\").trigger('keyup')";
		executeJavascript(script);
	}

	public void limpiarFiltroFecha() {
		waitForElementPresent(By.cssSelector(fechaFichero));
		getElementSearch(By.cssSelector(fechaFichero)).clear();
	}

	public void cargarFiltroFecha(String fecha) {
		String script = "$(\"" + fechaFichero + "\").val(" + fecha + ")";
		executeJavascript(script);
		script = "$(\"" + nombreFichero + "\").trigger('keyup')";
		executeJavascript(script);
	}

	public void clickBtnActualizar() {

		waitForElementPresent(By.cssSelector(btnActualizar));
		getElementSearch(By.cssSelector(btnActualizar)).click();

	}

	public void clickDownload() {

		waitForElementPresent(By.cssSelector(accion));
		getElementSearch(By.cssSelector(accion)).click();

	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		btnActualizar = propertieValue.get("btnActualizar").toString();
		nombreFichero = propertieValue.get("nombreFichero").toString();
		fechaFichero = propertieValue.get("fechaFichero").toString();
		accion = propertieValue.get("accion").toString();
	}

}
