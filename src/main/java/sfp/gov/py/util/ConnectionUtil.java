package sfp.gov.py.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class ConnectionUtil {

	private String ruta;
	private static ConnectionUtil util;
	private Connection connection;

	private ConnectionUtil() {
		ruta = System.getProperty("user.dir") + File.separator + "test.db";
		loadConnection();
	}

	private void loadConnection() {
		connection = null;

		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:" + ruta;
			connection = DriverManager.getConnection(url, "sa", "");
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
		query.append("CREATE TABLE test_data ( ");
		query.append("id IDENTITY PRIMARY KEY, ");
		query.append("class_name VARCHAR(255),  ");
		query.append("element_name VARCHAR(255), ");
		query.append("element_input VARCHAR(512), ");
		query.append("element_ouput VARCHAR(512), ");
		query.append(" );");
		Statement st;
		try {
			st = ConnectionUtil.getInstance().getConnection().createStatement();
			st.executeUpdate(query.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
