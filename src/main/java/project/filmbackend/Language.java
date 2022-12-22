package project.filmbackend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Year;
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
    private String name;


    @Column(name = "last_update")
    private Date lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "language" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)// this one broke it when eager ??
     Set<Film> films = new HashSet<>();

    public Language(int languageId, String languageName, Date lastUpdate){
        this.languageId=languageId;
        this.name=languageName;
        this.lastUpdate=lastUpdate;

    }
    public Language(){


    }


    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }




}
