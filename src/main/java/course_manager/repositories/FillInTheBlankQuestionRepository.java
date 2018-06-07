package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import course_manager.models.FillInTheBlankQuestion;


public interface FillInTheBlankQuestionRepository
	extends CrudRepository<FillInTheBlankQuestion, Integer> {
	
}