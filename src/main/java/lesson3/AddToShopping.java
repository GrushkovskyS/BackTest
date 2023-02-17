package lesson3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class AddToShopping {


    public Integer id;

    public String name;
@JsonIgnore
    public Measures measures;


    public List<Object> usages;


    public List<Object> usageRecipeIds;

    public Boolean pantryItem;

    public String aisle;

    public Float cost;

    public Integer ingredientId;

}