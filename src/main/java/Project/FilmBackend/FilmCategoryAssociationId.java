package Project.FilmBackend;

import java.io.Serializable;

public class FilmCategoryAssociationId implements Serializable {

    private int filmId;

    private int categoryId;


    public int hashCode() {
        return (int)(filmId + categoryId);
    }

    public boolean equals(Object object) {
        if (object instanceof FilmCategoryAssociationId) {
            FilmCategoryAssociationId otherId = (FilmCategoryAssociationId) object;
            return (otherId.filmId == this.filmId) && (otherId.categoryId == this.categoryId);
        }
        return false;
    }

}