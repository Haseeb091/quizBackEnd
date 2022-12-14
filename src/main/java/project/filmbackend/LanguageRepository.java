package project.filmbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer> {

    @Query(value =
            "            Select language.name AS name" +
                    "    FROM language" +
                    " WHERE language_id != :languageId "+
                    " order by rand(:seed) "+
                    " limit :dataLimit ;"
            , nativeQuery = true)
    ArrayList<String> getRandomLanguages(@Param("dataLimit") int dataLimit, @Param("seed") int seed, @Param("languageId") int languageId);
}

