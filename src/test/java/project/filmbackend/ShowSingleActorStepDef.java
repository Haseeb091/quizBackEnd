package project.filmbackend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

import static io.restassured.RestAssured.given;

@ScenarioScope
public class ShowSingleActorStepDef {
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    LanguageRepository languageRepository;

    int actorId;


    Actor chosenActor;

    @Given("actor exists with id {int}")
    public void actor_exists_with_id(int id){



        actorRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("cant acsess "+id));
        actorId=id;

    }

    @When("i request the actors details")
    public void i_request_the_actors_details(){


        chosenActor=actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceAccessException("cant acsess "+actorId));

        Assertions.assertNotEquals(null,chosenActor,"Actor is null");

    }

    @Then("webpage should show actors {string} and {string}")
    public void webpage_should_show_actors_firstname_lastname(String firstName,String lastName){
        String correctDetails= firstName +" "+lastName;
        String testDetails=chosenActor.getFirstName()+" "+chosenActor.getLastName();
        Assertions.assertEquals(correctDetails,testDetails,"the first name and last name do not match");

    }
}
