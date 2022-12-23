package project.filmbackend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

@ScenarioScope
public class RandomLanguageStepDef {


    @Autowired
    LanguageRepository languageRepository;


    int languageId;
    int seed;

    int numberOfLanguages;


    private ArrayList<String> languagesList;

    @Given("the language id {int}")
    public void the_language_id(int languageId) {

        this.languageId = languageId;

    }

    @Given("the seeds value  {int}")
    public void the_seed_value(int seed) {

        this.seed = seed;
    }

    @Given("the number of languages {int} required")
    public void the_number_of_languages_required(int numberOfLanguages) {


        this.numberOfLanguages = numberOfLanguages;


    }

    @When("i request the random languages details")
    public void i_request_the_random_language_details() {


        languagesList = languageRepository.getRandomLanguages(numberOfLanguages, seed, languageId);
        Assertions.assertNotNull(languagesList);


    }

    @Then("webpage should show language List {string}")
    public void webpage_should_show_language_list(String expectedYearsList) {


        String actualAnswer = "";
        for (String language : languagesList) {

            actualAnswer += language + " -";

        }

        Assertions.assertEquals(expectedYearsList, actualAnswer);

    }

    @Then("language list size is equal to {int}")
    public void language_list_size_is_equal_to(int numberOfYears) {

        Assertions.assertEquals(numberOfLanguages, languagesList.size());
    }


}
