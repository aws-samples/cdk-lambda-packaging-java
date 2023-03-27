package helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Property {
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
}
