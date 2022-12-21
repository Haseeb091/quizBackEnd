package Project.FilmBackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;


@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {






    @Query(value =
            "            Select DISTINCT actor.actor_id as actor_id,actor.first_name as first_name,actor.last_name as last_name, actor.last_update as last_update " +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " WHERE film.title != :title  ORDER BY rand() " , nativeQuery = true)
    Iterable<Actor> getActorsNotInMovie(@Param("title") String title);
    //get actors which are not in movie
    //change query so it goes in actors


    @Query(value =
            "            Select film.film_id AS film_id,film.title AS title,film.description As description, film.language_id as language_id" +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " GROUP BY film_id "+" HAVING COUNT(actor.actor_id)>3 " , nativeQuery = true)
    Iterable<Film> getMoviesWhichHaveAtLeastThreeActor();

// have to return all columns in film repor or doesnt work


    @Query(value =
            "Select * FROM film "+
           " INNER JOIN film_category ON film.film_id = film_category.film_id "+
            "INNER JOIN category ON film_category.category_id = category.category_id"+
           " WHERE category.name = ?1"+
           " order by rand() "+
           " limit ?2 ;" , nativeQuery = true)
    Iterable<Object> getMovieInCat(String cat, int dataLimit);
    // get certain amount of random movies from cat

    @Query(value =
            "Select Distinct release_year FROM film "+
                    " WHERE release_year != :year"+
                    " order by rand(:seed) "+
                    " limit :dataLimit ;" , nativeQuery = true)
    ArrayList<Date> getMoviesNotOfYear(@Param("year") Year year, @Param("dataLimit") int dataLimit,@Param("seed") int seed);




    @Query(value =
            "Select * FROM film "+
                    " INNER JOIN film_category ON film.film_id = film_category.film_id "+
                    "INNER JOIN category ON film_category.category_id = category.category_id"+
                    " WHERE category.name != ?1"+
                    " order by rand(1) "+
                    " limit ?2 ;" , nativeQuery = true)
    Iterable<Object> getMovieNotInCat(String cat, int dataLimit);






    @Query(value =
            "Select * FROM film "+
                    "INNER JOIN language ON film.language_id = language.language_id;"
            , nativeQuery = true)
    Iterable<Object> getLanguageAndFilms();


    @Query(value =
            "            Select *" +
                    "    FROM film" +
                    " order by rand(:seed) "+
                    " limit :dataLimit ;"
            , nativeQuery = true)
    Iterable<Film> getRandomFilms( @Param("dataLimit") int dataLimit,@Param("seed") int seed);



    @Query(value =
            "            Select film.title AS title " +
                    "    FROM film" +
                    "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
                    " INNER JOIN actor ON film_actor.actor_id = actor.actor_id "+
                    " WHERE actor.actor_id != :actorId "+
                    " GROUP BY film.film_id "+
                    " order by rand(:seed) "+" limit :dataLimit ;" , nativeQuery = true)
    ArrayList<String> getMoviesWhichDontHaveActorInThem(@Param("dataLimit") int dataLimit,@Param("seed") int seed,@Param("actorId") int actorId);



    @Query(value =
            "            Select film.title AS title " +
                    "    FROM film" +
                    "    INNER JOIN film_category ON film.film_id = film_category.film_id "+
                    " INNER JOIN category ON film_category.category_id = category.category_id "+
                    " WHERE category.category_id != :catId "+
                    " GROUP BY film.film_id "+
                    " order by rand(:seed) "+" limit :dataLimit ;" , nativeQuery = true)
    ArrayList<String> getMoviesWhichAreNotApartOfCategory(@Param("dataLimit") int dataLimit,@Param("seed") int seed,@Param("catId") int catId);

}