package course_manager.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlankQuestion extends Question{
	
	private String variables;
	private String description;
	private String questionText;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}
	
}