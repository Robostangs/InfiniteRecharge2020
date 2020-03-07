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
    private static boolean testFinished = false;

    public static void dtTester(CANSparkMax leftFront, CANSparkMax leftMiddle, CANSparkMax leftBack, CANSparkMax rightFront, CANSparkMax rightMiddle, CANSparkMax rightBack)
    {
        testFinished = false;   
        List<CANSparkMax> motors = new ArrayList<CANSparkMax>();
        

        motors.add(leftFront);
        motors.add(leftMiddle);
        motors.add(leftBack);
        motors.add(rightFront);
        motors.add(rightMiddle);
        motors.add(rightBack);

        dt.driveNoPID(1, 1);
        Timer.delay(2);

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                      D R I V E T R A I N                         ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        for(int motornum=0; motornum<motors.size();motornum++){
            CANSparkMax motor=motors.get(motornum);
           //returns name of motor for easier analysis
            System.out.println("Testing " + motors.get(motornum).getDeviceId() + "...");
            if(Constants.DT_ACCEPTED_MIN_CURRENT >= motor.getOutputCurrent() || motor.getOutputCurrent() >= Constants.DT_ACCEPTED_MAX_CURRENT)
            {   
                System.out.println("!!! CURRENT ERROR !!!");
                System.out.println("CANSparkMax: " + motor.getDeviceId() + " is currently outputting at " + motor.getOutputCurrent() + " Amps!");
                System.out.println("...Its expected output is around " + (Constants.DT_ACCEPTED_MIN_CURRENT + Constants.DT_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                System.out.println();
                allMotorsFunctional = false;
            }
            if(Constants.DT_ACCEPTED_MIN_VELOCITY >= motor.getEncoder().getVelocity() || motor.getEncoder().getVelocity() >= Constants.DT_ACCEPTED_MAX_VELOCITY)
            {   
                System.out.println("!!! VELOCITY ERROR !!!");
                System.out.println("CANSparkMax: " + motor.getDeviceId() + " is currently outputting at " + motor.getEncoder().getVelocity() + " RPM!");
                System.out.println("...Its expected output is around " + (Constants.DT_ACCEPTED_MIN_VELOCITY + Constants.DT_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                System.out.println();
                allMotorsFunctional = false;
            }

           


            motornum = motornum + 1;
            

        if(allMotorsFunctional == true){

            System.out.println("All motors working...");

        }
       

        Timer.delay(1);

       dt.stop();
  

       testFinished = true;

    }

}




    public static boolean areAllMotorsFunctional(){
        return allMotorsFunctional;
    }



    public static boolean isTestFinished(){
        return testFinished;
    }

    
}