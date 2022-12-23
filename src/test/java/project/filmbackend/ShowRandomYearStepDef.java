package project.filmbackend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.*;
import java.time.Year;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;

@ScenarioScope
public class ShowRandomYearStepDef {


//@Param("year")
//Year year, @Param("dataLimit") int dataLimit,@Param("seed") int seed


    @Autowired
    FilmRepository filmRepository;


    int year;
    int seed;

    int numberOfYears;


    private ArrayList<Date> yearsList;


    @Given("the year {int}")
    public void the_year(int year) {

        // Year tempYear=Year.of(year);
        this.year = year;

    }

    @Given("the seed value {int}")
    public void the_seed_value(int seed) {

        this.seed = seed;
    }

    @Given("the number of years {int} required")
    public void the_number_of_years_required(int numberOfYears) {


        this.numberOfYears = numberOfYears;


    }

    @When("i request the random years details")
    public void i_request_the_random_year_details() {


        yearsList = filmRepository.getRandomYearNotEqual(Year.of(year), numberOfYears, seed);
        Assertions.assertNotNull(yearsList);


    }

    @Then("webpage should show year List {string}")
    public void webpage_should_show_years_list(String expectedYearsList) {


        String actualAnswer = "";
        for (Date filmDate : yearsList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(filmDate);
            System.out.println(calendar.get(Calendar.YEAR));
            actualAnswer += calendar.get(Calendar.YEAR) + " -";

        }

        Assertions.assertEquals(expectedYearsList, actualAnswer);
        Assertions.assertFalse(actualAnswer.contains(year + ""));
    }

    @Then("years list size is equal to {int}")
    public void year_list_size_is_equal_to(int numberOfYears) {

        Assertions.assertEquals(numberOfYears, yearsList.size());
    }

}
