package sfp.gov.py.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonUtil {

	private static CommonUtil commonUtil;

	private CommonUtil() {

	}

	public static CommonUtil getInstance() {
		if (commonUtil == null) {
			commonUtil = new CommonUtil();
		}
		return commonUtil;
	}

	/**
	 * Metodo que parse una consulta Sql, reemplazando los placeholder por sus
	 * valores correspondientes
	 * 
	 * @param query,
	 *            consulta Sql
	 * @param params,
	 *            Map de valores
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String stringFormMap(String query, Map params) {
		Iterator keys = params.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			query = query.replace(":" + key.toString(), params.get(key).toString());
		}
		return query;
	}

	/**
	 * Metodo que convierte los valores de un ResultSet a un Map
	 * 
	 * @param resultBySearch
	 * @param rowValues
	 * @param columnValues
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
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

	/**
	 * Metodo que obtiene los valores por tipo desde un ResultSet
	 * 
	 * @param resultBySearch
	 * @param columnIndex
	 * @param sqlType
	 * @param columnValue
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
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

	/**
	 * Metodo que obtiene valores de una entidad especifica
	 * 
	 * @param className,
	 *            nombre de la clase a obtener desde la base de datos
	 * @param fullClassName,
	 *            Nombre completo de clase incluyendo el paquete de la misma
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, String> getElementFromDatabaseByClassName(String className, String fullClassName) {

		ArrayList listValues = getDataFromDatabase(className);

		return getSingleDataFromList(fullClassName, listValues);
	}

	/**
	 * Metodo que obtiene todos los valores de una entidad especifica
	 * 
	 * @param className,
	 *            nombre de la clase a obtener desde la base de datos
	 * @param fullClassName,
	 *            Nombre completo de clase incluyendo el paquete de la misma
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getAllElementFromDatabaseByClassName(String className, String fullClassName) {

		ArrayList listValues = getDataFromDatabase(className);

		return getAllDataFromListMap(fullClassName, listValues);
	}

	/**
	 * Metodo que obtiene valores de una entidad desde la bases de datos
	 * 
	 * @param className,
	 *            nombre de la clase a obtener desde la base de datos
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getDataFromDatabase(String className) {
		Operation operation = new Operation(ConnectionUtil.getInstance().getConnection());
		Map<String, String> params = new HashMap<String, String>();
		params.put("className", className);
		ArrayList<Map> listValues = operation.getArrayDataFromParams("", params);
		return listValues;
	}

	/**
	 * Metodo que formatea una lista de registros a Map con los atributos
	 * especificos de una entidad
	 * 
	 * @param className,
	 *            nombre de la clase a obtener desde la base de datos
	 * @param listValues,
	 *            listado de registros
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, String> getSingleDataFromList(String className, ArrayList<Map> listValues) {

		Map<String, String> properties = getClassProperties(className);
		for (Object value : listValues) {
			Map mapValue = (Map) value;
			if (properties.containsKey(mapValue.get(Table.ELEMENT_NAME.getDescripcion()))) {
				properties.replace(mapValue.get(Table.ELEMENT_NAME.getDescripcion()).toString(),
						mapValue.get(Table.ELEMENT_INPUT.getDescripcion()).toString());
			}
		}

		return properties;
	}

	/**
	 * Metodo que formatea una lista de registros agrupados por la columna
	 * Table.REFERENCE de la base de datos. Itera por cada grupo y genera un Map
	 * con los atributos especificos de una entidad
	 * 
	 * @param fullClassName,
	 *            Nombre completo de clase incluyendo el paquete de la misma
	 * @param listValues,
	 *            listado de registros
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getAllDataFromListMap(String fullClassName, ArrayList<Map> listValues) {

		Map properties = getClassProperties(fullClassName);
		ArrayList listaDataFromListMap = new ArrayList<>();
		Map<Object, List<Map>> groupByReference = listValues.stream()
				.collect(Collectors.groupingBy(element -> element.get(Table.REFERENCE.getDescripcion())));

		Set<Object> keys = groupByReference.keySet();
		keys.forEach(key -> {
			List<Map> listGroup = groupByReference.get(key);
			Map entityValue = new HashMap<>();
			listGroup.forEach(mapValue -> {
				
				if (properties.containsKey(mapValue.get(Table.ELEMENT_NAME.getDescripcion()))) {
					entityValue.put(mapValue.get(Table.ELEMENT_NAME.getDescripcion()),
							mapValue.get(Table.ELEMENT_INPUT.getDescripcion()).toString());
					if ((mapValue.get(Table.ELEMENT_OUPUT.getDescripcion()) != null
							|| mapValue.get(Table.ELEMENT_OUPUT.getDescripcion()) != "")
							&& mapValue.get(Table.ELEMENT_OUPUT.getDescripcion()).toString().length() > 0) {
						entityValue.put("valid", mapValue.get(Table.ELEMENT_OUPUT.getDescripcion()).toString());
					}
				}
				
			});
			listaDataFromListMap.add(entityValue);
		});

		return listaDataFromListMap;
	}

	/**
	 * Obtiene lista de atributos definidos en la Clase que recibe como
	 * parametro
	 * 
	 * @param className,
	 *            Nombre completo de clase incluyendo el paquete de la misma
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, String> getClassProperties(String className) {
		Map<String, String> properties = new HashMap<>();
		try {
			Class clazz = Class.forName(className);
			Field[] listProperties = clazz.getDeclaredFields();
			for (Field field : listProperties) {
				properties.put(field.getName(), "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

}
