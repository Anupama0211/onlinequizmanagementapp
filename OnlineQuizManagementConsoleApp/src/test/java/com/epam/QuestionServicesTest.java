package com.epam;

import com.epam.entities.Question;
import com.epam.services.questionservices.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServicesTest {

    Question question;
    @BeforeEach
     void setup(){
       question = new QuestionBuilder()
                .setTitle("Capital Of India")
                .setOptions(new String[]{"Kolkata", "Delhi", "Hyderabad", "Bhopal"})
                .setAnswer("Delhi")
                .setTopic("GK")
                .setDifficulty("Easy")
                .setMarks(2)
                .build();
    }

    @Test
    void modifyQuestionTest() {
        assertTrue( new ModifyQuestion().perform(question, 1));
        assertFalse( new ModifyQuestion().perform(null, 1));

    }

    @Test
    void addQuestionTest() {
        assertTrue(new AddQuestion().perform(question));
        assertFalse(new AddQuestion().perform(null));
        new RemoveQuestions().perform(1);
    }

    @Test
     void viewQuestionTest() {
       assertEquals(new QuestionListSize().get(),new ViewQuestions().perform().size());
    }

    @Test
     void removeQuestionTest() {
        assertFalse(new RemoveQuestions().perform(0));
        assertTrue(new RemoveQuestions().perform(new QuestionListSize().get()));
        assertTrue(new RemoveQuestions().perform(1));
    }
}
