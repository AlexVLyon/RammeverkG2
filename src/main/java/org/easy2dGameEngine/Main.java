package org.easy2dGameEngine;

import org.easy2dGameEngine.EditScene;
import org.easy2dGameEngine.GameEditor;
import org.easy2dGameEngine.Window.Screen;

public class Main {

    public static void main(String[] args) {
        Screen s = new Screen();
        s.CreateWindow(1000, 1000, "tt");
        s.ChangeScreenColor(1, 1, 1, 1);
        GameEditor a = new EditScene();

        s.ConnectClassToScreen(a);
        s.run();
    }
}
