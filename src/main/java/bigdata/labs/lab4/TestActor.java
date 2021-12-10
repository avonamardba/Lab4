package bigdata.labs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestActor extends AbstractActor {
    private final ActorRef storageActor;

    TestActor(ActorRef storageActor) {
        this.storageActor = storageActor;
    }

    private String executeTest(TestMessage testMessage) {
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(testMessage.getParentPackage().getJsScript());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        Invocable invocable = (Invocable) engine;
        try {
            return invocable.invokeFunction(testMessage.getParentPackage().getFunctionName(),
                    testMessage.getParams()).toString();
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
