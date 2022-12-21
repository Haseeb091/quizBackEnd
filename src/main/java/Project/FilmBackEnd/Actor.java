package Project.FilmBackEnd;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="actor")
public class Actor {


    public Actor(int actorId,String firstName,String lastName){

        this.actorId=actorId;
        this.firstName=firstName;
        this.lastName=lastName;


    }

    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorId;



    @ManyToMany(mappedBy="actors")
    private List<Film> films;


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }










}
