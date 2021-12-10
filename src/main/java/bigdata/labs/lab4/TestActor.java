package bigdata.labs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import org.omg.PortableInterceptor.INACTIVE;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestActor extends AbstractActor {
    private final ActorRef storageActor;

    TestActor(ActorRef storageActor) {
        this.storageActor = storageActor;
    }

    private String executeTest(TestMessage testMessage) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(testMessage.getParentPackage().getJsScript());
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(testMessage.getParentPackage().getFunctionName(), testMessage.getParams().toString());
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
