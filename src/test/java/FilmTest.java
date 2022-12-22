import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.filmbackend.Actor;
import project.filmbackend.Film;

import java.time.Year;

public class FilmTest {




    @Test
    public void filmConstructor(){
        Year tempYear;
        tempYear=Year.of(2012);
        Film film=new Film(1,2,tempYear,"this is movie description","iron man");

        Assertions.assertEquals(1,film.getFilmid(),"film id should be 1 but isnt");
        Assertions.assertEquals(2,film.getLanguageId(),"language id should be 2 but isnt");
        Assertions.assertEquals("2012",film.getReleaseYear().toString(),"release year should be 2012 ");
        Assertions.assertEquals("this is movie description",film.getDescription(),"movie description not correct ");
        Assertions.assertEquals("iron man",film.getTitle(),"movie title not correct ");




    }

    @Test
    public void filmGetAndSetFilmId(){
        Year tempYear;
        tempYear=Year.of(2012);
        Film film=new Film(1,2,tempYear,"this is movie description","iron man");

        Assertions.assertEquals(1,film.getFilmid(),"film id should be 1 but isnt");
        film.setFilmid(2);

        Assertions.assertEquals(2,film.getFilmid(),"film id should be 2 but isnt");


    }

    @Test
    public void filmGetAndSetLanguageId(){
        Year tempYear;
        tempYear=Year.of(2012);
        Film film=new Film(1,1,tempYear,"this is movie description","iron man");

        Assertions.assertEquals(1,film.getLanguageId(),"languag id should be 1 but isnt");
        film.setLanguageId(2);

        Assertions.assertEquals(2,film.getLanguageId(),"language id should be 2 but isnt");


    }
    @Test
    public void filmGetAndSetReleaseYear(){
        Year tempYear;
        tempYear=Year.of(2012);
        Film film=new Film(1,1,tempYear,"this is movie description","iron man");

        Assertions.assertEquals("2012",film.getReleaseYear().toString(),"release year  should be 2012 but isnt");

        film.setReleaseYear(Year.of(2000));

        Assertions.assertEquals("2000",film.getReleaseYear().toString(),"release year  should be 2000 but isnt");


    }
    @Test
    public void filmGetAndSetDescription(){
        Year tempYear;
        tempYear=Year.of(2012);
        Film film=new Film(1,1,tempYear,"this is movie description","iron man");

        Assertions.assertEquals("this is movie description",film.getDescription(),"description is not correct");
        film.setDescription("new description");

        Assertions.assertEquals("new description",film.getDescription(),"description is not correct");



    }
    @Test
    public void filmGetAndSetTitle(){
        Year tempYear;
        tempYear=Year.of(2012);
        Film film=new Film(1,1,tempYear,"this is movie description","iron man");

        Assertions.assertEquals("iron man",film.getTitle(),"title is not correct");
        film.setTitle("iron man2");

        Assertions.assertEquals("iron man2",film.getTitle(),"title is not correct");



    }



}
