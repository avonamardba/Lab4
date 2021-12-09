package bmstu.bigdata.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    private String testName;
    private String actualResult;
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

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public void setParentPackage(TestPackage parentPackage) {
        this.parentPackage = parentPackage;
    }

    public String getTestName() {
        return this.testName;
    }

    public String getActualResult() {
        return this.actualResult;
    }

    public String getExpectedResult() {
        return this.expectedResult;
    }

    public Object[] getParams() {
        return this.params;
    }

    public TestPackage getParentPackage() {
        return this.parentPackage;
    }

}
