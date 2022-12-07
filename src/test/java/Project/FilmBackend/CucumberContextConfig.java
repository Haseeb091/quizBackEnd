package Project.FilmBackend;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = FilmBackendApplication.class )
public class CucumberContextConfig {



}
