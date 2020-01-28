package frc.RobotTests;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class Tester {
    public static void test(ArrayList motors){

        boolean allMotorsFunctional = true;
        for(Object m: motors){
            if(m.getClass().equals(TalonFX.class)){
                TalonFX motor = (TalonFX) m;
                motor.set(ControlMode.PercentOutput, 0.5); //will they follow eachother? 
                Timer.delay(2); // delay current check until they hit full speed
                if(Constants.LAUNCHER_ACCEPTED_MIN_CURRENT >= motor.getStatorCurrent() || motor.getStatorCurrent() >= Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)
                 {   
                    System.out.println("!!! CURRENT ERROR !!!");
                    System.out.println("TalonFX: " + motor.getDeviceID() + " is currently outputting at " + motor.getStatorCurrent() + " Amps!");
                    System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_CURRENT + Constants.LAUNCHER_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                    System.out.println();
                    allMotorsFunctional = false;
                }

                if(Constants.LAUNCHER_ACCEPTED_MIN_VELOCITY >= motor.getSelectedSensorVelocity() || motor.getSelectedSensorVelocity() >= Constants.LAUNCHER_ACCEPTED_MAX_VELOCITY)
                {   
                    System.out.println("!!! VELOCITY ERROR !!!");
                    System.out.println("TalonFX: " + motor.getDeviceID() + " is currently outputting at " + motor.getSelectedSensorVelocity() + " RPM!");
                    System.out.println("...Its expected output is around " + (Constants.LAUNCHER_ACCEPTED_MIN_VELOCITY + Constants.LAUNCHER_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                    System.out.println();
                    allMotorsFunctional = false;
                }

                motor.set(ControlMode.PercentOutput, 0);
            }
            if(m.getClass().equals(TalonSRX.class)){
                TalonSRX motor = (TalonSRX) m;
                
            }
            if(m.getClass().equals(CANSparkMax.class)){
                CANSparkMax motor = (CANSparkMax) m;
                motor.set(0.5);
                Timer.delay(2);
                if(Constants.MOTOR_ACCEPTED_MIN_CURRENT >= motor.getOutputCurrent() || motor.getOutputCurrent() >= Constants.MOTOR_ACCEPTED_MAX_CURRENT)
                {   
                    System.out.println("!!! CURRENT ERROR !!!");
                    System.out.println("CANSparkMax: " + motor.getDeviceId() + " is currently outputting at " + motor.getOutputCurrent() + " Amps!");
                    System.out.println("...Its expected output is around " + (Constants.MOTOR_ACCEPTED_MIN_CURRENT + Constants.MOTOR_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                    System.out.println();
                    allMotorsFunctional = false;
                }
    
                if(Constants.MOTOR_ACCEPTED_MIN_VELOCITY >= motor.getEncoder().getVelocity() || motor.getEncoder().getVelocity() >= Constants.MOTOR_ACCEPTED_MAX_VELOCITY)
                {   
                    System.out.println("!!! VELOCITY ERROR !!!");
                    System.out.println("CANSparkMax: " + motor.getDeviceId() + " is currently outputting at " + motor.getEncoder().getVelocity() + " RPM!");
                    System.out.println("...Its expected output is around " + (Constants.MOTOR_ACCEPTED_MIN_VELOCITY + Constants.MOTOR_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                    System.out.println();
                    allMotorsFunctional = false;
                }
                motor.set(0);
            }
        }
        if(allMotorsFunctional == true){

            System.out.println("All motors working...");

        }

        Timer.delay(3);
        
    }
}