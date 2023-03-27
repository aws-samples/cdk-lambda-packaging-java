package helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Party {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
