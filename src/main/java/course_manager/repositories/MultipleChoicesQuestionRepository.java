package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import course_manager.models.MultipleChoiceQuestion;

public interface MultipleChoicesQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer>{

}
