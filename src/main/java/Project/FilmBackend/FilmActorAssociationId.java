package Project.FilmBackend;

import java.io.Serializable;

public class FilmActorAssociationId implements Serializable {

    private int filmId;

    private int actorId;


    public int hashCode() {
        return (int)(filmId + actorId);
    }

    public boolean equals(Object object) {
        if (object instanceof FilmActorAssociationId) {
            FilmActorAssociationId otherId = (FilmActorAssociationId) object;
            return (otherId.filmId == this.filmId) && (otherId.actorId == this.actorId);
        }
        return false;
    }

}