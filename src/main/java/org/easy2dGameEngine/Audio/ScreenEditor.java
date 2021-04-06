package org.easy2dGameEngine.Audio;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public abstract class ScreenEditor {
    protected Renderer renderer = new Renderer();

    protected static Camera camera = new Camera(new Vector2f(0,0));


    public List<Entity> entityList = new ArrayList<>();


    public ScreenEditor() {

        start();
    }
    public void Render(){
        this.renderer.render();
    }


    public void start(){
        for(Entity en : entityList) {
            en.Start();
this.renderer.add(en);
            this.renderer.render();
        }


    }

    public abstract void Update();
    public abstract void init();


    public void AddGameObjectsToScreen(Entity add)
    {
            entityList.add(add);
            add.Start();
            this.renderer.add(add);
    }

public Camera getcamera(){
        return this.camera;
}

}
