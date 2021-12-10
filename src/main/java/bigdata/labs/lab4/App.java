package bigdata.labs.lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

import static akka.http.javadsl.server.PathMatchers.segment;

public class App extends AllDirectives {
    private Route createRoute(ActorRef routerActor) {
        return concat(
                get(
                        () -> pathPrefix("getPackage",
                                () -> path(segment(), (String id) -> {

                                })))
        )
    }
}
