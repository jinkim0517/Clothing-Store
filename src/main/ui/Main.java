package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            ClothingStore store = new ClothingStore();
        } catch (FileNotFoundException e) {
            System.out.println("Application could not run. No save file found.");
        }
    }
}
