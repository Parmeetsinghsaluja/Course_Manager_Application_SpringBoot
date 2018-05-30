package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.myapp.models.Topic;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins= "*", maxAge = 3600)
public class WidgetServices {
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/widget/save{topicId}")
	public void saveAllWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			for(Widget widget: topic.getWidgets()) {
				widgetRepository.delete(widget);
			}
			for(Widget widget: widgets) {
				widget.setTopic(topic);
				widgetRepository.save(widget);
			}
		}
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets(){
		return (List<Widget>) widgetRepository.findAll();
		
	}
	
	@GetMapping("/api/widget{topicId}")
	public List<Widget> findAllWidgetsForTopic(@PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			return topic.getWidgets();
		}
		else {
			return null;	
		}
	}
	
	@PostMapping("/api/widget")
	public Widget createCourse(@RequestBody Widget widget) {
		return widgetRepository.save(widget);
	}
	
	@PutMapping("/api/widget/{widgetId}")
	@ResponseBody
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			widget.setHeadingValue(newWidget.getHeadingValue());
			widget.setLink(newWidget.getLink());
			widget.setListType(newWidget.getListType());
			widget.setText(newWidget.getText());
			widget.setTopic(newWidget.getTopic());
			widget.setWidgetOrder(newWidget.getWidgetOrder());
			widget.setWidgetType(newWidget.getWidgetType());
			return widget;
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		widgetRepository.deleteById(id);
	}
	
}
