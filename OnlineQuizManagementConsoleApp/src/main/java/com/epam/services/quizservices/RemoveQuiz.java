package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.userinterface.GetQuizIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveQuiz implements QuizLibraryService {
    private static final Logger LOGGER= LogManager.getLogger(RemoveQuiz.class);
    @Override
    public void perform() {
        if (QuizDatabase.size() > 0) {
            LOGGER.info(new QuizTitles().get());
            int quizIndex = GetQuizIndex.get();
            QuizDatabase.deleteQuiz(quizIndex-1);
            LOGGER.info("Quiz is deleted..");
        } else {
            LOGGER.warn("Quiz Library is empty");
        }
    }
}
