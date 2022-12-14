package project.filmbackend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BasicFilmQuestion {



    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }


    public String getQuestion() {
        return question;
    }

    private void setQuestion(String question) {
        if(getQuestionType().equals("")){
            this.question="";

        }else if(getQuestionType().equals("year")){

            this.question="what year was the movie "+question+" created in?";
        }else if(getQuestionType().equals("language")){
            this.question="what language was the movie "+question+" created in?";

        }else if(getQuestionType().equals("actor")){
            this.question="what movie was  "+question+" in?";

        }else if(getQuestionType().equals("category")){
            this.question="what movie is apart of  "+question+" category?";

        }
    }

    private String question;

    public String getQuestionType() {
        return questionType;
    }

    private void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    private String questionType="";

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    private String correctAnswer;



    private ArrayList<String> possibleAnswers;

    public BasicFilmQuestion(String questionData,String questionType,ArrayList<String>incorrectAnswers,String correctAnswer ,int seed){

        setQuestionType(questionType);
        setQuestion(questionData);
        this.correctAnswer=correctAnswer;
        this.possibleAnswers=incorrectAnswers;

        this.possibleAnswers.add(correctAnswer);
        Collections.shuffle(this.possibleAnswers, new Random(seed));



    }
}
