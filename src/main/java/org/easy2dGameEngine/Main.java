package org.easy2dGameEngine;

import org.easy2dGameEngine.Audio.BackgroundSound;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        // Sound:
        try
        {
            String filePath = "src/main/java/org/easy2dGameEngine/Audio/mixkit-rain-and-thunder-storm-2390.wav";
            BackgroundSound audioPlayer = new BackgroundSound(filePath);
            System.out.println("PLAY");

            audioPlayer.play();
        }

        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
        Screen s = new Screen();
        s.CreateWindow(1000,1000,"asd");

        s.run();






    }
}
