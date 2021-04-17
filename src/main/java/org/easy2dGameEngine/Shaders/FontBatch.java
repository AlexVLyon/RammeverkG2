package org.easy2dGameEngine.Shaders;


import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15C.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15C.glGenBuffers;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL31.GL_TEXTURE_BUFFER;

public class FontBatch {

    private int[] indices = {
            0, 1, 3,
            1, 2, 3
    };

    // 25 quads
    public static int BATCH_SIZE = 1000;
    public static int VERTEX_SIZE = 6;
    public float[] vertices = new float[BATCH_SIZE * VERTEX_SIZE];
    public int size = 0;
    private Matrix4f projection = new Matrix4f();

    private int vertexarrayid;
    private int vertexbufferid;
    public FontShader shader;
    public FontRenderer font;




    void GenerateElementBuffer() {
        int elementSize = BATCH_SIZE * 3;
        int[] elementBuffer = new int[elementSize];

        for (int i=0; i < elementSize; i++) {
            elementBuffer[i] = indices[(i % 6)] + ((i / 6) * 4);
        }

        int ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);
    }
    void GenerateShaderPointers(){
        int stride = 7 * Float.BYTES;
        glVertexAttribPointer(4, 2, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(4);

        glVertexAttribPointer(5, 3, GL_FLOAT, false, stride, 2 * Float.BYTES);
        glEnableVertexAttribArray(5);

        glVertexAttribPointer(6, 2, GL_FLOAT, false, stride, 5 * Float.BYTES);
        glEnableVertexAttribArray(6);
    }



    public void initBatch() {

        shader = new FontShader("assets/fontShader.glsl");
        font = new FontRenderer( 22);

        projection.identity();
        projection.ortho(0, 800, 0, 600, 1f, 100f);

        vertexarrayid = glGenVertexArrays();
        glBindVertexArray(vertexarrayid);

        vertexbufferid = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexbufferid);
        glBufferData(GL_ARRAY_BUFFER, Float.BYTES * VERTEX_SIZE * BATCH_SIZE, GL_DYNAMIC_DRAW);

        GenerateElementBuffer();

      GenerateShaderPointers();
    }



    public void UpdateText() {

        // Clear the buffer on the GPU, and then upload the CPU contents, and then draw
        glBindBuffer(GL_ARRAY_BUFFER, vertexbufferid);
        glBufferData(GL_ARRAY_BUFFER, Float.BYTES * VERTEX_SIZE * BATCH_SIZE, GL_DYNAMIC_DRAW);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

        // Draw the buffer that we uploaded
        shader.use();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_BUFFER, font.textureId);
        shader.uploadTexture("uFontTexture", 0);
        shader.uploadMat4f("uProjection", projection);

        glBindVertexArray(vertexarrayid);
        glDrawElements(GL_TRIANGLES, size * 6, GL_UNSIGNED_INT, 0);


        // Reset batch for use on next draw call
        size = 0;
        shader.detach();
    }


    public void addToScreenCharacter(float x, float y, float scale, CharacterInformationBitMap charInfo, int rgb) {
        float r = (float)((rgb >> 16) & 0xFF) / 255.0f;
        float g = (float)((rgb >> 8) & 0xFF) / 255.0f;
        float b = (float)((rgb) & 0xFF) / 255.0f;



        float x1 = x + scale * charInfo.width;
        float y1 = y + scale * charInfo.height;

        float ux0 = charInfo.textureBitmapCoordinates[0].x; float uy0 = charInfo.textureBitmapCoordinates[0].y;
        float ux1 = charInfo.textureBitmapCoordinates[1].x; float uy1 = charInfo.textureBitmapCoordinates[1].y;

        int index = size * 7;
        vertices[index] = x1;      vertices[index + 1] = y;
        vertices[index + 2] = r;   vertices[index + 3] = g;  vertices[index + 4] = b;
        vertices[index + 5] = ux1; vertices[index + 6] = uy0;

        index += 7;
        vertices[index] = x1;      vertices[index + 1] = y1;
        vertices[index + 2] = r;   vertices[index + 3] = g;  vertices[index + 4] = b;
        vertices[index + 5] = ux1; vertices[index + 6] = uy1;

        index += 7;
        vertices[index] = x;      vertices[index + 1] = y1;
        vertices[index + 2] = r;   vertices[index + 3] = g;  vertices[index + 4] = b;
        vertices[index + 5] = ux0; vertices[index + 6] = uy1;

        index += 7;
        vertices[index] = x;      vertices[index + 1] = y;
        vertices[index + 2] = r;   vertices[index + 3] = g;  vertices[index + 4] = b;
        vertices[index + 5] = ux0; vertices[index + 6] = uy0;

        size += 4;
    }

    public void addText(String text, int x, int y, float scale, int rgb) {
        for (int i=0; i < text.length(); i++) {
            char c = text.charAt(i);

            CharacterInformationBitMap charInfo = font.getCharacter(c);

            addToScreenCharacter(x, y, scale, charInfo, rgb);
            x += charInfo.width * scale;

        }
    }
}