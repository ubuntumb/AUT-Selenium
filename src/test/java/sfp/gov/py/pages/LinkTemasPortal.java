package sfp.gov.py.pages;

import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;

public class LinkTemasPortal extends PageBase {
	
	private String idMenu;

	public LinkTemasPortal(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	private void loadPropertiesValues() {
		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		idMenu = propertieValue.get("idMenu").toString();
	}
	
	public void clickTemas() {
		getElementSearchById(idMenu).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
