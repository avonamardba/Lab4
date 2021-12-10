package bigdata.labs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestResult {
    private String actualResult;
    private String expectedResult;
    private Boolean success;

    @JsonCreator
    public TestResult(@JsonProperty("expectedResult") String expectedResult,
                      @JsonProperty("actualResult") String actualResult,
                      @JsonProperty("success") Boolean success) {
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
        this.success = success;
    }
}
