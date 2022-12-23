import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.filmbackend.Actor;
import project.filmbackend.BasicFilmQuestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BasicFilmQuestionTest {



    @Test
    public void BasicFilmQuestionConstructor(){
        ArrayList<String>incorrectAnswers=new ArrayList<>();
        incorrectAnswers.add("1999");
        incorrectAnswers.add("2000");
        BasicFilmQuestion basicFilmQuestion= new BasicFilmQuestion("Academy Dinasaur","year",incorrectAnswers,"2020",1);
        Assertions.assertEquals("what year was the movie Academy Dinasaur created in?",basicFilmQuestion.getQuestion(),"question is not correct");
        Assertions.assertEquals("year",basicFilmQuestion.getQuestionType(),"question type is not year is not correct");
        Assertions.assertEquals(3,basicFilmQuestion.getPossibleAnswers().size(),"the list size should be 3 including the correct answers");
        Assertions.assertEquals("2020",basicFilmQuestion.getCorrectAnswer(),"the correct answer value is not right");


    }

    @Test
    public void BasicFilmQuestionConstructorQuestionTypeLanguage(){

        ArrayList<String>incorrectAnswers=new ArrayList<>();
        incorrectAnswers.add("english");
        incorrectAnswers.add("french");
        BasicFilmQuestion basicFilmQuestion= new BasicFilmQuestion("Academy Dinasaur","language",incorrectAnswers,"japanese",1);
        Assertions.assertEquals("what language was the movie Academy Dinasaur created in?",basicFilmQuestion.getQuestion(),"question is not correct");
        Assertions.assertEquals("language",basicFilmQuestion.getQuestionType(),"question type is not language is not correct");


    }

    @Test
    public void BasicFilmQuestionConstructorQuestionTypeActor(){

        ArrayList<String>incorrectAnswers=new ArrayList<>();
        incorrectAnswers.add("movie1");
        incorrectAnswers.add("movie2");
        BasicFilmQuestion basicFilmQuestion= new BasicFilmQuestion("bob","actor",incorrectAnswers,"movie3",1);
        Assertions.assertEquals("what movie was  bob in?",basicFilmQuestion.getQuestion(),"question is not correct");
        Assertions.assertEquals("actor",basicFilmQuestion.getQuestionType(),"question type is not actor is not correct");



    }

    @Test
    public void BasicFilmQuestionConstructorQuestionTypeCat(){

        ArrayList<String>incorrectAnswers=new ArrayList<>();
        incorrectAnswers.add("movie1");
        incorrectAnswers.add("movie2");
        BasicFilmQuestion basicFilmQuestion= new BasicFilmQuestion("Action","category",incorrectAnswers,"movie3",1);
        Assertions.assertEquals("what movie is apart of  Action category?",basicFilmQuestion.getQuestion());
        Assertions.assertEquals("category",basicFilmQuestion.getQuestionType());



    }



}



