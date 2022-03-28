package com.epam.services;


import com.epam.dao.QuizRepository;
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
    QuestionService questionService;

    @Autowired
    ModelMapper modelMapper;


    public QuizDto getAQuiz(int quizId) {
        return modelMapper
                .map(quizRepository
                                .findById(quizId)
                                .orElseThrow(() -> new InvalidIDException("Invalid Quiz ID!!"))
                        , QuizDto.class);
    }

    public QuizDto insertQuiz(QuizDto quizDto, List<Integer> questionIds)  {
        if (quizDto.getQuizId() != 0) {
            String title = quizDto.getTitle();
            quizDto = getAQuiz(quizDto.getQuizId());
            quizDto.setTitle(title);
        }
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        Set<Question> questions = new HashSet<>();
        for (int questionId : questionIds) {
            questions.add(modelMapper.map(questionService.getQuestionByID(questionId), Question.class));
        }
        if (quiz.getQuestions() == null) {
            quiz.setQuestions(questions);
        } else {
            quiz.getQuestions().addAll(questions);
        }
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    public void deleteQuiz(int quizId) {
        quizRepository.deleteById(quizId);
    }

    public List<QuizDto> getAllQuizzes() {
        List<Quiz> quizzes = (List<Quiz>) quizRepository.findAll();
        if (quizzes.isEmpty()) {
            throw new EmptyLibraryException("Quiz Library is empty!!");
        }
        return modelMapper.map(quizzes, new TypeToken<List<QuizDto>>() {
        }.getType());
    }


    public void deleteQuestionInQuiz(int quizId, int questionId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            Optional<Question> questionOptional = quiz
                    .getQuestions()
                    .stream()
                    .filter(question -> question.getQuestionId() == questionId)
                    .findFirst();
            if (questionOptional.isPresent()) {
                quiz.getQuestions().remove(questionOptional.get());
                quizRepository.save(quiz);
            } else {
                throw new InvalidIDException("Invalid Question ID");
            }
        } else {
            throw new InvalidIDException("Invalid Quiz ID");
        }
    }
}
