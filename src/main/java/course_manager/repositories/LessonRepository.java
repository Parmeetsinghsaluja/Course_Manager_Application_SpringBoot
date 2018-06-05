package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import course_manager.models.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

}
