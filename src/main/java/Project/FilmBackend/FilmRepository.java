package Project.FilmBackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {

    @Query("SELECT f FROM Film f WHERE f.rentalDuration BETWEEN ?1 AND ?2")
    Page<Film> findDuration(int start, int end,Pageable pageable);










//get 2 random actors in movie
    @Query(value = "    SELECT subquery.filmid AS filmid," +
            "    subquery.actorid AS actorid," +
            "    subquery.title AS title," +
            "    subquery.description AS description" +
            "    FROM (" +
            "            Select film.film_id AS filmid,film.title AS title,actor.actor_id AS actorid,film.description As description" +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " WHERE film.title LIKE '%ACADEMY%' ORDER BY rand() LIMIT 2" +
            ") subquery" +
            "    Order by actorid", nativeQuery = true)
    Iterable<Model> getTwoActorsFromMovie();
//move to actor

    @Query(value = "    SELECT subquery.filmid AS filmid," +
            "    subquery.actorid AS actorid," +
            "    subquery.title AS title," +
            "    subquery.description AS description" +
            "    FROM (" +
            "            Select film.film_id AS filmid,film.title AS title,actor.actor_id AS actorid,film.description As description" +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " WHERE film.title NOT LIKE '%ACADEMY%' ORDER BY rand() LIMIT 4" +
            ") subquery" +
            "    Order by actorid", nativeQuery = true)
    Iterable<Model> getNotActorsInMovie();
    //get actors which are not in movie
    //change querry so it goes in actors


    @Query(value =
            "            Select film.film_id AS filmid,film.title AS title,film.description As description" +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " GROUP BY filmid "+" HAVING COUNT(actor.actor_id)>10 " , nativeQuery = true)
    Iterable<Object> getMoviesWhichHaveAtLeastOneActor();
// gets movies with at least 1 actor change to 1
    @Query(value =
            "            Select film.film_id AS filmid,film.title AS title,film.description As description" +
                    "    FROM film" +
                    "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
                    "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
                    " GROUP BY filmid "+" HAVING COUNT(actor.actor_id)>10 " , nativeQuery = true)
    Iterable<Object> getCat();





    @Query(value =
            "Select * FROM film "+
           " INNER JOIN film_category ON film.film_id = film_category.film_id "+
            "INNER JOIN category ON film_category.category_id = category.category_id"+
           " WHERE category.name = ?1"+
           " order by rand() "+
           " limit ?2 ;" , nativeQuery = true)
    Iterable<Object> getMovieInCat(String cat, int dataLimit);
    // get certain amount of movies from cat




    @Query(value =
            "Select * FROM film "+
                    " INNER JOIN film_category ON film.film_id = film_category.film_id "+
                    "INNER JOIN category ON film_category.category_id = category.category_id"+
                    " WHERE category.name != ?1"+
                    " order by rand() "+
                    " limit ?2 ;" , nativeQuery = true)
    Iterable<Object> getMovieNotInCat(String cat, int dataLimit);

//    @Query(value =
//            "Select * FROM film "+
//                    " INNER JOIN film_category ON film.film_id = film_category.film_id "+
//                    "INNER JOIN category ON film_category.category_id = category.category_id"+
//                    " order by rand() "+
//                    " limit 1 ;" , nativeQuery = true)
//    Iterable<Object> getMovieForCategoryQuiz(String cat);

    @Query(value =
            "Select Distinct category.name FROM film_category "+
                    "INNER JOIN category ON film_category.category_id = category.category_id;"
            , nativeQuery = true)
    Iterable<Object> getCategoryQuiz();

    //get all categories that have been used in films
    // move this query
}