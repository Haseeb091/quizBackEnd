

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.filmbackend.Actor;

 class ActorTest {

    @Test
    public void actorConstructor(){
        Actor testActor=new Actor(1,"bob","smith");

        Assertions.assertEquals(1,testActor.getActorId(),"actor id should be 1");
        Assertions.assertEquals("bob",testActor.getFirstName(),"actor id should be 1");
        Assertions.assertEquals("smith",testActor.getLastName(),"actor id should be 1");



    }


    @Test
    public void actorIdGetAndSet(){
        Actor testActor=new Actor(1,"bob","smith");

        Assertions.assertEquals(1,testActor.getActorId());
       testActor.setActorId(2);
        Assertions.assertEquals(2,testActor.getActorId());



    }

    @Test
    public void actorFirstNameGetAndSet(){
        Actor testActor=new Actor(1,"bob","smith");

        Assertions.assertEquals("bob",testActor.getFirstName());
        testActor.setFirstName("bobby");
        Assertions.assertEquals("bobby",testActor.getFirstName());



    }

    @Test
    public void actorLastNameGetAndSet(){
        Actor testActor=new Actor(1,"bob","smith");

        Assertions.assertEquals("smith",testActor.getLastName());
        testActor.setLastName("james");
        Assertions.assertEquals("james",testActor.getLastName());



    }
}
