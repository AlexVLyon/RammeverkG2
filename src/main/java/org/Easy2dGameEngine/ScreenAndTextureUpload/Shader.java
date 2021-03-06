package org.Easy2dGameEngine.ScreenAndTextureUpload;


import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15C.glGenBuffers;
import static org.lwjgl.opengl.GL20.*;


class Shader {
    private int shaderID;
    boolean using;

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "layout (location=2) in vec2 aTexCoord;\n" +
            "layout (location=3) in float aTexId;\n" +
            "\n" +
            "uniform mat4 uProjection;\n" +
            "uniform mat4 uView;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "out vec2 fTexCoord;\n" +
            "out float fTexId;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    fTexCoord = aTexCoord;\n" +
            "    fTexId = aTexId;\n" +
            "\n" +
            "    gl_Position = uProjection * uView * vec4(aPos, 1.0);\n" +
            "}\n";

    private String fragmentShaderSrc = " #version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "in vec2 fTexCoord;\n" +
            "in float fTexId;\n" +
            "\n" +
            "uniform sampler2D uTextures[16];\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    if (fTexId > 0) {\n" +
            "        int id = int(fTexId);\n" +
            "        color = fColor * texture(uTextures[id], fTexCoord);\n" +
            "        //color = vec4(fTexCoord, 0, 1);\n" +
            "    } else {\n" +
            "        color = fColor;\n" +
            "    }\n" +
            "}\n";


    public Shader() {
        compile();
    }


    public void compile() {
        int vertexID, fragmentID;

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);


        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);


        shaderID = glCreateProgram();
        glAttachShader(shaderID, vertexID);
        glAttachShader(shaderID, fragmentID);
        glLinkProgram(shaderID);


    }


    public void use() {
        if (!using) {
            glUseProgram(shaderID);
            using = true;
        }
    }

    public void detach() {

        glUseProgram(0);
        using = false;

    }

    public void uploadMat4f(String name, Matrix4f mat4) {

        int location = glGetUniformLocation(shaderID, name);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(location, false, matBuffer);
    }


    public void uploadIntArray(String name, int[] array) {
        int location = glGetUniformLocation(shaderID, name);
        use();
        glUniform1iv(location, array);
    }

    public void UploadTexture(String name, int slot) {
        int location = glGetUniformLocation(shaderID, name);
        use();
        glUniform1i(location, slot);
    }

}
