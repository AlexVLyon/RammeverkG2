package org.easy2dGameEngine;

import org.easy2dGameEngine.Entity.Entity;

import static org.lwjgl.glfw.GLFW.*;

public class EditScene extends GameEditor {


    public Entity entity1, entity2;


    public EditScene() {
    }

    @Override
    public void init() {
        entity1 = new Entity("assets/images/MoreDmg.png", 111, 111);
        entity2 = new Entity("assets/images/testImage.png", 411, 111);
    }

//depth-buffering

    @Override
    public void Update() {
        AddGameObjectsToScreen(entity1);
        AddGameObjectsToScreen(entity2);

        if (KeyEventListener.ButtonPressed(GLFW_KEY_D))
            entity1.MoveEntityX(10);
        if (KeyEventListener.ButtonPressed(GLFW_KEY_A))
            entity1.MoveEntityX(-10);
        if (KeyEventListener.ButtonPressed(GLFW_KEY_W))
            entity1.MoveEntityY(10);
        if (KeyEventListener.ButtonPressed(GLFW_KEY_S)) {
            entity1.MoveEntityY(-10);
        }

        AddTextToScreen("This is text on screen", 100, 200, 1f, 0xAA01BB);
    }
}
