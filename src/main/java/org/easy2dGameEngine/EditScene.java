package org.easy2dGameEngine;

import org.easy2dGameEngine.Entity.Entity;
import org.easy2dGameEngine.Entity.GameObject;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.lwjgl.glfw.GLFW.*;

public class EditScene extends GameEditor {
    Random rand = new Random();

    public GameObject test,test2;

    int truff = 0;

    public int returnRandomintX(){
       return rand.nextInt(750);
    }

    public int returnRandomintY(){
        return rand.nextInt(550);
    }
    public EditScene() {


    }

    @Override
    public void init() {

        test = new GameObject("assets/images/testImage.png", 411, 311);
        test2 = new GameObject("assets/images/MoreDmg.png", 211, 111);

test.setHp(1);
test2.setAttack(1212);

    }

//depth-buffering

    @Override
    public void Update() {

        AddGameObjectsToScreen(test);
        AddGameObjectsToScreen(test2);

if(test2.Collision(test)) {
   test.setXpos(returnRandomintX());
    test.setYpos(returnRandomintY());
    truff++;
}

        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.D))
           test2.MoveEntityX(10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.A))
            test2.MoveEntityX(-10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.W))
            test2.MoveEntityY(10);
        if (KeyEventListener.ButtonPressed(KeyEventListener.KeyEvents.S)) {
            test2.MoveEntityY(-10);
        }

        AddTextToScreen("Antall ganger truffet: " + truff  , 100, 500, 1f, 0xAA01BB);
    }
}
