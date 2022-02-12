package com.epam.database;

import com.epam.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsDatabase {
    private static List<Question> questions = new ArrayList<>();

    static {
        questions.add(new Question("The malicious software program, which is detrimental to other computer programs is known as",
                "Easy",
                "Basics",
                new String[]{"Virus", "Worms", "Trojans", "Spyware"},
                "Virus", 1));

        questions.add(new Question("Who was the father of computer?",
                "Easy",
                "Basics",
                new String[]{"Charlie Babbage", "Dennis Ritchie", "Charles Babbage", "Ken Thompson"},
                "Charles Babbage", 1));

        questions.add(new Question("Who invented Java Programming?",
                "Easy",
                "Basics",
                new String[]{"Guido van Rossum", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup"},
                "James Gosling", 1));

        questions.add(new Question("Which statement is true about Java?",
                "Easy",
                "Basics",
                new String[]{
                        "Java is a sequence-dependent programming language",
                        "Java is a code dependent programming language",
                        "Java is a platform-dependent programming language",
                        "Java is a platform-independent programming language"
                },
                "Java is a platform-independent programming language", 1));

        questions.add(new Question(" Which component is used to compile, debug and execute the java programs?",
                "Easy",
                "Basics",
                new String[]{"JRE", "JIT", "JDK", "JVM"},
                "JDK", 1));

        questions.add(new Question(" Which one of the following is not a Java feature?",
                "Easy",
                "Basics",
                new String[]{"Object-oriented", "Use of pointers", "Portable", "Dynamic and Extensible"},
                "Use of Pointers", 1));

        questions.add(new Question("Which of these cannot be used for a variable name in Java?",
                "Easy",
                "Basics",
                new String[]{"identifier & keyword", "identifier", "keyword", "none of the mentioned"},
                "keyword", 1));
        questions.add(new Question("What is the extension of java code files?",
                "Easy",
                "Basics",
                new String[]{".js", ".txt", ".class", ".java"},
                ".java", 1));
    }

    private QuestionsDatabase() {

    }

    public static void addQuestion(Question question) {
        questions.add(question);
    }

    public static Question removeQuestion(int indexToBeRemoved) {
        Question question = questions.get(indexToBeRemoved);
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

    public static int size() {
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
        questions.set(indexTobeModified, question);
    }

    public static List<Question> getQuestions() {
        return questions;
    }

}
