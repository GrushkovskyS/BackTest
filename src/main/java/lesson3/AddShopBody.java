package lesson3;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "item",
        "aisle",
        "parse"
})
@Generated("jsonschema2pojo")
public class AddShopBody {

    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("parse")
    private Boolean parse;

    public AddShopBody(String item, String aisle, Boolean parse) {
        this.item = item;
        this.aisle = aisle;
        this.parse = parse;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public Boolean getParse() {
        return parse;
    }

    public void setParse(Boolean parse) {
        this.parse = parse;
    }

    public String string(){
        return "{\n" +
                "    \"item\": \"1 package baking powder\",\n" +
                "    \"aisle\": \"Baking\",\n" +
                "    \"parse\": true\n" +
                "}";
    }
    //    AddShopBody addShopBody = new AddShopBody("1 package baking powder", "Baking",true);
}