package org.Easy2dGameEngine.ScreenAndTextureUpload;

import org.joml.Vector2f;

 class CharacterInformationBitMap {
    public float x;
    public float y;
    public float width;
    public float height;

    public Vector2f[] textureBitmapCoordinates = new Vector2f[4];

    public CharacterInformationBitMap(int x, int y, int width, int height) {
        this.x = (float) x;
        this.y = (float) y;
        this.width = (float) width;
        this.height = (float) height;
    }


    public void CalculateCoordiantes(int fontwidth, int fontheight) {
        float x0 = x / (float) fontwidth;
        float x1 = (x + width) / (float) fontwidth;
        float y0 = (y - height) / (float) fontheight;
        float y1 = y / (float) fontheight;


        textureBitmapCoordinates[0] = new Vector2f(x0, y1);
        textureBitmapCoordinates[1] = new Vector2f(x1, y0);


    }
}
