package sfp.gov.py.pages;

import org.openqa.selenium.WebDriver;

import sfp.gov.py.core.PageBase;

public class ConfiguracionPage extends PageBase {

	private String periodoInactividad;
	private String tiempoExpiracion;
	private String correo;
	private String contrasenha;
	private String host;
	private String puerto;
	private String checkStarttls;
	private String asunto;
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
	}

}
