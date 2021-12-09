package bmstu.bigdata.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class TestActor extends AbstractActor {
    private final ActorRef storageActor;

    TestActor(ActorRef storageActor) {
        this.storageActor = storageActor;
    }

//    private executeTest(T)
    @Override
    public Receive createReceive() {
        return null;
    }
}
