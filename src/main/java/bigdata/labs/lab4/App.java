package bigdata.labs.lab4;

import akka.actor.ActorRef;
import akka.compat.Future;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;

import static akka.http.javadsl.server.PathMatchers.segment;

public class App extends AllDirectives {
    private static final int TIMEOUT = 3000;
    private Route createRoute(ActorRef routerActor) {
        return concat(
                get(
                        () -> pathPrefix("getPackage",
                                () -> path(segment(), (String id) -> {
                                    Future<Object> result = Patterns.ask(routerActor, id, TIMEOUT)
                                })))
        )
    }
}
