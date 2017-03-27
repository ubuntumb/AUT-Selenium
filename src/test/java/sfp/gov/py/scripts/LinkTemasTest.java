package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.CHDriver;
import sfp.gov.py.core.FFDriver;
import sfp.gov.py.core.TestBase;
import sfp.gov.py.pages.LinkTemasPortal;
import sfp.gov.py.pages.LoginPage;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.ConfigLoader;
import sfp.gov.py.util.WaitTool;

public class LinkTemasTest extends TestBase {
	
	private LinkTemasPortal temas;

	@Override
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
		temas = new LinkTemasPortal(driver, baseUrl);
		
//		LoginPage login = new LoginPage(driver, baseUrl);
//		
//		List valoresIniciales = CommonUtil.getInstance().getAllElementFromDatabaseByClassName(LoginTest.class.getSimpleName(), LoginTest.class.getName());
//		Map valorInicial = new HashMap<>();
//		
//		for(Object valor : valoresIniciales){
//			Map valorIni = (Map)valor;
//			if(valorIni.containsKey("valid")){
//				if(valorIni.get("valid").equals("true")){
//					valorInicial.putAll(valorIni);
//					break;
//				}
//			}
//		}
//		
//		login.open();
//		home = login.loginAs(valorInicial.get("username").toString(), valorInicial.get("password").toString());
	}
	
	@Test(description = "Go to Login and try access control", groups = "acceso", dataProvider = "loginCredential")
	public void goToLink(Map values) {
		
		temas.open();
		temas.clickTemas();
		

//		Assert.assertEquals(dashboard.isUserNameLinkPresented(), Boolean.parseBoolean(valid));
	}
	
	@DataProvider(name = "loginCredential")
	private Object[][] loadTestData() {

		ArrayList listaTestData = CommonUtil.getInstance().getAllElementFromDatabaseByClassName("LoginTest",
				LoginTest.class.getName());
		Object[][] testData = new Object[listaTestData.size()][1];
		for (int i = 0; i < listaTestData.size(); i++) {
			testData[i][0] = listaTestData.get(i);
		}

		return testData;
	}
}
