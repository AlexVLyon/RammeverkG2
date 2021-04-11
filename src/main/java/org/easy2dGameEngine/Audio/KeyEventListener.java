package org.easy2dGameEngine.Audio;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyEventListener {

    private static KeyEventListener keyboardispressed;



    private boolean[] keyPressed = new boolean[350];

    public static KeyEventListener get(){
        if(keyboardispressed == null)
            return keyboardispressed = new KeyEventListener();

        return keyboardispressed;
    }

    public static void invoke (long window, int key, int scancode, int action, int mods) {
        if ( action == GLFW_PRESS) {
get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }

    }

    public static boolean ButtonPressed(int key){

        return get().keyPressed[key];

    }






}
