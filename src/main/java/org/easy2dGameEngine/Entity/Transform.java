package org.easy2dGameEngine.Entity;

import org.joml.Vector2f;

public class Transform {

    public Vector2f position;
    public Vector2f scale;
    private int DefaultWidth = 256;
    private int DefaultHeight = 256;

    public Transform() {
        initialize(new Vector2f(), new Vector2f());
    }

    public Transform(int x, int y) {

        initialize(new Vector2f(x, y), new Vector2f(DefaultWidth, DefaultHeight));
    }

    public void initialize(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;

    }


    public void AdjustScale(int Width, int Height) {
        this.scale = new Vector2f(Width, Height);
    }

}
