package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.WaitTool;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez] Licensed under the Apache
 *         Open Source License, Version 2.0
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class DashboardPage extends PageBase {

	private String username;
	private String exitButtonLink;
	private String slimMenu;
	private String roleButton;
	private String menuAdmin;
	private String usuarioButton;
	private String configGralButton;
	private String logButton;
	private String auditButton;

	public DashboardPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public boolean isUserNameLinkPresented() {

		waitForElementPresent(By.cssSelector(username));
		return isElementPresent(By.cssSelector(username));
	}

	public boolean isExitButtonLinkPresented() {

		waitForElementPresent(By.cssSelector(exitButtonLink));
		return isElementPresent(By.cssSelector(exitButtonLink));
	}

	public void showExitButtonLink() {

		waitForElementPresent(By.cssSelector(username));
		getElementSearch(By.cssSelector(username)).click();
	}

	public HomePage clickToExitButton() {

		executeJavascript(exitButtonLink);

		return new HomePage(driver, url);
	}

	public void goToAdminMenu() {

		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_PAGE);
		executeJavascript(slimMenu);
		getElementSearch(By.cssSelector(menuAdmin)).click();

	}

	public RolePage goToRoleView() {

		executeJavascript(roleButton);

		return new RolePage(driver, url);

	}

	public UsuarioPage goToUsuarioView() {

		executeJavascript(usuarioButton);

		return new UsuarioPage(driver, url);
	}

	public ConfiguracionPage goToConfigGralView() {

		executeJavascript(configGralButton);

		return new ConfiguracionPage(driver, url);
	}

	public LogPage goToLogView() {

		executeJavascript(logButton);

		return new LogPage(driver, url);
	}
	
	public AuditoriaPage goToAuditView() {
		WaitTool.resetImplicitWait(driver);
		executeJavascript(auditButton);
		return new AuditoriaPage(driver, url);
	}
	

	private void loadPropertiesValues() {

		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		username = propertieValue.get("username").toString();
		slimMenu = propertieValue.get("slimMenu").toString();
		roleButton = propertieValue.get("roleButton");
		menuAdmin = propertieValue.get("menuAdmin");
		exitButtonLink = propertieValue.get("exitButtonLink").toString();
		usuarioButton = propertieValue.get("usuarioButton").toString();
		configGralButton = propertieValue.get("configGralButton").toString();
		logButton = propertieValue.get("logButton").toString();
		auditButton = propertieValue.get("auditButton").toString();
	}


}
