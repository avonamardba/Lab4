package bigdata.labs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.ReceiveBuilder;

public class RouterActor extends AbstractActor {
    private ActorRef storageActor;
    private ActorRef testActor;
    private SupervisorStrategy strategy;

    private void executeTests(TestPackage testPackage) {
        for (TestMessage testMessage : testPackage.getTests()) {
            testMessage.setParent(testPackage);
            testActor.tell(testMessage, ActorRef.noSender());
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestPackage.class,
                        message -> executeTests(message))
                .match(String.class,
                        message -> storageActor.forward(message, getContext()))
                .build();
    }
}