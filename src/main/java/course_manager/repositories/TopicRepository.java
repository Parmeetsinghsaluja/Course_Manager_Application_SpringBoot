package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import course_manager.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

}
