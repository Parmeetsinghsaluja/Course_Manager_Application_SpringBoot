package course_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import course_manager.models.Assignment;
import course_manager.models.Exam;
import course_manager.models.Lesson;
import course_manager.models.Widget;
import course_manager.repositories.AssignmentRepository;
import course_manager.repositories.ExamRepository;
import course_manager.repositories.LessonRepository;
import course_manager.repositories.WidgetRepository;


@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository repository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			return lesson.getWidgets();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public void saveExamsForLesson(@RequestBody Exam exam, @PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			exam.setLesson(lesson);
			examRepository.save(exam);
		}
	}
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public void saveAssignmentsForLesson(@RequestBody Assignment assignment, @PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			assignment.setLesson(lesson);
			assignmentRepository.save(assignment);
		}
	}
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widgets) {
		repository.deleteAll();
		for(Widget widget: widgets) {
			repository.save(widget);
		}
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repository.findAll();
	}
}