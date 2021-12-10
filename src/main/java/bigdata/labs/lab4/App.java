package bigdata.labs.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;

import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.PathMatchers.segment;

public class App extends AllDirectives {
    private static final int TIMEOUT = 3000;
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;


    private final ActorRef routerActor;

    private App(ActorRef routerActor) {
        this.routerActor = routerActor;
    }

    private Route createRoute() {
        return concat(
                get(
                        () -> pathPrefix("getPackage",
                                () -> path(segment(),
                                        (String id) -> {
                                            Future<Object> result = Patterns.ask(routerActor, id, TIMEOUT);
                                            return completeOKWithFuture(result, Jackson.marshaller());
                                        }
                                ))),
                post(
                        () -> path("postPackage",
                                () -> entity(Jackson.unmarshaller(TestPackage.class),
                                        testPackage -> {
                                            routerActor.tell(testPackage, ActorRef.noSender());
                                            return complete("Start tests");
                                        }))));
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("App");
        ActorRef routerActor = system.actorOf(Props.create(RouterActor.class, system), "routerActor");

        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);

        App instance = new App(routerActor);

        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute().flow(system, materializer);
        CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost(HOSTNAME, PORT),
                materializer);

        String statrMessage = String.format("Server start at ")
    }
}