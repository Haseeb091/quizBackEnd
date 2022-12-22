package project.filmbackend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Year;
import java.util.List;

@Entity
@Table(name="film")
public class Film {

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private   int filmId;



    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="film_actor",
            joinColumns=@JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="actor_id"))
    private List<Actor> actors ;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="film_category",
            joinColumns=@JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns=@JoinColumn(name="category_id", referencedColumnName="category_id"))
     private List<Category> categories;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "language_id", insertable=false, updatable=false)
    private Language language;


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private Year releaseYear;
//
    @Column(name = "language_id")
    private int languageId;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getFilmid() {
        return filmId;
    }

    public void setFilmid(int filmid) {
        this.filmId = filmid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear=releaseYear;

    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }




    public Film(int filmId,int languageId,Year releaseYear,String description,String title){
this.filmId=filmId;
this.languageId=languageId;
this.releaseYear=releaseYear;
this.description=description;
this.title=title;

    }

    public Film(){

    }




}

