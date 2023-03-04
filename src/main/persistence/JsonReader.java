package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// NOTE: This class was taken and then modified from the JsonSerializationDemo from EdX:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        Inventory inventory = new Inventory();
        addClothes(inventory, jsonObject);
        return inventory;
    }

    // MODIFIES: inventory
    // EFFECTS: parses inventory from JSON object and adds them to the inventory
    private void addClothes(Inventory inventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("clothes");
        for (Object json : jsonArray) {
            JSONObject nextClothing = (JSONObject) json;
            addClothing(inventory, nextClothing);
        }
    }

    // MODIFIES: inventory
    // EFFECTS: parses clothes from JSON object and adds it to the inventory
    private void addClothing(Inventory inventory, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String type = jsonObject.getString("type");
        JSONArray sizes = jsonObject.getJSONArray("sizes");
        int sales = jsonObject.getInt("sales");

        Clothing c;
        if (type.equals("Top")) {
            c = new Top(name, price);
        } else if (type.equals("Outerwear")) {
            c = new Outerwear(name, price);
        } else if (type.equals("Bottom")) {
            c = new Bottom(name, price);
        } else {
            c = new Footwear(name, price);
        }

        c.setSizes(jsonArrayToStringArray(sizes));
        c.addSales(sales);

        inventory.addClothing(c);
    }

    // EFFECTS: Converts a JSONArray object into a string array.
    private ArrayList<String> jsonArrayToStringArray(JSONArray jsonArr) {
        ArrayList<String> converted = new ArrayList<>();
        for (Object j: jsonArr) {
            converted.add(j.toString());
        }
        return converted;
    }
}
