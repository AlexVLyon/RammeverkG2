package org.easy2dGameEngine.ScreenAndTextureUpload;

import static org.lwjgl.glfw.GLFW.*;

/**
 * A class that listens to buttons
 */
public class KeyEventListener {

    private static KeyEventListener keyboardispressed = new KeyEventListener();

    /**
     * This is a enum which contains all available buttons to use
     */
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

    /**
     * This method returns true or false based on whether a button is pressed or not
     * @param key Takes in a enum which checks what button is pressed
     * @return returns true or false if the button that is specified is pressed or not
     * @see KeyEvents
     */
    public static boolean ButtonPressed(KeyEvents key) {
            return keyboardispressed.keyPressed[key.ordinal() + 65];
    }
}
