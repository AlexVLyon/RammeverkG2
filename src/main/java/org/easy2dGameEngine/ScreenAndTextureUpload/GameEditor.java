package org.easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEditor {
    private Renderer renderer = new Renderer();
    private static Camera camera = new Camera(new Vector2f(0, 0));
    private List<Entity> entityList = new ArrayList<>();

    private FontBatch batch = new FontBatch();

    boolean firstime = true;

    public void AddTextToScreen(String text, int xpos, int ypos, float scale, int rgbColors) {
        if (firstime) {
            batch.initBatch();
            firstime = false;
        }

        batch.addText(text, xpos, ypos, scale, rgbColors);
        batch.UpdateText();
    }



    public abstract void Update();

    public abstract void init();

    public void AddGameObjectToScreen(Entity entity) {
        StartAndRender.entityList.add(entity);
        StartAndRender.renderer.add(entity);
    }

    public static Camera GetCamera() {
        return camera;
    }
}
