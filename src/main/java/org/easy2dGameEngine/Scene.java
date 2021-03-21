package org.easy2dGameEngine;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Renderer renderer = new Renderer();
    protected static Camera camera;


    public List<Entity> entityList = new ArrayList<>();
    boolean isRunning = false;

    public Scene() {
        start();
    }


    public void start(){
        for(Entity en : entityList) {
            en.Start();
this.renderer.add(en);
        }

isRunning = true;
    }

    public abstract void Update(float dt);
    public void init(){

    }

    public void AddGameObjectsToScreen(Entity add){
        if(!isRunning){
            entityList.add(add);

        }else
        {
            entityList.add(add);
            add.Start();
            this.renderer.add(add);
        }

    }

public static  Camera getcamera(){
        return camera;
}


}
