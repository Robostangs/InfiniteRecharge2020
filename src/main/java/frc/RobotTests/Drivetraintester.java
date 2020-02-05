package frc.RobotTests;

import java.util.ArrayList;
import java.util.List;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Drivetrain;

public class Drivetraintester {

    private static Drivetrain dt = new Drivetrain();

    public static void dtTester(MotorController leftFront, MotorController leftMiddle, MotorController leftBack, MotorController rightFront, MotorController rightMiddle, MotorController rightBack)
    {

        
        List<MotorController> motors = new ArrayList<MotorController>();
        boolean allMotorsFunctional = true;

        motors.add(leftFront);
        motors.add(leftMiddle);
        motors.add(leftBack);
        motors.add(rightFront);
        motors.add(rightMiddle);
        motors.add(rightBack);

        dt.driveNoPID(0.8, 0.8);

        
        
        
        for(MotorController motor : motors){
            if(Constants.MOTOR_ACCEPTED_MIN_CURRENT >= motor.getOutputCurrent() || motor.getOutputCurrent() >= Constants.MOTOR_ACCEPTED_MAX_CURRENT)
            {   
                System.out.println("!!! CURRENT ERROR !!!");
                System.out.println("MotorController: " + motor.getName() + " is currently outputting at " + motor.getOutputCurrent() + " Amps!");
                System.out.println("...Its expected output is around " + (Constants.MOTOR_ACCEPTED_MIN_CURRENT + Constants.MOTOR_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                System.out.println();
                allMotorsFunctional = false;
            }

            if(Constants.MOTOR_ACCEPTED_MIN_VELOCITY >= motor.getEncoder().getVelocity() || motor.getEncoder().getVelocity() >= Constants.MOTOR_ACCEPTED_MAX_VELOCITY)
            {   
                System.out.println("!!! VELOCITY ERROR !!!");
                System.out.println("MotorController: " + motor.getName() + " is currently outputting at " + motor.getEncoder().getVelocity() + " RPM!");
                System.out.println("...Its expected output is around " + (Constants.MOTOR_ACCEPTED_MIN_VELOCITY + Constants.MOTOR_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                System.out.println();
                allMotorsFunctional = false;
            }

           



            
        }

        if(allMotorsFunctional == true){

            System.out.println("All motors working...");

        }

        Timer.delay(3);

        dt.driveNoPID(0, 0);

        
    }









    
}
