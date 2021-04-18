package org.easy2dGameEngine;

import org.easy2dGameEngine.Entity.Entity;
import org.easy2dGameEngine.Entity.GameObject;

import static org.lwjgl.glfw.GLFW.*;

public class EditScene extends GameEditor {


public GameObject test;

    public EditScene() {
    }

    @Override
    public void init() {

        test = new GameObject("assets/images/testImage.png", 411, 311);

    }

//depth-buffering

    @Override
    public void Update() {
        AddGameObjectsToScreen(test);


        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.D))
           test.MoveEntityX(10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.A))
            test.MoveEntityX(-10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.W))
            test.MoveEntityY(10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.S)) {
            test.MoveEntityY(-10);
        }

        AddTextToScreen("This is text on screen J", 100, 500, 1f, 0xAA01BB);
    }
}
