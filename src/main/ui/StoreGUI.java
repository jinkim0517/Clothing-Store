package ui;

import model.*;
import ui.menus.MainScreen;

import java.util.HashMap;

public class StoreGUI {
    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen(new Inventory());
    }
}