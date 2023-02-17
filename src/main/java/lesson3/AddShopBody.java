package lesson3;

import java.io.Serializable;

public class AddShopBody implements Serializable {

    private static final long serialVersionUID = 1L;


    private String item;

    private String aisle;

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