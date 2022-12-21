package Project.FilmBackEnd;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="category")
public class Category {


    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;


    @Column(name = "name")
    private String categoryName;

    @ManyToMany(mappedBy="categories")
    private List<Film> films;


    @Column(name = "last_update")
    private Date lastUpdate;
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




    public Category(){


    }

    public Category(int categoryId,String categoryName){

        this.categoryId=categoryId;
        this.categoryName=categoryName;


    }


}
