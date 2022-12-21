package project.filmbackend;

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
            "Select Distinct release_year FROM film "+
                    " WHERE release_year != :year"+
                    " order by rand(:seed) "+
                    " limit :dataLimit ;" , nativeQuery = true)
    ArrayList<Date> getMoviesNotOfYear(@Param("year") Year year, @Param("dataLimit") int dataLimit,@Param("seed") int seed);





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