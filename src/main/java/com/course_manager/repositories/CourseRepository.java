package com.course_manager.repositories;
import org.springframework.data.repository.CrudRepository;

import com.course_manager.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}