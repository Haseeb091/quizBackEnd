package Project.FilmBackend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BasicFilmQuestion {



    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if(getQuestionType()==""){
            setQuestion("");

        }else if(getQuestionType()=="year"){

            this.question="what year was the movie "+question+" created in";
        }else if(getQuestionType()=="language"){
            this.question="what language was the movie "+question+" created in";

        }
    }

    private String question;

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    private String questionType="";

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    private String correctAnswer;



    private ArrayList<String> possibleAnswers;

    public BasicFilmQuestion(String questionData,String questionType,ArrayList<String>incorrectAnswers,String correctAnswer ,int seed){
       // this.film=film;
        setQuestionType(questionType);
        setQuestion(questionData);
        this.correctAnswer=correctAnswer;
        this.possibleAnswers=incorrectAnswers;

        this.possibleAnswers.add(correctAnswer);
        Collections.shuffle(this.possibleAnswers, new Random(seed));



    }
}
