package model.clothes;

import model.Event;
import model.EventLog;
import model.Item;
import model.ItemList;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Clothes extends ItemList {
    private String outfitName;

    //EFFECTS: initializes outfits to be an array list, with an input of a name for the array list
    public Clothes(String outfitName) {
        super(outfitName);
        this.outfitName = outfitName;
    }

    public JSONObject toJsonClothes() {
        JSONObject json = new JSONObject();
        json.put("o", outfitName);
        json.put("outfits", arrayToJson());
        return json;
    }

    public void removeItem(Item i) {
        EventLog.getInstance().logEvent(new Event("Removed Clothing To List"));
    }
}

