package project.filmbackend;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;



import java.sql.Date;


import java.util.*;


@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin

public class FilmBackEndApplication {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private LanguageRepository languageRepository;

	public FilmBackEndApplication(ActorRepository ar, FilmRepository fr, LanguageRepository lr){
		this.actorRepository=ar;
		this.filmRepository=fr;
		this.languageRepository=lr;



	}
	public static void main(String[] args) {
		SpringApplication.run(FilmBackEndApplication.class, args);
	}


	@GetMapping("/getRandomMovies/{seed}/{dataLimit}")
	public @ResponseBody
	Iterable<Film> getRandomMovies(@PathVariable int seed, @PathVariable int dataLimit) {



			return	filmRepository.getRandomFilms(dataLimit,seed);



	}








	@GetMapping("/getYearQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getYearQuestion(@PathVariable int id,@PathVariable int seed) {



		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException(""));

		ArrayList<Date>filmDates=filmRepository.getRandomYearNotEqual(filmQuestion.getReleaseYear(),3,seed);
		ArrayList<String>incorrectOptions=new ArrayList<>();


		for (Date filmDate:filmDates) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filmDate);
			calendar.get(Calendar.YEAR);
			incorrectOptions.add(calendar.get(Calendar.YEAR)+"");

		}
		return new BasicFilmQuestion(filmQuestion.getTitle(),"year",incorrectOptions,filmQuestion.getReleaseYear().toString(),seed);


	}

	@GetMapping("/getLanguageQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getLanguageQuestion(@PathVariable int id,@PathVariable int seed) {


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException(""));
		ArrayList<String>incorrectOptions=languageRepository.getRandomLanguages(3,seed,filmQuestion.getLanguageId());

		return new BasicFilmQuestion(filmQuestion.getTitle(),"language",incorrectOptions,filmQuestion.getLanguage().getName(),seed);


	}


	@GetMapping("/getActorQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getActorQuestion(@PathVariable int id,@PathVariable int seed) {


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException(""));

		List<Actor>allActors =filmQuestion.getActors();
		Collections.shuffle(allActors, new Random(seed));
		Actor actorQuestion=allActors.get(0);
		ArrayList<String> incorrectOptions=filmRepository.getMoviesWhichDontHaveActorInThem(3,seed,actorQuestion.getActorId());


		return new BasicFilmQuestion(actorQuestion.getFirstName()+" "+actorQuestion.getLastName(),"actor",incorrectOptions,filmQuestion.getTitle(),seed);



	}

	@GetMapping("/getCategoryQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getCategoryQuestion(@PathVariable int id,@PathVariable int seed) {


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException(""));

		List<Category>categories =filmQuestion.getCategories();

		Category categoryQuestion=categories.get(0);
		ArrayList<String> incorrectOptions=filmRepository.getMoviesWhichAreNotApartOfCategory(3,seed,categoryQuestion.getCategoryId());



			return new BasicFilmQuestion(categoryQuestion.getCategoryName(),"category",incorrectOptions,filmQuestion.getTitle(),seed);
	}

}


