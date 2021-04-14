package org.easy2dGameEngine.Shaders;

import org.easy2dGameEngine.CharacterInformationBitMap;
import org.lwjgl.BufferUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;


import static org.lwjgl.opengl.GL11.*;

public class FontRenderer {
    String filePath;
    int fontsize;
    int width;
    int height;
    private Map<Integer, CharacterInformationBitMap> characterMap;
    public int textureId;

    public FontRenderer( int fontSize) {

        this.fontsize = fontSize;
        this.characterMap = new HashMap<>();
        GenerateFontBitmap();
    }

    public CharacterInformationBitMap getCharacter(int codepoint) {
        return characterMap.getOrDefault(codepoint, new CharacterInformationBitMap(0, 0, 0, 0));
    }


    public void GenerateFontBitmap() {
        Font font = new Font(filePath, Font.PLAIN, fontsize);

        // Create fake image to get font information
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(font);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        width = 0;
        height = fontMetrics.getHeight();

        int x = 0;
        int y = (int)(fontMetrics.getHeight() * 1.4f);

        for (int i=0; i < font.getNumGlyphs(); i++) {
            if (font.canDisplay(i)) {
                // Get the sizes for each codepoint glyph, and update the actual image width and height
                CharacterInformationBitMap charInfo = new CharacterInformationBitMap(x, y, fontMetrics.charWidth(i), fontMetrics.getHeight());
                characterMap.put(i, charInfo);
                width = Math.max(x + fontMetrics.charWidth(i), width);

                x += charInfo.width;

            }
        }
        height += fontMetrics.getHeight() * 1.4f;
        g2d.dispose();

        // Create the real texture
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        for (int i=0; i < font.getNumGlyphs(); i++) {
            if (font.canDisplay(i)) {
                CharacterInformationBitMap info = characterMap.get(i);
                info.CalculateCoordiantes(width, height);
                g2d.drawString("" + (char)i, info.x, info.y-5);
            }
        }
        g2d.dispose();

        uploadTexture(img);
    }

    private void uploadTexture(BufferedImage image) {

        int[] pixels = new int[image.getHeight() * image.getWidth()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
        for (int y=0; y < image.getHeight(); y++) {
            for (int x=0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                byte alphaComponent = (byte)((pixel >> 24) & 0xFF);
                buffer.put(alphaComponent);
                buffer.put(alphaComponent);
                buffer.put(alphaComponent);
                buffer.put(alphaComponent);
            }
        }

        buffer.flip();
        textureId = glGenTextures();

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight() ,
                0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        buffer.clear();
    }
}