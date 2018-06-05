package course_manager.repositories;
import org.springframework.data.repository.CrudRepository;

import course_manager.models.TrueFalseQuestion;

public interface TrueFalseQuestionRepository extends CrudRepository<TrueFalseQuestion, Integer>{

}
