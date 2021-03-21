package org.easy2dGameEngine;

import Components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;


import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class RenderBatch  {


    private final int POS_SIZE = 2;
    private final int COLOR_SIZE = 4;
    private final int POS_OFFSET = 0;
    private final int COLOR_OFFSET = POS_OFFSET + POS_SIZE * Float.BYTES;
    private final int vertex_SIZE = 9;
    private final int VERTEX_SIZE_bytes = vertex_SIZE * Float.BYTES;

    private final int TEXTURE_COORDINATES_SIZE = 2;
    private final int TEXTURE_ID = 1;
    private final int TEXTURE_OFFSET = COLOR_OFFSET + COLOR_SIZE* Float.BYTES;
    private final int TEXTURE_ID_OFFSET = TEXTURE_COORDINATES_SIZE + TEXTURE_OFFSET * Float.BYTES;

    private int[] textureslots = {0, 1, 2, 3, 4, 5, 6, 7};
    private SpriteRenderer[] sprites;
    private int numberOfSprites;
    public  boolean hasSpace;

    private List<Texture> textureList;
    private int vertexarrayID, vertexBufferID;
    private  int maxBatchSize;
    private Shader shader;
    private float[] vertices;





    public RenderBatch(int maxBatchSize) {
            this.sprites = new SpriteRenderer[maxBatchSize];
            this.maxBatchSize = maxBatchSize;
            this.textureList = new ArrayList<>();
            vertices = new float[maxBatchSize * 4 * vertex_SIZE];

            this.numberOfSprites = 0;
            this.hasSpace = true;
        shader = new Shader("Shader/default.glsl");
        shader.compile();
    }





    public void start(){

            vertexarrayID = glGenVertexArrays();
            glBindVertexArray(vertexarrayID);

            vertexBufferID = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
            glBufferData(GL_ARRAY_BUFFER, vertices.length * Float.BYTES, GL_DYNAMIC_DRAW);

            int elementBufferID = glGenBuffers();
            int[] indices = generateIndices();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementBufferID);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

            glVertexAttribPointer(0, POS_SIZE, GL_FLOAT, false, VERTEX_SIZE_bytes, POS_OFFSET);
            glEnableVertexAttribArray(0);


            glVertexAttribPointer(1, COLOR_SIZE, GL_FLOAT, false, VERTEX_SIZE_bytes, COLOR_OFFSET);
            glEnableVertexAttribArray(1);


            glVertexAttribPointer(2, TEXTURE_COORDINATES_SIZE, GL_FLOAT, false, VERTEX_SIZE_bytes, TEXTURE_OFFSET);
            glEnableVertexAttribArray(2);


            glVertexAttribPointer(3, TEXTURE_ID, GL_FLOAT, false, VERTEX_SIZE_bytes, TEXTURE_ID_OFFSET);
            glEnableVertexAttribArray(3);

    }


    public void render() {

    glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
    glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

    shader.use();
        shader.uploadMat4f("uProj", Scene.getcamera().getProjectionMatrix());
        shader.uploadMat4f("uView", Scene.getcamera().getViewMatrix());
    for (int i = 0; i < textureList.size(); i++) {
        glActiveTexture(GL_TEXTURE0 + i + 1);
        textureList.get(i).Bind();

    }
    shader.uploadIntArray("uTextures", textureslots);
    glBindVertexArray(vertexarrayID);
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, this.numberOfSprites * 6, GL_UNSIGNED_INT, 0);

    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
    glBindVertexArray(0);

    for (int i = 0; i < textureList.size(); i++) {
        textureList.get(i).UnBind();
    }

    shader.detach();

    }


    public void addSprite(SpriteRenderer spriteRenderer){
        int index = this.numberOfSprites;
        this.sprites[index] = spriteRenderer;
        this.numberOfSprites++;

if(spriteRenderer.getTexture() != null){
    if(!textureList.contains(spriteRenderer.getTexture()))
        textureList.add(spriteRenderer.getTexture());

}
        loadVertexProperties(index);

        if(numberOfSprites >= this.maxBatchSize)
            this.hasSpace = false;

    }

    public void loadVertexProperties(int index) {
        SpriteRenderer sprite = this.sprites[index];

        int offset = index * 4 * vertex_SIZE;

        Vector4f color = sprite.getColor();
        Vector2f[] texcoordinates = sprite.getTextCoordin();

        int textureid = 0;
        if(sprite.getTexture() != null) {
            for (int i = 0; i < textureList.size(); i++) {
                if(textureList.get(i) == sprite.getTexture()){
                    textureid = i +1 ;
                    break;
                }

            }

        }

        float xAdd = 1.0f;
        float yAdd = 1.0f;
        for(int i = 0; i < 4; i++){
            if(i == 1){
                yAdd = 0.0f;
            }else if (i == 2){
                xAdd = 0.0f;
            }else if(i == 3){
                yAdd = 1.0f;
            }

            vertices[offset] = sprite.entity.transform.position.x + (xAdd * sprite.entity.transform.scale.x);
            vertices[offset +1 ] = sprite.entity.transform.position.y + (yAdd * sprite.entity.transform.scale.y);

            vertices[offset +2 ] = color.x;
            vertices[offset +3 ] = color.y;
            vertices[offset +4 ] = color.z;
            vertices[offset +5 ] = color.w;

            vertices[offset +6] = texcoordinates[i].x;
            vertices[offset +7] = texcoordinates[i].y;

            vertices[offset +8] = textureid;
            offset += vertex_SIZE;
        }
    }

    private int[] generateIndices(){
        int[] elements = new int[6 * maxBatchSize];
        for(int i = 0; i < maxBatchSize; i++) {

            loadElementIndices(elements, i);
         }
        return elements;
        }

        private void loadElementIndices(int[] elements, int index){
        int offsetArrayIndex = 6 * index;
        int offset = 4 * index;

        elements[offsetArrayIndex] = offset + 3;
        elements[offsetArrayIndex+1] = offset +2;
            elements[offsetArrayIndex+2] = offset +0;

            elements[offsetArrayIndex+3] = offset + 0;
            elements[offsetArrayIndex+4] = offset +2;
            elements[offsetArrayIndex+5] = offset +1;


        }
    }



