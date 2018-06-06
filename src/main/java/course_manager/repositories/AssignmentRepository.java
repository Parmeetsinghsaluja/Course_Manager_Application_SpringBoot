package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;
import course_manager.models.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

}
