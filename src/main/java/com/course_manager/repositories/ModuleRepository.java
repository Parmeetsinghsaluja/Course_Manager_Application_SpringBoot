package com.course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.course_manager.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer>{

}