package org.easy2dGameEngine.Audio;

import org.joml.Vector2f;

public class CharacterInformationBitMap {
    float x;
    float y;
    float width;
    float height;

    public Vector2f[] textureCoordinates = new Vector2f[4];

    public CharacterInformationBitMap(int x, int y, int width, int height) {
        this.x = (float)x;
        this.y = (float)y;
        this.width = (float)width;
        this.height = (float)height;
    }


    public void CalculateTextureCoords(int fontwidth, int fontheight){
        float x0 = (float)x/ (float)fontwidth;
        float x1 = (float)(x +width)/ (float)fontwidth;
        float y0 = (float) ( y - height) / (float)fontheight;
        float y1 = (float)( y ) / (float)fontheight;


        textureCoordinates[0] = new Vector2f(x0,y1);
        textureCoordinates[1] = new Vector2f(x1,y0);



    }
}
