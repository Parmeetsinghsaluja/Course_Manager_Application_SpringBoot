package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import course_manager.models.Exam;

public interface ExamRepository
extends CrudRepository<Exam, Integer>{

}
