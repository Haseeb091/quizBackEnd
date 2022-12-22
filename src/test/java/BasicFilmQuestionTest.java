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



}



