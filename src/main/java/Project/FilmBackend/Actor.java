package Project.FilmBackend;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actor")
public class Actor {




    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int actorId;

    @OneToMany(mappedBy = "actor" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FilmActor> filmActors = new HashSet<>();


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


public Actor(){


}

    public Actor(String firstName,String lastName){

        this.firstName=firstName;

        this.lastName=lastName;
    }





}
