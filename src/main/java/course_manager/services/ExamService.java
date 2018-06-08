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
import org.springframework.web.bind.annotation.PutMapping;
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
	FillInTheBlankQuestionRepository blanksRepository;
	@Autowired
	MultipleChoicesQuestionRepository multiRepository;

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepository.findById(questionId);
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
			blanksRepository.save(fbQuestion);
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
			multiRepository.save(multiQuestion);
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
	
	@PutMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion updateTrueFalseQuestion(@PathVariable("questionId") int questionId, 
										@RequestBody TrueFalseQuestion tfQuestion) {
		Optional<TrueFalseQuestion> data = trueFalseRepository.findById(questionId);
		if(data.isPresent()) {
			System.out.println("Inside + "+ questionId);
			TrueFalseQuestion newTFQuestion = data.get();
			newTFQuestion.setTitle(tfQuestion.getTitle());
			newTFQuestion.setDescription(tfQuestion.getDescription());
			newTFQuestion.setPoints(tfQuestion.getPoints());
			newTFQuestion.setTrue(tfQuestion.isTrue());
			return trueFalseRepository.save(newTFQuestion);
		}
		return null;
	}
	
	@PutMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion updateMultipleChoiceQuestion(@PathVariable("questionId") int questionId, 
										@RequestBody MultipleChoiceQuestion multiChoiceQuestion) {
		Optional<MultipleChoiceQuestion> data = multiRepository.findById(questionId);
		if(data.isPresent()) {
			MultipleChoiceQuestion newMultiChoiceQuestion = data.get();
			newMultiChoiceQuestion.setTitle(multiChoiceQuestion.getTitle());
			newMultiChoiceQuestion.setDescription(multiChoiceQuestion.getDescription());
			newMultiChoiceQuestion.setOptions(multiChoiceQuestion.getOptions());
			newMultiChoiceQuestion.setCorrectOption(multiChoiceQuestion.getCorrectOption());
			newMultiChoiceQuestion.setPoints(multiChoiceQuestion.getPoints());
			return multiRepository.save(newMultiChoiceQuestion);
		}
		return null;
	}
	
	@PutMapping("/api/blanks/{questionId}")
	public FillInTheBlankQuestion updateFillInTheBlankQuestion(@PathVariable("questionId") int questionId, 
										@RequestBody FillInTheBlankQuestion fillInTheBlankQuestion) {
		Optional<FillInTheBlankQuestion> data = blanksRepository.findById(questionId);
		if(data.isPresent()) {
			FillInTheBlankQuestion newFillInTheBlankQuestion = data.get();
			newFillInTheBlankQuestion.setTitle(fillInTheBlankQuestion.getTitle());
			newFillInTheBlankQuestion.setDescription(fillInTheBlankQuestion.getDescription());
			newFillInTheBlankQuestion.setPoints(fillInTheBlankQuestion.getPoints());
			newFillInTheBlankQuestion.setVariables(fillInTheBlankQuestion.getVariables());
			return blanksRepository.save(newFillInTheBlankQuestion);
		}
		return null;
	}
	
	@PutMapping("/api/essay/{questionId}")
	public EssayQuestion updateEssayQuestion(@PathVariable("questionId") int questionId, 
									@RequestBody FillInTheBlankQuestion essayQuestion) {
		Optional<EssayQuestion> data = essayRepository.findById(questionId);
		if(data.isPresent()) {
			EssayQuestion newEssayQuestion = data.get();
			newEssayQuestion.setTitle(essayQuestion.getTitle());
			newEssayQuestion.setDescription(essayQuestion.getDescription());
			newEssayQuestion.setPoints(essayQuestion.getPoints());
			return essayRepository.save(newEssayQuestion);
		}
		return null;
	}
	
	@DeleteMapping("/api/truefalse/{questionId}")
	public void deleteTrueFalseQuestion(@PathVariable("questionId") int id) {
		trueFalseRepository.deleteById(id);
	}
	@DeleteMapping("/api/essay/{questionId}")
	public void deleteEssayQuestion(@PathVariable("questionId") int id) {
		essayRepository.deleteById(id);
	}
	@DeleteMapping("/api/blanks/{questionId}")
	public void deleteFillInTheBlanksQuestion(@PathVariable("questionId") int id) {
		blanksRepository.deleteById(id);
	}
	@DeleteMapping("/api/multi/{questionId}")
	public void deleteMultipleChoiceQuestion(@PathVariable("questionId") int id) {
		multiRepository.deleteById(id);
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
}