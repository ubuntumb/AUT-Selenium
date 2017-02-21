package sfp.gov.py.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;
import sfp.gov.py.util.CommonUtil;

/**
 * @author mbenitez
 * Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

public class ConfiguracionPage extends PageBase {

	private String periodoInactividad;
	private String tiempoExpiracion;
	private String correo;
	private String contrasenha;
	private String host;
	private String puerto;
	private String checkStarttls;
	private String asunto;
	private String btnEnviar;
	private String idSeguimiento;
	private String correoCuentaServicio;
	private String idVista;
	private String pathArchivoCuenta;
	private String btnValidarCuenta;
	private String pathBaseArchivo;
	private String pathBaseAyuda;
	private String extensiones;
	private String tamanhoDocumento;
	private String pathBaseLog;
	private String pathArchivoImgIzq;
	private String pathArchivoImgCentral;
	private String pathArchivoImgDerecho;
	private String pathArchivoMobileImgIzq;
	private String pathArchivoMobileImgDer;
	private String pathArchivoConfigImgIzq;
	private String pathArchivoConfigImgDer;
	private String urlPie;
	private String btnGuardar;
	private String btnVolver;

	public ConfiguracionPage(WebDriver driver, String url) {
		super(driver, url);
		loadPropertiesValues();
	}

	public void cargarPeriodoInactividad(String maxPeriodo) {
		getElementSearch(By.cssSelector(periodoInactividad)).sendKeys(maxPeriodo);
	}

	public void limpiarPeriodInactividad() {
		waitForElementPresent(By.cssSelector(periodoInactividad));
		getElementSearch(By.cssSelector(periodoInactividad)).clear();
	}

	public void cargarTiempoExpiracion(String expiracion) {
		getElementSearch(By.cssSelector(tiempoExpiracion)).sendKeys(expiracion);
	}

	public void limpiarTiempoExpiracion() {
		waitForElementPresent(By.cssSelector(tiempoExpiracion));
		getElementSearch(By.cssSelector(tiempoExpiracion)).clear();
	}
	
	public void cargarLogoPortalIzq(String pathArchivoImg) {
		getElementSearch(By.cssSelector(pathArchivoImgIzq)).sendKeys(pathArchivoImg);
	}
	
	public void limpiarLogoPortalIzq() {
		getElementSearch(By.cssSelector(pathArchivoImgIzq)).clear();
	}
	
	public String getTiempoExpiracion() {
		return getElementSearch(By.cssSelector(tiempoExpiracion)).getAttribute("value");
	}

	private void loadPropertiesValues() {

		propertieValue = CommonUtil.getInstance().getElementFromDatabaseByClassName(this.getClass().getSimpleName(),
				this.getClass().getName());
		periodoInactividad = propertieValue.get("periodoInactividad").toString();
		tiempoExpiracion = propertieValue.get("tiempoExpiracion").toString();
		correo = propertieValue.get("correo").toString();
		contrasenha = propertieValue.get("contrasenha").toString();
		host = propertieValue.get("host").toString();
		puerto = propertieValue.get("puerto").toString();
		checkStarttls = propertieValue.get("checkStarttls").toString();
		asunto = propertieValue.get("asunto").toString();
		btnEnviar = propertieValue.get("btnEnviar").toString();
		idSeguimiento = propertieValue.get("idSeguimiento").toString();
		correoCuentaServicio = propertieValue.get("correoCuentaServicio").toString();
		idVista = propertieValue.get("idVista").toString();
		pathArchivoCuenta = propertieValue.get("pathArchivoCuenta").toString();
		btnValidarCuenta = propertieValue.get("btnValidarCuenta").toString();
		pathBaseArchivo = propertieValue.get("pathBaseArchivo").toString();
		pathBaseAyuda = propertieValue.get("pathBaseAyuda").toString();
		extensiones = propertieValue.get("extensiones").toString();
		tamanhoDocumento = propertieValue.get("tamanhoDocumento").toString();
		pathBaseLog = propertieValue.get("pathBaseLog").toString();
		pathArchivoImgIzq = propertieValue.get("pathArchivoImgIzq").toString();
		pathArchivoImgCentral = propertieValue.get("pathArchivoImgCentral").toString();
		pathArchivoImgDerecho = propertieValue.get("pathArchivoImgDerecho").toString();
		pathArchivoMobileImgIzq = propertieValue.get("pathArchivoMobileImgIzq").toString();
		pathArchivoMobileImgIzq = propertieValue.get("pathArchivoMobileImgDer").toString();
		pathArchivoConfigImgIzq = propertieValue.get("pathArchivoConfigImgIzq").toString();
		pathArchivoConfigImgDer = propertieValue.get("pathArchivoConfigImgDer").toString();
		urlPie = propertieValue.get("urlPie").toString();
		btnGuardar = propertieValue.get("btnGuardar").toString();
		btnVolver = propertieValue.get("btnVolver").toString();
	}

}
