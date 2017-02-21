/**
 * 
 */
package sfp.gov.py.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author mbenitez Copyright [2017] [Marcos Benitez] Licensed under the Apache
 *         Open Source License, Version 2.0
 *         http://www.apache.org/licenses/LICENSE-2.0
 */
public class FileManager {

	public static boolean validarExistenciaArchivoPorNombre(String nombre) {

		List<File> archivos = Arrays.asList(obtenerListadoArchivoDesdeDirectorio());
		boolean exists = false;
		for (File file : archivos) {
			if (file.getName().equalsIgnoreCase(nombre)) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	public static boolean validarExistenciaArchivoPorExtension(String extension) {

		ArrayList<File> archivos = (ArrayList<File>) Arrays.asList(obtenerListadoArchivoDesdeDirectorio());
		boolean exists = false;
		for (File file : archivos) {
			if (file.getName().contains(extension)) {
				exists = true;
				break;
			}
		}
		return exists;
	}

	public static File[] obtenerListadoArchivoDesdeDirectorio() {

		Properties properties = ConfigLoader.getInstance().getConfigResourceConf();
		File directorio = new File(properties.getProperty("app.download-path"));
		File[] archivos = directorio.listFiles();

		return archivos;
	}
}
