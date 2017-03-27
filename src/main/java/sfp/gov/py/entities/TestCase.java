package sfp.gov.py.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the test_case database table.
 * 
 */
@Entity
@Table(name="test_case")
@NamedQuery(name="TestCase.findAll", query="SELECT t FROM TestCase t")
public class TestCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	private String name;

	//bi-directional many-to-one association to TestAction
	@OneToMany(mappedBy="testCaseBean")
	private List<TestAction> testActions;

	public TestCase() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestAction> getTestActions() {
		return this.testActions;
	}

	public void setTestActions(List<TestAction> testActions) {
		this.testActions = testActions;
	}

	public TestAction addTestAction(TestAction testAction) {
		getTestActions().add(testAction);
		testAction.setTestCaseBean(this);

		return testAction;
	}

	public TestAction removeTestAction(TestAction testAction) {
		getTestActions().remove(testAction);
		testAction.setTestCaseBean(null);

		return testAction;
	}

}