package sfp.gov.py.core;

public class Enums {

	public enum Driver { FFDRIVER, CHDRIVER};
	
	public enum TestActionType {not_defined, execute_javascript, element_value_assignation, click_event, clear_input, get_page,
		check_if_element_exists, sleep, wait_for_element_present, wait_for_page_to_load}
	
	public enum TestActionGetElementBy {id, cssSelector}
}
