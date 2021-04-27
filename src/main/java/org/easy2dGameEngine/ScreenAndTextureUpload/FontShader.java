package org.easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.*;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

 class FontShader {

    private int shaderProgramID;
    private boolean beingUsed = false;

    private String vertexSource = "#version 330 core\n" +
            "layout(location=4) in vec2 aPos;\n" +
            "layout(location=5) in vec3 aColor;\n" +
            "layout(location=6) in vec2 aTexCoords;\n" +
            "\n" +
            "out vec2 fTexCoords;\n" +
            "out vec3 fColor;\n" +
            "\n" +
            "uniform mat4 uProjection;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fTexCoords = aTexCoords;\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = uProjection * vec4(aPos, -5, 1);\n" +
            "}";
    private String fragmentSource = " #version 330 core\n" +
            "\n" +
            "in vec2 fTexCoords;\n" +
            "in vec3 fColor;\n" +
            "\n" +
            "uniform sampler2D uFontTexture;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    float c = texture(uFontTexture, fTexCoords).r;\n" +
            "    color = vec4(1, 1, 1, c) * vec4(fColor, 1);\n" +
            "}\n" +
            "\n";
    private String filepath;

    public FontShader() {


        compile();
    }

    public void compile() {
        // ============================================================
        // Compile and link shaders
        // ============================================================
        int vertexID, fragmentID;

        // First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        // First load and compile the vertex shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);


        // Link shaders and check for errors
        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);


    }

    public void use() {
        if (!beingUsed) {
            // Bind shader program
            glUseProgram(shaderProgramID);
            beingUsed = true;
        }
    }

    public void detach() {
        glUseProgram(0);
        beingUsed = false;
    }

    public void uploadMat4f(String varName, Matrix4f mat4) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }


    public void uploadTexture(String varName, int slot) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1i(varLocation, slot);
    }



}


