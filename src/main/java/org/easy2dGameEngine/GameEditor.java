package org.easy2dGameEngine;





import org.easy2dGameEngine.Entity.Entity;
import org.easy2dGameEngine.Shaders.FontBatch;
import org.easy2dGameEngine.Shaders.Renderer;
import org.easy2dGameEngine.Window.Camera;
import org.joml.Vector2f;



import java.util.ArrayList;
import java.util.List;

public abstract class GameEditor {
    public Renderer renderer = new Renderer();
    public static Camera camera = new Camera(new Vector2f(0,0));
    public List<Entity> entityList = new ArrayList<>();

    private FontBatch batch = new FontBatch();

boolean firstime = true;

public void AddTextToScreen(String text, int xpos, int ypos, float scale, int rgbColors){
if(firstime) {

    batch.initBatch();
    firstime = false;
}
    batch.addText(text, xpos, ypos, scale, rgbColors);
    batch.UpdateText();
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

    public abstract void Update() ;
    public abstract void init();

public void removeGameObject(Entity add){
    entityList.remove(add);
    renderer.RemoveEntity(add);

}


    public void AddGameObjectsToScreen(Entity add)  {




        entityList.add(add);
        add.Start();
        this.renderer.add(add);
    }

public Camera getcamera(){
        return this.camera;
}

}