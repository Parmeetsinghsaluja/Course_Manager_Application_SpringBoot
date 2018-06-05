package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import course_manager.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
