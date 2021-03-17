package com.company;



import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;


public class Shader {
private int shaderID;

private String vertexSource;
    private String fragmentSource;
    private String filepath;

    public Shader(String file){
this.filepath = file;
try{
String source = new String(Files.readAllBytes(Paths.get(filepath)));
String[] string = source.split("(#type)( )+([a-zA-Z])+");

int index = source.indexOf("#type") + 6;
int endofline = source.indexOf("\r\n", index);
String pattern = source.substring(index,endofline).trim();

    index = source.indexOf("#type", endofline) + 6;
    endofline = source.indexOf("\r\n", index);
    String pattern2 = source.substring(index,endofline).trim();

if(pattern.equals("vertex")){
vertexSource = string[1];
}else if (pattern.equals("fragment")){
    fragmentSource = string[1];
}

    if(pattern2.equals("vertex")){
        vertexSource = string[2];
    }else if (pattern2.equals("fragment")){
        fragmentSource = string[2];
    }

} catch (IOException e) {
e.printStackTrace();
assert false : "error";
}


    }

    public void compile(){
        int vertexID,fragmentID;

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID,vertexSource);
        glCompileShader(vertexID);



        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID ,fragmentSource);
        glCompileShader(fragmentID );


        shaderID = glCreateProgram();
        glAttachShader(shaderID,vertexID);
        glAttachShader(shaderID,fragmentID);
        glLinkProgram(shaderID);



    }

    public void use(){
        glUseProgram(shaderID);

    }

    public void detach(){

        glUseProgram(0);
    }

public void uploadMat4f(String name, Matrix4f mat4){

        int location = glGetUniformLocation(shaderID, name);
    FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
    mat4.get(matBuffer);
    glUniformMatrix4fv(location, false, matBuffer);
}

    public void UploadTexture(String name, int slot){
        int location = glGetUniformLocation(shaderID, name);
        use();
        glUniform1i(location,slot);
    }

    public void uploadIntArray(String name, int[] array){
        int location = glGetUniformLocation(shaderID, name);
        use();
        glUniform1iv(location,array);
    }

}
