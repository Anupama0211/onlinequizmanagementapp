//package com.epam.database;
//
//import com.epam.entities.Question;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class QuestionsDatabase {
//    private static List<Question> questions = new ArrayList<>();
//    static final String BASICS = "Basics";
//    static final String EASY = "Easy";
//
//
////    static {
////        questions.add(new Question(1,
////                "The malicious software program, which is detrimental to other computer programs is known as",
////                EASY,
////                BASICS,
////                List.of(new O"Virus", "Worms", "Trojans", "Spyware"),
////                "Virus", 1));
////
////        questions.add(new Question(2,
////                "Who was the father of computer?",
////                EASY,
////                BASICS,
////                new String[]{"Charlie Babbage", "Dennis Ritchie", "Charles Babbage", "Ken Thompson"},
////                "Charles Babbage", 1));
////
////        questions.add(new Question(3,
////                "Who invented Java Programming?",
////                EASY,
////                BASICS,
////                new String[]{"Guido van Rossum", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup"},
////                "James Gosling", 1));
////
////        questions.add(new Question(4,
////                "Which statement is true about Java?",
////                EASY,
////                BASICS,
////                new String[]{
////                        "Java is a sequence-dependent programming language",
////                        "Java is a code dependent programming language",
////                        "Java is a platform-dependent programming language",
////                        "Java is a platform-independent programming language"
////                },
////                "Java is a platform-independent programming language", 1));
////
////        questions.add(new Question(5,
////                " Which component is used to compile, debug and execute the java programs?",
////                EASY,
////                BASICS,
////                new String[]{"JRE", "JIT", "JDK", "JVM"},
////                "JDK", 1));
////
////        questions.add(new Question(6,
////                " Which one of the following is not a Java feature?",
////                EASY,
////                BASICS,
////                new String[]{"Object-oriented", "Use of pointers", "Portable", "Dynamic and Extensible"},
////                "Use of Pointers", 1));
////
////        questions.add(new Question(7,
////                "Which of these cannot be used for a variable name in Java?",
////                EASY,
////                BASICS,
////                new String[]{"identifier & keyword", "identifier", "keyword", "none of the mentioned"},
////                "keyword", 1));
////        questions.add(new Question(8,
////                "What is the extension of java code files?",
////                EASY,
////                BASICS,
////                new String[]{".js", ".txt", ".class", ".java"},
////                ".java", 1));
////    }
//
//
//    public void addQuestion(Question question) {
//        questions.add(question);
//    }
//
//    public Question removeQuestion(int indexToBeRemoved) {
//        Question question = questions.get(indexToBeRemoved);
//        questions.remove(indexToBeRemoved);
//        return question;
//    }
//
//
//    public int size() {
//        return questions.size();
//    }
//
//
//    public void modifyQuestion(Question question, int indexTobeModified) {
//        questions.set(indexTobeModified, question);
//    }
//
//    public List<Question> getQuestions() {
//        return questions;
//    }
//}