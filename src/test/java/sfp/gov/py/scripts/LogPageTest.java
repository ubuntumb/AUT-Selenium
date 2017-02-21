/**
 * 
 */
package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.TestBase;
import sfp.gov.py.pages.LogPage;
import sfp.gov.py.util.CommonUtil;
import sfp.gov.py.util.FileManager;

/**
 * @author mbenitez
 * Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class LogPageTest extends TestBase {
	
	private String nombreFichero;
	private LogPage logPage;
	private String nombreArchivoDescargado;

	@Test(description="Verificacion de descarga de archivo",dataProvider="logTesg")
	public void testDownload(Map<String, String> testData){
		
		home.goToAdminMenu();
		logPage = home.goToLogView();
		loadTestData(testData);
		logPage.limpiarFiltroNombreFichero();
		logPage.cargarFiltroNombreFichero(nombreFichero);
		logPage.clickDownload();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertTrue(FileManager.validarExistenciaArchivoPorNombre(nombreArchivoDescargado));
		
	}
	private void loadTestData(Map<String, String> testData) {
		nombreFichero = testData.get("nombreFichero");
		nombreArchivoDescargado = testData.get("nombreArchivoDescargado");
		//timepoExpiracion = testData.get("timepoExpiracion");
		//pathArchivoImgIzq = testData.get("pathArchivoImgIzq");
	}
	
	@DataProvider(name="logTesg")
	public Object[][] editConfGral(){
		ArrayList datosDePrueba = CommonUtil.getInstance().getAllElementFromDatabaseByClassName(
				LogPageTest.class.getSimpleName(), LogPageTest.class.getName());
		Object[][] testData = new Object[datosDePrueba.size()][1];
		for (int i = 0; i < datosDePrueba.size(); i++) {
			testData[i][0] = datosDePrueba.get(i);
		}
		
		return testData;
	}
}
