package course_manager.models;

import javax.persistence.Entity;

@Entity
public class EssayQuestion extends Question{

	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
