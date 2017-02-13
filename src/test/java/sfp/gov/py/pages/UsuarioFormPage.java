package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;

public class UsuarioFormPage extends PageBase {

	private String nombre;
	private String apellido;
	private String cedula;
	private String correo;
	private String usuario;
	private String roleDiv;
	private String roleFilter;
	private String btnGuardar;
	private String btnVolver;
	private String role;
	private String addRole;
	private String roleSearchFilter;
	private String roleSearchRow;

	public UsuarioFormPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void fillInputNombre(String fillNombre) {
		
		waitForElementPresent(By.cssSelector(nombre));
		getElementSearch(By.cssSelector(nombre)).sendKeys(fillNombre);
	}

	public void limpiarInputNombre() {
		getElementSearch(By.cssSelector(nombre)).clear();
	}

	public void fillInputApellido(String fillApellido) {
		getElementSearch(By.cssSelector(apellido)).sendKeys(fillApellido);
	}

	public void limpiarInputApellido() {
		getElementSearch(By.cssSelector(apellido));
	}

	public void fillInputNroCedula(String fillNroCedula) {
		getElementSearch(By.cssSelector(cedula)).sendKeys(fillNroCedula);
	}

	public void limpiarInputNroCedula() {
		getElementSearch(By.cssSelector(cedula)).clear();
		;
	}

	public void fillInputCorreo(String fillCorreo) {
		getElementSearch(By.cssSelector(correo)).sendKeys(fillCorreo);
	}

	public void limpiarInputCorreo() {
		getElementSearch(By.cssSelector(correo)).clear();
	}

	public void fillInputUsuario(String fillUsuario) {
		getElementSearch(By.cssSelector(usuario)).sendKeys(fillUsuario);
	}

	public void limpiarInputUsuario() {
		getElementSearch(By.cssSelector(usuario)).clear();
		;
	}

	public void clickRoleDiv() {
		getElementSearch(By.cssSelector(roleDiv)).click();
	}

	public void buscarRoleByNombre(String roleName) {

		waitForElementPresent(By.cssSelector(roleFilter));
		WebElement elem = getElementSearch(By.cssSelector(roleFilter));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "$(\"" + roleFilter + "\").val('" + roleName + "')";
		js.executeScript(script);
		elem.sendKeys("");

	}

	public void limpiarFiltroRol() {

		waitForElementPresent(By.cssSelector(roleFilter));
		getElementSearch(By.cssSelector(roleFilter)).clear();
	}

	public UsuarioSearchPage clickBtnGuardar() {

		getElementSearch(By.cssSelector(btnGuardar)).click();

		return new UsuarioSearchPage(driver, url);
	}

	public UsuarioSearchPage clickBtnVolver(String btnVolv) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(btnVolver);
		return new UsuarioSearchPage(driver, url);
	}

	public void selectedRole() {
		waitForElementPresent(By.cssSelector(role));
		getElementSearch(By.cssSelector(role)).click();
	}
	
	public void clickAddRole() {
		getElementSearch(By.cssSelector(addRole)).click();
	}
	
	public void searchRoleAdd(String roleName) {
		waitForElementPresent(By.cssSelector(roleSearchFilter));
		WebElement element = getElementSearch(By.cssSelector(roleSearchFilter));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "$(\"" + roleSearchFilter + "\").val('" + roleName + "')";
		js.executeScript(script);
		element.sendKeys("");
	}
	
	public boolean checkRowRoleAdd() {
		waitForElementPresent(By.cssSelector(roleSearchRow));
		
		return isElementPresent(By.cssSelector(roleSearchRow));
	}
	
	

	private void loadPropertiesValues() {

		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		nombre = propertieValue.get("nombre").toString();
		apellido = propertieValue.get("apellido").toString();
		cedula = propertieValue.get("cedula").toString();
		correo = propertieValue.get("correo").toString();
		usuario = propertieValue.get("usuario").toString();
		roleDiv = propertieValue.get("roleDiv").toString();
		roleFilter = propertieValue.get("roleFilter").toString();
		btnGuardar = propertieValue.get("btnGuardar").toString();
		btnVolver = propertieValue.get("btnVolver").toString();
		role = propertieValue.get("role").toString();
		addRole = propertieValue.get("addRole").toString();
		roleSearchFilter = propertieValue.get("roleSearchFilter").toString();
		roleSearchRow = propertieValue.get("roleSearchRow").toString();

	}

}
