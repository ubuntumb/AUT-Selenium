package sfp.gov.py.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sfp.gov.py.core.Enums.TestActionGetElementBy;
import sfp.gov.py.core.Enums.TestActionType;
import sfp.gov.py.entities.TestAction;

public class SeleniumIDE_HTML_Loader {
	
	private static List<TestAction> configureAction(String command, String target, String value) {
		List<TestAction> actions = new ArrayList<>();
		TestAction action = new TestAction();
		if (command.equals("click") || command.equals("clickAndWait"))
			action.setEnumType(TestActionType.click_event);
		else if (command.equals("type")) {
			action.setEnumType(TestActionType.element_value_assignation);
			action.setExpression(value);
		}
		else {
			action.setEnumType(TestActionType.not_defined);
		}
		
		if (target.startsWith("id=")) {
			action.setEnumGetElementBy(TestActionGetElementBy.id);
			action.setExpression(target.substring(3));
		}
		else if (target.startsWith("css=")) {
			action.setEnumGetElementBy(TestActionGetElementBy.cssSelector);
			action.setExpression(target.substring(4));
		}
			
		if (!value.isEmpty())
			action.setElementValue(value);
		actions.add(action);
		System.out.println("Command : " + command);
		System.out.println("Target : " + target);
		System.out.println("Value : " + value);
		return actions;
	} 
	
	public static List<TestAction> importFromHTML(String htmlPath) throws Exception {
		List<TestAction> actions = new ArrayList<>();
		String copyPath = System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID().toString().replace("-", "");
		File copy = new File(copyPath);
		FileWriter filewriter = new FileWriter(copy, true); 
		BufferedWriter bufferW = new BufferedWriter(filewriter); 
		
		String line = ""; 
		BufferedReader buffer = new BufferedReader(new FileReader(new File(htmlPath))); 
		while ((line = buffer.readLine()) != null) { 
			if (!line.startsWith("<!DOCTYPE html")) {
				bufferW.write(line); 
				bufferW.newLine(); 
			}
		}
		bufferW.close(); 
		buffer.close(); 
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(copy);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("tr");
		for (int i = 1; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				NodeList tdList = eElement.getElementsByTagName("td");
				String command = tdList.item(0).getTextContent();
				String target = tdList.item(1).getTextContent();
				String value = tdList.item(2).getTextContent();
				actions.addAll(configureAction(command, target, value));
			}
		}
		copy.delete();
		return actions;
	}
	
	public static void main(String[] args) throws Exception {
		TestBase2.actions =  importFromHTML("C:/Users/user/Desktop/Nueva carpeta (2)/prueba.html");
		System.out.println("-------------ACCIONES----------------");
		for (TestAction testAction : TestBase2.actions) {
			System.out.println("Expresion: " + testAction.getExpression());
			System.out.println("Element: " + testAction.getEnumGetElementBy());
			System.out.println("Tipo: " + testAction.getEnumType());
			System.out.println("Valor: " + testAction.getElementValue());
			System.out.println("------------");
		}
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { TestBase2.class });
		testng.addListener(tla);
		testng.run();
	}
}
