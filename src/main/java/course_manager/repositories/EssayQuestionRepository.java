package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import course_manager.models.EssayQuestion;

public interface EssayQuestionRepository
	extends CrudRepository<EssayQuestion, Integer> {
	
}