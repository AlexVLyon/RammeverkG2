package org.easy2dGameEngine.Shaders;



import org.joml.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class FontShader {

    private int shaderProgramID;
    private boolean beingUsed = false;

    private String vertexSource = "#version 330 core\n" +
            "layout(location=4) in vec2 APos;\n" +
            "layout(location=5) in vec3 AColor;\n" +
            "layout(location=6) in vec2 ATexCoords;\n" +
            "\n" +
            "out vec2 FTexCoords;\n" +
            "out vec3 FColor;\n" +
            "\n" +
            "uniform mat4 UProjection;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    FTexCoords = ATexCoords;\n" +
            "    FColor = AColor;\n" +
            "    gl_Position = UProjection * vec4(APos, -5, 1);\n" +
            "}";
    private String fragmentSource = "#version 330 core\n" +
            "\n" +
            "in vec2 FTexCoords;\n" +
            "in vec3 FColor;\n" +
            "\n" +
            "uniform sampler2D uFontTexture;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    float c = texture(uFontTexture, FTexCoords).r;\n" +
            "    color = vec4(1, 1, 1, c) * vec4(FColor, 1);\n" +
            "}";

    public FontShader() {


        compile();
    }

    public void compile() {
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

        // Link shaders
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
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(24);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }


    public void uploadTexture(String varName, int slot) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1i(varLocation, slot);
    }

}


