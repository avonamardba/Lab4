package bigdata.labs.lab4;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

public class RouterActor extends AbstractActor {
    private static final int RETRIES_COUNT = 5;
    private static final int WORKERS_COUNT = 5;
    private ActorRef storageActor;
    private ActorRef testActor;
    private SupervisorStrategy strategy;

    RouterActor(ActorSystem system) {
        this.storageActor = system.actorOf(Props.create(StorageActor.class));
        this.strategy = new OneForOneStrategy(

        )
    }

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