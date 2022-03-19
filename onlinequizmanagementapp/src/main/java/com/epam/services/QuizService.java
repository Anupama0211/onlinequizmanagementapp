package com.epam.services;


import com.epam.dao.QuizRepository;
import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class QuizService {


    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ModelMapper modelMapper;


    public QuizDto getAQuiz(int quizId) throws InvalidIDException {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isEmpty()) {
            throw new InvalidIDException("Invalid Question ID!!");
        }
        return modelMapper.map(quizOptional.get(), QuizDto.class);
    }

    public QuizDto insertQuiz(QuizDto quizDto, Set<QuestionDto> questionDtoSet) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        Set<Question> questions = new HashSet<>();
        questionDtoSet
                .forEach(questionDto -> questions
                        .add(modelMapper.map(questionDto, Question.class)));
        quiz.setQuestions(questions);
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    public void deleteQuiz(int quizId) {
        quizRepository.deleteById(quizId);
    }

    public List<QuizDto> getAllQuizzes() throws EmptyLibraryException {
        List<Quiz> quizzes = (List<Quiz>) quizRepository.findAll();
        if (quizzes.isEmpty()) {
            throw new EmptyLibraryException("Quiz Library is empty!!");
        }
        return modelMapper.map(quizzes, new TypeToken<List<QuizDto>>() {
        }.getType());
    }


    public void deleteQuestionInQuiz(int quizId, int questionId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        Quiz quiz;
        if (quizOptional.isPresent()) {
            quiz = quizOptional.get();
            Optional<Question> questionOptional = quiz
                    .getQuestions()
                    .stream()
                    .filter(question -> question.getQuestionId() == questionId)
                    .findFirst();
            questionOptional.ifPresent(question -> quiz.getQuestions().remove(question));
            quizRepository.save(quiz);
        }
    }

    public void addQuestionFromQuestionLibrary(QuizDto quizDto, QuestionDto questionDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        Question question = modelMapper.map(questionDto, Question.class);
        quiz.getQuestions().add(question);
        quizRepository.save(quiz);
    }
}
