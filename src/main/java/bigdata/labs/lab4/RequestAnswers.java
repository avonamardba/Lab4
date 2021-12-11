package bigdata.labs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RequestAnswers {
    private String packageId;
    private ArrayList<TestResult> results;

    @JsonCreator
    public RequestAnswers(@JsonProperty("packageId") String packageId) {
        this.packageId = packageId;
        this.results = results;
    }
}
