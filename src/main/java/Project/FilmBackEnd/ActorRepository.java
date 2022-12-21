package Project.FilmBackEnd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer> {

    @Query(value =
            "            Select *" +
                    "    FROM actor"
            , nativeQuery = true)
    Iterable<Actor> getTwoActors2FromMovie(@Param("title") String title);

    @Query(value =
            "            Select DISTINCT actor.actor_id as actor_id,actor.first_name as first_name,actor.last_name as last_name, actor.last_update as last_update " +
                    "    FROM film" +
                    "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
                    "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
                    " WHERE film.title != :title  ORDER BY rand() " , nativeQuery = true)
    Iterable<Actor> getActorsNotInMovie(@Param("title") String title);
    //get actors which are not in movie
    //change query so it goes in actors
}
