package frc.robot;

import java.util.ArrayList;

public abstract class Subsystems {

    public abstract void checkStart();

    public abstract void stop();

    public abstract ArrayList<MotorController> getMotors();

    public void brake(){
        ArrayList<MotorController> x = this.getMotors();
        
        for(MotorController motor:x){
            motor.set(0);
        }
    }

}