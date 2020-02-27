package frc.RobotTests;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Shooter;

public class ShooterTester {

    private static Shooter shooter = Shooter.getInstance();
    private static boolean allMotorsFunctional = true;
    private static boolean testFinished = false;
    public static ArrayList<TalonFX> motors = new ArrayList<TalonFX>();

    public ShooterTester(){

    }

    public static void shooterTest(TalonFX launcherLeft, TalonFX launcherRight) {

        int motornum = 0;

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                       S H O O T E R                              ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        motors.add(launcherLeft);
        motors.add(launcherRight);

        shooter.launchNoPID(1, 1);


        for(TalonFX motor : motors){

            System.out.println("Testing " + motors.get(motornum) + "...");
            
            if(Constants.LAUNCHER_ACCEPTED_MIN_CURRENT >= motor.getStatorCurrent() || motor.getStatorCurrent() >= Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)
            {   
                System.out.println("!!! CURRENT ERROR !!!");
                System.out.println("TalonFX: " + motor.getDeviceID() + " is currently outputting at " + motor.getStatorCurrent() + " Amps!");
                System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_CURRENT + Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                System.out.println();
                allMotorsFunctional = false;
            }

            if(Constants.MOTOR_ACCEPTED_MIN_VELOCITY >= motor.getSelectedSensorVelocity() || motor.getSelectedSensorVelocity() >= Constants.MOTOR_ACCEPTED_MAX_VELOCITY)
            {   
                System.out.println("!!! VELOCITY ERROR !!!");
                System.out.println("TalonFX: " + motor.getDeviceID() + " is currently outputting at " + motor.getSelectedSensorVelocity() + " RPM!");
                System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_VELOCITY + Constants.LAUNCHER_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                System.out.println();
                allMotorsFunctional = false;
            }

            motornum = motornum + 1;

        }

        Timer.delay(4);

        shooter.launchNoPID(0, 0);
        if(allMotorsFunctional == true){
            System.out.println("All motors functional");
        }

        

        testFinished = true;


    }


    public static boolean allMotorsFunctional(){
        return allMotorsFunctional();
    }


    public static boolean isTestFinished(){
        return testFinished;
    }





}
