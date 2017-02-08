package sfp.gov.py.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class Operation {

	private Connection connection;

	public Operation(Connection cn) {
		this.connection = cn;
	}

	public Integer insertOrUpdate(String query, List<Map> listParams) {
		StringBuilder queryBuilder = new StringBuilder();
		Statement sqlStat;
		Integer state = 0;

		for (Object params : listParams.toArray()) {
			queryBuilder.append(CommonUtil.stringFormMap(query, (Map) params));
		}

		try {
			sqlStat = this.connection.createStatement();
			state = sqlStat.executeUpdate(queryBuilder.toString());
		} catch (SQLException e) {
			System.out.println("Operation -> insertOrUpdate");
			e.printStackTrace();
		}

		return state;

	}

	@SuppressWarnings("rawtypes")
	public ArrayList<Map> getArrayDataFromParams(String query, Map params) {
		String defaultQuery = "SELECT * FROM TEST_DATA WHERE class_name = ':className' and element_name is not null";
		StringBuilder queryBuilder = new StringBuilder();
		Statement sqlStat;
		ResultSet resultBySearch;
		ArrayList resultToReturn = new ArrayList<>();
		Map<String, String> rowValues = new HashMap<>();
		Map<String, String> columValues = new HashMap<>();
		
		query = query.equals("") ? defaultQuery : query;
		queryBuilder.append(CommonUtil.stringFormMap(query, params));
		try {
			sqlStat = connection.createStatement();
			resultBySearch = sqlStat.executeQuery(queryBuilder.toString());
			while (resultBySearch.next()) {
				rowValues = CommonUtil.convertResultSetToMap(resultBySearch, rowValues, columValues);
				Map rowDataCopy = new HashMap<>();
				rowDataCopy.putAll(rowValues);
				resultToReturn.add(rowDataCopy);
			}

		} catch (SQLException e) {
			System.out.println("Operation -> getArrayDataFromParams");
			e.printStackTrace();
		}
		return resultToReturn;
	}

}
