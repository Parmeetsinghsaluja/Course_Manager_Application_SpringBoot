package course_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import course_manager.models.Assignment;
import course_manager.models.Lesson;
import course_manager.models.Widget;
import course_manager.repositories.AssignmentRepository;
import course_manager.repositories.LessonRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Optional<Assignment> findAllAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		return assignmentRepository.findById(assignmentId); 
	}

	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignments() {
		return assignmentRepository.findAll(); 
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public Iterable<Assignment> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			List<Widget> widgets = lesson.getWidgets();
			List<Assignment> assignments = new ArrayList<Assignment>(); 
			for (Widget widget: widgets) {
				if (widget.getWidgetType() == "assignment")
				{
					assignments.add((Assignment) widget);
				}
			}
			return assignments;
		}
		return null;	
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
}
