package bigdata.labs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestAnswers {
    private String packageId;

    @JsonCreator
    public RequestAnswers(@JsonProperty("packageId") String packageId) {
        this.packageId = packageId;
    }
}
