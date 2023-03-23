package ui;

import model.*;
import ui.menus.MainScreen;

public class StoreGUI {

    private Inventory inventory;

    public StoreGUI() {
        MainScreen mainScreen = new MainScreen(new Inventory());
    }


}
