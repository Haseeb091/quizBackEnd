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
        Assertions.assertEquals(2012,film.getReleaseYear().toString(),"release year should be 2012 ");
        Assertions.assertEquals("this is movie description",film.getDescription(),"movie description not correct ");
        Assertions.assertEquals("iron man",film.getTitle(),"movie title not correct ");




    }


   
}
