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
    @Query(value =
            "            Select actor.actor_id AS actorid,actor.first_name As name, film.film_id As filmid" +
            "    FROM film" +
            " INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            " INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " WHERE film.title = :title "+ " ORDER BY rand() LIMIT 2"
            , nativeQuery = true)
    Iterable<Model> getTwoActorsFromMovie(@Param("title") String title);
//move to actor

    @Query(value =
            "            Select DISTINCT actor.actor_id AS actorid,actor.first_name As name" +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " WHERE film.title != :title  ORDER BY rand() " , nativeQuery = true)
    Iterable<Object> getActorsNotInMovie(@Param("title") String title);
    //get actors which are not in movie
    //change query so it goes in actors


    @Query(value =
            "            Select film.film_id AS filmid,film.title AS title,film.description As description" +
            "    FROM film" +
            "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
            "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
            " GROUP BY filmid "+" HAVING COUNT(actor.actor_id)>3 " , nativeQuery = true)
    Iterable<Object> getMoviesWhichHaveAtLeastThreeActor();




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
            "Select * FROM film "+
                    " INNER JOIN film_category ON film.film_id = film_category.film_id "+
                    "INNER JOIN category ON film_category.category_id = category.category_id"+
                    " WHERE category.name != ?1"+
                    " order by rand() "+
                    " limit ?2 ;" , nativeQuery = true)
    Iterable<Object> getMovieNotInCat(String cat, int dataLimit);



    @Query(value =
            "Select Distinct category.name FROM film_category "+
                    "INNER JOIN category ON film_category.category_id = category.category_id;"
            , nativeQuery = true)
    Iterable<Category> getCategoryQuiz();

    //get all categories that have been used in films
    // move this query


    @Query(value =
            "Select * FROM film "+
                    "INNER JOIN language ON film.language_id = language.language_id;"
            , nativeQuery = true)
    Iterable<Object> getLanguageAndFilms();


// gets movies with at least 1 actor change to 1
//    @Query(value =
//            "            Select film.film_id AS filmid,film.title AS title,film.description As description" +
//                    "    FROM film" +
//                    "            INNER JOIN film_actor ON film.film_id = film_actor.film_id "+
//                    "INNER JOIN actor ON film_actor.actor_id = actor.actor_id"+
//                    " GROUP BY filmid "+" HAVING COUNT(actor.actor_id)>10 " , nativeQuery = true)
//    Iterable<Object> getCat();

//    @Query(value =
//            "Select * FROM film "+
//                    " INNER JOIN film_category ON film.film_id = film_category.film_id "+
//                    "INNER JOIN category ON film_category.category_id = category.category_id"+
//                    " order by rand() "+
//                    " limit 1 ;" , nativeQuery = true)
//    Iterable<Object> getMovieForCategoryQuiz(String cat);

}