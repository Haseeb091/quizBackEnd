package Project.FilmBackend;



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

public class FilmBackendApplication {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private LanguageRepository languageRepository;

	public FilmBackendApplication(ActorRepository ar, FilmRepository fr,LanguageRepository lr){
		this.actorRepository=ar;
		this.filmRepository=fr;
		this.languageRepository=lr;



	}
	public static void main(String[] args) {
		SpringApplication.run(FilmBackendApplication.class, args);
	}

	@GetMapping("allactors")
	public  @ResponseBody Iterable<Actor> getAllActors(){

		return actorRepository.findAll();
	}

	@GetMapping("/actor/{id}")
	public @ResponseBody Actor oneActor(@PathVariable int id) {


		return actorRepository.findById(id)
				.orElseThrow(() -> new ResourceAccessException("cant acsess "+400));
//




	}

	@PostMapping("/newactor")
	public @ResponseBody Actor newActor(@RequestBody Actor newActor) {
		return actorRepository.save(newActor);
	}


	@GetMapping("/updateLanguageId")
	String updateLanguageId() {



		ArrayList<Film> a =new ArrayList<Film>();
		a.addAll(filmRepository.findAll());

		for (Film film:a) {
			Random r=new Random();

			film.setLanguageId(r.nextInt(1,6));
			filmRepository.save(film);
		}


		return "update complete";

	}
	@PutMapping("/actor/{id}")
	Actor replaceEmployee(@RequestBody Actor newActor, @PathVariable int id) {

		return actorRepository.findById(id)
				.map(actor -> {
					actor.setFirstName(newActor.getFirstName());
					actor.setLastName(newActor.getLastName());
					return actorRepository.save(actor);
				})
				.orElseGet(() -> {
					newActor.setActorId(id);
					return actorRepository.save(newActor);
				});



	}

	@DeleteMapping("/actor/{id}")
	void deleteActor(@PathVariable int id) {
		actorRepository.deleteById(id);
	}

	// film

	@GetMapping("allfilms")
	public  @ResponseBody Iterable<Film> getAllFilms(){

		return filmRepository.findAll();
	}

	@GetMapping("/film/{id}")
	public @ResponseBody Film oneFilm(@PathVariable int id) {

//		return filmRepository.findById(1)
//				.orElseThrow(() -> new ResourceAccessException("cant acsess "+id));

		return filmRepository.findById(id)
				.orElseThrow(() -> new ResourceAccessException("cant acsess "+id));
	}

	@PostMapping("/film")
	public @ResponseBody Film newFilm(@RequestBody Film newFilmJson) {
		return filmRepository.save(newFilmJson);
	}

//this ones working
	@GetMapping("/getRandomMovies/{seed}/{dataLimit}")
	public @ResponseBody
	Iterable<Film> getRandomMovies(@PathVariable int seed, @PathVariable int dataLimit) {
		try {


			return	filmRepository.getRandomFilms(dataLimit,seed);
		}catch (Exception e){

			throw e;
		}


	}

	// another query needed to get the language and another one to get languages that are not equal to input parameter





	@GetMapping("/getMovieInCategory")
	public @ResponseBody Iterable<Object> getMovieInCategory(@RequestBody Category cat) {



		return filmRepository.getMovieNotInCat(cat.getCategoryName(),5);
	}

	@GetMapping("/getYearQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getYearQuestion(@PathVariable int id,@PathVariable int seed) {
		// recive movie id try to obtain movie
		// then send to fr to get movies not made in year
		// then add to custom object and send back

//		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("cant acsess "+400));;
//		Iterable<Film> incorrectMovies =  filmRepository.getMoviesNotOfYear(filmQuestion.getReleaseYear(),3);
//		ArrayList<String>incorrectOptions=new ArrayList<>();
//		for (Film incorrectMovie:incorrectMovies) {
//
//			incorrectOptions.add(incorrectMovie.getReleaseYear().toString());
//
//
//		}
//		 BasicFilmQuestion basicFilmQuestion=new BasicFilmQuestion(filmQuestion,incorrectOptions);
//		return basicFilmQuestion;


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("cant acsess "+400));;

		ArrayList<Date>filmDates=filmRepository.getMoviesNotOfYear(filmQuestion.getReleaseYear(),3,seed);;
		ArrayList<String>incorrectOptions=new ArrayList<>();


		for (Date filmDate:filmDates) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(filmDate);
			calendar.get(Calendar.YEAR);
			incorrectOptions.add(calendar.get(Calendar.YEAR)+"");

		}
		BasicFilmQuestion basicFilmQuestion=new BasicFilmQuestion(filmQuestion.getTitle(),"year",incorrectOptions,filmQuestion.getReleaseYear().toString(),seed);
		return basicFilmQuestion;
	//	return filmRepository.getMovieNotInCat(cat.getCategoryName(),5);
	}

	@GetMapping("/getLanguageQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getLanguageQuestion(@PathVariable int id,@PathVariable int seed) {


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("cant acsess "+400));;


		ArrayList<String>incorrectOptions=languageRepository.getRandomLanguages(3,seed,filmQuestion.getLanguageId());




		BasicFilmQuestion basicFilmQuestion=new BasicFilmQuestion(filmQuestion.getTitle(),"language",incorrectOptions,filmQuestion.getLanguage().getName(),seed);
		return basicFilmQuestion;
		//	return filmRepository.getMovieNotInCat(cat.getCategoryName(),5);
	}


	@GetMapping("/getActorQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getActorQuestion(@PathVariable int id,@PathVariable int seed) {


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("cant acsess "+400));;

// use actor id to find film where actor not in there
		List<Actor>allActors =filmQuestion.getActors();
		Collections.shuffle(allActors, new Random(seed));
		Actor actorQuestion=allActors.get(0);
		ArrayList<String> incorrectOptions=filmRepository.getMoviesWhichDontHaveActorInThem(3,seed,actorQuestion.getActorId());


		BasicFilmQuestion basicFilmQuestion=new BasicFilmQuestion(actorQuestion.getFirstName()+" "+actorQuestion.getLastName(),"actor",incorrectOptions,filmQuestion.getTitle(),seed);
		return basicFilmQuestion;

		//	return filmRepository.getMovieNotInCat(cat.getCategoryName(),5);
	}

	@GetMapping("/getCategoryQuestion/{seed}/{id}")
	public @ResponseBody BasicFilmQuestion getCategoryQuestion(@PathVariable int id,@PathVariable int seed) {


		Film filmQuestion= filmRepository.findById(id).orElseThrow(() -> new ResourceAccessException("cant acsess "+400));;

// use actor id to find film where actor not in there
		List<Category>categories =filmQuestion.getCategories();

		Category categoryQuestion=categories.get(0);
		ArrayList<String> incorrectOptions=filmRepository.getMoviesWhichAreNotApartOfCategory(3,seed,categoryQuestion.getCategoryId());


		BasicFilmQuestion basicFilmQuestion=new BasicFilmQuestion(categoryQuestion.getCategoryName(),"category",incorrectOptions,filmQuestion.getTitle(),seed);

			return basicFilmQuestion;
	}

}


