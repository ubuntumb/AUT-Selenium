package sfp.gov.py.util;
/**
 * 
 * @author mbenitez Copyright [2017] [Marcos Benitez]
 * Licensed under the Apache 
 * Open Source License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 */
public enum Table {
	ID("ID"),
	CLASS_NAME("class_name"),
	ELEMENT_NAME("element_name"),
	ELEMENT_INPUT("element_input"),
	REFERENCE("reference_test"),
	ELEMENT_OUPUT("element_output");
	
	private String descipcion;
	private Table(String descripcion){
		this.descipcion = descripcion;
	}
	
	public String getDescripcion(){
		return this.descipcion;
	}
	
}
