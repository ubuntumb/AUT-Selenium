package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;

public class UsuarioPage extends PageBase {

	private String addUsuario;
	private String btnUpdate;
	
	public UsuarioPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}
	
	public UsuarioFormPage clickAddButton() {
		
		waitForElementPresent(By.cssSelector(addUsuario));
		getElementSearch(By.cssSelector(addUsuario)).click();
		
		return new UsuarioFormPage(driver, url);
	}
	
	public void clickBtnUpdate(){
		
		waitForElementPresent(By.cssSelector(btnUpdate));
		getElementSearch(By.cssSelector(btnUpdate)).click();
	}
	
	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(), this.getClass().getName());
		addUsuario = propertieValue.get("addUsuario").toString();
		btnUpdate = propertieValue.get("btnUpdate").toString();
	}
	

}
