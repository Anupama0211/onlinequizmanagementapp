package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.userinterface.GetQuizIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PrintAQuiz implements QuizLibraryService {
    private static final Logger LOGGER= LogManager.getLogger(PrintAQuiz.class);
    @Override
    public void perform() {
        if (QuizDatabase.size() > 0) {
            LOGGER.info(new QuizTitles().get());
            int quizIndex = GetQuizIndex.get();
            LOGGER.info(QuizDatabase.getQuiz(quizIndex - 1));
        } else {
            LOGGER.info("Quiz Library is Empty");
        }
    }
}