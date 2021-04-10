#type vertex
#version 330 core
layout(location=4) in vec2 APos;
layout(location=5) in vec3 AColor;
layout(location=6) in vec2 ATexCoords;

out vec2 FTexCoords;
out vec3 FColor;

uniform mat4 UProjection;

void main()
{
    FTexCoords = ATexCoords;
    FColor = AColor;
    gl_Position = UProjection * vec4(APos, -5, 1);
}

    #type fragment
    #version 330 core

in vec2 FTexCoords;
in vec3 FColor;

uniform sampler2D uFontTexture;

out vec4 color;

void main()
{
    float c = texture(uFontTexture, FTexCoords).r;
    color = vec4(1, 1, 1, c) * vec4(FColor, 1);
}