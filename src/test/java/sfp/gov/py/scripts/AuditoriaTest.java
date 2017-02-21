package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.TestBase;
import sfp.gov.py.pages.AuditoriaFormPage;
import sfp.gov.py.pages.AuditoriaPage;
import sfp.gov.py.pages.AuditoriaSearchPage;
import sfp.gov.py.util.CommonUtil;

public class AuditoriaTest extends TestBase {

	private AuditoriaFormPage auditoriaFromPage;
	private AuditoriaSearchPage auditoriaSearchPage;
	private AuditoriaPage auditoriaPage;
	private String filtroTabla;
	private String valid;

	@Test(description = "Verificar la adicion de tablas para auditoria", dataProvider = "auditAdd")
	public void test(Map<String, String> testData) {
		Reporter.log("Run adicion de tablas para auditoria");
		home.goToAdminMenu();
		auditoriaPage = home.goToAuditView();
		auditoriaFromPage = auditoriaPage.clickBtnAddTabla();
		loadTestData(testData);
		auditoriaFromPage.cargarFiltro(filtroTabla);
		auditoriaFromPage.checkFilterResult();
		auditoriaSearchPage = auditoriaFromPage.clickBtnGuardar();
		auditoriaFromPage = auditoriaPage.clickBtnAddTabla();
		auditoriaFromPage.limpiarFiltro();
		boolean validate = auditoriaFromPage.countFilterTable() == Integer.parseInt(valid) ? true : false;
		Assert.assertTrue(validate);
		
	}

	private void loadTestData(Map<String, String> testData) {
		filtroTabla = testData.get("filtroTabla");
		valid = testData.get("valid").toString();
	}

	@DataProvider(name = "auditAdd")
	public Object[][] editConfGral() {
		ArrayList datosDePrueba = CommonUtil.getInstance().getAllElementFromDatabaseByClassName(
				AuditoriaTest.class.getSimpleName(), AuditoriaTest.class.getName());
		Object[][] testData = new Object[datosDePrueba.size()][1];
		for (int i = 0; i < datosDePrueba.size(); i++) {
			testData[i][0] = datosDePrueba.get(i);
		}

		return testData;
	}
}
