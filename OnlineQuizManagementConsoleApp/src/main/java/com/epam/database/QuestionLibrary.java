package com.epam.database;

import com.epam.entities.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionLibrary {
    private static List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("The malicious software program, which is detrimental to other computer programs is known as",
                    "Easy",
                    "Basics",
                    new String[]{"a.Virus", "b.Worms", "c.Trojans", "d.Spyware"},
                    "Virus", 1),

            new Question("Who was the father of computer?",
                    "Easy",
                    "Basics",
                    new String[]{"a.Charlie Babbage", "b.Dennis Ritchie", "c.Charles Babbage", "d.Ken Thompson"},
                    "Charles Babbage", 1),

            new Question("Who invented Java Programming?",
                    "Easy",
                    "Basics",
                    new String[]{"a.Guido van Rossum", "b.James Gosling", "c.Dennis Ritchie", "d.Bjarne Stroustrup"},
                    "James Gosling", 1),

            new Question("Which statement is true about Java?",
                    "Easy",
                    "Basics",
                    new String[]{
                            "a.Java is a sequence-dependent programming language",
                            "b.Java is a code dependent programming language",
                            "c.Java is a platform-dependent programming language",
                            "d.Java is a platform-independent programming language"
                    },
                    "Java is a platform-independent programming language", 1),

            new Question(" Which component is used to compile, debug and execute the java programs?",
                    "Easy",
                    "Basics",
                    new String[]{"a.JRE", "b.JIT", "c.JDK", "d.JVM"},
                    "JDK", 1),

            new Question(" Which one of the following is not a Java feature?",
                    "Easy",
                    "Basics",
                    new String[]{"a.Object-oriented", "b.Use of pointers", "c.Portable", "d.Dynamic and Extensible"},
                    "Use of Pointers", 1),

            new Question("Which of these cannot be used for a variable name in Java?",
                    "Easy",
                    "Basics",
                    new String[]{"a.identifier & keyword", "b.identifier", "c.keyword", "d.none of the mentioned"},
                    "keyword", 1),
            new Question("What is the extension of java code files?",
                    "Easy",
                    "Basics",
                    new String[]{"a..js", "b..txt", "c..class", "d..java"},
                    ".java", 1)));

    public static void addQuestion(Question question) {
        questions.add(question);
    }

    public static Question removeQuestion(int indexToBeRemoved) {
        Question question=questions.get(indexToBeRemoved);
        questions.remove(indexToBeRemoved);
        return question;
    }

    public static void modifyTitle(int indexToBeModified, String title) {
        questions.get(indexToBeModified).setTitle(title);
    }

    public static void modifyDifficulty(int indexToBeModified, String difficulty) {
        questions.get(indexToBeModified).setDifficulty(difficulty);
    }

    public static void modifyTopic(int indexToBeModified, String topic) {
        questions.get(indexToBeModified).setTopic(topic);
    }
    public static int size(){
        return questions.size();
    }

    public static void modifyOptions(int indexToBeModified, String[] options) {
        questions.get(indexToBeModified).setOptions(options);
    }

    public static void modifyAnswer(int indexToBeModified, String answer) {
        questions.get(indexToBeModified).setAnswer(answer);
    }

    public static void modifyMarks(int indexToBeModified, int marks) {
        questions.get(indexToBeModified).setMarks(marks);
    }

    public static void modifyQuestion(Question question, int indexTobeModified) {
        questions.add(indexTobeModified, question);
    }

    public static List<Question> getQuestions() {
        return questions;
    }


    public static void printQuestionsInLibrary() {
        int index = 1;
        for (Question question : questions) {
            System.out.println(index + " " + question);
            index++;
        }
    }
}
