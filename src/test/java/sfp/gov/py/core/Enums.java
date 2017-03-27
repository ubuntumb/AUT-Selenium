package sfp.gov.py.core;

public class Enums {

	public enum Driver { FFDRIVER, CHDRIVER};
	
	public enum TestActionType {execute_javascript, element_value_assignation, click_event, clear_input, get_page,
		check_if_element_exists, sleep}
	
	public enum TestActionGetElementBy {id, cssSelector}
}
