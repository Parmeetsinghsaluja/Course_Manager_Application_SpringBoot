package com.course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.course_manager.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

}
