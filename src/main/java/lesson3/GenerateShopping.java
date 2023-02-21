package lesson3;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})

@Data
public class GenerateShopping {

    public List<Object> aisles;

    public Float cost;

    public Integer startDate;

    public Integer endDate;
}