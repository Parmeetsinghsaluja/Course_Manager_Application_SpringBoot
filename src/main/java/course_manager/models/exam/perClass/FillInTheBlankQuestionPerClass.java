package course_manager.models.exam.perClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PER_CLASS_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlankQuestionPerClass
	extends BaseQuestionPerClass {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
}
