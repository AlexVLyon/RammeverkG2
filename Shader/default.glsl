#type vertex
#version 330 core
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCoords;
layout (location=3) in float atextureId;

uniform mat4 uProj;
uniform mat4 uView;

out vec4 fColor;
out vec2 fTexCoords;
out float ftextureId;

void main()
{
    fColor = aColor;
    fTexCoords = aTexCoords;
    ftextureId = atextureId;

    gl_Position = uProj * uView * vec4(aPos, 1.0);
}
    #type fragment
    #version 330 core
in vec4 fColor;
in vec2 fTexCoords;
in float ftextureId;

uniform sampler2D uTextures[16];

out vec4 color;

void main()
{

    int id = int(ftextureId);
    color = fColor * texture(uTextures[id], fTexCoords);
    //color = vec4(fTexCoords, 0, 1);

}


