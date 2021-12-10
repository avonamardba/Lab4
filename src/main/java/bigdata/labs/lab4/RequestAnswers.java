package bigdata.labs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RequestAnswer {
    private String packageId;
    private ArrayList<TestResult> tests;

    @JsonCreator
    public RequestAnswer(@JsonProperty("packageId") String packageId,
                         @JsonProperty("tests") ArrayList<TestResult> tests) {
        this.packageId = packageId;
        this.tests = tests;
    }
}
