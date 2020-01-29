package frc.RobotTests;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.Intake;

public class IntakeTester {

    private static Intake IT = new Intake();
    public static boolean allMotorsFunctional = true;

    public static void itTester(TalonSRX belt, TalonSRX ingestor)
    {

        
        List<TalonSRX> motors = new ArrayList<TalonSRX>();
        

        motors.add(belt);
        motors.add(ingestor);
        
        IT.beltMove(1.0);
        IT.ingest(1.0);

        
        
        
        for(TalonSRX motor : motors){
            if(Constants.TALONSRX_ACCEPTED_MIN_CURRENT >= motor.getStatorCurrent() || motor.getStatorCurrent() >= Constants.TALONSRX_ACCEPTED_MAX_CURRENT)
            {   
                System.out.println("!!! CURRENT ERROR !!!");
                System.out.println("TalonSRX: " + motor.getBaseID() + " is currently outputting at " + motor.getStatorCurrent() + " Amps!");
                System.out.println("...Its expected output is around " + (Constants.TALONSRX_ACCEPTED_MIN_CURRENT + Constants.TALONSRX_ACCEPTED_MAX_CURRENT)/2 + " Amps!");
                System.out.println();
                allMotorsFunctional = false;
            }

            //POSSIBLY CHANGE CONVERSION, CURRENTLY 15/50
            if(Constants.TALONSRX_ACCEPTED_MIN_VELOCITY >= motor.getSelectedSensorVelocity()*(15/50) || motor.getSelectedSensorVelocity()*(15/50) >= Constants.TALONSRX_ACCEPTED_MAX_VELOCITY)
            {   
                System.out.println("!!! VELOCITY ERROR !!!");
                System.out.println("TalonSRX: " + motor.getBaseID() + " is currently outputting at " + motor.getSelectedSensorVelocity()*(15/50) + " RPM!");
                System.out.println("...Its expected output is around " + (Constants.TALONSRX_ACCEPTED_MIN_VELOCITY + Constants.TALONSRX_ACCEPTED_MAX_VELOCITY)/2 + " RPM!");
                System.out.println();
                allMotorsFunctional = false;
            }

           



            
        }

        if(allMotorsFunctional == true){

            System.out.println("All motors working...");

        }

        Timer.delay(3);

        IT.stop();

        
    }









    
}