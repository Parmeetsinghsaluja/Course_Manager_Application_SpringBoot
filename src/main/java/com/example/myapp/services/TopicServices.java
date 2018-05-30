package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.myapp.models.Topic;
import com.example.myapp.models.Lesson;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicServices {
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Topic createTopic(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Topic newTopic) {
			Optional<Lesson> Data = lessonRepository.findById(lessonId);
			if(Data.isPresent()) {
				Lesson lesson = Data.get();
				newTopic.setLesson(lesson);
				return topicRepository.save(newTopic);
		}
		return null;		
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public List<Topic> findAllTopicsForLesson(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();
		}
		return null;		
	}
	
	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") int topicId)
	{   
		topicRepository.deleteById(topicId);
	}
	
	@GetMapping("/api/topic")
	public List<Topic> findAllTopics()
	{
		return (List<Topic>) topicRepository.findAll();
	}
}