package model.clothes;

import model.Item;
import org.json.JSONObject;

// SOURCES: A123-Review, Lab5-HelpDesk-Starter
public class Clothing extends Item {

    private String top;
    private String bottom;
    private String footwear;
    private String accessories;

    //EFFECTS: initializes Clothing with top, bottom, footwear and accessories
    public Clothing(String name) {
        super(name);
        top = "";
        bottom = "";
        footwear = "";
        accessories = "";
    }

    public String getTop() {
        return top;
    }

    //MODIFIES: this
    //EFFECTS: modifies top of clothing
    public void setTop(String top) {
        this.top = top;
    }

    //EFFECTS: returns bottom of clothing
    public String getBottom() {
        return bottom;
    }

    //MODIFIES: this
    //EFFECTS: modifies bottom of clothing
    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    //EFFECTS: returns footwear of clothing
    public String getFootwear() {
        return footwear;
    }

    //MODIFIES: this
    //EFFECTS: modifies bottom of footwear
    public void setFootwear(String footwear) {
        this.footwear = footwear;
    }

    //EFFECTS: returns accessories of clothing
    public String getAccessories() {
        return accessories;
    }

    //MODIFIES: this
    //EFFECTS: modifies bottom of accessories
    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("top", top);
        json.put("bottom", bottom);
        json.put("footwear", footwear);
        json.put("accessories", accessories);
        return json;
    }




}
