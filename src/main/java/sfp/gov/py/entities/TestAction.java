package sfp.gov.py.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sfp.gov.py.core.Enums.TestActionGetElementBy;
import sfp.gov.py.core.Enums.TestActionType;


/**
 * The persistent class for the test_action database table.
 * 
 */
@Entity
@Table(name="test_action")
@NamedQuery(name="TestAction.findAll", query="SELECT t FROM TestAction t")
public class TestAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="enum_type")
	@Enumerated(EnumType.STRING)
	private TestActionType enumType;
	
	@Column(name="enum_get_element_by")
	@Enumerated(EnumType.STRING)
	private TestActionGetElementBy enumGetElementBy;
	
	private String expression;

	private Integer order;
	
	@Column(name="element_value")
	private String elementValue;
	
	//bi-directional many-to-one association to TestAction
	@ManyToOne
	@JoinColumn(name="test_action_assert_1")
	private TestAction testAction1;

	//bi-directional many-to-one association to TestAction
	@OneToMany(mappedBy="testAction1")
	private List<TestAction> testActions1;

	//bi-directional many-to-one association to TestAction
	@ManyToOne
	@JoinColumn(name="test_action_assert_2")
	private TestAction testAction2;

	//bi-directional many-to-one association to TestAction
	@OneToMany(mappedBy="testAction2")
	private List<TestAction> testActions2;

	//bi-directional many-to-one association to TestCase
	@ManyToOne
	@JoinColumn(name="test_case")
	private TestCase testCaseBean;

	public TestAction() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TestActionType getEnumType() {
		return this.enumType;
	}

	public void setEnumType(TestActionType enumType) {
		this.enumType = enumType;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public TestCase getTestCaseBean() {
		return this.testCaseBean;
	}

	public void setTestCaseBean(TestCase testCaseBean) {
		this.testCaseBean = testCaseBean;
	}
	
	public String getElementValue() {
		return elementValue;
	}
	
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	public TestActionGetElementBy getEnumGetElementBy() {
		return enumGetElementBy;
	}
	
	public void setEnumGetElementBy(TestActionGetElementBy enumGetElementBy) {
		this.enumGetElementBy = enumGetElementBy;
	}
	
	public TestAction getTestAction1() {
		return this.testAction1;
	}

	public void setTestAction1(TestAction testAction1) {
		this.testAction1 = testAction1;
	}

	public List<TestAction> getTestActions1() {
		return this.testActions1;
	}

	public void setTestActions1(List<TestAction> testActions1) {
		this.testActions1 = testActions1;
	}

	public TestAction addTestActions1(TestAction testActions1) {
		getTestActions1().add(testActions1);
		testActions1.setTestAction1(this);

		return testActions1;
	}

	public TestAction removeTestActions1(TestAction testActions1) {
		getTestActions1().remove(testActions1);
		testActions1.setTestAction1(null);

		return testActions1;
	}

	public TestAction getTestAction2() {
		return this.testAction2;
	}

	public void setTestAction2(TestAction testAction2) {
		this.testAction2 = testAction2;
	}

	public List<TestAction> getTestActions2() {
		return this.testActions2;
	}

	public void setTestActions2(List<TestAction> testActions2) {
		this.testActions2 = testActions2;
	}

	public TestAction addTestActions2(TestAction testActions2) {
		getTestActions2().add(testActions2);
		testActions2.setTestAction2(this);

		return testActions2;
	}

	public TestAction removeTestActions2(TestAction testActions2) {
		getTestActions2().remove(testActions2);
		testActions2.setTestAction2(null);

		return testActions2;
	}
}