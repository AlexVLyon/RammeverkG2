package org.easy2dGameEngine.Audio;


import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    public String filepath;
    private int textureId;


    public Texture(String filepath){
        //filepath to the texture
        this.filepath = filepath;

        //creates the texture to the GPU
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,textureId);

        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);

        //When stretching, pixalete
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
        //when minimizing the image
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);

        //loading image
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channel = BufferUtils.createIntBuffer(1);
        stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = stbi_load(filepath, width, height, channel, 0);


        if(image != null){
            if(channel.get(0) == 4) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            }
            else if (channel.get(0) == 3){
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0), 0, GL_RGB, GL_UNSIGNED_BYTE, image);
            } else
                assert false : "error with the RGBA";
        }else
            assert false : "Could not find the filepath " + filepath;

        stbi_image_free(image);

    }

    public void Bind(){

        glBindTexture(GL_TEXTURE_2D,textureId);
    }

    public void UnBind(){
        glBindTexture(GL_TEXTURE_2D,0);
    }


}
