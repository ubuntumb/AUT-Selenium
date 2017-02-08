package sfp.gov.py.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import sfp.gov.py.pages.DashboardPage;
import sfp.gov.py.pages.LoginPage;
import sfp.gov.py.scripts.LoginTest;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.ConfigLoader;
import sfp.gov.py.util.WaitTool;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class TestBase {

	protected WebDriver driver;
	protected String baseUrl;
	protected DashboardPage home;
	protected LoginPage login;

	@BeforeTest
	public void beforeSuite() {

		this.driver = CHDriver.getDriver();
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_PAGE);
		ConfigLoader conf = ConfigLoader.getInstance();
		this.baseUrl = conf.getConfigResourceConf().getProperty("baseUrl");
		login = new LoginPage(driver, baseUrl);
		
		List valoresIniciales = CommonUtil.getInstance().getAllElementFromDatabaseByClassName(LoginTest.class.getSimpleName(), LoginTest.class.getName());
		Map valorInicial = new HashMap<>();
		
		for(Object valor : valoresIniciales){
			Map valorIni = (Map)valor;
			if(valorIni.containsKey("valid")){
				if(valorIni.get("valid").equals("true")){
					valorInicial.putAll(valorIni);
					break;
				}
			}
		}
		
		login.open();
		home = login.loginAs(valorInicial.get("username").toString(), valorInicial.get("password").toString());
		
	}

	@AfterTest
	public void afterSuite() {
		driver.quit();
	}

}
