package project.filmbackend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

import java.time.Year;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static io.restassured.RestAssured.given;

@ScenarioScope
public class ShowRandomFilmsStepDef {

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    LanguageRepository languageRepository;

    int actorId;
    int seed;

    int numberOfFilms;




    Iterable<Film> films;


    @Given("the seed {int}")
    public void the_seed(int seed) {
        this.seed = seed;


    }

    @Given("the number of films {int} required")
    public void the_number_of_films_required(int numberOfFilms) {


        this.numberOfFilms = numberOfFilms;


    }

    @When("i request the random film details")
    public void i_request_the_random_film_details() {


        films = filmRepository.getRandomFilms(numberOfFilms, seed);
        System.out.println(films.toString());

        Assertions.assertNotEquals(null, films, "films list is null");


    }

    @Then("webpage should show film List {string}")
    public void webpage_should_show_film_list(String filmList) {
        String testFilmDetails = "";
        for (Film film : films) {
            testFilmDetails += film.getFilmid() + " ";
            testFilmDetails += film.getTitle() + " ";
            testFilmDetails += film.getReleaseYear() + " ";
            testFilmDetails += film.getDescription() + " ";

            testFilmDetails += " -";


        }
        Assertions.assertEquals(filmList, testFilmDetails);

    }

    @Then("film list size is equal to {int}")
    public void film_list_size_is_equal_to(int filmSize) {

        Assertions.assertEquals(filmSize, ((Collection<?>) films).size());
    }
}
