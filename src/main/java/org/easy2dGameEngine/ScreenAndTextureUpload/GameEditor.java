package org.easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;


/**
 * An Abstract class which provides methods that is required to create a game
 */

public abstract class GameEditor {
    private static Camera camera = new Camera(new Vector2f(0, 0));
    private FontBatch batch = new FontBatch();
    boolean firstime = true;

    /**
     * This method will take in string value, int x and y positions, scale value and int RgbColors To Draw on the screen
     *
     * @param text takes in a string value, that is going to be drawn on the screen
     * @param xpos takes in a int value, that specifies the x-location of the text
     * @param ypos takes in a int value that specifies the y-location of the text
     * @param scale takes in a float value that specifies the scale of the text
     * @param rgbColors takes in RGB values which will define the color of the text
     */
    public void AddTextToScreen(String text, int xpos, int ypos, float scale, int rgbColors) {
        if (firstime) {
            batch.initBatch();
            firstime = false;
        }
        batch.addText(text, xpos, ypos, scale, rgbColors);
        batch.UpdateText();
    }

    /**
     * This method will take a string value, int x and y positions
     *
     * @param text takes in a string value, that is going to be drawn on the screen
     * @param xpos takes in a int value that specifies the x-location of the text
     * @param ypos takes in a int value that specifies the y-location of the text
     */
    public void AddTextToScreen(String text, int xpos, int ypos) {
        if (firstime) {
            batch.initBatch();
            firstime = false;
        }
        batch.addText(text, xpos, ypos,  1f, 0xAA01BB);
        batch.UpdateText();
    }

    /**
     *
     * @param text  takes in a string value, that is going to be drawn on the screen
     * @param xpos takes in a int value that specifies the x-location of the text
     * @param ypos takes in a int value that specifies the y-location of the text
     * @param scale takes in a float value that specifies the scale of the text
     */
    public void AddTextToScreen(String text, int xpos, int ypos, float scale) {
        if (firstime) {
            batch.initBatch();
            firstime = false;
        }
        batch.addText(text, xpos, ypos,  scale, 0xAA01BB);
        batch.UpdateText();
    }


    /**
     * An abstract Method which will update the method per frame
     */
    public abstract void Update();

    /**
     * A abstract init method which will initialize objects
     */
    public abstract void init();

    /**
     * This method will take in an entity object which will add the object to the screen
     *
     * @param entity Takes in an entity object to draw on the screen
     */
    public void AddGameObjectToScreen(Entity entity) {
        StartAndRender.entityList.add(entity);
        StartAndRender.renderer.add(entity);
    }

    /**
     *This method gets the camera
     *
     * @return returns the camera of the screen
     * @see Camera
     */
    public static Camera GetCamera() {
        return camera;
    }
}
