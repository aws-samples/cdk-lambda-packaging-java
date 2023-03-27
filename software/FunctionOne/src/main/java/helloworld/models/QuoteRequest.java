package helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteRequest {
    @JsonProperty("riskData")
    private RiskData riskData;
    @JsonProperty("party")
    private Party party;
}
