package course_manager.models;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceQuestion extends Question {
	private String options;
	private int correctOption;
	private String description;
	
	public String getOptions() {
		return options;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
}
