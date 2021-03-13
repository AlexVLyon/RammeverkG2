package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class Font {

    String filePath;
    int fontsize, width, height;

    private Map<Integer, CharacterInformationBitMap> characterMap;

    public Font(String filePath, int fontsize){
        this.filePath = filePath;
        this.fontsize = fontsize;
        this.characterMap = new HashMap<>();
        GenerateBitMap();
    }


    void GenerateBitMap(){
        java.awt.Font font = new java.awt.Font(this.filePath, java.awt.Font.PLAIN, fontsize);

        BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setFont(font);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();

        int estimatedWidth = (int)Math.sqrt(font.getNumGlyphs() ) * font.getSize()+1;
        width = 0;
        height = fontMetrics.getHeight();
        int x = 0;
        int y = (int)(fontMetrics.getHeight() * 1.4f);

        for(int i = 0; i < font.getNumGlyphs(); i++){
            if(font.canDisplay(i)){
                CharacterInformationBitMap characterInformationBitMap = new CharacterInformationBitMap(x,y,fontMetrics.charWidth(i)+11,fontMetrics.getHeight());
                characterMap.put(i,characterInformationBitMap);
                width = Math.max(x + fontMetrics.charWidth(i) , width) ;

                x += characterInformationBitMap.width ;
                if(x > estimatedWidth){
                    x = 0;
                    y += fontMetrics.getHeight() * 1.4f;
                    height += fontMetrics.getHeight() * 1.4f;
                }
            }
        }

        height += fontMetrics.getHeight() * 1.4f;
        graphics2D.dispose();


        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.BLACK);
        for(int i = 0; i < font.getNumGlyphs(); i++){
            if(font.canDisplay(i)){
                CharacterInformationBitMap characterInformationBitMap = characterMap.get(i);
                graphics2D.drawString("" + (char)i, characterInformationBitMap.x, characterInformationBitMap.y);
            }
        }
        graphics2D.dispose();
        try {
            File file = new File("ss2.png");
            ImageIO.write(image,"png",file);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
