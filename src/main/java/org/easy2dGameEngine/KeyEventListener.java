package org.easy2dGameEngine;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class KeyEventListener {

    private static KeyEventListener keyboardispressed = new KeyEventListener();

     enum KeyEvents {
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
         Z,
         SPACE,

    }


    private boolean[] keyPressed = new boolean[350];

    public static void GetScreenKeyEvents(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keyboardispressed.keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            keyboardispressed.keyPressed[key] = false;
        }

    }

    public static boolean ButtonPressed(KeyEvents key) {
        //spacebar
if(key.ordinal() == 25)
    return keyboardispressed.keyPressed[32];

            return keyboardispressed.keyPressed[key.ordinal() + 65];


    }
}
