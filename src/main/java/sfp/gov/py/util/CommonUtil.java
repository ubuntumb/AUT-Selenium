package sfp.gov.py.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public class CommonUtil {

	private CommonUtil() {
	}

	public static String stringFormMap(String query, Map params) {
		Iterator keys = params.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			query = query.replace(":" + key.toString(), params.get(key).toString());
		}
		return query;
	}

	public static Map<String, String> convertResultSetToMap(ResultSet resultBySearch, Map rowValues, Map columnValues)
			throws SQLException {

		Map<String, String> columnValue = new HashMap<>();
		ResultSetMetaData resultMetadata = resultBySearch.getMetaData();
		rowValues.clear();

		for (int i = 1; i <= resultMetadata.getColumnCount(); i++) {
			columnValue = getValueFromResultSet(resultBySearch, i, resultMetadata.getColumnType(i), columnValue);
			rowValues.put(resultMetadata.getColumnName(i), columnValue.get("value"));
		}

		return rowValues;
	}

	public static Map getValueFromResultSet(ResultSet resultBySearch, int columnIndex, int sqlType, Map columnValue)
			throws SQLException {

		Map valueToReturn = new HashMap<>();
		columnValue.clear();

		switch (sqlType) {
		case Types.INTEGER:
			valueToReturn.put("value", resultBySearch.getInt(columnIndex));
			break;
		case Types.BIGINT:
			valueToReturn.put("value", resultBySearch.getInt(columnIndex));
			break;
		case Types.FLOAT:
			valueToReturn.put("value", resultBySearch.getFloat(columnIndex));
			break;
		case Types.DECIMAL:
			valueToReturn.put("value", resultBySearch.getBigDecimal(columnIndex));
			break;
		case Types.NUMERIC:
			valueToReturn.put("value", resultBySearch.getBigDecimal(columnIndex));
			break;
		case Types.DOUBLE:
			valueToReturn.put("value", resultBySearch.getDouble(columnIndex));
			break;
		case Types.CHAR:
			valueToReturn.put("value", resultBySearch.getCharacterStream(columnIndex));
			break;
		case Types.VARCHAR:
			valueToReturn.put("value", resultBySearch.getString(columnIndex));
			break;
		case Types.BINARY:
			valueToReturn.put("value", resultBySearch.getBinaryStream(columnIndex));
			break;
		case Types.BOOLEAN:
			valueToReturn.put("value", resultBySearch.getBoolean(columnIndex));
			break;
		default:
			valueToReturn.put("value", resultBySearch.getObject(columnIndex));
			break;
		}
		return valueToReturn;
	}
}
