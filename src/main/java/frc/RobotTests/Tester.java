package frc.RobotTests;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.MotorController;

public class Tester {
    public static void test(ArrayList<MotorController> motors){

        boolean allMotorsFunctional = true;
        for(MotorController motor: motors){
                if(motor.master==null){
                    motor.set(0.5);
                }
                else{
                    motor.master.set(.5);
                }
                Timer.delay(2); // delay current check until they hit full speed
                if(Constants.LAUNCHER_ACCEPTED_MIN_CURRENT >= motor.getCurrent() || motor.getCurrent() >= Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)
                 {   
                    System.out.println("!!! CURRENT ERROR !!!");
                    System.out.println("TalonFX: " + motor.getDeviceID() + " is currently outputting at " + motor.getCurrent() + " Amps!");
                    System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_CURRENT + Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                    System.out.println();
                    allMotorsFunctional = false;
                }

                if(Constants.LAUNCHER_ACCEPTED_MIN_VELOCITY >= motor.getVelocity() || motor.getVelocity() >= Constants.LAUNCHER_ACCEPTED_MAX_VELOCITY)
                {   
                    System.out.println("!!! VELOCITY ERROR !!!");
                    System.out.println("TalonFX: " + motor.getDeviceID() + " is currently outputting at " + motor.getVelocity() + " RPM!");
                    System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_VELOCITY + Constants.LAUNCHER_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                    System.out.println();
                    allMotorsFunctional = false;
                }
                if(motor.master==null){
                    motor.set(0);
                }
                else{
                    motor.master.set(0);
                }
          
        }
        if(allMotorsFunctional == true){

            System.out.println("All motors working...");

        }

        Timer.delay(3);
        
    }
}