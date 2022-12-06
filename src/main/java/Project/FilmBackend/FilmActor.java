package Project.FilmBackend;

import jakarta.persistence.*;

@Entity
@Table(name="film_actor")
@IdClass(FilmActorAssociationId.class)
public class FilmActor {


    @Id
    @Column(name = "actor_id")
    int actorId;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @ManyToOne
    @JoinColumn(name = "film_id", insertable=false, updatable=false)
    Film film;

    @ManyToOne
    @JoinColumn(name = "actor_id", insertable=false, updatable=false)
    Actor actor;

    @Id
    @Column(name = "film_id")
    int filmId;


public FilmActor(){


}

    public FilmActor(int actorid, int filmId){

        this.actorId=actorid;

        this.filmId=filmId;
    }
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

}
