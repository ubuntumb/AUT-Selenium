package sfp.gov.py.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez] 
 * Licensed under the Apache
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class ConnectionUtil {

	private static ConnectionUtil util;
	private Connection connection;
	private String ruta;
	private String usuario;
	private String driver;
	private String password;
	private Properties properties;

	private ConnectionUtil() {

		loadProperties();
		loadConnection();
	}

	private void loadProperties() {
		properties = ConfigLoader.getInstance().getConfigResourceConf();
		usuario = (String) properties.get("bd.usuario");
		password = (String) properties.get("bd.password");
		driver = (String) properties.get("bd.driver");
		ruta = (String) properties.get("bd.ruta");

	}

	private void loadConnection() {
		connection = null;

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(ruta, usuario, password);
		} catch (Exception e) {
			System.out.println("ConnectionUtil -> loadConnection");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public static ConnectionUtil getInstance() {
		if (util == null) {
			util = new ConnectionUtil();
		}
		return util;
	}

	public static void main(String[] arguments) {
		StringBuilder query = new StringBuilder();
		// query.append("DROP TABLE test_data; ");
		// query.append("ALTER TABLE test_data ADD COLUMN REFERENCE
		// VARCHAR('255');");
		/*
		 * query.append("CREATE TABLE test_data ( "); query.append(
		 * "id SERIAL PRIMARY KEY, "); query.append("class_name VARCHAR(255),  "
		 * ); query.append("element_name VARCHAR(255), "); query.append(
		 * "element_input TEXT, "); query.append("element_ouput TEXT ");
		 * query.append(" );");
		 */

		Statement st;
		try {
			st = ConnectionUtil.getInstance().getConnection().createStatement();
			st.executeUpdate(query.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
