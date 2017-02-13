package sfp.gov.py.scripts;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sfp.gov.py.core.TestBase;
import sfp.gov.py.pages.RoleFormPage;
import sfp.gov.py.pages.RolePage;
import sfp.gov.py.pages.RoleSearchPage;
import sfp.gov.py.util.CommonUtil;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache 
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class RoleTest extends TestBase {

	private RoleFormPage roleForm;
	private RolePage role;
	private RoleSearchPage roleSearch;
	private String nombre;
	private String funcion;
	private String nombreEdit;
	private String valid;

	@Test(description = "Verificar la creacion de nuevo Rol", dataProvider = "addRole")
	public void addNewRole(Map<String, String> testValue) {
		
		home.goToAdminMenu();
		role = home.goToRoleView();
		roleForm = role.clickRoleAddButton();
		loadTestData(testValue);
		roleForm.fillInputName(nombre);
		roleForm.fillInputFilter(funcion);
		roleForm.selectState();
		roleForm.clickToFind();
		roleSearch = roleForm.checkPermiso();
		roleSearch.findByNombre(nombre);

		boolean isNewRole = roleSearch.isNewRole();
		role.clickBtnUpdate();
		Assert.assertEquals(isNewRole, Boolean.parseBoolean(valid));
	}

	@Test(description = "Verificar la edicion de Rol", dependsOnMethods = "addNewRole")
	public void editRole() {
		role.clickBtnUpdate();
		roleSearch.findByNombre(nombre);
		roleForm = roleSearch.clickLinkEdit();

		roleForm.clearInput();
		roleForm.fillInputName(nombreEdit);
		roleForm.clickToBtnGuardar();

		roleSearch.findByNombre(nombre);
		boolean isNewRole = roleSearch.isNewRole();
		Assert.assertEquals(isNewRole, Boolean.parseBoolean(valid));
	}

	private void loadTestData(Map<String, String> testValue) {
		nombre = testValue.get("nombre").toString();
		funcion = testValue.get("funcion").toString();
		if (testValue.containsKey("valid"))
			valid = testValue.get("valid").toString();
		if (testValue.containsKey("nombreEdit"))
			nombreEdit = testValue.get("nombreEdit").toString();
	}

	@DataProvider(name = "addRole")
	public Object[][] addRole() {
		ArrayList<Map<String, String>> listaTestData = CommonUtil.getInstance()
				.getAllElementFromDatabaseByClassName(RoleTest.class.getSimpleName(), RoleTest.class.getName());
		Object[][] testData = new Object[listaTestData.size()][1];
		for (int i = 0; i < listaTestData.size(); i++) {
			testData[i][0] = listaTestData.get(i);
		}

		return testData;
	}

}
