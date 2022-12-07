package Project.FilmBackend;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;


import java.time.Year;
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

	public FilmBackendApplication(ActorRepository ar, FilmRepository fr){
		this.actorRepository=ar;
		this.filmRepository=fr;



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

		return actorRepository.findById(1)
				.orElseThrow(() -> new ResourceAccessException("cant acsess "+1));
	}

	@PostMapping("/newactor")
	public @ResponseBody Actor newActor(@RequestBody Actor newActor) {
		return actorRepository.save(newActor);
	}


	@GetMapping("/updateyear")
	String updateAllFilms() {

//put in try catch
// or figure out error code handling

		ArrayList<Film> a =new ArrayList<Film>();
		a.addAll(filmRepository.findAll());

		for (Film film:a) {
			Random r=new Random();

			film.setLanguageId(r.nextInt(1,6));
			filmRepository.save(film);
		}
		//was meant to be film change it

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

		return filmRepository.findById(id)
				.orElseThrow(() -> new ResourceAccessException("cant acsess "+id));
	}

	@PostMapping("/film")
	public @ResponseBody Film newFilm(@RequestBody Film newFilmJson) {
		return filmRepository.save(newFilmJson);
	}
	@GetMapping("/actorsNotInMovie")
	public @ResponseBody Iterable<Object> actorsNotInParticlularMovie(@RequestBody Film film) {
		//need to make separte mapping for each query

		//return filmRepository.findDuration(5,6);
		//Iterable<FilmModel> models=filmRepository.test();
//		List<FilmModel> modelsList= new ArrayList<FilmModel>();
//		if(models != null) {
//			for(FilmModel e: models) {
//				modelsList.add(e);
//			}
//		}
//
//		Comparator<FilmModel> compareById =
//				(FilmModel o1, FilmModel o2) -> o1.getTitle().compareTo( o2.getTitle() );
//		Collections.sort(modelsList, compareById);
//
//
//		return  modelsList;

		//Pageable pageable = PageRequest.of(0, 10);
		return filmRepository.getActorsNotInMovie(film.getTitle());
	}

	// another querry needed to get the language and anotherone to get languages that are not equal to input parameter

	@GetMapping("/actorsInMovie")
	public @ResponseBody Iterable<Model> getTwoActorsFromMovie(@RequestBody Film film) {



		return filmRepository.getTwoActorsFromMovie(film.getTitle());
	}

	@GetMapping("/getMoviesWhichHaveAtLeastThreeActor")
	public @ResponseBody Iterable<Object> getMoviesWhichHaveAtLeastThreeActor() {



		return filmRepository.getMoviesWhichHaveAtLeastThreeActor();
	}

	@GetMapping("/getMovieInCategory")
	public @ResponseBody Iterable<Object> getMovieInCategory(@RequestBody Category cat) {



		return filmRepository.getMovieInCat(cat.getCategoryName(),5);
	}


}


 interface ActorModel {
	int getFilmid();
	String getTitle();
	String getDescription();
	int getActorid();



}