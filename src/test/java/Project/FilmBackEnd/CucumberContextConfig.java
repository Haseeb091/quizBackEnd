package Project.FilmBackEnd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = FilmBackEndApplication.class )
public class CucumberContextConfig {



}
