package org.easy2dGameEngine.Window;

import static org.lwjgl.glfw.GLFW.*;

public class KeyEventListener {

    private static KeyEventListener keyboardispressed = new KeyEventListener();

    public enum KeyEvents {
         A,
         B,
         C,
         D,
         E,
         F,
         G,
         H,
         I,
         J,
         K,
         L,
         M,
         N,
         O,
         P,
         Q,
         R,
         S,
         T,
         U,
         V,
         W,
         X,
         Y,
         Z


    }


    private boolean[] keyPressed = new boolean[350];

    protected static void GetScreenKeyEvents(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keyboardispressed.keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            keyboardispressed.keyPressed[key] = false;
        }

    }

    public static boolean ButtonPressed(KeyEvents key) {
        //spacebar

            return keyboardispressed.keyPressed[key.ordinal() + 65];


    }
}
