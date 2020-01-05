package frc.robot;

import com.revrobotics.CANSparkMax;

public class Shooter{

    public static Shooter instance;
    //shooter motor controllers
    //shooter angle motor controller
    

    public static Shooter getInstance(){
        if (instance == null)
            instance = new Shooter();
        return instance;
    }

    public Shooter(){
        //motor controllers
        //angle controller

        //motor pid
        //angle motor pid
    }

    public static void shoot(double power){
        //set motor controller pid position to double power
    }

    public static void layupAngle(double setAngle){
        //set angle controller pid position to setAngle, setAngle will be contant 
    }

    public static void setAngle(double angle){
        //set angle controller pid position to angle
        //angle will be modified based on distance from reflective tape
    }

    


}
