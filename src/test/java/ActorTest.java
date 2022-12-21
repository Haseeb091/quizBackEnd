

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.filmbackend.Actor;

public class ActorTest {

    @Test
    public void flagGetAndSet(){
        Actor testActor=new Actor(1,"bob","smith");

        Assertions.assertEquals(testActor.getActorId(),1,"actor id should be 1");



    }
}
