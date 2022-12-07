package Project.FilmBackend;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="language")
public class Language {




    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;


    @Column(name = "name")
    private String categoryName;


    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(mappedBy = "language" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)// this one broke it when eager ??
    private Set<Film> films = new HashSet<>();

    public Language(){


    }

    public Language(int languageId){

        this.languageId=languageId;


    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
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




}
