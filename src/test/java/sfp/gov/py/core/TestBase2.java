package sfp.gov.py.core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sfp.gov.py.core.Enums.Driver;
import sfp.gov.py.core.Enums.TestActionGetElementBy;
import sfp.gov.py.core.Enums.TestActionType;
import sfp.gov.py.entities.TestAction;
import sfp.gov.py.entities.TestCase;
import sfp.gov.py.util.ConfigLoader;
import sfp.gov.py.util.WaitTool;

public class TestBase2 {

	protected WebDriver driver;
	protected String baseUrl;

	@BeforeTest
	public void beforeSuite() {
		ConfigLoader conf = ConfigLoader.getInstance();
		this.baseUrl = conf.getConfigResourceConf().getProperty("app.baseUrl");
		String appDriverUse = conf.getConfigResourceConf().getProperty("app.driver");

		if(appDriverUse.equals(Driver.FFDRIVER.toString())){
			this.driver = FFDriver.getDriver();
		}else if(appDriverUse.equals(Driver.CHDRIVER.toString())){
			this.driver = CHDriver.getDriver();
		}else{
			this.driver = FFDriver.getDriver();
		}

		WaitTool.setImplicitWait(driver, WaitTool.DEFAULT_WAIT_4_PAGE);
	}
	
	private By getBy(TestAction action) {
		switch (action.getEnumGetElementBy()) {
		case cssSelector:
			return By.cssSelector(action.getExpression());

		default:
			break;
		}
		return null;
	}
	
	private WebElement getElementSearch(PageBase2 page, TestAction action) {
		By by = getBy(action);
		return page.getElementSearch(by);
	}
	
	public boolean isElementPresent(PageBase2 page, TestAction action) {
		By by = getBy(action);
		page.waitForElementPresent(by);
		return page.isElementPresent(by);
	}

	@Test
	public void testLogin() {
		TestCase testCase = new TestCase();
		
		TestAction actionLimpUser = new TestAction();
		actionLimpUser.setExpression("#form_position input[id$=user]");
		actionLimpUser.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
		actionLimpUser.setEnumType(TestActionType.clear_input);
		
		TestAction actionLimpPass = new TestAction();
		actionLimpPass.setExpression("#form_position input[id$=pass]");
		actionLimpPass.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
		actionLimpPass.setEnumType(TestActionType.clear_input);

		TestAction action1 = new TestAction();
		action1.setExpression("#form_position input[id$=user]");
		action1.setEnumType(TestActionType.element_value_assignation);
		action1.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
		action1.setElementValue("SYSTEM");
		
		TestAction action2 = new TestAction();
		action2.setExpression("#form_position input[id$=pass]");
		action2.setEnumType(TestActionType.element_value_assignation);
		action2.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
		action2.setElementValue("SFPCH123");
		
		TestAction actionSleep = new TestAction();
		actionSleep.setExpression("3");
		actionSleep.setEnumType(TestActionType.sleep);
		
		TestAction action3 = new TestAction();
		action3.setExpression("#form_position button:first-child");
		action3.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
		action3.setEnumType(TestActionType.click_event);
		
		TestAction action4 = new TestAction();
		action4.setExpression(baseUrl+"public/login.xhtml");
		action4.setEnumType(TestActionType.get_page);
		
//		TestAction assertAct = new TestAction();
//		assertAct.setEnumType(TestActionType.check_assert);
		
		TestAction actionAssert1 = new TestAction();
		actionAssert1.setExpression(".buttonMenuTopBarPerson > button");
		actionAssert1.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
		actionAssert1.setEnumType(TestActionType.check_if_element_exists);
		
//		TestAction actionAssert2 = new TestAction();
//		actionAssert2.setExpression("true");
//		actionAssert2.setEnumType(TestActionType.convert_to_boolean);
		
//		assertAct.setTestAction1(actionAssert1);
//		assertAct.setTestAction2(actionAssert2);
		
		
		TestAction actionAlert = new TestAction();
		actionAlert.setExpression("alert('Test Completado. La página se cerrará en 5 segundos');");
		actionAlert.setEnumType(TestActionType.execute_javascript);
		
		TestAction actionSleepFinal = new TestAction();
		actionSleepFinal.setExpression("5");
		actionSleepFinal.setEnumType(TestActionType.sleep);
		
		List<TestAction> actions = new ArrayList<>();
		actions.add(actionLimpUser);
		actions.add(actionLimpPass);
		actions.add(action1);
		actions.add(action2);
		actions.add(actionSleep);
		actions.add(action3);
		actions.add(action4);
		actions.add(actionAssert1);
		actions.add(actionAlert);
		actions.add(actionSleepFinal);
		
		testCase.setTestActions(actions);
		
		PageBase2 page = new PageBase2(driver, baseUrl+"public/login.xhtml");
		page.open();
		
		for (TestAction testAction : testCase.getTestActions()) {
			WebElement element = null;
			switch (testAction.getEnumType()) {
			case clear_input:
				element = getElementSearch(page, testAction);
				element.clear();
				break;
			case click_event:
				element = getElementSearch(page, testAction);
				element.click();
				break;
			case element_value_assignation:
				element = getElementSearch(page, testAction);
				element.sendKeys(testAction.getElementValue());
				break;
			case get_page:
				page = new PageBase2(driver, testAction.getExpression());
				break;
			case check_if_element_exists:
				boolean isPresent = isElementPresent(page, testAction);
				Assert.assertEquals(isPresent, Boolean.parseBoolean("true"));
				break;
			case execute_javascript:
				page.executeJavascript(testAction.getExpression());
				break;
			case sleep:
				int miliseconds = Integer.valueOf(testAction.getExpression()) * 1000;
				try {
					Thread.sleep(miliseconds);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
	}

	@AfterTest
	public void afterSuite() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { TestBase2.class });
		testng.addListener(tla);
		testng.run();

	}
}
