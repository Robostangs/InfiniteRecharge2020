package frc.RobotTests;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;


import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Shooter;

public class ShooterTester {




    public ShooterTester(){

    }

    public void shooterTest(TalonFX launcherLeft, TalonFX launcherRight) {

        boolean allMotorsFunctional = true;
        List<TalonFX> motors = new ArrayList<TalonFX>();

        Shooter.launchNoPID(0.5, 0.5);


        for(TalonFX motor : motors){
            if(Constants.LAUNCHER_ACCEPTED_MIN_CURRENT >= motor.getStatorCurrent() || motor.getStatorCurrent() >= Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)
            {   
                System.out.println("!!! CURRENT ERROR !!!");
                System.out.println("TalonFX: " + motor + " is currently outputting at " + motor.getStatorCurrent() + " Amps!");
                System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_CURRENT + Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                System.out.println();
                allMotorsFunctional = false;
            }

            if(Constants.MOTOR_ACCEPTED_MIN_VELOCITY >= motor.getSelectedSensorVelocity() || motor.getSelectedSensorVelocity() >= Constants.MOTOR_ACCEPTED_MAX_VELOCITY)
            {   
                System.out.println("!!! VELOCITY ERROR !!!");
                System.out.println("TalonFX: " + motor + " is currently outputting at " + motor.getSelectedSensorVelocity() + " RPM!");
                System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_VELOCITY + Constants.LAUNCHER_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                System.out.println();
                allMotorsFunctional = false;
            }

        }

        Timer.delay(3);

        Shooter.launchNoPID(0, 0);
        if(allMotorsFunctional == true){
                System.out.println("All motors functional");
        }

        


    }








}
