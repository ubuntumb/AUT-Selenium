package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;

public class UsuarioSearchPage extends PageBase {
	
	private String usuario;
	private String table;

	public UsuarioSearchPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}
	
	public void buscarPorUsuario(String filtro){
		
		limpiarFiltroUsuario();
		
		WebElement elem = getElementSearch(By.cssSelector(usuario));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "$(\""+usuario+"\").val('"+filtro+"')";
		js.executeScript(script);
		elem.sendKeys("");
		waitForElementPresent(By.cssSelector(table));
	}
	
	public void limpiarFiltroUsuario(){
		
		waitForElementPresent(By.cssSelector(usuario));
		getElementSearch(By.cssSelector(usuario)).clear();
	}
	
	public UsuarioFormPage clickUsuarioEdit(){
		
		waitForElementPresent(By.cssSelector(table));
		getElementSearch(By.cssSelector(table)).click();
		
		return new UsuarioFormPage(driver, url);
	}
	
	public boolean checkUserExists(){
		waitForElementPresent(By.cssSelector(table));
		return isElementPresent(By.cssSelector(table));
	}
	
	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		usuario = propertieValue.get("usuario");
		table = propertieValue.get("table");
	}

}
