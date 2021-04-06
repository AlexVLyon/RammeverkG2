package org.easy2dGameEngine.Audio;

public class Main {

    public static void main(String[] args) {


        try
        {
            String filePath = "C:/Users/Mohammed Ali Davami/Desktop/GameEngine/src/com/company/Audio/mixkit-rain-and-thunder-storm-2390.wav";
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
        s.CreateWindow(1000,1000,"Mario");
        //  Texture tex1 = new Texture("assets/images/MoreDmg.png");


        s.run();





    }
}
