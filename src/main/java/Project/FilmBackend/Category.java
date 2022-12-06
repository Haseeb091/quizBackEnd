package Project.FilmBackend;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="category")
public class Category {


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(Set<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;


    @Column(name = "name")
    private String categoryName;


    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FilmCategory> filmCategories = new HashSet<>();

    public Category(){


    }

    public Category(int categoryId){

        this.categoryId=categoryId;


    }


}
