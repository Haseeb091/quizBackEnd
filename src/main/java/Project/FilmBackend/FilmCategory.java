package Project.FilmBackend;

import jakarta.persistence.*;






@Entity
@Table(name="film_category")
@IdClass(FilmCategoryAssociationId.class)
public class FilmCategory {


    @Id
    @Column(name = "film_id")
    int filmId;

    @Id
    @Column(name = "category_id")
    int categoryId;




    @ManyToOne
    @JoinColumn(name = "film_id", insertable=false, updatable=false)
    Film film;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable=false, updatable=false)
    Category category;




    public FilmCategory(){


    }

    public FilmCategory(int categoryId, int filmId){

        this.categoryId=categoryId;

        this.filmId=filmId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
