package bmstu.bigdata.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    private String testName;
    private String result;
    private String expectedResult;
    private Object[] params;
    private TestPackage parentPackage;

    @JsonCreator
    public TestMessage(@JsonProperty("testName") String testName,
                       @JsonProperty("expectedResult") String expectedResult,
                       @JsonProperty("params") Object[] params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

}
