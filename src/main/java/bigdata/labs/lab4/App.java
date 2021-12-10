package bigdata.labs.lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class App extends AllDirectives {
    private Route createRoute(ActorRef routerActor) {
        return concat()
    }
}
