package org.easy2dGameEngine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyEventListener {

    private static KeyEventListener keyboardispressed= new KeyEventListener();



    private boolean[] keyPressed = new boolean[350];

    public static void GetScreenKeyEvents(long window, int key, int scancode, int action, int mods) {
        if ( action == GLFW_PRESS) {
            keyboardispressed.keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            keyboardispressed.keyPressed[key] = false;
        }

    }

    public static boolean ButtonPressed(int key){

        return keyboardispressed.keyPressed[key];

    }






}
