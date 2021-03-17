package org.GameFramework;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


public class EditScene extends Scene {

    public  int positionsize = 3;
    public   int colorssize = 4;
    public   int bytesize = 4;
    public   int vertexsizebytes = (positionsize + colorssize) * bytesize;
    public  int vertexarrayid, vertexbufferid,elementbufferid;
    Shader  myshader;



    public float[] vertexArray={
            //position                      //color
             100.5f,0.5f,0.0f,                1.0f,0.0f,0.0f,1.0f,   //bottom right
            0.5f, 100.5f, 0.0f,              0.0f,1.0f,0.0f,1.0f,   //top left
            100.5f, 100.5f, 0.0f,               0.0f,0.0f,1.0f,1.0f ,   //top right
            0.5f, 0.5f, 0.0f,             1.0f,1.0f,0.0f,1.0f   //bottom left

    };



    public int[] elementArray={
            2,1,0,
            0,1,3


    };

    public EditScene(){

    }


@Override
public void init(){
        this.camera = new Camera(new Vector2f());
        myshader = new Shader("Shader/default.glsl");
myshader.compile();

    vertexarrayid = glGenVertexArrays();
    glBindVertexArray(vertexarrayid);

    FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
    vertexBuffer.put(vertexArray).flip();

    vertexbufferid = glGenBuffers();
    glBindBuffer(GL_ARRAY_BUFFER,vertexbufferid);
    glBufferData(GL_ARRAY_BUFFER,vertexBuffer,GL_STATIC_DRAW);

    IntBuffer elementbuffer = BufferUtils.createIntBuffer(elementArray.length);
    elementbuffer.put(elementArray).flip();

    elementbufferid = glGenBuffers();
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,elementbufferid);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,elementbuffer,GL_STATIC_DRAW);




    glVertexAttribPointer(0,positionsize, GL_FLOAT, false, vertexsizebytes, 0);
    glEnableVertexAttribArray(0);

    glVertexAttribPointer(1,colorssize,GL_FLOAT,false,vertexsizebytes,positionsize*bytesize);
    glEnableVertexAttribArray(1);
}

    @Override
    public void Update(float dt) {

        myshader.use();
        camera.Camerapos.x -= dt * 155.0f;

        myshader.uploadMat4f("uProj", camera.getProjectionMatrix());
        myshader.uploadMat4f("uView", camera.getViewMatrix());
        glBindVertexArray(vertexarrayid);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES,elementArray.length,GL_UNSIGNED_INT,0);



        //disable everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
        myshader.detach();
    }
}
