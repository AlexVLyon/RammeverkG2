package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Camera camera;


    private List<Entity> entityList = new ArrayList<>();
    boolean isRunning;

    public Scene() {
        start();
    }


    public void start(){
        for(Entity en : entityList)
            en.Start();
isRunning = true;
    }

    public abstract void Update(float dt);
    public void init(){

    }




}
