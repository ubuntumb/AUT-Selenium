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
	protected enum TIPO_DRIVER { FFDRIVER, CHDRIVER};

	@BeforeTest
	public void beforeSuite() {

		ConfigLoader conf = ConfigLoader.getInstance();
		this.baseUrl = conf.getConfigResourceConf().getProperty("app.baseUrl");
		String appDriverUse = conf.getConfigResourceConf().getProperty("app.driver");
		
		if(appDriverUse.equals(TIPO_DRIVER.FFDRIVER.toString())){
			this.driver = FFDriver.getDriver();
		}else if(appDriverUse.equals(TIPO_DRIVER.CHDRIVER.toString())){
			this.driver = CHDriver.getDriver();
		}else{
			this.driver = FFDriver.getDriver();
		}
		
		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_PAGE);
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
