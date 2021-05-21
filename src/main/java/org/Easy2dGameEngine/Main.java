package org.Easy2dGameEngine;

import org.Easy2dGameEngine.Audio.SoundBuffer;
import org.Easy2dGameEngine.Audio.SoundSource;
import org.Easy2dGameEngine.ScreenAndTextureUpload.GameEditor;
import org.Easy2dGameEngine.ScreenAndTextureUpload.Screen;

public class Main {

    public static void main(String[] args) {
        Screen s = new Screen();
        s.CreateWindow(1000, 1000, "tt");
        s.ChangeScreenColor(1, 1, 1, 1);
        GameEditor a = new EditScene();

        s.ConnectClassToScreen(a);
        s.run();


        // SoundListener marioEars = new SoundListener(new Vector3f(mario.getX(), mario.getY(), 0));

        try {
            SoundBuffer jumpingSound = new SoundBuffer("/sounds/jump.wav");
            SoundSource marioFeet = new SoundSource(false, true);
            marioFeet.setBuffer(jumpingSound.getBufferId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
