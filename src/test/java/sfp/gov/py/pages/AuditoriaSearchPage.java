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

/**
 * @author mbenitez Copyright [2017] [Marcos Benitez] Licensed under the Apache
 *         Open Source License, Version 2.0
 *         http://www.apache.org/licenses/LICENSE-2.0
 */
public class AuditoriaSearchPage extends PageBase {

	private String filtroOperacionDiv;
	private String selectOperacion;
	private String filtroFecha;
	private String filtroUsuario;
	private String rowSelected;

	public AuditoriaSearchPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void limpiarFiltroFecha() {
		waitForElementPresent(By.cssSelector(filtroFecha));
		getElementSearch(By.cssSelector(filtroFecha));
	}

	public void limpiarFiltroUsuario() {
		waitForElementPresent(By.cssSelector(filtroUsuario));
		getElementSearch(By.cssSelector(filtroUsuario));
	}

	public void clickFiltroOperacionDiv() {
		waitForElementPresent(By.cssSelector(filtroOperacionDiv));
		getElementSearch(By.cssSelector(filtroOperacionDiv));
	}

	public void selectFiltroOperacion() {
		waitForElementPresent(By.cssSelector(selectOperacion));
		getElementSearch(By.cssSelector(selectOperacion)).click();
	}

	public Integer verificarExistenciaRegistro() {
		List<WebElement> listadoRows = getElementsSearch(By.cssSelector(rowSelected));
		return listadoRows.size();
	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		filtroOperacionDiv = propertieValue.get("filtroOperacionDiv").toString();
		selectOperacion = propertieValue.get("selectOperacion").toString();
		filtroFecha = propertieValue.get("filtroFecha").toString();
		filtroUsuario = propertieValue.get("filtroUsuario").toString();
		rowSelected = propertieValue.get("rowSelected").toString();
	}

}
