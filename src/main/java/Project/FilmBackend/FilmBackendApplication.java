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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;


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


		return actorRepository.findById(id)
				.orElseThrow(() -> new ResourceAccessException("cant acsess "+400));




	}

	@PostMapping("/newactor")
	public @ResponseBody Actor newActor(@RequestBody Actor newActor) {
		return actorRepository.save(newActor);
	}


	@GetMapping("/updateLanguageId")
	String updateLanguageId() {

//put in try catch
// or figure out error code handling

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

		return filmRepository.findById(id)
				.orElseThrow(() -> new ResourceAccessException("cant acsess "+id));
	}

	@PostMapping("/film")
	public @ResponseBody Film newFilm(@RequestBody Film newFilmJson) {
		return filmRepository.save(newFilmJson);
	}
	@GetMapping("/actorsNotInMovie")
	public @ResponseBody Iterable<Object> actorsNotInParticlularMovie(@RequestBody Film film) {

		return filmRepository.getActorsNotInMovie(film.getTitle());
	}

	// another query needed to get the language and another one to get languages that are not equal to input parameter

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