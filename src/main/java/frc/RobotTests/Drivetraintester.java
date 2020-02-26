package frc.RobotTests;

import java.util.ArrayList;
import java.util.List;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Drivetrain;

public class Drivetraintester {

    private static Drivetrain dt = Drivetrain.getInstance();
    private static boolean allMotorsFunctional = true;

    public static void dtTester(CANSparkMax leftFront, CANSparkMax leftMiddle, CANSparkMax leftBack, CANSparkMax rightFront, CANSparkMax rightMiddle, CANSparkMax rightBack)
    {

        
        List<CANSparkMax> motors = new ArrayList<CANSparkMax>();
        

        motors.add(leftFront);
        motors.add(leftMiddle);
        motors.add(leftBack);
        motors.add(rightFront);
        motors.add(rightMiddle);
        motors.add(rightBack);

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                      D R I V E T R A I N                         ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        dt.driveNoPID(1, 1);
        Timer.delay(5);//Ensure conditions stabilize before recording data
        
        for(CANSparkMax motor : motors){
            
           //returns name of motor for easier analysis
            System.out.println("Testing " + motors.indexOf(motor)+ "...");

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
            

        if(allMotorsFunctional == true){

            System.out.println("All motors working...");

        }

        Timer.delay(10);

       dt.stop();

    }

}




    public static boolean allMotorsFunctional(){
        return allMotorsFunctional;//why was this returning allMotorsFunctional()
    }





    
}