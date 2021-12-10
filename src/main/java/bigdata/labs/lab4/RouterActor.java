package bigdata.labs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.SupervisorStrategy;

public class RouterActor extends AbstractActor {
    private ActorRef storageActor;
    private ActorRef testActor;
    private SupervisorStrategy strategy;

    
    @Override
    public Receive createReceive() {
        return null;
    }
}
