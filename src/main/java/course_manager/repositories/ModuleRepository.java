package course_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import course_manager.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer>{

}
