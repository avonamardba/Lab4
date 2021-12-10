package bigdata.labs.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private final HashMap<String, ArrayList<TestMessage>> storage = new HashMap<>();

    private ArrayList<TestMessage> getTests(String packageId) throws Exception {
        if (this.storage.containsKey(packageId)) {
            return this.storage.get(packageId);
        } else throw new Exception("No such package");
    }

    private RequestAnswer makeResults(String packageId) throws Exception {
        ArrayList<TestResult> answers = new ArrayList<>();
        if (this.storage.containsKey(packageId)) {
            for (TestMessage testMessage : this.getTests(packageId)) {
                String expectedResult = testMessage.getExpectedResult();
                String actualResult = testMessage.getActualResult();
                TestResult testResult = new TestResult(expectedResult,
                        actualResult,
                        actualResult.equals(expectedResult));
                answers.add(testResult);
            }
            
        }
    }

    private void putTest(TestMessage testMessage) {
        String packageId = testMessage.getParent().getPackageId();
        if (this.storage.containsKey(packageId)) {
            this.storage.get(packageId).add(testMessage);
        } else {
            ArrayList<TestMessage> tests = new ArrayList<>();
            tests.add(testMessage);
            this.storage.put(packageId, tests);
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(TestMessage.class, test -> this.putTest(test)).match(String.class, id -> sender().tell());
    }
}
