package org.easy2dGameEngine;

import org.easy2dGameEngine.Audio.SoundBuffer;
import org.easy2dGameEngine.Audio.SoundListener;
import org.easy2dGameEngine.Audio.SoundSource;
import org.easy2dGameEngine.ScreenAndTextureUpload.GameEditor;
import org.easy2dGameEngine.ScreenAndTextureUpload.Screen;
import org.joml.Vector3f;

public class Main {

    public static void main(String[] args) {
        Screen s = new Screen();
        s.CreateWindow(1000, 1000, "tt");
        s.ChangeScreenColor(1, 1, 1, 1);
        GameEditor a = new EditScene();

        s.ConnectClassToScreen(a);
        s.run();



        SoundListener marioEars = new SoundListener(new Vector3f(mario.getX(), mario.getY(), 0));

        try {
            SoundBuffer jumpingSound = new SoundBuffer("/sounds/jump.wav");
            SoundSource marioFeet = new SoundSource(false, true);
            marioFeet.setBuffer(jumpingSound.getBufferId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
