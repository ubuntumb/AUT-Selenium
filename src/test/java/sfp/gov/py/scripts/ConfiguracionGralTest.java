/**
 * 
 */
package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.TestBase;
import sfp.gov.py.pages.ConfiguracionPage;
import sfp.gov.py.util.CommonUtil;

/**
 * @author mbenitez
 * Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class ConfiguracionGralTest extends TestBase {

	private String maxInactividad;
	private String timepoExpiracion;
	private ConfiguracionPage configPage;
	private String pathArchivoImgIzq;
	
	
	@Test(description="Verificacion de Edicion de datos de la Configuracion General",dataProvider="confEdit")
	public void editConfigGral(Map<String,String> testData){
		loadTestData(testData);
		Reporter.log("Verificar Edicion Configuracion General");
		home.goToAdminMenu();
		configPage = home.goToConfigGralView();
		configPage.limpiarPeriodInactividad();
		configPage.limpiarTiempoExpiracion();
		configPage.cargarPeriodoInactividad(maxInactividad);
		configPage.cargarTiempoExpiracion(timepoExpiracion);
		configPage.cargarLogoPortalIzq(pathArchivoImgIzq);
		
		Assert.assertEquals(timepoExpiracion, configPage.getTiempoExpiracion());
		
	}
	
	private void loadTestData(Map<String, String> testData) {
		maxInactividad = testData.get("maxInactividad");
		timepoExpiracion = testData.get("timepoExpiracion");
		pathArchivoImgIzq = testData.get("pathArchivoImgIzq");
	}
	
	@DataProvider(name="confEdit")
	public Object[][] editConfGral(){
		ArrayList datosDePrueba = CommonUtil.getInstance().getAllElementFromDatabaseByClassName(
				ConfiguracionGralTest.class.getSimpleName(), ConfiguracionGralTest.class.getName());
		Object[][] testData = new Object[datosDePrueba.size()][1];
		for (int i = 0; i < datosDePrueba.size(); i++) {
			testData[i][0] = datosDePrueba.get(i);
		}
		
		return testData;
	}
}
