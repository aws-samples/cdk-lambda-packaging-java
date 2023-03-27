package helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RiskData {
    @JsonProperty("property")
    private Property property;
}
