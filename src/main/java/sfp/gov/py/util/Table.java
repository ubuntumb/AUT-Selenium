package sfp.gov.py.util;

public enum Table {
	ID("ID"),
	CLASS_NAME("CLASS_NAME"),
	ELEMENT_NAME("ELEMENT_NAME"),
	ELEMENT_INPUT("ELEMENT_INPUT"),
	REFERENCE("REFERENCE"),
	ELEMENT_OUPUT("ELEMENT_OUPUT");
	
	private String descipcion;
	private Table(String descripcion){
		this.descipcion = descripcion;
	}
	
	public String getDescripcion(){
		return this.descipcion;
	}
	
}
