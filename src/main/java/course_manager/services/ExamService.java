package course_manager.services;

import java.util.ArrayList;
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

import course_manager.models.EssayQuestion;
import course_manager.models.Exam;
import course_manager.models.FillInTheBlankQuestion;
import course_manager.models.Lesson;
import course_manager.models.MultipleChoiceQuestion;
import course_manager.models.Question;
import course_manager.models.TrueFalseQuestion;
import course_manager.models.Widget;
import course_manager.repositories.EssayQuestionRepository;
import course_manager.repositories.ExamRepository;
import course_manager.repositories.FillInTheBlankQuestionRepository;
import course_manager.repositories.LessonRepository;
import course_manager.repositories.MultipleChoicesQuestionRepository;
import course_manager.repositories.TrueFalseQuestionRepository;


@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	EssayQuestionRepository essayRepository;
	@Autowired
	FillInTheBlankQuestionRepository fbRepository;
	@Autowired
	MultipleChoicesQuestionRepository multiRepo;

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/truefalse")
	public void saveTrueFalseForExam(@RequestBody TrueFalseQuestion tf,@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tf.setExam(exam);
			trueFalseRepository.save(tf);
		}
	}
	
	@PostMapping("/api/exam/{examId}/essay")
	public void saveEssayForExam(@RequestBody EssayQuestion essayQuestion, @PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			essayQuestion.setExam(exam);
			essayRepository.save(essayQuestion);
		}
	}
	
	@PostMapping("/api/exam/{examId}/blanks")
	public void saveFillInTheBlankForExam(@RequestBody
			FillInTheBlankQuestion fbQuestion,
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			fbQuestion.setExam(exam);
			fbRepository.save(fbQuestion);
		}
	}
	
	@PostMapping("/api/exam/{examId}/choice")
	public void saveMultiChoiceForExam(@RequestBody
			MultipleChoiceQuestion multiQuestion,
			@PathVariable("examId") int examId) {
		System.out.println(examId);
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			multiQuestion.setExam(exam);
			multiRepo.save(multiQuestion);
		}
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public Iterable<Exam> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			List<Exam> examList = new ArrayList<Exam>(); 
			List<Widget> widgetList = lesson.getWidgets();
			for (Widget widget: widgetList) {
				if (widget.getWidgetType() == "Exam")
				{
					examList.add((Exam) widget);
				}
			}
			return examList;
		}
		return null;	
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
}