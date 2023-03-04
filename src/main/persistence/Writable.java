package persistence;

import org.json.JSONObject;

// An interface for writable data
// This class was taken from the JsonSerializationDemo from EdX:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java
public interface Writable {
    // EFFECTS: returns the object as a JSObject
    JSONObject toJson();
}
