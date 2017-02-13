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
import sfp.gov.py.pages.UsuarioFormPage;
import sfp.gov.py.pages.UsuarioPage;
import sfp.gov.py.pages.UsuarioSearchPage;
import sfp.gov.py.util.CommonUtil;

/**
 * @author mbenitez Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class UsuarioTest extends TestBase {

	private UsuarioPage usuarioPage;
	private UsuarioFormPage usuarioFrom;
	private UsuarioSearchPage usuarioSearch;

	private String nombre;
	private String apellido;
	private String cedula;
	private String correo;
	private String usuario;
	private String rolFilter;
	private String nombreEdit;
	private String valid;

	@Test(dataProvider = "addUser", description = "Verificar creación de nuevo Usuario")
	public void addNewUser(Map<String, String> testData) {
		home.goToAdminMenu();
		usuarioPage = home.goToUsuarioView();
		usuarioFrom = usuarioPage.clickAddButton();
		loadTestData(testData);
		usuarioFrom.fillInputNombre(nombre);
		usuarioFrom.fillInputApellido(apellido);
		usuarioFrom.fillInputNroCedula(cedula);
		usuarioFrom.fillInputCorreo(correo);
		usuarioFrom.fillInputUsuario(usuario);
		usuarioFrom.clickRoleDiv();
		usuarioFrom.buscarRoleByNombre(rolFilter);
		usuarioFrom.selectedRole();
		usuarioFrom.clickAddRole();
		usuarioSearch = usuarioFrom.clickBtnGuardar();
		usuarioSearch.buscarPorUsuario(usuario);
		boolean isExistsNewUser = usuarioSearch.checkUserExists();

		Assert.assertEquals(isExistsNewUser, Boolean.parseBoolean(valid));

	}

	@Test(description = "Verificar la edición de Usuario", dependsOnMethods = "addNewUser")
	public void editNewUser() {

		usuarioSearch.limpiarFiltroUsuario();
		usuarioSearch.buscarPorUsuario(usuario);
		usuarioFrom = usuarioSearch.clickUsuarioEdit();
		usuarioFrom.limpiarInputNombre();
		usuarioFrom.fillInputNombre(nombreEdit);
		usuarioSearch = usuarioFrom.clickBtnGuardar();

		usuarioSearch.limpiarFiltroUsuario();
		usuarioSearch.buscarPorUsuario(usuario);

		boolean isExistsEditUser = usuarioSearch.checkUserExists();
		Assert.assertEquals(isExistsEditUser, Boolean.parseBoolean(valid));

	}

	private void loadTestData(Map<String, String> testData) {
		nombre = testData.get("nombre");
		apellido = testData.get("apellido");
		cedula = testData.get("cedula");
		correo = testData.get("correo");
		usuario = testData.get("usuario");
		rolFilter = testData.get("rolFilter");
		if (testData.containsKey("valid"))
			valid = testData.get("valid").toString();
		if (testData.containsKey("nombreEdit"))
			nombreEdit = testData.get("nombreEdit").toString();
	}

	@DataProvider(name = "addUser")
	public Object[][] userTestData() {
		ArrayList<Map<String, String>> listaTestData = CommonUtil.getInstance()
				.getAllElementFromDatabaseByClassName(UsuarioTest.class.getSimpleName(), UsuarioTest.class.getName());
		Object[][] testData = new Object[listaTestData.size()][1];
		for (int i = 0; i < listaTestData.size(); i++) {
			testData[i][0] = listaTestData.get(i);
		}
		return testData;
	}
}
