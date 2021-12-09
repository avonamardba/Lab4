package bmstu.bigdata.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TestPackage {
    private String packageId;
    private String functionName;
    private String jsScript;
    private ArrayList<TestMessage> tests;

    public TestPackage(@JsonProperty("packageId") String packageId,
                       @JsonProperty("functionName") String functionName,
                       @JsonProperty("jsScript") String jsScript,
                       @JsonProperty("tests") ArrayList<TestMessage> tests) {
        this.packageId = packageId;
        this.functionName = functionName;
        this.jsScript = jsScript;
        this.tests = tests;
    }

    

}
