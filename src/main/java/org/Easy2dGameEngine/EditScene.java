package org.Easy2dGameEngine;

import org.Easy2dGameEngine.ScreenAndTextureUpload.GameEditor;
import org.Easy2dGameEngine.ScreenAndTextureUpload.KeyEventListener;


import java.util.Random;

public class EditScene extends GameEditor {
    Random rand = new Random();

    public GameObject test, test2;

    int truff = 0;

    public int returnRandomintX() {
        return rand.nextInt(750);
    }

    public int returnRandomintY() {
        return rand.nextInt(550);
    }

    public EditScene() {


    }

    @Override
    public void init() {

        test = new GameObject("assets/images/testImage.png", 411, 311);
        test2 = new GameObject("assets/images/MoreDmg.png", 211, 111);
    }

//depth-buffering

    @Override
    public void Update() {

        AddGameObjectToScreen(test);
        AddGameObjectToScreen(test2);
        GetCamera().followEntity(test2, 100, -400);
        if (test2.Collision(test)) {
            test.setXpos(returnRandomintX());
            test.setYpos(returnRandomintY());
            truff++;
        }

        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.D))
            test.MoveEntityX(10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.A))
            test.MoveEntityX(-10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.W))
            test.MoveEntityY(10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.S)) {
            test.MoveEntityY(-10);
        }

        AddTextToScreen("Antall ganger truffet: " + truff, 100, 500);
        AddTextToScreen("Antall gangeras truffet: " + truff, 100, 200, 1f, 0);
    }
}
