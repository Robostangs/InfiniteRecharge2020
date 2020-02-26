package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.Timer;

public class Music {

    public static Music instance;

    public static int songSelection;
    public static int timeToPlayLoops;
    public static String[] songs = new String[] {};
    public static Orchestra orchestra_ = new Orchestra();

    public static Music getInstance() {
        if (instance == null)
            instance = new Music();
        return instance;
    }

    public Music() {
        //d
    }

    public static void loadMusicSelection(TalonFX Falcon1, TalonFX Falcon2, TalonFX Falcon3, TalonFX Falcon4, String songSelection) {

        /* 
        **  .chrp files are converted from .midi in pheonix tuner and placed into src/main/deploy~
        */
      
        orchestra_.addInstrument(Falcon1);
        orchestra_.addInstrument(Falcon2);
        orchestra_.addInstrument(Falcon3);
        orchestra_.addInstrument(Falcon4);
        
        orchestra_.loadMusic(songSelection);
        

        Timer.delay(2); //delay so that midi can parse

    }

    public static void play(){
        orchestra_.play();
    }
}