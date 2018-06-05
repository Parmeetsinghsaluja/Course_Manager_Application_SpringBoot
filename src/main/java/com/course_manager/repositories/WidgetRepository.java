package com.course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.course_manager.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	
}
